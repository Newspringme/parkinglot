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
        <label class="layui-form-label required">轮播图</label>

        <div class="layui-upload-drag" id="test10">
            <p>点击上传，或将文件拖拽到此处</p>
            <div class="layui-hide" id="uploadDemoView">
                <hr>
                <img src="" alt="上传成功后渲染" style="max-width: 196px">
                <input type="hidden" name="imgurl" id="imgurl" lay-verify="required" lay-reqtext="图片不能为空" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">标题</label>
        <div class="layui-input-block">
            <input type="text" name="title" lay-verify="required" lay-reqtext="标题不能为空" placeholder="请输入标题" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">权限值</label>
        <div class="layui-input-block">
            <input type="number" name="weight" lay-verify="required" lay-reqtext="权限值不能为空,只能是阿拉伯数字" placeholder="请输入权限值" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">开始时间</label>
        <div class="layui-input-block">
            <input type="text" name="starttime" id="starttime" lay-verify="required" placeholder="请输入开始日期" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">结束时间</label>
        <div class="layui-input-block">
            <input type="text" name="endtime" id="endtime" lay-verify="required" placeholder="请输入结束日期" autocomplete="off" class="layui-input">
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
    layui.use(['form','laydate'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.$,
        laydate = layui.laydate;
        laydate.render({
            elem: '#starttime'
        });
        laydate.render({
            elem: '#endtime'
        });
        //监听提交
        form.on('submit(saveBtn)', function (data) {
            console.log(layui.$('#uploadDemoView').find('img').attr('src'));
            console.log("title"+data.field.title);
            console.log("weight"+data.field.weight);
            console.log("starttime"+data.field.starttime);
            console.log("endtime"+data.field.endtime);
            $.ajax({
                url:"${pageContext.request.contextPath}/AdminController/addSlideShow"
                ,type:"POST"
                ,dataType:"text"
                ,data:{
                    url:layui.$('#uploadDemoView').find('img').attr('src'),
                    title:data.field.title,
                    weight:data.field.weight,
                    starttime:data.field.starttime,
                    endtime:data.field.endtime,
                },
                success:function (msg) {
                    msg=msg+"";
                    if (msg=='true'){
                        var index = layer.alert("添加成功",  function () {
                            // 关闭弹出层
                            layer.close(index);
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);

                        });
                    }else {
                        layer.alert("添加失败");
                    }

                }
            })
            return false;
        });

    });
    layui.use('upload', function() {
        var $ = layui.jquery
            , upload = layui.upload;
        //拖拽上传
        upload.render({
            elem: '#test10'
            , url: '/parkinglot/AdminController/slideshowupload' //改成您自己的上传接口
            , done: function (res) {
                layer.msg(res.msg);
                console.log(res.files)
                layui.$('#uploadDemoView').removeClass('layui-hide').find('img').attr('src', res.files);
                $('#imgurl').val(res.files);
            }
        });
    })
</script>
</body>
</html>