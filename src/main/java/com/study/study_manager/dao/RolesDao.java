package com.study.study_manager.dao;

import com.study.study_manager.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface RolesDao {

    @Select("SELECT * from role where id in (" +
            "SELECT roles_id FROM user_role where user_id=${userId}" +
            ")")
    List<Role> getRoles(@Param("userId") Integer userId);
}
