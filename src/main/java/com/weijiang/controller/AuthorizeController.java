package com.weijiang.controller;

import com.weijiang.entity.AccessToken;
import com.weijiang.entity.GitHubUser;
import com.weijiang.model.User;
import com.weijiang.provider.GithubProvider;
import com.weijiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

//用户登录
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @Value("${github.client.secret}")
    private String secret;
    @Value("${github.client.id}")
    private String id;
    @Value("${github.uri}")
    private String uri;
    //在界面点击登录按钮时，会访问authorize接口，此时会自动跳转到callback地址，并携带code，此时携带code访问github的access_token接口，并返回access_token
    //再访问user接口拿到用户信息

    @GetMapping("/callback")
    public String control(@RequestParam(name = "code") String code , @RequestParam(name = "state") String state , HttpServletResponse response){
        AccessToken accessToken = new AccessToken();
        accessToken.setClient_id(id);
        accessToken.setClient_secret(secret);
        accessToken.setCode(code);
        accessToken.setState(state);
        accessToken.setRedirect_uri(uri);
        String accessToken1 = githubProvider.getAccessToken(accessToken);
        GitHubUser githubuser = githubProvider.getUser(accessToken1);
        if(githubuser != null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setAccountId(String.valueOf(githubuser.getId()));
            user.setToken(token);
            user.setName(githubuser.getName());
            user.setAvartaUrl(githubuser.getAvatar_url());
            userService.createOrupdate(user);
            //获取cookie
            Cookie cookie = new Cookie("token" , token);
            response.addCookie(cookie);
            //request.getSession().setAttribute("user" , githubuser);
            return "redirect:/";
        }
            return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response,
                         HttpServletRequest request){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token" , null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
