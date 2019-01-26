package com.study.study_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/ueditor")
    public String ueditor(){
        return "/ueditorTest";
    }
}
