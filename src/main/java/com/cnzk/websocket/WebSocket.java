package com.cnzk.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/websocket/{ip}")
@Component
public class WebSocket {
    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    // concurrent包的线程安全Map，用来存放每个客户端对应的WebSocket对象。
    private static ConcurrentHashMap<String, WebSocket> webSocketMap = new ConcurrentHashMap<String, WebSocket>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private String ip; // 客户端ip
    public static final String ACTION_PRINT_ORDER = "printOrder";
    public static final String ACTION_SHOW_PRINT_EXPORT = "showPrintExport";
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("ip") String ip) {
        this.session = session;
        this.ip = ip;
        webSocketMap.put(ip, this);
        addOnlineCount(); // 在线数加1
		System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("ip") String ip) {
        webSocketMap.remove(ip); // 从set中删除
         Map<String, String> map = session.getPathParameters();
         webSocketMap.remove(map.get("ip"));
        subOnlineCount(); // 在线数减1
         System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     *            客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
		System.out.println("来自客户端的消息:" + message);
        sendMessage(message);
        sendInfo(message);
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 像当前客户端发送消息
     *
     * @param message
     *            字符串消息
     * @throws IOException
     */
    public void sendMessage(String message) {
        try {
            System.out.println("像客户端发送消息"+message);
            this.session.getBasicRemote().sendText(message);
//             this.session.getAsyncRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) {

        if (message.equals("refresh")){
            message = "refresh,"+message;
        }else {
            message = "success,"+message;
        }
        for (Map.Entry<String, WebSocket> entry : webSocketMap.entrySet()) {
            WebSocket value = entry.getValue();
            value.sendMessage(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }

    public static ConcurrentHashMap<String, WebSocket> getWebSocketMap() {
        return webSocketMap;
    }
}

