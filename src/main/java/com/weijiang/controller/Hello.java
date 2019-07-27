package com.weijiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Hello {

    @GetMapping("/ABC")
    public String Hellow(@RequestParam("name") String name , Model model){
        model.addAttribute("ename" , name);
        return "hello";
    }
}
