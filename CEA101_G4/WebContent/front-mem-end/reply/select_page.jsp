<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>SP Reply: Home</title>

<style>
#sidebar {
	height: 100%;
}

#content {
	height: 90%;
}

.content {
	width: 98%;
	height: 95%;
}

#container {
	height: 96%;
	margin-left: 1px;
	padding: 50px;
	background-color: #E9EEF4;
}

.table td, .table th {
	padding: .5rem;
	vertical-align: top;
	border-top: 1px solid #dee2e6;
}

div>li {
	text-align: center;
	margin: 1px;
	text-align: center;
}

b, strong {
	color: #17a2b8;
}

a {
	color: white;
}

a:hover {
	color: #dee2e6;
}

.navbar {
	margin-top: 80px;
}

.nav-profile {
	height: 95%;
}
</style>

</head>
<body>


	<div id=container>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">Error Message:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<nav class="navbar navbar-dark bg-dark">
			<a
				href='<%=request.getContextPath()%>/front-mem-end/reply/front_AllReply.jsp'>列出我的所有評論</a>
			<a
				href='<%=request.getContextPath()%>/front-mem-end/reply/front_addReply.jsp'>新增一筆評論</a>

			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/reply/reply.do">
				<b>輸入評論編號</b> <input type="text" placeholder="RPL001" name="replyId">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="Search" class="btn btn-info">

			</FORM>
		</nav>

		<jsp:useBean id="replySvc" scope="page"
			class="com.reply.model.ReplyService" />
		<br>
		<div>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/reply/reply.do">
				<b>輸入活動編號</b> <input type="text" placeholder="ACT001" name="actId">
				<input type="hidden" name="action" value="getOne_By_ActId">
				<input type="submit" value="Search" class="btn btn-info">

			</FORM>
		</div>


		<!-- 		<ul> -->
		<!-- 			<li id=list> -->
		<!-- 				<FORM METHOD="post" -->
		<%-- 					ACTION="<%=request.getContextPath()%>/reply/reply.do"> --%>
		<!-- 					<b>選擇評論編號 :</b> <select size="1" name="replyId"> -->
		<%-- 						<c:forEach var="replyVO" items="${replySvc.all}"> --%>
		<%-- 							<option class="dropdown-item" value="${replyVO.replyId}">${replyVO.replyId} --%>
		<%-- 						</c:forEach> --%>
		<!-- 					</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
		<!-- 					<input type="submit" value="送出" class="btn btn-info"> -->
		<!-- 				</FORM> -->
		<!-- 			</li> -->

		<!-- 			<li id=list> -->
		<!-- 				<FORM METHOD="post" -->
		<%-- 					ACTION="<%=request.getContextPath()%>/reply/reply.do"> --%>
		<!-- 					<b>選擇會員編號:</b> <select size="1" name="replyId"> -->
		<%-- 						<c:forEach var="replyVO" items="${replySvc.all}"> --%>
		<%-- 							<option class="dropdown-item" value="${replyVO.replyId}">${replyVO.memId} --%>
		<%-- 						</c:forEach> --%>
		<!-- 					</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
		<!-- 					<input type="submit" value="送出" class="btn btn-info"> -->
		<!-- 				</FORM> -->
		<!-- 			</li> -->

		<!-- 		</ul> -->
	</div>
</body>
</html>