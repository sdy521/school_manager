package com.study.school_manager.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.school_manager.core.BaseDao;
import com.study.school_manager.core.BaseService;
import com.study.school_manager.dao.MenuDao;
import com.study.school_manager.entity.Menu;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService extends BaseService<Menu> {

    @Resource
    private MenuDao menuDao;
    @Override
    protected BaseDao<Menu> getDao() {
        return menuDao;
    }

    /**
     * 获取menu列表构造成的json
     */
    public JSONArray jstreeMenu() {
        return jstree(menuDao.selectNotDeleted());
    }

    /**
     * 构造jstree菜单json
     *
     * @param all   所有菜单
     * @return  json
     */
    public JSONArray jstree(List<Menu> all) {
        //根据菜单拼出页面上jstree所需的json
        JSONArray jsonArray = new JSONArray();
        for (Menu menu : all) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", menu.getCode());
            jsonObject.put("parent", StringUtils.isEmpty(menu.getPcode()) ? "#" : menu.getPcode());
            jsonObject.put("text", menu.getName());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
