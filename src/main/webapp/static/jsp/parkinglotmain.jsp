<%--
  Created by IntelliJ IDEA.
  User: LQ
  Date: 2020-6-6
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台主界面</title>

    <%
        String path = request.getContextPath();
        System.out.println(path);
    %>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/layui/css/layui.css"/>

    <script charset="UTF-8" src = "${pageContext.request.contextPath}/static/js/jquery-3.4.1.js" charset="utf-8"></script>
    <script charset="UTF-8" src="${pageContext.request.contextPath}/static/js/json2.js" type="text/javascript" ></script>
    <script charset="UTF-8" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>

</head>
<body class="layui-layout-body">

<input type="hidden" id="path" value="<%=path%>">
<div class="layui-btn-group">
    <button type="button" class="layui-btn" onclick="findmenu()">菜单</button>

</div>

<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">layui 后台布局</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test" lay-shrink="all" style="height: 100%">
                <c:if test="${not empty menuMap}">
                    <c:forEach items="${menuMap}" var="menu1" step="1">
                        <li class="layui-nav-item">
                            <a href="javascript:;">${menu1.key}</a>
                            <dl class="layui-nav-child">
                                <c:forEach items="${menu1.value}" var="menu2" step="1">
                                    <%--                            获取根目录：${pageContext.request.contextPath}        /${menu2.getMenu_url()}         拿到的是servlet返回的数据--%>
                                    <dd><a href= "javascript:void(0)" title="${pageContext.request.contextPath}/${menu2.getMenuUrl()}" onclick="getsrc(this)">${menu2.getMenuName()}</a></dd>
                                </c:forEach>
                            </dl>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <iframe src = "" name = "click" style = "border:0" id="myiframe"></iframe>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script charset="UTF-8" src="../layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
        
    });
    function findmenu() {

        var path = $("#path").val();
        $.ajax({
            url: path + "/LoginController/findMenu",
            async: true,
            type: "POST",
            success: function (data) {
                if (data=='success') {
                    location.href=path+"/static/jsp/parkinglotmain.jsp";
                }
            }
        });
    }

    //iframe的页面路径获取
    var mytitle;
    function getsrc(node){
        mytitle = node.title;
        var myiframe = document.getElementById("myiframe");
        myiframe.src = mytitle;
        console.log(myiframe.src)
    }

</script>
</body>
</html>