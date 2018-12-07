package com.study.study_manager.dao;

import com.study.study_manager.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

//创建数据访问接口
@Component
public interface SysUserRepository {
    @Select("select * from sys_user where username=#{username}")
    SysUser findByUsername(@Param("username") String username);
}
