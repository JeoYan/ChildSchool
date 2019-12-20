<!DOCTYPE html>
<%--/**--%>
<%--* 家长管理---%>
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
	<title>家长管理</title>
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
	<h1 style="text-align: center">家长管理</h1>
	<div >
		查询条件:
	</div>
	<br>
	<div style="text-align: center">
	创建时间:
	<div class="layui-inline">
		<input class="layui-input" type="date" name="starttime" id="demo1" autocomplete="off">
	</div>	至
	<div class="layui-inline">
		<input class="layui-input" type="date" name="endtime" id="demo2" autocomplete="off">
	</div>

	<br>

	家长名称:
	<div class="layui-inline">
		<input class="layui-input" name="pname" id="demoReload" autocomplete="off">
	</div>
	<button class="layui-btn" data-type="reload">查询</button>
	<button class="layui-btn layui-btn-normal" data-type="add" >新增</button>
	</div>
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
			url: '/ChildSchool/parentxs.action' ,
			page: 1,
			limit:5,
			limits:[5],
			// method:'post',

			cols: [[
				{field: 'pid', title: '家长编号', width:130 , sort: true, fixed: 'left'} ,
				{field: 'pname', title: '家长名称', width:130} ,
				{field: 'bname', title: '宝宝名称', width:130} ,
				{field: 'prelation', title: '亲子关系', width:130} ,
				{field: 'pphone', title: '联系方式', width:130, sort: true} ,
				{field: 'pjob', title: '职业', width:130},
				{field: 'pdate', title: '创建时间', width:130},

				{fixed: 'right', title:'操作', toolbar: '#barDemo', width:130}
			]]

		});


		var $ = layui.$, active = {
			//查询
			reload: function(){
				var demoReload = $('#demoReload');
				var demo1= $('#demo1');
				var demo2= $('#demo2');
				//执行重载
				table.reload('test', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					,where: {

						pname: demoReload.val(),
						starttime:demo1.val(),
						endtime:demo2.val()
					}
				}, 'data');
			},
			//增加
			add: function () {
				layer.open({
					type: 2,
					title: '新增',
					content: '/ChildSchool/xzparent.action',
					maxmin: true,
					area: ['500px', '400px'],
					btn: ['确定', '取消'],
					yes: function (index, layero) {
						//家长名称
						var pname=$(layero).find('iframe')[0].contentWindow.pname.value;
						//宝宝名称
						var bname=$(layero).find('iframe')[0].contentWindow.bname.value;
						//亲子关系
						var prelation=$(layero).find('iframe')[0].contentWindow.prelation.value;
						//联系方式
						var pphone=$(layero).find('iframe')[0].contentWindow.pphone.value;
						//职业
						var pjob=$(layero).find('iframe')[0].contentWindow.pjob.value;


						var ob={pname:pname,bname:bname,prelation:prelation,pphone:pphone,pjob:pjob};
						alert(ob);
						$.ajax({
							type:"POST",//提交方式
							url:"/ChildSchool/addparent.action",//提交地址
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
					alert(data.pid);
					var id =data.pid;
					if (v==true){
					var ob= {pid:id};
					alert(ob);
					//走AJAX
					$.ajax({
						type:"POST",//提交方式
						url:"/ChildSchool/deleteparent.action",//路径
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
				alert(data.bname);

				layer.open({
					type: 2,
					title: '修改',
					content: '/ChildSchool/xgparent.action',
					maxmin: true,
					area: ['500px', '500px'],
					btn: ['确定', '取消'],
					success: function (layero,index) {
						var body = layer.getChildFrame('body', index);
						body.find("#bname").val(data.bname)

					},
					yes:function (index,layero) {
						//家长名称
						var pname=$(layero).find('iframe')[0].contentWindow.pname.value;
						//宝宝名称
						var bname=$(layero).find('iframe')[0].contentWindow.bname.value;
						//亲子关系
						var prelation=$(layero).find('iframe')[0].contentWindow.prelation.value;
						//联系方式
						var pphone=$(layero).find('iframe')[0].contentWindow.pphone.value;
						//职业
						var pjob=$(layero).find('iframe')[0].contentWindow.pjob.value;
						var pid = data.pid;
						var ob = {pid: pid, pname:pname,bname: bname,prelation:prelation,pphone:pphone,pjob:pjob};
						$.ajax({
							type: "POST",//提交方式
							url: "/ChildSchool/updateparent.action",//路径
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