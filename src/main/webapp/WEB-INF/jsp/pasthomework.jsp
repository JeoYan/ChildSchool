<%--
  Created by IntelliJ IDEA.
  User: 陈美杰
  Date: 2019/12/27
  Time: 10:36
  往期作业界面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>往期作业</title>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="../../layuiadmin/layui/css/layui.css" media="all">
</head>
<body>
<div class="layui-container" id="box">
	<h1>往期作业</h1>
	<table class="layui-hide" lay-filter="test" id="LAY_table_user" ></table>
</div>
</body>
<script src="../../layuiadmin/layui/layui.js" charset="UTF-8"></script>
<script>
	layui.use(['layer','table'], function(){
		var table = layui.table;
		var layer = layui.layer;
		var data;
		var $ = layui.$;
		var object;
		//方法级渲染
		table.render({
			elem: '#LAY_table_user'
			,filter:'test'
			,url: '/ChildSchool/HomeworkController/queryPastHomework'
			,cols: [[
				{field: 'hid', title: '作业编号'}
				,{field: 'hName', title: '作业名称'}
				,{field: 'hDate', title: '发布时间',sort: true}
				,{field: 'score', title: '作业评价',sort: true}
			]]
			,cellMinWidth: 80
			,limits:[5,10,20,40,100]
			,limit:5
			,id: 'testReload'
			,page: true
			,height: 350
		});
	});

</script>
</html>
