package com.warrr.zipflex.api.chatbot.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import com.warrr.zipflex.api.chatbot.dto.in.ChatMessageRequestDto;
import com.warrr.zipflex.api.chatbot.dto.out.ChatMessageResponseDto;
import lombok.RequiredArgsConstructor;

@Service
// @Transactional
@RequiredArgsConstructor
public class ChatBotServiceImpl implements ChatBotService {

    private final ChatClient chatClient;

    @Override
    public ChatMessageResponseDto saveMessage(ChatMessageRequestDto requestDto) {
        return ChatMessageResponseDto.toResponse(chatClient.prompt()
                        .system(t -> t.param("language", "korean").param("character", "chill"))
                        .user(requestDto.getMessage()).call().content());
    }

}
