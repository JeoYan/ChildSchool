<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/31
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>直播权限管理</title>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="../../layuiadmin/layui/css/layui.css" media="all">
</head>
<body>
<div class="layui-container" id="box">
	<div class="layui-row" id="menuDiv" style="display:none;">
		<div id="test5" class="demo-tree"></div>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
			<legend></legend>
		</fieldset>
		<div style="margin-left: 30%">
			<button type="button" id="editMenu" class="layui-btn">分配</button>
			<button type="button" id="cancelBtn" class="layui-btn">取消</button>
		</div>

	</div>

	<div style="text-align: center">
		<h1 style="margin: 0 auto">园所名称：智慧幼儿园</h1>
		<div class="layui-inline" style="width:96%">
			<table class="layui-hide" lay-filter="test" id="LAY_table_user" ></table>
		</div>
	</div>
</div>
</body>
<script type="text/html" id="toolbarDemo">
	<div class="layui-btn-container">
		<button class="layui-btn layui-btn-sm" lay-event="sortAuthority">配置权限</button>
	</div>
</script>
<script src="../../layuiadmin/layui/layui.js" charset="UTF-8"></script>
<script type="text/javascript">
	layui.use(['layer','table','tree','util'], function(){
		var table = layui.table;
		var layer = layui.layer;
		var data;
		var $ = layui.$;
		var tree = layui.tree
			,util = layui.util;
		var object;
		//方法级渲染
		table.render({
			elem: '#LAY_table_user'
			,filter:'test'
			,url: '/ChildSchool/VideoController/findAllVideoRole'
			,cols: [[
				{field: 'rid', title: '角色id',align: 'center'}
				,{field: 'rname', title: '角色名称',align: 'center'}
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
			if(layEvent==='sortAuthority'){
				$.ajax({
					url:'/ChildSchool/VideoController/findVideoByVRid',
					type:'Post',
					data:{
						id:data.rid
					},
					success:function (data) {
						tree.render({
							elem: '#test5'
							,data:data
							,id:'menu'
							,onlyIconControl: true  //是否仅允许节点左侧图标控制展开收缩
							,showCheckbox: true
							,isJump: true  //link 为参数匹配
						});
					},
					error:function () {
						layer.msg("树形数据获取失败");
					}
				});


				layer.open({
					type:1,
					title:"配置权限",
					area: ['30%','50%'],
					offset: '100px',
					content: $("#menuDiv")
				});
			}
		});

		$("#editMenu").on('click',function () {
			var checkedData = tree.getChecked('menu'); //获取选中节点的数据
			var str = JSON.stringify(checkedData);    //转为json格式
			$.ajax({
				type: 'post',
				url: '/ChildSchool/VideoController/updateMenu',
				data:{str:str,rid:data.rid},
				async : true,
				success : function(msg){
					if($.trim(msg)==="1"){
						layer.msg("分配成功", {icon: 6});
						setTimeout(function(){
							layer.closeAll();//关闭所有的弹出层
						}, 500);
					}else{
						layer.msg("分配失败", {icon: 5});
					}
				}
			});
		});

	});

</script>
</html>
