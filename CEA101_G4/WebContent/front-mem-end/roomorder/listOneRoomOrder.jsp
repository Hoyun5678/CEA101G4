<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="roomOrderVO" scope="request" class="com.roomorder.model.RoomOrderVO" />
<jsp:useBean id="roomOrderDetailSvc" scope="page" class="com.roomorderdetail.model.RoomOrderDetailService" />

<c:set var="roomOrderDetailVO" scope="page" value="${roomOrderDetailSvc.getOneRoomOrderDetail(roomOrderVO.roomOrderId)}" />
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




</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-5 d-inline-block">roomOrderId: </div>
			<div class="col-7 d-inline-block">${roomOrderVO.roomOrderId}</div>
			<div class="col-5 d-inline-block">sellMemId: </div>
			<div class="col-7 d-inline-block">${roomOrderVO.sellMemId}</div>
			<div class="col-5 d-inline-block">memId: </div>
			<div class="col-7 d-inline-block">${roomOrderVO.memId}</div>
			<div class="col-5 d-inline-block">roomOrderTime: </div>
			<div class="col-7 d-inline-block">${roomOrderVO.roomOrderTime}</div>
			<div class="col-5 d-inline-block">checkInDate: </div>
			<div class="col-7 d-inline-block">${roomOrderVO.checkInDate}</div>
			<div class="col-5 d-inline-block">checkOutDate: </div>
			<div class="col-7 d-inline-block">${roomOrderVO.checkOutDate}</div>
			<div class="col-5 d-inline-block">expectArrTime: </div>
			<div class="col-7 d-inline-block">${roomOrderVO.expectArrTime}</div>
			<div class="col-5 d-inline-block">roomOrderRemarks: </div>
			<div class="col-7 d-inline-block">${roomOrderVO.roomOrderRemarks}</div>
			<div class="col-5 d-inline-block">roomOrderSum: </div>
			<div class="col-7 d-inline-block">${roomOrderVO.roomOrderSum}</div>
			<div class="col-5 d-inline-block">roomOrderStatus: </div>
			<div class="col-7 d-inline-block">${roomOrderVO.roomOrderStatus}</div>
			<div class="col-5 d-inline-block">roomPaymentStatus: </div>
			<div class="col-7 d-inline-block">${roomOrderVO.roomPaymentStatus}</div>

			<hr>
			
			
			<div class="col-5 d-inline-block">roomOrderDetailSvc: </div>
			<div class="col-7 d-inline-block">${roomOrderDetailSvc}</div>
			
			<div class="col-5 d-inline-block">room_order_id: </div>
			<div class="col-7 d-inline-block">${roomOrderDetailVO.room_order_id}</div>
			<div class="col-5 d-inline-block">room_id: </div>
			<div class="col-7 d-inline-block">${roomOrderDetailVO.room_id}</div>
			<div class="col-5 d-inline-block">room_cur_price: </div>
			<div class="col-7 d-inline-block">${roomOrderDetailVO.room_cur_price}</div>
			<div class="col-5 d-inline-block">room_guest_name: </div>
			<div class="col-7 d-inline-block">${roomOrderDetailVO.room_guest_name}</div>
			<div class="col-5 d-inline-block">room_guest_mail: </div>
			<div class="col-7 d-inline-block">${roomOrderDetailVO.room_guest_mail}</div>
			<div class="col-5 d-inline-block">room_guest_tel: </div>
			<div class="col-7 d-inline-block">${roomOrderDetailVO.room_guest_tel}</div>
		</div>
	</div>

	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>


</body>
</html>