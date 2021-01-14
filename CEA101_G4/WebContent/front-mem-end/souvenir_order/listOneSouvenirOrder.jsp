<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.souvenir_order.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.member.model.*"%>
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
<title>特產訂單資料</title>
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
	color: #fffde5;
}

table.table.table-dark.table-striped {
	background-color: #666;
	width: 1100px;
	position: fixed;
	left: 50%;
	transform: translateX(-50%);
	color: #fffde5;
}

.table-bordered td, .table-bordered th {
	font-size: 10;
}

.btn:not(:disabled):not(.disabled) {
	font-size: 10;
}
</style>

</head>
<body bgcolor='white'>
	<%@include file="/front-mem-end/bar.jsp"%>
	<div align="center">
		<h3>特產訂單</h3>
	</div>
	<div class="container-fluid">
		<div class="container">
			<div class="row">
				<div class="col calendarCol">
					<main class="main">
						<table id="example"
							class="table table-striped table-bordered table-hover"
							cellspacing="0" width="100%" style="text-align: center;">
							<tr>
								<th>訂單編號</th>
								<!-- 		<th>員工編號</th> -->
								<!-- 							<th>會員編號</th> -->
								<th>收貨人</th>
								<th>收貨地址</th>
								<th>聯絡電話</th>
<!-- 								<th>特產運費</th> -->
								<th>訂單總價</th>
								<!-- 							<th>訂單備註</th> -->
								<th>運送方式</th>
								<th>訂單狀態</th>
								<th>付款狀態</th>
								<th>出貨狀態</th>
								<th>訂購日期</th>
								<th></th>
							</tr>
							<jsp:useBean id="soSvc" scope="page"
								class="com.souvenir_order.model.SouvenirOrderService" />
							<c:forEach var="soVO"
								items="${soSvc.getSouOrderByMemId(memVO.mem_id)}">
								<tr>
									<td><a
										href="<%=request.getContextPath()%>/souvenir_order_detail/souvenir_order_detail.do?sou_order_id=${soVO.sou_order_id}&action=listSouvenirOrderDetails_ByCompositeQuery2">${soVO.sou_order_id}</a></td>
									<%-- 			<td>${soVO.emp_id}</td> --%>
									<%-- 								<td>${soVO.mem_id}</td> --%>
									<td>${soVO.sou_receiver_name}</td>
									<td>${soVO.sou_receiver_address}</td>
									<td>${soVO.sou_receiver_phone}</td>
									<%-- 								<td>${soVO.sou_shipment_fee}</td> --%>
									<td>${soVO.sou_order_sum_price}</td>
									<%-- 								<td>${soVO.sou_order_remarks}</td> --%>
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
											<c:when test="${soVO.sou_payment_status==0}">
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
									<td><fmt:formatDate value="${soVO.sou_order_date}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td><c:choose>
											<c:when
												test="${(soVO.sou_order_status!=2)&&(soVO.sou_order_status!=3) }">
												<form METHOD="post"
													ACTION="<%=request.getContextPath()%>/souvenir_order/souvenir_order.do">
													<button type="submit" class="btn btn-light btn-xs dt-edit"
														style="margin-right: 16px;">取消訂單</button>
													<input type="hidden" name="sou_order_id"
														value="${soVO.sou_order_id}"> <input type="hidden"
														name="action" value="memCancelSouOrder">
												</form>
											</c:when>
										</c:choose></td>
								</tr>
							</c:forEach>
						</table>
					</main>
				</div>
			</div>
		</div>
	</div>

</body>
</html>