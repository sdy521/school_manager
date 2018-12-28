package com.study.study_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends BaseController{
    @RequestMapping("/main")
    public String main(Model model) {
        model.addAttribute("menus",getMenus());
        return "main";
    }
}
