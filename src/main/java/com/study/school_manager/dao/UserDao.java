package com.study.school_manager.dao;

import com.study.school_manager.core.BaseDao;
import com.study.school_manager.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import java.util.List;

//创建数据访问接口
@Component
public interface UserDao extends BaseDao<User> {
    @Select({"<script>",
            "select * from user where deleted=${deleted} and type=${type} " +
                    "<when test='name!=null'> and name like '%${name}%' </when>" +
                    "order by id ${sort}",
            "</script>"})
    List<User> selectByPage(@Param("name") String name, @Param("deleted") Integer deleted, @Param("sort") String sort, @Param("type") Integer type);

    @Select({"<script>",
            "select * from user where deleted=0 and id !=1 " +
            "<when test='name!=null'> and name like '%${name}%' </when> order by id desc",
            "</script>"})
    List<User> selectByPage2(@Param("name") String name);
}
