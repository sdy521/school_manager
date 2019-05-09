package com.study.study_manager.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author sdy
 * @date 2019/5/9 15:48
 */
@Component
public interface OperationLogDao {

    @Insert("insert into operation_log(name,method,user_name) values(#{name},#{method},#{username})")
    void insert(@Param("name") String name,@Param("method") String method,@Param("username") String username);
}
