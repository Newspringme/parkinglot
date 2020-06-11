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
    <title>月缴产品管理</title>
    <link rel="stylesheet" href="<%=path%>/static/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=path%>/static/css/public.css" media="all">

    <script charset="UTF-8" src="<%=path%>/static/js/jquery-3.4.1.js" charset="utf-8"></script>
    <script charset="UTF-8" src="<%=path%>/static/js/json2.js" type="text/javascript" ></script>

    <script src="<%=path%>/static/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
    <script src="<%=path%>/static/layui/layui.js" charset="UTF-8"></script>

    <style>
        .layui-input, .layui-textarea {
            display: block;
            width: 80%;
            padding-left: 10px;
        }

        .layui-form-item {
            margin: 32px 0;
            clear: both;
            *zoom: 1;
        }

    </style>
</head>
<body>

<div class="layuimini-container">
    <input type="hidden" id="path" value="<%=path%>">
    <div class="layuimini-main">

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>
            </div>
        </script>

        <table class="layui-hide" id="comboMsgTbl" lay-filter="currentTableFilter"></table>


        <script type="text/html" id="currentTableBar">
            {{#  if(d.comboState=="启用"){ }}
            <%--   这里的d是固定的，它代表数据表格在渲染数据的一个迭代对象。 --%>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-update" lay-event="updata">禁用</a>
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            {{#  } }}

            {{#  if(d.comboState=="禁用"){ }}
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-update" lay-event="updata">启用</a>
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            {{#  } }}
        </script>

    </div>
</div>

<%--    表格的js--%>
<script>
    var load=layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;
        var path = $('#path').val();
        tableIns = table.render({
            elem: '#comboMsgTbl',
            url: path+'/AdminController/queryComboList',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {field: 'comboId', width: 150, title: 'ID', sort: true},
                {field: 'comboName', maxwidth: 765,minWidth: 100, title: '类别'},
                {field: 'comboValue', maxwidth: 765,minWidth: 100, title: '金额'},
                {field: 'comboState', maxwidth: 765,minWidth: 100, title: '状态'},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 10,
            page: true,
            skin: 'line'
        });




        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: '添加套餐',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['50%', '50%'],
                    content: '/parkinglot/pages/table/combo-add.jsp',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            }
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                var index = layer.open({
                    title: '编辑套餐',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['50%', '50%'],
                    content: '/parkinglot/pages/table/combo-edit.jsp?comboId='+data.comboId+'&comboName='+data.comboName+'&comboValue='+data.comboValue,
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'updata') {
                layer.confirm('是否要禁止套餐：'+obj.data.comboName, function (index) {
                    $.ajax({
                        url:path+'/AdminController/updataCombo'
                        ,type:"POST"
                        ,dataType:"text"
                        ,data:{
                            comboId:data.comboId,
                            comboState:data.comboState
                        },
                        success:function (msg) {
                            if (msg=='true'){
                                layer.alert("操作成功");
                                updata();
                            }else {
                                layer.alert("操作失败");
                            }
                        }
                    })
                    layer.close(index);
                });
            }
        });

        form.verify({
            comboName: function(value){
                if(value.length < 2){
                    return '月缴名称至少得2个字符';
                }
            }
            ,comboValue: [
                /^([1-9][0-9]*)$/
                ,'金额为数字'
            ]
        });

        //刷新表格
        function updata() {
            tableIns.reload()
        };

    });
    </script>


</body>
</html>
