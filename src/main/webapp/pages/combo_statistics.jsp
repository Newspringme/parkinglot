<%--
  Created by IntelliJ IDEA.
  User: LQ
  Date: 2020-6-13
  Time: 10:31
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
    <title>月缴产品收入</title>
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
    <input type="hidden" id="path" value="<%=path%>">
    <div id="main" style="width: 80%;height: 74%;position: absolute;left: 50%;margin-left: -40%;"></div>


    <script>
        window.onload = function () {
            layui.use(['layer'], function () {
                var $ = layui.jquery;
                var path = $("#path").val();
                $.ajax({
                    url: path+"/AdminController/showPieComboStatistics",
                    async: true,
                    type: "POST",
                    success: function (msg) {
                        if (msg != "error") {
                            var statisticsData = msg;
                            var legendList = statisticsData.statisticsMap.legendList;
                            var inPieDataList = statisticsData.statisticsMap.inPieDataList;
                            var outPieDataList = statisticsData.statisticsMap.outPieDataList;
                            console.log(legendList);
                            console.log(inPieDataList);
                            console.log(outPieDataList);
                            createChart(legendList,inPieDataList,outPieDataList);
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

        function createChart(legendList,inPieDataList,outPieDataList) {
            var myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据
            var option = {
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b}: {c} ({d}%)'
                },
                legend: {
                    orient: 'vertical',
                    left: 10,
                    data: legendList
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        selectedMode: 'single',
                        radius: [0, '30%'],

                        label: {
                            position: 'inner'
                        },
                        labelLine: {
                            show: false
                        },
                        data: inPieDataList
                    },
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: ['40%', '55%'],
                        label: {
                            formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                            backgroundColor: '#eee',
                            borderColor: '#aaa',
                            borderWidth: 1,
                            borderRadius: 4,
                            rich: {
                                a: {
                                    color: '#999',
                                    lineHeight: 22,
                                    align: 'center'
                                },
                                hr: {
                                    borderColor: '#aaa',
                                    width: '100%',
                                    borderWidth: 0.5,
                                    height: 0
                                },
                                b: {
                                    fontSize: 16,
                                    lineHeight: 33
                                },
                                per: {
                                    color: '#eee',
                                    backgroundColor: '#334455',
                                    padding: [2, 4],
                                    borderRadius: 2
                                }
                            }
                        },
                        data: outPieDataList
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    </script>
</body>
</html>
