package com.study.study_manager.test;

import com.study.study_manager.util.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/ueditor")
    public String ueditor(){
        return "/ueditorTest";
    }

    @RequestMapping("/redis")
    @ResponseBody
    public void redis(){
        RedisUtil.SET("15206120938","123",60);
    }

    @RequestMapping("/get")
    @ResponseBody
    public void get(){
        Object o = RedisUtil.GET("15206120938");
        System.out.println("o:"+o);
    }
}
