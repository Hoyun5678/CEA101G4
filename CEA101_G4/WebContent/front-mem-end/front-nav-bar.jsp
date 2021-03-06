<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Super Going</title>
<meta charset="UTF-8">
<meta name="description" content="wonderful glamping">
<meta name="keywords" content="wonderful, naga, glamping">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/index-nav-bar.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/index-nav-bar.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script type="text/javascript"
	src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
	integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
	crossorigin="anonymous"></script>
<!-- UIkit CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/js/uikit/uikit.min.css">
<!-- UIkit JS -->
<script src="<%=request.getContextPath()%>/js/uikit/uikit.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
</head>
<body>
<header>
		<div class="ceee">
			<div class="header">
				<div class="navone">
					<a href="<%=request.getContextPath()%>/front-mem-end/front-index.jsp"><img
						src="<%=request.getContextPath()%>/image/LOGO/logo.png" alt=""
						id="navlogo"></a>
					<div class="align-self-center underline" id="room_act">
						<h4>住宿</h4>
					</div>
					<div class="align-self-center underline" id="act_act">
						<h4>體驗活動</h4>
					</div>
					<div class="align-self-center underline" id="sou_act">
						<a href="<%=request.getContextPath()%>/front-mem-end/souvenir/souvenir.jsp"><h4>特產</h4></a>
					</div>
					<div class="align-self-center underline" style="white-space:nowrap;">
						<c:if test="${not empty sessionScope.memVO}">
							<a href="<%=request.getContextPath()%>/front-mem-end/mem/listOneMem.jsp"><h4>${memVO.mem_name}你好<i class="fas fa-child fa-1x" style="color: ghostwhite;"></i></h4></a>
						</c:if>

						<c:if test="${empty sessionScope.memVO}">
							<a href="<%=request.getContextPath()%>/front-mem-end/mem/memLogin.jsp"><h4>登入會員</h4></a>
						</c:if>
					</div>
				</div>
				<div class="navtwo">
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/room/room.do" id="formRoom">
				<c:set var="sel" scope="page" value="${param.sel}" />
				<c:set var="datefilter" scope="page" value="${param.datefilter}" />
				<c:set var="roomCapacity" scope="page" value="${param.roomCapacity}" />
					<div class="container" id="room">
						<div class="justify-content-md-center">
							<div class="align-self-center cc ">
								地點： <input type="text" name="sel" value='${ not empty sel ? sel : "" }' autocomplete="off">
							</div>
							<div class="align-self-center cc ">
								入住與退房日期：<input type="text" name="datefilter" size="23" readonly autocomplete="off" value='${ not empty datefilter ? datefilter : "" }' />
							</div>
							<div class="align-self-center cc ">
								人數：<input type="number" value="2" min="1" max="20" autocomplete="off" step="1" name="roomCapacity" value='${ not empty roomCapacity ? roomCapacity : "" }'>
							</div>
							<input type="hidden" name="action" value="getByMultiCondition" />
							<div class="bigbig">
								<label id="roomSearchBtn"
									for="checkyes"><span class="fas fa-search"></span>
								</label>
								<label class='smallBtn d-none' 
									for="checkyes"><span class="fas fa-search"></span>
								</label>
							</div>
						</div>
					</div>
				</form>
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/ActivityPeriod/ActivityPeriod.do" name="form1" id="foract">
				<c:set var="key_word" scope="page" value="${param.key_word}" />
				<c:set var="datefilter1" scope="page" value="${param.datefilter1}" />
					<div class="container" id="act">
						<div class="justify-content-md-center">
							<div class="align-self-center cc ">
								關鍵字：<input type="text" name="key_word" autocomplete="off">
							</div>
							<div class="align-self-center cc ">
								體驗日期：<input type="text" name="datefilter1" value="" onkeydown="return false" autocomplete="off" />
							</div>
							<div class="bigbig">
								<label id="actSearchBtn"
									for="checkyes2"><span class="fas fa-search"></span>
								</label>
								<label class='smallBtn d-none' 
									for="checkyes2"><span class="fas fa-search"></span>
								</label>
							</div>
						</div>
					</div>
					<input type="hidden" name="action" value="getActPeriodByKeyDate">
				</form>
				</div>
			</div>
		</div>
	</header>
</body>
</html>