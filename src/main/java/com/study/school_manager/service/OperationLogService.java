package com.study.school_manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.school_manager.dao.OperationLogDao;
import com.study.school_manager.dto.OperationLogParam;
import com.study.school_manager.entity.OperationLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Sdy
 * @Date:Created in 22:44 2019/5/14
 */
@Service
public class OperationLogService {

    @Resource
    private OperationLogDao operationLogDao;

    public PageInfo<OperationLog> selectAll(OperationLogParam param){
        PageHelper.startPage(param.getPage(),param.getRows());
        List<OperationLog> list = operationLogDao.selectAll();
        return new PageInfo<>(list);
    }
}
