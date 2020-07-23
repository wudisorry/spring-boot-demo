package com.arh.springbootdemo.web.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author chenli
 * @Date 2020/7/23
 **/
@ServerEndpoint("/webSocket/userId/{userId}")
@Component
public class WebSocketServer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    private static AtomicInteger onlineCount = new AtomicInteger();

    private static ConcurrentHashMap<Integer, Session> concurrentHashMap = new ConcurrentHashMap();

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Integer userId) {
        if (concurrentHashMap.containsKey(userId)) {
            logger.error("已经存在此userId了");
            return;
        }
        concurrentHashMap.put(userId, session);
        onlineCount.incrementAndGet();
        logger.debug("有连接进来了，userId：" + userId + ",当前人数:" + onlineCount);
    }

    @OnClose
    public void onClose(@PathParam("userId") Integer userId) {
        Session session = concurrentHashMap.remove(userId);
        if (session != null) {
            onlineCount.decrementAndGet();
            logger.debug("当前连接关闭，userId：" + userId + ",当前人数:" + onlineCount);
            try {
                session.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    @OnMessage
    public void onMessage(Session session, @PathParam("userId") Integer userId, String msg) {
        logger.debug("userId：" + userId + ",收到消息:" + msg);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error(throwable.getMessage(), throwable);
    }

    public void sendMsg(Integer userId, String msg) throws IOException {
        Session session = concurrentHashMap.get(userId);
        if (session != null && session.isOpen()) {
            session.getBasicRemote().sendText(msg);
        } else {
            logger.debug("session是null或已经关闭");
        }
    }
}
