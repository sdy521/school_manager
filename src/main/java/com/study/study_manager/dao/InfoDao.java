package com.study.study_manager.dao;

import com.study.study_manager.core.BaseDao;
import com.study.study_manager.entity.Info;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface InfoDao extends BaseDao<Info> {

    @Select({"<script>",
            "SELECT i.*,u.name,u.img FROM info i " +
            "LEFT JOIN user u ON i.userid=u.id " +
            "WHERE u.type=${type} <when test='name!=null'> and u.name = #{name}</when>" +
             "<when test='sex!=null'> and i.sex=${sex} </when> and i.deleted=0",
            "</script>"})
    List<Map> selectByType(@Param("type") Integer type,@Param("name") String name,@Param("sex") Integer sex);

    @Update("update info set deleted=1 where userid = ${userid}")
    void deleteInfo(@Param("userid") Integer userid);
}
