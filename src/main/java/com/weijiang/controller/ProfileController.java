package com.weijiang.controller;

import com.weijiang.entity.Pagination;
import com.weijiang.model.User;
import com.weijiang.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

//个人问题页面
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    //发布问题的列表
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action , Model model,
                          HttpServletRequest request ,
                          @RequestParam(name = "page" , defaultValue = "1") Integer page,
                          @RequestParam(name = "size" , defaultValue = "5") Integer size){
        if ("questions".equals(action)){
            model.addAttribute("section" , "questions");
            model.addAttribute("sectionName" , "我的问题列表");
        } else if ("replies".equals(action)){
            model.addAttribute("section" , "replies");
            model.addAttribute("sectionName" , "最新回复");
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }

        Pagination pagination = questionService.list(user.getId() , page , size);
        model.addAttribute("pagination" , pagination);
        return "profile";
    }
}
