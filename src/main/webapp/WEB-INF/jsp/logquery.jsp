<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String layuiPath = request.getContextPath() + "/layuiadmin/layui/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>日志列表</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script src=<%=layuiPath + "layui.js"%>></script>
</head>
<body>
<div class="container" style="width: 82%;margin:0 auto">
	<div style="margin-top: 30px;">
<h1 style="text-align: center">
	日志查询
</h1>
	</div>
<div class="demoTable">
	操作时间
	<div class="layui-inline">
		<input class="layui-input" type="date" name="id" id="startDate" autocomplete="off">
	</div>
	至
	<div class="layui-inline">
		<input class="layui-input" type="date" name="id" id="endDate" autocomplete="off">
	</div>
	操作人
	<div class="layui-inline">
		<input class="layui-input" name="id" id="wName" autocomplete="off">
	</div>
	<button class="layui-btn" data-type="reload">查询</button>
	<button type="button" class="layui-btn"  onclick="logCountByMonth()">按月统计日志</button>
	<button type="button" class="layui-btn"  onclick="logCountByWid()">按人员统计日志</button>
<%--	<button class="layui-btn " data-type="add">增加用户</button>--%>
</div>
<table id="demo" lay-filter="test"></table>
</div>
<script>
	layui.use('table', function () {
		var table = layui.table;
		//数据表格
		table.render({
			elem: '#demo'
			, url: '/ChildSchool/BackAction/findLog.action' //数据接口
			, page: true //开启分页
			, cols: [[ //表头
				{field: 'lDate', title: '操作日期', sort: true, fixed: 'left'}
				,{field: 'lTime', title: '操作时间'}
				, {field: 'wName', title: '操作人'}
				, {field: 'lEvent', title: '操作事项', sort: true}

			]]
			, id: 'testReload'
			, limit: 5
			, limits: [5, 10, 20]
		});

		var $ = layui.$, active = {
			reload: function () {
				var startDate = $('#startDate');
				var endDate = $('#endDate');
				var wName = $('#wName');

				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					, where: {


						startDate: startDate.val(),
						endDate: endDate.val(),
						wName: wName.val()
					}
				}, 'data');
			}
		};

		$('.demoTable .layui-btn').on('click', function () {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});


	});
	function logCountByMonth() {
		layer.open({
			type: 2
			, title: '统计日志'
			, offset: 'auto'
			, content: '/ChildSchool/BackAction/logCountByMonthView.action'
			, area: ['800px', '600px']
			, btn: ['关闭']
			// , shade: 0

		});
	}
	function logCountByWid() {
		layer.open({
			type: 2
			, title: '统计日志'
			, offset: 'auto'
			, content: '/ChildSchool/BackAction/logCountByWidView.action'
			, area: ['800px', '600px']
			, btn: ['关闭',]
			// , shade: 0

		});
	}
</script>

</body>
</html>
