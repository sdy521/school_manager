package com.study.school_manager.dao;

import com.study.school_manager.core.BaseDao;
import com.study.school_manager.entity.Notice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface NoticeDao extends BaseDao<Notice> {

    @Select("select * from notice where deleted = 0 ORDER BY update_time DESC")
    List<Notice> selectByPage();

    @Select("SELECT n.*,un.create_time AS ucreateTime FROM user_notice un " +
            "LEFT JOIN notice n ON un.notice_id=n.id " +
            "WHERE un.create_time LIKE '${createTime}%' AND un.user_id=${userId} ORDER BY un.create_time DESC")
    List<Map> selectNotice(@Param("createTime") String createTime, @Param("userId")Integer userId);
}
