<%--
  Created by IntelliJ IDEA.
  User: LQ
  Date: 2020-2-29
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>实时出库</title>

    <link rel="stylesheet" href="<%=path%>/static/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=path%>/static/lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <link rel="stylesheet" href="<%=path%>/static/css/public.css" media="all">

    <script charset="UTF-8" src="<%=path%>/static/js/jquery-3.4.1.js" charset="utf-8"></script>
    <script charset="UTF-8" src="<%=path%>/static/js/json2.js" type="text/javascript" ></script>
    <script src="<%=path%>/static/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
    <style>
        .main_btn > p {height:40px;}
        .main_btn > label {height:20px;}
        .layui-form-label {
            float: none;
            display: block;
            padding: 8px 15px;
            width: 370px;
            font-weight: 400;
            line-height: 20px;
            text-align: left;
            font-size: 20px;
            margin: 0 0 0 54px;
        }
    </style>
</head>
<body>


<input type="hidden" id="path" value="<%=path%>">
<div class="layuimini-container">
    <div class="layuimini-main layui-top-box">
        <div class="layui-box">
            <div class="layui-row layui-col-space10">
                <div class="layui-col-md12">
                    <blockquote class="layui-elem-quote main_btn" style="text-align: center">
                        <img id="img" src="" alt="">
                    </blockquote>
                </div>
            </div>
        </div>

        <div class="layui-box">
            <div class="layui-row layui-col-space10">
                <div class="layui-col-md6">
                    <label class="layui-form-label">车牌号：<div id="carnumber" style="display: inline"></div></label>
                    <label class="layui-form-label">用户：<div id="username" style="display: inline"></div></label>
                    <label class="layui-form-label">车辆情况：<div id="state" style="display: inline"></div></label>
                    <label class="layui-form-label">停车位：<div id="ps" style="display: inline"></div></label>
                    <label class="layui-form-label">入库时间：<div id="entertime" style="display: inline"></div></label>
                    <label class="layui-form-label">出库时间：<div id="exittime" style="display: inline"></div></label>
                </div>
                <div class="layui-col-md6">
                    <label class="layui-form-label">停放时长：<div id="time" style="display: inline"></div></label>
                    <label class="layui-form-label">应缴费：<div id="money" style="display: inline"></div></label>
                    <br><br><br>
                    <div class="layui-timeline-content layui-text" style="text-align: center">
                        <div class="layui-timeline-title">
                            <button type="button" class="layui-btn" onclick="onlinePay()">在线收费</button>
                            <button type="button" class="layui-btn" onclick="cashPay()">现金收费</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<%=path%>/static/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>

<script>
    var carnumber;
    var username;
    var state;
    var ps;
    var entertime;
    var exittime;
    var time;
    var money;
    var img_str;
    //layui.use加载模块
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var websocket = null;
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://39.102.35.36:8080/parkinglot/websocket/ip");
            // websocket = new WebSocket("ws://localhost:8080/parkinglot/websocket/ip");
        } else {
            alert("您的浏览器不支持websocket");
        }
        websocket.onerror = function () {
            setMessageInHtml("send error！");
        }
        websocket.onopen = function () {
            setMessageInHtml("connection success！")
        }

        websocket.onmessage = function (event) {
            setMessageInHtml(event);
            // console.log(event);
        }
        websocket.onclose = function () {
            setMessageInHtml("closed websocket!")
        }
        window.onbeforeunload = function () {
            clos();
        }

        //关闭连接
        function clos() {
            websocket.close(3000, "强制关闭");
        }

        //发送信息
        function send() {
            var msg = document.getElementById('text').value;
            websocket.send(msg);
        }

        // 接收信息
        function setMessageInHtml(message) {
            if (message.data.split(",")[0] == "success") {
                carnumber = message.data.split(",")[1];
                username = message.data.split(",")[2];
                state = message.data.split(",")[3];
                ps = message.data.split(",")[4];
                entertime = message.data.split(",")[5];
                exittime = message.data.split(",")[6];
                time = message.data.split(",")[7];
                money = message.data.split(",")[8];
                img_str = message.data.split(",")[9];
                $("#img").attr("src", "data:image/png; base64," + img_str);
                document.getElementById("carnumber").innerHTML = carnumber;
                document.getElementById("username").innerHTML = username;
                document.getElementById("state").innerHTML = state;
                document.getElementById("ps").innerHTML = ps;
                document.getElementById("entertime").innerHTML = entertime;
                document.getElementById("exittime").innerHTML = exittime;
                document.getElementById("time").innerHTML = time;
                document.getElementById("money").innerHTML = money;
            } else if(message.data.split(",")[0] == "carexit"){
                layer.alert('支付成功', {icon: 6}, function () {
                    location.reload(true);
                });
            }else {
                layer.msg("暂无车辆出场", {icon: 5});
            }


        }
    })

    //异常出库
    function abnormalOut() {
        var path = $('#path').val();
        $.ajax({
            url: path+"/AdminController/editRates",
            async: true,
            type: 'POST',
            data: {"ratesId":ratesId,"ratesUprice": ratesUprice, "ratesMaxprice": ratesMaxprice},
            success: function (data) {
                if (data=="success") {
                    layer.msg('修改成功',{icon:6})
                    uprice.innerHTML = ratesUprice;
                    maxprice.innerHTML = ratesMaxprice;
                    updata();
                }else{
                    layer.msg('修改失败，请重试',{icon:5})
                }
            },
            error: function () {
                layer.msg('网络繁忙',{icon:2});
                window.parent.location.href=path+"/url/login";
            }
        });
    }
    //在线收费
    function onlinePay() {
        var path = $('#path').val();
        if (carnumber==null){
            layer.msg('当前无车辆出场',{icon:5})
        }else if(money==0.0){
            layer.msg('免费停车',{icon:6})
            $.ajax({
                url: path+"/ChargeController/freePay",
                async: true,
                type: 'POST',
                data: {"exittime":exittime,"carnumber": carnumber, "username": username,"money":money},
                success: function (data) {
                    if (data=="success") {
                        layer.alert('出库成功',{icon:6},function () {
                            location.reload(true);
                        });
                    }else{
                        layer.msg('出库失败，请重试',{icon:5})
                    }
                },
                error: function () {
                    layer.msg('网络繁忙',{icon:2});
                    window.parent.location.href=path+"/url/login";
                }
            });
        }
        else {
            window.open("${pageContext.request.contextPath}/alipay?type=car:&enter="+entertime+"&exit="+exittime+"&carNum="+carnumber+"&username="+username);
        }
    }
    //现金收费
    function cashPay() {
        var path = $('#path').val();
        if (carnumber==null){
            layer.msg('当前无车辆出场',{icon:5})
        }else if(money==0.0) {
            layer.msg('免费停车', {icon: 6})
            $.ajax({
                url: path + "/ChargeController/freePay",
                async: true,
                type: 'POST',
                data: {"exittime": exittime, "carnumber": carnumber, "username": username, "money": money},
                success: function (data) {
                    if (data == "success") {
                        layer.alert('出库成功', {icon: 6}, function () {
                            location.reload(true);
                        });
                    } else {
                        layer.msg('出库失败，请重试', {icon: 5})
                    }
                },
                error: function () {
                    layer.msg('网络繁忙', {icon: 2});
                    window.parent.location.href = path + "/url/login";
                }
            });
        } else {
            layer.confirm('是否支付完成？',{icon:7},function (index) {
                layer.close(index);
                $.ajax({
                    url: path+"/ChargeController/cashPay",
                    async: true,
                    type: 'POST',
                    data: {"exittime":exittime,"carnumber": carnumber, "username": username,"money":money},
                    success: function (data) {
                        if (data=="success") {
                            layer.alert('支付成功',{icon:6},function () {
                                location.reload(true);
                            });
                        }else{
                            layer.msg('支付失败，请重试',{icon:5})
                        }
                    },
                    error: function () {
                        layer.msg('网络繁忙',{icon:2});
                        window.parent.location.href=path+"/url/login";
                    }
                });
            });
        }


    }
</script>
</body>
</html>
