<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.replyreport.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	ReplyReportVO replyReportVO = (ReplyReportVO) request.getAttribute("replyReportVO");
	//EmpServlet.java(Concroller), �s�Jreq��replyReportVO����
%>

<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>���׸�� - listOneReplyReport.jsp</title>

<style>
table {
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 8px;
	text-align: center;
}

#tbody {
	background-color: white;
}

#backhome {
	margin-top: 40px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h3>���׽s����� - ListOneReplyReport.jsp</h3>

	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th>�������|�s��</th>
				<th>���u�s��</th>
				<th>���׽s��</th>
				<th>�|���s��</th>
				<th>�������|���G</th>
			</tr>
		</thead>
		<tbody id=tbody>
			<tr>
				<%-- 			<td><%=replyReportVO.getReplyId()%></td> --%>

				<td>${replyReportVO.reportId}</td>
				<td>${replyReportVO.empId}</td>
				<td>${replyReportVO.replyId}</td>
				<td>${replyReportVO.memId}</td>
				<td>${replyReportVO.reportResult}</td>

			</tr>
		</tbody>
	</table>
	<br>
	<div id=backhome>
		<tr>
			<td>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-mem-end/replyreport/front_select_replyreport.jsp"
						class="btn btn-dark">�^����</a>
				</h4>
			</td>
		</tr>
	</div>
</body>
</html>