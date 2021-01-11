<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>FontTopBar</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script src="https://kit.fontawesome.com/0316f9a1d0.js"
	crossorigin="anonymous"></script>
<script
	src='https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js'></script>
<link
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'>
<script
	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.8.1/baguetteBox.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front-mem-end/bar.css">
</head>
 
<body>
	<div class=”container”>
		<nav
			class="navbar navbar-expand-lg bg-light navbar-light justify-content-center"
			id="topbar">
			<a class="navbar-brand"
				href="<%=request.getContextPath()%>/front-mem-end/front-index.jsp">
				<img src="<%=request.getContextPath()%>/image/LOGO/logo.png"
				alt="Logo" id="logo">
			</a>
			<div class="ul">
				<ul class="navbar-nav">
					<li class="nav-item" id="active"><a class="nav-link" href="#">住宿</a>
					</li>
					<li class="nav-item" id="active"><a class="nav-link"
						href="<%=request.getContextPath()%>/front-mem-end/activity_period/listActivityPeriod.jsp">體驗</a>
					</li>
					<li class="nav-item" id="active"><a class="nav-link"
						href="<%=request.getContextPath()%>/front-mem-end/souvenir/souvenir.jsp">伴手禮</a>
					</li>

					<c:if test="${empty sessionScope.memVO}">
						<li class="nav-item" id="active"><a class="nav-link"
							href="<%=request.getContextPath()%>/front-mem-end/mem/memLogin.jsp">登入會員</a>
						</li>

					</c:if>
					<c:if test="${not empty sessionScope.memVO}">




						<div class="dropdown2">

							<div id="account2" onclick="myFunction2()" class="dropbtn2">
								<li class="nav-item" id="active"><a class="nav-link">購物車</a></li>

							</div>

							<div id="myDropdown2" class="dropdown-content2">
								<jsp:include page="/front-mem-end/souvenir/souvenircart.jsp"
									flush="true" />

							</div>
						</div>









						<div class="dropdown">

							<div id="account" onclick="myFunction()" class="dropbtn">
								<i class="fas fa-bars 4x" id="list"></i> <i
									class="far fa-user-circle fa-2x" id="user"></i>
							</div>

							<div id="myDropdown" class="dropdown-content">
								<a
									href="<%=request.getContextPath()%>/front-mem-end/mem/listOneMem.jsp"><i
									class="far fa-user" style="padding-right: 13px;"></i>管理帳戶</a> <a
									href="#"><i class="fas fa-hotel"></i>房間訂單</a> <a
									href="<%=request.getContextPath()%>/front-mem-end/activity_order/listOneActivityOrder.jsp"><i
									class="fas fa-umbrella-beach" style="padding-right: 8px;"></i>活動訂單</a>
								<a href="#"><i class="fas fa-gifts"
									style="padding-right: 8px;"></i>伴手禮訂單</a> <a
									href="<%=request.getContextPath()%>/roomproductcollect/roomproductcollect.do?mem_id=${memVO.mem_id}&action=showCollect"><i
									class="fab fa-gratipay" style="padding-right: 12px;"></i>我的收藏</a> <a
									href="#"><i class="far fa-comments"
									style="padding-right: 12px;"></i>評語</a> <a
									href="<%=request.getContextPath()%>/member/member.do?action=log_out"><i
									class="fas fa-sign-out-alt" style="padding-right: 12px;"></i>登出</a>
							</div>
						</div>





					</c:if>


				</ul>
			</div>
		</nav>
	</div>
	<script>
		/* When the user clicks on the button, 
		 toggle between hiding and showing the dropdown content */
		function myFunction() {
			document.getElementById("myDropdown").classList.toggle("show");
		}

		// Close the dropdown if the user clicks outside of it
		window.onclick = function(event) {
			if (!event.target.matches('.dropbtn')) {

				var dropdowns = document
						.getElementsByClassName("dropdown-content");
				var i;
				for (i = 0; i < dropdowns.length; i++) {
					var openDropdown = dropdowns[i];
					if (openDropdown.classList.contains('show')) {
						openDropdown.classList.remove('show');
					}
				}
			}
		}

		function myFunction2() {
			document.getElementById("myDropdown2").classList.toggle("show2");
		}

		// Close the dropdown if the user clicks outside of it
		window.onclick = function(event) {
			if (!event.target.matches('.dropbtn2')) {

				var dropdowns = document
						.getElementsByClassName("dropdown-content2");
				var i;
				for (i = 0; i < dropdowns.length; i++) {
					var openDropdown = dropdowns[i];
					if (openDropdown.classList.contains('show2')) {
						openDropdown.classList.remove('show2');
					}
				}
			}
		}
	</script>
</body>
</html>