package com.warrr.zipflex.api.favorite.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FavoriteType {

    REGION("legal_district"),
    HOUSE("house_info");
    
    private final String favoriteType;
    
}
