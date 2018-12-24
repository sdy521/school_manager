package com.study.study_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout (HttpServletRequest request, HttpServletResponse response) {
   /*     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            //设置为离线状态
            userDetailsService.updateOnline(((User)auth.getPrincipal()).getId(), 4 ,null);
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }*/
        return "ok";
    }

}
