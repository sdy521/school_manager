package com.study.study_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(value = "/login1", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }
    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public ModelAndView log(@RequestParam(value = "error", required = false) String error){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }
}
