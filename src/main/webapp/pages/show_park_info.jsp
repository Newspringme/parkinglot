<%--
  Created by IntelliJ IDEA.
  User: 86189
  Date: 2020/6/19
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>场内车辆信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js"></script>
</head>
<body>
<!--        行工具栏-->
<div style="display: none" id="toolBar">
    <button type="button" class="layui-btn layui-btn-sm layui-icon layui-icon-" lay-event="showCar">查看车辆</button>
</div>
<!--搜索条件开始-->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>搜索条件</legend>
</fieldset>
<form  action="" onsubmit="return false;" class="layui-form" method="post" >
    <div class="layui-inline">
        <label class="layui-form-label">车牌号：</label>
        <div class="layui-input-block">
            <input type="text" name="carNum" autocomplete="off" placeholder="请输入车牌号" class="layui-input">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">区域：</label>
        <div class="layui-input-block">
            <select name="parkName" class="layui-select">
                <option selected="selected" value="">请选择区域</option>
                <option value="park_A">A区</option>
                <option value="park_B">B区</option>
                <option value="park_C">C区</option>
                <option value="park_D">D区</option>
            </select>
        </div>
    </div>
    <div class="layui-inline">
        <div class="layui-input-block">
            <button type="submit"  lay-submit lay-filter="formSearch" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" >搜索</button>
            <button type="reset" class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置</button>
        </div>
    </div>
</form>
<!--搜索条件结束-->
<!--弹出层开始-->
<div id="showCar" class="layui-anim layui-anim-scale">
    <form id="formCar" class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label" style="margin-left: 120px;">车牌号：</label>
            <div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">

            </div>
        </div>
    </form>
</div>
<!--弹出层结束-->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>场内车辆信息</legend>
</fieldset>
<table class="layui-hide" id="parkTable" lay-filter="parkTable"></table>
<script type="text/javascript">
    layui.use(['jquery','layer','form','table'],function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        // 渲染数据表格
        table.render({
            id: 'parkTable'
            ,elem: '#parkTable'
            , url: '${pageContext.request.contextPath}/parkController/showParkInfo'
            , title: '车位信息'
            , limit: 12//每页多少条数据
            , limits: [12,24,36,48]
            ,page:true
            ,toolbar:'#toolBar'
            ,defaultToolbar:['filter','print']
            ,skin: 'line' //行边框风格
            ,even: true //开启隔行背景
            ,size: 'lg' //大尺寸的表格，sm为小尺寸
            , text: {
                none: '数据被组长吃了' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
            }
            , cols: [[
                {field:'parkId' ,title: '序号',width:100}
                ,{field:'parkName' ,title: '停车位名',width:150}
                ,{field:'parkState', title:'车位状态',align:'center', width:150}
                ,{field:'carNum', title:'车牌号',align:'center', width:150}
                ,{field:'eventId', title:'车位编号',align:'center', width:150}
                ,{fixed: 'right', title:'操作',align:'center',width:600, toolbar: '#toolBar'}
            ]]
        });
        //提交条件查询表单
        form.on('submit(formSearch)',function (data) {
            var msg=JSON.stringify(data.field);
            //表格重载之后就会根据条件参数进行分页
            table.reload('parkTable',{
                page:{
                    curr:1//从第一页开始
                },
                where:{
                    msg:msg//条件
                },
                method:'post',
                url:'${pageContext.request.contextPath}/parkController/showParkInfo'
            })
        });
        //监听行工具条事件
        table.on('tool(parkTable)',function (obj) {
            var data = obj.data;
            var layEvent = obj.event;
            if (layEvent === 'showCar'){
                //页面层-自定义
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 0,
                    shadeClose: true,
                    skin: 'layui-layer-molv',
                    content: '#showCar',
                    success:function () {
                        form.val("formCar",data);
                    }
                });
            }
        });
    });
</script>
</body>
</html>
