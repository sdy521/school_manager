package com.study.study_manager.service;

import com.study.study_manager.core.BaseDao;
import com.study.study_manager.core.BaseService;
import com.study.study_manager.dao.NoticeDao;
import com.study.study_manager.entity.Notice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NoticeService extends BaseService<Notice> {
    @Resource
    private NoticeDao noticeDao;

    @Override
    protected BaseDao<Notice> getDao() {
        return noticeDao;
    }
}
