package com.study.study_manager.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.study_manager.core.BaseDao;
import com.study.study_manager.core.BaseService;
import com.study.study_manager.dao.FileDao;
import com.study.study_manager.entity.File;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sdy
 * @date 2019/4/11 14:02
 */
@Service
public class FileService extends BaseService<File> {
    @Resource
    private FileDao fileDao;

    @Override
    protected BaseDao<File> getDao() {
        return fileDao;
    }

    public List<File> selectAll(){
        return fileDao.selectAll();
    }

    /***
     * 构建jstree
     * @param list
     * @return
     */
    public JSONArray getJson(List<File> list){
        JSONArray jsonArray = new JSONArray();
        for(File file:list){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",file.getCode());
            jsonObject.put("parent", StringUtils.isEmpty(file.getPcode())?"#":file.getPcode());
            jsonObject.put("text",file.getName());
            jsonObject.put("icon","fa fa-bars");
            JSONObject state = new JSONObject();
            state.put("opened", true);
            jsonObject.put("state",state);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
