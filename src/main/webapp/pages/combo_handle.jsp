<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>月缴办理</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all">
		<script src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
		<style>
			.all {
				background-color: rgba(255, 255, 255, 0.5);
				margin: 200px auto;
				width: 800px;
				height: 500px;
				text-align: center;
			}
		</style>
	</head>
	<body style="background-image: url('${pageContext.request.contextPath}/static/img/combobg.jpg')">
		<div class="all">
			<div class="layui-form-item" style="padding-top: 100px">
				<input id="carNum" type="text" placeholder="请输入车牌号" style="width: 120px;height: 35px" >
				<button id="search" style="width:80px;height: 37px" class="layui-btn layui-btn-normal layui-icon layui-icon-search">验证</button>
			</div>
			<div id="handle" class="layui-form-item" style="margin-top: 100px">
				<button style="width:210px;height: 40px" class="layui-btn layui-btn-normal">办理新月缴</button>
			</div>
			<div id="renew" class="layui-form-item" style="margin-top: 100px">
				<button style="width:210px;height: 40px" class="layui-btn layui-btn-normal">月缴续费</button>
			</div>
			<div id="carInfo" style="display: none">
				<form onsubmit="return false;" class="layui-form " lay-filter="formCar">
					<div class="layui-form-item">
						<label class="layui-form-label" style="margin-left: 120px;margin-top: 20px">车牌号：</label>
						<div class="layui-input-block"
						     style="float: left; margin-left: 20px;margin-top: 20px;width: 200px;">
							<input name="carNum" type="text" readonly autocomplete="off" class="layui-input carNum">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label" style="margin-left: 120px;">车型：</label>
						<div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
							<input name="carType" type="text" readonly autocomplete="off" class="layui-input carType">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label" style="margin-left: 120px;">颜色：</label>
						<div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
							<input name="carColor" type="text" readonly autocomplete="off" class="layui-input carColor">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label" style="margin-left: 120px;">品牌：</label>
						<div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
							<input name="carBrand" type="text" readonly autocomplete="off" class="layui-input carBrand">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label" style="margin-left: 120px;">套餐：</label>
						<div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
							<input name="comboName" type="text" readonly autocomplete="off"
							       class="layui-input comboName">
						</div>
					</div>
				</form>
			</div>
			<div id="newCombo" style="display: none">
				<div class="layui-form-item">
					<label class="layui-form-label" style="margin-left: 120px;margin-top: 20px">车牌号：</label>
					<div class="layui-input-block"
					     style="float: left; margin-left: 20px;margin-top: 20px;width: 200px;">
						<input id="comboCarNum" name="carNum" type="text" readonly autocomplete="off"
						       class="layui-input  ">
						<input type="hidden" name="type" value="card">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="margin-left: 120px;">套餐：</label>
					<div class="layui-input-block">
						<select id="comboId" name="comboid" lay-verify="required"
						        style="float: left; margin-left: 20px;width: 200px;height: 30px">
							<option value="" disabled selected>请选择套餐</option>
							<option value="1">月卡（300元/月）</option>
							<option value="2">季卡（800元/季）</option>
							<option value="3"> 半年卡（1500元/半年）</option>
							<option value="4">年卡（3000元/年）</option>
							<option value="5">至尊卡（5999元/3年）</option>
						</select>
					</div>
					<div id="sure" class="layui-form-item" style="margin-top: 60px">
						<button style="width:200px;height: 40px" class="layui-btn" >确定</button>
					</div>
				</div>
			</div>
			<div id="handleRenew" style="display: none">
				<div class="layui-form-item">
					<label class="layui-form-label" style="margin-left: 120px;margin-top: 20px">车牌号：</label>
					<div class="layui-input-block"
					     style="float: left; margin-left: 20px;margin-top: 20px;width: 200px;">
						<input id="renewCarNum" readonly name="carNum" type="text" autocomplete="off"
						       class="layui-input ">
						<input type="hidden" name="type" value="carRenew">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="margin-left: 120px;">套餐：</label>
					<div class="layui-input-block">
						<select id="addTime" lay-verify="required"
						        style="float: left; margin-left: 20px;width: 200px;height: 30px">
							<option value="" disabled selected>请选择月份</option>
							<option value="1">1个月</option>
							<option value="2">3个月</option>
							<option value="3">6个月</option>
							<option value="4">12个月</option>
							<option value="5">36个月</option>
						</select>
					</div>
					<div id="sureRenew" class="layui-form-item" style="margin-top: 60px">
						<button style="width:200px;height: 40px" class="layui-btn">确定</button>
					</div>

				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		layui.use(['jquery', 'layer', 'form'], function () {
			var $ = layui.jquery;
			var layer = layui.layer;
			var form = layui.form;
			$("#search").on("click", function () {
				//获取车辆信息
				$.post("${pageContext.request.contextPath}/CarController/verifyCar?carNum=" + $("#carNum").val(), function (msg) {
					var tbCar = msg;
					var vipId = tbCar.vipId;
					form.val("formCar", tbCar)
					if (vipId == 2) {
						//	是月缴用户才能办理月缴续费
						$('#renewCarNum').val(tbCar.carNum);
					} else {
						//月缴用户不能重复办理月缴产品，只能续费来变更套餐
						$('#comboCarNum').val(tbCar.carNum);
					}
					openCarInfo();
				});
			})

			//打开车辆信息页面
			function openCarInfo() {
				layer.open({
					type: 1,
					title: '车辆信息',
					content: $('#carInfo'),
					area: ['600px', '400px'],
					shade: [0.5, '#fff'], //0.5透明度的白色背景
					skin: 'layui-layer-molv',//墨绿皮肤
					// skin: 'layui-layer-lan',//深蓝
					offset: '50px',//上边距
					shift: 1 //动画类型
				})
			}

			//	办理新月缴
			$("#handle").on("click", function () {
				layer.open({
					type: 1,
					title: '办理新月缴',
					content: $('#newCombo'),
					area: ['600px', '300px'],
					shade: [0.5, '#fff'], //0.5透明度的白色背景
					skin: 'layui-layer-molv',//墨绿皮肤
					// skin: 'layui-layer-lan',//深蓝
					offset: '50px',//上边距
					shift: 1 //动画类型
				})
				$("#sure").on("click", function () {
					var comboId = $("#comboId").val();
					var carNum = $("#comboCarNum").val();
					if (carNum == null || carNum == "") {
						layer.alert("请先验证车牌号或该车牌号不能办理月缴", {icon: 7}, function (index) {
							layer.close(index);
						})
					} else if (comboId == null) {
						layer.alert("请选择套餐", {icon: 6}, function (index) {
							layer.close(index);
						})
					} else {
						window.open("${pageContext.request.contextPath}/alipay?type=card&carNum=" + carNum + "&comboid=" + comboId);
						window.location.reload();

					}
				})
			})
			//	办理续费
			$("#renew").on("click", function () {
				layer.open({
					type: 1,
					title: '办理续费',
					content: $('#handleRenew'),
					area: ['600px', '300px'],
					shade: [0.5, '#fff'], //0.5透明度的白色背景
					skin: 'layui-layer-molv',//墨绿皮肤
					// skin: 'layui-layer-lan',//深蓝
					offset: '50px',//上边距
					shift: 1 //动画类型
				})
				$("#sureRenew").on("click", function () {
					var comboId = $("#addTime").val();
					var carNum = $("#renewCarNum").val();
					if (carNum == null || carNum == "") {
						layer.alert("请先验证车牌号或该车牌号不能办理续费", {icon: 7}, function (index) {
							layer.close(index);
						})
					} else if (addTime == null) {
						layer.alert("请选择续费时长", {icon: 6}, function (index) {
							layer.close(index);
						})
					} else {
						window.open("${pageContext.request.contextPath}/alipay?type=cardRenew&carNum=" + carNum + "&comboid=" + comboId);
						window.location.reload();
					}
				})
			})

		});
	</script>
</html>
