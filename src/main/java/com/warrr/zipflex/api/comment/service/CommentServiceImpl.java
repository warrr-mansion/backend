package com.warrr.zipflex.api.comment.service;

import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_COMMENT_MODIFY_AUTHORITY;
import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_EXIST_COMMENT;
import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_EXIST_USER;
import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_SIGN_IN;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.comment.dao.CommentDao;
import com.warrr.zipflex.api.comment.dto.in.CommentCreateRequestDto;
import com.warrr.zipflex.api.comment.dto.in.CommentPageRequestDto;
import com.warrr.zipflex.api.comment.dto.in.CommentUpdateRequestDto;
import com.warrr.zipflex.api.comment.dto.out.CommentResponseDto;
import com.warrr.zipflex.api.comment.vo.in.CommentCreateRequestVo;
import com.warrr.zipflex.api.comment.vo.in.CommentUpdateRequestVo;
import com.warrr.zipflex.api.member.dao.MemberDao;
import com.warrr.zipflex.global.exception.BaseException;
import com.warrr.zipflex.global.support.CursorPage;
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
    public void updateComment(Long commentId, CommentUpdateRequestVo requestVo,
                    AuthUserDetail authUserDetail) {

        String memberUuid = getAuthenticatedMemberUuid(authUserDetail);

        if (!getComment(commentId).getMemberUuid().equals(memberUuid)) {
            throw new BaseException(NO_COMMENT_MODIFY_AUTHORITY);
        }

        commentDao.updateComment(CommentUpdateRequestDto.toDto(commentId, requestVo, memberUuid));
    }

    @Override
    public void deleteComment(Long commentId, AuthUserDetail authUserDetail) {
        if (!getComment(commentId).getMemberUuid()
                        .equals(getAuthenticatedMemberUuid(authUserDetail))) {
            throw new BaseException(NO_COMMENT_MODIFY_AUTHORITY);
        }

        commentDao.deleteComment(commentId);
    }

    @Transactional(readOnly = true)
    @Override
    public CursorPage<CommentResponseDto> getComments(CommentPageRequestDto requestDto) {
        List<CommentResponseDto> rawList = commentDao.findAllByHouseInfoIdAndPage(requestDto);

        boolean hasNext = rawList.size() > requestDto.getPageSize();
        List<CommentResponseDto> content =
                        hasNext ? rawList.subList(0, requestDto.getPageSize()) : rawList;
        Long nextCursor = hasNext ? content.get(content.size() - 1).getCommentId() : null;

        return CursorPage.<CommentResponseDto>builder().content(content).hasNext(hasNext)
                        .nextCursor(nextCursor).pageSize(content.size())
                        .pageNo(requestDto.getPageNo()).build();
    }

    @Transactional(readOnly = true)
    @Override
    public CommentResponseDto getComment(Long commentId) {
        return Optional.ofNullable(commentDao.findById(commentId))
                        .orElseThrow(() -> new BaseException(NO_EXIST_COMMENT));
    }

    private String getAuthenticatedMemberUuid(AuthUserDetail authUserDetail) {
        return Optional.ofNullable(authUserDetail).orElseThrow(() -> new BaseException(NO_SIGN_IN))
                        .getUuid();
    }

}
