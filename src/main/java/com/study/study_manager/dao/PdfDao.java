package com.study.study_manager.dao;

import com.study.study_manager.core.BaseDao;
import com.study.study_manager.entity.Pdf;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author sdy
 * @date 2019/4/30 14:28
 */
@Component
public interface PdfDao extends BaseDao<Pdf> {

    @Select("select * from pdf where deleted=0 order by id desc")
    List<Pdf> selectByPage();
}
