package com.warrr.zipflex.api.ai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.warrr.zipflex.api.ai.dto.in.ChatMessageRequestDto;
import com.warrr.zipflex.api.ai.dto.out.ChatMessageResponseDto;
import com.warrr.zipflex.api.ai.dto.out.CommentSummaryResponseDto;
import com.warrr.zipflex.api.ai.service.OpenAiService;
import com.warrr.zipflex.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Open AI")
@RequestMapping("/v1")
@RequiredArgsConstructor
@RestController
public class OpenAiController {

    private final OpenAiService openAiService;
    
    @Operation(summary = "챗봇 대화")
    @PostMapping("/chatbot")
    public BaseResponse<ChatMessageResponseDto> sendMessage(@RequestBody ChatMessageRequestDto requestDto) {
        return new BaseResponse<>(openAiService.saveMessage(requestDto));
    }
    
    @Operation(summary = "댓글 요약 조회")
    @GetMapping("/houses/{houseInfoId}/comments/summary")
    public BaseResponse<CommentSummaryResponseDto> summary(@PathVariable Long houseInfoId) {
        return new BaseResponse<>(openAiService.getCommentSummary(houseInfoId));
    }
    
}
