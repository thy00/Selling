package cn.thyonline.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Description:
 * @Author: Created by thy
 * @Date: 2018/6/26 14:29
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    //定义容器储存session
    private static CopyOnWriteArraySet<WebSocket> webSockets=new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        webSockets.add(this);
        log.info("【websocket消息】有新的连接，总数：{}",webSockets.size());
    }

    @OnClose
    public void onClose(){
        webSockets.remove(this);
        log.info("【websocket消息】连接断开，总数：{}",webSockets.size());
    }
    @OnMessage
    public void onMessage(String message){
        log.info("【websocket消息】收到客户端消息，{}",message);
    }


    public void sendMessage(String message){
        for (WebSocket webSocket:webSockets){
            log.info("【websocket消息】广播消息，message={}",message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
