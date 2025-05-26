package com.warrr.zipflex.api.comment.service;

import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.comment.dto.in.CommentPageRequestDto;
import com.warrr.zipflex.api.comment.dto.out.CommentResponseDto;
import com.warrr.zipflex.api.comment.vo.in.CommentCreateRequestVo;
import com.warrr.zipflex.api.comment.vo.in.CommentUpdateRequestVo;
import com.warrr.zipflex.global.support.CursorPage;

public interface CommentService {

    void createComment(CommentCreateRequestVo requestVo, AuthUserDetail authUserDetail);

    void updateComment(Long id, CommentUpdateRequestVo requestVo, AuthUserDetail authUserDetail);

    void deleteComment(Long id, AuthUserDetail authUserDetail);

    CursorPage<CommentResponseDto> getComments(CommentPageRequestDto requestDto);

}
