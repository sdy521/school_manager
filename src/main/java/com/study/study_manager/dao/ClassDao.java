package com.study.study_manager.dao;

import com.study.study_manager.core.BaseDao;
import com.study.study_manager.entity.Class;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ClassDao extends BaseDao<Class> {

    @Select({"<script>",
            "select c.*,u.name AS createUserName from class c " +
                    "LEFT JOIN `user` u ON u.id=c.create_user" +
                    " where 1=1" +
                    "<when test='deleted!=null'> and c.deleted=${deleted}</when>" +
                    "<when test='name!=null'> and c.name like '%${name}%'</when>",
            "</script>"})
    List<Map> selectByPage(@Param("name") String name, @Param("deleted") Integer deleted);

    @Select("select * from class where id=${id}")
    Class selectOneById(@Param("id") Integer id);
}
