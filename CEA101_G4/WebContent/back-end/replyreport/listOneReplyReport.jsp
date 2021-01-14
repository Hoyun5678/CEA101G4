<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.replyreport.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	ReplyReportVO replyReportVO = (ReplyReportVO) request.getAttribute("replyReportVO");
	//EmpServlet.java(Concroller), 存入req的replyReportVO物件
%>

<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>評論資料 - listOneReplyReport.jsp</title>

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

	<h3>評論編號資料 - ListOneReplyReport.jsp</h3>

	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th>評論檢舉編號</th>
				<th>員工編號</th>
				<th>評論編號</th>
				<th>會員編號</th>
				<th>評論檢舉結果</th>
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
						href="<%=request.getContextPath()%>/back-end/replyreport/back_select.jsp"
						class="btn btn-dark">回首頁</a>
				</h4>
			</td>
		</tr>
	</div>
</body>
</html>