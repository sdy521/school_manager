package com.study.study_manager.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.study_manager.core.BaseDao;
import com.study.study_manager.core.BaseService;
import com.study.study_manager.dao.FileDao;
import com.study.study_manager.entity.File;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${filedesk.location}")
    private String path;
    @Value("${localfiledesk.location}")
    private String localpath;
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
            jsonObject.put("type",file.getType()==0?"package":"file");
            jsonObject.put("icon",StringUtils.isEmpty(file.getIcon())?"":file.getIcon());
            JSONObject state = new JSONObject();
            state.put("opened", true);
            jsonObject.put("state",state);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public void updateNameByCode(String newName,String code){
        fileDao.updateNameByCode(newName,code);
    }
    public void updateNamePathByCode(String newName,String path,String code){
        fileDao.updateNamePathByCode(newName,path,code);
    }

    public File getFileByCode(String code){
        return fileDao.getFileByCode(code);
    }

    public String getFilePath(String code){
        return fileDao.getFilePath(code);
    }

    public void delete(String code){
        fileDao.deleteByCode(code);
    }

    public Integer getTypeByCode(String code){
        return fileDao.getTypeByCode(code);
    }

    public List<Integer> getIdListByCode(String code){
        return fileDao.getIdListByCode(code);
    }

    public List<String> getNameListByCode(String code){
        return fileDao.getNameListByCode(code);
    }

    public void deleteByIds(String ids){
        fileDao.deleteByIds(ids);
    }

    public void deleteFile(String name){
        //删除本地文件
        String localp = localpath+ java.io.File.separator+name;
        java.io.File file = new java.io.File(localp);
        if(file.exists()){
            file.delete();
        }
        //删除服务器文件
        String p = path+ java.io.File.separator+name;
        java.io.File file1 = new java.io.File(p);
        if(file1.exists()){
            file1.delete();
        }
    }
}
