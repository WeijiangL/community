package com.weijiang.entity;

import lombok.Data;

//AccessToken中的参数
@Data
public class AccessToken {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
