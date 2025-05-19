package com.warrr.zipflex.api.chatbot.dto.in;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChatMessageRequestDto {

    private String chatUuid;
    private String message;

}
