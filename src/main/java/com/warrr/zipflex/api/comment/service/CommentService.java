package com.warrr.zipflex.api.comment.service;

import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.comment.vo.in.CommentCreateRequestVo;

public interface CommentService {

    void createComment(CommentCreateRequestVo requestVo, AuthUserDetail authUserDetail);

}
