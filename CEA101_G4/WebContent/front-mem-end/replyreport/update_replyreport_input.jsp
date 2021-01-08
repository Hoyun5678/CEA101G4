<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.replyreport.model.*"%>
<%@ page import="java.io.*,java.util.*,javax.servlet.*,java.text.*"%>

<%
	ReplyReportVO replyReportVO = (ReplyReportVO) request.getAttribute("replyReportVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
	System.out.println(replyReportVO);
%>
<html>
<head>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�������|��ƭק�</title>

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

.nav {
	height: 60px;
}

.nav ul {
	height: 64px;
	float: right;
}

.input-group-text {
	background-color: white;
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
		ACTION="<%=request.getContextPath()%>/replyReport/replyReport.do"
		name="form1">

		<div id=actrow>
			<tr>
				<td><b>�������|�s��:<font color=red><b></b></font></td>
				<td><%=replyReportVO.getReportId()%></td><br>
			</tr>
			<br>
			<tr>
				<td>���׭��u�s��:</td>
				<td><input type="TEXT" name="empId" class="input-group-text" 
					size="20" value="<%=replyReportVO.getEmpId()%>" /></td>
			</tr>
		</div>
		<div id=actrow>
			<tr>
				<td>�|���s��:</td>
				<td><input type="TEXT" name="memId" class="input-group-text" 
					size="20" value="<%=replyReportVO.getMemId()%>" /></td>
			</tr>
		</div>
		<div id=actrow>
			<tr>
				<td>���׽s��:</td>
				<td><input type="TEXT" name="replyId" class="input-group-text" 
					size="20" value="<%=replyReportVO.getReplyId()%>" /></td>
			</tr>
		</div>
		<div id=actrow>
			<tr>
				<td>���|���G���A:</td>
				<td><input type="radio" name="reportResult" size="45" value="0" />�ݳB�z
					<input type="radio" name="reportResult" size="45" value="1" />�w�B�z�P�N
					<input type="radio" name="reportResult" size="45" value="2" />�w�B�z���P�N</td></b>
			</tr>
		</div>


		<!-- 	<JSP:USEBEAN ID="REPLYSVC" SCOPE="PAGE" CLASS="COM.REPLY.MODEL.REPLYSERVICE" /> -->
		<!-- 	<tr> -->
		<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
		<!-- 		<td><select size="1" name="deptno"> -->
		<%-- 			<c:forEach var="deptVO" items="${replySvc.all}"> --%>
		<%-- 				<option value="${replyReportVO.reportId}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
		<%-- 			</c:forEach> --%>
		<!-- 		</select></td> -->
		<!-- 	</tr> -->

		<div id=submit>
			<input type="hidden" name="action" value="update"> <input
				type="hidden" name="reportId"
				value="<%=replyReportVO.getReportId()%>"> <input
				type="submit" class="btn btn-success" value="�e�X�ק�">
		</div>
	</FORM>

	<br>
	<div id=backhome>

		<h4>
			<a
				href="<%=request.getContextPath()%>/replyreport/front_select_replyreport.jsp"
				class="btn btn-dark">����</a>
		</h4>

	</div>
</body>




</html>