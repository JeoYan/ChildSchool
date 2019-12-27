<!DOCTYPE html>
<%--/**--%>
<%--* 幼儿管理---%>
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
	<title>幼儿管理</title>
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
	<h1 style="text-align: center">幼儿管理</h1>
	<div >
		查询条件:
	</div>
	<br>
	<div style="text-align: center">
		<form class="layui-form" action="" onsubmit="return false">
	创建时间:
	<div class="layui-inline">
		<input class="layui-input" type="date" name="starttime" id="demo1" autocomplete="off">
	</div>	至
	<div class="layui-inline">
		<input class="layui-input" type="date" name="endtime" id="demo2" autocomplete="off">
	</div>

	<br>

	宝宝名称:
	<div class="layui-inline">
		<input class="layui-input" name="bname" id="demoReload" autocomplete="off">
	</div>

		<div class="layui-inline">
			<label class="layui-form-label">所在班级</label>
			<div class="layui-input-inline" >
				<select name="cname" id="demoReload1">
					<option value=""></option>

					<c:forEach items="${requestScope.cname}" begin="0" step="1" var="y">
						<option value="${y.cid}">${y.cname}</option>
					</c:forEach>

				</select>
			</div>
		</div>

	<button class="layui-btn" data-type="reload">查询</button>
	<button class="layui-btn layui-btn-normal" data-type="add" >新增</button>
		</form>
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
			url: '/ChildSchool/babyxs.action' ,
			page: 1,
			limit:5,
			limits:[5],
			// method:'post',

			cols: [[
				{field: 'bid', title: '宝宝编号' , sort: true, fixed: 'center'} ,
				{field: 'bname', title: '宝宝名称'} ,
				{field: 'cname', title: '班级名称'} ,
				{field: 'cid', title: '班级id',hide:true} ,
				{field: 'bsex', title: '性别', sort: true} ,
				{field: 'bbirth', title: '出生年月'},
				{field: 'bdate', title: '创建时间'},

				{fixed: 'right', title:'操作', toolbar: '#barDemo'}
			]]

		});


		var $ = layui.$, active = {
			//查询
			reload: function(){
				var demoReload = $('#demoReload');
				var demo1= $('#demo1');
				var demo2= $('#demo2');
				var demoReload1 =  $("#demoReload1").find("option:selected").text();
				//执行重载
				table.reload('test', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					,where: {

						bname: demoReload.val(),
						starttime:demo1.val(),
						endtime:demo2.val(),
						cname: demoReload1
					}
				}, 'data');
			},
			//增加
			add: function () {
				layer.open({
					type: 2,
					title: '新增',
					content: '/ChildSchool/Admission.action',
					maxmin: true,
					area: ['1000px', '520px'],
					btn: ['确定', '取消'],
					yes: function (index, layero) {
						//幼儿名称
						var bname=$(layero).find('iframe')[0].contentWindow.bname.value;
						//班级名称
						var cid=$(layero).find('iframe')[0].contentWindow.cname.value;
						//性别
						var bsex=$(layero).find('iframe')[0].contentWindow.bsex.value;
						//出生年月
						var bbirth=$(layero).find('iframe')[0].contentWindow.bbirth.value;
						//入园时间
						var bdate=$(layero).find('iframe')[0].contentWindow.bdate.value;
						//宝宝地址
						var baddress=$(layero).find('iframe')[0].contentWindow.baddress.value;

						//家长名称
						var pname=$(layero).find('iframe')[0].contentWindow.pname.value;
						//亲子关系
						var prelation=$(layero).find('iframe')[0].contentWindow.prelation.value;

						//家长手机
						var pphone=$(layero).find('iframe')[0].contentWindow.pphone.value;
						//家长职业
						var pjob=$(layero).find('iframe')[0].contentWindow.pjob.value;
						//家长身份证
						var identitycard=$(layero).find('iframe')[0].contentWindow.identitycard.value;

						var ob={bname:bname,cid:cid,bsex:bsex,bbirth:bbirth,bdate:bdate,baddress:baddress,
							pname:pname,prelation:prelation,pjob:pjob,pphone:pphone,identitycard:identitycard};

						alert(ob);
						$.ajax({
							type:"POST",//提交方式
							url:"/ChildSchool/addAdmission.action",//提交地址
							data:ob,//数据
							dataType:"json",//希望返回的数据类型
							async:true,//异步操作
							success:function (msg) {
								alert(msg);
								if(msg.msg==1){
									alert("增加入园信息成功");
									table.reload('test');
									layer.close(index);
								}else if(msg.msg=="2"){
									alert("增加入园信息失败");
								}

							},
							error:function () {
								alert("服务器正忙")
							}
						})
					}
				});
			},
			// add: function () {
			// 	layer.open({
			// 		type: 2,
			// 		title: '新增',
			// 		content: '/ChildSchool/xzbaby.action',
			// 		maxmin: true,
			// 		area: ['500px', '400px'],
			// 		btn: ['确定', '取消'],
			// 		yes: function (index, layero) {
			// 			//教师名称
			// 			var bname=$(layero).find('iframe')[0].contentWindow.bname.value;
			// 			//性别
			// 			var bsex=$(layero).find('iframe')[0].contentWindow.bsex.value;
			// 			//出生年月
			// 			var bbirth=$(layero).find('iframe')[0].contentWindow.bbirth.value;
			//
			// 			var ob={bname:bname,bsex:bsex,bbirth:bbirth};
			// 			alert(ob);
			// 			$.ajax({
			// 				type:"POST",//提交方式
			// 				url:"/ChildSchool/addbaby.action",//提交地址
			// 				data:ob,//数据
			// 				dataType:"json",//希望返回的数据类型
			// 				async:true,//异步操作
			// 				success:function (msg) {
			// 					alert(msg);
			// 					if(msg.msg==1){
			// 						alert("增加成功");
			// 						table.reload('test');
			// 						layer.close(index);
			// 					}else if(msg.msg=="2"){
			// 						alert("增加失败");
			// 					}
			//
			// 				},
			// 				error:function () {
			// 					alert("服务器正忙")
			// 				}
			// 			})
			// 		}
			// 	});
			// },
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

				var v=confirm('删除时会连带家长一起删除，确定么');
					alert(data.bid);
					var id =data.bid;
					if (v==true){
					var ob= {bid:id};
					alert(ob);
					//走AJAX
					$.ajax({
						type:"POST",//提交方式
						url:"/ChildSchool/deletebaby.action",//路径
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
				alert(data.cid);

				layer.open({
					type: 2,
					title: '修改',
					content: '/ChildSchool/xgbaby.action',
					maxmin: true,
					area: ['500px', '400px'],
					btn: ['确定', '取消'],
					success: function (layero,index) {
						var body = layer.getChildFrame('body', index);
						body.find("#bname").val(data.bname);
						body.find("#cname").val(data.cid);
						body.find("#bsex").val(data.bsex);
					},
					yes:function (index,layero) {
						var bname = $(layero).find('iframe')[0].contentWindow.bname.value;
						var bsex = $(layero).find('iframe')[0].contentWindow.bsex.value;
						//班级名称
						var cid=$(layero).find('iframe')[0].contentWindow.cname.value;

						var bid = data.bid;

						var ob = {bid: bid,cid:cid,bname: bname,bsex:bsex};
						$.ajax({
							type: "POST",//提交方式
							url: "/ChildSchool/updatebaby.action",//路径
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