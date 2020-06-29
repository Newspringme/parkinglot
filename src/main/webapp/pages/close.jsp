<%--
  Created by IntelliJ IDEA.
  User: ALL BLUE
  Date: 2020/6/26
  Time: 0:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>test</title>
    <link rel="stylesheet" href="<%=path%>/static/layui/css/layui.css">
    <script type="text/javascript" src="<%=path%>/static/layui/layui.js"></script>
</head>
<body>
<div class="app">
    <h3></h3>
</div>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.3.2.js"></script>
<!--<script src="https://res.wx.qq.com/open/js/jweixin-1.3.0.js"></script>-->
<script>
    /* eslint-disable */
    var ua = window.navigator.userAgent.toLowerCase();
    if (ua.match(/MicroMessenger/i) == 'micromessenger') {   //判断是否是微信环境


        layui.use('table', function () {
            var table = layui.table;
            var $ = layui.jquery;
            $(function () {

                // alert("自动返回" + window.location.href)
                $("h3").text("路径：" + window.location.href);
                // alert("微信环境");
                wx.miniProgram.navigateTo({
                    url: "/pages/park/pay/payres/payres"
                });
                // var webhref = window.location.href;
                // // alert("param:"+param)
                // var param = webhref.split("charset=")[1]
                // // alert("param---:" + param)
                // var paramArr = param.split('&');
                // var orderNum = paramArr[1];
                // var money = paramArr[3].split("total_amount=")
                // orderNum = orderNum.split("out_trade_no=")[1]
                // alert("param---:" + paramArr[2] + "   订单号：" + orderNum
                //     +"\n paramArr[3]:"+paramArr[3])
                // wx.miniProgram.navigateTo({
                //     url: "../index/index"
                // });
            })
        });
    } else {
        // alert("非微信环境")
        alert("支付成功");
        window.close();
        // window.location.href = 'https://blog.csdn.net/qq_37235231/article/details/82053062';
    }


    // function getParamer() {
    //
    //     var url = window.location.href.split("?")[1]; /*获取url里"?"后面的值*/
    //     if (url) { /*判断是否是一个参数还是多个参数*/
    //         url = url.split("=");
    //         return url[1]; /*返回想要的参数值*/
    //     } else {
    //         return '';
    //     }
    // }

</script>
</body>
</html>