package com.warrr.zipflex.api.house.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BuildingType {

    APARTMENT("apartment"),
    VILLA("villa"),
    OFFICETEL("officetel"),
    UNKNOWN("unknown");
    
    private final String buildingType;
}
