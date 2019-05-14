package com.study.school_manager.core;

import com.study.school_manager.entity.BaseEntity;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

public abstract class BaseService<T extends BaseEntity>{

    private Class<T> entityClass;

    protected abstract BaseDao<T> getDao();

    public BaseService() {
        //getGenericSuperclass()获取直接父类
        //转成ParameterizedType
        //getActualTypeArguments()获取泛型数组
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

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
        Condition condition = new Condition(entityClass);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("deleted", false);
        return getDao().selectByExample(condition);
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
