package com.study.study_manager.socket;

import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author sdy
 * @date 2019/4/17 17:17
 * 后端实现websocket客户端
 */
//@Component
public class ScoketClient implements WebSocketService {

//    @Autowired
    private WebSocketClient webSocketClient;

    @Override
    public void groupSending(String message) {
        webSocketClient.send(message);
    }

    @Override
    public void appointSending(String name, String message) {
        // 这里指定发送的规则由服务端决定参数格式
        webSocketClient.send("TOUSER"+name+";"+message);
    }
}
