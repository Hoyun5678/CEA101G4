<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.souvenir_product.model.*"%>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Cart.jsp</title>
 <link rel="stylesheet" type="text/css" href="css/back-index-sidebar.css">
</head>
<style>
		@import url(https://fonts.googleapis.com/css?family=Raleway:400,500,700);
        @import url(https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css);
        #countQuantity{
        	display:inline;"
        }
</style>
<script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>
<body bgcolor="#FFFFFF">
	<%@ include file="/front-mem-end/bar.jsp"%>
<%Vector<SouvenirProductVO> buylist = (Vector<SouvenirProductVO>) session.getAttribute("soupVO");%>
<%if (buylist != null && (buylist.size() > 0)) {%>
<br>
<table border="1" width="740">
	<tr bgcolor="#999999">
		<th width="200">特產名稱</th><th width="100">價格</th>
		<th width="60">數量</th><th width="120"></th>
	</tr>
	
	<%
	 for (int index = 0; index < buylist.size(); index++) {
		SouvenirProductVO order = buylist.get(index);
	%>
	<tr>
		<td width="200"><div align="center"><b><%=order.getSou_name()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getSou_price()%></b></div></td>
		<td width="100"><div align="center" name="quantity"><b><%=order.getQuantity()%></b></div></td>
		
		<td width="100"><div align="center">
          <form name="deleteForm" action="<%=request.getContextPath()%>/shopping/shopping.do" method="POST">
              <input type="hidden" name="action" value="DELETECART">
              <input type="hidden" name="del" value="<%= index %>">
              <input type="submit" value="刪除"></div>
        </td></form>
	</tr>
	<%}%>
</table>
<p>
          <form name="checkoutForm" action="<%=request.getContextPath()%>/shopping/shopping.do" method="POST">
              <input type="hidden" name="action"	value="CHECKOUT"> 
              <input type="submit" value="付款結帳">
          </form>
<%}%>
</body>
</html>