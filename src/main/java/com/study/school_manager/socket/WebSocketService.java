package com.study.school_manager.socket;

/**
 * @author sdy
 * @date 2019/4/17 17:14
 */
public interface WebSocketService {
    /**
     * 群发
     * @param message
     */
    void groupSending(String message);

    /**
     * 指定发送
     * @param name
     * @param message
     */
    void appointSending(String name,String message);

}
