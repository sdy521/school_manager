package com.study.study_manager.spare;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

/**
 * @author sdy
 * @date 2019/5/10 17:17
 */
public class Test {
    public static void main(String[] args) {
        try {
            HttpResponse httpRequest = Unirest.post("http://192.168.1.85:8081/api/systemInfo").asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
