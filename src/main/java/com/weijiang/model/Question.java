package com.weijiang.model;

import lombok.Data;

@Data
public class Question {
    private int id;
    private String title;
    private String description;
    private long gmt_create;
    private long gmt_modified;
    private String tag;
    private int comment_count;
    private int view_count;
    private int like_count;
    private int creator;
}
