package com.study.study_manager.controller;

import com.study.study_manager.dto.RequestMessage;
import com.study.study_manager.dto.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage message) {
        System.out.println(message.getTitle());
        return new ResponseMessage("<strong>"+message.getTitle()+"</strong>");
    }
}
