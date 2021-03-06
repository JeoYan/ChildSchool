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
	<title>发布安全教育视频</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script  src=<%=layuiPath + "layui.js"%>></script>
</head>
<body>
<div class="layui-fluid">
<div class="layui-card">
	<div>
		<h1 style="text-align: center">
			安全教育配置
		</h1>
	</div>
	<div>
		<h3 style="text-align: left">
			查询条件
		</h3>
	</div>
<div class="demoTable">
<%--	<div style="padding-bottom: 10px;">--%>
	发布时间：
	<div class="layui-inline">
		<input class="layui-input" type="date" name="id" id="startDate" autocomplete="off">
	</div>
	至
	<div class="layui-inline">
		<input class="layui-input" type="date" name="id" id="endDate" autocomplete="off">
	</div>
	视频名称
	<div class="layui-inline">
		<input class="layui-input" name="id" id="safeName" autocomplete="off">
	</div>
	<button class="layui-btn" data-type="reload">查询</button>

	<button class="layui-btn " data-type="add">新增</button>
</div>


<table id="demo" lay-filter="test" ></table>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="addTest">配置试题</a>
	<a class="layui-btn layui-btn-xs" lay-event="edit">重新上传</a>
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
			, url: '/ChildSchool/BackAction/safeStudyManagement.action' //数据接口
			, page: true //开启分页
			, cols: [[ //表头
				{field: 'safeId',title: '视频编号', sort: true, fixed: 'left',align: 'center'}
				,{field: 'safeName', title: '视频名称', sort: true, fixed: 'left', align: 'center'}
				, {field: 'startDate', title: '开始时间', align: 'center'}
				, {field: 'endDate', title: '结束时间', sort: true, align: 'center'}
				, {field: 'safeDate', title: '发布时间', align: 'center'}
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
				var safeName = $('#safeName');

				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					, where: {
						startDate: startDate.val(),
						endDate: endDate.val(),
						safeName: safeName.val()
					}
				}, 'data');
			},
			//新增视频
			add: function () {
				layer.open({
					type: 2
					, title: '新增安全教育视频'
					, offset: 'auto'
					, content: '/ChildSchool/web/addsafestudyvideo.action'
					, area: ['600px', '450px']
					, btn: ['关闭']
					, shade: 0

				});
			}
		};

		$('.demoTable .layui-btn').on('click', function () {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});


		table.on('tool(test)', function (obj) {
			var data = obj.data;
			if (obj.event === 'addTest') { //配置试题

				layer.open({
					type: 2
					, title: '配置试题'
					, offset: 'auto'
					, content: '/ChildSchool/BackAction/addSafeStudyTestView.action?safeId='+data.safeId
					, area: ['950px', '700px']
					, btn: ['关闭']
					, shade: 0

				});


			}else if (obj.event === 'del') {//删除视频
				layer.confirm('真的删除么', function (index) {
					var ob = {"safeId": data.safeId,"safeName": data.safeName};
					$.ajax({
						url: '/ChildSchool/BackAction/delSafeStudyVideo.action',
						type: "POST",
						data: ob,
						dataType: "json",
						success: function (msg) {
							if (msg.msg === "ok") {
								layer.msg("删除成功", {icon: 6});
								reloadTable.reload();
								layer.close(index);
							} else if (msg.msg === "error") {
								layer.msg("删除失败", {icon: 5});
							}

						},
						error: function () {
							layer.msg("服务器正忙", {icon: 5});
						}
					});
				});
			}
			//重新上传视频
			else if (obj.event === 'edit') {

				 layer.open({
					 type: 2
					 , title: '重新上传'
					 , offset: 'auto'
					 , content: '/ChildSchool/web/updatesafestudyvideo.action'
					 , area: ['500px', '450px']
					 , btn: ['关闭']
					 , shade: 0
					 , success: function (layero, index) {
						 var body = layer.getChildFrame('body', index);
						 body.find("#safeName").val(data.safeName);
						 body.find("#rangeDate").val(data.startDate+" - "+data.endDate);
						 body.find("#safeId").val(data.safeId);
						 body.find("#oldSafeName").val(data.safeName);
					 }
					 // , yes: function (index, layero) {
						//  //表单取值
						//  var userName = $(layero).find('iframe')[0].contentWindow.userName.value;
						//  var userState = $(layero).find('iframe')[0].contentWindow.userState.value;
						//  var ob = {"userName": userName, "userState": userState};
						//  $.ajax({
						// 	 type: "POST",//提交的方式
						// 	 url: "/u151/BackAction/addUser.action",
						// 	 data: ob,//提交的数据
						// 	 dataType: "json",//希望返回的数据类型
						// 	 success: function (msg) {//成功的方法  msg为返回数据
						// 		 if (msg.msg === "1") {
						// 			 alert("添加成功");
						// 			 reloadTable.reload();
						// 			 layer.close(index); //关闭弹窗
						// 		 } else if (msg.msg === "0") {
						// 			 alert("添加失败");
						// 		 }
						// 	 },
						// 	 error: function () {//错误的方法
						// 		 alert("服务器正忙")
						// 	 }
						//  });
					 //
					 //
					 // }
				 });

			}
		});

	});


</script>
</div>
</div>
</body>
</html>
