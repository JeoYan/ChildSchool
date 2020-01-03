<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/21
  Time: 1:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<title>公告管理</title>

	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
	      content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/layui/css/layui.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/style/admin.css"%> media="all">
	<link rel="stylesheet" href=<%=path+"/layuiadmin/style/login.css"%> media="all">

	<script src=<%=path + "/js/jquery-3.4.1.js"%>></script>
	<script src=<%=path + "/layuiadmin/layui/layui.js"%>></script>

</head>
<body>


<div id="LAY-user-login" style="display: none;">
	<div class="layadmin-user-login-box layadmin-user-login-header">
		<h2>公告管理</h2>
	</div>
	<div style="text-align: center">
		<%--		<div class="layui-inline" style="width:1000px;text-align: right">--%>
		<%--			<a href="userlogin.jsp">登录</a>&nbsp&nbsp&nbsp&nbsp--%>
		<%--			<a href="userlogin.jsp">注册</a>--%>
		<%--		</div>--%>
		<div class="layui-inline" style="width:500px;">
			<hr>
		</div>


	</div>
	<div class="layui-card-body">
		<div style="text-align: center;">

			<div class="layui-inline">
				注册时间：
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="startDate" placeholder="yyyy-MM-dd HH:mm:ss">
				</div>
				至
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="endDate" name="endDate" placeholder="yyyy-MM-dd HH:mm:ss">
				</div>
				&nbsp;公告标题：
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="notitle" name="title"  >
				</div>

			</div>


			<div class="layui-inline">
				<button class="layui-btn layuiadmin-btn-admin" id="serace" lay-submit
				        lay-filter="LAY-user-back-search">
					<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
				</button>
			</div>

			<div class="layui-inline">
				<button class="layui-btn layuiadmin-btn-useradmin" data-type="add">新增公告</button>

			</div>
		</div>
	</div>
	<div style="text-align: center">
		<div class="layui-inline" style="width:96%">
			<table id="demo" lay-filter="demo"></table>
		</div>
	</div>
	<div class="layui-trans layadmin-user-login-footer">

	</div>


</div>


<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="update">修改</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
</script>

<script>

	layui.use('form', function () {
		var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

		form.render();
	});

	layui.use('table', function () {
		var table = layui.table;

		//第一个实例
		table.render({
			elem: '#demo'
			, height: 350
			, url: '/ChildSchool/findNoticelManage.action' //数据接口
			, page: true //开启分页
			, limit: 5
			, done: function () {
				$('.layui-laypage-limits').hide();
			}
			, id: 'demo'
			, cols: [[ //表头
				// {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
				// ,
				{type: 'numbers', title: '序号', align: 'center'}
				, {field: 'notitle', title: '公告名',align: 'center'}
				, {field: 'nconntext', title: '公告内容', align: 'center'}
				, {field: 'ndate', title: '发布时间', align: 'center'}
				, {field: 'wname', title: '发布人', align: 'center'}
				, {field: 'wid', title: 'wid', hide: true}
				, {field: '', title: '操作', align: "center", toolbar: "#barDemo"}

			]]
		});


		var $ = layui.$, active = {
			reload: function () {
				var demoReload = $('#demo');
				//执行重载
				table.reload('demo', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					, where: {
						key: {
							id: demoReload.val()
						}
					}
				}, 'data');
			}

		};


		layui.config({}).extend({}).use(['table'], function () {
			var $ = layui.$
				, form = layui.form
				, table = layui.table;


			// //时间搜索
			// $('#startDate').on('click', function () {
			// 	$('#endDate').val("9999-12-30 00:00:00");
			// });
			//
			// $('#endDate').on('click', function () {
			// 	$('#startDate').val("2019-12-01 00:00:00");
			// });

			//监听搜索
			$('#serace').on('click', function () {
				var startDate = $('#startDate').val();
				var endDate = $('#endDate').val();
				var notitle = $('#notitle').val();
				table.reload('demo', {
					where: {
						'startDate': startDate,
						'endDate': endDate,
						'notitle': notitle
					}, page: {
						curr: 1
					}
				})

			});

			//修改
			table.on('tool(demo)', function (obj) {//注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
				var data = obj.data;//获得当前行数据
				if (obj.event === 'update') {
					layer.open({
						type: 2
						, title: '修改公告'
						, content: '/ChildSchool/updateNoticeView.action'
						, maxmin: true
						, area: ['500px', '450px']
						, btn: ['确定', '取消']
						, success: function (layero, index) {
							//回显到窗口
							var body = layer.getChildFrame('body', index);
							body.find("#notitle").val(data.notitle);
							body.find("#nconntext").val(data.nconntext);
							body.find("#wName").val(data.wname);




						}
						, yes: function (index, layero) {
							//窗口拿数据

							//发布人
							var wid = data.wid;
							//时间
							var ndate = data.ndate;


							//标题
							var notitle = $(layero).find('iframe')[0].contentWindow.notitle.value;


							//内容
							var nconntext = $(layero).find('iframe')[0].contentWindow.nconntext.value;




							if (notitle.length===0){
								layer.msg("请输入标题！");
							}else if (nconntext.length===0){
								layer.msg("请输入公告内容！");
							} else {
								var ob = {
									wid: wid,
									notitle: notitle,
									nconntext: nconntext,
									ndate:ndate
								};

								$.ajax({
									type: "POST",//提奥的方式
									url: "/ChildSchool/updateNotice.action",//提交的地址
									data: ob,//提交的数据
									dataType: "json",//希望返回的数据类型
									async: true,//异步操作
									success: function (num) {//成功的方法  msg为返回数据

										if (num>0) {
											layer.msg("修改成功！");
											table.reload('demo');
											layer.close(index);
										}else {
											layer.msg("修改失败！");
										}
									},
									error: function () {//错误的方法
										alert("服务器未找到")
									}
								});
							}


						},

						// ,value: data.USERNAME
					});
				} else if (obj.event === 'delete') {
					layer.confirm('确定删除？', function (index) {
						// layer.close(index);
						//发布人
						var wid = data.wid;
						//时间
						var ndate = data.ndate;

						var ob = {wid: wid, ndate: ndate};


						$.ajax({
							type: "POST",//提奥的方式
							url: "/ChildSchool/deleteNoticel.action",//提交的地址
							data: ob,//提交的数据
							dataType: "json",//希望返回的数据类型
							async: true,//异步操作
							success: function (num) {//成功的方法  msg为返回数据
								// alert(num);
								//msg字符串切割得到list和

								//未查询到数据时执行
								if (num >= 1) {
									layer.msg("删除成功");

									var index=table.cache.demo;
								//	alert(index.length===1);
									if(index.length===1){
										table.reload('demo', {
											page: {
												curr: 1
											}
										});

									}else {
										table.reload('demo');

									}

									// //刷新表格
									// table.reload();
									layer.close(index);

								}
							},
							error: function () {//错误的方法
								alert("服务器未找到")
							}
						});

					});


				}

			});


			//新增
			var active = {
				batchdel: function () {
					var checkStatus = table.checkStatus('LAY-user-manage')
						, checkData = checkStatus.data; //得到选中的数据

					if (checkData.length === 0) {
						return layer.msg('请选择数据');
					}

					layer.prompt({
						formType: 1
						, title: '敏感操作，请验证口令'
					}, function (value, index) {
						layer.close(index);

						layer.confirm('确定删除吗？', function (index) {

							//执行 Ajax 后重载
							/*
							admin.req({
							  url: 'xxx'
							  //,……
							});
							*/
							table.reload('LAY-user-manage');
							layer.msg('已删除');
						});
					});
				}
				, add: function () {
					layer.open({
						type: 2
						, title: '新增公告'
						, content: '/ChildSchool/addNoticeView.action'
						, maxmin: true
						, area: ['500px', '450px']
						, btn: ['确定', '取消']
						, yes: function (index, layero) {
							//alert("增加");



							//发布人
							var wid = $(layero).find('iframe')[0].contentWindow.wid.value;

							//标题
							var nctitle = $(layero).find('iframe')[0].contentWindow.nctitle.value;
							//内容
							var nconntext = $(layero).find('iframe')[0].contentWindow.nconntext.value;

							if (nctitle.length===0){
								layer.msg("请输入标题！");
							}else if (nconntext.length===0){
								layer.msg("请输入公告内容！");
							} else {
								var ob = {
									wid: wid,
									notitle: nctitle,
									nconntext: nconntext
								};

								$.ajax({
									type: "POST",//提奥的方式
									url: "/ChildSchool/addNotice.action",//提交的地址
									data: ob,//提交的数据
									dataType: "json",//希望返回的数据类型
									async: true,//异步操作
									success: function (num) {//成功的方法  msg为返回数据

										if (num>0) {
											layer.msg("发送成功！");
											table.reload('demo');
											layer.close(index);
										}else {
											layer.msg("发送失败！");
										}
									},
									error: function () {//错误的方法
										alert("服务器未找到")
									}
								});
							}

						}
					});
				}
			};

			$('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
		});

	});

	//时间选择器
	layui.use('laydate', function () {
		var laydate = layui.laydate;
		//常规用法
		laydate.render({
			elem: '#startDate'
			,type: 'datetime'
		});
		laydate.render({
			elem: '#endDate'
			,type: 'datetime'
		});
	});

</script>


</body>
</html>

