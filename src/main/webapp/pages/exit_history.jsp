<%--
  Created by IntelliJ IDEA.
  User: 86189
  Date: 2020/6/18
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>历史出场信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js"></script>

</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>历史出场信息</legend>

</fieldset>
<ul class="layui-timeline">
    <%--<li class="layui-timeline-item">--%>
        <%--<i class="layui-icon layui-timeline-axis">&#xe63f;</i>--%>
        <%--<div class="layui-timeline-content layui-text">--%>
            <%--<h3 class="layui-timeline-title">8月18日</h3>--%>
            <%--<p>--%>
                <%--layui 2.0 的一切准备工作似乎都已到位。发布之弦，一触即发。--%>
                <%--<br>不枉近百个日日夜夜与之为伴。因小而大，因弱而强。--%>
                <%--<br>无论它能走多远，抑或如何支撑？至少我曾倾注全心，无怨无悔 <i class="layui-icon"></i>--%>
            <%--</p>--%>
        <%--</div>--%>
    <%--</li>--%>
</ul>
<script>
    $(function () {
        $.post("${pageContext.request.contextPath}/CarController/queryExit",{start:0,end:6},function (data) {
            var history = JSON.parse(data);
            for (var i = 0; i < history.length; i++) {
                $(".layui-timeline").prepend(
                    "<li class='layui-timeline-item'>" +
                    "<i class='layui-icon layui-timeline-axis'>&#xe63f;</i>" +
                    "<div class='layui-timeline-content layui-text'>" +
                    "<h3 class='layui-timeline-title'>"+history[i].exitTime+"</h3>" +
                    "<p>车牌号：" + history[i].carNum +
                    "</p></div></li>"
                );
            }
        });
    });

    //websocket
    var websocket = null;
    if('WebSocket' in window){
        websocket = new WebSocket("ws://localhost:8080/parkinglot/websocket/ip");
    }
    else{
        alert("您的浏览器不支持websocket");
    }

    websocket.onerror = function(){
        setMessageInHtml("send error！");
    };

    websocket.onopen = function(){
        setMessageInHtml("connection success!")
    };

    websocket.onmessage  = function(event){
        setMessageInHtml(event.data);
        console.log(event);
    };

    websocket.onclose = function(){
        setMessageInHtml("closed websocket!")
    };

    window.onbeforeunload = function(){
        clos();
    };

    // 接收信息
    function setMessageInHtml(message){
        if (message.data.split(",")[0]=="new") {
            alert("xxx");
            window.reload(true);
        }
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

</script>
</body>
</html>
