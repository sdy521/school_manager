package com.study.school_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sdy
 * @date 2019/6/14 14:34
 */
@Controller
@RequestMapping("/map")
public class MapController extends BaseController{

    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("menus",getMenus("map"));
        return "/modular/map/list";
    }
}
