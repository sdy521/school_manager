package com.study.school_manager.spare;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

/**
 * @author sdy
 * @date 2019/5/10 17:17
 */
public class Test {
    public static void getMysql57(){
        try {
            HttpResponse<JsonNode> httpRequest = Unirest.post("http://192.168.1.85:8081/api/systemInfo")
                    .header("accept","application/json").asJson();
            String result = httpRequest.getBody().getObject().get("data").toString();
            if(result.indexOf("running")!=-1){
                System.out.println("正在运行");
            }else {
                System.out.println("停止运行");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        getMysql57();
    }
}
