package com.study.study_manager.core;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author fonlin
 * @date 2018/4/20
 */
public interface BaseDao<T> extends Mapper<T>, MySqlMapper<T> {

}
