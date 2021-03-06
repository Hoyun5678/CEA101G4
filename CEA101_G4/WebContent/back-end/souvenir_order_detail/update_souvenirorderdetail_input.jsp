<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.souvenir_order_detail.model.*"%>

<%
SouvenirOrderDetailVO sodVO = (SouvenirOrderDetailVO) request.getAttribute("sodVO"); //SuvenirOrderServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%= sodVO==null %>--${sodVO.sou_order_id}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>特產訂單資料修改 - update_souvenirOrderDetail_input.jsp</title>

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
	width: 500px;
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

<table id="table-1">
	<tr><td>
		 <h3>特產訂單資料修改 - update_souvenirOrderDetail_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/souvenir_order_detail/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/souvenir_order_detail/souvenir_order_detail.do" name="form2">
<table>
	<tr>
		<td>特產訂單編號:<font color=red><b></b></font></td>
		<td><%=sodVO.getSou_order_id()%></td>
	</tr>
	<tr>
		<td>特產商品編號:</td>
		<td><%=sodVO.getSou_id()%></td>
	</tr>
	<tr>
		<td>特產訂購數量:</td>
		<td><input type="TEXT" name="sou_order_amount" size="45"
			 value="<%=sodVO.getSou_order_amount()%>" /></td>
	</tr>
	<tr>
		<td>特產商品單價:</td>
		<td><input type="TEXT" name="sou_price" size="45"
			 value="<%=sodVO.getSou_price()%>" /></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="sou_order_id" value="<%=sodVO.getSou_order_id()%>">
<input type="hidden" name="sou_id" value="<%=sodVO.getSou_id()%>">
<input type="submit" value="送出修改"></FORM>
</body>




</html>