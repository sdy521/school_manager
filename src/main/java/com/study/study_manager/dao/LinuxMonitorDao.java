package com.study.study_manager.dao;

import com.study.study_manager.entity.LinuxMonitor;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author sdy
 * @date 2019/5/14 14:05
 */
@Component
public interface LinuxMonitorDao {

    @Select("select * from linux_monitor")
    List<LinuxMonitor> selectAll();
}
