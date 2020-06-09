<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/6
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>管理员列表</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css"  media="all">
<%--		<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js" charset="utf-8"></script>--%>
<%--		<script src="${pageContext.request.contextPath}/js/packaging.js" charset="utf-8"></script>--%>
		<script src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/cropper/cropper.css">
		<script src="${pageContext.request.contextPath}/static/cropper/cropper.js"></script>
		<script src="${pageContext.request.contextPath}/static/cropper/croppers.js"></script>
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
		<form  action="" onsubmit="return false;" class="layui-form" method="post" >
			<div class="layui-inline">
				<label class="layui-form-label">管理员账号:</label>
				<div class="layui-input-block">
					<input type="text" name="adminName" autocomplete="off" placeholder="请输入管理员账号" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">添加时间：</label>
				<div class="layui-input-block">
					<input type="text" name="startTime" id="startTime" readonly autocomplete="off" placeholder="请选择开始时间" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">至：</label>
				<div class="layui-input-block">
					<input type="text" name="endTime" id="endTime" readonly autocomplete="off" placeholder="请选择截止时间" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<div class="layui-input-block">
					<button type="submit"  lay-submit lay-filter="formSearch" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" >搜索</button>
					<button type="reset" class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置</button>
				</div>
			</div>
		</form>
		<!--        表格开始-->
		<!--        头部工具栏-->
		<div style="display: none" id="adminToolbar">
			<button type="button" class="layui-btn layui-btn-xm layui-icon layui-icon-add-circle" lay-event="add">新增管理员</button>
			<button type="button" class="layui-btn layui-btn-xm layui-btn-danger layui-icon layui-icon-delete" lay-event="batchDelete">批量删除</button>
		</div>
		<!--        行工具栏-->
		<div style="display: none" id="adminBar">
			<button type="button" class="layui-btn layui-btn-sm layui-icon layui-icon-edit" lay-event="edit">编辑</button>
			<button type="button"  id="uploadImg" class="layui-btn layui-btn-sm layui-icon layui-icon-upload-circle" lay-event="uploadImg">上传头像</button>
			<button type="button" class="layui-btn layui-btn-sm layui-icon layui-icon-download-circle" lay-event="queryImg">查看头像</button>
<%--			<button type="button" class="layui-btn layui-btn-sm  layui-btn-danger layui-icon layui-icon-delete" lay-event="delete">删除</button>--%>
		</div>
		<!--        表格主体-->
		<table class="layui-hide" id="adminTable" lay-filter="adminTable"></table>
		<!--        表格结束-->
		<!--        添加弹出层开始-->
		<div id="addAdmin" onsubmit="return false;" style="display: none">
			<form class="layui-form" id="addForm" action="" style="margin-top: 30px" >
				<div class="layui-form-item">
					<label class="layui-form-label" style="margin-left: 120px;">账号名称：</label>
					<div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
						<input  type="text" name="adminName" lay-verify="adminName"
						        autocomplete="off" class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label"style="margin-left: 120px;">密&emsp;&emsp;码：</label>
					<div class="layui-input-block"style="float: left; margin-left: 20px;width: 200px;">
						<input type="password" name="adminPass" lay-verify="adminPass"
						       autocomplete="off" class="layui-input adminPass">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"style="margin-left: 120px;">确认密码：</label>
					<div class="layui-input-block"style="float: left; margin-left: 20px;width: 200px;">
						<input type="password" name="surePass"  lay-verify="adminPass"
						       autocomplete="off" class="layui-input surePass">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label"style="margin-left: 120px;">所属角色：</label>
					<div class="layui-input-block"style="float: left; margin-left: 20px;width: 200px;">
						<select name="roleId" lay-verify="required" lay-reqtext="管理员岂能没有角色？">
							<option value="" >请选择角色</option>
							<option value="1">普通管理员</option>
							<option value="2">高级管理员</option>
						</select>
					</div>
				</div>

				<div class="layui-form-item">
					<div class="layui-input-block" style="margin-left: 220px;">
						<button type="submit" class="layui-btn layui-icon layui-icon-release" lay-submit lay-filter="formAddAdmin">提交</button>
						<button type="reset" class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置</button>
					</div>
				</div>
			</form>
		</div>
		<!--        编辑弹出层开始-->
		<div id="updateAdmin" onsubmit="return false;" style="display: none">
			<form class="layui-form" lay-filter="formUpdateAdmin" id="updateForm" action="" style="margin-top: 30px" >
				<div class="layui-form-item">
					<label class="layui-form-label" style="margin-left: 120px;">账号名称：</label>
					<div class="layui-input-block" style="float: left; margin-left: 20px;width: 200px;">
						<input  type="text" name="adminName" lay-verify="required" lay-reqtext="管理员账号岂能为空？"
						        autocomplete="off" class="layui-input adminName">
					</div>
				</div>

						<input type="hidden" name="adminId"
						       autocomplete="off" class="layui-input layui-form-item adminId">


				<div class="layui-form-item">
					<label class="layui-form-label"style="margin-left: 120px;">所属角色：</label>
					<div class="layui-input-block"style="float: left; margin-left: 20px;width: 200px;">
						<select id="roleId" name="roleId" required lay-verify="required" lay-reqtext="管理员岂能没有角色？">
							<option value="" >请选择角色</option>
							<option value="1">初级管理员</option>
							<option value="2">高级管理员</option>
						</select>
					</div>
				</div>

				<div class="layui-form-item">
					<div class="layui-input-block" style="margin-left: 220px;">
						<button type="submit" class="layui-btn layui-icon layui-icon-release" lay-submit lay-filter="formUpdateAdmin">提交</button>
						<button type="reset" class="layui-btn layui-btn-warm layui-icon layui-icon-refresh">重置</button>
					</div>
				</div>
				</form>
		</div>
				<script type="text/javascript">
					layui.use(['jquery','layer','form','table','laydate','croppers'],function () {
						var $ = layui.jquery;
						var layer = layui.layer;
						var form = layui.form;
						var table = layui.table;
						var laydate=layui.laydate;
						var croppers=layui.croppers;
						//给开始时间和结束时间绑定时间选择器
						laydate.render({
							elem:'#startTime'
						});
						laydate.render({
							elem:'#endTime'
						})
						// 渲染数据表格
						table.render({
							id: 'aTable'
							,elem: '#adminTable'
							, url: '${pageContext.request.contextPath}/AdminController/queryAdmin'
							, title: '管理员数据表'
							, limit: 5//每页多少条数据
							, limits: [5,10,20,40,80,100]
							,page:true
							,toolbar:'#adminToolbar'
							,defaultToolbar:['filter','print']
							, totalRow:true//开启合计行
							,initSort: {
								field: 'regTime' //排序字段，对应 cols 设定的各字段名
								,type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
							}
							,skin: 'line' //行边框风格
							,skin: 'row' //列边框风格
							,even: true //开启隔行背景
							,size: 'lg' //大尺寸的表格，sm为小尺寸
							, text: {
								none: '数据被小小苏吃了' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
							}
							, height: 'full-280'//总高-空白280
							, cols: [[
								{type: 'numbers',align:'center',width:100, fixed: 'left',totalRowText:'合计：'}
								,{field:'checkbox' ,type: 'checkbox'}
								,{field:'adminId',hide:true, title:'ID',align:'center',width:100, sort: true}
								,{field:'adminName', title:'管理员账号',align:'center', width:120,templet:'#nameTpl'}
								,{field:'adminSex', title:'性别',align:'center', width:80,templet: '#sexTpl'}
								,{field:'adminTel', title:'手机号码',align:'center', width:120}
								,{field:'adminState', title:'状态',align:'center', width:120}
								,{field:'roleName', title:'角色',align:'center', width:120}
								,{field:'roleId', title:'角色id',hide:true,width:100}
								,{field:'regTime', title:'添加时间',align:'center', width:200}
								,{fixed: 'right', title:'操作',align:'center',width:220, toolbar: '#adminBar'}
							]]
						})

						//头部工具栏监听事件
						table.on('toolbar(adminTable)', function(obj){
							var nowId='${admin.adminId}';
							var checkStatus = table.checkStatus('aTable');
							data=checkStatus.data;
							delList=[];
							data.forEach(function(n,i){
								if (n.adminId==nowId){
									alert("当前登录的账号不能删除的哦(*￣︶￣)");
								}else {
									delList.push(n.adminId);
								}
							});
							switch(obj.event){
								case 'add':
									openAddAdmin();
									break;
								case 'batchDelete':
									if (delList.length > 0) {
										layer.confirm('真的要删除这么多吗？',{icon:5},function (index) {
											layer.close(index);
											// 向服务端发送删除指令
											$.post("${pageContext.request.contextPath}/deleteAdmin?delList="+delList,null,function (msg) {
												if (msg == 'true') {
													layer.alert('删除成功',{icon:6},function (index) {
														window.location.reload();
													});
												}else {
													layer.alert('删除失败',{icon:2});
												}
											})
										});
									}else {
										layer.alert("至少选一个删除吧",{icon:4},function (index) {
											layer.close(index);
										})
									}
									break;
							};
						});
						//打开添加页面
						function openAddAdmin(){
							layer.open({
								type: 1,
								title: '添加管理员',
								content: $('#addAdmin'),
								area: ['600px','400px'],
								shade: [0.5,'#fff'], //0.5透明度的白色背景
								// shadeClose: true, //开启遮罩关闭
								skin: 'layui-layer-molv',//墨绿皮肤
								offset: '50px',//上边距
								shift: 1 //动画类型
							})
							//提交添加表单
							form.on('submit(formAddAdmin)', function(data){
								if ($(".adminPass").val() != $(".surePass").val()) {
									layer.alert("确认密码有误",{icon:2},function (index) {
										layer.close(index);
									});
								}else {
									var msg=JSON.stringify(data.field);
									$.post("${pageContext.request.contextPath}/AdminController/addAdmin?msg="+encodeURI(msg),function (msg) {
										if (msg=='true'){
											layer.alert('管理员添加成功',{icon:6},function (index) {
												window.location.reload();
											});
										} else {
											layer.alert("管理员添加失败",{icon:2});
										}
									})
								}
								return false;//阻止页面刷新

							});
						}
						//打开编辑页面
						function openUpdateAdmin(obj){
							layer.open({
								type: 1,
								title: '更新管理员',
								content: $('#updateAdmin'),
								area: ['600px','300px'],
								shade: [0.5,'#fff'], //0.5透明度的白色背景
								// shadeClose: true, //开启遮罩关闭
								skin: 'layui-layer-molv',//墨绿皮肤
								offset: '50px',//上边距
								shift: 2, //动画类型
								success:function (index) {
									form.val("formUpdateAdmin",obj.data);
								}
							})
							//提交修改表单
							form.on('submit(formUpdateAdmin)', function(data){
								var msg=JSON.stringify(data.field);
								alert(msg);
								$.post("${pageContext.request.contextPath}/AdminController/updateAdmin?msg="+encodeURI(msg),function (msg) {
									msg=msg+"";
									if (msg=='true'){
										layer.alert('修改成功',{icon:6},function (index) {
											window.location.reload();
										});
									} else {
										layer.alert("修改失败",{icon:2});
									}
								})
								return false;//阻止页面刷新
							});
						}
						//提交条件查询表单
						form.on('submit(formSearch)', function(data){
							var msg=JSON.stringify(data.field);
							//表格重载之后就会根据条件参数进行分页
							table.reload('aTable',{
								page:{
									curr:1//从第一页开始
								},
								where:{
									msg:msg//条件

								},
								method:'post',
								url:'${pageContext.request.contextPath}/AdminController/queryAdmin'

							})
						})
						//监听行工具条事件
						table.on('tool(adminTable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
							var data = obj.data; //获得当前行数据
							var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
							var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
							var loginId='${admin.adminId}';
							if(layEvent == 'uploadImg'){ //上传头像
								layui.config({
									base: '${pageContext.request.contextPath}/static/cropper/' //layui自定义layui组件目录
								}).use(['form','croppers'], function () {
									var $ = layui.jquery
										,form = layui.form
										,croppers = layui.croppers
										,layer= layui.layer;

									// 创建一个头像上传组件
									croppers.render({
										elem: '#uploadImg'
										,saveW:150     //保存宽度
										,saveH:150
										,mark:1/1    //选取比例
										,area:'900px'  //弹窗宽度
										,url: "upload/uploadImg"  //图片上传接口返回和（layui 的upload 模块）返回的JOSN一样
										,done: function(url){ //上传完毕回调
											$("#inputimgurl").val(url);
											$("#srcimgurl").attr('src',url);
										}
									});

								});

							} else if(layEvent === 'edit'){ //编辑
								openUpdateAdmin(obj);
							}

						});
						//	自定义验证规则
						form.verify({
							adminName: function(value){
								if(value.length < 2){
									return '管理员名称至少得2个字符啊';
								}
							}
							,adminPass: [
								/^[\S]{6,12}$/
								,'密码必须6到12位，且不能出现空格'
							]
						});
					});
				</script>
		<%--根据性别更改样式--%>
		<script type="text/html" id="sexTpl">
			{{#  if(d.adminSex === '女'){ }}
			<span style="color: #F581B1;">{{ d.adminSex }}</span>
			{{#  } else { }}
			{{ d.adminSex }}
			{{#  } }}
		</script>
		<!-- 姓名-->
		<script type="text/html" id="nameTpl">
			<a href="/?table-demo-id={{d.id}}" class="layui-table-link" target="_blank">{{ d.adminName }}</a>
		</script>
	</body>
</html>
