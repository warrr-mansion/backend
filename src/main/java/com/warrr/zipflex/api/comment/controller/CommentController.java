package com.warrr.zipflex.api.comment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.warrr.zipflex.api.comment.dto.in.CommentPageRequestDto;
import com.warrr.zipflex.api.comment.dto.out.CommentResponseDto;
import com.warrr.zipflex.api.comment.service.CommentService;
import com.warrr.zipflex.global.response.BaseResponse;
import com.warrr.zipflex.global.support.CursorPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequestMapping("/v1")
@Tag(name = "Comment")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 목록 조회 (페이지네이션)")
    @GetMapping("/houses/{houseInfoId}/comments")
    public BaseResponse<CursorPage<CommentResponseDto>> list(@PathVariable Long houseInfoId,
                    @RequestParam(defaultValue = "1") int pageNo,
                    @RequestParam(defaultValue = "10") int pageSize) {

        return new BaseResponse<>(commentService
                        .getComments(CommentPageRequestDto.toDto(houseInfoId, pageNo, pageSize)));
    }

    @Operation(summary = "댓글 단건 조회")
    @GetMapping("/comments/{commentId}")
    public BaseResponse<CommentResponseDto> detail(@PathVariable Long commentId) {

        return new BaseResponse<>(commentService.getComment(commentId));
    }
    
}
