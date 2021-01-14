<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.souvenir_order_detail.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listSouvenirOrderDetails_ByCompositeQuery" scope="request" type="java.util.List<SouvenirOrderDetailVO>" />
<jsp:useBean id="soupSvc" scope="page" class="com.souvenir_product.model.SouvenirProductService" />


<html>
<head><title>複合查詢 - listSouvenirOrderDetails_ByCompositeQuery.jsp</title>

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
<h4>
<!-- ☆萬用複合查詢  - 可由客戶端 select_page.jsp 隨意增減任何想查詢的欄位<br> -->
<!-- ☆此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能</h4> -->
</div>

<table class="table table-dark table-striped">
	<tr>
		<th>特產訂單編號</th>
		<th>特產編號</th>
		<th>數量</th>
		<th>價格</th>
		
	</tr>
	<c:forEach var="sodVO" items="${listSouvenirOrderDetails_ByCompositeQuery}">
		
		<tr>
			<td>${sodVO.sou_order_id}</td>
			<td>${soupSvc.getOneSou(sodVO.sou_id).sou_name}</td>
			<td>${sodVO.sou_order_amount}</td>
			<td>${sodVO.sou_price}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/souvenir_order_detail/souvenir_order_detail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="sou_order_id"  value="${sodVO.sou_order_id}">
			     <input type="hidden" name="sou_id"  value="${sodVO.sou_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
</div>
</body>
</html>