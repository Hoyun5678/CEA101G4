<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.room.model.*"%>

<jsp:useBean id="roomSvc" scope="page" class="com.room.model.RoomService" />
<jsp:useBean id="roomphotoSvc" scope="page" class="com.roomphoto.model.RoomPhotoService" />
<html>
<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-mem-end/front-mem-room.css">
    
<title>房間搜尋</title>

</head>
<body style="background-color: #40444e;">

<%@include file="/front-mem-end/front-nav-bar.jsp" %>
<div class="container" style="margin-top: 200px;">

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
					<img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomphotoVO.roomPhotoId}&action=getOnePhoto" class="d-block">
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
		<div class="col-7 desCol">
			房間名稱: ${roomVO.roomName}<br>
			住宿價格/天: ${roomVO.roomPrice}<br>
			房間容納人數: ${roomVO.roomCapacity}<br>
			房間敘述: ${roomVO.roomDes}


		</div>
		<div class="col">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room/room.do">
			     <input type="hidden" name="roomId"  value="${roomVO.roomId}">
			     <input type="hidden" name="action"	value="checkRoomDetail">
			     <button type="submit" class="btn btn-secondary detailBtn">查看</button>
			</FORM>
		</div>
		</div>

	</c:forEach>

	</div>
</div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	<script>
    	$(document).ready(function() {
			$(".carousel-indicators li:first-child").addClass("active");
			$(".carousel-inner .carousel-item:first-child").addClass("active");
			
			
		    $(window).scroll(function () {
		        if ($(window).scrollTop() >= 300) {
		            $('.header').addClass('upup');
		            $('.cc').hide();
		            $('.justify-content-md-center').addClass('shsh');
		            $('.header').removeClass('open');
		            $('.shsh').click(function () {
		                $('.header').removeClass('upup');
		                $('.header').addClass('open');
		                $('.cc').show();
		                $('.justify-content-md-center').removeClass('shsh');
		            });
		        } else {
		            $('.header').removeClass('upup');
		            $('.header').removeClass('open');
		            $('.cc').show();
		            $('.justify-content-md-center').removeClass('shsh');
		        }
		    });
			
		});
	</script>
    
</body>
</html>