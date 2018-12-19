package com.study.study_manager.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher_nameList")
public class NameListController {

    @RequestMapping("/list")
    public String list(Model model){
        return "/management/teacher/nameList/list";
    }
}
