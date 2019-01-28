package com.study.study_manager.controller;

import com.github.pagehelper.PageInfo;
import com.study.study_manager.core.JSONResult;
import com.study.study_manager.core.Result;
import com.study.study_manager.core.jqGrid.JqGridResult;
import com.study.study_manager.dto.NoticeParam;
import com.study.study_manager.entity.Notice;
import com.study.study_manager.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {
    @Resource
    private NoticeService noticeService;

    @RequestMapping("/grid")
    @ResponseBody
    public Result grid(NoticeParam param){
        PageInfo<Notice> pageInfo = noticeService.selectByPage(param);
        JqGridResult result = new JqGridResult();
        result.setTotal(pageInfo.getPages());
        result.setRecords(pageInfo.getTotal());
        result.setPage(pageInfo.getPageNum());
        result.setRows(pageInfo.getList());
        return new JSONResult(result);
    }

    /***
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer id){
        Notice notice = new Notice();
        notice.setId(id);
        noticeService.delete(notice);
        return OK;
    }
}
