<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*"%>
<%@ page import="com.souvenir_product.model.*"%>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
 <title></title>
</head>
<style>
#centertable{
            margin-left: 50%;
            transform: translateX(-50%);
}

*{
text-align: center;
}
</style>
<body>
	<%@ include file="/front-mem-end/bar.jsp"%>
<br>

<table id="centertable" class="table-sm table-hover" border="1" width="720">
	<tr>
		<th width="200">產品名稱</th>
		<th width="100">價格</th>
		<th width="100">數量</th>
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
		<td> <font color="red"><b>$<%=amount%></b></font> </td>
	</tr>
</table>
<br>
<p>
<a href="<%=request.getContextPath()%>/front-mem-end/souvenir/souvenir.jsp"><button class="btn btn-success">繼續購物</button></a>
<a href="<%=request.getContextPath()%>/front-mem-end/souvenir_order/addSouvenirOrder.jsp"><button class="btn btn-success">確認購買</button></a>

</body>
</html>
