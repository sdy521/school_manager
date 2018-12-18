package com.study.study_manager.dao;

import com.study.study_manager.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SysRolesDao {

    @Select("SELECT * from sys_role where id in (" +
            "SELECT roles_id FROM sys_user_roles where sys_user_id=${userId}" +
            ")")
    List<SysRole> getRoles(@Param("userId") Integer userId);
}
