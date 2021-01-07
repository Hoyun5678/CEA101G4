<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reply.model.*"%>

<%
	ReplyVO replyVO = (ReplyVO) request.getAttribute("replyVO");
%>
<%-- <%= replyVO==null %>--${replyVO.deptno}-- --%>

<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">


<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>評論留言新增 - addReply.jsp</title>

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
	text-align: center;
	width: 80%;
	margin-left: 40px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
	margin-left: 40px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 2px;
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

.input-group-text:hover {
	background-color: white;
}
</style>

</head>
<body bgcolor='white'>



	<h3>評論資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/reply/reply.do" name="form1">

		<div id=actrow>
			<tr>
				<td>活動期別編號:</td>
				<td><input type="TEXT" class="input-group-text"
					name="actPeriodId"
					value="<%=(replyVO == null) ? "AP00" : replyVO.getActPeriodId()%>" /></td>
			</tr>
		</div>
		<div id=actrow>
			<tr>
				<td>一般會員編號:</td>
				<td><input type="TEXT" class="input-group-text" name="memId"
					value="<%=(replyVO == null) ? "MEM00" : replyVO.getMemId()%>" /></td>
			</tr>
		</div>
		<div id=actrow>
			<tr>
				<td>活動評論內容:</td>

				<td><textarea class="form-control" aria-label="With textarea"
						name="replyContent"
						value="<%=(replyVO == null) ? "" : replyVO.getReplyContent()%>"></textarea></td>
			</tr>
		</div>
	</form>



	<div id=actrow>
		<tr>
			<td>活動評論狀態:</td>
			<td><input type="radio" name="replyVisible" size="45" value="0" />不顯示
				<input type="radio" name="replyVisible" size="45" value="1" />顯示</td>
		</tr>
	</div>
	<div id=submit>
		<input type="hidden" name="action" value="insert"> <input
			type="submit" class="btn btn-success" value="送出新增">

	</div>
	</FORM>
	<br>
	<div id=backhome>
		<tr>
			<td>
				<h3>
					<a href="<%=request.getContextPath()%>/front-mem-end/reply/front_select_reply.jsp"
						class="btn btn-dark">回上頁</a>
				</h3>
			</td>
		</tr>
	</div>

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


</html>