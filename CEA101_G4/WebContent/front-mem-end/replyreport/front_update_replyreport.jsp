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

<body id=body>
	<%@include file="/front-mem-end/bar.jsp"%>
	<section id="sidebar">
		<div id="sidebar-nav">
			<ul>
				<li><a
					href="<%=request.getContextPath()%>/front-mem-end/reply/front_select_reply.jsp"><i
						class="fa"></i>�ڪ����װ�</a></li>
				<li><a
					href="<%=request.getContextPath()%>/front-mem-end/replyreport/front_select_replyreport.jsp"><i
						class="fa"></i>�������|�d��</a></li>

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
						<b>
							<li class="nav-item" id="active">
								<div class="font-icon">
									<a class="nav-link" href="#">��J</a>
								</div>
						</li>
							<li class="nav-item" id="active">
								<div class="font-icon">
									<a class="nav-link" href="#">����</a>
								</div>
						</li>
							<li class="nav-item" id="active">
								<div class="font-icon">
									<a class="nav-link" href="#">���§</a>
								</div>
						</li>
							<li class="nav-item" id="active">
								<div class="font-icon">
									<a class="nav-link" href="#">�[�J���x</a>
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
						</b>
					</ul>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="content-header">
				<h1>�������|���</h1>
			</div>
			<%@ include
				file="/front-mem-end/replyreport/update_replyreport_input.jsp"%>

		</div>
	</section>
</body>
</html>