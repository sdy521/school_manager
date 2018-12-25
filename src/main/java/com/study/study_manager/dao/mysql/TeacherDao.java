package com.study.study_manager.dao.mysql;

import com.study.study_manager.core.BaseDao;
import com.study.study_manager.entity.mysql.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherDao extends BaseDao<Teacher> {

    @Select({"<script>",
            "select * from teacher where deleted=${deleted} " +
            "<when test='name!=null'> and name like '%${name}%' </when>" +
             "order by id ${sort}",
            "</script>"})
    List<Teacher> selectByPage(@Param("name") String name,@Param("deleted") Integer deleted,@Param("sort") String sort);
}
