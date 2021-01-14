
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reply.model.*"%>
<%@ page import="java.io.*,java.util.*,javax.servlet.*,java.text.*"%>

<%
	ReplyVO replyVO = (ReplyVO) request.getAttribute("replyVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	System.out.println(replyVO);
%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">


<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>評論留言修改</title>

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
	height: 80%;
}

#backhome {
	text-align: center;
}

.input-group-text {
	background-color: white;
}

.nav {
	height: 60px;
}

.nav ul {
	height: 64px;
	float: right;
}
</style>

</head>
<body bgcolor='white'>



	<h4>評論資料修改:</h4>

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
				<td><b>評論編號 </td>
				<td><font><input type="hidden" name="replyId"
						value="${replyVO.replyId}"><%=replyVO.getReplyId()%></font></td>

			</tr>
		</div>
		<div id=actrow>
			<tr>
				<td>會員編號</td>
				<td><font><input type="hidden" name="memId"
						value="<%=replyVO.getMemId()%>"><%=replyVO.getMemId()%></font></td>
			</tr>
		</div>
		<div id=actrow>
			<tr>
				<td>活動評論時間:</td>
				<td><input type="hidden" name="replyTime"
					value="<%=replyVO.getReplyTime()%>"> <%
 	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	out.print(ft.format(replyVO.getReplyTime()));
 %></td>
			</tr>
		</div>

		<div id=actrow>

			<td>活動期別編號:</td>
			<td><input type="hidden" name="actId"
				value="<%=replyVO.getActId()%>"><%=replyVO.getActId()%></td>
		</div>

		<div id=actrow>

			<td>活動評論內容:<textarea name="replyContent" class="form-control"
					aria-label="With textarea"><%=replyVO.getReplyContent()%></Textarea>
		</div>

		<div id=actrow>

			<td>活動評論狀態:</td>
			<td><input type="radio" name="replyVisible" size="45" value="0" />不顯示
				<input type="radio" name="replyVisible" size="45" value="1" />顯示</td> </b>

		</div>


		<!-- 	<JSP:USEBEAN ID="REPLYSVC" SCOPE="PAGE" CLASS="COM.REPLY.MODEL.REPLYSERVICE" /> -->
		<!-- 	<tr> -->
		<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
		<!-- 		<td><select size="1" name="deptno"> -->
		<%-- 			<c:forEach var="deptVO" items="${replySvc.all}"> --%>
		<%-- 				<option value="${replyVO.replyId}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
		<%-- 			</c:forEach> --%>
		<!-- 		</select></td> -->
		<!-- 	</tr> -->


		<div id=submit>
			<input type="hidden" name="action" value="update"> <input
				type="hidden" name="replyId" value="<%=replyVO.getReplyId()%>">
			<input type="hidden" name="replyId" value="<%=replyVO.getActId()%>">
			<input type="hidden" name="replyId"
				value="<%=replyVO.getReplyContent()%>"> <input type="hidden"
				name="replyId" value="<%=replyVO.getReplyTime()%>"> <input
				type="hidden" name="forEmp" value="forEmp"> <input
				type="submit" class="btn btn-success" value="送出修改">
		</div>
	</FORM>

	<div id=backhome>
		<tr>
			<td>
				<h3>
					<a
						href="<%=request.getContextPath()%>/back-end/reply/back_AllReply.jsp"
						class="btn btn-dark">回上頁</a>
				</h3>
			</td>
		</tr>
	</div>
</body>




</html>