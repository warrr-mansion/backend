package com.warrr.zipflex.api.ai.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PromptDomain {

    COMMENT_SUMMARY("comment-summary"),
    CHATBOT("chatbot");
    
    private final String promptDomain;
    
}
