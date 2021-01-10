<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.souvenir_order.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
SouvenirOrderVO soVO = (SouvenirOrderVO) request.getAttribute("soVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>特產訂單資料 - listOneSouvenir_Order.jsp</title>
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
	width: 1100px;
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

body {
	background-color: #E5E5E5;
	text-align: center;
	border-radius: 30px;
}


h4 {
	color: blue;
	display: inline;
}
</style>

<style>
th, td {
	padding: 5px;
	text-align: center;
}

a {
	font-size: 15px;
	color: black;
}

a:hover {
	text-decoration: none;
	color: white;
}

a#local {
	font-size: 30px;
}

input.input-group-text:hover {
	opacity: 0.5;
}

font {
	color: green;
}

#pageselect {
	display: flex;
	flex-direction: row;
	justify-content: center;
}

table#table-1 {
	background-color: #888;
	text-align: center;
	border: solid black 2px;
	color:#fffde5;
}
table.table.table-dark.table-striped {
    background-color: #666;
    width:1100px;
      position: fixed;
        left: 50%;
        transform: translateX(-50%);
        color:#fffde5;
}
</style>

</head>
<body bgcolor='white'>
 <div id="wrapper">
        <%@ include file="/back-end/back-index-sidebar.jsp"%>

        <div id="page-content-wrapper">
	<div class="container-fluid">
<!-- <h4>此頁暫練習採用 Script 的寫法取值:</h4> -->
</div>

<table class="table table-dark table-striped">
<!-- <tr> -->
<!-- 			<td>特產編號</td> -->
<%-- 			<td><%=soVO.getSou_order_id()%></td> --%>

<!-- </tr> -->
<!-- <tr> -->
<!-- 			<td>員工編號</td> -->
<%-- 			<td><%=soVO.getEmp_id()%></td> --%>

<!-- </tr> -->
<!-- <tr> -->
<!-- 			<td>會員編號</td> -->
<%-- 			<td><%=soVO.getMem_id()%></td> --%>

<!-- </tr> -->
<!-- <tr> -->
<!-- 			<td>特產收貨人姓名</td> -->
<%-- 			<td><%=soVO.getSou_receiver_name()%></td> --%>

<!-- </tr> -->
<!-- <tr> -->
<!-- 			<td>特產收貨人地址</td> -->
<%-- 			<td><%=soVO.getSou_receiver_address()%></td> --%>

<!-- </tr> -->
<!-- <tr> -->
<!-- 			<td>特產收貨人聯絡電話</td> -->
<%-- 			<td><%=soVO.getSou_receiver_phone()%></td> --%>

<!-- </tr> -->
<!-- <tr> -->
<!-- 			<td>特產運費</td> -->
<%-- 			<td><%=soVO.getSou_shipment_fee()%></td> --%>

<!-- </tr> -->
<!-- <tr> -->
<!-- 			<td>特產訂單總價</td> -->
<%-- 			<td><%=soVO.getSou_order_sum_price()%></td> --%>

<!-- </tr> -->
<!-- <tr> -->
<!-- 			<td>特產訂單備註</td> -->
<%-- 			<td><%=soVO.getSou_order_remarks()%></td> --%>

<!-- </tr> -->
<!-- <tr> -->
<!-- 			<td>特產訂單運送方式</td> -->
<%-- 			<td><%=soVO.getSou_shipping_method()%></td> --%>

<!-- </tr> -->
<!-- <tr> -->
<!-- 			<td>特產訂單狀態</td> -->
<%-- 			<td><%=soVO.getSou_order_status()%></td> --%>

<!-- </tr> -->
<!-- <tr> -->
<!-- 			<td>特產訂單付款狀態</td> -->
<%-- 			<td><%=soVO.getSou_payment_status()%></td> --%>

<!-- </tr> -->
<!-- <tr> -->
<!-- 			<td>特產訂單出貨狀態</td> -->
<%-- 			<td><%=soVO.getSou_shipment_status()%></td> --%>

<!-- </tr> -->
<!-- <tr> -->
<!-- 			<td>特產訂購日期</td> -->
<%-- 			<td><%=soVO.getSou_order_date()%></td> --%>
<!-- </tr> -->
	
	<tr>
		<th>特產編號</th>
		<th>員工編號</th>
		<th>會員編號</th>
		<th>特產收貨人姓名</th>
		<th>特產收貨人地址</th>
		<th>特產收貨人聯絡電話</th>
		<th>特產運費</th>
		<th>特產訂單總價</th>
		<th>特產訂單備註</th>
		<th>特產訂單運送方式</th>
		<th>特產訂單狀態</th>
		<th>特產訂單付款狀態</th>
		<th>特產訂單出貨狀態</th>
		<th>特產訂購日期</th>
	</tr>
	<tr>
		<td>${soVO.sou_order_id}</td>
			<td>${soVO.emp_id}</td>
			<td>${soVO.mem_id}</td>
			<td>${soVO.sou_receiver_name}</td>
			<td>${soVO.sou_receiver_address}</td>
			<td>${soVO.sou_receiver_phone}</td>
			<td>${soVO.sou_shipment_fee}</td>
			<td>${soVO.sou_order_sum_price}</td>
			<td>${soVO.sou_order_remarks}</td>
			<td><c:choose>
													<c:when test="${soVO.sou_shipping_method==0}">
                                                        	宅配
                                                    </c:when>
                                                    <c:when test="${soVO.sou_shipping_method==1}">
                                                       	 	超商取貨
                                                    </c:when>
                                                </c:choose></td>
					<td><c:choose>
                                                    <c:when test="${soVO.sou_order_status==0}">
                                                        	處理中
                                                    </c:when>
                                                    <c:when test="${soVO.sou_order_status==1}">
                                                       	 已確認
                                                    </c:when>
                                                    <c:when test="${soVO.sou_order_status==2}">
                                                       	 已完成
                                                    </c:when>
                                                    <c:when test="${soVO.sou_order_status==3}">
                                                    	 已取消
                                                    </c:when>
                                                    <c:when test="${soVO.sou_order_status==4}">
                                                    	  已關閉
                                                    </c:when>
                                                </c:choose></td>
					<td><c:choose>
                                                    <c:when test="${soVO.sou_shipment_status==0}">
                                                        	未付款
                                                    </c:when>
                                                    <c:when test="${soVO.sou_payment_status==1}">
                                                       	 付款失敗
                                                    </c:when>
                                                    <c:when test="${soVO.sou_payment_status==2}">
                                                       	 超過付款時間
                                                    </c:when>
                                                    <c:when test="${soVO.sou_payment_status==3}">
                                                    	    已付款
                                                    </c:when>
                                                    <c:when test="${soVO.sou_payment_status==4}">
                                                    	  退款中
                                                    </c:when>
                                                    <c:otherwise>
                                                      	  已退款
                                                    </c:otherwise>
                                                </c:choose></td>
					<td><c:choose>
                                                    <c:when test="${soVO.sou_shipment_status==0}">
                                                       	訂單處理中
                                                    </c:when>
                                                    <c:when test="${soVO.sou_shipment_status==1}">
                                                       	 準備出貨
                                                    </c:when>
                                                    <c:when test="${soVO.sou_shipment_status==2}">
                                                       	已出貨
                                                    </c:when>
                                                    <c:when test="${soVO.sou_shipment_status==3}">
                                                    	    已到貨
                                                    </c:when>                    
                                                </c:choose></td>
			<td><fmt:formatDate value="${soVO.sou_order_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>
</table>
</div>
</div>
</body>
</html>