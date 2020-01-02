<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/20
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String cssPath = application.getContextPath()+"/css/" ;
	String jsPath =application.getContextPath()+"/js/" ;
	String path = request.getContextPath();
	String uicssPath = request.getContextPath()+"/layui/" ;
	String uiPath = request.getContextPath()+"/layui/";
%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
	<title>单个标注点沿直线的轨迹运动</title>

<%--	<meta charset="utf-8" />--%>
<%--	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />--%>
<%--	<title></title>--%>
	<link rel="icon" href="./img/favicon.ico">
<%--	<link rel="stylesheet" href=<%=cssPath +"frontPage.css"%> >--%>
<%--	<script src=<%=jsPath+"frontPage.js"%>></script>--%>
	<link rel="stylesheet" href=<%=uicssPath +"css/layui.css"%> media="all">
	<script src="<%=jsPath+"jquery-3.4.1.js"%>"></script>
	<script src=<%=uiPath+"layui.js"%>></script>
	<script src=<%=jsPath+"advertisement.js"%>></script>

</head>

<body>
<%--路线名下拉框--%>
<div class="layui-input-inline" >
	<select id="routeListSelect" name="routeListSelect"  style="margin-left: 3%;" onchange="change()">
		<option  value=""></option>
		<c:if test="${null != requestScope.routeLists}">
		<c:forEach items="${requestScope.routeLists}" begin="0" step="1" var="i">
			<option  value="${i.routeName}">${i.routeName}</option>
		</c:forEach>
		</c:if>
	</select>
</div>
<%--地图--%>
<div id="allmap"></div>
</body>
<script type="text/javascript">
	var items =[];
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	map.centerAndZoom(new BMap.Point(113.962196, 22.545144), 13);
	map.enableScrollWheelZoom(); // 允许滚轮缩放

	// var myP1 = new BMap.Point(113.962196, 22.545144);
	// var myP2 = new BMap.Point(113.987268, 22.54884);
	// var myP3 = new BMap.Point(113.976281, 22.53543);
	// var myP4 = new BMap.Point(113.957956, 22.539636);

	//终点
	var myIcon = new BMap.Icon("http://lbsyun.baidu.com/jsdemo/img/Mario.png", new BMap.Size(32, 70), {    //小车图片
		//offset: new BMap.Size(0, -5),    //相当于CSS精灵
		imageOffset: new BMap.Size(0, 0)    //图片的偏移量。为了是图片底部中心对准坐标点。
	});
	var driving2 = new BMap.DrivingRoute(map,
		{
			renderOptions: { //结果呈现设置
				map: map,
				autoViewport: true,  //检索结束后自动调整地图视野
				enableDragging: true   //起终点可进行拖拽
			},
			policy: BMAP_DRIVING_POLICY_LEAST_DISTANCE //驾车方案的策略配置,最少时间


		});

	function run(ar) {
		var arg = ar;
		function p(x, y) {
			var r = [];
			for (var h = 0; h < arguments.length; h++) {
				r.push(arguments[h]);
			}
			var p1 = r[0];
			var p2 = r[1];

			console.log('原始值', arg.indexOf(p1));

			var driving = new BMap.DrivingRoute(map,
				{
					renderOptions: { //结果呈现设置
						map: map,
						autoViewport: true,  //检索结束后自动调整地图视野
						enableDragging: true   //起终点可进行拖拽
					},
					policy: BMAP_DRIVING_POLICY_LEAST_DISTANCE //驾车方案的策略配置,最少距离


				});    //驾车实例
			driving.search(p1, p2);





			driving.setSearchCompleteCallback(function () {
				var pts = driving.getResults().getPlan(0).getRoute(0).getPath();
				var carMk = new BMap.Marker(pts[0], { icon: myIcon });
				map.addOverlay(carMk);
				var i = 0;
				function resetMkPoint(i) {
					carMk.setPosition(pts[i]);
					var s = arg.indexOf(p1);
					if (i < pts.length) {
						setTimeout(function () {
							i++;
							resetMkPoint(i);
						}, 20);
					} else {
						console.log('当前值', s);
						if (s < arg.length - 1) {
							s++;
							carMk.hide();
							p(arg[s], arg[s + 1]);
							console.log('加过后的值', s, arg[s], arg);
						}
					}
				}
				setTimeout(function () {
					resetMkPoint(5);

				}, 2000)

			});
		}
		p(arg[0], arg[1]);
	}





  function change() {

	  var routeNameSelect=$("#routeListSelect").val();
	  items.length = 0;
	  map.clearOverlays();
	  // var interval = setInterval(run, 1);
	  // clearInterval(interval);
	  $.ajax({
		  url:"/bus/StationByRouteName?routeName="+routeNameSelect,
		  // data: ob,
		  type:"Post",
		  dataType:"json",
		  async: true,
		  success: function (data) {

			  for (var i = 0; i < data.length; i ++) {
				  var point = new BMap.Point(data[i].stationLon,data[i].stationLat);
				  items.push(point);

				  //标注站点位置和名字
				  var mk = new BMap.Marker(point);
				  map.addOverlay(mk);
				  // addMarker(point);
				  var label = new BMap.Label(data[i].stationName,{offset:new BMap.Size(20,-10)});//覆盖物上的标签
				  mk.setLabel(label);

			  }
			  driving2.search(items[0], items[items.length-1]);    //显示一条公交线路

			  run(items);

		  },
		  error:function(msg){
			  alert("服务器正忙");
		  }
	  });

  }



</script>

</html>
