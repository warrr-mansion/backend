package com.warrr.zipflex.api.favorite.service;

import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.favorite.vo.in.FavoriteRequestVo;
import jakarta.validation.Valid;

public interface FavoriteService {

    void addFavoriteRegion(FavoriteRequestVo requestVo, AuthUserDetail authUserDetail);

}
