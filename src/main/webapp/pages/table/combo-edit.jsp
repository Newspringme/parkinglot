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
<input type="hidden" id="path" value="<%=path%>">
<div class="layui-form layuimini-form">
    <div class="layui-form-item">
        <label class="layui-form-label">套餐id</label>
        <div class="layui-input-block">
            <input type="text" name="comboId" placeholder="" value="<%=request.getParameter("comboId")%>" class="layui-input" readonly="readonly">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">套餐名</label>
        <div class="layui-input-block">
            <input type="text" name="comboName" lay-verify="comboName" lay-reqtext="套餐名不能为空" placeholder="请输入套餐名"  value="<%=request.getParameter("comboName")%>" class="layui-input" autocomplete="off">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">金额</label>
        <div class="layui-input-block">
            <input type="number" name="comboValue" lay-verify="comboValue" lay-reqtext="金额不能为空" placeholder="请输入金额"  value="<%=request.getParameter("comboValue")%>" class="layui-input" autocomplete="off">
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
        var path = $('#path').val();
        //监听提交
        form.on('submit(saveBtn)', function (data) {

            $.ajax({
                url:path+'/AdminController/editCombo'
                ,type:"POST"
                ,dataType:"text"
                ,data:{
                    comboId:data.field.comboId,
                    comboName:data.field.comboName,
                    comboValue:data.field.comboValue
                },
                success:function (msg) {
                    msg=msg+"";
                    if (msg=='true'){
                        var index = layer.alert("修改成功",  function () {
                            // 关闭弹出层
                            parent.location.reload();
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
        form.verify({
            comboName: function(value){
                if(value.length < 2){
                    return '月缴名称至少得2个字符';
                }
            }
            ,comboValue: [
                /^([1-9][0-9]*)$/
                ,'金额为数字，且不能以0开头'
            ]
        });
    });
</script>
</body>
</html>