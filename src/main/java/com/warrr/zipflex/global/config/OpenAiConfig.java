package com.warrr.zipflex.global.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {
    
    @Value("${spring.ai.openai.system-prompt}")
    private String prompt;

    @Bean
    ChatClient buildChatClient(ChatClient.Builder builder) {
        return builder.defaultSystem(prompt).build();
    }
    
}
