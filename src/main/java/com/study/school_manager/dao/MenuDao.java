package com.study.school_manager.dao;

import com.study.school_manager.core.BaseDao;
import com.study.school_manager.entity.Menu;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MenuDao extends BaseDao<Menu> {

    @Select("select * from menu where deleted=0")
    List<Menu> selectNotDeleted();

    @Select("select * from menu where deleted=0 and type=-1")
    List<Menu> selectNotLimit();
}
