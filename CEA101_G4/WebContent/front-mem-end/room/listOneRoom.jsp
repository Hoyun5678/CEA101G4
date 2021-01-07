<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.room.model.*"%>

<jsp:useBean id="roomVO" scope="request" class="com.room.model.RoomVO" />
<jsp:useBean id="roomphotoSvc" scope="page" class="com.roomphoto.model.RoomPhotoService" />
<jsp:useBean id="sellSvc" scope="page" class="com.sell.model.SellService" />
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<!--     <link rel="stylesheet" href="css/style.css"> -->

<title>Insert title here</title>
<style>
		/* 圖片輪播設定 */
		
	.carousel-item {
		height: 350px;
		
	}
	.carousel-item img {
		width: 100%;
		
		over-flow: hidden;
		position: absolute;
		top: 50%;
		left: 0;
		transform: translateY(-50%);
	}
	
	#map {
    height: 100%;
	}

	
</style>



</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-12">照片
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
		</div>
		<div class="row">
			<div class="col-6">房間資訊
				<div>房間名稱: ${roomVO.roomName}</div>
				<div>住宿價格/天: ${roomVO.roomPrice}</div>
				<div>房間容納人數: ${roomVO.roomCapacity}</div>
				<div>房間上架時間: <fmt:formatDate value="${roomVO.roomOnTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></div>
				<div>房間收藏次數: ${roomVO.roomCollect}</div>
				<div>房間敘述: ${roomVO.roomDes}</div>
			</div>
			<div class="col-6">Google 地圖
				<div id="map" class="">
				</div>
			</div>
			<div class="col-6">關於房東
			<c:set var="sellVO" scope="page" value="${sellSvc.getOneSell(roomVO.sellMemId)}"/>
				<div>房東名字: ${sellVO.sellMemName}</div>
			
			
			</div>
			<div class="col-6">訂房狀態(日曆)</div>
		</div>
	</div>
	
	<div>
		<form id="insertRoomOrder" action="<%=request.getContextPath() %>/roomorder/roomorder.do" method="POST">
			<div>測試新增訂單</div>
			<input type="text" name="sellMemId" value="${sellVO.sellMemId}" />
			<input type="text" name="roomId" value="${roomVO.roomId}" />
			<input type="text" name="memId" value="MEM005" />
			<input type="text" name="checkInDate" value="2021-02-03" />
			<input type="text" name="checkOutDate" value="2021-02-05" />
			<input type="text" name="expectArrTime" value="2021-02-03 15:00:00" />
			<input type="text" name="roomOrderRemarks" value="我要帶狗狗" />
			<input type="text" name="roomOrderSum" value="20000" />
			<input type="text" name="room_cur_price" value="10000" />
			<input type="text" name="room_guest_name" value="123" />
			<input type="text" name="room_guest_mail" value="有mail" />
			<input type="text" name="room_guest_tel" value=" 0912333444" />
			<input type="hidden" name="action" value="insert" />
			
			<button type="submit">新增訂單</button>
		</form>
	</div>

	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<!--     <script -->
<!--   		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDK-5rFDe76_LASpBLJJSHYd1JM7W9ttWg"> -->
<!--  	</script> -->
	<script>
    	$(document).ready(function() {
			$(".carousel-indicators li:first-child").addClass("active");
			$(".carousel-inner .carousel-item:first-child").addClass("active");
			initMap();

		});
		let initMap = function() {
			let map = new google.maps.Map(document.getElementById("map"), {
				center: { lat: 24.9576852, lng: 121.2250143 },
				zoom: 15,
			});
		} 
	</script>

</body>
</html>