<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script src="https://kit.fontawesome.com/0316f9a1d0.js"
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/front-mem-end/front-reply.css">


</head>
<style>
.nav-logo-image img {
	width: 50%;
	float: left;
	margin-left: 60px;
}

.nav-item {
	height: 60px;
}

.nav-profile {
	height: 60px;
}
</style>

<body id=body>
	<section id="sidebar">
		<div class="white-label">
			<div class="nav-logo-image">
				<a
					href="<%=request.getContextPath()%>/front-mem-end/front-index.jsp">
					<img src="<%=request.getContextPath()%>/image/LOGO/logo.png"
					alt="SuperGoing">
				</a>
			</div>
		</div>
		<div id="sidebar-nav">
			<ul>
				<li class="active"><a
					href="<%=request.getContextPath()%>/front-mem-end/front-index.jsp"><i
						class="fa"></i> 首頁</a></li>
				<li><a
					href="<%=request.getContextPath()%>/front-mem-end/reply/front_select_reply.jsp"><i
						class="fa"></i>評論區</a></li>
				<li><a
					href="<%=request.getContextPath()%>/front-mem-end/replyreport/front_select_replyreport.jsp"><i
						class="fa"></i>評論檢舉查詢</a></li>
				<li><a href="#"><i class="fa"></i>會員通知查詢</a></li>
				<li><a href="#"><i class="fa"></i>民宿會員通知查詢</a></li>
				<li><a href="#"><i class="fa"></i>FAQ</a></li>

			</ul>
		</div>
	</section>
	<section id="content">
		<div id="header">
			<div class="header-nav">
				<div class="menu-button">
					<!--<i class="fa fa-navicon"></i>-->
				</div>
				<div class="nav">
					<ul>
						<li class="nav-item" id="active">
							<div class="font-icon">
								<a class="nav-link" href="#">住宿</a>
							</div>
						</li>
						<li class="nav-item" id="active">
							<div class="font-icon">
								<a class="nav-link" href="#">體驗</a>
							</div>
						</li>
						<li class="nav-item" id="active">
							<div class="font-icon">
								<a class="nav-link" href="#">伴手禮</a>
							</div>
						</li>
						<li class="nav-item" id="active">
							<div class="font-icon">
								<a class="nav-link" href="#">加入平台</a>
							</div>
						</li>
						<li class="nav-profile">
							<div class="nav-profile-name">

								<a
									href="<%=request.getContextPath()%>/front-mem-end/mem/memLogin.jsp">
									<i class="fas fa-bars 4x" id="list"></i> <i
									class="far fa-user-circle fa-2x" id="user"></i>
								</a>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="content-header">
				<h1>評論檢舉資料</h1>
			</div>
			<%@ include file="/front-mem-end/replyreport/update_replyreport_input.jsp"%>

		</div>
	</section>
</body>
</html>