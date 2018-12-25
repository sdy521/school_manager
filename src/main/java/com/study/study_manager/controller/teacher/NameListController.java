package com.study.study_manager.controller.teacher;

import com.github.pagehelper.PageInfo;
import com.study.study_manager.core.JSONResult;
import com.study.study_manager.core.Result;
import com.study.study_manager.core.jqGrid.JqGridResult;
import com.study.study_manager.dto.mysql.TeacherParam;
import com.study.study_manager.entity.mysql.Teacher;
import com.study.study_manager.service.teacher.NameListService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        String pwd = teacher.getPassword();
        String bcrypt = BCrypt.hashpw(pwd,BCrypt.gensalt());
        teacher.setPassword(bcrypt);
        nameListService.insert(teacher);
        return new Result(0,"增加成功");
    }

    /***
     * 根据id查找老师
     * @param id
     * @return
     */
    @RequestMapping("/selectOne")
    @ResponseBody
    public Result selectOne(@RequestParam Integer id){
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher = nameListService.selectOne(teacher);
        return new JSONResult(teacher);
    }

    /***
     * 修改
     * @param teacher
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Teacher teacher){
        nameListService.updateSelective(teacher);
        return new Result(0,"修改成功");
    }

    /***
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam Integer id){
        Teacher teacher = new Teacher();
        teacher.setId(id);
        nameListService.delete(teacher);
        return new Result(0,"删除成功");
    }

    /***
     * 恢复
     * @param id
     * @return
     */
    @RequestMapping("/recover")
    @ResponseBody
    public Result recover(@RequestParam Integer id){
        Teacher teacher = new Teacher();
        teacher.setId(id);
        nameListService.recover(teacher);
        return new Result(0,"恢复成功");
    }
}
