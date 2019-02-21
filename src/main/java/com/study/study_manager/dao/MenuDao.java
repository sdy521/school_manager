package com.study.study_manager.dao;

import com.study.study_manager.core.BaseDao;
import com.study.study_manager.entity.Menu;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MenuDao extends BaseDao<Menu> {

    @Select("select * from menu where deleted=0")
    List<Menu> selectNotDeleted();
}
