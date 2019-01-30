package com.study.study_manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.study_manager.core.BaseDao;
import com.study.study_manager.core.BaseService;
import com.study.study_manager.dao.NoticeDao;
import com.study.study_manager.dto.NoticeParam;
import com.study.study_manager.entity.Notice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeService extends BaseService<Notice> {
    @Resource
    private NoticeDao noticeDao;

    @Override
    protected BaseDao<Notice> getDao() {
        return noticeDao;
    }

    public PageInfo<Notice> selectByPage(NoticeParam param){
        PageHelper.startPage(param.getPage(),param.getRows());
        List<Notice> list = noticeDao.selectByPage();
        return new PageInfo<>(list);
    }

    public List<Notice> selectByTime(String dateTime){
        return noticeDao.selectNotice(dateTime);
    }
}
