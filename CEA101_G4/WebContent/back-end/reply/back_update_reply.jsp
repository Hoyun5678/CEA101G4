<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script src="https://kit.fontawesome.com/0316f9a1d0.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/back-end/back-reply.css">

<style>
.nav-logo-image img {
	width: 50%;
	float: left;
	margin-left: 60px;
}

.nav-profile {
	height: 60px;
}

.nav-item {
	height: 60px;
}

.nav {
	float: right;
}

#topbar {
	margin-top: 0px;
}

a.nav-link {
	color: #fcfdfd !important;
	font-size: 16px;
}
</style>
</head>

<body id=body>

	<section id="sidebar">
		<%@include file="/back-end/back-index-sidebar.jsp"%>
	</section>
	<section id="content">
		<div class="content">
			<div class="content-header">
				<h1>���׸�Ƭd��</h1>
			</div>
			<%@ include file="/back-end/reply/update_reply_input.jsp"%>

		</div>
	</section>
</body>
</html>