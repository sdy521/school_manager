package com.study.study_manager.service.teacher;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.study_manager.core.BaseDao;
import com.study.study_manager.core.BaseService;
import com.study.study_manager.dao.mysql.TeacherDao;
import com.study.study_manager.dto.mysql.TeacherParam;
import com.study.study_manager.entity.mysql.Teacher;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class NameListService extends BaseService<Teacher> {
    @Resource
    private TeacherDao teacherDao;

    @Override
    protected BaseDao<Teacher> getDao() {
        return teacherDao;
    }
    public PageInfo<Teacher> selectByPage(TeacherParam param){
        PageHelper.startPage(param.getPage(),param.getRows());
        List<Teacher> list = teacherDao.selectByPage(param.getName(),param.getDeleted(),param.getSord());
        return new PageInfo<Teacher>(list);
    }

    public void recover(Teacher teacher){
        teacher.setDeleted(false);
        teacher.setUpdateTime(new Date());
        teacherDao.updateByPrimaryKeySelective(teacher);
    }
}
