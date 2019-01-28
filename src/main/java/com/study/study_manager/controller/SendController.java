package com.study.study_manager.controller;

import com.study.study_manager.core.JSONResult;
import com.study.study_manager.core.Result;
import com.study.study_manager.entity.Notice;
import com.study.study_manager.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/send")
public class SendController extends BaseController{

    @Resource
    private NoticeService noticeService;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("menus",getMenus("send"));
        return "/modular/send/list";
    }

    /***
     * 修改弹窗
     * @param id
     * @return
     */
    @RequestMapping("/updateModal")
    @ResponseBody
    public Result updateModal(Integer id){
        Notice notice = new Notice();
        notice.setId(id);
        notice = noticeService.selectOne(notice);
        return new JSONResult(notice);
    }

    /***
     * 修改
     * @param notice
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Notice notice){
        noticeService.updateSelective(notice);
        return OK;
    }
}
