package com.study.school_manager.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author sdy
 * @date 2019/5/15 10:15
 */
@Component
public interface LoginLogDao {

    @Insert("insert into login_log(user_name,user_type,ip,msg) values(#{userName},${userType},#{ip},#{msg})")
    void insert(@Param("userName")String userName,@Param("userType")Integer userType,@Param("ip")String ip,@Param("msg")String msg);
}
