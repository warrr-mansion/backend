package com.warrr.zipflex.api.chatbot.service;

import static com.warrr.zipflex.api.chatbot.domain.model.RoleType.ASSISTANT;
import static com.warrr.zipflex.api.chatbot.domain.model.RoleType.MEMBER;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.warrr.zipflex.api.chatbot.domain.document.ChatMessage;
import com.warrr.zipflex.api.chatbot.domain.model.RoleType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatBotAsyncProcessor {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    private static final int MAX_CHAT_HISTORY = 10;

    @Async
    public void saveMessageAsync(String key, String memberMessage, String assistMessage) {
        pushMessage(key, MEMBER, memberMessage);
        pushMessage(key, ASSISTANT, assistMessage);

        // trimChatHistory(key);
    }

    @Transactional
    private void pushMessage(String key, RoleType roleType, String content) {
        try {
            String json = objectMapper.writeValueAsString(
                            ChatMessage.toDocument(roleType.getRoleType(), content));
            redisTemplate.opsForList().rightPush(key, json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("메시지 직렬화 실패", e);
        }
    }

    @Transactional
    private void trimChatHistory(String key) {
        redisTemplate.opsForList().trim(key, -MAX_CHAT_HISTORY, -1);
    }

}
