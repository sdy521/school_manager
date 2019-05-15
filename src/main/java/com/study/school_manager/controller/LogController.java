package com.study.school_manager.controller;

import com.github.pagehelper.PageInfo;
import com.study.school_manager.core.JSONResult;
import com.study.school_manager.core.Result;
import com.study.school_manager.core.jqGrid.JqGridResult;
import com.study.school_manager.dto.LoginLogParam;
import com.study.school_manager.dto.OperationLogParam;
import com.study.school_manager.entity.LoginLog;
import com.study.school_manager.entity.OperationLog;
import com.study.school_manager.service.LoginLogService;
import com.study.school_manager.service.OperationLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Sdy
 * @Date:Created in 22:24 2019/5/14
 */
@Controller
@RequestMapping("/log")
public class LogController extends BaseController{

    @Resource
    private OperationLogService operationLogService;

    @Resource
    private LoginLogService loginLogService;

    /***
     * 操作日志界面
     * @param model
     * @return
     */
    @RequestMapping("/operationList")
    public String operationList(Model model){
        model.addAttribute("menus",getMenus("operationList"));
        return "/modular/log/operationList";
    }

    @RequestMapping("/operationGrid")
    @ResponseBody
    public Result operationGrid(OperationLogParam param){
        PageInfo<OperationLog> pageInfo = operationLogService.selectAll(param);
        JqGridResult result = new JqGridResult();
        result.setPage(pageInfo.getPageNum());
        result.setRecords(pageInfo.getTotal());
        result.setTotal(pageInfo.getPages());
        result.setRows(pageInfo.getList());
        return new JSONResult(result);
    }

    /***
     * 登入日志界面
     * @param model
     * @return
     */
    @RequestMapping("/loginList")
    public String loginLog(Model model){
        model.addAttribute("menus",getMenus("loginList"));
        return "/modular/log/loginList";
    }

    @RequestMapping("/loginGrid")
    @ResponseBody
    public Result loginGrid(LoginLogParam param){
        PageInfo<LoginLog> pageInfo = loginLogService.selectAll(param);
        JqGridResult result = new JqGridResult();
        result.setPage(pageInfo.getPageNum());
        result.setRecords(pageInfo.getTotal());
        result.setTotal(pageInfo.getPages());
        result.setRows(pageInfo.getList());
        return new JSONResult(result);
    }
}
