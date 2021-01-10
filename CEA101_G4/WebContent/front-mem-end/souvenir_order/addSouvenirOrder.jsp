<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.souvenir_order.model.*"%>

<%
  SouvenirOrderVO soVO = (SouvenirOrderVO) request.getAttribute("soVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>特產訂單資料新增 - addSouvenir_Order.jsp</title>

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
	width: 600px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>
	<%@ include file="/front-mem-end/bar.jsp"%>
<h3>確認購買資訊:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/souvenir_order/souvenir_order.do" name="form2">
<table>
	<tr>
		<td>員工編號:</td>
		<td><input type="TEXT" name="emp_id" size="45"
			 value="<%= (soVO==null)? "EMP003" : soVO.getEmp_id()%>" /></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="mem_id" size="45"
			 value="<%= (soVO==null)? "MEM004" : soVO.getMem_id()%>" /></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>特產訂購日期:</td> -->
<!-- 		<td><input type="TEXT" name="sou_order_date" size="45" -->
<%-- 			 value="<%= (soVO==null)? "2021-01-02 23:39:44.0" : soVO.getSou_order_date()%>" /></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>特產收貨人姓名:</td>
		<td><input type="TEXT" name="sou_receiver_name" size="45"
			 value="<%= (soVO==null)? "信彰" : soVO.getSou_receiver_name()%>" /></td>
	</tr>
	<tr>
		<td>特產收貨人地址:</td>
		<td><input type="TEXT" name="sou_receiver_address" size="45"
			 value="<%= (soVO==null)? "新北市板橋區" : soVO.getSou_receiver_address()%>" /></td>
	</tr>
	<tr>
		<td>特產收貨人聯絡電話:</td>
		<td><input type="TEXT" name="sou_receiver_phone" size="45"
			 value="<%= (soVO==null)? "0978666889" : soVO.getSou_receiver_phone()%>" /></td>
	</tr>
	<tr>
		<td>特產運費:</td>
		<td><input type="TEXT" name="sou_shipment_fee" size="45"
			 value="<%= (soVO==null)? "10" : soVO.getSou_shipment_fee()%>" /></td>
	</tr>
	<tr>
		<td>特產訂單總價:</td>
		<td><input type="TEXT" name="sou_order_sum_price" size="45"
			 value="<%= (soVO==null)? "3000" : soVO.getSou_order_sum_price()%>" /></td>
	</tr>
	<tr>
		<td>特產訂單備註:</td>
		<td><input type="TEXT" name="sou_order_remarks" size="45"
			 value="<%= (soVO==null)? "請仔細包裝" : soVO.getSou_order_remarks()%>" /></td>
	</tr>
	<tr>
		<td>特產訂單運送方式:</td>
		<td><input type="TEXT" name="sou_shipping_method" size="45"
			 value="<%= (soVO==null)? "0" : soVO.getSou_shipping_method()%>" /></td>
	</tr>
	<tr>
		<td>特產訂單狀態:</td>
		<td><input type="TEXT" name="sou_order_status" size="45"
			 value="<%= (soVO==null)? "0" : soVO.getSou_order_status()%>" /></td>
	</tr>
	<tr>
		<td>特產訂單付款狀態:</td>
		<td><input type="TEXT" name="sou_payment_status" size="45"
			 value="<%= (soVO==null)? "0" : soVO.getSou_payment_status()%>" /></td>
	</tr>
	<tr>
		<td>特產訂單出貨狀態:</td>
		<td><input type="TEXT" name="sou_shipment_status" size="45"
			 value="<%= (soVO==null)? "0" : soVO.getSou_shipment_status()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>
</html>