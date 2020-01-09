<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/18
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String layuiPath = request.getContextPath() + "/layuiadmin/layui/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>安全教育</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script  src=<%=layuiPath + "layui.js"%>></script>
</head>
<body>
<div class="layui-fluid">
<div class="layui-card">
	<div>
		<h1 style="text-align: center">
			查看宝宝安全教育
		</h1>
		<div>
			<h2 style="text-align: center">
				宝宝姓名：<label  id="bName"></label>
			</h2>
		</div>
	</div>

<table id="demo" lay-filter="test" ></table>
<script>

	var reloadTable = null;
	layui.use('table', function () {
		var table = layui.table
			, form = layui.form;
		var layer = layui.layer;

		//数据表格
			reloadTable = table.render({
			elem: '#demo'
			, url: '/ChildSchool/BackAction/querySafeStudy.action?pid='+"<%=request.getParameter("pid")%>"
			, page: true //开启分页
			, cols: [[ //表头
					{field: 'safeId',title: '视频编号', sort: true, fixed: 'left',align: 'center'}
					,{field: 'safeName', title: '视频名称', sort: true, fixed: 'left', align: 'center'}
					, {field: 'endDate', title: '结束时间', sort: true, align: 'center'}
					, {field: 'totalScore', title: '得分', align: 'center'}
					, {field: 'state', title: '完成情况', align: 'center'}
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
