package com.warrr.zipflex.api.ai.service;

import com.warrr.zipflex.api.ai.dto.in.ChatMessageRequestDto;
import com.warrr.zipflex.api.ai.dto.out.ChatMessageResponseDto;
import com.warrr.zipflex.api.ai.dto.out.CommentSummaryResponseDto;

public interface OpenAiService {

    ChatMessageResponseDto saveMessage(ChatMessageRequestDto requestDto);

    CommentSummaryResponseDto getCommentSummary(Long houseInfoId);
    
}
