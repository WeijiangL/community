package com.weijiang.entity;

import lombok.Data;

@Data
public class CommentDTo {
    private Long parentId;
    private String content;
    private Integer type;
}
