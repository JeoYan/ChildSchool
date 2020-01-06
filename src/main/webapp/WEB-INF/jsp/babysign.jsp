<%--
  Created by IntelliJ IDEA.
  User: 陈美杰
  Date: 2019/12/24
  Time: 16:57
  显示宝宝考勤信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>宝宝考勤信息</title>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="../../layuiadmin/layui/css/layui.css" media="all">
</head>
<body>
<div class="layui-row" style="width: 100%" id="lookBabySign">
	<div style="width: 80%; margin: 0 auto">
		<div style="width: 96%; margin: 0 auto">
			<h1 style="width: 20%; margin: 0 auto">宝宝考勤</h1>
			<div class="layui-form-item" style="width: 30%;margin: 0 auto">
				<label class="layui-form-label">宝宝姓名：</label>
				<label class="layui-form-label" id="showBabyName"></label>
				<label class="layui-form-label">宝宝性别：</label>
				<label class="layui-form-label" id="showBabySex"></label>
			</div>
		</div>


		<table class="layui-table" id="babySignInfo">
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
			<tr id="pTr">
				<td>下午</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
			</tr>
		</table>
		<div style="width: 20%; margin: 0 auto">
			<button class="layui-btn" id="pre">上一周</button>
			<button class="layui-btn" id="next">下一周</button>
		</div>
	</div>
</div>
</body>
<script src="../../layuiadmin/layui/layui.js" charset="UTF-8"></script>
<script>
	layui.use(['layer','table'], function(){
		var table = layui.table;
		var layer = layui.layer;
		var data;
		var $ = layui.$;
		var object;
		$(function () {
			var $dateTds = $("#dateTr").find("td");
			var $aTds = $("#aTr").find("td");
			var $pTds = $("#pTr").find("td");
			for (var i = 1; i < $aTds.length; i++) {
				$aTds.eq(i).text("");
			}
			for (var j = 1; j < $pTds.length ; j++) {
				$pTds.eq(j).text("");
			}
			$.ajax({
				type:'post',
				url:'/ChildSchool/ClassSignController/queryBabySign',
				data:{'bid':1},
				dataType:'json',
				async:true,
				success:function (babySignInfo) {
					$("#showBabyName").text("小一");
					$("#showBabySex").text("男");
					var dateString = babySignInfo["dateString"];
					var babySignList = babySignInfo["babySignList"];
					for (var i = 1; i < $dateTds.length; i++) {
						$dateTds.eq(i).text(dateString[i-1]);
					}

					for (var j = 0; j <babySignList.length ; j++) {
						for (var k = 1; k < $dateTds.length; k++) {
							if (babySignList[j].bsDate === $dateTds.eq(k).text()) {
								if (babySignList[j].bsPeriod === "上午") {
									if (babySignList[j].bsTime !==null){
										$aTds.eq(k).text(babySignList[j].bsTime);
									} else {
										$aTds.eq(k).text(babySignList[j].sName);
									}
								}else {
									if (babySignList[j].bsTime !==null){
										$pTds.eq(k).text(babySignList[j].bsTime);
									} else {
										$pTds.eq(k).text(babySignList[j].sName);
									}
								}
							}
						}
					}
				},
				error:function () {
					layer.msg("数据传输失败");
				}
			});
		});

		$("#pre").on('click',function () {
			var $dateTds = $("#dateTr").find("td");
			var $aTds = $("#aTr").find("td");
			var $pTds = $("#pTr").find("td");
			for (var i = 1; i < $aTds.length; i++) {
				$aTds.eq(i).text("");
			}
			for (var j = 1; j < $pTds.length ; j++) {
				$pTds.eq(j).text("");
			}
			$.ajax({
				type:'post',
				url:'/ChildSchool/ClassSignController/queryBabySign',
				data:{'bid':1,'flag':'pre','thisDate':$dateTds.eq(1).text()},
				dataType:'json',
				async:true,
				success:function (babySignInfo) {
					var dateString = babySignInfo["dateString"];
					var babySignList = babySignInfo["babySignList"];

					for (var i = 1; i < $dateTds.length; i++) {
						$dateTds.eq(i).text(dateString[i-1]);
					}

					for (var j = 0; j <babySignList.length ; j++) {
						for (var k = 1; k < $dateTds.length; k++) {
							if (babySignList[j].bsDate === $dateTds.eq(k).text()) {
								if (babySignList[j].bsPeriod === "上午") {
									if (babySignList[j].bsTime !==null){
										$aTds.eq(k).text(babySignList[j].bsTime);
									} else {
										$aTds.eq(k).text(babySignList[j].sName);
									}
								}else {
									if (babySignList[j].bsTime !==null){
										$pTds.eq(k).text(babySignList[j].bsTime);
									} else {
										$pTds.eq(k).text(babySignList[j].sName);
									}
								}
							}
						}
					}
				},
				error:function () {
					layer.msg("数据传输失败");
				}
			});
		});

		$("#next").on('click',function () {
			var $dateTds = $("#dateTr").find("td");
			var $aTds = $("#aTr").find("td");
			var $pTds = $("#pTr").find("td");
			for (var i = 1; i < $aTds.length; i++) {
				$aTds.eq(i).text("");
			}
			for (var j = 1; j < $pTds.length ; j++) {
				$pTds.eq(j).text("");
			}
			$.ajax({
				type:'post',
				url:'/ChildSchool/ClassSignController/queryBabySign',
				data:{'bid':1,'flag':'next','thisDate':$dateTds.eq(1).text()},
				dataType:'json',
				async:true,
				success:function (babySignInfo) {
					var dateString = babySignInfo["dateString"];
					var babySignList = babySignInfo["babySignList"];
					for (var i = 1; i < $dateTds.length; i++) {
						$dateTds.eq(i).text(dateString[i-1]);
					}

					for (var j = 0; j <babySignList.length ; j++) {
						for (var k = 1; k < $dateTds.length; k++) {
							if (babySignList[j].bsDate === $dateTds.eq(k).text()) {
								if (babySignList[j].bsPeriod === "上午") {
									if (babySignList[j].bsTime !==null){
										$aTds.eq(k).text(babySignList[j].bsTime);
									} else {
										$aTds.eq(k).text(babySignList[j].sName);
									}
								}else {
									if (babySignList[j].bsTime !==null){
										$pTds.eq(k).text(babySignList[j].bsTime);
									} else {
										$pTds.eq(k).text(babySignList[j].sName);
									}
								}
							}
						}
					}
				},
				error:function () {
					layer.msg("数据传输失败");
				}
			});
		});
	});

</script>
</html>
