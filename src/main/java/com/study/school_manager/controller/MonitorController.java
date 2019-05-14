package com.study.school_manager.controller;

import com.study.school_manager.core.JSONResult;
import com.study.school_manager.core.Result;
import com.study.school_manager.entity.LinuxMonitor;
import com.study.school_manager.service.LinuxMonitorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sdy
 * @date 2019/5/14 13:42
 */
@Controller
@RequestMapping("/monitor")
public class MonitorController extends BaseController{

    @Resource
    private LinuxMonitorService monitorService;

    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("menus",getMenus("monitor"));
        return "/modular/monitor/list";
    }

    @RequestMapping("/grid")
    @ResponseBody
    public Result grid(){
        List<LinuxMonitor> list = monitorService.selectAll();
        List<String> pathList = new ArrayList<>();
       /* list.forEach(linuxMonitor -> {
            String ip = linuxMonitor.getIp();
            String port = linuxMonitor.getPort();
            String path = "http://"+ip+":"+port+"/api/systemInfo";
            pathList.add(path);
        });*/
        List<Map> result = monitorService.getSystemInfo(list);
        return new JSONResult(result);
    }
}
