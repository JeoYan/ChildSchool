<!DOCTYPE html>
<%--/**--%>
<%--* 接送管理---%>
<%--* by 陈超--%>
<%--*/--%>

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
	<title>接送信息</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1 ,user-scalable=0">
	<link rel="stylesheet" href=<%=uicssPath+"layui/css/layui.css"%> media="all">
	<%--	<link rel="stylesheet" href=<%=uicssPath+"style/admin.css"%>>--%>
	<script src=<%=uiPath+"layui/layui.js"%>></script>
	<script src=<%=JsPath+"jquery-3.4.1.js"%>></script>
	<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->


</head>
<body>
<div class="layui-row" id="lookBabySign" style="display:none;">
	<div style="text-align: center">
		<h1 style="text-align: center;">宝宝接送信息</h1>
		<div class="layui-form-item" style="text-align: center">
			<label class="layui-form-label" >宝宝姓名：</label>
			<label class="layui-form-label" id="showBName"></label>
			<label class="layui-form-label" >宝宝性别：</label>
			<label class="layui-form-label" id="showBSex"></label>
			<label class="layui-form-label" >班级名称：</label>
			<label class="layui-form-label" id="showCname"></label>
		</div>
	<table id="BabySignInfo" border="1px" >
		<tr id="dateTr" >
			<td>日期</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
		</tr>
		<tr id="aTr" >
			<td>上午</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
		</tr>
		<tr id="apeo" >
			<td>接送人</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
		</tr>
		<tr id="pTr" >
			<td>下午</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
		</tr>
		<tr id="ppeo" >
			<td>接送人</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
		</tr>

	</table >
		<button class="layui-btn" id="pre" >上一周</button>
		<button class="layui-btn" id="next">下一周</button>
	</div>
</div>

<div class="demoTable">
	<h1 style="text-align: center">接送信息</h1>
	<div >
		查询条件:
	</div>

	<div style="text-align: center">
		<form class="layui-form" action="" onsubmit="return false">

				宝宝名称:
				<div class="layui-inline">
					<input class="layui-input" name="bname" id="demoReload" autocomplete="off">
				</div>

				班级名称:

					<div class="layui-inline">
						<label class="layui-form-label">班级名称</label>
						<div class="layui-input-inline">
							<select name="cname" id="demoReload1">
								<option value=""></option>


								<c:forEach items="${requestScope.cname}" begin="0" step="1" var="y">
									<option value="${y.cid}">${y.cname}</option>
								</c:forEach>

							</select>
						</div>
					</div>

		<button class="layui-btn" data-type="reload" id="search">查询</button>
<%--			<button class="layui-btn layui-btn-normal" data-type="add" >考勤</button>--%>
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
<%--		<a class="layui-btn layui-btn-xs" lay-event="attend">考勤</a>--%>
		<button class="layui-btn layui-btn-sm" lay-event="teacherSign">接收信息</button>
	</div>
</script>


<script src="<%=uiPath+"layui/layui.js"%>" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
	layui.use(['layer','table'], function() {
		var table = layui.table
			, layer = layui.layer;
		var object;
		var data;
		var $ = layui.$;

		table.render({
			elem: '#LAY_table_user',
			filter: 'test',
			height: 350,
			url: '/ChildSchool/pickupxs.action',
			page: 1,
			limit: 5,
			id: 'testReload',
			cellMinWidth: 80,
			limits: [5, 10, 20, 40, 100],
			cols: [[
				{type: 'numbers', title: '序号'} ,
				{field: 'bid', title: '宝宝编号', hide: true,sort: true, fixed: 'center'},
				{field: 'bname', title: '宝宝名称'},
				{field: 'cname', title: '所在班级', sort: true},
				{field: 'wid', title: '教师编号', hide: true,sort: true, fixed: 'center'},
				{field: 'wname', title: '教师名称'},
				{field: 'bsex', title: '性别'},
				{fixed: 'right', title: '操作', toolbar: '#barDemo'}
			]]

		});



		//监听行工具事件
		table.on('tool(test)', function (obj) {
			object = obj;
			data = obj.data;
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			if (layEvent === 'teacherSign') {
				alert(data.bname);
				alert(data.bsex);
				alert(data.cname);
				var bid = data.bid;

				var $dateTds = $("#dateTr").find("td");
				var $aTds = $("#aTr").find("td");
				var $apeo = $("#apeo").find("td");
				var $pTds = $("#pTr").find("td");
				var $ppeo = $("#ppeo").find("td");
				for (var i = 1; i < $aTds.length; i++) {
					$aTds.eq(i).text("");
				}
				for (var j = 1; j < $pTds.length ; j++) {
					$pTds.eq(j).text("");
				}
				for (var a = 1; a < $apeo.length; a++) {
					$apeo.eq(a).text("");
				}
				for (var b = 1; b < $ppeo.length; b++) {
					$ppeo.eq(b).text("");
				}
				$.ajax({
					type: "POST",//提交方式
					url: "/ChildSchool/babypickupSign.action",//路径
					data: {'bid': bid},//数据
					dataType: "json",//希望返回的数据类型
					async: true,//异步操作
					success: function (BabySignInfo) {//成功的方法 msg为返回数据
						$("#showBName").text(data.bname);
						$("#showBSex").text(data.bsex);
						$("#showCname").text(data.cname);
						var dateString = BabySignInfo["dateString"];
						var babySignList = BabySignInfo["babySignList"];
						alert(dateString);
						alert(babySignList);

						for (var i = 1; i < $dateTds.length; i++) {
							$dateTds.eq(i).text(dateString[i - 1]);
						}
						for (var j = 0; j < babySignList.length; j++) {
							for (var k = 1; k < $dateTds.length; k++) {
								if (babySignList[j].bsdate === $dateTds.eq(k).text()) {

									if (babySignList[j].bsperiod === "上午") {
										if (babySignList[j].bstime !== null) {

											$aTds.eq(k).text(babySignList[j].bstime);
											$apeo.eq(k).text(babySignList[j].pname);

										} else {
											$aTds.eq(k).text(babySignList[j].sname);
										}
									}
										else {
										if (babySignList[j].bstime !== null) {
											$pTds.eq(k).text(babySignList[j].bstime);
											$ppeo.eq(k).text(babySignList[j].pname);
										} else {
											$pTds.eq(k).text(babySignList[j].sname);
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
					type: 1,
					title: "教师考勤信息",
					area: ['55%', '60%'],
					content: $("#lookBabySign")
				});
			}
			else if(obj.event === 'attend'){
				alert(data.bname);
				alert(data.bid);

				layer.open({
					type: 2,
					title: '考勤',
					content: '/ChildSchool/face.action',
					maxmin: true,
					area: ['500px', '500px'],
					btn: ['关闭'],
					success: function (layero,index) {
						var body = layer.getChildFrame('body', index);
						body.find("#bid").val(data.bid);



					},
					yes:function (index,layero) {
						layer.close(index);

					}
				})

			}
		});

		var active = {
			//查询
			reload: function () {
				var demoReload = $('#demoReload');
				var demoReload1 = $("#demoReload1").find("option:selected").text();

				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					, where: {

						'bname': demoReload.val(),
						'cname': demoReload1
					}
				}, 'data');

			}

		};
		$("#pre").on('click',function () {

			var bid = data.bid;
			var $dateTds = $("#dateTr").find("td");
			var $aTds = $("#aTr").find("td");
			var $apeo = $("#apeo").find("td");
			var $pTds = $("#pTr").find("td");
			var $ppeo = $("#ppeo").find("td");
			for (var i = 1; i < $aTds.length; i++) {
				$aTds.eq(i).text("");
			}
			for (var j = 1; j < $pTds.length ; j++) {
				$pTds.eq(j).text("");
			}
			for (var a = 1; a < $apeo.length; a++) {
				$apeo.eq(a).text("");
			}
			for (var b = 1; b < $ppeo.length; b++) {
				$ppeo.eq(b).text("");
			}
			var ob={'bid': bid,'flag':'pre','thisDate':$dateTds.eq(1).text()};
			$.ajax({
				type: "POST",//提交方式
				url: "/ChildSchool/babypickupSign.action",//路径
				data: ob,//数据
				dataType: "json",//希望返回的数据类型
				async: true,//异步操作
				success: function (BabySignInfo) {//成功的方法 msg为返回数据

					var dateString = BabySignInfo["dateString"];
					var babySignList = BabySignInfo["babySignList"];

					for (var i = 1; i < $dateTds.length; i++) {
						$dateTds.eq(i).text(dateString[i - 1]);
					}
					for (var j = 0; j < babySignList.length; j++) {
						for (var k = 1; k < $dateTds.length; k++) {
							if (babySignList[j].bsdate === $dateTds.eq(k).text()) {

								if (babySignList[j].bsperiod === "上午") {
									if (babySignList[j].bstime !== null) {

										$aTds.eq(k).text(babySignList[j].bstime);
										$apeo.eq(k).text(babySignList[j].pname);
									} else {
										$aTds.eq(k).text(babySignList[j].sname);
									}
								}
								else {
									if (babySignList[j].bstime !== null) {
										$pTds.eq(k).text(babySignList[j].bstime);
										$ppeo.eq(k).text(babySignList[j].pname);
									} else {
										$pTds.eq(k).text(babySignList[j].sname);
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
		});

		$("#next").on('click',function () {
			var bid = data.bid;

			var $dateTds = $("#dateTr").find("td");
			var $aTds = $("#aTr").find("td");
			var $apeo = $("#apeo").find("td");
			var $pTds = $("#pTr").find("td");
			var $ppeo = $("#ppeo").find("td");
			for (var i = 1; i < $aTds.length; i++) {
				$aTds.eq(i).text("");
			}
			for (var j = 1; j < $pTds.length ; j++) {
				$pTds.eq(j).text("");
			}
			for (var a = 1; a < $apeo.length; a++) {
				$apeo.eq(a).text("");
			}
			for (var b = 1; b < $ppeo.length; b++) {
				$ppeo.eq(b).text("");
			}
			var ob={'bid': bid,'flag':'next','thisDate':$dateTds.eq(1).text()};
			$.ajax({
				type: "POST",//提交方式
				url: "/ChildSchool/babypickupSign.action",//路径
				data: ob,//数据
				dataType: "json",//希望返回的数据类型
				async: true,//异步操作
				success: function (BabySignInfo) {//成功的方法 msg为返回数据

					var dateString = BabySignInfo["dateString"];
					var babySignList = BabySignInfo["babySignList"];

					for (var i = 1; i < $dateTds.length; i++) {
						$dateTds.eq(i).text(dateString[i - 1]);
					}
					for (var j = 0; j < babySignList.length; j++) {
						for (var k = 1; k < $dateTds.length; k++) {
							if (babySignList[j].bsdate === $dateTds.eq(k).text()) {

								if (babySignList[j].bsperiod === "上午") {
									if (babySignList[j].bstime !== null) {

										$aTds.eq(k).text(babySignList[j].bstime);
										$apeo.eq(k).text(babySignList[j].pname);
									} else {
										$aTds.eq(k).text(babySignList[j].sname);
									}
								}
								else {
									if (babySignList[j].bstime !== null) {
										$pTds.eq(k).text(babySignList[j].bstime);
										$ppeo.eq(k).text(babySignList[j].pname);
									} else {
										$pTds.eq(k).text(babySignList[j].sname);
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
		});

		$('.demoTable .layui-btn').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});

	});

</script>




</html>