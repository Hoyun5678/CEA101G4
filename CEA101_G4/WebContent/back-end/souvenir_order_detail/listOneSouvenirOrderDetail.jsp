<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.souvenir_order_detail.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	SouvenirOrderDetailVO sodVO = (SouvenirOrderDetailVO) request.getAttribute("sod"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>特產訂單明細資料 - listOneSouvenir_Order_detail.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 1200px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<!-- <h4>此頁暫練習採用 Script 的寫法取值:</h4> -->
	<table id="table-1">
		<tr>
			<td>
				<h3>特產訂單 - ListOneSouvenir_Order_detail.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/souvenir_order_detail/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<
			<th>特產訂單編號</th>
			<th>特產編號</th>
			<th>特產訂購數量</th>
			<th>特產商品單價</th>
		</tr>
		<tr>
			<td>${sodVO.sou_order_id}</td>
			<td>${sodVO.sou_id}</td>
			<td>${sodVO.sou_order_amount}</td>
			<td>${sodVO.sou_price}</td>
		</tr>
	</table>

</body>
</html>