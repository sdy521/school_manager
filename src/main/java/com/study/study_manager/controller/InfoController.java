package com.study.study_manager.controller;

import com.github.pagehelper.PageInfo;
import com.study.study_manager.core.JSONResult;
import com.study.study_manager.core.Result;
import com.study.study_manager.core.jqGrid.JqGridResult;
import com.study.study_manager.dto.InfoParam;
import com.study.study_manager.service.InfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
@RequestMapping("/teacher_info")
public class InfoController extends BaseController{

    @Resource
    private InfoService infoService;

    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("menus",getMenus("teacher_info"));
        return "/management/teacherInfo/list";
    }

    @RequestMapping("/grid")
    @ResponseBody
    public Result gird(InfoParam param){
        PageInfo pageInfo = infoService.selectByType(param);
        JqGridResult result = new JqGridResult();
        result.setTotal(pageInfo.getPages());
        result.setRecords(pageInfo.getTotal());
        result.setPage(pageInfo.getPageNum());
        result.setRows(pageInfo.getList());
        return new JSONResult(result);
    }
}
