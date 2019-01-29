package com.study.study_manager.dao;

import com.study.study_manager.core.BaseDao;
import com.study.study_manager.entity.Notice;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NoticeDao extends BaseDao<Notice> {

    @Select("select * from notice where deleted = 0 ORDER BY update_time DESC")
    List<Notice> selectByPage();
}
