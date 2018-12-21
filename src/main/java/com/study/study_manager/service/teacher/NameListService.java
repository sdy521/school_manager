package com.study.study_manager.service.teacher;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.study_manager.dao.mysql.TeacherDao;
import com.study.study_manager.dto.mysql.TeacherParam;
import com.study.study_manager.entity.mysql.Teacher;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class NameListService {
    @Resource
    private TeacherDao teacherDao;

    public PageInfo<Teacher> selectByPage(TeacherParam param){
        PageHelper.startPage(param.getPage(),param.getRows());
        List<Teacher> list = teacherDao.selectByPage(param.getName(),param.getSord());
        return new PageInfo<Teacher>(list);
    }
}
