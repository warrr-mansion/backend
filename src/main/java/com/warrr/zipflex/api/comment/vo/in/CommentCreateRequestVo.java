package com.warrr.zipflex.api.comment.vo.in;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommentCreateRequestVo {

    private Long houseInfoId;
    private String content;
    
}
