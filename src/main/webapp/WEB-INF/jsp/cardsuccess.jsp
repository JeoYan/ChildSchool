<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
%>
<script src="<%=request.getContextPath()%>/js/jquery.min.js" type="text/javascript"></script>

<html>

<head>
	<title>卡充值业务</title>
</head>


<body style="background: #0B4252">


<div style="background-image: url(<%=path+"/images/bg.png"%>);width: 87%;height: 98%;    margin: 0 auto;
		">
	<h1 style="color: #cf671b; text-align: center">充值成功</h1>

	<table style="text-align: center;width: 100%;margin-top:50px">

		<tr style="line-height:50px">
			<td style="font-size: 20px;">
				支付项目：卡充值
			</td>
		</tr>
	<tr style="line-height:50px">
		<td style="font-size: 20px;">
			订单编号: ${out_trade_no }
		</td>
	</tr >
		<tr style="line-height:50px">
	<td style="font-size: 20px;">
		支付宝交易号: ${trade_no }
	</td>
	</tr>
	<tr style="line-height:50px">
	<td style="font-size: 20px;">
		充值金额: ${save_amount }
	</td>
	</tr>
		<tr style="line-height:50px">
			<td style="font-size: 20px;">
				账户总金额: ${totalMoney }
			</td>
		</tr>

</table>
</div>

<script type="text/javascript">
	function webpageClose(){
		window.close();
	}
	setTimeout( webpageClose,5000)//10s钟后关闭
</script>


</body>

</html>


