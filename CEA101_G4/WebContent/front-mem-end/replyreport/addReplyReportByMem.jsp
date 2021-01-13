<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.replyreport.model.*"%>

<%
	ReplyReportVO replyReportVO = (ReplyReportVO) request.getAttribute("replyReportVO");
	String memId = request.getParameter("memId");
	String replyId = request.getParameter("replyId");
	String replyContent = request.getParameter("replyContent");
	// 	ReplyReportVO replyReportVO1 = (ReplyReportVO) request.getAttribute("memId");
	// 	ReplyReportVO replyReportVO2 = (ReplyReportVO) request.getAttribute("replyId");
%>

<%-- <%= replyReportVO==null %>--${replyReportVO.deptno}-- --%>

<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>檢舉留言新增 - addReplyReport.jsp</title>

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

table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}

.input-group {
	width: 60%;
	height: 100;
}

tr {
	float: left;
}

h4 {
	text-align: center;
}

textarea.form-control {
	height: 80px;
	width: 70%;
}

form {
	font-size: 14px;
	margin: 20px;
}

#actrow {
	margin: 10px;
	margin-left: 50px;
}

#submit {
	text-align: center;
}

.content {
	margin: 15px;
	float: left;
	background: #E9EEF4;
	width: 90%;
}

#backhome {
	text-align: center;
}

.input-group-text {
	background-color: white;
}
</style>

</head>
<body bgcolor='white'>

	<%@include file="/front-mem-end/bar.jsp"%>
	<h3>評論檢舉資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: black"></font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: black">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/replyReport/replyReport.do"
		name="form1">

		<div id=actrow></div>
		<div id=actrow>
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="memId" class="input-group-text"
					value="<%=memId%>" /></td>
			</tr>
		</div>
		<div id=actrow>

			<tr>
				<td>評論編號:</td>
				<td><input type="TEXT" name="replyId" class="input-group-text"
					value="<%=replyId%>" /></td>
			</tr>
			<tr>
				<td>評論內容:</td>
				<td><input type="TEXT" name="replyContent" class="input-group-text"
					value="<%=replyContent%>" /></td>
			</tr>
		</div>

		<div id=submit>

			<br> <input type="hidden" name="action" value="memInsert">
			<input type="submit" onclick="myFunction()" class="btn btn-success"
				value="送出新增">
		</div>
	</FORM>

	<div id=backhome>
		<tr>
			<td>
				<h3>
					<a
						href="<%=request.getContextPath()%>/front-mem-end/activity_period/listActivityPeriod.jsp"
						class="btn btn-dark">回瀏覽頁</a>
				</h3>
			</td>
		</tr>
	</div>
	<script>
		function myFunction() {
			alert("確認送出檢舉");
		}
	</script>
</body>


</html>