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
	String jsPath = request.getContextPath() + "/js/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>电子围栏</title>
	<link rel="stylesheet" href=<%=layuiPath+"css/layui.css"%>>
	<script  src=<%=layuiPath + "layui.js"%>></script>
	<script src=<%=jsPath + "jquery-3.4.1.js"%>></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="//api.map.baidu.com/api?v=1.2"></script>
	<title>多个标注点沿折线的轨迹运动</title>
</head>
<body>
<div>
	<h1 style="text-align: center">
		电子围栏
	</h1>
	<div>
		<h2 style="text-align: center">
			幼儿园名称：厦门智慧幼儿园
		</h2>
	</div>
	<div>
		<h2 style="text-align: center" id="today">日期：
<%--			<script type="text/javascript">--%>

<%--				function getTime(){--%>

<%--					var date = new Date(); //创建时间对象--%>

<%--					var year = date.getFullYear(); //获取年--%>

<%--					var month = date.getMonth()+1;//获取月--%>

<%--					var day = date.getDate(); //获取当日--%>

<%--					var time = year+"-"+month+"-"+day; //组合时间--%>
<%--					--%>
<%--					alert("当前日期："+time);--%>

<%--				}--%>
<%--			</script>--%>
		</h2>
		<button class="layui-btn" data-type="findWarning">查询</button>
	</div>
</div>
<div id="allmap" ></div>
</body>
</html>
<script type="text/javascript">
	//查询
	function getTime(){

		var date = new Date(); //创建时间对象

		var year = date.getFullYear(); //获取年

		var month = date.getMonth()+1;//获取月

		var day = date.getDate(); //获取当日

		var today ="日期："+ year+"-"+month+"-"+day; //组合时间

		$("#today")[0].innerHTML=today;

	}
	getTime();
	layui.use('table', function () {
		var layer = layui.layer;
		var active = {
			findWarning: function () {
			layer.open({
				type: 2
				, title: '报警信息'
				, offset: 'auto'
				, content: '/ChildSchool/web/warntable.action'
				, area: ['888px', '550px']
				, btn: ['关闭']
				, shade: 0
			});
		}

	};
		$('.layui-btn').on('click', function () {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});





	// 百度地图API功能

	var map = new BMap.Map("allmap");
	map.centerAndZoom(new BMap.Point(118.193199,24.488834), 19);  // 初始化地图,设置中心点坐标和地图级别
	map.clearOverlays();// 清空覆盖物
	// var pStart = new BMap.Point(118.192795, 24.488966);
	// var pEnd = new BMap.Point(118.193442, 24.4883);
	var pStart = new BMap.Point(118.192058, 24.489451);
	var pEnd = new BMap.Point(118.193572, 24.488028);
	var polygon = new BMap.Polygon([
		new BMap.Point(pStart.lng,pStart.lat),
		new BMap.Point(pEnd.lng,pStart.lat),
		new BMap.Point(pEnd.lng,pEnd.lat),
		new BMap.Point(pStart.lng,pEnd.lat)
	], {strokeColor:"blue", strokeWeight:6, strokeOpacity:0.5});
	map.addOverlay(polygon);

	var items =[];
	var labels=['宝宝1','宝宝2','宝宝3','宝宝4'];
	var bounds = null;
	var linesPoints = null;
	var myIcon = new BMap.Icon("http://lbsyun.baidu.com/jsdemo/img/Mario.png", new BMap.Size(32, 70), {imageOffset: new BMap.Size(0, 0)});
	function initLine(){
		bounds = new Array();
		linesPoints = new Array();

		// var spoi1 = new BMap.Point(118.193199,24.488513);    // 起点1
		// var spoi2 = new BMap.Point(118.193199,24.488513);    // 起点2
		// var spoi3 = new BMap.Point(118.193199,24.488513);    // 起点3
		// var epoi1  = new BMap.Point(118.192471,24.489056);    // 终点
		// var epoi2  = new BMap.Point(118.193001,24.488152);    // 终点
		// var epoi3  = new BMap.Point(118.19346,24.488637);    // 终点
		var spoi1 = new BMap.Point(118.193199,24.488513);    // 起点1
		var spoi2 = new BMap.Point(118.193199,24.488513);    // 起点2
		var spoi3 = new BMap.Point(118.193199,24.488513);    // 起点3
		var spoi4 = new BMap.Point(118.193199,24.488513);    // 起点3
		var epoi1  = new BMap.Point(118.19191, 24.489114);    // 终点1
		var epoi2  = new BMap.Point(118.193707, 24.488559);    // 终点2
		var epoi3  = new BMap.Point(118.19231, 24.488259);    // 终点3
		var epoi4  = new BMap.Point(118.193123, 24.488998);    // 终点4

			// var driving3 = new BMap.DrivingRoute(map,{onSearchComplete:drawLine});  // 驾车实例,并设置回调
			// driving3.search(spoi1, epoi1);                                       // 搜索一条线路
			// var driving4 = new BMap.DrivingRoute(map,{onSearchComplete:drawLine});  // 驾车实例,并设置回调
			// driving4.search(spoi2, epoi2);                                       // 搜索一条线路
			// var driving5 = new BMap.DrivingRoute(map,{onSearchComplete:drawLine});  // 驾车实例,并设置回调
			// driving5.search(spoi3, epoi3);                                       // 搜索一条线路

		var driving1 = new BMap.DrivingRoute(map,{onSearchComplete:drawLine});  // 驾车实例,并设置回调
		driving1.search(spoi1, epoi1);                                       // 搜索一条线路
		var driving2 = new BMap.DrivingRoute(map,{onSearchComplete:drawLine});  // 驾车实例,并设置回调
		driving2.search(spoi2, epoi2);                                       // 搜索一条线路
		var driving3 = new BMap.DrivingRoute(map,{onSearchComplete:drawLine});  // 驾车实例,并设置回调
		driving3.search(spoi3, epoi3);
		// 搜索一条线路
		var driving4 = new BMap.DrivingRoute(map,{onSearchComplete:drawLine});  // 驾车实例,并设置回调
		driving4.search(spoi4, epoi4);

		setTimeout(function(){
			run();
		},1500);
		// for (var i = 0; i < 3; i ++) {
		//
		// 			var spoi = new BMap.Point(data[i].sPoiLng,data[i].sPoiLat);
		// 			var point = new BMap.Point(data[i].ePoiLng,data[i].ePoiLat);
		//
		// 			var driving37 = new BMap.DrivingRoute(map,{onSearchComplete:drawLine});  // 驾车实例,并设置回调
		// 			driving37.search(spoi, point);                                       // 搜索一条线路
		// 			items.push(data[i]);
		//
		//
		// 		}
		// 		setTimeout(function(){
		// 			run(items);
		// 		},1500);

		// $.ajax({
		// 	url:"/ChildSchool/BackAction/fenceBaby.action",
		// 	// data: ob,
		// 	type:"Post",
		// 	dataType:"json",
		// 	// async: true,
		// 	success: function (data) {
		//
		// 		for (var i = 0; i < 3; i ++) {
		//
		// 			var spoi = new BMap.Point(data[i].sPoiLng,data[i].sPoiLat);
		// 			var point = new BMap.Point(data[i].ePoiLng,data[i].ePoiLat);
		//
		// 			var driving37 = new BMap.DrivingRoute(map,{onSearchComplete:drawLine});  // 驾车实例,并设置回调
		// 			driving37.search(spoi, point);                                       // 搜索一条线路
		// 			items.push(data[i]);
		//
		//
		// 		}
		// 		setTimeout(function(){
		// 			run(items);
		// 		},1500);
		// 	},
		// 	error:function( ){
		// 		alert("服务器正忙");
		// 	}
		// });

	}

	// var change=true;
	function run(){
		// for(var m = 0;m < linesPoints.length; m++){
		// 	var pts = linesPoints[m];
		// 	var len = pts.length;
		// 	var flag=true;
		// 	var carMk = new BMap.Marker(pts[0],{icon:myIcon});
		// 	var label = new BMap.Label(items[m].bName,{offset:new BMap.Size(20,-10)});
		// 	carMk.setLabel(label);
		// 	carMk.setTitle(items[m].bid);
		// 	map.addOverlay(carMk);
		// 	resetMkPoint(1,len,pts,carMk,flag)
		// }



		for(var m = 0;m < linesPoints.length; m++){
			var pts = linesPoints[m];
			var len = pts.length;
			var flag=true;
			var carMk = new BMap.Marker(pts[0],{icon:myIcon});
			var label = new BMap.Label(labels[m],{offset:new BMap.Size(20,-10)});
			carMk.setLabel(label);
			carMk.setTitle(m+2);
			map.addOverlay(carMk);
			resetMkPoint(1,len,pts,carMk,flag)
		}



		function resetMkPoint(i,len,pts,carMk,flag) {
			var ob;
			var bid = JSON.parse(carMk.getTitle());
			var flag2 = false;
			if (pts[i].lng < pStart.lng && pts[i].lat < pStart.lat && pts[i].lat > pEnd.lat) {
				ob = {"bid": bid, "area": "西门"};
				flag2 = true;
			} else if (pts[i].lng > pEnd.lng && pts[i].lat < pStart.lat && pts[i].lat > pEnd.lat) {
				ob = {"bid": bid, "area": "东门"};
				flag2 = true;
			} else if (pts[i].lng > pStart.lng && pts[i].lng < pEnd.lng && pts[i].lat > pStart.lat) {
				ob = {"bid": bid, "area": "北门"};
				flag2 = true;
			} else if (pts[i].lng > pStart.lng && pts[i].lng < pEnd.lng && pts[i].lat < pEnd.lat) {
				ob = {"bid": bid, "area": "南门"};
				flag2 = true;
			}
			if (flag && flag2) {
				$.ajax({
					type: "POST",//提交的方式
					url: "/ChildSchool/BackAction/addWarning.action",
					data: ob,//提交的数据
					dataType: "json",//希望返回的数据类型
					success: function (msg) {//成功的方法  msg为返回数据
						if (msg.msg === "1") {
							console.log("报警添加成功");
						} else if (msg.msg === "0") {
							console.log("报警添加失败");
						}
						flag = false;
					},
					error: function () {//错误的方法
						alert("服务器正忙")
					}
				});
			}
			carMk.setPosition(pts[i]);
			if (i < len - 1) {
				setTimeout(function () {
					i++;
					resetMkPoint(i, len, pts, carMk, flag);
					// console.log(flag+carMk.getTitle());
				}, 1000);
			}
		}
		// 	if (change) {
		// 		setTimeout(function(){
		// 			if (i < len-1) {
		// 				i++;
		// 				console.log(flag+carMk.getTitle());
		// 				resetMkPoint(i,len,pts,carMk,flag);
		// 			}else {
		// 				change=false;
		// 			}
		//
		//
		// 		},500);
		// 	}
		// 	if (!change) {
		// 		setTimeout(function(){
		// 			if (i >1) {
		// 				i--;
		// 				console.log(flag+carMk.getTitle());
		// 				resetMkPoint(i,len,pts,carMk,flag);
		// 			}else {
		// 				change=true;
		// 			}
		//
		// 		},500);
		// 	}
		// }
		// carMk.setPosition(pts[i]);
		// if(i < len-1){
		// 	setTimeout(function(){
		// 		i++;
		// 		resetMkPoint(i,len,pts,carMk,flag);
		// 		console.log(flag+carMk.getTitle());
		// 	},500);
		// }

	}



	// function run(items){
	//
	// 	for(var m = 0;m < linesPoints.length; m++){
	// 		var pts = linesPoints[m];
	// 		var len = pts.length;
	// 		var carMk = new BMap.Marker(pts[0],{icon:myIcon});
	// 		var label = new BMap.Label(items[m].bName,{offset:new BMap.Size(20,-10)});
	// 		carMk.setLabel(label);
	// 		carMk.setTitle(items[m].bid);
	// 		var flag=true;
	// 		map.addOverlay(carMk);
	// 		resetMkPoint(1,len,pts,carMk,flag)
	// 	}
	//
	// 	function resetMkPoint(i,len,pts,carMk,flag){
	// 		var ob;
	// 		var bid=JSON.parse(carMk.getTitle());
	// 		var flag2=false;
	// 		if (pts[i].lng<pStart.lng && pts[i].lat<pStart.lat && pts[i].lat>pEnd.lat) {
	// 			ob={"bid":bid,"area":"西门"};
	// 			flag2=true;
	// 		}else if(pts[i].lng>pEnd.lng && pts[i].lat<pStart.lat && pts[i].lat>pEnd.lat){
	// 			ob={"bid":bid,"area":"东门"};
	// 			flag2=true;
	// 		}else if(pts[i].lng>pStart.lng && pts[i].lng<pEnd.lng && pts[i].lat>pStart.lat){
	// 			ob={"bid":bid,"area":"北门"};
	// 			flag2=true;
	// 		}else if(pts[i].lng>pStart.lng && pts[i].lng<pEnd.lng && pts[i].lat<pEnd.lat){
	// 			ob={"bid":bid,"area":"南门"};
	// 			flag2=true;
	// 		}
	// 		if (flag && flag2){
	// 			$.ajax({
	// 				type: "POST",//提交的方式
	// 				url: "/ChildSchool/BackAction/addWarning.action",
	// 				data: ob,//提交的数据
	// 				dataType: "json",//希望返回的数据类型
	// 				success: function (msg) {//成功的方法  msg为返回数据
	// 					if (msg.msg === "1") {
	// 						console.log("报警添加成功");
	// 					} else if (msg.msg === "0") {
	// 						console.log("报警添加失败");
	// 					}
	// 					flag=false;
	// 				},
	// 				error: function () {//错误的方法
	// 					alert("服务器正忙")
	// 				}
	// 			});
	// 		}
	//
	//
	// 		carMk.setPosition(pts[i]);
	// 		if(i < len-1){
	// 			setTimeout(function(){
	// 				i++;
	// 				resetMkPoint(i,len,pts,carMk,flag);
	// 				console.log(flag+carMk.getTitle());
	// 			},500);
	// 		}
	// 	}
	//
	// }
	function drawLine(results){
		var opacity = 0.45;//不透明度
		var planObj = results.getPlan(0);//返回索引指定的方案。索引0表示第一条方案
		var b = new Array();
		var addMarkerFun = function(point,imgType,index,title){
			var url;
			var width;
			var height;
			var myIcon;
			// imgType:1的场合，为起点和终点的图；2的场合为车的图形
			if(imgType == 1){
				url = "http://lbsyun.baidu.com/jsdemo/img/dest_markers.png";
				width = 42;
				height = 34;
				myIcon = new BMap.Icon(url,new BMap.Size(width, height),{offset: new BMap.Size(14, 32),imageOffset: new BMap.Size(0, 0 - index * height)});
			}else{
				url = "http://lbsyun.baidu.com/jsdemo/img/trans_icons.png";
				width = 22;
				height = 25;
				var d = 25;
				var cha = 0;
				var jia = 0
				if(index == 2){
					d = 21;
					cha = 5;
					jia = 1;
				}
				myIcon = new BMap.Icon(url,new BMap.Size(width, d),{offset: new BMap.Size(10, (11 + jia)),imageOffset: new BMap.Size(0, 0 - index * height - cha)});
			}

			var marker = new BMap.Marker(point, {icon: myIcon});
			if(title != null && title != ""){
				marker.setTitle(title);
			}
			// 起点和终点放在最上面
			if(imgType == 1){
				marker.setTop(true);
			}
			map.addOverlay(marker);
		}
		var addPoints = function(points){
			for(var i = 0; i < points.length; i++){
				bounds.push(points[i]);
				b.push(points[i]);
			}
		}
		// 绘制驾车步行线路
		for (var i = 0; i < planObj.getNumRoutes(); i ++){//	返回方案包含的步行线路段数
			var route = planObj.getRoute(i);
			if (route.getDistance(false) <= 0){continue;}
			addPoints(route.getPath());
			// 驾车线路
			if(route.getRouteType() == BMAP_ROUTE_TYPE_DRIVING){
				map.addOverlay(new BMap.Polyline(route.getPath(), {strokeColor: "#0030ff",strokeOpacity:opacity,strokeWeight:6,enableMassClear:true}));
			}else{
				// 步行线路有可能为0
				map.addOverlay(new BMap.Polyline(route.getPath(), {strokeColor: "#30a208",strokeOpacity:0.75,strokeWeight:4,enableMassClear:true}));
			}
		}
		map.setViewport(bounds);
		// 终点
		addMarkerFun(results.getEnd().point,1,1);
		// 开始点
		addMarkerFun(results.getStart().point,1,0);
		linesPoints[linesPoints.length] = b;
	}
	initLine();

	map.enableScrollWheelZoom(true);    //开启鼠标滚轮缩放
</script>
