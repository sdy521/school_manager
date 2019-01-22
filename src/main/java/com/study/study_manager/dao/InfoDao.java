package com.study.study_manager.dao;

import com.study.study_manager.core.BaseDao;
import com.study.study_manager.entity.Info;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface InfoDao extends BaseDao<Info> {

    @Select("SELECT i.*,u.name,u.img FROM user_info i " +
            "LEFT JOIN user u ON i.userid=u.id " +
            "WHERE i.type=${type}")
    List<Map> selectByType(@Param("type") Integer type);
}
