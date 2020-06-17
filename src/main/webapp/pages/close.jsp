<%--
  Created by IntelliJ IDEA.
  User: LQ
  Date: 2020-6-17
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>支付成功</title>
</head>
<body>
<script>
    window.onload=function(){
        var websocket = null;
        if('WebSocket' in window){
            websocket = new WebSocket("ws://localhost:8080/parkinglot/websocket/ip");
        }
        else{
            alert("您的浏览器不支持websocket");
        }

        websocket.onerror = function(){
            setMessageInHtml("send error！");
        }

        websocket.onopen = function(){
            setMessageInHtml("connection success！")
        }

        websocket.onmessage  = function(event){
            setMessageInHtml(event.data);
            console.log(event);
        }

        websocket.onclose = function(){
            setMessageInHtml("closed websocket!")
        }

        window.onbeforeunload = function(){
            clos();
        }

        // 接收信息
        function setMessageInHtml(message){
            document.getElementById('message').innerHTML += message;
        }

        //关闭连接
        function clos(){
            websocket.close(3000,"强制关闭");
        }
        //发送信息
        function send(msg){
            alert(msg);
            websocket.send(msg);
        }

        send("refresh");
        alert("支付成功")
        // window.close();
    }



</script>
</body>
</html>
