package com.study.study_manager.dao.mysql;

import com.study.study_manager.entity.mysql.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherDao {

    @Select({"<script>",
            "select * from teacher where deleted = 0 " +
            "<when test='name!=null'> and name like '%${name}%' </when>" +
             "order by id ${sort}",
            "</script>"})
    List<Teacher> selectByPage(@Param("name") String name,@Param("sort") String sort);

    @Insert("insert into teacher(name,password,deleted) values(#{name},#{password},0)")
    void insert(@Param("name") String name,@Param("password") String password);
}
