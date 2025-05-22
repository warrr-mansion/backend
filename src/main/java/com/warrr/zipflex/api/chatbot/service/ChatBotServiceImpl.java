package com.warrr.zipflex.api.chatbot.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.warrr.zipflex.api.chatbot.domain.document.ChatMessage;
import com.warrr.zipflex.api.chatbot.dto.in.ChatMessageRequestDto;
import com.warrr.zipflex.api.chatbot.dto.out.ChatMessageResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatBotServiceImpl implements ChatBotService {

    private final ChatClient chatClient;
    private final ChatBotAsyncProcessor chatBotAsyncProcessor;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    private static final String TOKEN_PREFIX = "chatUuid";
    private static final int MAX_CHAT_HISTORY = 20;

    @Override
    @Transactional(readOnly = true)
    public ChatMessageResponseDto saveMessage(ChatMessageRequestDto requestDto) {
        String key = TOKEN_PREFIX + requestDto.getChatUuid();
        String assistMessage = chatClient.prompt()
                        .system(t -> t.param("language", "korean").param("character", "chill"))
                        .messages(redisTemplate.opsForList().range(key, -MAX_CHAT_HISTORY, -1)
                                        .stream().map(obj -> objectMapper.convertValue(obj, ChatMessage.class)
                                                        .toAiMessage()).toList())
                        .user(requestDto.getMessage()).call().content();

        chatBotAsyncProcessor.saveMessageAsync(key, requestDto.getMessage(), assistMessage);
        return ChatMessageResponseDto.toDto(assistMessage);
    }

}
