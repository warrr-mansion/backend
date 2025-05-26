package com.warrr.zipflex.api.favorite.service;

import static com.warrr.zipflex.global.response.BaseResponseStatus.ALREADY_FAVORITED;
import static com.warrr.zipflex.global.response.BaseResponseStatus.INVALID_FAVORITE_TYPE;
import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_SIGN_IN;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.favorite.dao.FavoriteDao;
import com.warrr.zipflex.api.favorite.dao.FavoriteHouseDao;
import com.warrr.zipflex.api.favorite.dao.FavoriteRegionDao;
import com.warrr.zipflex.api.favorite.domain.model.FavoriteType;
import com.warrr.zipflex.api.favorite.dto.in.FavoritePageRequestDto;
import com.warrr.zipflex.api.favorite.dto.out.FavoriteHouseResponseDto;
import com.warrr.zipflex.api.favorite.dto.out.FavoriteRegionResponseDto;
import com.warrr.zipflex.api.favorite.vo.in.FavoriteRequestVo;
import com.warrr.zipflex.global.exception.BaseException;
import com.warrr.zipflex.global.support.CursorPage;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteHouseDao favoriteHouseDao;
    private final FavoriteRegionDao favoriteRegionDao;

    @Override
    public void addFavoriteRegion(FavoriteRequestVo requestVo, AuthUserDetail authUserDetail) {
        FavoriteDao favoriteDao = getDaoByFavoriteType(requestVo.getFavoriteType());
        String memberUuid = getAuthenticatedMemberUuid(authUserDetail);

        if (favoriteDao.existsByCodeAndMemberUuid(requestVo.getCode(), memberUuid)) {
            throw new BaseException(ALREADY_FAVORITED);
        }

        favoriteDao.insert(requestVo.getCode(), memberUuid);
    }

    @Override
    public void removeFavoriteRegion(FavoriteRequestVo requestVo, AuthUserDetail authUserDetail) {
        getDaoByFavoriteType(requestVo.getFavoriteType()).deleteByCodeAndMemberUuid(
                        requestVo.getCode(), getAuthenticatedMemberUuid(authUserDetail));
    }

    @Transactional(readOnly = true)
    @Override
    public CursorPage<?> getFavoritesByPage(FavoritePageRequestDto requestDto,
                    AuthUserDetail authUserDetail) {

        FavoriteDao favoriteDao = getDaoByFavoriteType(requestDto.getFavoriteType());
        String memberUuid = getAuthenticatedMemberUuid(authUserDetail);

        return switch (requestDto.getFavoriteType()) {
            case HOUSE -> {
                @SuppressWarnings("unchecked")
                List<FavoriteHouseResponseDto> rawList = (List<FavoriteHouseResponseDto>) favoriteDao
                                .findAllByMemberUuidAndPage(memberUuid, requestDto);

                boolean hasNext = rawList.size() > requestDto.getPageSize();
                List<FavoriteHouseResponseDto> content =
                                hasNext ? rawList.subList(0, requestDto.getPageSize()) : rawList;
                Long nextCursor = hasNext ? content.get(content.size() - 1).getId() : null;
                
                yield CursorPage.<FavoriteHouseResponseDto>builder().content(content).hasNext(hasNext)
                                .nextCursor(nextCursor).pageSize(content.size()).build();
            }

            case REGION -> {
                @SuppressWarnings("unchecked")
                List<FavoriteRegionResponseDto> rawList = (List<FavoriteRegionResponseDto>) favoriteDao
                                .findAllByMemberUuidAndPage(memberUuid, requestDto);

                boolean hasNext = rawList.size() > requestDto.getPageSize();
                List<FavoriteRegionResponseDto> content =
                                hasNext ? rawList.subList(0, requestDto.getPageSize()) : rawList;
                Long nextCursor = hasNext ? content.get(content.size() - 1).getId() : null;
                
                yield CursorPage.<FavoriteRegionResponseDto>builder().content(content).hasNext(hasNext)
                                .nextCursor(nextCursor).pageSize(content.size()).build();
            }
        };
    }

    private String getAuthenticatedMemberUuid(AuthUserDetail authUserDetail) {
        return Optional.ofNullable(authUserDetail).orElseThrow(() -> new BaseException(NO_SIGN_IN))
                        .getUuid();
    }

    private FavoriteDao getDaoByFavoriteType(FavoriteType favoriteType) {
        switch (favoriteType) {
            case REGION:
                return favoriteRegionDao;
            case HOUSE:
                return favoriteHouseDao;
            default:
                throw new BaseException(INVALID_FAVORITE_TYPE);
        }
    }

}
