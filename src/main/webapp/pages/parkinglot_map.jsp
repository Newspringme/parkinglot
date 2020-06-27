<%@ page import="com.cnzk.pojo.TbCoordinate" %><%--
  Created by IntelliJ IDEA.
  User: 86189
  Date: 2020/6/10
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
    <%--TbCoordinate machine =(TbCoordinate) request.getSession().getAttribute("machine");--%>
    <%--String x = machine.getCoordinateX();--%>
    <%--String y = machine.getCoordinateY();--%>
<%--%>--%>
<html>
<head>
    <meta charset="utf-8">
    <title>地图</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <link href="${pageContext.request.contextPath}/static/Case/lib/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/static/Case/css/common.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/static/Case/css/iconfont/iconfont.css" rel="stylesheet" />
</head>
<style type="text/css">
    .viewmode-group {
        position: absolute;
        right: 12px;
        top: 32%;
        border-radius: 6px;
        border: none;
    }
    #pannel {
        position: absolute;
        left: 2%;
        bottom: 10%;
        z-index: 999;
    }
    .viewmode-group button {
        display: inline-block;
        width: 38px;
        height: 38px;
        border-radius: 4px;
        border: none;
        background-image: url("${pageContext.request.contextPath}/static/Case/image/wedgets/3D.png");
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

    .search {
        position: absolute;
        padding-left: 10px;
        top: 60px;
        left: 150px;
        font-size: 13px;
        height: auto;
        border: 1px solid #e6e6e6;
        background: #fff;
        /* box-shadow: 3px 3px 5px #bdbdbd; */
        box-sizing: border-box;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        z-index: 999;
        border-radius: 4px;
    }

    .searchText {
        width: 300px;
        height: 20px;
        outline: none;
        border: none;
        margin: 0 0 0 14px;
        font-size: 13px;
        -webkit-appearance: none;
    }

    .router {
        height: 40px;
        padding: 6px 0;
        box-sizing: border-box;
    }

    .router .routerInput  {
        width: 250px;
        border: none;
        outline: 0;
        height: 20px;
        font-size: 16px;
        border-bottom: 1px solid #dedede;
        margin-left: 6px;
    }

    #shopSearch>ul {
        display: block;
        list-style-type: none;
        /* height: 0; */
        padding: 10px;
    }

    .list ul>li {
        list-style: none;
        padding: 10px 12px;
        font-size: 13px;
        color: #5d5d5d;
        cursor: pointer;
        box-sizing: border-box;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
    }

    .list ul>li>span {
        padding-right: 18px;
    }

    .list ul>li:hover {
        background: #bbb;
        color:#fff;
    }

    #description {
        position: absolute;
        left: 50%;
        top: 100px;
        padding: 10px 25px;
        background: rgba(255, 255, 255, 255);
        border-radius: 4px;
        margin-left: -140px;
        opacity: 0.7;
    }

    button,input[type="button"] {
        padding: 7px 11px;
        background-color: #fff;
        border: none;
        cursor: pointer;
        border-radius: 3px;
    }

    .container-fluid h1 {
        text-align: center;
    }
</style>
<body ms-controller = "ctrl" class="ms-controller">
<%--<input type="hidden" id="coorX" value="<%=x%>">--%>
<%--<input type="hidden" id="coorY" value="<%=y%>">--%>
<div id="map-container"></div>
<div class="loading">
    <div class="lodingImg"></div>
</div>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <h1>
            <a href="" title="菜鸟室内三维地图">菜鸟室内三维地图</a>
        </h1>
    </div>
</nav>
<!-- 搜索 -->
<div class="search">
    <span id="btnSearch" class="glyphicon glyphicon-search" aria-hidden="true"></span>
    <input id="searchText" type="text" class="searchText" placeholder="输入车牌号"> |
    <%--<button type="button" id="startnav" class="btn btn-default" style="border: none">--%>
        <%--<span id="btnNav" class="glyphicon glyphicon-map-marker"></span>--%>
        <%--导航--%>
    <%--</button>--%>
    <button type="button" id="searchCar" class="btn btn-default" style="border: none">
        <span id="btnSearchCar" class="glyphicon glyphicon-search"></span>
        搜索车辆
    </button>
</div>
<div id="description">
    暂无导航提示信息
</div>
<%--3D/2D切换--%>
<div id="pannel">
    <input type="button" class="btn btn-default btnclass" onclick="clearNavi()" value="清除" />
    <input type="button" class="btn btn-default btnclass" onclick="startNavi1()" value="开始第一人称导航" />
    <input type="button" class="btn btn-default btnclass" onclick="startNavi2()" value="开始第三人称导航" />
</div>
<div class="viewmode-group">
    <button id="btn3D" class="btn btn-default"></button>
</div>
<%--车位情况--%>
<div class="parking fix" id="parking"><span id="park_id"></span>车位情况：<span id="park_state"></span></div>
<div class="codition fix">
    <ul>
        <li><span class="codition-first"></span>已停车</li>
        <li><span class="codition-second"></span>未停车</li>
    </ul>
</div>
<%--停车场车位余量--%>
<div class="i-test-tip fix" id="i-test-tip">
    <div class="test-tip">
        停车场车位总数：<span id="total"></span>个，当前剩余车位数 <span id="free"></span>。
    </div>
</div>

<script src="${pageContext.request.contextPath}/static/Case/lib/esmap-1.6.min.js"></script>
<script src="${pageContext.request.contextPath}/static/Case/lib/jquery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath}/static/Case/lib/jquery.qrcode.min.js"></script>
<script src="${pageContext.request.contextPath}/static/Case/lib/tips_controls.js"></script>
<script src="${pageContext.request.contextPath}/static/Case/lib/bootstrap.min.js"></script>
<script type="text/javascript">
    //定义全局map变量
    var map;//地图对象
    var lm,im,floorLayer;//标注
    var clickCount = 0;//点击次数
    var navi; //导航对象
    var esmapID = 'cai_niao_parkinglot';
    var styleid = 1005;
    var floorControl; // 楼层控制控件配置参数（几楼）
    var dataSrc = "${pageContext.request.contextPath}/static/Case/data";
    var themeSrc = "${pageContext.request.contextPath}/static/Case/data/theme";
    var container = document.getElementById('map-container');
    var parkData;//停车场数据
    //rgb颜色：红，绿，黄（高亮）
    var color = ["#ff0000", "#00ff00", "#ffff00"];
    window.onload = function () {
        if (navi) {
            navi.stop();
            navi = null;
        }
        var ctlOpt = new esmap.ESControlOptions({
            position: esmap.ESControlPositon.RIGHT_TOP,
            imgURL: "${pageContext.request.contextPath}/static/Case/image/wedgets/"
        });
        // 放大、缩小控件配置
        var ctlOpt1 = new esmap.ESControlOptions({
            position: esmap.ESControlPositon.LEFT_TOP, // 位置 左上角
            // 位置x,y的偏移量
            offset: {
                x: 10,
                y: 100
            },
            imgURL: "${pageContext.request.contextPath}/static/Case/image/wedgets/"
        });
        map = new esmap.ESMap({
            container: container, // 渲染dom
            mapDataSrc: dataSrc, //地图数据位置
            mapThemeSrc: themeSrc, //主题数据位置
            focusAlphaMode: true, // 对不可见图层启用透明设置 默认为true
            focusAnimateMode: true, // 开启聚焦层切换的动画显示
            focusAlpha: 0.4, // 对不聚焦图层启用透明设置，当focusAlphaMode = true时有效
            focusFloor: 1,
            mapAudioSrc: '${pageContext.request.contextPath}/static/Case/lib',
            token:'20200611',
            themeID: styleid, //自定义样式主题ID
            mapAudioSrc: '${pageContext.request.contextPath}/static/Case/lib',
        });
        map.openMapById(esmapID); //打开地图
        map.showCompass = true; //显示指南针
        //地图加载完成回调
        map.on("loadComplete", function () {
            floorControl = new esmap.ESScrollFloorsControl(map, ctlOpt);
            floorLayer = map.getFloor(1);
            var zoomControl = new esmap.ESZoomControl(map, ctlOpt1);
            createNavi();
            bingEvents();
            marquee();
            // //先执行显示一次
            setTimeout(function () {getParkData();},10);
            //开启定时器从后台获取数据
            setInterval(function () {getParkData();}, 15000);}
        );
        //判断起点是否是同一处坐标
        var lastCoord = null;
        var curfnum = null;
        var h = 1;
        //点击地图事件，开始选点开始后，点击地图一次为起点，第二次点击为终点
        map.on('mapClickNode', function (event) {
            if (event.nodeType == 4) {
                curfnum = event.floor;
                h = 1;
                mapCoord = event.hitCoord;
            }
            if (event.nodeType == 5) {
                curfnum = event.FloorNum;
                h = event.RoomHigh;
                mapCoord = event.hitCoord;
            }
            if (event.name != "路" && event.name != "自助机" && event.name != "人行道"){
                $("#parking").css("fontSize", "18px").html();
                $("#park_id").css("color", "#ffff00").html(event.name); //停车位ID(parkName)
                removeAll();
            }
            for (var i = 0; i < parkData.length; ++i) {
                if (event.ID == parkData[i].eventId) {
                    $("#park_state").html(parkData[i].parkState);
                }
            }
        });

        //为模型填充div添加点击事件
        container.onclick = function () {
            var fnum = curfnum;
            fnum&&mapCoord&&show(fnum, mapCoord);
        };
        container.ontouchend = function(){
            var fnum = curfnum;
            fnum&&mapCoord&&show(fnum, mapCoord);
        };

        //点击时的标注
        show = function (fnum, coord) {
            if (!navi) return;
            if (coord != null) {
                //第三次点击清除路径，重现设置起点起点
                if (clickCount == 2) {
                    navi.clearAll();
                    clickCount = 0;
                    lastCoord = null;
                }

                //第一次点击添加起点
                if (clickCount == 0) {
                    lastCoord = coord;
                    navi.setStartPoint({
                        x: coord.x,
                        y: coord.y,
                        fnum: fnum,
                        height: h,
                        url: '${pageContext.request.contextPath}/static/Case/image/start.png',
                        size: 64
                    });
                } else if (clickCount == 1) { //添加终点并画路线
                    //判断起点和终点是否相同
                    if (lastCoord.x == coord.x) {
                        alert("起点和终点不能相同!,请重新选点")
                        return;
                    }
                    navi.setEndPoint({
                        x: coord.x,
                        y: coord.y,
                        fnum: fnum,
                        height: h,
                        url: '${pageContext.request.contextPath}/static/Case/image/end.png',
                        size: 64
                    });
                    // 画导航线
                    navi.drawNaviLine();
                }
                clickCount++;
            }
            curfnum = null;
        };
    };

    //获取停车场数据
    function getParkData() {
        $.getJSON("${pageContext.request.contextPath}/parkController/getParkData",function (data) {
            parkData = data.put;
            //解析数据
            var total = 0;
            var d = {"idList":[[],[],[]]};
            for (var i = 0; i < parkData.length; i++)
            {
                var park = parkData[i];
                var parkState;
                if (park.parkState == "已停车")
                {
                    parkState = 1;
                }else{
                    parkState = 2;
                }
                total++;
                d.idList[parkState].push(park.eventId);
            }
            var showText = "";

            //调用批量修改颜色接口来修改
            map.changeModelColor({
                id: d.idList[1],
                color: color[0]
            });
            map.changeModelColor({
                id: d.idList[2],
                color: color[1]
            });
            showText += ":"+d.idList[2].length+"个";
            //3.显示更新统计
            $("#free").html(showText); //滚动字幕 相应楼层剩余停车位数
            $("#total").html(total);
        });
    }

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

    //绑定事件2D-3D
    function bingEvents() {
        document.getElementById("btn3D").onclick = function () {
            if (map.viewMode == esmap.ESViewMode.MODE_2D) {
                map.viewMode = esmap.ESViewMode.MODE_3D; //2D-->3D
                document.getElementById("btn3D").style.backgroundImage =
                    "url('${pageContext.request.contextPath}/static/Case/image/wedgets/3D.png')";
            } else {
                map.viewMode = esmap.ESViewMode.MODE_2D; //3D-->2D
                document.getElementById("btn3D").style.backgroundImage =
                    "url('${pageContext.request.contextPath}/static/Case/image/wedgets/2D.png')";
            }
        };
    }

    //添加当前位置标注
    <%--function addCurrPoint(){--%>
        <%--var machineX = $("#coorX").val();--%>
        <%--var machineY = $("#coorY").val();--%>
        <%--var layer = new esmap.ESLayer('textMaker');--%>
        <%--layer.name = 'mylayer';--%>
        <%--//添加文本标注，默认样式--%>
        <%--var tm = new esmap.ESTextMarker({--%>
            <%--x: machineX - 0,--%>
            <%--y: machineY - 0,--%>
            <%--name: "当前位置",--%>
            <%--image: "${pageContext.request.contextPath}/static/Case/image/start.png",--%>
            <%--imageAlign: "bottom",--%>
            <%--imageSize: 60,--%>
            <%--height: 3,--%>
            <%--showLevel: 20,--%>
            <%--fillcolor: "255,0,0", //填充色--%>
            <%--fontsize: "12.0", //字体大小--%>
            <%--strokecolor: "255,255,0" //边框色--%>
        <%--});--%>
        <%--layer.addMarker(tm);--%>
        <%--floorLayer.addLayer(layer);--%>
    <%--}--%>

    var searchText;
    //搜索车辆
    $("#searchCar").on("click",function () {
        removeAll();
        searchText = $("#searchText").val();
        if (searchText != null || searchText != "") {
            $.post("${pageContext.request.contextPath}/parkController/queryParkByCarNum?searchText="+searchText,function (data) {
                if (data==="false") {
                    alert("停车场内没有车牌号为：【"+searchText+"】的车");
                }else{
                    var park = JSON.parse(data);
                    map.changeModelColor({
                        id: park.eventId,
                        color: color[2]
                    });
                    var layer = new esmap.ESLayer('imageMarker');
                    im = new esmap.ESImageMarker({
                        x: park.tbCoordinate.coordinateX - 0,
                        y: park.tbCoordinate.coordinateY - 0,
                        url: '${pageContext.request.contextPath}/static/Case/image/user.png',
                        size: 64,
                        showLevel: 20,
                        height: 0.5,
                        id: 1,
                        name: 'imageMarker'
                    });
                    layer.addMarker(im);
                    floorLayer.addLayer(layer);
                }
            });
        } else {
            alert("请输入车牌号");
        }
    });

    //清除功能
    var removeAll = function () {
        var re = floorLayer.removeLayersByTypes([esmap.ESLayerType.IMAGE_MARKER]);
        lm && map.removeLocationMarker(lm);
    };

    //创建导航对象
    function createNavi() {
        if (map.naviData.length == 0) {
            console.warn("地图导航数据信息不存在！");
            return;
        }
        if (!navi) {
            //初始化导航对象
            navi = new esmap.ESNavigation({
                map: map,
                locationMarkerUrl: '${pageContext.request.contextPath}/static/Case/image/pointer.png',
                locationMarkerSize: 150,
                speed: 1,
                followAngle: true,
                followPosition: true,
                followGap: 3,
                tiltAngle: 30,
                audioPlay: true,
                // scaleLevel:0,
                mode: 1,
                offsetHeight: 1,
                // 设置导航线的样式
                lineStyle: {
                    color: '#58a2e4',
                    //设置线为导航线样式
                    lineType: esmap.ESLineType.ESARROW,
                    // lineType: esmap.ESLineType.FULL,
                    lineWidth: 6,
                    offsetHeight: 0.5,
                    smooth: true,
                    seeThrough: false,
                    noAnimate: true
                    // 设置边线的颜色
                    // godEdgeColor: '#920000',
                    // 设置箭头颜色
                    // godArrowColor: "#ffffff"
                },
                scaleAnimate: true,
                isMultiFloors: false
            });
        }
        navi.on("walking", function (data) {
            //显示导航展示信息
            showDis(data);
        });

        navi.on("complete", function () {
            console.log("停止");
            document.getElementById('description').innerText = "到达终点";
        })
    }

    //清除导航数据
    function clearNavi() {
        if (navi)
            navi.clearAll();
        clickCount = 0;
        document.getElementById("description").innerText = "暂无导航提示信息";
    }

    //第一人称导航
    function startNavi1() {
        navi.followAngle = true;
        navi.followPosition = true;
        navi.scaleAnimate = true;
        if(navi.isSimulating){
            navi.stop();
            navi.reset();
        }
        //下句解决IOS苹果手机不能播放问题，不能删除
        map.ESAudioTool.playSoundByName('startNaving');
        navi.simulate();
    }

    //第三人称导航
    function startNavi2() {
        if(navi.isSimulating){
            navi.stop();
            navi.reset();
        }
        navi.followAngle = false;
        navi.followPosition = false;
        navi.scaleAnimate = false;
        //下句解决IOS苹果手机不能播放问题，不能删除
        map.ESAudioTool.playSoundByName('startNaving');
        navi.simulate();
    }

    //显示路径数据
    function showDis(data) {
        //距终点的距离
        var distance = data.remain;
        //路线提示信息
        var info = navi.naviDescriptions[data.index];
        var f = info[0] + parseInt(data.distanceToNext) + info[2];
        //普通人每分钟走80米。
        var time = distance / 80;
        var m = parseInt(time);
        var s = Math.floor((time % 1) * 60);
        document.getElementById('description').innerHTML = '<p>距终点：' + distance.toFixed(1) + ' 米</p><p>大约需要：  ' + m + '  分钟   ' + s +
            '   秒</p><p>路线提示：' + f + ' </p>';
    }
</script>
</body>
</html>
