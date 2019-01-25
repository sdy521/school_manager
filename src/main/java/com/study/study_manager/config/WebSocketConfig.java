package com.study.study_manager.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //这一行代码用来注册STOMP协议节点，同时指定使用SockJS协议。
        stompEndpointRegistry.addEndpoint("/endpointSang").withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //configureMessageBroker方法用来配置消息代理，由于我们是实现推送功能，这里的消息代理是/topic
        registry.enableSimpleBroker("/topic");
    }
}
