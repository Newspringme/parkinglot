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
    <link href="${pageContext.request.contextPath}/static/esmap/lib/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/static/esmap/css/common.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/static/esmap/css/iconfont/iconfont.css" rel="stylesheet" />

    <script src="${pageContext.request.contextPath}/static/esmap/lib/config.js"></script>
    <script src="${pageContext.request.contextPath}/static/esmap/lib/esmap-1.6.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/esmap/lib/jquery-2.1.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/esmap/lib/jquery.qrcode.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/esmap/lib/tips_controls.js"></script>
    <script src="${pageContext.request.contextPath}/static/esmap/lib/bootstrap.min.js"></script>
</head>
<style type="text/css">
    .viewmode-group {
        position: absolute;
        right: 12px;
        top: 32%;
        border-radius: 6px;
        border: none;
    }

    .viewmode-group button {
        display: inline-block;
        width: 38px;
        height: 38px;
        border-radius: 4px;
        border: none;
        background-image: url("${pageContext.request.contextPath}/static/esmap/image/wedgets/3D.png");
    }

    .parking {
        width: 320px;
        height: 46px;
        line-height: 46px;
        left: 100px;
        bottom: 14px;
        border: 1px solid #083344;
        border-radius: 4px;
        color: rgb(255, 255, 255);
        background-color: rgba(71, 92, 105, 0.8);
        font-size: 16px;
        text-align: center;
    }

    .fix {
        position: fixed;
    }

    .codition {
        width: 120px;
        left: 10px;
        bottom: 120px;
    }

    .codition ul {
        width: 100%;
        padding: 6px;
        list-style-type: none;
    }

    .codition ul li {
        display: list-item;
        height: 36px;
        background-color: rgb(255, 255, 255);
        line-height: 36px;
        text-align: center;
    }

    .codition ul li span {
        display: inline-block;
        width: 18px;
        height: 18px;
        border-radius: 50%;
        margin-right: 10px;
        vertical-align: middle;
    }

    .codition-first {
        background-color: #f00;
    }

    .codition-second {
        background-color: #0f0;
    }

    .codition-third {
        background-color: rgb(126, 172, 202);
    }

    .i-test-tip {
        width: 360px;
        height: 46px;
        left: 470px;
        bottom: 14px;
        border-radius: 4px;
        overflow: hidden;
        /* position: relative; */
        background-color: rgba(71, 92, 105, 0.8);
        text-align: center;
    }

    .test-tip {
        position: absolute;
        top: 0;
        left: 100%;
        color: #fff;
        font-size: 24px;
        line-height: 46px;
        white-space: nowrap;
        word-break: keep-all;
        text-overflow: ellipsis;
    }

    .test-tip span {
        color: #0f0;
    }
</style>
<body ms-controller = "ctrl" class="ms-controller">
<div id="map-container"></div>
<div class="loading">
    <div class="lodingImg"></div>
</div>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <h1>
            <a href="" title="室内地图-地图轨迹回放">菜鸟室内三维地图</a>
        </h1>

        <div class="tips-right">
            <span class="tip1"></span> <span class="tip2"></span>
        </div>
        <div class="tips-msg">
            <div class="msg msg1">
                <div class="erweima"></div>
                <p>手机扫一扫进入体验</p>
            </div>
            <div class="msg msg2">
                <h4>停车场车位占用</h4>
                <p>1. 停车场车位占用例子定时5秒从后台获取数据</p>
                <div style="display: none">停车场车位占用例子定时5秒从后台获取数据,停车场导航,商场导航,停车场定位,易景室内三维地图引擎提供地图浏览、缩放、旋转、图层显隐等基础功能，支持自定义室内地图显示风格及样式，可自动绘制楼层热力图、散点图等专题地图，快速进行空间大数据分析展示。支持跨楼层精准的点到点之间的最短、最优路径计算，支持对路径结果进行导航和动画,并提供丰富的地图主题资源供二次开发调用。</div>
            </div>
        </div>
    </div>
</nav>

<div class="viewmode-group">
    <button id="btn3D" class="btn btn-default"></button>
</div>
<div class="parking fix" id="parking"><span id="carid"></span>车位情况：<span id="YorN"></span></div>
<div class="codition fix">
    <ul>
        <li><span class="codition-first"></span>已停车</li>
        <li><span class="codition-second"></span>未停车</li>
    </ul>
</div>
<div class="i-test-tip fix" id="i-test-tip">
    <div class="test-tip">
        停车场车位总数：<span id="total"></span>个，当前剩余车位数 <span id="freedata"></span>。
    </div>
</div>

<script type="text/javascript">
    //定义全局map变量
    var map;
    var esmapID = 'cai_niao_parkinglot';
    var styleid = getQueryString("styleid") || defaultOpt.themeID;
    var floorControl;
    // 楼层控制控件配置参数（几楼）
    var ctlOpt = new esmap.ESControlOptions({
        position: esmap.ESControlPositon.RIGHT_TOP,
        imgURL: "${pageContext.request.contextPath}/static/esmap/image/wedgets/"
    });
    // 放大、缩小控件配置
    var ctlOpt1 = new esmap.ESControlOptions({
        position: esmap.ESControlPositon.LEFT_TOP, // 位置 左上角
        // 位置x,y的偏移量
        offset: {
            x: 20,
            y: 80
        },
        imgURL: "${pageContext.request.contextPath}/static/esmap/image/wedgets/"
    });
    var dataSrc = "${pageContext.request.contextPath}/static/esmap/data";
    var themeSrc = "${pageContext.request.contextPath}/static/esmap/data/theme";
    map = new esmap.ESMap({
        container: $("#map-container")[0], // 渲染dom
        mapDataSrc: dataSrc, //地图数据位置
        mapThemeSrc: themeSrc, //主题数据位置
        focusAlphaMode: true, // 对不可见图层启用透明设置 默认为true
        focusAnimateMode: true, // 开启聚焦层切换的动画显示
        focusAlpha: 0.4, // 对不聚焦图层启用透明设置，当focusAlphaMode = true时有效
        focusFloor: 1,
        mapAudioSrc: '${pageContext.request.contextPath}/static/esmap/lib',
        token:'20200611',
        // visibleFloors: "all",
        themeID: styleid //自定义样式主题ID
    });
    map.openMapById(esmapID); //打开地图
    map.showCompass = true; //显示指南针

    var parkData = null,
        pos = 0;
    var color = [ "#ff0000", "#00ff00"];
    var statusname=["有车","无车"];
    //地图加载完成回调
    map.on("loadComplete", function () {
        floorControl = new esmap.ESScrollFloorsControl(map, ctlOpt);
        var zoomControl = new esmap.ESZoomControl(map, ctlOpt1);
        bingEvents();
        marquee();
        //先执行显示一次；
        setTimeout(function () {CallLoadData(0)},10);
        //开启定时器从后台获取数据
        setInterval(function () {
            CallLoadData((++pos % 2));
        }, 5000);
    });

    function CallLoadData(pos) {
        var id = [];
        var fileName = 'data' + pos + '.json'; //json数据切换
        $.getJSON(fileName, function (data) {
            var mydata=new Map();
            parkData = data.put;
            //1.解析数据,将数据按每层进行整理
            var total =  0;
            for (var i = 0, len = parkData.length; i < len; ++i) {
                var m = parkData[i];
                var d=mydata.get(m.fnum);
                if(d==null)
                {
                    d = {"idlist":[[],[],[]]};
                }
                total++;
                d.idlist[m.status].push(m.ID);
                mydata.set(m.fnum,d);
            }

            var showtext="";
            //2.更新车位颜色
            for (var i = 1; i < mydata.size; i++) {
                var d1 = mydata.get(i);
                var fnum = i;
                for(var j=0;j<color.length;j++)
                {
                    //调用批量修改颜色接口来修改
                    map.changeModelColor({
                        id:d1.idlist[j],
                        fnum: fnum,
                        color: color[j]
                    });
                }
                showtext += map.floorNames[fnum-1]+":"+d1.idlist[2].length+"个  ";
            }

            //3.显示更新统计
            $("#freedata").html(showtext); //滚动字幕 相应楼层剩余停车位数
            $("#total").html(total);
        });
    }
    //地图点击事件
    map.on("mapClickNode", function (event) {
        if (event.nodeType == esmap.ESNodeType.NONE ||
            event.nodeType == esmap.ESNodeType.FLOOR ||
            event.name == "楼梯"){
            return;
        }
        $("#parking").css("fontSize", "18px").html();
        $("#carid").css("color", "rgb(255, 255, 0)").html(event.name); //停车位ID
        for (var i = 0; i < parkData.length; ++i) {
            if (event.ID == parkData[i].ID) {
                $("#YorN").html(statusname[parkData[i].status]);
            }
        }
    });

    //滚动字幕
    function marquee() {
        var scrollWidth = $('#i-test-tip').width();
        var textWidth = $('.test-tip').width();
        var i = scrollWidth;
        setInterval(function () {
            i--;
            if (i < -textWidth) {
                i = scrollWidth;
            }
            $('.test-tip').animate({
                'left': i + 'px'
            }, 8);
        }, 8);
    }

    //绑定事件
    function bingEvents() {
        document.getElementById("btn3D").onclick = function () {
            if (map.viewMode == esmap.ESViewMode.MODE_2D) {
                map.viewMode = esmap.ESViewMode.MODE_3D; //2D-->3D
                document.getElementById("btn3D").style.backgroundImage =
                    "url('${pageContext.request.contextPath}/static/esmap/image/wedgets/3D.png')";
            } else {
                map.viewMode = esmap.ESViewMode.MODE_2D; //3D-->2D
                document.getElementById("btn3D").style.backgroundImage =
                    "url('${pageContext.request.contextPath}/static/esmap/image/wedgets/2D.png')";
            }
        };
    }
</script>
</body>
</html>
