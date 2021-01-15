<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.replyreport.model.*"%>


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
				<th>會員編號</th>
				<th>檢舉編號</th>
				<th>評論編號</th>
				<th></th>
				<th></th>

			</tr>

			<jsp:useBean id="replyReportSvc" scope="page"
				class="com.replyreport.model.ReplyReportService" />
		</thead>

		<c:forEach var="replyReportVOlist"
			items="${replyReportSvc.getReplyReportByMemId(sessionScope.memVO.mem_id)}">

				<tbody id=tbody>

					<tr>
						<td>${replyReportVOlist.memId}</td>
						<td>${replyReportVOlist.reportId}</td>
						<%-- 					<td>${replyReportVOlist.empId}</td> --%>
						<td>${replyReportVOlist.replyId}</td>
						<%-- 					<td>${replyReportVOlist.reportResult}</td> --%>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/replyReport/replyReport.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="修改" class="btn btn-info"> <input
									type="hidden" name="reportId" value="${replyReportVO.reportId}">
								<input type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/replyReport/replyReport.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="刪除" class="btn btn-danger">
								<input type="hidden" name="reportId"
									value="${replyReportVO.reportId}"> <input type="hidden"
									name="action" value="delete">
							</FORM>
						</td>
					</tr>
			</c:forEach>
			</tbody>
	</table>
	<section id=page>

		<br>
		<h2>
			<a
				href="<%=request.getContextPath()%>/front-mem-end/replyreport/front_select_replyreport.jsp"
				class="btn btn-dark">回上頁</a>
		</h2>
	</section>
</body>
</html>