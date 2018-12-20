package com.study.study_manager.dao.mysql;

import com.study.study_manager.entity.mysql.Teacher;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherDao {

    @Select("select * from teacher where deleted = 0")
    List<Teacher> selectByPage();
}
