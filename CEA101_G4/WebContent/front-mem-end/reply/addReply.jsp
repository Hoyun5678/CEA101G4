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
<title>���ׯd���s�W - addReply.jsp</title>

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

.input-group-text {
	background-color: white;
}
</style>

</head>
<body bgcolor='white'>



	<h3>���׸�Ʒs�W:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: blue">�Ч�����g:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: blue">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/reply/reply.do" name="form1">

		<div id=actrow>
			<tr>
				<td><b>���ʽs��:</td>
				<td><input type="TEXT" class="input-group-text"
					name="actId"
					value="<%=(replyVO == null) ? "AP00" : replyVO.getActId()%>" /></td>
			</tr>
		</div>
		<div id=actrow>
		<jsp:useBean id="replySvc" scope="page" class="com.reply.model.ReplyService"/>
			
			<tr>
				<td>�@��|���s��:</td>
				<td><input type="TEXT" class="input-group-text" name="memId"
					value="${sessionScope.memVO.mem_id}" /></td>
			</tr>
		</div>
		<div id=actrow>
			<tr>
				<td>���ʵ��פ��e:</td>

				<td><textarea class="form-control" aria-label="With textarea"
						name="replyContent"
						value="<%=(replyVO == null) ? "" : replyVO.getReplyContent()%>"></textarea></td>
			</tr>
		</div>




		<div id=actrow>
<!-- 			<tr> -->
<!-- 				<td>���ʵ��ת��A:</td> -->
<!-- 				<td><input type="radio" name="replyVisible" size="45" value="0" />����� -->
<!-- 					<input type="radio" name="replyVisible" size="45" value="1" />���</td></b> -->
<!-- 			</tr> -->
		</div>
		<div id=submit>
			<input type="hidden" name="action" value="insert"> 
			<input type="hidden" name="replyVisible" value=0>
			<input
				type="submit" class="btn btn-success" value="�e�X�s�W">
		</div>
	</FORM>

	<br>
	<div id=backhome>
		<tr>
			<td>
				<h3>
					<a
						href="<%=request.getContextPath()%>/front-mem-end/reply/front_select_reply.jsp"
						class="btn btn-dark">�^�W��</a>
				</h3>
			</td>
		</tr>
	</div>

</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->


</html>