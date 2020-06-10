<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/11
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>欢迎页</title>

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/imagedrawer.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/imagedrawer.js"></script>
		<style type="text/css">
			html, body, #main {
				margin: 0px;
				padding: 0px;
				overflow: hidden;
				width: 100%;
				height: 100%;
				min-width: 100%;
				max-width: 100%;
				min-height: 100%;
				max-height: 100%;
			}

			#container {
				width: 100%;
				height: 100%;
				margin: auto;
				/*margin-top: 150px;*/
			}

			#container img {
				width: 100%;
				height: 100%;
			}
		</style>

	</head>
	<body>

		<div class="htmleaf-container">

			<div id="main">
				<div id="container">
					<img id="bob"  src="${pageContext.request.contextPath}/static/img/car1.jpg">
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(document).ready(function () {
				$('div#container').drawImage();
			});
		</script>
	</body>
</html>
