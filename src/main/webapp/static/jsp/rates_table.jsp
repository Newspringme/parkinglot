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
    <link rel="stylesheet" href="<%=path%>/static/css/public.css" media="all">

    <script charset="UTF-8" src="<%=path%>/static/js/jquery-3.4.1.js" charset="utf-8"></script>
    <script charset="UTF-8" src="<%=path%>/static/js/json2.js" type="text/javascript" ></script>

    <script src="<%=path%>/static/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
    <script src="<%=path%>/static/layui/layui.js" charset="UTF-8"></script>

</head>
<body>

<div class="layuimini-container">
    <input type="hidden" id="path" value="<%=path%>">
    <div class="layuimini-main">

        <table class="layui-hide" id="ratesMsgTbl" lay-filter="currentTableFilter"></table>

        <%--    点击按钮弹出层的form--%>
<%--        <form class = "layui-form" action="" style="display: none" id="changeAForm">--%>
<%--            <div id="test12" class="demo-tree-more"></div>--%>
<%--        </form>--%>

        <%--表格中按钮的模板--%>
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="changeAuthority">修改计费价格</a>
        </script>
    </div>
</div>



<%--    表格的js--%>
<script>
    //layui.use加载模块
    layui.use(['form', 'table'], function () {
        var table = layui.table,
            form = layui.form,
            $ = layui.jquery;

        var path = $('#path').val();

        table.render({
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
                 {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
                ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 10,
            page: true,
            skin: 'line',
        });


        // //监听事件
        // table.on('tool(currentTableFilter)', function(obj){
        //     var data = obj.data;
        //     //obj点击那行的所有字段属性
        //     var tr = obj.tr; //获得当前行 tr 的DOM对象
        //     var roleId = tr.children("td").eq(0).text();
        //     console.log(roleId);
        //     if (obj.event === 'changeAuthority')
        //     {
        //         var layer = layui.layer;
        //         layer.open({
        //             type: 1,
        //             title: '权限菜单分配',
        //             content: $('#changeAForm'),
        //             offset: '50px',
        //             anim: 1,
        //             isOutAnim: true,
        //             resize: false,
        //             area: ['50%', '80%'],
        //             closeBtn: 2,//弹出层的类型
        //             shade: 0.6,
        //             move: false,
        //             btn: ['提交', '取消'],
        //             btn1: function (index, layero) {
        //                 updateAuthority(roleId ,index);
        //                 return false;
        //             },
        //             btn2: function (index, layero) {
        //                 layer.close(index);
        //                 return false;
        //             },
        //             cancel: function (index, layero) {
        //                 layer.close(index);
        //                 return false;
        //             }
        //         });
        //         var path = $('#path').val();
        //         $.ajax({
        //             url: path+"/AdminController/queryMenuTree",
        //             async: true,
        //             type: 'POST',
        //             data:{
        //                 'roleId':data.roleId
        //             },
        //             dataType:'json',
        //             success: function (msg) {
        //                 if (data=="error") {
        //                     layer.msg('网络繁忙');
        //                 }else {
        //                     showTree(msg);
        //                 }
        //             },
        //             error: function () {
        //                 layer.msg('网络繁忙');
        //                 window.parent.location.href=path+"/url/login";
        //             }
        //         });
        //     }
        // });

        function showTree(data) {
            console.log(data);

            layui.use(['tree','util','form'],function () {
                var tree = layui.tree
                    ,layer = layui.layer
                    ,util = layui.util
                    ,form = layui.form;;
                tree.render({
                    elem: '#test12'
                    ,data: data
                    ,showCheckbox: true  //是否显示复选框
                    ,id: 'demoId1'
                });
            });
        };

        function updateAuthority(roleId,index){
            console.log("点击提交")
            var layer = layui.layer;
            layui.use(['tree','util'], function () {
                var tree = layui.tree;
                var checkData = tree.getChecked('demoId1');
                checkData = JSON.stringify(checkData);
                console.log(checkData);
                var path = $('#path').val();

                $.ajax({
                    url: path+"/AdminController/updateMenuTree",
                    async: true,
                    type: 'POST',
                    data:{'checkData':checkData,'roleId':roleId},
                    success: function (data) {
                        if (data=="success") {
                            layer.close(index);
                            layer.msg('修改成功，更新网页显示最新权限',{icon:6})
                        }else{
                            layer.msg('修改失败，请重试',{icon:5})
                        }
                    },
                    error: function () {
                        layer.msg('网络繁忙',{icon:2});
                        window.parent.location.href=path+"/url/login";
                    }
                });
            });
        };

    });
    </script>

</body>
</html>
