package com.study.school_manager.dao;

import com.study.school_manager.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface RolesDao {

    @Select("SELECT * from role where id in (" +
            "SELECT roles_id FROM user_role where user_id=${userId}" +
            ")")
    List<Role> getRoles(@Param("userId") Integer userId);

    @Select("SELECT * FROM `role` WHERE id = " +
            "(SELECT roles_id FROM `user_role` WHERE user_id = " +
            "(SELECT id FROM `user` WHERE name = #{username}))")
    Role getMenuRoles(@Param("username") String username);

    @Select("select roles_id from user_role where user_id = ${userid}")
    Integer getRoleId(@Param("userid") Integer userid);

    @Update("update user_role set roles_id = ${roleid} where user_id = ${userid}")
    void updateUserRole(@Param("userid") Integer userid,@Param("roleid") Integer roleid);

    @Insert("insert into user_role(user_id,roles_id) values(${userid},${roleid})")
    void insertUserRole(@Param("userid") Integer userid,@Param("roleid") Integer roleid);
}
