<%--
  Created by IntelliJ IDEA.
  User: 陈美杰
  Date: 2019/12/17
  Time: 14:48
  用来显示班级考勤信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>班级考勤信息</title>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="../../layuiadmin/layui/css/layui.css" media="all">
</head>
<body>
<div class="layui-row" id="lookBabySign" style="display:none;">
	<div>
		<h1>宝宝考勤</h1>
		<div class="layui-form-item">
			<label class="layui-form-label">宝宝姓名：</label>
			<label class="layui-form-label" id="showBabyName"></label>
			<label class="layui-form-label">宝宝性别：</label>
			<label class="layui-form-label" id="showBabySex"></label>
		</div>
		<table id="babySignInfo" border="1px">
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
		<button class="layui-btn" id="pre">上一周</button>
		<button class="layui-btn" id="next">下一周</button>
	</div>
</div>

<div class="layui-container" id="box">
	<h1>班级考勤信息</h1>
	<div class="demoTable">
		<div>班级名称：英才小（1）班</div>
		宝宝名称：
		<div class="layui-inline">
			<input  class="layui-input" name="babyName" id="babyName" autocomplete="off">
		</div>
		<button class="layui-btn" id="query" data-type="reload">搜索</button>
	</div>
	<div style="text-align: center">
		<div class="layui-inline" style="width:96%">
			<table class="layui-hide" lay-filter="test" id="LAY_table_user" ></table>
		</div>
	</div>

</div>
</body>
<script type="text/html" id="toolbarDemo">
	<div class="layui-btn-container">
		<a class="layui-btn layui-btn-xs" lay-event="attend">考勤</a>
		<button class="layui-btn layui-btn-sm" lay-event="babySign">考勤信息</button>
	</div>
</script>
<script src="../../layuiadmin/layui/layui.js" charset="UTF-8"></script>
<script>
	layui.use(['layer','table'], function(){
		var table = layui.table;
		var layer = layui.layer;
		var data;
		var $ = layui.$;
		var object;
		//方法级渲染
		table.render({
			elem: '#LAY_table_user'
			,filter:'test'
			,url: '/ChildSchool/ClassSignController/classSign'
			,cols: [[
				{field: 'bid', title: '宝宝编号',align: 'center'}
				,{field: 'bName', title: '宝宝名称',align: 'center'}
				,{field: 'pName', title: '家长名称',align: 'center', sort: true}
				,{field: 'bSex', title: '性别'}
				,{title:'操作', toolbar: '#toolbarDemo',align: 'center'}
			]]
			// ,cellMinWidth: 80
			,limits:[5,10,20,40,100]
			,limit:5
			,id: 'testReload'
			,page: true
			,height: 350
		});

		//监听事件
		table.on('tool(test)', function(obj){
			object = obj;
			data = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			if(layEvent==='babySign'){
				var bid = data.bid;
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
					data:{'bid':bid},
					dataType:'json',
					async:true,
					success:function (babySignInfo) {
						$("#showBabyName").text(data.bName);
						$("#showBabySex").text(data.bSex);
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

				layer.open({
					type:1,
					title:"宝宝考勤",
					area: ['50%','70%'],
					content: $("#lookBabySign")
				});
			}
			else if(obj.event === 'attend'){
				alert(data.bname);
				alert(data.bid);

				layer.open({
					type: 2,
					title: '考勤',
					content: '/ChildSchool/babyface.action',
					maxmin: true,
					area: ['500px', '500px'],
					btn: ['关闭'],
					success: function (layero,index) {
						var body = layer.getChildFrame('body', index);
						body.find("#bid").val(data.bid);



					},
					yes:function (index,layero) {
						layer.close(index);
						// var wid = data.wid;
						// var ob = {wid: wid};
						// layer.close(index);
						// $.ajax({
						// 	type: "POST",//提交方式
						// 	url: "/ChildSchool/facelogin.action0",//路径
						// 	data: ob,//数据
						// 	dataType: "json",//希望返回的数据类型
						// 	async: true,//异步操作
						// 	success: function (msg) {//成功的方法 msg为返回数据
						//
						// 		if (msg.msg == "1") {
						// 			alert("修改成功");
						// 			table.reload('test');
						// 			layer.close(index);
						// 		} else if (msg.msg == "2") {
						// 			alert("修改失败")
						// 		}
						//
						// 	},
						// 	error: function () { //错误的方法
						// 		alert("服务器正忙")
						// 	}
						// });
					}
				})

			}
		});




		var active = {
			reload: function(){
				var babyName = $('#babyName');
				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					,where: {
						'babyName':babyName.val(),
					}
				}, 'data');
			}
		};

		$("#pre").on('click',function () {
			var bid = data.bid;
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
				data:{'bid':bid,'flag':'pre','thisDate':$dateTds.eq(1).text()},
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
			var bid = data.bid;
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
				data:{'bid':bid,'flag':'next','thisDate':$dateTds.eq(1).text()},
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

		$('#query').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});


	});

</script>
</html>
