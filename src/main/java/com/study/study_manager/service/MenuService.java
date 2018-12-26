package com.study.study_manager.service;

import com.study.study_manager.core.BaseDao;
import com.study.study_manager.core.BaseService;
import com.study.study_manager.dao.MenuDao;
import com.study.study_manager.entity.Menu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MenuService extends BaseService<Menu> {

    @Resource
    private MenuDao menuDao;
    @Override
    protected BaseDao<Menu> getDao() {
        return menuDao;
    }
}
