package com.study.school_manager.controller;

import com.study.school_manager.core.JSONResult;
import com.study.school_manager.core.Result;
import com.study.school_manager.service.NoticeService;
import com.study.school_manager.util.SpringSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController extends BaseController{
    @Resource
    private NoticeService noticeService;

    @RequestMapping("/main")
    public String main(Model model) {
        model.addAttribute("menus",getMenus("main"));
        //添加公告
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Map> list = noticeService.selectByTime(sdf.format(new Date()), SpringSecurity.getSysUser().getId());
        model.addAttribute("listnotice",list);
        return "main";
    }

    @RequestMapping("/selectNotice")
    @ResponseBody
    public Result selectNotice(@RequestParam String dateTime){
        List<Map> list = noticeService.selectByTime(dateTime,SpringSecurity.getSysUser().getId());
        return new JSONResult(list);
    }
}
