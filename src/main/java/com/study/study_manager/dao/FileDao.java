package com.study.study_manager.dao;

import com.study.study_manager.core.BaseDao;
import com.study.study_manager.entity.File;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author sdy
 * @date 2019/4/11 14:03
 */
public interface FileDao extends BaseDao<File> {

    @Select("select * from file where deleted=0")
    List<File> selectAll();

    @Update("update file set name=#{newName} where code=#{code}")
    void updateNameByCode(@Param("newName") String newName,@Param("code") String code);
}
