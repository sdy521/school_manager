package com.study.school_manager.controller;

import com.github.pagehelper.PageInfo;
import com.study.school_manager.core.JSONResult;
import com.study.school_manager.core.Result;
import com.study.school_manager.core.jqGrid.JqGridResult;
import com.study.school_manager.dto.InfoParam;
import com.study.school_manager.service.StudentInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/student_info")
public class StudentInfoController extends BaseController{

    @Resource
    private StudentInfoService studentInfoService;

    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("menus",getMenus("student_info"));
        return "/modular/studentInfo/list";
    }

    @RequestMapping("/grid")
    @ResponseBody
    public Result gird(InfoParam param){
        if("".equals(param.getName())){
            param.setName(null);
        }
        if("".equals(param.getSex())){
            param.setSex(null);
        }
        PageInfo pageInfo = studentInfoService.selectByType(param);
        JqGridResult result = new JqGridResult();
        result.setTotal(pageInfo.getPages());
        result.setRecords(pageInfo.getTotal());
        result.setPage(pageInfo.getPageNum());
        result.setRows(pageInfo.getList());
        return new JSONResult(result);
    }

    /***
     * 获取用户信息详情
     * @param userid
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Result detail(@RequestParam Integer userid){
        Map map = studentInfoService.selectDetailOne(userid);
        return new JSONResult(map);
    }
}
