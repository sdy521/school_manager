package com.study.study_manager.core;

import com.study.study_manager.entity.mysql.BaseEntity;
import java.util.Date;

public abstract class BaseService<T extends BaseEntity>{

    protected abstract BaseDao<T> getDao();

    public int insert(T entity){
        saveBaseInfo(entity);
        getDao().insert(entity);
        return entity.getId();
    }

    protected void saveBaseInfo(T entity){
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
    }
}
