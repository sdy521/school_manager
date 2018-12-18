package com.study.study_manager.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping("/main")
    public String main(Model model) {
        return "main";
    }

    @RequestMapping("/")
    public String hello() {
        return "不验证哦";
    }

    @RequestMapping("/security")
    public String security() {
        return "hello world security";
    }

}
