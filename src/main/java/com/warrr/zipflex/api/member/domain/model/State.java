package com.warrr.zipflex.api.member.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum State {

    ACTIVATION("activation"),
    SLEEP("sleep"),
    AWAIT_DELETION("await_deletion"),
    DELETED("deleted");
    
    private final String state;
    
}
