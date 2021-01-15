<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>SP ReplyReport: Home</title>

<style>
#container {
	height: 96%;
	margin-left: 1px;
	padding: 50px;
	border-style: dotted;
	background-color: #E9EEF4;;
	border: 1px;
}

.table td, .table th {
	padding: .5rem;
	vertical-align: top;
	border-top: 1px solid #dee2e6;
}

div>li {
	text-align: center;
	margin: 1px;
	text-align: center;
}

b, strong {
	color: #17a2b8;
}

a {
	color: white;
}

a:hover {
	color: #dee2e6;
}

.navbar {
	margin-top: 80px;
}

.nav-profile {
	height: 95%;
}
</style>

</head>

<body>

	<div id=container>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">Error Message:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<nav class="navbar navbar-dark bg-dark">

			<a
				href='<%=request.getContextPath()%>/front-mem-end/replyreport/front_AllReplyReport.jsp''>列出我的所有評論檢舉</a>
			<a
				href='<%=request.getContextPath()%>/front-mem-end//replyreport/front_addReplyReport.jsp'>新增一筆評論檢舉</a>

			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/replyReport/replyReport.do">
				<b>輸入評論檢舉編號 (ex:REP001):</b> <input type="text" name="repsortId">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="Search" class="btn btn-info">
			</FORM>
			<jsp:useBean id="replyReportSvc" scope="page"
				class="com.replyreport.model.ReplyReportService" />


		</nav>
		<br>
		<div>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/replyReport/replyReport.do">
				<b>選擇評論檢舉編號 :</b> <select size="1" name="reportId">
					<c:forEach var="replyReportVO" items="${replyReportSvc.all}">
						<option value="${replyReportVO.reportId}">${replyReportVO.reportId}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="Search" class="btn btn-info">
			</FORM>
		</div>
</body>
</html>