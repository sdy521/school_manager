package com.study.study_manager.core;

import com.study.study_manager.entity.BaseEntity;
import com.study.study_manager.entity.Menu;

import java.util.Date;
import java.util.List;

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

    /***
     * 查询全部结果
     * @return
     */
    public List<T> selectAll(){
        return getDao().selectAll();
    }

    /***
     * 根据条件查询全部
     * @param entity
     * @return
     */
    public List<T> select(T entity){
        return getDao().select(entity);
    }

    /***
     * 更新  null也更新
     * @param entity
     * @return
     */
    public Integer update(T entity){
        saveBaseInfo(entity);
        getDao().updateByPrimaryKey(entity);
        return entity.getId();
    }
    protected void saveBaseInfo(T entity){
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
    }
}
