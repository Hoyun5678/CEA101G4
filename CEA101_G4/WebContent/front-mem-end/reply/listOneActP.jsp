<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.activity_period.model.*"%>
<%@ page import="com.reply.model.*"%>
<%@ page import="java.util.*"%>


<%
	ReplyVO replyVO = (ReplyVO) request.getAttribute("replyVO");
	String memId = request.getParameter("memId");
	String replyId = request.getParameter("replyId");
	String replyContent = request.getParameter("replyContent");
	String actId = request.getParameter("actId");

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

textarea.form-control {
	margin-top: 5px;
	margin-left: 240px;
	width: 800px;
	height: 100px !important;
}

#add {
	margin-left: 30px;
}

.navbar navbar-dark bg-dark {
	width: 800px !important;
	margin-left:
}

.h5, h5 {
	text-align: center;
}

#submit {
	margin-left: 585px;
}

#tbody:hover {
	background-color: e9ecef;
}

div.img_block {
	width: 600px;
	margin: 50px;
}
</style>

</head>
<body bgcolor='white'>
	<%@include file="/front-mem-end/bar.jsp"%>
	<jsp:useBean id="actphoSvc" scope="page"
		class="com.activity_photo.model.ActivityPhotoService" />
	<div class="img_block">

		<td><img class="img-fluid" id=photo
			src="<%=request.getContextPath()%>/ActivityPhoto/ActivityPhoto.do?act_id=${act_id}&action=getListActPhoByActId"
			alt="活動示意圖"></td>
		<td>${actphoSvc.getOneActPro(actperVO.act_id).act_name}</td>

	</div>
	<!-- 	<nav class="navbar navbar-dark bg-dark"> -->
	<!-- 		<a -->
	<%-- 			href='<%=request.getContextPath()%>/front-mem-end/reply/front_addReply.jsp'>新增一筆評論</a> --%>
	<!-- 	</nav> -->

	<table class="table">

		<thead class="thead-light ">
			<tr>
				<th>活動編號</th>
				<th>評論編號</th>
				<th>會員編號</th>
				<th>活動評論內容</th>
				<th>活動評論時間</th>
				<th></th>


				<!-- 				<th>活動評論狀態</th> -->
			</tr>

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
	<div id=comment>
		<h5>張貼留言</h5>
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/reply/reply.do">
			<textarea class="form-control" aria-label="With textarea"
				name="replyContent" value="" placeholder="我要留言..."></textarea>




			<input type="hidden" name="action" value="insert"> <input
				type="hidden" name="actId" value="${actId}"> <input
				type="hidden" name="memId" value="${sessionScope.memVO.mem_id}">
			<input type="hidden" name="replyVisible" value=0> <input
				type="hidden" name="byMem" value="byMem">
			<button onclick="myFunction1()" class="btn btn-success" id=submit
				type="submit">送出新增</button>
		</FORM>
	</div>
	<div id=backhome>

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
	<script>
		function myFunction1() {
			alert("留言成功");
		}
	</script>
</body>
</html>