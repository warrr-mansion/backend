package com.warrr.zipflex.api.comment.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.warrr.zipflex.api.comment.dto.in.CommentCreateRequestDto;
import com.warrr.zipflex.api.comment.dto.in.CommentPageRequestDto;
import com.warrr.zipflex.api.comment.dto.in.CommentUpdateRequestDto;
import com.warrr.zipflex.api.comment.dto.out.CommentResponseDto;

@Mapper
public interface CommentDao {

    void insertComment(CommentCreateRequestDto requestDto);

    void updateComment(CommentUpdateRequestDto requestDto);

    CommentResponseDto findById(Long id);

    void deleteComment(Long id);

    List<CommentResponseDto> findAllByHouseInfoIdAndPage(CommentPageRequestDto requestDto);

}
