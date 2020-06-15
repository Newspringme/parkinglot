<%--
  Created by IntelliJ IDEA.
  User: 96581
  Date: 2020/6/6
  Time: 16:22
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
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=path%>/static/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=path%>/static/css/public.css" media="all">
    <style>
        body {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<div class="layui-form layuimini-form">
    <div class="layui-form-item">
        <label class="layui-form-label">id</label>
        <div class="layui-input-block">
            <input type="text" name="paramid"  value="<%=request.getParameter("paramId")%>" class="layui-input" readonly="readonly">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">参数名</label>
        <div class="layui-input-block">
            <input type="text" name="paramname" lay-verify="required"  value="<%=request.getParameter("paramName")%>" class="layui-input" readonly="readonly">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">参数值</label>
        <div class="layui-input-block">
            <input type="text" name="paramvalue" lay-verify="required" lay-reqtext="参数值不能为空" placeholder="请输入参数值" value="<%=request.getParameter("paramValue")%>" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
    </div>
</div>
<script src="<%=path%>/static/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.$;

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            $.ajax({
                url:"${pageContext.request.contextPath}/AdminController/editParam"
                ,type:"POST"
                ,dataType:"text"
                ,data:{
                    paramId:data.field.paramid,
                    paramName:data.field.paramname,
                    paramValue:data.field.paramvalue
                },
                success:function (msg) {
                    msg=msg+"";
                    if (msg=='true'){
                        var index = layer.alert("修改成功",  function () {
                            // 关闭弹出层
                            layer.close(index);
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);

                        });
                    }else {
                        layer.alert("修改失败");
                    }

                }
            })
            return false;
        });

    });
</script>
</body>
</html>