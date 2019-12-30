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
			查看宝宝安全教育
		</h1>
		<div>
			<h2 style="text-align: center">
				班级名称：<label  id="className"></label>
			</h2>
		</div>
	</div>
	<div>
		<h3 style="text-align: left">
			查询条件
		</h3>
	</div>
<div class="demoTable">
	宝宝姓名
	<div class="layui-inline">
		<input class="layui-input" name="id" id="bName" autocomplete="off">
		<input type="hidden" id="hiddenCid">
	</div>
	<button class="layui-btn" data-type="reload">查询</button>
</div>



<table id="demo" lay-filter="test" ></table>
<%--<script type="text/html" id="titleTpl">--%>
<%--	{{d.LAY_TABLE_INDEX+1}}--%>
<%--</script>--%>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="babySafeStudy">查看宝宝安全教育</a>
</script>
<script>

	var reloadTable = null;
	layui.use('table', function () {
		var table = layui.table
			, form = layui.form;
		var layer = layui.layer;
		var $ = layui.$;
		//数据表格
			reloadTable = table.render({
			elem: '#demo'
			, url:  '/ChildSchool/BackAction/classSafeStudy.action?cid='+"<%=request.getParameter("cid")%>"
			, page: true //开启分页
			, cols: [[ //表头
				{field: 'bid',title: '宝宝编号', sort: true, fixed: 'left',align: 'center'}
				,{field: 'bName', title: '宝宝姓名', sort: true, align: 'center'}
				,{field: 'pid',title: '家长编号', sort: true,align: 'center'}
				, {field: 'pName', title: '家长姓名', align: 'center'}
				, {fixed: 'right', title: '操作', toolbar: '#barDemo', align: 'center'}
			]]
			, id: 'testReload'
			, limit: 5
			, limits: [5, 10, 20]

		});


		//查询
		var  active = {
			reload: function () {
				var bName = $('#bName');

				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					, where: {
						bName: bName.val()
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
			 if (obj.event === 'babySafeStudy') {

				layer.open({
					type: 2
					, title: '查看宝宝安全教育'
					, offset: 'auto'
					, content: '/ChildSchool/BackAction/babysafestudy.action?pid='+data.pid
					, area: ['900px', '550px']
					, btn: ['确定', '取消']
					, shade: 0
					, success: function (layero, index) {
						var body = layer.getChildFrame('body', index);
						body.find("#bName").html(data.bName);
						body.find("#hiddenPid").val(data.pid);
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
