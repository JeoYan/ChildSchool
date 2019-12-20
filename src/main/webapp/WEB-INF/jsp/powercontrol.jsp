<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/19
  Time: 2:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<title>教职工权限管理</title>

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
		<h2>教职工权限管理</h2>
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
			<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin"
			     style="padding: 20px 0 0 0;">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">用户名:</label>
						<div class="layui-input-inline">
							<input type="tel" name="phone"  class="layui-input" id="wName">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">角色:</label>
						<div class="layui-input-inline">
							<select name="modules"  lay-search="" id="rid">
								<option value="">请选择角色</option>
								<option value="1">教师</option>
								<option value="2">保健员</option>
								<option value="3">安防员</option>
							</select>
						</div>
					</div>

					<%--查询按钮--%>
					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-admin" id="serace" lay-submit
						        lay-filter="LAY-user-back-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
						</button>
					</div>

				</div>

			</div>
		</div>
	</div>


	<%--表主体--%>
	<div style="text-align: center">
		<div class="layui-inline" style="width:96%">
			<table id="demo" lay-filter="demo"></table>
		</div>
	</div>

	<div class="layui-trans layadmin-user-login-footer">

	</div>

</div>



<script type="text/html" id="barDemo">

	<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="edit">修改职工权限分配</a>

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
			, url: '${pageContext.request.contextPath}/power/getTeacherTable.action' //数据接口
			, page: true //开启分页
			, limit: 5
			, done: function () {
				$('.layui-laypage-limits').hide();
			}
			, id: 'demo'
			, cols: [[ //表头
				{type: 'numbers', title: '序号', align: 'center'},
				{field: 'wname', title: '职工', align: 'center'} ,
				{field: 'rname', title: '角色', align: 'center', sort: true} ,
				{field: 'wdate', title: '创建时间',align: 'center'},
				// {field: '', title: '菜单权限分配',align: 'center',templet:function(d){
				// 		var str="<div class='layui-btn-group demoTable'>";
				// 		 str += "<button type='button' class='layui-btn layui-btn-xs layui-btn-danger' onclick='myclick(this)'  value='"+d.wid+"'>修改职工权限分配</button>" ;
				// 		str +="</div>";
				// 		 return str;
				// 	}},

				{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'},
				{field: 'sid', title: '用户状态',align: 'center',templet:function(d){
						var status;
						if (d.sid === 1) {
							status = '正常';
						} else {
							status = '禁用';
						}
						return status;
					}},
				{field: '', title: '操作',align: 'center',templet:function(d){
					var str;
						if (d.sid === 1) {
							str = "<input type='checkbox' lay-filter='switch' value='"+d.wid+"' name='switch' lay-skin='switch' lay-text='启用|禁用'>" ;
						} else {
							str = "<input type='checkbox' lay-filter='switch' value='"+d.wid+"' name='switch' checked lay-skin='switch' lay-text='启用|禁用'>" ;
						}
						return str;
					}}
			]]
		});
		//重载表格
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

		//修改权限按钮
		table.on('tool(demo)', function (obj) {//注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
			var data = obj.data;//获得当前行数据
			var wid=data.wid;
			alert(wid);
			if (obj.event === 'edit') {
				layer.open({
					type: 2
					, title: '权限修改'
					, content: '${pageContext.request.contextPath}/power/callPowerPage.action'
					, maxmin: true
					, area: ['450px', '500px']
					, success: function (layero, index) {
						//向子窗口设置值
						var body = layer.getChildFrame('body', index);
						body.find("#hiddenWid").val(wid);
						body.find("#wName").html("姓名:"+data.wname);
						body.find("#rName").html("角色:"+data.rname);
					}

					// ,value: data.USERNAME
				});
			}

		});


		layui.config({}).extend({}).use(['table'], function () {
			var $ = layui.$
				, form = layui.form
				, table = layui.table;

			//监听搜索
			$('#serace').on('click', function () {

				var wName = $('#wName').val();
				var rid = $('#rid').val();

				table.reload('demo', {
					where: {
						'wName': wName,
						'rid': rid
					}, page: {
						curr: 1
					}
				})

			});


			$('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
		});
		//禁用启用
		layui.use('form', function () {
			var form = layui.form;
			form.on('switch', function (data) {
				var statusCode = 0;
				var status = data.elem.checked;
				if (status === true) {
					statusCode = 2;
				} else {
					statusCode = 1;
				}
				var wid = data.elem.value;
				$.ajax({
					async: false,//异步操作
					type: "POST",
					url: "${pageContext.request.contextPath}/power/updateStatus.action",//注意路径
					data: {"wid": wid, "statusCode": statusCode},
					dataType: "text",
					success: function (data) {
						alert(data);
						if (data === "Ok") {
							alert("-----修改成功成功------");
							layui.table.reload('demo');
						} else {
							alert("-----修改失败------");
						}
					},
					error: function (data) {
						alert("-----请检查网络------" + data);
					}
				});
			});
		});
	});

</script>


</body>
</html>

