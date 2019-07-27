package com.weijiang.controller;

import com.weijiang.entity.AccessToken;
import com.weijiang.entity.GitHubUser;
import com.weijiang.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.secret}")
    private String secret;
    @Value("${github.client.id}")
    private String id;
    @Value("${github.uri}")
    private String uri;
    //在界面点击登录按钮时，会访问authorize接口，此时会自动跳转到callback地址，并携带code，此时携带code访问github的access_token接口，并返回access_token
    //再访问user接口拿到用户信息

    @GetMapping("/callback")
    public String control(@RequestParam(name = "code") String code , @RequestParam(name = "state") String state){
        AccessToken accessToken = new AccessToken();
        accessToken.setClient_id(id);
        accessToken.setClient_secret(secret);
        accessToken.setCode(code);
        accessToken.setState(state);
        accessToken.setRedirect_uri(uri);
        String accessToken1 = githubProvider.getAccessToken(accessToken);
        GitHubUser user = githubProvider.getUser(accessToken1);
        System.out.println(user.getName());
        return "hello";
    }

}
