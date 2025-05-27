package com.warrr.zipflex.api.ai.service;

import com.warrr.zipflex.api.ai.dto.in.ChatMessageRequestDto;
import com.warrr.zipflex.api.ai.dto.out.ChatMessageResponseDto;

public interface ChatBotService {

    ChatMessageResponseDto saveMessage(ChatMessageRequestDto requestDto);
    
}
