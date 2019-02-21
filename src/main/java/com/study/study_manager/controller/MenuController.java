package com.study.study_manager.controller;

import com.study.study_manager.core.JSONResult;
import com.study.study_manager.core.Result;
import com.study.study_manager.entity.Menu;
import com.study.study_manager.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{

    @Resource
    private MenuService menuService;
    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("menus",getMenus("menu"));
        return "/modular/memu/list";
    }

    @RequestMapping("/grid")
    @ResponseBody
    public List<Menu> grid(){
        Menu param = new Menu();
        param.setDeleted(false);
        List<Menu> menu = menuService.select(param);
        Collections.sort(menu, Comparator.comparing(Menu::getSort));
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
        return OK;
    }

    /***
     * 查找一个对象
     * @param id
     * @return
     */
    @RequestMapping("/selectOne")
    @ResponseBody
    public Result selectOne(@RequestParam Integer id){
        Menu menu = new Menu();
        menu.setId(id);
        menu = menuService.selectOne(menu);
        return new JSONResult(menu);
    }

    /***
     * 更新
     * @param menu
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Menu menu){
        menuService.update(menu);
        return OK;
    }

    /***
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam Integer id){
        Menu menu = new Menu();
        menu.setId(id);
        menuService.delete(menu);
        return OK;
    }
}
