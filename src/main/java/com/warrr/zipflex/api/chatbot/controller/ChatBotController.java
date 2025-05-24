package com.warrr.zipflex.api.chatbot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.warrr.zipflex.api.chatbot.dto.in.ChatMessageRequestDto;
import com.warrr.zipflex.api.chatbot.dto.out.ChatMessageResponseDto;
import com.warrr.zipflex.api.chatbot.service.ChatBotService;
import com.warrr.zipflex.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "ChatBot")
@RequestMapping("/v1/chatbot")
@RequiredArgsConstructor
@RestController
public class ChatBotController {

    private final ChatBotService chatBotService;
    
    @Operation(summary = "챗봇 대화")
    @PostMapping
    public BaseResponse<ChatMessageResponseDto> sendMessage(@RequestBody ChatMessageRequestDto requestDto) {
        return new BaseResponse<>(chatBotService.saveMessage(requestDto));
    }
    
}
