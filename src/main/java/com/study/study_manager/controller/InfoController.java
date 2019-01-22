package com.study.study_manager.controller;

import com.study.study_manager.service.InfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/teacher_info")
public class InfoController extends BaseController{

    @Resource
    private InfoService infoService;

    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("menus",getMenus("teacher_info"));
        return "/teacherInfo/list";
    }
}
