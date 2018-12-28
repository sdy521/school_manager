package com.study.study_manager.controller;

import com.study.study_manager.core.JSONResult;
import com.study.study_manager.core.Result;
import com.study.study_manager.entity.Menu;
import com.study.study_manager.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{

    @Resource
    private MenuService menuService;
    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("menus",getMenus());
        return "/management/memu/list";
    }

    @RequestMapping("/grid")
    @ResponseBody
    public List<Menu> grid(){
        List<Menu> menu = menuService.selectAll();
        return menu;
    }
    @RequestMapping("/jstree")
    @ResponseBody
    public Result jstree() {
        return new JSONResult(menuService.jstreeMenu());
    }

    /***
     * 增加
     * @param params
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public Result insert(@RequestBody Menu params){
        menuService.insert(params);
        return new Result(0,"增加成功");
    }
}
