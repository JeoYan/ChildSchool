<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/18
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String layuiPath = request.getContextPath() + "/layuiadmin/layui/";
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
	创建时间：
	<div class="layui-inline">
		<input class="layui-input" type="date" name="id" id="startDate" autocomplete="off">
	</div>
	至
	<div class="layui-inline">
		<input class="layui-input" type="date" name="id" id="endDate" autocomplete="off">
	</div>
	班级名称
	<div class="layui-inline">
		<input class="layui-input" name="id" id="cName" autocomplete="off">
	</div>
	<button class="layui-btn" data-type="reload">查询</button>
</div>



<table id="demo" lay-filter="test"></table>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="addCourse">配置课程</a>
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
			, url: '/ChildSchool/BackAction/courseManagement.action' //数据接口
			, page: true //开启分页
			, cols: [[ //表头
				{field: 'cName', title: '班级名称', sort: true, fixed: 'left', align: 'center'}
				, {field: 'wName', title: '班主任', align: 'center'}
				, {field: 'classroom', title: '所在班级', sort: true, align: 'center'}
				, {field: 'courseAddDate', title: '创建时间', align: 'center'}
				, {fixed: 'right', title: '操作', toolbar: '#barDemo', align: 'center'}
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
				var cName = $('#cName');

				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					, where: {
						startDate: startDate.val(),
						endDate: endDate.val(),
						cName: cName.val()
					}
				}, 'data');
			}

		};


		$('.demoTable .layui-btn').on('click', function () {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});


		// //配置课程
		// table.on('tool(test)', function (obj) {
		// 	var data = obj.data;
		// 	 if (obj.event === 'addCourse') {
		//
		// 		layer.open({
		// 			type: 2
		// 			, title: '配置课程'
		// 			, offset: 'auto'
		// 			, content: '/ChildSchool/web/coursetable2.action'
		// 			, area: ['500px', '250px']
		// 			, btn: ['确定', '取消']
		// 			, shade: 0
		// 			, success: function (layero, index) {
		// 				var body = layer.getChildFrame('body', index);
		// 				body.find("#userName").val(data.userName);
		// 				if (data.userState == '禁用') {
		// 					body.find("#userState").removeAttr('checked');
		// 					body.find("#userState").next().removeClass('layui-form-onswitch');
		// 					body.find("#userState").next().find('em').html('OFF');
		// 				}
		// 				var userState = body.find("#userState").val(data.userState);
		// 			}
		// 			, yes: function (index, layero) {
		// 				var userName = $(layero).find('iframe')[0].contentWindow.userName.value;
		// 				var userState = $(layero).find('iframe')[0].contentWindow.userState.value;
		// 				var ob = {"userName": userName, "userState": userState, "userId": data.userId};
		// 				$.ajax({
		// 					type: "POST",//提交的方式
		// 					url: "/u151/BackAction/updateUser.action",
		// 					data: ob,//提交的数据
		// 					dataType: "json",//希望返回的数据类型
		// 					success: function (msg) {//成功的方法  msg为返回数据
		// 						if (msg.msg === "1") {
		// 							alert("修改成功");
		// 							reloadTable.reload();
		// 							layer.close(index); //关闭弹窗
		// 						} else if (msg.msg === "0") {
		// 							alert("修改失败");
		// 						}
		// 					},
		// 					error: function () {//错误的方法
		// 						alert("服务器正忙")
		// 					}
		// 				});
		//
		//
		// 			}
		// 		});
		//
		// 	}
		// });


		//
		//配置课程
		table.on('tool(test)', function (obj) {
			var data = obj.data;
			 if (obj.event === 'addCourse') {

				layer.open({
					type: 2
					, title: '配置课程'
					, offset: 'auto'
					, content: '/ChildSchool/web/fullcalendar.action'
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
