<< HEAD
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reply.model.*"%>

<%
	ReplyService replySvc = new ReplyService();
	List<ReplyVO> list = replySvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有評論資料</title>
</head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>Document</title>
<style>
#sidebar {
	height: 110%;
}

#content {
	height: 95%;
}

.content {
	width: 98%;
	height: 90%;
}

h4 {
	color: blue;
	display: inline;
}

table {
	width: 60%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

#tbody:hover {
	background-color: white;
}

/* table, th, td { */
/* 	border: 1px solid #CCCCFF; */
/* } */
th, td {
	padding: 5px;
	text-align: center;
}

#page {
	padding: 1px;
	height: 40px;
	text-align: center;
	height: 40px;
}

h2 {
	text-align: center;
}

.table .thead-dark th {
	font-size: 12px;
}

.table td, .table th {
	font-size: 12px;
}
</style>

<body bgcolor='white'>

	<!-- 	<h4>此頁練習採用 EL 的寫法取值:</h4> -->




	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th>評論編號</th>
				<th>活動期別編號</th>
				<th>會員編號</th>
				<th>活動評論內容</th>
				<th>活動評論時間</th>
				<th>活動評論狀態</th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="replyVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
		</thead>
		<tbody id=tbody>
			<tr>
				<td>${replyVO.replyId}</td>
				<td>${replyVO.actId}</td>
				<td>${replyVO.memId}</td>
				<td>${replyVO.replyContent}</td>
				<td><fmt:formatDate value="${replyVO.replyTime}" type="both" /></td>
				<td>${replyVO.replyVisible}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/reply/reply.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改" class="btn btn-info"> <input
							type="hidden" name="replyId" value="${replyVO.replyId}">
						<input type="hidden" name="forEmp" value="forEmp"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/reply/reply.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除" class="btn btn-danger"> <input
							type="hidden" name="replyId" value="${replyVO.replyId}">
						<input type="hidden" name="forEmp" value="forEmp"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
				<!-- 				<td> -->
				<!-- 					<FORM METHOD="post" -->
				<%-- 						ACTION="<%=request.getContextPath()%>/replyReport/replyReport.do" --%>
				<!-- 						style="margin-bottom: 0px;"> -->
				<!-- 						<input type="submit" value="檢舉" class="btn btn-warning"> <input -->
				<%-- 							type="hidden" name="replyId" value="${replyVO.replyId}"> --%>
				<!-- 						<input type="hidden" name="action" value="insert"> -->
				<!-- 					</FORM> -->
				<!-- 				</td> -->
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<section id=page>
		<%@ include file="page2.file"%>

		<h2>
			<a
				href="<%=request.getContextPath()%>/back-end/reply/back_select.jsp"
				class="btn btn-dark">回上頁</a>
		</h2>

	</section>
</body>
</html>