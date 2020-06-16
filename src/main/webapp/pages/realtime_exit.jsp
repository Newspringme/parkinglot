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

    <style>
        .main_btn > p {height:40px;}
        .main_btn > label {height:20px;}
        .layui-form-label {
            float: none;
            display: block;
            padding: 8px 15px;
            width: 200px;
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
                        <img src="<%=path%>/static/img/u0.jpg" alt="">
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
                            <button type="button" class="layui-btn" onclick="">异常出库</button>
                            <button type="button" class="layui-btn" onclick="">确认出库</button>
                            <button type="button" class="layui-btn" onclick="">在线收费</button>
                            <button type="button" class="layui-btn" onclick="">现金收费</button>
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
    // window.onload = function () {
    //     var path = $("#path").val();
    //     uprice = document.getElementById("uprice");
    //     maxprice = document.getElementById("maxprice");
    //
    //     $.ajax({
    //         url: path+"/AdminController/queryPrice",
    //         async: true,
    //         type: "POST",
    //         success: function (data) {
    //             if (data != "error") {
    //                 console.log(data);
    //                 uprice.innerHTML = data.ratesUprice;
    //                 maxprice.innerHTML = data.ratesMaxprice;
    //             } else {
    //                 layer.msg("暂无计费规则",{icon:5});
    //             }
    //         },
    //         error: function () {
    //             layer.msg('当前网络繁忙',{icon:2});
    //             window.parent.location.href=path+"/url/login";
    //             }
    //         });
    //
    //     //layui.use加载模块
    //     layui.use(['form', 'table'], function () {
    //
    //         var table = layui.table,
    //             form = layui.form,
    //             $ = layui.jquery;
    //
    //         var path = $('#path').val();
    //
    //         tableIns = table.render({
    //             elem: '#ratesMsgTbl',   //表格的id
    //             url: path+'/AdminController/queryRatesList',
    //             defaultToolbar: ['filter', 'exports', 'print', {
    //                 title: '提示',
    //                 layEvent: 'LAYTABLE_TIPS',
    //                 icon: 'layui-icon-tips'
    //             }],
    //             cols: [[
    //                 {field: 'ratesId', width: 150, title: 'ID', sort: true},
    //                 {field: 'ratesUprice', maxwidth: 765,minWidth: 100, title: '小时/单价'},
    //                 {field: 'ratesMaxprice', maxwidth: 765,minWidth: 100, title: '天/最高金额'},
    //             ]],
    //             limits: [10, 15, 20, 25, 50, 100],
    //             limit: 10,
    //             page: true,
    //             skin: 'line',
    //         });
    //         table.on('tool(currentTableFilter)', function(obj) {
    //             var data = obj.data;
    //             //obj点击那行的所有字段属性
    //             var tr = obj.tr; //获得当前行 tr 的DOM对象
    //             ratesId = tr.children("td").eq(0).text();
    //             console.log("++++"+ratesId);
    //         });
    //
    //     });
    // }
    //
    //
    // //刷新表格
    // function updata() {
    //     tableIns.reload()
    // };
    //
    // function updataPrice() {
    //     var path = $('#path').val();
    //     var ratesUprice = $('.ratesUprice').val();
    //     var ratesMaxprice = $('.ratesMaxprice').val();
    //     var ratesId = 1;
    //     $.ajax({
    //         url: path+"/AdminController/editRates",
    //         async: true,
    //         type: 'POST',
    //         data: {"ratesId":ratesId,"ratesUprice": ratesUprice, "ratesMaxprice": ratesMaxprice},
    //         success: function (data) {
    //             if (data=="success") {
    //                 layer.msg('修改成功',{icon:6})
    //                 uprice.innerHTML = ratesUprice;
    //                 maxprice.innerHTML = ratesMaxprice;
    //                 updata();
    //             }else{
    //                 layer.msg('修改失败，请重试',{icon:5})
    //             }
    //         },
    //         error: function () {
    //             layer.msg('网络繁忙',{icon:2});
    //             window.parent.location.href=path+"/url/login";
    //         }
    //     });
    // }

</script>
</body>
</html>
