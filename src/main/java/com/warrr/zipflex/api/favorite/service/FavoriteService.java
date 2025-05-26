package com.warrr.zipflex.api.favorite.service;

import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.favorite.dto.in.FavoritePageRequestDto;
import com.warrr.zipflex.api.favorite.vo.in.FavoriteRequestVo;
import com.warrr.zipflex.global.support.CursorPage;

public interface FavoriteService {

    void addFavoriteRegion(FavoriteRequestVo requestVo, AuthUserDetail authUserDetail);

    void removeFavoriteRegion(FavoriteRequestVo requestVo, AuthUserDetail authUserDetail);

    CursorPage<?> getFavoritesByPage(FavoritePageRequestDto requestDto,
                    AuthUserDetail authUserDetail);

}
