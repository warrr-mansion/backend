package com.warrr.zipflex.api.comment.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.comment.service.CommentService;
import com.warrr.zipflex.api.comment.vo.in.CommentCreateRequestVo;
import com.warrr.zipflex.api.comment.vo.in.CommentUpdateRequestVo;
import com.warrr.zipflex.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequestMapping("/v1/auth/comments")
@Tag(name = "Authenticated Comment")
@SecurityRequirement(name = "JWT")
@RequiredArgsConstructor
@RestController
public class AuthRequiredCommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 작성")
    @PostMapping
    public BaseResponse<Void> create(@RequestBody CommentCreateRequestVo requestVo,
                    @AuthenticationPrincipal AuthUserDetail authUserDetail) {

        commentService.createComment(requestVo, authUserDetail);
        return new BaseResponse<>();
    }
    
    @Operation(summary = "댓글 수정")
    @PatchMapping("/{id}")
    public BaseResponse<Void> update(@PathVariable Long id,
                    @RequestBody CommentUpdateRequestVo requestVo,
                    @AuthenticationPrincipal AuthUserDetail authUserDetail) {

        commentService.updateComment(id, requestVo, authUserDetail);
        return new BaseResponse<>();
    }
    
    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable Long id,
                    @AuthenticationPrincipal AuthUserDetail authUserDetail) {

        commentService.deleteComment(id, authUserDetail);
        return new BaseResponse<>();
    }

}
