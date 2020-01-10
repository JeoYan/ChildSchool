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
	<title>新闻中心</title>
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
		<h2>新闻中心</h2>
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
				发布时间：
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="startDate" placeholder="yyyy-MM-dd HH:mm:ss">
				</div>
				至
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="endDate" name="endDate"
					       placeholder="yyyy-MM-dd HH:mm:ss">
				</div>
				<%--				<br>--%>
				&nbsp;标题：
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="ntitle" name="title" style="width: 150px">
				</div>
				发布人：
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="worker" name="worker" style="width: 120px">
				</div>

			</div>


			<div class="layui-inline">
				<button class="layui-btn layuiadmin-btn-admin" id="serace" lay-submit
				        lay-filter="LAY-user-back-search">
					<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
				</button>
			</div>

			<div class="layui-inline">
				<button class="layui-btn layuiadmin-btn-useradmin" data-type="add">新增新闻</button>

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
	<%--	<a class="layui-btn layui-btn-xs" lay-event="see">查看</a>--%>
	<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update">修改</a>
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
			, url: '/ChildSchool/news/findNews.action' //数据接口
			, page: true //开启分页
			, limit: 5
			, done: function () {
				$('.layui-laypage-limits').hide();
			}
			, id: 'demo'
			, cols: [[ //表头
				{type: 'numbers', title: '序号', align: 'center'}
				, {field: 'nid', title: '新闻id', align: 'center', hide: true}
				, {field: 'ntitle', title: '标题', align: 'center'}
				, {field: 'nconntext', title: '内容', align: 'center'}
				, {field: 'ndate', title: '发布时间', align: 'center'}
				, {field: 'wName', title: '发布人', align: 'center'}
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


			//监听搜索
			$('#serace').on('click', function () {
				var startDate = $('#startDate').val();
				var endDate = $('#endDate').val();
				var ntitle = $('#ntitle').val();
				var worker = $('#worker').val();

				table.reload('demo', {
					where: {
						'startDate': startDate,
						'endDate': endDate,
						'ntitle': ntitle,
						'worker': worker
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
						, title: '修改'
						, content: '/ChildSchool/news/updateNewsView.action'
						, maxmin: true
						, area: ['500px', '450px']
						, btn: ['确定', '取消']
						, success: function (layero, index) {
							//回显到窗口
							var body = layer.getChildFrame('body', index);
							body.find("#ntitle").val(data.ntitle);
							body.find("#nconntext").val(data.nconntext);
							body.find("#wName").val(data.wName);
						}
						, yes: function (index, layero) {
							//窗口拿数据

							//发布人
							var wid = ${sessionScope.wid};
							//新闻id
							var nid = data.nid;

							//标题
							var ntitle = $(layero).find('iframe')[0].contentWindow.ntitle.value;


							//内容
							var nconntext = $(layero).find('iframe')[0].contentWindow.nconntext.value;
							if (ntitle.length === 0) {
								layer.msg("请输入标题！");
							} else if (nconntext.length === 0) {
								layer.msg("请输入新闻内容！");
							} else {
								var ob = {
									wid: wid,
									nid: nid,
									ntitle: ntitle,
									nconntext: nconntext
								};

								$.ajax({
									type: "POST",//提奥的方式
									url: "/ChildSchool/news/updateNews.action",//提交的地址
									data: ob,//提交的数据
									dataType: "text",//希望返回的数据类型
									async: true,//异步操作
									success: function (data) {//成功的方法  msg为返回数据

										if (data === "Ok") {
											layer.msg("修改成功！");
											table.reload('demo');
											layer.close(index);
										} else {
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
						//发布人
						var nid = data.nid;
						//时间
						// var ndate = data.ndate;

						var ob = {'wid': wid, 'nid': nid};


						$.ajax({
							type: "POST",//提奥的方式
							url: "/ChildSchool/news/deleteNews.action",//提交的地址
							data: ob,//提交的数据
							dataType: "text",//希望返回的数据类型
							async: true,//异步操作
							success: function (data) {//成功的方法  msg为返回数据
								// alert(num);
								//msg字符串切割得到list和

								//未查询到数据时执行
								if (data === "Ok") {
									layer.msg("删除成功");
									var index = table.cache.demo;
									if (index.length === 1) {
										table.reload('demo', {
											page: {
												curr: 1
											}
										});

									} else {
										table.reload('demo');
									}

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
				add: function () {
					layer.open({
						type: 2
						, title: '新增新闻'
						, content: '/ChildSchool/news/addNewsView.action'
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

							if (nctitle.length === 0) {
								layer.msg("请输入标题！");
							} else if (nconntext.length === 0) {
								layer.msg("请输入新闻内容！");
							} else {
								var ob = {
									wid: wid,
									ntitle: nctitle,
									nconntext: nconntext
								};

								$.ajax({
									type: "POST",//提奥的方式
									url: "/ChildSchool/news/addNews.action",//提交的地址
									data: ob,//提交的数据
									dataType: "text",//希望返回的数据类型
									async: true,//异步操作
									success: function (data) {//成功的方法  msg为返回数据
										if (data === "Ok") {
											layer.msg("新增成功！");
											table.reload('demo');
											layer.close(index);
										} else {
											layer.msg("新增失败！");
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
			, type: 'datetime'
		});
		laydate.render({
			elem: '#endDate'
			, type: 'datetime'
		});
	});

</script>


</body>
</html>

