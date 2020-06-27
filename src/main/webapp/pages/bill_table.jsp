<%--
  Created by IntelliJ IDEA.
  User: 96581
  Date: 2020/6/6
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>收支明细</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=path%>/static/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=path%>/static/css/public.css" media="all">
    <script src="<%=path%>/static/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
</head>
<body>
<div class="layuimini-container">
    <input type="hidden" id="path" value="<%=path%>">
    <div class="layuimini-main">
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">订单编号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="billNum" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">操作日期</label>
                            <div class="layui-input-inline">
                                <input type="text" name="billTime" id="billTime"  placeholder="开始日期" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary"  lay-submit lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>


    </div>
</div>

<script>
    var load=layui.use(['form', 'table','laydate'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table,
            laydate = layui.laydate;
        var path = $("#path").val();
        //日期
        laydate.render({
            elem: '#billTime'
        });

        table.render({
            elem: '#currentTableId',
            url: path+'/AdminController/queryBill',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {field: 'billNum', maxwidth: 150,minWidth: 100, title: '订单编号'},
                {field: 'billTime', maxwidth: 200,minWidth: 100, title: '账单时间'},
                {field: 'billMoney', minWidth: 80, title: '金额'},
                {field: 'comboName', minWidth: 100, title: '消费事项'},
                {field: 'userName', minWidth: 100, title: '用户名'},
                {field: 'billType', minWidth: 100, title: '支付状态'}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 10,
            page: true,
            skin: 'line'
        });
        form.on('submit(data-search-btn)', function (data) {


            //执行搜索重载
            table.reload('currentTableId', {
                page: {
                    curr: 1
                }
                , where: {
                    billNum: data.field.billNum,
                    billTime:data.field.billTime
                }
            }, 'data');

            return false;
        });


    });
</script>

</body>
</html>