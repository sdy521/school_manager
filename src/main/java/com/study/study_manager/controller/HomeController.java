package com.study.study_manager.controller;

import com.study.study_manager.entity.Notice;
import com.study.study_manager.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class HomeController extends BaseController{
    @Resource
    private NoticeService noticeService;

    @RequestMapping("/main")
    public String main(Model model) {
        model.addAttribute("menus",getMenus("main"));
        //添加公告
        List<Notice> list = noticeService.selectAll();
        model.addAttribute("listnotice",list);
        return "main";
    }
}
