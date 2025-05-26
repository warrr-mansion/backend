package com.warrr.zipflex.api.comment.dao;

import org.apache.ibatis.annotations.Mapper;
import com.warrr.zipflex.api.comment.dto.CommentCreateRequestDto;

@Mapper
public interface CommentDao {

    void insertComment(CommentCreateRequestDto requestDto);
    
}
