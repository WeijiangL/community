package com.weijiang.controller;

import com.weijiang.entity.Pagination;
import com.weijiang.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

//主页
@Controller
public class Hello {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    //接收前台的参数传给相对应的方法，执行对应功能
    public String Hellow(HttpServletRequest request , Model model,
                         @RequestParam(name = "page" , defaultValue = "1") Integer page,
                         @RequestParam(name = "size" , defaultValue = "5") Integer size){

        //分页操作
        Pagination pagination = questionService.list(page, size);
        model.addAttribute("pagination" , pagination);
        return "hello";
    }
}
