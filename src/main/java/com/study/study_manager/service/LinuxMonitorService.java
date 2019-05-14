package com.study.study_manager.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.study.study_manager.dao.LinuxMonitorDao;
import com.study.study_manager.entity.LinuxMonitor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sdy
 * @date 2019/5/14 14:06
 */
@Service
public class LinuxMonitorService {
    @Resource
    private LinuxMonitorDao linuxMonitorDao;

    public List<LinuxMonitor> selectAll(){
        return linuxMonitorDao.selectAll();
    }

    /***
     * 获取linux系统上服务信息
     * @param list
     */
    public List<Map> getSystemInfo(List<LinuxMonitor> list){
        List<Map> listMap = new ArrayList<>();
        try {
            for(int i=0;i<list.size();i++) {
                LinuxMonitor linuxMonitor= list.get(i);
                String path = "http://"+linuxMonitor.getIp()+":"+linuxMonitor.getPort()+"/api/systemInfo";
                Map map = new HashMap();
                HttpResponse<JsonNode> response = Unirest.post(path).header("accept", "application/json").asJson();
                String result = response.getBody().getObject().get("data").toString();
                if(result.indexOf("running")!=-1){
                    map.put("mysqlStatus",1);
                }else {
                    map.put("mysqlStatus",0);
                }
                map.put("id",i+1);
                map.put("ip",linuxMonitor.getIp());
                map.put("port",linuxMonitor.getPort());
                listMap.add(map);
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return listMap;
    }
}
