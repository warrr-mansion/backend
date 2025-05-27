package com.warrr.zipflex.api.member.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    VISITOR("visitor"),
    SELLER("seller"),
    ADMIN("admin");
    
    private final String role;
    
}
