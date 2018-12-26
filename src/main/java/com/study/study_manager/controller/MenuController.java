package com.study.study_manager.controller;

import com.study.study_manager.entity.Menu;
import com.study.study_manager.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;
    @RequestMapping("/list")
    public String list(){
        return "/management/memu/list";
    }

    @RequestMapping("/grid")
    @ResponseBody
    public List<Menu> grid(){
        List<Menu> menu = menuService.selectAll();
        return menu;
    }
}
