package com.warrr.zipflex.api.chatbot.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChatMessageResponseDto {

    private String message;

    @Builder
    public ChatMessageResponseDto(String message) {
        this.message = message;
    }
    
    public static ChatMessageResponseDto toResponse(String message) {
        return ChatMessageResponseDto.builder()
                        .message(message)
                        .build();
    }
    
}
