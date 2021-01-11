<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.souvenir_product.model.*"%>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Cart.jsp</title>
 <link rel="stylesheet" type="text/css" href="css/back-index-sidebar.css">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
 
</head>
<style>
#centertable{
            margin-left: 50%;
            transform: translateX(-50%);
}

*{
text-align: center;
/* text-color: black; */
}
</style>
<script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>
<body>
<%-- 	<%@ include file="/front-mem-end/bar.jsp"%> --%>
<%Vector<SouvenirProductVO> buylist = (Vector<SouvenirProductVO>) session.getAttribute("soupVO");%>
<%if (buylist != null && (buylist.size() > 0)) {%>
<br>
<div class="container">
<table id="centertable" class="table-sm table-hover" border="1" width="740">
	<tr>
		<th scope="col" width="200">特產名稱</th>
		<th scope="col" width="100">價格</th>
		<th scope="col" width="60">數量</th>
		<th scope="col" width="120"></th>
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
              <input type="submit" class="btn btn-outline-danger" value="刪除"></div>
        </td></form>
	</tr>
	<%}%>
</table>
<p>
          <form name="checkoutForm" action="<%=request.getContextPath()%>/shopping/shopping.do" method="POST">
              <input type="hidden" name="action"	value="CHECKOUT"> 
              <input type="submit" class="btn btn-success" value="付款結帳">
          </form>
<%}%>
</div>
</body>
</html>