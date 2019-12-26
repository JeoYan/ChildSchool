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
	<title>安全教育</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script  src=<%=layuiPath + "layui.js"%>></script>
</head>
<body>
<div class="layui-fluid">
<div class="layui-card">
	<div>
		<h1 style="text-align: center">
			安全教育
		</h1>
	</div>




<table id="demo" lay-filter="test" ></table>
<%--<script type="text/html" id="titleTpl">--%>
<%--	{{d.LAY_TABLE_INDEX+1}}--%>
<%--</script>--%>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="addCourse">播放视频</a>
	<a class="layui-btn layui-btn-xs" lay-event="addCourse">安全试题</a>
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
				{field: 'cid',title: '视频编号', sort: true, fixed: 'left',align: 'center'}
				,{field: 'cName', title: '视频名称', sort: true, fixed: 'left', align: 'center'}
				, {field: 'wName', title: '发布时间', align: 'center'}
				, {field: 'classroom', title: '得分', sort: true, align: 'center'}
				, {field: 'courseAddDate', title: '完成情况', align: 'center'}
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


		//配置课程
		table.on('tool(test)', function (obj) {
			var data = obj.data;
			 if (obj.event === 'addCourse') {

				layer.open({
					type: 2
					, title: '配置课程'
					, offset: 'auto'
					, content: '/ChildSchool/BackAction/courseTable.action?cid='+data.cid
					, area: ['800px', '600px']
					, btn: ['确定', '取消']
					, shade: 0
					, success: function (layero, index) {
						var body = layer.getChildFrame('body', index);
						body.find("#className").html(data.cName);
					}
				});


			}
		});





	});


</script>
</div>
</div>
</body>
</html>
