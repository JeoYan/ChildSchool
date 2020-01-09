<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/18
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String layuiPath = request.getContextPath() + "/layuiadmin/layui/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>卡入库</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script  src=<%=layuiPath + "layui.js"%>></script>
</head>
<body>
<div class="layui-fluid">
	<div class="layui-card">
		<div>
			<h1 style="text-align: center">
				卡片管理
			</h1>
		</div>
		<div>
			<h3 style="text-align: left">
				查询条件
			</h3>
		</div>
		<div class="demoTable">
			<%--	<div style="padding-bottom: 10px;">--%>
			开始卡号：
			<div class="layui-inline">
				<input class="layui-input" name="id" id="startNum" autocomplete="off">
			</div>
			截止卡号
			<div class="layui-inline">
				<input class="layui-input" name="id" id="endNum" autocomplete="off">
			</div>
			入库时间
			<div class="layui-inline">
				<input class="layui-input" type="date" name="id" id="cardTime" autocomplete="off">
			</div>
			宝贝姓名
			<div class="layui-inline">
				<input class="layui-input" name="id" id="bName" autocomplete="off">
			</div>
			<button class="layui-btn" data-type="reload">查询</button>

			<button class="layui-btn " data-type="add">新增</button>
		</div>


		<table id="demo" lay-filter="test" ></table>
		<script type="text/html" id="barDemo">
			{{# if(d.cardState ==null){ }}
			{{# if(d.bName ==null){ }}
			<a class="layui-btn layui-btn-xs" lay-event="bindCard">绑定宝宝</a>
			{{# } }}
			{{# if(d.bName !=null){ }}
			<a class="layui-btn layui-btn-xs" lay-event="unbindCard">解除绑定</a>
			{{# } }}
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
			{{# } }}
		</script>
		<script>

			var reloadTable = null;
			layui.use('table', function () {
				var table = layui.table
					, form = layui.form;
				var layer = layui.layer;

				//数据表格
				reloadTable = table.render({
					elem: '#demo'
					, url: '/ChildSchool/BackAction/findCard.action' //数据接口
					, page: true //开启分页
					, cols: [[ //表头
						{field: 'cardNum',title: '卡片编号', sort: true, fixed: 'left',align: 'center'}
						,{field: 'cardTime', title: '导入时间', sort: true, fixed: 'left', align: 'center'}
						, {field: 'bid', title: '宝宝编号', align: 'center'}
						, {field: 'bName', title: '宝宝姓名', align: 'center'}
						, {field: 'cardState', title: '卡片状态', sort: true, align: 'center'}
						, {fixed: 'right', title: '操作', toolbar: '#barDemo', align: 'center'}
					]]
					, id: 'testReload'
					, limit: 5
					, limits: [5, 10, 20]

				});

				//查询
				var $ = layui.$, active = {
					reload: function () {
						var startNum = $('#startNum');
						var endNum = $('#endNum');
						var bName = $('#bName');
						var cardTime = $('#cardTime');
						//执行重载
						table.reload('testReload', {
							page: {
								curr: 1 //重新从第 1 页开始
							}
							, where: {
								startNum: startNum.val(),
								endNum: endNum.val(),
								bName: bName.val(),
								cardTime: cardTime.val()
							}
						}, 'data');
					},
					//卡入库
					add: function () {
						layer.open({
							type: 2
							, title: '卡入库'
							, offset: 'auto'
							, content: '/ChildSchool/BackAction/addCardView.action'
							, area: ['600px', '310px']
							, btn: ['关闭']
							, shade: 0

						});
					}
				};

				$('.demoTable .layui-btn').on('click', function () {
					var type = $(this).data('type');
					active[type] ? active[type].call(this) : '';
				});


				table.on('tool(test)', function (obj) {
					var data = obj.data;
					if (obj.event === 'del') {//删除
						layer.confirm('真的删除么', function (index) {
							var ob = {"cardNum": data.cardNum};
							if (data.bName==null || data.bName=='') {
								$.ajax({
									url: '/ChildSchool/BackAction/delCard.action',
									type: "POST",
									data: ob,
									dataType: "json",
									success: function (msg) {
										if (msg.msg === "ok") {
											layer.msg("删除成功", {icon: 6});
											reloadTable.reload();
											layer.close(index);
										} else if (msg.msg === "error") {
											layer.msg("删除失败", {icon: 5});
										}

									},
									error: function () {
										layer.msg("服务器正忙", {icon: 5});
									}
								});
							}else {
								layer.msg("请先解绑", {icon: 5});
								reloadTable.reload();
								layer.close(index);

							}

						});
					}
					//绑定
					else if (obj.event === 'bindCard') {
						layer.open({
							type: 2
							, title: '绑定'
							, offset: 'auto'
							, content: '/ChildSchool/BackAction/cardBindView.action'
							, area: ['500px', '450px']
							, btn: ['关闭']
							, shade: 0
							, success: function (layero, index) {
								var body = layer.getChildFrame('body', index);
								body.find("#cardNum").val(data.cardNum);
								body.find("#cardTime").val(data.cardTime);
							}

						});

					}
					//解除绑定
					else if (obj.event === 'unbindCard') {
						layer.confirm('确定解除绑定吗？', function (index) {
								var ob = {"cardNum": data.cardNum};
								$.ajax({
									url: '/ChildSchool/BackAction/unbindCard.action',
									type: "POST",
									data: ob,
									dataType: "json",
									success: function (msg) {
										if (msg.msg === "ok") {
											layer.msg('解除绑定成功', {
												time: 1000,
												icon: 1,
												offset: '50px'
											}, function () {
												reloadTable.reload()
											});
										} else if (msg.msg === "error") {
											layer.msg("解除绑定成功", {icon: 5});
										}

									},
									error: function () {
										layer.msg("服务器正忙", {icon: 5});
									}
								});
						});
					}
				});

			});


		</script>
	</div>
</div>
</body>
</html>
