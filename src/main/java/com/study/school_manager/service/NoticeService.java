package com.study.school_manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.school_manager.core.BaseDao;
import com.study.school_manager.core.BaseService;
import com.study.school_manager.dao.NoticeDao;
import com.study.school_manager.dto.NoticeParam;
import com.study.school_manager.entity.Notice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    public List<Map> selectByTime(String dateTime, Integer userId){
        return noticeDao.selectNotice(dateTime,userId);
    }
}
