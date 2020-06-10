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
    <title>计费规则管理</title>

    <link rel="stylesheet" href="<%=path%>/static/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=path%>/static/lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <link rel="stylesheet" href="<%=path%>/static/css/public.css" media="all">

    <script charset="UTF-8" src="<%=path%>/static/js/jquery-3.4.1.js" charset="utf-8"></script>
    <script charset="UTF-8" src="<%=path%>/static/js/json2.js" type="text/javascript" ></script>

    <style>
        .main_btn > p {height:40px;}
        .main_btn > label {height:20px;}
    </style>
</head>
<body>


<input type="hidden" id="path" value="<%=path%>">
<div class="layuimini-container">
    <div class="layuimini-main layui-top-box">
        <div class="layui-box">
            <div class="layui-row layui-col-space10">
                <div class="layui-col-md12">
                    <blockquote class="layui-elem-quote main_btn">
                        <p>菜鸡停车收费规则：</p>
                        <p>半小时以内：免费</p>
                        <label style="display: block;">以后每小时：<div id="uprice" style="display: inline"></div>元</label><br>
                        <label style="display: block;">每天最高收费：<div id="maxprice" style="display: inline"></div>元</label>
                        <p>备注：</p>
                        <p>1、停车收费标注可通过下方进行修改。</p>
                        <p>2、停车不足一小时按一小时计价。</p>
                    </blockquote>
                </div>
            </div>
        </div>

        <div class="layui-box">
            <div class="layui-row layui-col-space10">
                <div class="layui-col-md6">
                    <table class="layui-hide" id="ratesMsgTbl" lay-filter="currentTableFilter"></table>
                </div>
                <div class="layui-col-md6">
                    <div class="layui-timeline-content layui-text">
                        <div class="layui-timeline-title">修改计费规则</div>
                    </div>

                    <div class="layui-timeline-content layui-text">
                        <h3 class="layui-timeline-title">小时/单价：</h3>
                        <div class="layui-input-block" style="margin-left: 20px;width: 200px;">
                            <input  type="text" name="ratesUprice" lay-verify="ratesUprice" lay-reqtext="金额不能为空"
                                    autocomplete="off" class="layui-input ratesUprice">
                        </div>
                    </div>

                    <div class="layui-timeline-content layui-text">
                        <h3 class="layui-timeline-title">天/最高金额：</h3>
                        <div class="layui-input-block" style="margin-left: 20px;width: 200px;">
                            <input  type="text" name="ratesMaxprice" lay-verify="ratesMaxprice" lay-reqtext="最高金额不能为空"
                                    autocomplete="off" class="layui-input ratesMaxprice">
                        </div>
                    </div>
                    <br>
                    <div class="layui-timeline-content layui-text">
                        <div class="layui-timeline-title">
                            <button type="button" class="layui-btn" onclick="updataPrice()">提交</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<%=path%>/static/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>


<script>

    var tableIns;
    var ratesId;
    var uprice;
    var maxprice;
    window.onload = function () {
        var path = $("#path").val();
        uprice = document.getElementById("uprice");
        maxprice = document.getElementById("maxprice");

        $.ajax({
            url: path+"/AdminController/queryPrice",
            async: true,
            type: "POST",
            success: function (data) {
                if (data != "error") {
                    console.log(data);
                    uprice.innerHTML = data.ratesUprice;
                    maxprice.innerHTML = data.ratesMaxprice;
                } else {
                    layer.msg("暂无计费规则",{icon:5});
                }
            },
            error: function () {
                layer.msg('当前网络繁忙',{icon:2});
                window.parent.location.href=path+"/url/login";
                }
            });

        //layui.use加载模块
        layui.use(['form', 'table'], function () {

            var table = layui.table,
                form = layui.form,
                $ = layui.jquery;

            var path = $('#path').val();

            tableIns = table.render({
                elem: '#ratesMsgTbl',   //表格的id
                url: path+'/AdminController/queryRatesList',
                defaultToolbar: ['filter', 'exports', 'print', {
                    title: '提示',
                    layEvent: 'LAYTABLE_TIPS',
                    icon: 'layui-icon-tips'
                }],
                cols: [[
                    {field: 'ratesId', width: 150, title: 'ID', sort: true},
                    {field: 'ratesUprice', maxwidth: 765,minWidth: 100, title: '小时/单价'},
                    {field: 'ratesMaxprice', maxwidth: 765,minWidth: 100, title: '天/最高金额'},
                ]],
                limits: [10, 15, 20, 25, 50, 100],
                limit: 10,
                page: true,
                skin: 'line',
            });
            table.on('tool(currentTableFilter)', function(obj) {
                var data = obj.data;
                //obj点击那行的所有字段属性
                var tr = obj.tr; //获得当前行 tr 的DOM对象
                ratesId = tr.children("td").eq(0).text();
                console.log("++++"+ratesId);
            });

        });
    }


    //刷新表格
    function updata() {
        tableIns.reload()
    };

    function updataPrice() {
        var path = $('#path').val();
        var ratesUprice = $('.ratesUprice').val();
        var ratesMaxprice = $('.ratesMaxprice').val();
        var ratesId = 1;
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

</script>
</body>
</html>
