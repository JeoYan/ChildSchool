<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/2
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>校园直播</title>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="../../layuiadmin/layui/css/layui.css" media="all">
</head>
<body>
<div style="text-align: center">
	<div style="margin: 0 auto">
		<h1 style="margin: 0 auto">园所名称：智慧幼儿园</h1>
		<div>
			<label class="layui-inline">宝宝姓名：小一</label>
			<label style="margin-left: 50px">班级：小一班</label>
		</div>
	</div>
	<div class="layui-inline" style="width:96%">
		<table class="layui-hide" lay-filter="test" id="LAY_table_user" ></table>
	</div>
</div>
</body>
<script type="text/html" id="toolbarDemo">
	<div class="layui-btn-container">
		<button class="layui-btn layui-btn-sm" lay-event="play">播放视频</button>
	</div>
</script>
<script type="text/html" src="../../video-js/video.js" charset="UTF-8"></script>
<script src="../../layuiadmin/layui/layui.js" charset="UTF-8"></script>
<script type="text/javascript">
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
			,url: '/ChildSchool/VideoController/findAllVideo'
			,cols: [[
				{field: 'vName', title: '监控位置',align: 'center'}
				,{field: 'url', title: '监控视频路径',align: 'center',hide:true}
				,{field: 'vid', title: '监控视频id',align: 'center',hide:true, sort: true}
				,{title:'操作', toolbar: '#toolbarDemo',align: 'center'}
			]]
			// ,cellMinWidth: 80
			,limits:[5,10,20,40,100]
			,limit:5
			,id: 'testReload'
			,page: true
			,height: 350
		});
		table.on('tool(test)', function(obj){
			object = obj;
			data = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			if(layEvent==='play'){
				var loadstr = '<video width="100%" height="100%"  controls="controls" autobuffer="autobuffer"  autoplay="autoplay" loop="loop"><source src="'+data.url+'" type="video/mp4"/></video>';
				layer.open({
					type: 1,
					title: data.vName,
					area: ['60%','80%'],
					content: loadstr
				});
			}
		});

	});

</script>
</html>
