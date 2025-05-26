package com.warrr.zipflex.api.comment.service;

import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_SIGN_IN;
import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_EXIST_USER;
import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_EXIST_COMMENT;
import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_COMMENT_MODIFY_AUTHORITY;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.comment.dao.CommentDao;
import com.warrr.zipflex.api.comment.dto.in.CommentCreateRequestDto;
import com.warrr.zipflex.api.comment.dto.in.CommentUpdateRequestDto;
import com.warrr.zipflex.api.comment.vo.in.CommentCreateRequestVo;
import com.warrr.zipflex.api.comment.vo.in.CommentUpdateRequestVo;
import com.warrr.zipflex.api.member.dao.MemberDao;
import com.warrr.zipflex.global.exception.BaseException;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final MemberDao memberDao;

    @Override
    public void createComment(CommentCreateRequestVo requestVo, AuthUserDetail authUserDetail) {
        String memberUuid = getAuthenticatedMemberUuid(authUserDetail);

        commentDao.insertComment(CommentCreateRequestDto.toDto(requestVo, memberUuid,
                        Optional.ofNullable(memberDao.findByUuid(memberUuid))
                                        .orElseThrow(() -> new BaseException(NO_EXIST_USER))
                                        .getNickname()));
    }

    @Override
    public void updateComment(Long id, CommentUpdateRequestVo requestVo,
                    AuthUserDetail authUserDetail) {

        String memberUuid = getAuthenticatedMemberUuid(authUserDetail);

        if (!Optional.ofNullable(commentDao.findById(id))
                        .orElseThrow(() -> new BaseException(NO_EXIST_COMMENT)).getMemberUuid()
                        .equals(memberUuid)) {
            throw new BaseException(NO_COMMENT_MODIFY_AUTHORITY);
        }

        commentDao.updateComment(CommentUpdateRequestDto.toDto(id, requestVo, memberUuid));
    }

    @Override
    public void deleteComment(Long id, AuthUserDetail authUserDetail) {
        if (!Optional.ofNullable(commentDao.findById(id))
                        .orElseThrow(() -> new BaseException(NO_EXIST_COMMENT)).getMemberUuid()
                        .equals(getAuthenticatedMemberUuid(authUserDetail))) {
            throw new BaseException(NO_COMMENT_MODIFY_AUTHORITY);
        }

        commentDao.deleteComment(id);
    }

    private String getAuthenticatedMemberUuid(AuthUserDetail authUserDetail) {
        return Optional.ofNullable(authUserDetail).orElseThrow(() -> new BaseException(NO_SIGN_IN))
                        .getUuid();
    }

}
