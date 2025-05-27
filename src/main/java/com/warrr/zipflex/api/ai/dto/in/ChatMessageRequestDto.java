package com.warrr.zipflex.api.ai.dto.in;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChatMessageRequestDto {

    private String chatUuid;
    private String message;

}
