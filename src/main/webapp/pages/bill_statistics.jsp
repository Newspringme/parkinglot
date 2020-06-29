<%--
  Created by IntelliJ IDEA.
  User: LQ
  Date: 2020-6-13
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>渠道收入</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=path%>/static/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=path%>/static/css/public.css" media="all">
    <script charset="UTF-8" src="<%=path%>/static/js/jquery-3.4.1.js" charset="utf-8"></script>
    <script src="<%=path%>/static/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
    <script charset="UTF-8" src="<%=path%>/static/js/echarts.js"></script>

</head>
<body>
<div class = "d2" style="margin: 10px;text-align: center;">
    <input type="hidden" id="path" value="<%=path%>">
    <button class= "layui-btn clickWeek" data-type="reload" onclick="clickWeek(this)">本周</button>
    <button class= "layui-btn clickMon" data-type="reload" onclick="clickMon(this)">本月</button>
    <button class= "layui-btn clickYear" data-type="reload" onclick="clickYear(this)">近半年</button>
    <br>
</div>
    <div id="selectcount" style="text-align: center"><label></label></div>

    <div id="main" style="width: 80%;height: 74%;position: absolute;left: 50%;margin-left: -40%;"></div>

<script>

</script>
<script>
    window.onload = function () {
        layui.use(['layer'], function () {
            var $ = layui.jquery;
            var path = $("#path").val();
            var mystatic = 1;
            $.ajax({
                url: path+"/AdminController/showBillStatistics",
                async: true,
                type: "POST",
                data:{"mystatic":mystatic},
                success: function (msg) {
                    if (msg != "error") {
                        var statisticsData = msg;
                        var XList = statisticsData.statisticsMap.XList;
                        var comboList = statisticsData.statisticsMap.comboList;
                        var tempList = statisticsData.statisticsMap.tempList;
                        if (statisticsData.statisticsMap.appccount==null){
                            var appccount = 0;
                        }else{
                            var appccount = statisticsData.statisticsMap.appccount;
                        }
                        console.log(XList);
                        console.log(comboList);
                        console.log(tempList);
                        console.log(appccount);
                        createChart(XList,comboList,tempList,appccount,mystatic);
                    } else {
                        layer.msg("暂无数据统计",{icon:5});
                    }
                },
                error: function () {
                    layer.msg('当前网络繁忙',{icon:2});
                    window.parent.location.href=path+"/url/login";
                }
            });
        });
    }

    function createChart(XList,comboList,tempList,appccount,mystatic) {
        var div = "<div>";
        if (mystatic==1){
            div += "<label>本周总收入额为："+appccount+"</label>" ;
        }else if(mystatic==2){
            div +="<label>本月总收入额为："+appccount+"</label>" ;
        }else if (mystatic==3){
            div +="<label>近半年总收入额为："+appccount+"</label>" ;
        }
        div += "</div>";
        $("#selectcount").html(div);
        $("#selectcount").show();

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: ''
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#6a7985'
                    }
                }
            },
            legend: {
                data: ['临时收入', '月缴收入']
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: XList
                }
            ],
            yAxis: [
                {
                    name: '金额',
                }
            ],
            series: [
                {
                    name: '临时收入',
                    type: 'line',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    },
                    areaStyle: {},
                    data: tempList
                },
                {
                    name: '月缴收入',
                    type: 'line',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    },
                    areaStyle: {},
                    data: comboList
                },
            ]
        };


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }

    //统计点击本周
    function clickWeek(node){
        var path = $("#path").val();
        var mystatic = 1;
        $.ajax({
            url: path+"/AdminController/showBillStatistics",
            async: true,
            type: "POST",
            data:{"mystatic":mystatic},
            success: function (msg) {
                if (msg != "error") {
                    var statisticsData = msg;
                    var XList = statisticsData.statisticsMap.XList;
                    var comboList = statisticsData.statisticsMap.comboList;
                    var tempList = statisticsData.statisticsMap.tempList;
                    if (statisticsData.statisticsMap.appccount==null){
                        var appccount = 0;
                    }else{
                        var appccount = statisticsData.statisticsMap.appccount;
                    }
                    console.log(XList);
                    console.log(comboList);
                    console.log(tempList);
                    console.log(appccount);
                    createChart(XList,comboList,tempList,appccount,mystatic);
                } else {
                    layer.msg("暂无数据统计",{icon:5});
                }
            },
            error: function () {
                layer.msg('当前网络繁忙',{icon:2});
                window.parent.location.href=path+"/url/login";
            }
        });
    }


    //统计点击本月
    function clickMon(node){
        var path = $("#path").val();
        var mystatic = 2;
        $.ajax({
            url: path+"/AdminController/showBillStatistics",
            async: true,
            type: "POST",
            data:{"mystatic":mystatic},
            success: function (msg) {
                if (msg != "error") {
                    var statisticsData = msg;
                    var XList = statisticsData.statisticsMap.XList;
                    var comboList = statisticsData.statisticsMap.comboList;
                    var tempList = statisticsData.statisticsMap.tempList;
                    if (statisticsData.statisticsMap.appccount==null){
                        var appccount = 0;
                    }else{
                        var appccount = statisticsData.statisticsMap.appccount;
                    }
                    console.log(XList);
                    console.log(comboList);
                    console.log(tempList);
                    console.log(appccount);
                    createChart(XList,comboList,tempList,appccount,mystatic);
                } else {
                    layer.msg("暂无数据统计",{icon:5});
                }
            },
            error: function () {
                layer.msg('当前网络繁忙',{icon:2});
                window.parent.location.href=path+"/url/login";
            }
        });
    }


    //统计点击近半年
    function clickYear(node) {
        var path = $("#path").val();
        var mystatic = 3;
        $.ajax({
            url: path+"/AdminController/showBillStatistics",
            async: true,
            type: "POST",
            data: {"mystatic": mystatic},
            success: function (msg) {
                if (msg != "error") {
                    var statisticsData = msg;
                    var XList = statisticsData.statisticsMap.XList;
                    var comboList = statisticsData.statisticsMap.comboList;
                    var tempList = statisticsData.statisticsMap.tempList;
                    if (statisticsData.statisticsMap.appccount==null){
                        var appccount = 0;
                    }else{
                        var appccount = statisticsData.statisticsMap.appccount;
                    }
                    console.log(XList);
                    console.log(comboList);
                    console.log(tempList);
                    console.log(appccount);
                    createChart(XList,comboList,tempList,appccount,mystatic);
                } else {
                    layer.msg("暂无数据统计", {icon: 5});
                }
            },
            error: function () {
                layer.msg('当前网络繁忙',{icon:2});
                window.parent.location.href=path+"/url/login";
            }
        });
    }


</script>
</body>
</html>
