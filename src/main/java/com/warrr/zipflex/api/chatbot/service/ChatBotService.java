package com.warrr.zipflex.api.chatbot.service;

import com.warrr.zipflex.api.chatbot.dto.in.ChatMessageRequestDto;
import com.warrr.zipflex.api.chatbot.dto.out.ChatMessageResponseDto;

public interface ChatBotService {

    ChatMessageResponseDto saveMessage(ChatMessageRequestDto requestDto);
    
}
