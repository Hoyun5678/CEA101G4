<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.room.model.*"%>

<jsp:useBean id="roomSvc" scope="page" class="com.room.model.RoomService" />
<jsp:useBean id="roomphotoSvc" scope="page" class="com.roomphoto.model.RoomPhotoService" />
<jsp:useBean id="sellSvc" scope="page" class="com.sell.model.SellService" />
<html>
<head>
	
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-mem-end/front-mem-room.css">
    <style type="text/css">
    img {
     	weight: 100%;
     }
     .container-fluid .carousel-inner img {
        height:650px !important;
     }
     .gMap {
     	overflow: hidden;
     }
     iframe {
     	width: 100%;
     	border-radius: 10px;
     }
     
</style>
    
<title>房間搜尋</title>

</head>
<body style="background-color: #40444e;">
	<%@include file="/front-mem-end/front-nav-bar.jsp" %>
	<div class="container-fluid p-0" >
		<div id="carouselExampleFade" class="carousel slide carousel-fade"
			data-interval="3000" data-ride="carousel" data-pause="false">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="<%=request.getContextPath()%>/image/room_list/1.jfif"
						class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="<%=request.getContextPath()%>/image/room_list/2.jfif"
						class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="<%=request.getContextPath()%>/image/room_list/3.jfif"
						class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="<%=request.getContextPath()%>/image/room_list/4.jfif"
						class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="<%=request.getContextPath()%>/image/room_list/5.jfif"
						class="d-block w-100" alt="...">
				</div>
				
			</div>
	</div>
	</div>
	
	
	<div class="container">

		<c:set var="noResult" scope="page" value="${param.noResult}" />
		<c:if test="${not empty noResult}">
			<div class="container">
				<div class="alert alert-dark" role="alert">
					<strong>${noResult}</strong>
				</div>
			</div>
		</c:if>
		<c:set var="list" value="${param.list}" scope="page" />
		<div class="container" id="searchResultRoomList">
			<c:forEach var="roomVO" items="${not empty list ? list: roomSvc.all}" varStatus="rowStatus">
			<div class="row roomContent">
				<div class="col-4 imgCol">
					<div id="indicators${rowStatus.index}" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<c:forEach var="roomphotoVO" items="${roomphotoSvc.getByRoomId(roomVO.roomId)}" varStatus="status">
								<li data-target="#indicators${rowStatus.index}" data-slide-to='<c:out value="${status.index}" />' class=""></li>
							</c:forEach>
						</ol>
						<div class="carousel-inner">
							<c:forEach var="roomphotoVO" items="${roomphotoSvc.getByRoomId(roomVO.roomId)}">
							<div class="carousel-item">
								<img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomphotoVO.roomPhotoId}&action=getOnePhoto&scaleSize=500" class="d-block">
							</div>
							</c:forEach>
			
						</div>
						<a class="carousel-control-prev" href="#indicators${rowStatus.index}" role="button" data-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a>
						<a class="carousel-control-next" href="#indicators${rowStatus.index}" role="button" data-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
				</div>
				<div class="col-3 desCol">
					房間名稱: ${roomVO.roomName}<br>
					住宿價格/天: ${roomVO.roomPrice}<br>
					房間容納人數: ${roomVO.roomCapacity}<br>
<%-- 					房間敘述: ${roomVO.roomDes} --%>
				</div>
			<div class="col-1">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room/room.do">
				     <input type="hidden" name="roomId"  value="${roomVO.roomId}">
				     <input type="hidden" name="datefilter"  value="${datefilter}">
				     <input type="hidden" name="action"	value="checkRoomDetail">
				     <button type="submit" class="btn btn-info detailBtn">查看</button>
				</FORM>
			</div>
			<c:set var='sellVO' value='${sellSvc.getOneSell(roomVO.sellMemId)}' scope='page' />
			<div class="col-4 gMap">
				<iframe 
				    width="360"
				    height="200"
					frameborder="0" 
					style="border:0"
					src="https://www.google.com/maps/embed/v1/view?key=AIzaSyDK-5rFDe76_LASpBLJJSHYd1JM7W9ttWg&zoom=14&center=${sellVO.sellLatitude},${sellVO.sellLongitud}">
				</iframe>
			</div>
			</div>
	
		</c:forEach>
	
		</div>
	</div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	<script>
    	$(document).ready(function() {
    		$(".carousel-indicators li:first-child").addClass("active");
			$(".carousel-inner .carousel-item:first-child").addClass("active");
    	});
	</script>
    
</body>
</html>