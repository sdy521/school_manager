package com.study.study_manager.controller;

import com.github.pagehelper.PageInfo;
import com.study.study_manager.core.JSONResult;
import com.study.study_manager.core.Result;
import com.study.study_manager.core.jqGrid.JqGridResult;
import com.study.study_manager.dto.ClassParam;
import com.study.study_manager.entity.Class;
import com.study.study_manager.service.ClassService;
import com.study.study_manager.util.SpringSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/class")
public class ClassController extends BaseController {

    @Resource
    private ClassService classService;

    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("menus",getMenus("class"));
        return "/modular/class/list";
    }

    @RequestMapping("/grid")
    @ResponseBody
    public Result grid(ClassParam param){
        PageInfo<Class> pageInfo = classService.selectByPage(param);
        JqGridResult result = new JqGridResult();
        result.setRecords(pageInfo.getTotal());
        result.setTotal(pageInfo.getPages());
        result.setPage(pageInfo.getPageNum());
        result.setRows(pageInfo.getList());
        return new JSONResult(result);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Result insert(@RequestParam String name){
        Integer userid = SpringSecurity.getSysUser().getId();
        Class clas = new Class();
        clas.setName(name);
        clas.setCreateUser(userid);
        clas.setUpdateUser(userid);
        classService.insert(clas);
        return OK;
    }

    @RequestMapping("/selectOne")
    @ResponseBody
    public Result selectOne(@RequestParam Integer id){
        Class clas = classService.selectOneById(id);
        return new JSONResult(clas);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestParam Integer id,@RequestParam String name){
        Integer userid = SpringSecurity.getSysUser().getId();
        Class clas = new Class();
        clas.setId(id);
        clas.setName(name);
        clas.setUpdateUser(userid);
        classService.updateSelective(clas);
        return OK;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam Integer id){
        Integer userid = SpringSecurity.getSysUser().getId();
        Class clas = new Class();
        clas.setId(id);
        clas.setUpdateUser(userid);
        classService.delete(clas);
        return OK;
    }
}
