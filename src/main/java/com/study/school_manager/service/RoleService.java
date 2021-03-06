package com.study.school_manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.school_manager.dao.RolesDao;
import com.study.school_manager.dao.UserDao;
import com.study.school_manager.dto.RoleParam;
import com.study.school_manager.entity.Role;
import com.study.school_manager.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {
    @Resource
    private RolesDao rolesDao;
    @Resource
    private UserDao userDao;

    public PageInfo<User> selectByPage(RoleParam param){
        PageHelper.startPage(param.getPage(),param.getRows());
        List<User> list = userDao.selectByPage2(param.getName());
        return new PageInfo<User>(list);
    }

    /***
     * 获取已经设置的权限
     * @param userid
     * @return
     */
    public Integer getRoleId(Integer userid){
        return rolesDao.getRoleId(userid);
    }

    /***
     * 授权
     * @param param
     */
    public void update(RoleParam param){
        rolesDao.updateUserRole(param.getUserid(),param.getRoleid());
    }
    public void insert(RoleParam param){
        rolesDao.insertUserRole(param.getUserid(),param.getRoleid());
    }

    /***
     * 获取用户权限
     * @param userid
     * @return
     */
    public Role getRoleByUserId(Integer userid){
        return rolesDao.getRoleByUserId(userid);
    }
}
