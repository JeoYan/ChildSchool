<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/18
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String layuiPath = request.getContextPath() + "/WEB-INF/layuiadmin/layui/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>课程管理</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script src=<%=layuiPath + "layui.js"%>></script>
</head>
<body>
<div class="demoTable">
	注册时间
	<div class="layui-inline">
		<input class="layui-input" type="date" name="id" id="startDate" autocomplete="off">
	</div>
	至
	<div class="layui-inline">
		<input class="layui-input" type="date" name="id" id="endDate" autocomplete="off">
	</div>
	用户名
	<div class="layui-inline">
		<input class="layui-input" name="id" id="userName" autocomplete="off">
	</div>
	<button class="layui-btn" data-type="reload">搜索</button>
</div>


<div class="demoTable">
	<div style="padding-bottom: 10px;">
		<button class="layui-btn " data-type="add">增加用户</button>
	</div>
</div>
<table id="demo" lay-filter="test"></table>
<script type="text/html" id="switchTpl">
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>

	var reloadTable = null;
	layui.use('table', function () {
		var table = layui.table
			, form = layui.form;
		var layer = layui.layer;

		//数据表格
			reloadTable = table.render({
			elem: '#demo'
			, url: '/u151/BackAction/findUser.action' //数据接口
			, page: true //开启分页
			, cols: [[ //表头
				{field: 'userName', title: '用户名', sort: true, fixed: 'left', align: 'center'}
				, {field: 'userRegistrationTime', title: '注册时间', align: 'center'}
				, {field: 'userScore', title: '积分', sort: true, align: 'center'}
				, {field: 'userUploadNum', title: '上传文档数', align: 'center'}
				, {field: 'userDownloadNum', title: '下载文档数', align: 'center'}
				, {field: 'userState', title: '用户状态', sort: true, align: 'center'}
				, {field: 'userSex', title: '操作', templet: '#switchTpl', unresize: true, align: 'center'}
			]]
			, id: 'testReload'
			, limit: 5
			, limits: [5, 10, 20]

		});

		//查询
		var $ = layui.$, active = {
			reload: function () {
				var startDate = $('#startDate');
				var endDate = $('#endDate');
				var userName = $('#userName');

				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					, where: {
						startDate: startDate.val(),
						endDate: endDate.val(),
						userName: userName.val()
					}
				}, 'data');
			},

			//新增
			add: function () {
				layer.open({
					type: 2
					, title: '添加用户'
					, offset: 'auto'
					, content: '/u151/web/Back-UserAdd.action'
					, area: ['500px', '250px']
					, btn: ['确定', '取消']
					, shade: 0
					, yes: function (index, layero) {
						//表单取值
						var userName = $(layero).find('iframe')[0].contentWindow.userName.value;
						var userState = $(layero).find('iframe')[0].contentWindow.userState.value;
						var ob = {"userName": userName, "userState": userState};
						$.ajax({
							type: "POST",//提交的方式
							url: "/u151/BackAction/addUser.action",
							data: ob,//提交的数据
							dataType: "json",//希望返回的数据类型
							success: function (msg) {//成功的方法  msg为返回数据
								if (msg.msg === "1") {
									alert("添加成功");
									reloadTable.reload();
									layer.close(index); //关闭弹窗
								} else if (msg.msg === "0") {
									alert("添加失败");
								}
							},
							error: function () {//错误的方法
								alert("服务器正忙")
							}
						});


					}
				});
			}
		};


		$('.demoTable .layui-btn').on('click', function () {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});

		//删除
		table.on('tool(test)', function (obj) {
			var data = obj.data;
			if (obj.event === 'del') {
				layer.confirm('真的删除行么', function (index) {
					var ob = {"userId": data.userId};
					$.ajax({
						url: "/u151/BackAction/delUser.action",
						type: "POST",
						data: ob,
						dataType: "json",
						success: function (msg) {
							if (msg.msg === "1") {
								layer.msg("删除成功", {icon: 6});
								reloadTable.reload();
								layer.close(index);
							} else if (msg.msg === "0") {
								layer.msg("删除失败", {icon: 5});
							}

						},
						error: function () {
							layer.msg("服务器正忙", {icon: 5});
						}
					});
				});
			}
			//修改
			else if (obj.event === 'edit') {

				layer.open({
					type: 2
					, title: '修改用户'
					, offset: 'auto'
					, content: '/u151/web/Back-UserEdit.action'
					, area: ['500px', '250px']
					, btn: ['确定', '取消']
					, shade: 0
					, success: function (layero, index) {
						var body = layer.getChildFrame('body', index);
						body.find("#userName").val(data.userName);
						if (data.userState == '禁用') {
							body.find("#userState").removeAttr('checked');
							body.find("#userState").next().removeClass('layui-form-onswitch');
							body.find("#userState").next().find('em').html('OFF');
						}
						var userState = body.find("#userState").val(data.userState);
					}
					, yes: function (index, layero) {
						var userName = $(layero).find('iframe')[0].contentWindow.userName.value;
						var userState = $(layero).find('iframe')[0].contentWindow.userState.value;
						var ob = {"userName": userName, "userState": userState, "userId": data.userId};
						$.ajax({
							type: "POST",//提交的方式
							url: "/u151/BackAction/updateUser.action",
							data: ob,//提交的数据
							dataType: "json",//希望返回的数据类型
							success: function (msg) {//成功的方法  msg为返回数据
								if (msg.msg === "1") {
									alert("修改成功");
									reloadTable.reload();
									layer.close(index); //关闭弹窗
								} else if (msg.msg === "0") {
									alert("修改失败");
								}
							},
							error: function () {//错误的方法
								alert("服务器正忙")
							}
						});


					}
				});

			}
		});


	});


</script>

</body>
</html>
