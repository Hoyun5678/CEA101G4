<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.replyreport.model.*"%>
<%@ page import="com.reply.model.*"%>

<%
	ReplyReportService replyReportSvc = new ReplyReportService();
	List<ReplyReportVO> list = replyReportSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有評論檢舉資料 - listAllReplyReport.jsp</title>
</head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>Document</title>
<style>
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
	text-align: center;
}

h2 {
	text-align: center;
}

a {
	color: #f8f9fa;
}

.content {
	height: 200%;
}
</style>

<body bgcolor='white'>


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
				<th>檢舉編號</th>
				<!-- 				<th>員工編號</th> -->
				<th>評論編號</th>
				<th>會員內容</th>
				<th>檢舉結果狀態</th>
				<th></th>
				<th></th>
				<th></th>


			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="replyReportVO" items="${list}">
		</thead>
		<tbody id=tbody>
			<tr>
				<td>${replyReportVO.reportId}</td>
				<%-- 				<td>${replyReportVO.empId}</td> --%>
				<td>${replyReportVO.replyId}</td>
				<td>${replyReportVO.memId}</td>
				<td>${replyReportVO.reportResult}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/replyReport/replyReport.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="查看" class="btn btn-info"> <input
							type="hidden" name="replyId" value="${replyReportVO.replyId}">
						<input type="hidden" name="forEmp" value="forEmp"> <input
							type="hidden" name="action" value="getOne_For_Display">
					</FORM>
				</td>


				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/replyReport/replyReport.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改" class="btn btn-info"> <input
							type="hidden" name="reportId" value="${replyReportVO.reportId}">
						<input type="hidden" name="forEmp" value="forEmp"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/replyReport/replyReport.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除" class="btn btn-danger"> <input
							type="hidden" name="reportId" value="${replyReportVO.reportId}">
						<input type="hidden" name="forEmp" value="forEmp"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
				</c:forEach>
			</tr>
		</tbody>
	</table>
	<section id=page>

		<br>
		<h2>
			<a
				href="<%=request.getContextPath()%>/back-end/replyreport/back_select.jsp"
				class="btn btn-dark">回上頁</a>
		</h2>
	</section>
</body>
</html>