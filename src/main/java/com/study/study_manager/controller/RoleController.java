package com.study.study_manager.controller;

import com.github.pagehelper.PageInfo;
import com.study.study_manager.core.JSONResult;
import com.study.study_manager.core.Result;
import com.study.study_manager.core.jqGrid.JqGridResult;
import com.study.study_manager.dto.RoleParam;
import com.study.study_manager.entity.User;
import com.study.study_manager.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;
    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("menus",getMenus("role"));
        return "/modular/role/list";
    }

    @RequestMapping("/grid")
    @ResponseBody
    public Result grid(RoleParam param){
        PageInfo<User> pageInfo = roleService.selectByPage(param);
        JqGridResult result = new JqGridResult();
        result.setPage(pageInfo.getPageNum());
        result.setRecords(pageInfo.getTotal());
        result.setTotal(pageInfo.getPages());
        result.setRows(pageInfo.getList());
        return new JSONResult(result);
    }

    @RequestMapping("/getRoleId")
    @ResponseBody
    public Result getRoleId(@RequestParam Integer userid){
        Integer roleid = roleService.getRoleId(userid);
        return new JSONResult(roleid);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody RoleParam param){
        //先看是否已存在userid
        Integer roleid = roleService.getRoleId(param.getUserid());
        if(roleid!=null){
            //修改
            roleService.update(param);
        }else {
            //插入
            roleService.insert(param);
        }
        return OK;
    }
}
