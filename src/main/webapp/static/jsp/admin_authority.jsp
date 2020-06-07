<%--
  Created by IntelliJ IDEA.
  User: LQ
  Date: 2020-2-29
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>权限管理</title>
    <%
        String path = request.getContextPath();
        System.out.println(path);
    %>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/back/backcss/BackMain_Authority.css">

    <script charset="UTF-8" src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js" charset="utf-8"></script>
    <script charset="UTF-8" src="${pageContext.request.contextPath}/static/js/json2.js" type="text/javascript" ></script>
    <script charset="UTF-8" src="${pageContext.request.contextPath}/static/js/layui/layui.js"></script>

</head>
<body>
    <div class="authorityMsg">
    <input type="hidden" id="path" value="<%=path%>">
    <div class="top">
        <p>权限管理</p>
    </div>
    <div class="table_top">
        <label class="layui-form-label">搜索角色:</label>
        <div class="layui-inline">
            <input class="layui-input" id="rolesname" name="rolesname" placeholder="请输入角色名称" autocomplete="off" />
        </div>
        <%--    reload是表格重载--%>
        <button class= "layui-btn search" data-type="reload" style="margin-left: 26px">搜索</button>

    </div>
    <table id="authorityMsgTbl" lay-filter="test"></table>

    <%--    点击按钮弹出层的form--%>
    <form class = "layui-form" action="" style="display: none" id="changeAForm">
        <div id="test12" class="demo-tree-more"></div>
    </form>

    <%--    表格的js--%>
    <script>
    //layui.use加载模块
    layui.use(['table','layer'], function () {
        var table = layui.table,
            $ = layui.jquery;
        var path = $('#path').val();
        //第一个实例
        table.render({
            elem: '#authorityMsgTbl'   //表格的id
            , url: path+'/AuthorityController/findAdminRolesList'
            , page: true //开启分页
            , id: 'authorityMsg'
            , limits: [5, 10, 20, 30]//下拉框中得数量
            , limit: 5 //每页默认显示的数量
            , cols: [[ //表头
                {field: 'rolesid', title: 'ID', width:'20%' , sort: true, fixed: 'left',align:'center'}
                , {field: 'rolesname', title: '角色', width: '30%' ,sort: true, fixed: 'left',align:'center'}
                , {field: 'operation', title: '操作', fixed: 'right', width:'50%', align:'center', toolbar: '#barDemo'}
            ]]
        });

        $('.authorityMsg .search').on('click', function () {
            var type = $(this).data('type');
            if (type == 'reload') {
                //执行重载
                table.reload('authorityMsg', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        rolesname: $("#rolesname").val(),
                    }
                });
            }
        });

        //监听事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //obj点击那行的所有字段属性
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            var rolesid = tr.children("td").eq(0).text();
            console.log(rolesid);
            if (obj.event === 'changeAuthority')
            {
                var layer = layui.layer;
                layer.open({
                    type: 1,
                    title: '用户配置',
                    content: $('#changeAForm'),
                    offset: '50px',
                    anim: 1,
                    isOutAnim: true,
                    resize: false,
                    area: ['400px', '450px'],
                    closeBtn: 2,//弹出层的类型
                    shade: 0.6,
                    move: false,
                    btn: ['提交', '取消'],
                    btn1: function (index, layero) {
                        updateAuthority(rolesid ,index);
                        return false;
                    },
                    btn2: function (index, layero) {
                        layer.close(index);
                        return false;
                    },
                    cancel: function (index, layero) {
                        layer.close(index);
                        return false;
                    }
                });
                var path = $('#path').val();
                $.ajax({
                    url: path+"/AuthorityController/findAdminTree",
                    async: true,
                    type: 'POST',
                    data:{'rolesid':rolesid},
                    dataType:'json',
                    success: function (msg) {
                        if (data=="error") {
                            layer.msg('网络繁忙');
                        }else {
                            showTree(msg);
                        }
                    },
                    error: function () {
                        layer.msg('网络繁忙');
                        window.parent.location.href=path+"/PathJmpController/path/BackLogin";
                    }
                });
            }
        });

        function showTree(data) {
            layui.use(['tree','util'],function () {
                var tree = layui.tree
                    ,layer = layui.layer
                    ,util = layui.util;
                tree.render({
                    elem: '#test12'
                    ,data: data
                    ,showCheckbox: true  //是否显示复选框
                    ,id: 'demoId1'
                });
            });
        };

        function updateAuthority(rolesid,index){
            console.log("点击提交")
            var layer = layui.layer;
            layui.use(['tree','util'], function () {
                var tree = layui.tree;
                var checkData = tree.getChecked('demoId1');
                checkData = JSON.stringify(checkData);
                console.log(checkData);
                var path = $('#path').val();

                $.ajax({
                    url: path+"/AuthorityController/updateAdminTree",
                    async: true,
                    type: 'POST',
                    data:{'checkData':checkData,'rolesid':rolesid},
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
                        window.parent.location.href=path+"/PathJmpController/path/BackLogin";
                    }
                });
            });
        };

    });
    </script>
    <%--表格中按钮的模板--%>
    <script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="changeAuthority">修改权限</a>
    </script>
</body>
</html>
