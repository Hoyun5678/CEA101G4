<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.replyreport.model.*"%>

<%
	ReplyReportVO replyReportVO = (ReplyReportVO) request.getAttribute("replyReportVO");
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
	<h3>評論檢舉資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: blue">請完成填寫:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: blue">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/replyReport/replyReport.do"
		name="form1">

		<div id=actrow>
			<tr>
				<td><b>員工編號:</td>
				<td><input type="TEXT" name="empId" class="input-group-text"
					value="<%=(replyReportVO == null) ? "EMP00" : replyReportVO.getEmpId()%>" /></td>
			</tr>
		</div>
		<div id=actrow>
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="memId" class="input-group-text"
					value="<%=(replyReportVO == null) ? "MEM00" : replyReportVO.getMemId()%>" /></td>
			</tr>
		</div>
		<div id=actrow>

			<tr>
				<td>評論編號:</td>
				<td><input type="TEXT" name="replyId" class="input-group-text"
					value="<%=(replyReportVO == null) ? "RPL00" : replyReportVO.getReplyId()%>" />
				</td>
			</tr>
		</div>
		<div id=actrow>
			<tr>
				<td>檢舉結果狀態:</td>
				<td><input type="radio" name="reportResult" size="45" value="0" />待處理
					<input type="radio" name="reportResult" size="45" value="1" />已處理同意
					<input type="radio" name="reportResult" size="45" value="2" />已處理不同意</b></td>
			</tr>
		</div>
		<div id=submit>

			<br> <input type="hidden" name="action" value="insert">
			<input type="submit" class="btn btn-success" value="送出新增">
		</div>
	</FORM>

	<div id=backhome>
		<tr>
			<td>
				<h3>
					<a
						href="<%=request.getContextPath()%>/back-end/replyreport/back_select.jsp"
						class="btn btn-dark">回首頁</a>
				</h3>
			</td>
		</tr>
	</div>
</body>


</html>