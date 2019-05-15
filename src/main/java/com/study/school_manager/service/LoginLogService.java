package com.study.school_manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.school_manager.dao.LoginLogDao;
import com.study.school_manager.dto.LoginLogParam;
import com.study.school_manager.entity.LoginLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sdy
 * @date 2019/5/15 14:01
 */
@Service
public class LoginLogService {

    @Resource
    private LoginLogDao loginLogDao;

    public PageInfo<LoginLog> selectAll(LoginLogParam param){
        PageHelper.startPage(param.getPage(),param.getRows());
        List<LoginLog> list = loginLogDao.selectAll();
        return new PageInfo<>(list);
    }
}
