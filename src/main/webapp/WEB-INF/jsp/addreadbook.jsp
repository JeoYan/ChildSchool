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
	<title>绘本上传</title>
	<meta name="referrer" content="no-referrer">
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
		<h2>新增绘本</h2>
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
				<button class="layui-btn layuiadmin-btn-useradmin" data-type="add">新增图片</button>
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
	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">预览</a>
	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>

	layui.use('form', function () {
		var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
		form.render();
	});

	//表格显示
	layui.use('table', function () {
		var table = layui.table;
		//第一个实例
		table.render({
			elem: '#demo'
			, url: '${pageContext.request.contextPath}/readBook/getBookPage.action' //数据接口
			, page: true //开启分页
			, limit: 5
			, done: function () {
				$('.layui-laypage-limits').hide();
			}
			, id: 'demo'
			, cols: [[ //表头
				// {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
				// ,
				{field: 'pageNum', title: '页数', align: 'center', width: 80}
				, {field: 'bpid', title: '绘本id', align: 'center', hide: true}
				, {field: 'bookid', title: '书id', align: 'center', hide: true}
				, {field: 'bpContent', title: '内容', align: 'center'}
				, {field: 'wid', title: '上传人的id', align: 'center', hide: true}
				, {field: 'wName', title: '上传人', align: 'center'}
				, {field: 'bDate', title: '上传时间', align: 'center'}
				, {field: 'url', title: '链接', align: 'center'}
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
				// var startDate = $('#startDate').val();
				// var endDate = $('#endDate').val();

				table.reload('demo', {
					where: {
						'bName': bName
						// 'startDate': startDate,
						// 'endDate': endDate
					}, page: {
						curr: 1
					}
				})

			});

			//修改
			table.on('tool(demo)', function (obj) {//注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
				var data = obj.data;//获得当前行数据
				if (obj.event === 'edit') {
					var bpid = data.bpid;
					var bName = $('#bName').val();
					var bpContent = data.bpContent;
					var pageNum = data.pageNum;
					// alert("修改");
					layer.open({
						type: 2
						, title: '新增图片'
						// , content: $('#addImgLayer')
						, content: '${pageContext.request.contextPath}/readBook/updateBookPage.action'
						, maxmin: true
						, area: ['500px', '450px']
						// , btn: ['确定', '取消']
						// , btnAlign: 'c'
						// ,zIndex:1
						, success: function (layero, index) {
							//向子窗口设置值
							var body = layer.getChildFrame('body', index);
							body.find("#hidebpid").val(bpid);
							body.find("#hideBookName").val(bName);
							body.find("#pageContent").val(bpContent);
							body.find("#pageNum").val(pageNum);
						}
						, end: function () {
							//放置你需要在子界面关闭后加载的方法
							$('#bName').val(bName);
							table.reload('demo', {
								where: {
									'bName': bName
								}, page: {
									curr: 1
								}
							})
						}
					});

				} else if (obj.event === 'del') {
					layer.confirm('确定删除？', function (index) {
						// layer.close(index);
						//page id
						var bpid = data.bpid;
						//书id
						var bookid = data.bookid;
						var ob = {'bpid': bpid, 'bookid': bookid};
						$.ajax({
							type: "POST",//提奥的方式
							url: "${pageContext.request.contextPath}/readBook/delPage.action",//提交的地址
							data: ob,//提交的数据
							dataType: "json",//希望返回的数据类型
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
					// alert("预览图片");
					//图片的地址
					var url = data.url;
					//图片内容
					var bpContent = data.bpContent;
					var url1 = "${pageContext.request.contextPath}/readBook/showImage.action?url=" + url;

					layer.open({
						type: 1
						, title: '预览图片'
						// , content: $('#addImgLayer')
						<%--, content: '${pageContext.request.contextPath}/readBook/updateBookPage.action'--%>
						, content: $('#showImage')
						, maxmin: true
						, area: ['600px', '450px']
						,shade: 0
						,shadeClose:true
						// , btn: ['确定', '取消']
						// , btnAlign: 'c'
						// ,zIndex:1
						, success: function (layero, index) {
							// alert(url1);
							// $("#myImage").attr("src", url1);
							$("#myTest").css("background-image","url(" + url1 + ")");
							$("#myContext").html(bpContent);

						}

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
					//绘本名字
					var bName = $('#bName').val();
					if (bName != null && bName != "") {
						//此处要正则判断----------------------------------------------------------
						layer.open({
							type: 2
							, title: '新增图片'
							// , content: $('#addImgLayer')
							, content: '${pageContext.request.contextPath}/readBook/addBookPage.action'
							, maxmin: true
							, area: ['500px', '450px']
							// , btn: ['确定', '取消']
							// , btnAlign: 'c'
							// ,zIndex:1
							, success: function (layero, index) {
								//向子窗口设置值
								var body = layer.getChildFrame('body', index);
								body.find("#hideBookName").val(bName);

								//设置新页数
								$.ajax({
									type: "POST",//提交的方式
									url: "${pageContext.request.contextPath}/readBook/getNewPage.action",//提交的地址
									data: {'bookName': bName},
									dataType: "text",//希望返回的数据类型
									async: true,//异步操作
									success: function (msg) {//成功的方法  msg为返回数据


										body.find("#pageNum").val(msg);

									},
									error: function () {//错误的方法
										alert("服务器正忙")
									}
								});


							}
							, end: function () {
								//放置你需要在子界面关闭后加载的方法
								$('#bName').val(bName);
								table.reload('demo', {
									where: {
										'bName': bName
									}, page: {
										curr: 1
									}
								})
							}
						});
					} else {
						alert("绘本内容为空！")
					}

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



<%--预览图片弹窗--%>
<div id="showImage" style="display: none;" >
	<div class="layadmin-user-login-box layadmin-user-login-header">
		<div class="layui-carousel" id="test10">
			<div carousel-item="">
<%--				<div><img src="" id="myImage"style="width: 500px;height: 450px" ></div>--%>
			<div id="myTest" style="width: 600px;height: 450px;background-size: 100% 100%;">
				<span id="myContext" style="font-family: 华文行楷;font-size: 30px;color: #0C0C0C"></span>
			</div>
			</div>
		</div>
	</div>

	<script>
		layui.use(['carousel', 'form'], function () {
			var carousel = layui.carousel
				, form = layui.form;
			//图片轮播
			carousel.render({
				elem: '#test10'
				, width: '778px'
				, height: '440px'
				, interval: 5000
			});
		});
	</script>


</div>


</body>
</html>

