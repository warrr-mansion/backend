package com.warrr.zipflex.api.favorite.service;

import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_SIGN_IN;
import static com.warrr.zipflex.global.response.BaseResponseStatus.INVALID_FAVORITE_TYPE;
import static com.warrr.zipflex.global.response.BaseResponseStatus.ALREADY_FAVORITED;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.favorite.dao.FavoriteDao;
import com.warrr.zipflex.api.favorite.dao.FavoriteHouseDao;
import com.warrr.zipflex.api.favorite.dao.FavoriteRegionDao;
import com.warrr.zipflex.api.favorite.domain.model.FavoriteType;
import com.warrr.zipflex.api.favorite.vo.in.FavoriteRequestVo;
import com.warrr.zipflex.global.exception.BaseException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteHouseDao favoriteHouseDao;
    private final FavoriteRegionDao favoriteRegionDao;

    @Transactional
    @Override
    public void addFavoriteRegion(FavoriteRequestVo requestVo, AuthUserDetail authUserDetail) {
        FavoriteDao favoriteDao = getDaoByFavoriteType(requestVo.getFavoriteType());
        String memberUuid = getAuthenticatedMemberUuid(authUserDetail);
        
        if (favoriteDao.existsByCodeAndMemberUuid(requestVo.getCode(),
                        memberUuid)) {
            throw new BaseException(ALREADY_FAVORITED);
        }

        favoriteDao.insert(requestVo.getCode(), memberUuid);
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
