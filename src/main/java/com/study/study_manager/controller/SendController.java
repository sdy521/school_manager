package com.study.study_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/send")
public class SendController extends BaseController{

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("menus",getMenus("send"));
        return "/management/send/list";
    }
}
