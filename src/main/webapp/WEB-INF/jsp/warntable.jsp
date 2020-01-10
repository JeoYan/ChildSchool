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
	<title>报警信息</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script  src=<%=layuiPath + "layui.js"%>></script>
</head>
<body>
<div class="layui-fluid">
<div class="layui-card">
	<div>
		<h1 style="text-align: center">
			报警日志
		</h1>
	</div>
<div class="demoTable">
	报警时间：
	<div class="layui-inline">
		<input class="layui-input" type="date" name="id" id="startDate" autocomplete="off">
	</div>
	至
	<div class="layui-inline">
		<input class="layui-input" type="date" name="id" id="endDate" autocomplete="off">
	</div>
	报警区域
	<div class="layui-inline">
		<input class="layui-input" name="id" id="area" autocomplete="off">
	</div>
	<button class="layui-btn" data-type="reload">查询</button>
</div>



<table id="demo" lay-filter="test" ></table>
<%--<script type="text/html" id="titleTpl">--%>
<%--	{{d.LAY_TABLE_INDEX+1}}--%>
<%--</script>--%>
<script>

	var reloadTable = null;
	layui.use('table', function () {
		var table = layui.table
			, form = layui.form;
		var layer = layui.layer;

		//数据表格
			reloadTable = table.render({
			elem: '#demo'
			, url: '/ChildSchool/BackAction/findWarning.action' //数据接口
			, page: true //开启分页
			, cols: [[ //表头
				{field: 'warnId',title: '日志编号', sort: true, fixed: 'left',align: 'center'}
				,{field: 'warnName', title: '日志名称', sort: true, fixed: 'left', align: 'center'}
				, {field: 'bName', title: '宝贝姓名', align: 'center'}
				, {field: 'area', title: '报警区域', sort: true, align: 'center'}
				, {field: 'warnTime', title: '报警时间', align: 'center'}
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
				var area = $('#area');

				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					, where: {
						startDate: startDate.val(),
						endDate: endDate.val(),
						area: area.val()
					}
				}, 'data');
			}

		};


		$('.demoTable .layui-btn').on('click', function () {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});

	});


</script>
</div>
</div>
</body>
</html>
