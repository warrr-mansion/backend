package com.warrr.zipflex.api.ai.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {

    MEMBER("member"), 
    ASSISTANT("assistant");

    private final String roleType;

}
