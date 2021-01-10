<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*"%>
<%@ page import="com.souvenir_product.model.*"%>
<%@ page import="com.souvenir_order.model.*"%>
<%@ page import="com.member.model.*"%>
<%
  SouvenirOrderVO soVO = (SouvenirOrderVO) request.getAttribute("soVO");
%>
<%MemberVO memVO = (MemberVO) session.getAttribute("memVO");  %>
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
		<th width="100">產品名稱</th>
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
<!-- 	<tr> -->
<!-- 		<td></td> -->
<!-- 		<td><div align="center"><font color="red"><b>總金額：</b></font></div></td> -->
<!-- 		<td></td> -->
<%-- 		<td> <font color="red"><b>$<%=amount%></b></font> </td> --%>
<!-- 	</tr> -->
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/souvenir_order/souvenir_order.do" name="form2">
<table>
			 
		<td><input type="hidden" name="emp_id" size="45"
			 value="<%= (soVO==null)? "EMP003" : soVO.getEmp_id()%>" /></td>

	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="mem_id" size="45" 
			 value="<%= (memVO==null)? "" : memVO.getMem_id()%>" readonly/></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>特產訂購日期:</td> -->
<!-- 		<td><input type="TEXT" name="sou_order_date" size="45" -->
<%-- 			 value="<%= (soVO==null)? "2021-01-02 23:39:44.0" : soVO.getSou_order_date()%>" /></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>特產收貨人姓名:</td>
		<td><input type="TEXT" name="sou_receiver_name" size="45"
			 value="<%= (memVO==null)? "" : memVO.getMem_name()%>" /></td>
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
			 value="<%= (soVO==null)? "30" : soVO.getSou_shipment_fee()%>" readonly/></td>
	</tr>
	<tr>
		<td>特產訂單總價:</td>
		<td><input type="TEXT" name="sou_order_sum_price" size="45"
			 value="3000" readonly/></td>
	</tr>
	<tr>
		<td>特產訂單備註:</td>
		<td><input type="TEXT" name="sou_order_remarks" size="45"
			 value="<%= (soVO==null)? "" : soVO.getSou_order_remarks()%>" /></td>
	</tr>
	<tr>
		<td>特產訂單運送方式:</td>
		<td><input type="TEXT" name="sou_shipping_method" size="45"
			 value="<%= (soVO==null)? "0" : soVO.getSou_shipping_method()%>" /></td>
	</tr>
	<tr>
<!-- 		<td>特產訂單狀態:</td> -->
		<td><input type="hidden" name="sou_order_status" size="45"
			 value="<%= (soVO==null)? "0" : soVO.getSou_order_status()%>" /></td>
	</tr>
	<tr>
<!-- 		<td>特產訂單付款狀態:</td> -->
		<td><input type="hidden" name="sou_payment_status" size="45"
			 value="<%= (soVO==null)? "0" : soVO.getSou_payment_status()%>" /></td>
	</tr>
	<tr>
<!-- 		<td>特產訂單出貨狀態:</td> -->
		<td><input type="hidden" name="sou_shipment_status" size="45"
			 value="<%= (soVO==null)? "0" : soVO.getSou_shipment_status()%>" /></td>
	</tr>

</table>
</table>
<p><a href="<%=request.getContextPath()%>/front-mem-end/souvenir/souvenir.jsp">繼續購物</a>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</center>
</body>
</html>
