<!DOCTYPE html>
<%--/**--%>
<%--* 平台资讯---%>
<%--* by 陈超--%>
<%--*/--%>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/01/06
  Time: 15:32
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
	<title>平台资讯</title>
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
	<h1 style="text-align: center">平台资讯</h1>
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

		资讯名称:
	<div class="layui-inline">
		<input class="layui-input" name="notitle" id="demoReload" autocomplete="off">
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
			url: '/ChildSchool/platformxs.action' ,
			page: 1,
			limit:5,
			limits:[5],
			// method:'post',

			cols: [[
				{field: 'noid', title: '资讯编号' , sort: true, fixed: 'center'} ,
				{field: 'notitle', title: '资讯名称'} ,
				{field: 'nconntext', title: '资讯内容', sort: true} ,
				{field: 'wid', title: '班主任id', sort: true,hide:true} ,

				{field: 'ndate', title: '创建时间'},

				{fixed: 'right', title:'操作', toolbar: '#barDemo'}
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

						cname: demoReload.val(),
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
					content: '/ChildSchool/xzplatform.action',
					maxmin: true,
					area: ['500px', '500px'],
					btn: ['确定', '取消'],
					yes: function (index, layero) {
						//教室名称
						var notitle=$(layero).find('iframe')[0].contentWindow.notitle.value;
						var nconntext=$(layero).find('iframe')[0].contentWindow.nconntext.value;

						alert(notitle);
						alert(nconntext);
						var ob={notitle:notitle,nconntext:nconntext};

						alert(ob);
						$.ajax({
							type:"POST",//提交方式
							url:"/ChildSchool/addplatform.action",//提交地址
							data:ob,//数据
							dataType:"json",//希望返回的数据类型
							async:true,//异步操作
							success:function (msg) {
								alert(msg);
								if(msg.msg==1){
									alert("增加资讯成功");
									table.reload('test');
									layer.close(index);
								}else if(msg.msg=="2"){
									alert("增加资讯失败");
								}

							},
							error:function () {
								alert("服务器正忙")
							}
						})
					}
				});
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

				var v=confirm('确定删除么');
					alert(data.noid);
					var id =data.noid;
					if (v==true){
					var ob= {noid:id};
					alert(ob);
					//走AJAX
					$.ajax({
						type:"POST",//提交方式
						url:"/ChildSchool/deleteplatform.action",//路径
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

				alert(data.noid);

				layer.open({
					type: 2,
					title: '修改',
					content: '/ChildSchool/xgplatform.action',
					maxmin: true,
					area: ['500px', '500px'],
					btn: ['确定', '取消'],
					success: function (layero,index) {
						var body = layer.getChildFrame('body', index);
						body.find("#notitle").val(data.notitle);

						body.find("#nconntext").val(data.nconntext);
					},
					yes:function (index,layero) {
						//教室名称
						var notitle=$(layero).find('iframe')[0].contentWindow.notitle.value;

						var nconntext=$(layero).find('iframe')[0].contentWindow.nconntext.value;
					var noid=data.noid;
						alert(notitle);
						alert(nconntext);

						var ob={ noid: noid,notitle:notitle,nconntext:nconntext};

						alert(ob);
						$.ajax({
							type: "POST",//提交方式
							url: "/ChildSchool/updateplatform.action",//路径
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