<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/18
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<title>亲子阅读管理</title>

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
		<h2>亲子阅读管理</h2>
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
				<label class="layui-form-label">绘本名称:</label>
				<div class="layui-input-inline">
					<input type="tel" name="phone" class="layui-input" id="bName">
				</div>
			</div>

			<div class="layui-inline">
				<label class="layui-form-label">日期</label>

				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="startDate" placeholder="请选择开始日期">
					<%--					<input type="text" class="layui-input" id="endDate" placeholder="请选择结束日期">--%>
				</div>

			</div>

			<div class="layui-inline">
				<%--				<label class="layui-form-label">至</label>--%>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="endDate" placeholder="请选择结束日期">
				</div>
			</div>


			<div class="layui-inline">
				<button class="layui-btn layuiadmin-btn-admin" id="serace" lay-submit
				        lay-filter="LAY-user-back-search">
					<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
				</button>
			</div>
			<div class="layui-inline">
				<button class="layui-btn layuiadmin-btn-useradmin" data-type="add">上传绘本</button>
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
	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看绘本</a>
	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
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
			, url: '${pageContext.request.contextPath}/readBook/getBookTable.action' //数据接口
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
				, {field: 'bookName', title: '绘本名称', align: 'center'}
				, {field: 'bookid', title: '书序号', align: 'center'}
				, {field: 'url', title: '绘本地址', align: 'center', hide: true}
				, {field: 'uploadDate', title: '上传时间', align: 'center'}
				, {field: 'wName', title: '上传人',align: "center"}
				// , {field: 'cid', title: 'cid', hide: true}
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

				var bName = $('#bName').val();
				var startDate = $('#startDate').val();
				var endDate = $('#endDate').val();

				table.reload('demo', {
					where: {
						'bName': bName,
						'startDate': startDate,
						'endDate': endDate
					}, page: {
						curr: 1
					}
				})

			});

			//表格操作框按钮监听
			table.on('tool(demo)', function (obj) {//注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
				var data = obj.data;//获得当前行数据
				if (obj.event === 'edit') {
					// alert("修改");
					var bName=data.bookName;
					var bookid=data.bookid;

					// alert(bName+bookid);
					layer.open({
						type: 2
						, title: '修改'
						, content: '${pageContext.request.contextPath}/readBook/updateReadBook.action'
						, maxmin: true
						, area: ['500px', '450px']
						// , btn: ['确定', '取消']
						, success: function (layero, index) {
							//回显到窗口
							var body = layer.getChildFrame('body', index);
							body.find("#bName").val(bName);
							body.find("#hideBookid").val(bookid);
						}
					});
				} else if (obj.event === 'del') {
					layer.confirm('确定删除？', function (index) {
						// layer.close(index);
						//膳食id
						var bookid = data.bookid;
						// alert("bookid"+bookid);
						var ob = {'bookid': bookid};
						$.ajax({
						type: "POST",//提奥的方式
							url: "${pageContext.request.contextPath}/readBook/delBook.action",//提交的地址--%>
							data: ob,//提交的数据
							dataType: "text",//希望返回的数据类型
							async: true,//异步操作
							success: function (num) {//成功的方法  msg为返回数据
								// alert(num);
								//msg字符串切割得到list和
								//未查询到数据时执行
								if (num >= 1) {
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
				} else if (obj.event === 'detail') {
					// alert("查看绘本");
					var bookid=data.bookid;
					// alert("bookid"+bookid);
					$.getJSON('${pageContext.request.contextPath}/readBook/getBook.action?bookid='+bookid, function(json){
						alert(json);
						layer.photos({
							photos: json
							,shift: 5 //0-6的选择，指定弹出图片动画类型，默认随机
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
					var a=layer.open({
						type: 2
						, title: '上传绘本'
						, content: '${pageContext.request.contextPath}/readBook/callAddPage.action'
						, maxmin : true
						, area: ['500px', '550px']
						// , btn: ['确定', '取消']
					});
					layer.full(a);
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
		});

		laydate.render({
			elem: '#endDate'
		});

	});

</script>


</body>
</html>

