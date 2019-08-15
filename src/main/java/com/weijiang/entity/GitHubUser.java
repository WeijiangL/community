package com.weijiang.entity;

import lombok.Data;

//用户信息
@Data
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
