<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/19
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<title>学员统计</title>
	<script  src=<%=path+"/js/jquery-3.4.1.js" %>></script>
	<script  src=<%=path+"/js/echarts.js" %>></script>
</head>
<body>
<div id="main" style="width: 95%;height:95%; align-content: center" ></div>



<script type="text/javascript">
	var nameArr = [];
	var boyArr = [];
	var girlArr= [];

	$(function() {
		$.ajax({
			method : "POST",
			url : "/ChildSchool/studentCount.action",
			dataType : "json",
			success : function(studentCountList) {


				for (var i = 0; i < studentCountList.length; i++) {
					//alert(postCountList[i].name);
					nameArr.push(studentCountList[i].classname);
					boyArr.push(studentCountList[i].boy);
					girlArr.push(studentCountList[i].girl);

				}
				// alert(nameArr);

				createEchars();// 创建饼图

			},
			error : function() {
				alert("服务器正忙");
			}
		});
	});


	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main'));

	function createEchars(){
		var option = {
			title : {
				text: '学员统计'

			},
			tooltip : {
				trigger: 'axis'
			},
			legend: {
				data:['男','女']
			},
			toolbox: {
				show : true,
				feature : {
					dataView : {show: true, readOnly: false},
					magicType : {show: true, type: ['line', 'bar']},
					restore : {show: true},
					saveAsImage : {show: true}
				}
			},
			calculable : true,
			xAxis : [
				{
					type : 'category',
					//班级
					data : nameArr
				}
			],
			yAxis : [
				{
					type : 'value'
				}
			],
			series : [
				{
					name:'男',
					type:'bar',
					data:boyArr,
					markPoint : {
						data : [
							{type : 'max', name: '男'},
							{type : 'min', name: '女'}
						]
					},
					markLine : {
						data : [
							{type : 'average', name: '平均人数'}
						]
					}
				},
				{
					name:'女',
					type:'bar',
					data:girlArr,
					markPoint : {
						data : [
							{type : 'max', name: '男'},
							{type : 'min', name: '女'}
							// {name : '最高人数', value : 182.2, xAxis: 7, yAxis: 183},
							// {name : '最低人数', value : 2.3, xAxis: 11, yAxis: 3}
						]
					},
					markLine : {
						data : [
							{type : 'average', name : '平均人数'}
						]
					}
				}
			]
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	}













</script>







</body>
</html>
