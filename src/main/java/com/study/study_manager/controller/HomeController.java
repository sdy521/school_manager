package com.study.study_manager.controller;

import com.study.study_manager.entity.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/main")
    public String main(Model model) {
        return "main";
    }
}
