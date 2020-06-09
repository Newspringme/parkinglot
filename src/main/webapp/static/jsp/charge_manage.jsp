<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/6
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>收费员列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
    <style>
        .layui-table-cell .layui-form-checkbox[lay-skin=primary] {
            top: 10px;
            padding: 0;
        }
    </style>
</head>
<body>
<!--搜索条件开始-->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>搜索条件</legend>
</fieldset>
<form  action="" onsubmit="return false;" class="layui-form" method="post" >
    <div class="layui-inline">
        <label class="layui-form-label">收费员账号:</label>
        <div class="layui-input-block">
            <input type="text" name="adminName" autocomplete="off" placeholder="请输入收费员账号" class="layui-input">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">添加时间：</label>
        <div class="layui-input-block">
            <input type="text" name="startTime" id="startTime" readonly autocomplete="off" placeholder="请选择开始时间" class="layui-input">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">至：</label>
        <div class="layui-input-block">
            <input type="text" name="endTime" id="endTime" readonly autocomplete="off" placeholder="请选择截止时间" class="layui-input">
        </div>
    </div>
    <div class="layui-inline">
        <div class="layui-input-block">
            <button type="submit"  lay-submit lay-filter="formSearch" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" >搜索</button>
            <button type="reset" class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置</button>
        </div>
    </div>
</form>
<!--        搜索条件结束-->
<!--        表格开始-->
<!--        头部工具栏-->
<div style="display: none" id="chargeToolbar">
    <button type="button" class="layui-btn layui-btn-xm layui-icon layui-icon-add-circle" lay-event="add">新增收费员</button>
    <button type="button" class="layui-btn layui-btn-xm layui-icon layui-icon-upload" lay-event="uploadHead">上传头像</button>
</div>
<!--        行工具栏-->
<div style="display: none" id="chargeBar">
    <button type="button" class="layui-btn layui-btn-sm layui-icon layui-icon-edit" lay-event="edit">编辑</button>
    <button type="button" class="layui-btn layui-btn-sm layui-icon layui-icon-auz" lay-event="modifyState">禁/启用</button>
    <button type="button" class="layui-btn layui-btn-sm layui-icon layui-icon-face-cry" lay-event="logOff">离职</button>
    <button type="button" class="layui-btn layui-btn-sm layui-icon layui-icon-key" lay-event="resetPass">重置密码</button>
    <button type="button" class="layui-btn layui-btn-sm layui-icon layui-icon-picture" lay-event="showHead">查看头像</button>
    <button type="button" class="layui-btn layui-btn-sm layui-icon layui-icon-file-b" lay-event="showDetailForm">查看详情</button>
</div>
<!--        表格主体-->
<table class="layui-hide" id="chargeTable" lay-filter="chargeTable"></table>
<!--        表格结束-->
<!--        添加弹出层开始-->
<div id="addCharge" onsubmit="return false;" style="display: none">
    <form class="layui-form" id="addForm" action="" style="margin-top: 30px" >
        <div class="layui-form-item">
            <label class="layui-form-label" style="margin-left: 120px;">账号名称：</label>
            <div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
                <input  type="text" name="adminName" lay-verify="adminName"
                        autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label"style="margin-left: 120px;">密&emsp;&emsp;码：</label>
            <div class="layui-input-block"style="float: left; margin-left: 20px;width: 200px;">
                <input type="password" name="adminPass" lay-verify="adminPass"
                       autocomplete="off" class="layui-input adminPass">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"style="margin-left: 120px;">确认密码：</label>
            <div class="layui-input-block"style="float: left; margin-left: 20px;width: 200px;">
                <input type="password" name="surePass"  lay-verify="adminPass"
                       autocomplete="off" class="layui-input surePass">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"style="margin-left: 120px;">联系方式：</label>
            <div class="layui-input-block"style="float: left; margin-left: 20px;width: 200px;">
                <input type="password" name="adminTel"  lay-verify="adminTel"
                       autocomplete="off" class="layui-input surePass">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"style="margin-left: 120px;">性别：</label>
            <div class="layui-form-select"style="float: left; margin-left: 20px;width: 200px;">
                <select id="sexSel" class="layui-select" name="adminSex">
                    <option>请选择性别</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 220px;">
                <button type="submit" class="layui-btn layui-icon layui-icon-release" lay-submit lay-filter="addCharge">提交</button>
                <button type="reset" class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置</button>
            </div>
        </div>
    </form>
</div>
<!--        添加弹出层结束->
<!--        编辑弹出层开始-->
<div id="updateCharge" onsubmit="return false;" style="display: none">
    <form class="layui-form" lay-filter="formUpdateAdmin" id="updateForm" action="" style="margin-top: 30px" >
        <div class="layui-form-item">
            <label class="layui-form-label" style="margin-left: 120px;">账号名称：</label>
            <div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
                <input  type="text" name="adminName" lay-verify="adminName" lay-reqtext="账号不能为空"
                        autocomplete="off" class="layui-input adminName">
            </div>
        </div>

        <input type="hidden" name="adminId" autocomplete="off" class="layui-input layui-form-item adminId">

        <div class="layui-form-item">
            <label class="layui-form-label" style="margin-left: 120px;">联系方式：</label>
            <div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
                <input  type="text" name="adminTel" lay-verify="adminTel" lay-reqtext="联系方式不能为空"
                        autocomplete="off" class="layui-input adminTel">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 220px;">
                <button type="submit" class="layui-btn layui-icon layui-icon-release" lay-submit lay-filter="formUpdateAdmin">提交</button>
                <button type="reset" class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置</button>
            </div>
        </div>



    </form>
</div>
<!--        添加弹出层结束-->
<%--        详情弹出层开始--%>
<div id="showDetail" style="display: none">
    <form class="layui-form" lay-filter="formDetail" action="" style="margin-top: 30px" >
        <div class="layui-form-item">
            <label class="layui-form-label" style="margin-left: 100px;">账号名称：</label>
            <div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
                <input  type="text" name="adminName"
                        autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="margin-left: 100px;">性别：</label>
            <div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
                <input  type="text" name="adminSex"
                        autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="margin-left: 100px;">注册时间：</label>
            <div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
                <input  type="text" name="regTime"
                        autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="margin-left: 100px;">状态：</label>
            <div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
                <input  type="text" name="adminState"
                        autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
    </form>
</div>
<%--        详情弹出层结束--%>
<%--        上传头像弹出层开始--%>
<div class="layui-form-item" style="display: none;margin-top: 100px;margin-left: 150px" id="uploadImg">
    <div class="layui-upload-button" style="float: left; width: 200px;">
        <button type="button" class="layui-btn" id="upload">
            <i class="layui-icon">&#xe67c;</i>点击上传
        </button>
    </div>
    <br>
    <div style="margin-top: 50px; width:300px;margin-left: -80px">
        <div class="layui-progress">
            <div class="layui-progress-bar" id="prograss-bar"></div>
        </div>
    </div>
</div>
<%--        上传头像弹出层结束--%>
<script type="text/javascript">
    layui.use(['jquery','layer','form','table','laydate'],function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var laydate=layui.laydate;
        //给开始时间和结束时间绑定时间选择器
        laydate.render({
            elem:'#startTime'
        });
        laydate.render({
            elem:'#endTime'
        });
        // 渲染数据表格
        table.render({
            id: 'chargeTable'
            ,elem: '#chargeTable'
            , url: '${pageContext.request.contextPath}/ChargeController/getChargeList'
            , title: '收费员数据表'
            , limit: 5//每页多少条数据
            , limits: [5,10,20,40,80,100]
            ,page:true
            ,toolbar:'#chargeToolbar'
            ,defaultToolbar:['filter','print']
            , totalRow:true//开启合计行
            ,initSort: {
                field: 'regTime' //排序字段，对应 cols 设定的各字段名
                ,type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
            }
            ,skin: 'line' //行边框风格
            ,even: true //开启隔行背景
            ,size: 'lg' //大尺寸的表格，sm为小尺寸
            , text: {
                none: '数据被组长吃了' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
            }
            , cols: [[
                {type: 'numbers',align:'center',width:100, fixed: 'left',totalRowText:'合计：'}
                ,{field:'checkbox' ,type: 'checkbox',width:70}
                ,{field:'adminName', title:'收费员账号',align:'center', width:110}
                ,{field:'adminSex', title:'性别',align:'center', width:100,sort: true}
                ,{field:'adminState', title:'状态',align:'center', width:100,sort: true}
                ,{field:'adminTel', title:'联系方式',align:'center', width:120}
                ,{field:'headImg', title:'头像路径',align:'center', width:120,hide:true}
                ,{field:'regTime', title:'注册时间',align:'center', width:200,sort: true}
                ,{fixed: 'right', title:'操作',align:'center',width:550, toolbar: '#chargeBar'}
            ]]
        });

        //头部工具栏监听事件
        table.on('toolbar(chargeTable)', function(obj){
            var nowId='${admin.adminId}';
            var checkStatus = table.checkStatus('chargeTable');
            data=checkStatus.data;
            delList=[];
            data.forEach(function(n,i){
                if (n.adminId==nowId){
                    alert("当前登录的账号不能删除的哦(*￣︶￣)");
                }else {
                    delList.push(n.adminId);
                }
            });
            switch(obj.event){
                case 'add':
                    openAddCharge();
                    break;
                case 'uploadHead':
                    if (delList.length == 1) {
                        openUpload(delList);
                    }else if (delList.length > 1) {
                        layer.alert("最多选择一个收费员",{icon:4},function (index) {
                            layer.close(index);
                        });
                    }else if (delList.length < 1) {
                        layer.alert("请选择一个收费员",{icon:4},function (index) {
                            layer.close(index);
                        });
                    }
                    break;
            }
        });
        //打开添加页面
        function openAddCharge(){
            layer.open({
                type: 1,
                title: '添加收费员',
                content: $('#addCharge'),
                area: ['600px','400px'],
                shade: [0.5,'#fff'], //0.5透明度的白色背景
                // shadeClose: true, //开启遮罩关闭
                skin: 'layui-layer-molv',//墨绿皮肤
                offset: '50px',//上边距
                shift: 1 //动画类型
            });
            //提交添加表单
            form.on('submit(addCharge)', function(data){
                if ($(".adminPass").val() != $(".surePass").val()) {
                    layer.alert("确认密码有误",{icon:2},function (index) {
                        layer.close(index);
                    });
                }else {
                    var msg=JSON.stringify(data.field);
                    $.post("${pageContext.request.contextPath}/ChargeController/insertNewCharge?msg="+encodeURI(msg),function (msg) {
                        if (msg=='true'){
                            layer.alert('收费员添加成功',{icon:6},function () {
                                location.reload(true);
                            });
                        } else {
                            layer.alert(msg,{icon:2});
                        }
                    })
                }
                return false;//阻止页面刷新

            });
        }
        //打开编辑页面
        function openUpdateAdmin(obj){
            layer.open({
                type: 1,
                title: '编辑收费员',
                content: $('#updateCharge'),
                area: ['600px','350px'],
                shade: [0.5,'#fff'], //0.5透明度的白色背景
                skin: 'layui-layer-molv',//墨绿皮肤
                offset: '50px',//上边距
                shift: 2, //动画类型
                success:function (index) {
                    form.val("formUpdateAdmin",obj.data);
                }
            });

            //提交修改表单
            form.on('submit(formUpdateAdmin)', function(data){
                var msg=JSON.stringify(data.field);
                alert(msg);
                $.post("${pageContext.request.contextPath}/ChargeController/updateCharge?msg="+encodeURI(msg),function (msg) {
                    if (msg==="true"){
                        layer.alert('修改成功',{icon:6},function (index) {
                            window.location.reload(true);
                        });
                    } else {
                        layer.alert("修改失败",{icon:2});
                    }
                });
                return false;//阻止页面刷新
            });
        }
        //打开详情页面
        function openDetail(obj){
            layer.open({
                type: 1,
                title: '收费员详情',
                closeBtn: 0,
                content: $('#showDetail'),
                area: ['500px','400px'],
                shade: [0.5,'#fff'], //0.5透明度的白色背景
                skin: 'layui-layer-molv',//墨绿皮肤
                offset: '50px',//上边距
                shift: 2, //动画类型
                shadeClose: true,
                success:function (index) {
                    form.val("formDetail",obj.data);
                }
            });
        }
        //打开上传头像页面
        function openUpload(obj){
            layer.open({
                type: 1,
                closeBtn: 0,
                content: $('#uploadImg'),
                area: ['450px','300px'],
                shade: [0.5,'#fff'], //0.5透明度的白色背景
                skin: 'layui-layer-molv',//墨绿皮肤
                offset: '50px',//上边距
                shift: 2, //动画类型
                shadeClose: true,
                success:function (index) {
                    //上传头像点击事件
                    layui.use('upload', function(){
                        var upload = layui.upload;
                        console.log(upload);
                        //执行实例
                        var uploadInst = upload.render({
                            elem: '#upload' //绑定元素
                            ,url: '${pageContext.request.contextPath}/ChargeController/uploadHeadImg' //上传接口
                            ,data:{adminId:obj}
                            ,done: function(res){
                                layer.alert(res);
                            }
                            ,error: function(){
                                //请求异常回调
                            }
                            ,accept: 'images'
                            ,size: 5120 //限制文件大小，单位 KB
                            ,progress: function(n, elem){
                                var percent = n + '%';//获取进度百分比
                                element.progress('progress-bar', percent); //可配合 layui 进度条元素使用
                                //以下系 layui 2.5.6 新增
                                console.log(elem); //得到当前触发的元素 DOM 对象。可通过该元素定义的属性值匹配到对应的进度条。
                            }
                        });
                    });
                }
            });
        }
        //提交条件查询表单
        form.on('submit(formSearch)', function(data){
            var msg=JSON.stringify(data.field);
            //表格重载之后就会根据条件参数进行分页
            table.reload('chargeTable',{
                page:{
                    curr:1//从第一页开始
                },
                where:{
                    msg:msg//条件
                },
                method:'post',
                url:'${pageContext.request.contextPath}/ChargeController/getChargeList'

            })
        });
        //监听行工具条事件
        table.on('tool(chargeTable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            if(layEvent === 'edit'){ //编辑
                openUpdateAdmin(obj);
            }
            else if(layEvent === 'modifyState'){//更新状态
                console.log(data);
                var state = data.adminState;
                var newState;
                if (state==='启用'){
                    newState = "禁用";
                }else{
                    newState = "启用";
                }
                layer.confirm('是否'+newState+'？',{icon:7},function (index) {
                    layer.close(index);
                    $.post("${pageContext.request.contextPath}/ChargeController/updateState?adminName="+data.adminName+"&adminState="+newState,null,function (msg) {
                        msg=msg+'';
                        if (msg == 'true') {
                            layer.alert('更新成功',{icon:6},function () {
                                location.reload(true);
                            });
                        }else {
                            layer.alert('更新失败',{icon:2});
                        }
                    });
                });
            }
            else if (layEvent==='logOff'){//离职
                layer.confirm('是否离职？',null,function (index) {
                    layer.close(index);
                    $.post("${pageContext.request.contextPath}/ChargeController/logOff?adminName="+data.adminName,null,function (msg) {
                        if (msg==='true'){
                            layer.alert('离职成功',{icon:6},function () {
                                location.reload(true);
                            });
                        }else{
                            layer.alert('离职失败',{icon:2});
                        }
                    });
                });
            }
            else if (layEvent==='resetPass'){
                layer.confirm('是否重置密码？',null,function (index) {
                    layer.close(index);
                    $.post("${pageContext.request.contextPath}/ChargeController/resetPass?adminName="+data.adminName,null,function (msg) {
                        if (msg==='true'){
                            layer.alert('密码重置成功',{icon:6});
                        }else{
                            layer.alert('密码重置失败',{icon:2});
                        }
                    });
                });
            }
            else if (layEvent==='showHead') {
                    var pathUrl = "${pageContext.request.contextPath}";
                    //页面层-图片
                    layer.open({
                        type: 1,
                        title: false,
                        closeBtn: 0,
                        area: ['500px','500px'],
                        skin: 'layui-layer-nobg', //没有背景色
                        shadeClose: true,
                        content: '<img src="'+ pathUrl + data.headImg +'">',
                    });
            }
            else if (layEvent==='showDetailForm'){
                openDetail(obj);
            }
        });
        //	自定义验证规则
        form.verify({
            adminName: function(value){
                if(value.length < 2){
                    return '管理员名称至少得2个字符啊';
                }
            }
            ,adminPass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且不能出现空格'
            ]
            ,adminTel: function (value) {
                if (value.lenth != 11) {
                    return '请输入11位手机号码';
                }
            }
        });
    });
</script>
</body>
</html>
