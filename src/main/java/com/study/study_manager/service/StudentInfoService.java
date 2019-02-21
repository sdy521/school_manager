package com.study.study_manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.study_manager.core.BaseDao;
import com.study.study_manager.core.BaseService;
import com.study.study_manager.dao.InfoDao;
import com.study.study_manager.dto.InfoParam;
import com.study.study_manager.entity.Info;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class StudentInfoService extends BaseService<Info> {
    @Resource
    private InfoDao infoDao;
    @Override
    protected BaseDao<Info> getDao() {
        return infoDao;
    }

    /**
     * 根据类别查看信息1 老师 2 学生
     * @param param
     * @return
     */
    public PageInfo selectByType(InfoParam param){
        PageHelper.startPage(param.getPage(),param.getRows());
        List<Map> list = infoDao.selectByType(param.getType(),param.getName(),param.getSex());
        return new PageInfo(list);
    }

    /***
     * 删除用户详情
     * @param userid
     */
    public void deleteInfo(Integer userid){
        infoDao.deleteInfo(userid);
    }

    /***
     * 获取用户详情
     * @param userid
     * @return
     */
    public Map selectDetailOne(Integer userid){
        return infoDao.selectDetailOne(userid);
    }

    /***
     * 获取用户详情
     * @param type
     * @return
     */
    public List<Map> selectInfoDetail(Integer type){
        return infoDao.selectInfoDetail(type);
    }
}
