package com.study.study_manager.service;

import com.study.study_manager.core.BaseDao;
import com.study.study_manager.core.BaseService;
import com.study.study_manager.dao.UserDao;
import com.study.study_manager.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InitPasswordService extends BaseService<User> {

    @Resource
    private UserDao userDao;

    @Override
    protected BaseDao<User> getDao() {
        return userDao;
    }
}
