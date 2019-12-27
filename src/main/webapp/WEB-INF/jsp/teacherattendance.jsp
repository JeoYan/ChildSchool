<!DOCTYPE html>
<%--/**--%>
<%--* 教师考勤---%>
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
	<title>教师考勤</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1 ,user-scalable=0">
	<link rel="stylesheet" href=<%=uicssPath+"layui/css/layui.css"%>>
	<%--	<link rel="stylesheet" href=<%=uicssPath+"style/admin.css"%>>--%>
	<script src=<%=uiPath+"layui/layui.js"%>></script>
	<script src=<%=JsPath+"jquery-3.4.1.js"%>></script>
	<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<div class="layui-row" id="lookTeacherSign" style="display:none;">
	<table id="teacherSignInfo" border="1px">
		<tr id="dateTr">
			<td>日期</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
		</tr>
		<tr id="aTr">
			<td>上午</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
		</tr>
		<tr id="mTr">
			<td>中午</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
		</tr>
		<tr id="pTr">
			<td>下午</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
		</tr>
	</table>
</div>

<div class="demoTable">
	<h1 style="text-align: center">教师考勤</h1>


	<div style="text-align: center">

		园所名称:英才幼儿园
	</div>

	<div style="text-align: center">
		<form class="layui-form" action="" onsubmit="return false">
		教师名称:
		<div class="layui-inline">
			<input class="layui-input" name="wname" id="demoReload" autocomplete="off">
		</div>

		<div class="layui-inline">
			<label class="layui-form-label">角色选择</label>
			<div class="layui-input-inline" >
				<select name="rname" id="demoReload1">
					<option value=""></option>

					<c:forEach items="${requestScope.role}" begin="0" step="1" var="y">
						<option value="${y.rid}">${y.rname}</option>
					</c:forEach>

				</select>
			</div>
		</div>

		<button class="layui-btn" data-type="reload" id="search">查询</button>
<%--		<button class="layui-btn layui-btn-normal" data-type="add" >新增</button>--%>
		</form>
	</div>
	<div style="text-align: center">
		<div class="layui-inline" style="width:96%">
			<table class="layui-hide" lay-filter="test" id="LAY_table_user" ></table>

		</div>
		</div>
</div>


</body>
<script type="text/html" id="barDemo">
	<div class="layui-btn-container" >
		<button class="layui-btn layui-btn-sm" lay-event="teacherSign">考勤信息</button>
	</div>
</script>


<script src="<%=uiPath+"layui/layui.js"%>" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
	layui.use(['layer','table'], function(){
		var table = layui.table
			,layer =layui.layer;
		var object;
		table.render({
			elem: '#LAY_table_user',
			filter:'test',
			height: 350 ,
			url: '/ChildSchool/attendancexs.action' ,
			page: 1,
			limit:5,
			id: 'testReload',
			cellMinWidth: 80,
			limits:[5,10,20,40,100],
			cols: [[
				{field: 'wid', title: '教师编号', sort: true, fixed: 'center'}      ,
				{field: 'wname', title: '教师名称'} ,
				{field: 'rname', title: '教师角色',  sort: true} ,
				{field: 'rid', title: '角色id', sort: true,hide:true} ,

				{fixed: 'right', title:'操作', toolbar: '#barDemo'}
			]]

		});

		//监听行工具事件
		table.on('tool(test)', function(obj){
			object =obj;
			var data = obj.data;
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		 if(layEvent === 'teacherSign'){
				alert(data.wname);
				alert(data.wid);
				var wid = data.wid;
				$.ajax({
							type: "POST",//提交方式
							url: "/ChildSchool/teacherSign.action",//路径
							data: {'wid':wid},//数据
							dataType: "json",//希望返回的数据类型
							async: true,//异步操作
							success: function (teacherSignInfo) {//成功的方法 msg为返回数据
								var dateString =teacherSignInfo["dateString"];
								var teacherSignList = teacherSignInfo["teacherSignList"];
								var $dateTds = $("#dateTr").find("td");
								var $aTds = $("#aTr").find("td");
								var $mTds = $("#mTr").find("td");
								var $pTds = $("#pTr").find("td");
								for (var i = 1; i < $dateTds.length; i++) {
									$dateTds.eq(i).text(dateString[i-1]);
								}
								for (var j = 0; i < teacherSignList.length; j++) {
									for (var k = 1; k < $dateTds.length; k++) {
										if (teacherSignList[j].wsdate === $dateTds.eq(k).text()) {
											if (teacherSignList[j].wsperiod === "上午") {
												if (teacherSignList[j].wstime !== null) {
													$aTds.eq(k).text(teacherSignList[j].wstime);
												} else {
													$aTds.eq(k).text(teacherSignList[j].sname);
												}
											} else if (teacherSignList[j].wsperiod === "中午") {
												if (teacherSignList[j].wstime !== null) {
													$mTds.eq(k).text(teacherSignList[j].wstime);
												} else {
													$mTds.eq(k).text(teacherSignList[j].sname);
												}
											}else  {
												if (teacherSignList[j].wstime !== null) {
													$pTds.eq(k).text(teacherSignList[j].wstime);
												} else {
													$pTds.eq(k).text(teacherSignList[j].sname);
												}
											}
										}
									}
								}

							},
							error: function () { //错误的方法
								alert("服务器正忙");
								layer.msg("数据传输失败");
							}
						});
			 layer.open({
				 type:1,
				 title:"教师考勤信息",
				 area: ['100%','100%'],
				 content: $("#lookTeacherSign")
			 });
					}

		});

		var $ = layui.$, active = {
			//查询
			reload: function(){
				var demoReload = $('#demoReload');
				var demoReload1 =  $("#demoReload1").find("option:selected").text();

				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					,where: {

						'wname': demoReload.val(),
						'rname': demoReload1
					}
				}, 'data');

			}


		};


		$('#search').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});


	});
</script>




</html>