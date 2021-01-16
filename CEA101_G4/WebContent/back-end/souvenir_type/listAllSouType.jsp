<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.souvenir_type.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	SouvenirTypeService soutSvc = new SouvenirTypeService();
	List<SouvenirTypeVO> list = soutSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>所有特產種類 </title>

<style>
body {
	background-color: #E5E5E5;
	text-align: center;
	    border-radius:30px;
}

table#table-1 {
	background-color: #888;
	text-align: center;
	border: solid black 2px;
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
th, td {
	padding: 5px;
	text-align: center;
}

#table-1 {
	margin: auto;
	text-align: center;
	font-size: 14px;
}

#table-1 td {
	vertical-align: middle;
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



</style>

</head>
<body>

	<div id="wrapper">
		<%@ include file="/back-end/back-index-sidebar.jsp"%>
		
		<div id="page-content-wrapper">
	<br>



	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table class="table table-dark table-striped" id="table-1">
		<thead class=".thead-dark">
			<tr>
				<th>特產種類編號</th>
				<th>特產種類名稱</th>
				<th></th>
				<th></th>
				
			</tr>
		</thead>

		<c:forEach var="soutVO" items="${list}" >

			<tr>
				<td>${soutVO.sou_type_id}</td>
				<td>${soutVO.sou_type_name}</td>
				
<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/souvenir_type/SouvenirTypeServlet"
						style="margin-bottom: 0px;">
						<input class="input-group-text" type="submit" value="修改">
						<input type="hidden" name="sou_type_id" value="${soutVO.sou_type_id}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/souvenir_type/SouvenirTypeServlet"
						style="margin-bottom: 0px;">
						<input class="input-group-text" type="submit" value="刪除">
						<input type="hidden" name="sou_type_id" value="${soutVO.sou_type_id}">
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</body>
</html>