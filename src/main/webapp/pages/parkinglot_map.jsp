<%--
  Created by IntelliJ IDEA.
  User: 86189
  Date: 2020/6/10
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>地图</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <link href="../static/esmap/lib/bootstrap.min.css" rel="stylesheet" />
    <script src="../static/esmap/lib/esmap-1.6.min.js" > </script >
</head>

<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <h1>
            室内停车场三维地图
        </h1>
    </div>
</nav>
<div id="map-container"></div>

<script type="text/javascript">
    var map = new esmap.ESMap({
        container:document.getElementById('map-container'), //地图显示容器
        token:"escope",  //打开地图对应的token
        mapDataSrc: "../static/esmap/data/", //地图数据位置，与推荐目录相同可省略
        mapThemeSrc: "../static/esmap/data/theme/", //地图主题位置，与推荐目录相同可省略
        viewMode: esmap.ESViewMode.MODE_3D ,	//初始二维还是三维状态
        defaultScaleLevel: 12,              //设置默认显示级别,1到24之间的整数
    });
    //根据ID打开地图
    map.openMapById(10005);
</script>
</body>
</html>
