package com.study.school_manager.dao;

import com.study.school_manager.core.BaseDao;
import com.study.school_manager.entity.Info;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface InfoDao extends BaseDao<Info> {

    @Select({"<script>",
            "SELECT i.sex,i.create_time,i.update_time,u.name,u.id FROM info i " +
            "LEFT JOIN user u ON i.userid=u.id " +
            "WHERE u.type=${type} <when test='name!=null'> and u.name = #{name}</when>" +
             "<when test='sex!=null'> and i.sex=${sex} </when> and i.deleted=0",
            "</script>"})
    List<Map> selectByType(@Param("type") Integer type,@Param("name") String name,@Param("sex") Integer sex);

    @Update("update info set deleted=1 where userid = ${userid}")
    void deleteInfo(@Param("userid") Integer userid);

    @Select("SELECT u.name,u.img,i.age,i.address,i.phone,i.sex FROM user u " +
            "LEFT JOIN info i ON i.userid=u.id " +
            "WHERE u.id=${userid}")
    Map selectDetailOne(@Param("userid") Integer userid);

    @Select("SELECT u.name,i.address,i.age,i.phone,i.sex FROM info i " +
            "LEFT JOIN user u ON u.id=i.userid " +
            "WHERE i.deleted=0 AND u.type=${type}")
    List<Map> selectInfoDetail(@Param("type") Integer type);
}
