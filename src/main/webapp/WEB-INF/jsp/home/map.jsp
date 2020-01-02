<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/30 0030
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path=request.getContextPath();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
		.anchorBL{display:none;}
		*{padding: 0; margin: 0;}
		.search{position: absolute; top: 0; left: 0; padding: 20px; background-color: rgba(255,255,255,0.2);}
		li{list-style: none; border: #A7C0E0 solid 1px;}
		.search ul{margin-top: 5%;}
		.search li{display: inline-block; background-color: #FFF; padding: 5px 20px; cursor: pointer;}
		.search input{font-family: "微软雅黑";}
		.contSear{width:300px;box-sizing: border-box;padding: 10px 20px;border:2px solid transparent;line-height: 20px;font-size: 16px;height: 38px;color: #333;position: relative;;outline: none;}
		.btSear{width: 80px; height: 35px; line-height: 35px; font-size: 18px; letter-spacing: 2px;}
		.comp{position: absolute; bottom: 0; left: 0; background-color: rgba(255,255,255,0.5); padding: 0 10px;}
		.comp img{width: 80px; height: 30px; float: left; padding: 5px;}
		.clearFloat{display: block; content: "" ; clear: both;}
		.comp p{float:left; font-size: 14px; color: #888; line-height: 40px;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=F8GGZGTAevRPL7BwLtTjQvEzdAlpzqTt"></script>

	<title>地图</title>
</head>
<body>
<div id="allmap"></div>
<div class="comp">
	<p>软件园</p>
</div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");    // 创建Map实例
	map.centerAndZoom(new BMap.Point(118.193199,24.488834), 12);  // 初始化地图,设置中心点坐标和地图级别
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	var point = new BMap.Point(118.193199,24.488834);
	var marker = new BMap.Marker(point);  // 创建标注
	map.addOverlay(marker);              // 将标注添加到地图中
	map.centerAndZoom(point, 17);
	var opts = {
		width : 200,     // 信息窗口宽度
		height: 100,     // 信息窗口高度
		title : "传一智慧幼儿园" , // 信息窗口标题
	}
	var infoWindow = new BMap.InfoWindow("地址：厦门市软件园东二门观日路56号", opts);  // 创建信息窗口对象
	map.openInfoWindow(infoWindow,point);//初始开启信息窗口
	marker.addEventListener("click", function(){//按钮点击开启信息窗口
		map.openInfoWindow(infoWindow,point);
	});
	var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});// 右上角，添加比例尺
	map.addControl(top_left_control);
	var mapType1 = new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP]});
	map.addControl(mapType1);
	var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT, type: BMAP_NAVIGATION_CONTROL_ZOOM}); //右上角，仅包含平移和缩放按钮
	map.addControl(top_right_navigation);
	var local = new BMap.LocalSearch(map, {
		renderOptions:{map: map}
	});
	//map.enableScrollWheelZoom(true); //全景添加卡网络
	// 覆盖区域图层测试 卡
	//map.addTileLayer(new BMap.PanoramaCoverageLayer());
	//var stCtrl = new BMap.PanoramaControl(); //构造全景控件
	//stCtrl.setOffset(new BMap.Size(20, 20));
	//map.addControl(stCtrl);//添加全景控件
</script>
