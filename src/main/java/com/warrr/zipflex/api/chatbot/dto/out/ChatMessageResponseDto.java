package com.warrr.zipflex.api.chatbot.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChatMessageResponseDto {

    private String content;

    @Builder
    public ChatMessageResponseDto(String content) {
        this.content = content;
    }
    
    public static ChatMessageResponseDto toDto(String content) {
        return ChatMessageResponseDto.builder()
                        .content(content)
                        .build();
    }
    
}
