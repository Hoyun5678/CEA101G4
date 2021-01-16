<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.souvenir_product.model.*"%>
<%@ page import="com.souvenir_order.model.*"%>
<%@ page import="com.souvenir_order_detail.model.*"%>
<%@ page import="com.member.model.*"%>
<%SouvenirOrderVO soVO = (SouvenirOrderVO) request.getAttribute("soVO");%>
<%SouvenirProductVO soupVO = (SouvenirProductVO) request.getAttribute("soupVO");%>
<%SouvenirOrderDetailVO sodVO = (SouvenirOrderDetailVO) request.getAttribute("sodVO");%>
<%MemberVO memVO = (MemberVO) session.getAttribute("memVO"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>確認購買資訊</title>
</head>
<style>
@import
	url('https://fonts.googleapis.com/css?family=Roboto+Condensed:400,700')
	;

body {
	font-family: 'Roboto Condensed', sans-serif;
	color: #262626;
	margin: 5% 0;
}

.container {
	width: 100%;
	padding-right: 15px;
	padding-left: 15px;
	margin-right: auto;
	margin-left: auto;
}

@media ( min-width : 1200px) {
	.container {
		max-width: 1140px;
	}
}

.d-flex {
	display: flex;
	flex-direction: row;
	background: #f6f6f6;
	border-radius: 0 0 5px 5px;
	padding: 25px;
}

form {
	flex: 4;
}

.Yorder {
	flex: 2;
	    margin-left: 50%;
    /* transform: translateX(-50%); */
    margin-top: -38% !important;
}

h2 {
	margin: 0;
	padding-left: 15px;
}

.required {
	color: red;
}

label, table {
	display: block;
	margin: 15px;
}

label {
	display: block !important;
}

label>span {
	float: left;
	width: 12%;
	margin-top: 12px;
	padding-right: 10px;
}

input[type="text"], input[type="tel"], input[type="email"], select {
	width: 36%;
	height: 30px;
	padding: 5px 10px;
	margin-top: 10px;
	border: 1px solid #dadada;
	color: #888;
}

select {
	width: 72%;
	height: 45px;
	padding: 5px 10px;
	margin-bottom: 10px;
}

.Yorder {
	margin-top: 15px;
	height: 650px;
	padding: 20px;
	border: 1px solid #dadada;
}

table {
	margin: 0;
	padding: 0;
}

th {
	border-bottom: 1px solid #dadada;
	padding: 10px 0;
}

tr>td:nth-child(1) {
	text-align: left;
	color: #2d2d2a;
}

tr>td:nth-child(2) {
	text-align: right;
	color: #52ad9c;
}

td {
	border-bottom: 1px solid #dadada;
	padding: 25px 25px 25px 0;
}

p {
	display: block;
	color: #888;
	margin: 0;
	padding-left: 25px;
}

.Yorder>div {
	padding: 15px 0;
}

button {
	width: 100%;
	margin-top: 10px;
	padding: 10px;
	border: none;
	border-radius: 30px;
	background: #52ad9c;
	color: #fff;
	font-size: 15px;
	font-weight: bold;
}

button:hover {
	cursor: pointer;
	background: #428a7d;
}
</style>

<body>
	<%@ include file="/front-mem-end/bar.jsp"%>
	<%-- 錯誤表列 --%>
	<br>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class="container">
		<div class="d-flex">
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/souvenir_order/souvenir_order.do"
				name="form2">
				<tr>		 
				<label> <span class="fname">收件人姓名 <span
						class="required">*</span></span> <input
					value="<%=(memVO == null) ? "" : memVO.getMem_name()%>" type="text"
					name="sou_receiver_name" placeholder="輸入名字" required>
				</label> <br> <label> <span>收件人地址 <span class="required">*</span></span>
					<input
					value="<%=(memVO == null) ? "新北市板橋區" : memVO.getMem_address()%>"
					type="text" name="sou_receiver_address"
					placeholder="House number and street name" required>
				</label> <br> <label> <span>電話 <span class="required">*</span></span>
					<input
					value="<%=(memVO == null) ? "0978666889" : memVO.getMem_tel()%>"
					type="TEXT" name="sou_receiver_phone">
				</label><br> <label> <span>訂單運送方式 <span
						class="required">*</span></span> <input
					value="<%=(soVO == null) ? "0" : soVO.getSou_shipping_method()%>"
					type="radio" name="sou_shipping_method">宅配
					<input
					value="<%=(soVO == null) ? "1" : soVO.getSou_shipping_method()%>"
					type="radio" name="sou_shipping_method" checked>超商取貨
				</label> <br><label> <span>特產訂單備註 <span class="required">*</span></span><input type="TEXT" name="sou_order_remarks"
			 value="<%= (soVO==null)? "請仔細包裝" : soVO.getSou_order_remarks()%>" /></td>
	</tr>
	
				<tr>

<!-- 		<td>員工編號:</td> -->
		<td><input type="hidden" name="emp_id"
			 value="<%= (soVO==null)? "EMP003" : soVO.getEmp_id()%>" /></td>
	</tr>
	<tr>
<!-- 		<td>會員編號:</td> -->
		<td><input type="hidden" name="mem_id"
			 value="<%=memVO.getMem_id()%>" /></td>
<!-- 		<td>特產運費:</td> -->
		<input type="hidden" name="sou_shipment_fee"
			 value="<%= (soVO==null)? "10" : soVO.getSou_shipment_fee()%>" />
	</tr>

<!-- 				<td>特產訂單狀態:</td>  -->
		<td><input type="hidden" name="sou_order_status"
			 value="<%= (soVO==null)? "0" : soVO.getSou_order_status()%>" /></td>
	</tr>
	<tr>
<!-- 		<td>特產訂單付款狀態:</td> -->
		<td><input type="hidden" name="sou_payment_status"
			 value="<%= (soVO==null)? "0" : soVO.getSou_payment_status()%>" /></td>
	</tr>
	<tr>
<!-- 		<td>特產訂單出貨狀態:</td> -->
		<td><input type="hidden" name="sou_shipment_status"
			 value="<%= (soVO==null)? "0" : soVO.getSou_shipment_status()%>" /></td>
	</tr>
	
			</form>
			<div class="Yorder">
				<table>
					<tr>
						<th colspan="2">Your order</th>
						<th></th>
						<th></th>
					</tr>
					<tr>
						<th width="200">產品名稱</th>
						<th width="100">價格</th>
						<th width="100">數量</th>
					</tr>

					<%
						Vector<SouvenirProductVO> buylist = (Vector<SouvenirProductVO>) session.getAttribute("soupVO");
						Integer amount = (Integer) request.getAttribute("amount");
					%>
					<%
						for (int i = 0; i < buylist.size(); i++) {
							SouvenirProductVO order = buylist.get(i);
							String sou_name = order.getSou_name();
							int sou_price = order.getSou_price();
							int quantity = order.getQuantity();
							String sou_id = order.getSou_id();
					%>
					<tr>
						<td width="200">
							<div align="left">
								<b> <%=sou_name%></b>
							</div>
						</td>
						<td width="100">
							<div align="left">
								<b> <%=quantity%></b>
							</div>
						</td>
						<td width="100">
							<div align="left">
								<b> <%=sou_price%></b>
							</div>
						</td>
					</tr>
					<tr>
<%-- 		<td width="200"><div align="center"><b><%=sou_name%></b></div></td> --%>
		<input type="hidden" name="sou_id" value="<%= sou_id%>">
<%-- 		<td width="100"><div align="center"><b><%=sou_price%></b></div></td> --%>
		<input type="hidden" name="sou_price" value="<%=sou_price%>">
<%-- 		<td width="100"><div align="center"><b><%=quantity%></b></div></td> --%>
		<input type="hidden" name="sou_order_amount" value="<%=quantity%>">
	</tr>
					<%
						}
					%>

					<tr>
						<td><b>總金額</b></td>
						<td></td>
						<td><b>${amount}</b></td>
						<input type="hidden" name="sou_order_sum_price"value="${amount}"/>
	</tr>
					</tr>
				</table>
				<br>
				<div>
					<input type="radio" name="dbt" value="cd"> 貨到付款
				</div>
				<div>
					<input type="radio" name="dbt" value="cd"> 信用卡 <span>
						<img
						src="https://www.logolynx.com/images/logolynx/c3/c36093ca9fb6c250f74d319550acac4d.jpeg"
						alt="" width="50">
					</span>
				</div>
				<input type="hidden" name="action" value="insert">
				<button onclick="check()" type="submit">下訂單</button>
			</div>
			<!-- Yorder -->
		</div>
	</div>
	<script>
		function check(){`
			alert("訂單成立");
		}
	</script>
</body>

</html>