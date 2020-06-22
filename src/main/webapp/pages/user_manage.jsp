<%--
  Created by IntelliJ IDEA.
  User: Useristrator
  Date: 2020/6/20
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>用户列表</title>
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
		<input type="hidden" class="path" value="${pageContext.request.contextPath}">
		<!--搜索条件开始-->
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
			<legend>搜索条件</legend>
		</fieldset>
		<form onsubmit="return false;" class="layui-form" method="post">
			<div class="layui-inline">
				<label class="layui-form-label">用户昵称:</label>
				<div class="layui-input-block">
					<input type="text" name="userName" autocomplete="off" placeholder="请输入用户昵称" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">添加时间：</label>
				<div class="layui-input-block">
					<input type="text" name="startTime" id="startTime" readonly autocomplete="off" placeholder="请选择开始时间"
					       class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">至：</label>
				<div class="layui-input-block">
					<input type="text" name="endTime" id="endTime" readonly autocomplete="off" placeholder="请选择截止时间"
					       class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<div class="layui-input-block">
					<button type="submit" lay-submit lay-filter="formSearch"
					        class="layui-btn layui-btn-normal  layui-icon layui-icon-search">搜索
					</button>
					<button type="reset" class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置</button>
				</div>
			</div>
		</form>
		<!--        表格开始-->
		<!--        头部工具栏-->
		<div style="display: none" id="userToolbar">
			<button type="button" class="layui-btn layui-btn-xm layui-icon layui-icon-add-circle" lay-event="add">
				新增用户
			</button>
			<button type="button" class="layui-btn layui-btn-xm layui-btn-danger layui-icon layui-icon-delete"
			        lay-event="batchDelete">删除用户
			</button>
		</div>
		<!--        表格主体-->
		<table class="layui-hide" id="userTable" lay-filter="userTable"></table>
		<!--        添加弹出层-->
		<div id="addUser" onsubmit="return false;" style="display: none">
			<form class="layui-form" id="addForm" action="" style="margin-top: 30px">
				<div class="layui-form-item">
					<label class="layui-form-label" style="margin-left: 120px;">用户昵称：</label>
					<div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
						<input type="text" name="userName" lay-verify="userName"
						       autocomplete="off" class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label" style="margin-left: 120px;">密&emsp;&emsp;码：</label>
					<div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
						<input type="password" name="userPass" lay-verify="userPass"
						       autocomplete="off" class="layui-input userPass">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="margin-left: 120px;">确认密码：</label>
					<div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
						<input type="password" name="surePass" lay-verify="userPass"
						       autocomplete="off" class="layui-input surePass">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="margin-left: 120px;">性&emsp;&emsp;别：</label>
					<div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
						<input type="radio" checked name="userSex" value="男" title="男" autocomplete="off"
						       class="layui-input">
						<input type="radio" name="userSex" value="女" title="女" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="margin-left: 120px;">年&emsp;&emsp;龄：</label>
					<div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
						<input type="number" name="userAge  " autocomplete="off" class="layui-input ">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="margin-left: 120px;">手机号码：</label>
					<div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
						<input type="tel" name="userTel" autocomplete="off" class="layui-input ">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="margin-left: 120px;">身份证号码：</label>
					<div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
						<input type="number" name="userCard" autocomplete="off" class="layui-input ">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block" style="margin-left: 220px;">
						<button type="submit" class="layui-btn layui-icon layui-icon-release" lay-submit
						        lay-filter="formAddUser">提交
						</button>
						<button type="reset" class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置</button>
					</div>
				</div>
			</form>
		</div>

		<!--        行工具栏-->
		<div style="display: none" id="userBar">

		</div>

		<script type="text/javascript">
			layui.use(['jquery', 'layer', 'form', 'table', 'laydate'], function () {
				var $ = layui.jquery;
				var layer = layui.layer;
				var form = layui.form;
				var table = layui.table;
				var laydate = layui.laydate;
				//给开始时间和结束时间绑定时间选择器
				laydate.render({
					elem: '#startTime'
				});
				laydate.render({
					elem: '#endTime'
				})
				// 渲染数据表格
				table.render({
					id: 'userTable'
					, elem: '#userTable'
					, url: '${pageContext.request.contextPath}/UserController/queryTbUser'
					, title: '用户数据表'
					, limit: 5//每页多少条数据
					, limits: [5, 10, 20, 40, 80, 100]
					, page: true
					, toolbar: '#userToolbar'
					, defaultToolbar: ['filter', 'print']
					, initSort: {
						field: 'regTime' //排序字段，对应 cols 设定的各字段名
						, type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
					}
					, skin: 'line' //行边框风格
					, skin: 'row' //列边框风格
					, even: true //开启隔行背景
					, size: 'lg' //大尺寸的表格，sm为小尺寸
					, text: {
						none: '数据被小小苏吃了' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
					}
					, cols: [[
						{type: 'numbers', align: 'center', width: 100, fixed: 'left'}
						, {field: 'checkbox', type: 'checkbox'}
						, {field: 'userId', hide: true, title: 'ID', align: 'center', width: 50, sort: true}
						, {field: 'userName', title: '用户名', align: 'center', width: 120, templet: '#nameTpl'}
						, {field: 'userSex', title: '性别', align: 'center', width: 80, templet: '#sexTpl', hide: true}
						, {field: 'userAge', title: '年龄', align: 'center', width: 100}
						, {field: 'userTel', title: '手机号码', align: 'center', width: 100, hide: true}
						, {field: 'userCard', title: '身份证号码', align: 'center', width: 150, hide: true}
						, {field: 'regTime', title: '注册时间', align: 'center', width: 200}
					]]
				});

				//头部工具栏监听事件
				table.on('toolbar(userTable)', function (obj) {
					var nowId = '${tbUser.userId}';
					var checkStatus = table.checkStatus('userTable');
					data = checkStatus.data;
					delList = [];
					data.forEach(function (n, i) {
						if (n.userId == nowId) {
							alert("当前登录的账号不能删除的哦(*￣︶￣)");
						} else {
							delList.push(n.userId);
						}
					});

					switch (obj.event) {
						case 'add':
							openAddUser();
							break;


						case 'batchDelete':
							if (delList.length > 0) {
								layer.confirm('确定删除所选择的用户？', {icon: 5}, function (index) {
									layer.close(index);
									// 向服务端发送删除指令
									$.post("${pageContext.request.contextPath}/UserController/deleteTbUser?delList=" + delList, null, function (msg) {
										msg = msg + '';
										if (msg == 'true') {
											layer.alert('删除成功', {icon: 6}, function (index) {
												window.location.reload();
											});
										} else {
											layer.alert('删除失败', {icon: 2});
										}
									})
								});
							} else {
								layer.alert("至少选一个删除吧", {icon: 4}, function (index) {
									layer.close(index);
								})
							}
							break;
					}
					;
				});

				//打开添加页面
				function openAddUser() {
					layer.open({
						type: 1,
						title: '添加用户',
						content: $('#addUser'),
						area: ['600px', '600px'],
						shade: [0.5, '#fff'], //0.5透明度的白色背景
						skin: 'layui-layer-molv',//墨绿 皮肤
						offset: '50px',//上边距
						shift: 1 //动画类型
					})
					//提交添加表单
					form.on('submit(formAddUser)', function (data) {
						if ($(".userPass").val() != $(".surePass").val()) {
							layer.alert("确认密码有误", {icon: 2}, function (index) {
								layer.close(index);
							});
						} else {
							var msg = JSON.stringify(data.field);
							$.post("${pageContext.request.contextPath}/UserController/addUser?msg=" + encodeURI(msg), function (msg) {
								msg = msg + '';
								if (msg == 'true') {
									layer.alert('用户添加成功', {icon: 6}, function (index) {
										window.location.reload();
									});
								} else {
									layer.alert("用户添加失败", {icon: 2});
								}
							})
						}
						return false;//阻止页面刷新

					});
				}
				//提交条件查询表单
				form.on('submit(formSearch)', function (data) {
					var msg = JSON.stringify(data.field);
					//表格重载之后就会根据条件参数进行分页
					table.reload('userTable', {
						page: {
							curr: 1//从第一页开始
						},
						where: {
							msg: msg//条件

						},
						method: 'post',
						url: '${pageContext.request.contextPath}/UserController/queryTbUser'

					})
				})

				//	自定义验证规则
				form.verify({
					userName: function (value) {
						if (value.length < 2) {
							return '管理员名称至少得2个字符啊';
						}
					}
					, userPass: [
						/^[\S]{6,12}$/
						, '密码必须6到12位，且不能出现空格'
					]
				});
			});


		</script>

		<%--根据性别更改样式--%>
		<script type="text/html" id="sexTpl">
			{{#  if(d.userSex === '女'){ }}
			<span style="color: #F581B1;">{{ d.userSex }}</span>
			{{#  } else { }}
			{{ d.userSex }}
			{{#  } }}
		</script>
		<!-- 姓名-->
		<script type="text/html" id="nameTpl">
			<a href="/?table-demo-id={{d.id}}" class="layui-table-link" target="_blank">{{ d.userName }}</a>
		</script>

	</body>
</html>
