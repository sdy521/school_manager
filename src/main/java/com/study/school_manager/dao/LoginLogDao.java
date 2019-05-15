package com.study.school_manager.dao;

import com.study.school_manager.entity.LoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author sdy
 * @date 2019/5/15 10:15
 */
@Component
public interface LoginLogDao {

    @Insert("insert into login_log(user_name,user_type,ip,msg) values(#{userName},${userType},#{ip},#{msg})")
    void insert(@Param("userName")String userName,@Param("userType")Integer userType,@Param("ip")String ip,@Param("msg")String msg);

    @Select("select * from login_log order by id desc")
    List<LoginLog> selectAll();
}
