package com.study.study_manager.controller;

import com.study.study_manager.core.JSONResult;
import com.study.study_manager.core.Result;
import com.study.study_manager.entity.Notice;
import com.study.study_manager.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController extends BaseController{
    @Resource
    private NoticeService noticeService;

    @RequestMapping("/main")
    public String main(Model model) {
        model.addAttribute("menus",getMenus("main"));
        //添加公告
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Notice> list = noticeService.selectByTime(sdf.format(new Date()));
        model.addAttribute("listnotice",list);
        return "main";
    }

    @RequestMapping("/selectNotice")
    @ResponseBody
    public Result selectNotice(@RequestParam String dateTime){
        List<Notice> list = noticeService.selectByTime(dateTime);
        return new JSONResult(list);
    }
}
