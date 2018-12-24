package com.study.study_manager.controller.teacher;

import com.github.pagehelper.PageInfo;
import com.study.study_manager.core.JSONResult;
import com.study.study_manager.core.Result;
import com.study.study_manager.core.jqGrid.JqGridResult;
import com.study.study_manager.dto.mysql.TeacherParam;
import com.study.study_manager.entity.mysql.Teacher;
import com.study.study_manager.service.teacher.NameListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/teacher_nameList")
public class NameListController {
    @Resource
    private NameListService nameListService;

    @RequestMapping("/list")
    public String list(Model model){
        return "/management/teacher/nameList/list";
    }

    @RequestMapping("/grid")
    @ResponseBody
    public Result grid(TeacherParam param){
        PageInfo<Teacher> pageInfo = nameListService.selectByPage(param);
        JqGridResult result = new JqGridResult();
        result.setPage(pageInfo.getPageNum());
        result.setRecords(pageInfo.getTotal());
        result.setTotal(pageInfo.getPages());
        result.setRows(pageInfo.getList());
        return new JSONResult(result);
    }

    /***
     * 增加
     * @param teacher
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public Result insert(@RequestBody Teacher teacher){
        nameListService.insert(teacher);
        return new Result(0,"增加成功");
    }
}
