package com.warrr.zipflex.api.comment.service;

import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.comment.vo.in.CommentCreateRequestVo;
import com.warrr.zipflex.api.comment.vo.in.CommentUpdateRequestVo;

public interface CommentService {

    void createComment(CommentCreateRequestVo requestVo, AuthUserDetail authUserDetail);

    void updateComment(Long id, CommentUpdateRequestVo requestVo, AuthUserDetail authUserDetail);

    void deleteComment(Long id, AuthUserDetail authUserDetail);

}
