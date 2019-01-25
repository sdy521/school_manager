package com.study.study_manager.controller;

import com.study.study_manager.dto.ResponseMessage;
import com.study.study_manager.entity.Notice;
import com.study.study_manager.service.NoticeService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class WsController {

    @Resource
    private NoticeService noticeService;

    @MessageMapping("/websocket")
    //会对路径的浏览器发送消息。
    @SendTo("/topic/getResponse")
    public ResponseMessage say(Notice notice) {
        noticeService.insert(notice);
        return new ResponseMessage("新通知");
    }
}
