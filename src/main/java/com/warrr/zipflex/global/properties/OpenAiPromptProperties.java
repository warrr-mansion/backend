package com.warrr.zipflex.global.properties;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "spring.ai.openai")
@Getter
@Setter
public class OpenAiPromptProperties {
    
    private Map<String, String> prompts;
    
}
