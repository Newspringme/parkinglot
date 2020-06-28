<%--
  Created by IntelliJ IDEA.
  User: 86189
  Date: 2020/6/23
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>自助端选择</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js"></script>
    <style>
        .main {
            background-color: rgba(255, 255, 255, 0.9);
            width: 400px;
            position: absolute;
            right: 35%;
            top: 22%;
            z-index: 99999999;
        }

        @media screen and (max-width: 768px) {
            .main {
                width: 100%;
                height: 350px;
                right: 0;
            }
        }
    </style>
</head>
<body style="background-image: url(https://i.loli.net/2020/06/09/JboPuV2t1UZKcRd.jpg);background-repeat:no-repeat;
        background-size:100% 100%;
        background-attachment: fixed;">
<div class="main">
    <form class="layui-form" onsubmit="return false;" style="text-align: center">
        <label class="layui-form-label" style="width: 200px;margin-left: 10%;font-size: 16px">请选择自助终端</label>
        <div class="layui-form-item">
            <button class="layui-btn layui-btn-radius layui-btn-normal" id="machine1">1号终端（入口）</button>
        </div>
        <div class="layui-form-item">
            <button class="layui-btn layui-btn-radius layui-btn-normal" id="machine2">2号终端（出口）</button>
        </div>
    </form>
</div>
<script>
    var machineNum;
    $("#machine1").on("click",function () {
        machineNum = 1;
        entryMap(machineNum);
    });
    $("#machine2").on("click",function () {
        machineNum = 2;
        entryMap(machineNum);
    });

    function entryMap(machineNum){
        $.post("${pageContext.request.contextPath}/CoorController/getSelfServiceCoor?machineNum="+machineNum,function (data) {
            window.location.href = "${pageContext.request.contextPath}/pages/parkinglot_map.jsp";
        });
    }
</script>
</body>
</html>
