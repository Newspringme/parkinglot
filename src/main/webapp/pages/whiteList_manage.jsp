<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>车辆白名单管理</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all">
		<script src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
		<style>
			.layui-table-cell .layui-form-checkbox[lay-skin=primary] {
				top: 10px;
				padding: 0;
			}
		</style>
	</head>
	<body>
		<!--        表格开始-->
		<!--        头部工具栏-->
		<div style="display: none" id="carToolbar">
			<button type="button" class="layui-btn layui-btn-xm layui-icon layui-icon-add-circle" lay-event="add">
				新增白名单
			</button>
		</div>
		<!--        行工具栏-->
		<div style="display: none" id="carBar">
			<button type="button" class="layui-btn layui-btn-sm  layui-btn-danger layui-icon layui-icon-delete"
			        lay-event="delete">删除
			</button>
		</div>
		<!--        表格主体-->
		<table class="layui-hide" id="carTable" lay-filter="carTable"></table>
		<!--        添加弹出层-->
		<div id="addCar" onsubmit="return false;" style="display: none">
			<form class="layui-form" id="addForm" action="" style="margin-top: 30px">
				<div class="layui-form-item">
					<label class="layui-form-label" style="margin-left: 120px;">车牌号：</label>
					<div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
						<input type="text" name="carNum" lay-verify="carNum"
						       autocomplete="off" class="layui-input carNum">
					</div>
				</div>
				<div class="layui-form-item" style="margin-top: 50px">
					<div class="layui-input-block" style="margin-left: 220px">
						<button type="submit" class="layui-btn layui-icon layui-icon-release" lay-submit
						        lay-filter="formAddCar">提交
						</button>
						<button type="reset" class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置</button>
					</div>
				</div>
			</form>
		</div>

	</body>
	<script type="text/javascript">
		layui.use(['jquery', 'layer', 'form', 'table'], function () {
			var $ = layui.jquery;
			var layer = layui.layer;
			var form = layui.form;
			var table = layui.table;
			// 渲染数据表格
			table.render({
				id: 'carTable'
				, elem: '#carTable'
				, url: '${pageContext.request.contextPath}/CarController/queryWhiteList'
				, title: '白名单数据表'
				, limit: 5//每页多少条数据
				, limits: [5,10,20,40,80,100]
				,page:true
				, toolbar: '#carToolbar'
				, defaultToolbar: ['filter', 'print']
				, skin: 'line' //行边框风格
				, skin: 'row' //列边框风格
				, even: true //开启隔行背景
				, size: 'lg' //大尺寸的表格，sm为小尺寸
				, text: {
					none: '数据被小小苏吃了' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
				}
				// , height: 'full-280'//总高-空白280//火狐不兼容，使用会导致数据错误
				, cols: [[
					  {type: 'numbers', align: 'center', width: 100, fixed: 'left'}
					, {field: 'carNum', title: '车牌号', align: 'center', width: 120}
					, {field: 'carBrand', title: '品牌', align: 'center', width: 150}
					, {field: 'carType', title: '车型', align: 'center', width: 120}
					, {field: 'carColor', title: '颜色', align: 'center', width: 120}
					, {field: 'userName', title: '车主', align: 'center', width: 100}
					, {field: 'userTel', title: '车主手机', align: 'center', width: 150}
					, {fixed: 'right', title: '操作', align: 'center', width: 200, toolbar: '#carBar'}
				]]
			});

			//头部工具栏监听事件
			table.on('toolbar(carTable)', function (obj) {
				switch (obj.event) {
					case 'add':
						openAddCar();
						break;
				}
				;
			});

			//打开添加页面
			function openAddCar() {
				layer.open({
					type: 1,
					title: '添加白名单',
					content: $('#addCar'),
					area: ['600px', '240px'],
					shade: [0.5, '#fff'], //0.5透明度的白色背景
					skin: 'layui-layer-molv',//墨绿皮肤
					offset: '50px',//上边距
					shift: 1 //动画类型
				})
				//提交添加表单
				form.on('submit(formAddCar)', function (data) {
					// var msg = JSON.stringify(data.field);
					var carNum=$('.carNum').val();
					$.post("${pageContext.request.contextPath}/CarController/addWhiteList?carNum=" + encodeURI(carNum), function (msg) {
						msg = msg + '';
						if (msg == 'true') {
							layer.alert('白名单添加成功', {icon: 6}, function (index) {
								window.location.reload();
							});
						} else if(msg=='false'){
							layer.msg("白名单添加失败", {icon: 2});
						} else if(msg=='noCar'){
							layer.msg("该车辆未绑定，请先绑定", {icon: 3});
						}else if(msg=='had'){
							layer.msg('该车辆已经存入白名单',{icon:6});
						}
					})
				})
				return false;//阻止页面刷新
			}

			//监听行工具条事件
			table.on('tool(carTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
				var data = obj.data; //获得当前行数据
				var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
				if (layEvent == 'delete') { //删除
					$.post("${pageContext.request.contextPath}/CarController/deleteWhiteList?carNum=" + encodeURI(data.carNum), function (msg) {
							msg = msg + '';
						if (msg == 'true') {
							layer.alert('白名单删除成功', {icon: 6}, function (index) {
								window.location.reload();
							});
						} else {
							layer.msg("白名单删除失败", {icon: 2});
						}
					})

				}

			});
			//	自定义验证规则
			// form.verify({
			// 	carName: function(value){
			// 		if(value.length < 2){
			// 			return '管理员名称至少得2个字符啊';
			// 		}
			// 	}
			// 	,carPass: [
			// 		/^[\S]{6,12}$/
			// 		,'密码必须6到12位，且不能出现空格'
			// 	]
			// });
		});
	</script>
</html>
