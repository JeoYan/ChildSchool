<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
%>
<script src="<%=request.getContextPath()%>/js/jquery.min.js" type="text/javascript"></script>

<html>

<head>
	<title>缴费成功后的购买详情界面</title>
</head>


<body style="background: #0B4252">


<div style="background-image: url(<%=path+"/images/bg.png"%>);width: 87%;height: 98%;    margin: 0 auto;
		">
	<h1 style="color: #cf671b; text-align: center">购买成功</h1>

	<table style="text-align: center;width: 100%;margin-top:50px">

		<tr style="line-height:50px">
			<td style="font-size: 20px;">
				购买商品：课外读物
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
		付款金额: ${total_amount }
	</td>
	</tr>

</table>
</div>
<%--    <script>--%>
<%--	    $(function() {--%>
<%--		    setTimeout(function() {--%>
<%--			    // 做一些事情;--%>
<%--		    }, 500);--%>
<%--	    });--%>
<%--    </script>--%>

<script type="text/javascript">
	function webpageClose(){
		window.close();
	}
	setTimeout( webpageClose,5000)//10s钟后关闭
</script>


</body>

</html>


