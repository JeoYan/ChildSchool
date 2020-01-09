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
	<title>查看班级安全教育</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script  src=<%=layuiPath + "layui.js"%>></script>
</head>
<body>
<div class="layui-fluid">
<div class="layui-card">
	<div>
		<h1 style="text-align: center">
			查看班级安全教育
		</h1>
	</div>
	<div>
		<h3 style="text-align: left">
			查询条件
		</h3>
	</div>
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



<table id="demo" lay-filter="test" ></table>
<%--<script type="text/html" id="titleTpl">--%>
<%--	{{d.LAY_TABLE_INDEX+1}}--%>
<%--</script>--%>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="classSafeStudy">查看班级安全教育</a>
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
			, url: '/ChildSchool/BackAction/teacherCourseQuery.action' //数据接口
			, page: true //开启分页
			, cols: [[ //表头
				{field: 'cid',title: '班级编号', sort: true, fixed: 'left',align: 'center'}
				,{field: 'cName', title: '班级名称', sort: true, fixed: 'left', align: 'center'}
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


		//查看宝宝安全教育
		table.on('tool(test)', function (obj) {
			var data = obj.data;
			 if (obj.event === 'classSafeStudy') {

				layer.open({
					type: 2
					, title: '查看宝宝安全教育'
					, offset: 'auto'
					, content: '/ChildSchool/BackAction/teacherBabySafe.action?cid='+data.cid
					, area: ['1000px', '700px']
					, btn: ['确定', '取消']
					, shade: 0
					, success: function (layero, index) {
						var body = layer.getChildFrame('body', index);
						body.find("#hiddenCid").val(data.cid);
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
