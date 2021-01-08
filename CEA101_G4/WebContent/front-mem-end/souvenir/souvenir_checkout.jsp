<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*"%>
<%@ page import="com.souvenir_product.model.*"%>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
 <title></title>
</head>
<style>
		@import url(https://fonts.googleapis.com/css?family=Raleway:400,500,700);
        @import url(https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css);
        
</style>
<body bgcolor="#FFFFFF">
	<%@ include file="/front-mem-end/bar.jsp"%>

<hr><p><center>

<table border="1" width="720">
	<tr bgcolor="#999999">
		<th width="200">產品名稱</th>
		<th width="100">價格</th>
		<th width="100">數量</th>
		<th width="120"></th>
	</tr>
	
	<%
		Vector<SouvenirProductVO> buylist = (Vector<SouvenirProductVO>) session.getAttribute("soupVO");
		String amount =  (String) request.getAttribute("amount");
	%>	
	<%	for (int i = 0; i < buylist.size(); i++) {
		SouvenirProductVO order = buylist.get(i);
			String sou_name = order.getSou_name();
			int sou_price = order.getSou_price();
			int quantity = order.getQuantity();
	%>
	<tr>
		<td width="200"><div align="center"><b><%=sou_name%></b></div></td>
		<td width="100"><div align="center"><b><%=sou_price%></b></div></td>
		<td width="100"><div align="center"><b><%=quantity%></b></div></td>
	</tr>
	<%
		}
	%>
	<tr>
		<td></td>
		<td><div align="center"><font color="red"><b>總金額：</b></font></div></td>
		<td></td>
		<td> <font color="red"><b>$<%=amount%></b></font> </td>
	</tr>
</table>
<p><a href="<%=request.getContextPath()%>/front-mem-end/souvenir/souvenir.jsp">繼續購物</a>
<a href="<%=request.getContextPath()%>/front-mem-end/souvenir_order/addSouvenirOrder.jsp">確認購買</a>
</center>
</body>
</html>
