<!DOCTYPE html>
<%--/**--%>
<%--* 教师管理---%>
<%--* by 陈超--%>
<%--*/--%>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/23
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = application.getContextPath();
	String uicssPath = request.getContextPath()+"/layuiadmin/";
	String JsPath = application.getContextPath()+"/js/";
	String cssPath = request.getContextPath()+"/css/";
	String uiPath = request.getContextPath()+"/layuiadmin/";

%>
<html>
<head>
	<meta charset="utf-8">
	<title>教师管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href=<%=uicssPath+"layui/css/layui.css"%>>
	<%--	<link rel="stylesheet" href=<%=uicssPath+"style/admin.css"%>>--%>
	<script src=<%=uiPath+"layui/layui.js"%>></script>
	<script src=<%=JsPath+"jquery-3.4.1.js"%>></script>
	<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>


<div class="demoTable">
	<h1 style="text-align: center">教师管理</h1>
	教师名称:
	<div class="layui-inline">
		<input class="layui-input" name="wname" id="demoReload" autocomplete="off">
	</div>
	<button class="layui-btn" data-type="reload">查询</button>
	<button class="layui-btn layui-btn-normal" data-type="add" >新增</button>

</div>

<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>


<script src="<%=uiPath+"layui/layui.js"%>" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
	layui.use('table', function(){
		var table = layui.table
			,upload =layui.upload;
		table.render({
			elem: '#test',
			height: 312 ,
			url: '/ChildSchool/teacherxs.action' ,
			page: 1,
			limit:5,
			limits:[5],
			// method:'post',

			cols: [[
				{field: 'wid', title: '教师编号', width:130 , sort: true, fixed: 'left'}      ,
				{field: 'wname', title: '教师名称', width:130} ,
				{field: 'rname', title: '角色', width:130, sort: true} ,
				{field: 'wdate', title: '创建时间', width:130}      ,
				{fixed: 'right', title:'操作', toolbar: '#barDemo', width:130}
			]]

		});


		var $ = layui.$, active = {
			//查询
			reload: function(){
				var demoReload = $('#demoReload');

				//执行重载
				table.reload('test', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					,where: {

						wname: demoReload.val()
					}
				}, 'data');
			},
			//增加
			add: function () {
				layer.open({
					type: 2,
					title: '新增',
					content: '/ChildSchool/xzteacher.action',
					maxmin: true,
					area: ['500px', '400px'],
					btn: ['确定', '取消'],
					yes: function (index, layero) {
						//教师名称
						var wname=$(layero).find('iframe')[0].contentWindow.wname.value;
						//角色
						var rid=$(layero).find('iframe')[0].contentWindow.rname.value;
						var wsex=$(layero).find('iframe')[0].contentWindow.wsex.value;
						var wbrith=$(layero).find('iframe')[0].contentWindow.wbrith.value;
						alert(wsex);
						alert(wbrith);

						var ob={wname:wname,rid:rid,wsex:wsex,wbrith:wbrith};
						alert(ob);
						$.ajax({
							type:"POST",//提交方式
							url:"/ChildSchool/addteacher.action",//提交地址
							data:ob,//数据
							dataType:"json",//希望返回的数据类型
							async:true,//异步操作
							success:function (msg) {
								alert(msg);
								if(msg.msg==1){
									alert("增加成功");
									table.reload('test');
									layer.close(index);
								}else if(msg.msg=="2"){
									alert("增加失败");
								}

							},
							error:function () {
								alert("服务器正忙")
							}
						})
					}
				});
			},
			uploadFile:function () {
				layer.open({
					type:2,
					title:"选择文件",
					content:"/jsp/upload.jsp",
					area:["500px","500px"],

				})
			}
          

		};

		$('.demoTable .layui-btn').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});

		//监听行工具事件
		table.on('tool(test)', function(obj){
			var data = obj.data;

			//删除
			if(obj.event === 'del'){

				var v=confirm('真的删除行么');
					alert(data.wid);
					var id =data.wid;
					if (v==true){
					var ob= {wid:id};
					alert(ob);
					//走AJAX
					$.ajax({
						type:"POST",//提交方式
						url:"/ChildSchool/deleteteacher.action",//路径
						data:ob,//数据
						dataType:"json",//希望返回的数据类型
						async:true,//异步操作
						success:function (msg) {//成功的方法 msg为返回数据

							if(msg.msg=="1"){
								alert("删除成功");
								table.reload('test');

							}else if(msg.msg=="2"){
								alert("删除失败")
							}

						},
						error:function () { //错误的方法
							alert("服务器正忙")
						}
					});
					}
			}
			//编辑
			else if(obj.event === 'edit'){
				alert(data.wname);
				alert(data.rname);
				layer.open({
					type: 2,
					title: '修改',
					content: '/ChildSchool/xgteacher.action',
					maxmin: true,
					area: ['500px', '500px'],
					btn: ['确定', '取消'],
					success: function (layero,index) {
						var body = layer.getChildFrame('body', index);
						body.find("#wname").val(data.wname);
						body.find("#rname").val(data.rname);
					},
					yes:function (index,layero) {
						var wname = $(layero).find('iframe')[0].contentWindow.wname.value;
						 var rid=$(layero).find('iframe')[0].contentWindow.rid.value;
						// var rname=$(layero).find('iframe')[0].contentWindow.rname.value;
						var wid = data.wid;
						var ob = {wid: wid, rid:rid,wname: wname};
						$.ajax({
							type: "POST",//提交方式
							url: "/ChildSchool/updateteacher.action",//路径
							data: ob,//数据
							dataType: "json",//希望返回的数据类型
							async: true,//异步操作
							success: function (msg) {//成功的方法 msg为返回数据

								if (msg.msg == "1") {
									alert("修改成功");
									table.reload('test');
									layer.close(index);
								} else if (msg.msg == "2") {
									alert("修改失败")
								}

							},
							error: function () { //错误的方法
								alert("服务器正忙")
							}
						});
					}
				})

			}
		});
	});

</script>



</body>
</html>