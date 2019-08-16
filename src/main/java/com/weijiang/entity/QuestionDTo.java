package com.weijiang.entity;

import com.weijiang.model.User;
import lombok.Data;

@Data
public class QuestionDTo {
    private Long id;
    private String title;
    private String description;
    private long gmt_create;
    private long gmt_modified;
    private String tag;
    private int comment_count;
    private int view_count;
    private int like_count;
    private int creator;
    private User user;
}
