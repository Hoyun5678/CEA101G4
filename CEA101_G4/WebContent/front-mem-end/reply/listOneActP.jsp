<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.reply.model.*"%>
<%@ page import="java.util.*"%>

<%
	ReplyService replySvc = new ReplyService();
	List<ReplyVO> list = replySvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>評論資料 - listOneReply.jsp</title>

<style>
h4 {
	/* 	margin-left: 48%; */
	margin-top: 400px;
	color: blue;
	display: inline;
	color: blue;
	color: blue;
}

table {
	background-color: white;
	margin-bottom: 5px;
}

th, td {
	padding: 8px;
	text-align: center;
}

#tbody:hover {
	background-color: white;
}

#backhome {
	margin-top: 15px;
	text-align: center;
}

.figure {
	width: 640px;
	height: 360px;
	margin-top: 30px;
	margin-left: 100px;
}

.title {
	margin-top: 10px;
	margin-left: 60px;
}

.table .thead-light th {
	font-size: 12px;
}

.table .thead-light th {
	font-size: 14px;
	text-align: center;
}

.table td, .table th {
	font-size: 14px;
	text-align: center;
}
#addReply{
	margin-right:100px;
}
</style>

</head>
<body bgcolor='white'>
	<%@include file="/front-mem-end/bar.jsp"%>
	<table>
		<tr>
		
			<figure class="figure">
				<img
					src="<%=request.getContextPath()%>/ActivityPhoto/ActivityPhoto.do?act_id=${act_id}&action=getListActPhoByActId"
					class="figure-img img-fluid rounded" alt="活動示意圖">
				<figcaption class="figure-caption">活動示意圖</figcaption>
				<td>${actproSvc.getOneActPro(actperVO.act_id).act_name}</td>

			</figure>
		<tr>
		<tr id=addReply>
			<td>
				<a
					href='<%=request.getContextPath()%>/front-mem-end/reply/front_addReply.jsp'>新增一筆評論</a>
			</td>
		<tr>
	</table>

	<div class=title>
		<h3>活動留言</h3>
	</div>

	<table class="table">

		<thead class="thead-light ">
			<tr>
				<th>活動編號</th>
				<th>評論編號</th>
				<th>會員編號</th>
				<th>活動評論內容</th>
				<th>活動評論時間</th>
				<th></th>
				<th></th>

				<!-- 				<th>活動評論狀態</th> -->
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="replyVO" items="${listReplyVO}">
		</thead>
		<tbody id=tbody>

			<tr>
				<td>${replyVO.actId}</td>
				<td>${replyVO.replyId}</td>
				<td>${replyVO.memId}</td>
				<td>${replyVO.replyContent}</td>
				<td><fmt:formatDate value="${replyVO.replyTime}" type="both" /></td>
				<%-- 				<td>${replyVO.replyVisible}</td> --%>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/replyReport/replyReport.do"
						style="margin-bottom: 0px;">
						<input type="hidden" name="replyId" value="${replyVO.replyId}">
						<input type="hidden" name="memId" value="${replyVO.memId}">
						<input type="hidden" name="action" value="memInsert">
						<button type="submit" value="檢舉" class="btn btn-warning">檢舉</button>
					</FORM>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

	<div id=backhome>
		<%@ include file="page2.file"%>
		<tr>
			<td>
				<h3>
					<a
						href="<%=request.getContextPath()%>/front-mem-end/activity_period/listActivityPeriod.jsp"
						class="btn btn-dark">回上頁</a>
				</h3>
			</td>
		</tr>
	</div>

</body>
</html>