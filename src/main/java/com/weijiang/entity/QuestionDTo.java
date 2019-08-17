package com.weijiang.entity;

import com.weijiang.model.User;
import lombok.Data;

@Data
public class QuestionDTo {
    private Long id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private String tag;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private int creator;
    private User user;
}
