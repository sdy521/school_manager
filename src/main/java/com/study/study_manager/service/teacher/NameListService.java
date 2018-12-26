package com.study.study_manager.service.teacher;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.study_manager.core.BaseDao;
import com.study.study_manager.core.BaseService;
import com.study.study_manager.dao.mysql.UserDao;
import com.study.study_manager.dto.TeacherParam;
import com.study.study_manager.entity.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class NameListService extends BaseService<User> {
    @Resource
    private UserDao userDao;

    @Override
    protected BaseDao<User> getDao() {
        return userDao;
    }
    public PageInfo<User> selectByPage(TeacherParam param){
        PageHelper.startPage(param.getPage(),param.getRows());
        List<User> list = userDao.selectByPage(param.getName(),param.getDeleted(),param.getSord(),1);
        return new PageInfo<User>(list);
    }

    public void recover(User teacher){
        teacher.setDeleted(false);
        teacher.setUpdateTime(new Date());
        userDao.updateByPrimaryKeySelective(teacher);
    }
}
