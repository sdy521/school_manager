package com.study.study_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }
}
