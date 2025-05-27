package com.warrr.zipflex.api.ai.service;

import static com.warrr.zipflex.api.ai.domain.model.PromptDomain.CHATBOT;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.warrr.zipflex.api.ai.domain.document.ChatMessage;
import com.warrr.zipflex.api.ai.dto.in.ChatMessageRequestDto;
import com.warrr.zipflex.api.ai.dto.out.ChatMessageResponseDto;
import com.warrr.zipflex.global.properties.OpenAiPromptProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatBotServiceImpl implements ChatBotService {

    private final ChatClient.Builder chatClientBuilder;
    private final OpenAiPromptProperties promptProperties;
    private final ChatBotAsyncProcessor chatBotAsyncProcessor;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    private static final String TOKEN_PREFIX = "chatUUID:";
    private static final int MAX_CHAT_HISTORY = 10;

    @Override
    @Transactional(readOnly = true)
    public ChatMessageResponseDto saveMessage(ChatMessageRequestDto requestDto) {
        String key = TOKEN_PREFIX + requestDto.getChatUuid();
        String prompt = promptProperties.getPrompts().get(CHATBOT.getPromptDomain());
        log.info("prompt: {}, {}", prompt, CHATBOT.getPromptDomain());
        ChatClient chatClient = chatClientBuilder.defaultSystem(prompt).build();
        
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
