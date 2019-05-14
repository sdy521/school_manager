package com.study.school_manager.dao;

import com.study.school_manager.entity.OperationLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author sdy
 * @date 2019/5/9 15:48
 */
@Component
public interface OperationLogDao {

    @Insert("insert into operation_log(name,method,user_name) values(#{name},#{method},#{username})")
    void insert(@Param("name") String name,@Param("method") String method,@Param("username") String username);

    @Select("select * from operation_log order by id desc")
    List<OperationLog> selectAll();
}
