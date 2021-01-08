<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reply.model.*"%>
<%@ page import="java.io.*,java.util.*,javax.servlet.*,java.text.*"%>

<%
	ReplyVO replyVO = (ReplyVO) request.getAttribute("replyVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
	System.out.println(replyVO);
%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">


<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>���ׯd���ק�</title>

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



	<h3>���׸�ƭק�:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
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
				<td><b>���׽s�� </td>
				<td><font><input type="hidden"><%=replyVO.getReplyId()%></font></td>

			</tr>
		</div>
		<div id=actrow>
			<tr>
				<td>�|���s��</td>
				<td><font><input type="hidden" name="memId"
						value="<%=replyVO.getMemId()%>"><%=replyVO.getMemId()%></font></td>
			</tr>
		</div>
		<div id=actrow>
			<tr>
				<td>���ʵ��׮ɶ�:</td>
				<td><input type="hidden" name="replyTime"
					value="<%=replyVO.getReplyTime()%>"> <%
 	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	out.print(ft.format(replyVO.getReplyTime()));
 %></td>
			</tr>
		</div>
		<div id=actrow>
			<tr>
				<td>���ʴ��O�s��:</td>
				<td><input type="TEXT" class="input-group-text"
					name="actPeriodId" size="20" value="<%=replyVO.getActPeriodId()%>" /></td>
			</tr>
		</div>

		<div id=actrow>
			<tr>
				<td>���ʵ��פ��e:</td>
				<td><textarea name="replyContent" class="form-control"
						aria-label="With textarea"><%=replyVO.getReplyContent()%></Textarea>
			</tr>
		</div>

		<div id=actrow>
			<tr>
				<td>���ʵ��ת��A:</td>
				<td><input type="radio" name="replyVisible" size="45" value="0" />�����
					<input type="radio" name="replyVisible" size="45" value="1" />���</td>
				</b>
			</tr>
		</div>

		<!-- 	<JSP:USEBEAN ID="REPLYSVC" SCOPE="PAGE" CLASS="COM.REPLY.MODEL.REPLYSERVICE" /> -->
		<!-- 	<tr> -->
		<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
		<!-- 		<td><select size="1" name="deptno"> -->
		<%-- 			<c:forEach var="deptVO" items="${replySvc.all}"> --%>
		<%-- 				<option value="${replyVO.replyId}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
		<%-- 			</c:forEach> --%>
		<!-- 		</select></td> -->
		<!-- 	</tr> -->

		</table>
		<div id=submit>
			<input type="hidden" name="action" value="update"> <input
				type="hidden" name="replyId" value="<%=replyVO.getReplyId()%>">
			<input type="submit" class="btn btn-success" value="�e�X�ק�">
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




</html>