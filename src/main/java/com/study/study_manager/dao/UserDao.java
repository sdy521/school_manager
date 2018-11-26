package com.study.study_manager.dao;

import com.study.study_manager.entity.User;

import java.util.List;

public interface UserDao {
    /***
     * 列出所有用户
     */
    List<User> selectAllUser();
}
