package com.weijiang.controller;

import com.weijiang.entity.QuestionDTo;
import com.weijiang.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//问题详情页面
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/questions/{id}")
    public String Question(@PathVariable(name = "id") Long id , Model model){
        QuestionDTo questionDTo = questionService.getById(id);
        model.addAttribute("question" , questionDTo);
        return "question";
    }
}
