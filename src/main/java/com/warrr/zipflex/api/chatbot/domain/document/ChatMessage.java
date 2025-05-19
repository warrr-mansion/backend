package com.warrr.zipflex.api.chatbot.domain.document;

import static com.warrr.zipflex.global.response.BaseResponseStatus.INVALID_ROLE;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import com.warrr.zipflex.api.chatbot.domain.model.RoleType;
import com.warrr.zipflex.global.exception.BaseException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ChatMessage {
    
    private RoleType role;
    private String content;

    @Builder
    public ChatMessage(RoleType role, String content) {
        this.role = role;
        this.content = content;
    }
    
    public static ChatMessage toDocument(String role, String content) {
        return ChatMessage.builder()
                        .role(RoleType.valueOf(role.toUpperCase()))
                        .content(content)
                        .build();
    }
    
    public Message toAiMessage() {
        return switch (role) {
            case MEMBER -> new UserMessage(content);
            case ASSISTANT -> new AssistantMessage(content);
            default -> throw new BaseException(INVALID_ROLE);
        };
    }
    
}
