package com.study.study_manager.core;

import com.study.study_manager.entity.BaseEntity;
import java.util.Date;

public abstract class BaseService<T extends BaseEntity>{

    protected abstract BaseDao<T> getDao();

    /***
     * 参数属性值为空的也插入
     * @param entity
     * @return
     */
    public int insert(T entity){
        saveBaseInfo(entity);
        getDao().insert(entity);
        return entity.getId();
    }

    /***
     * 根据主键更新属性不为null的值
     * @param entity
     * @return
     */
    public int updateSelective(T entity){
        saveBaseInfo(entity);
        getDao().updateByPrimaryKeySelective(entity);
        return entity.getId();
    }

    /***
     * 删除  其实是更新
     * @param entity
     * @return
     */
    public int delete(T entity){
        entity.setDeleted(true);
        saveBaseInfo(entity);
        getDao().updateByPrimaryKeySelective(entity);
        return entity.getId();
    }
    /***
     * 根据属性值查找一个对象
     * @param entity
     * @return
     */
    public T selectOne(T entity){
        return getDao().selectOne(entity);
    }
    protected void saveBaseInfo(T entity){
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
    }
}
