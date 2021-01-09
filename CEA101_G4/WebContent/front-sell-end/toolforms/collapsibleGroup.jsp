<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="roomOrderSvc" scope="page" class="com.roomorder.model.RoomOrderService" />
<jsp:useBean id="roomOrderDetailSvc" scope="page" class="com.roomorderdetail.model.RoomOrderDetailService" />

<c:set var='sellMemId' scope="page" value='${param.sellMemId}' />
<c:set var='checkInDate' scope="page" value='${param.checkInDate}' />

<c:set var='roomOrderList' scope='page' value='${roomOrderSvc.getBySellMemIdAndDate(sellMemId, checkInDate)}' />
	<c:forEach var="roomOrderVO" items="${roomOrderList}" varStatus="userStatus">
		<div class="card">
			<div class="card-header" id="headingOne">
				<h5 class="mb-0">
					<button class="btn btn-link" data-toggle="collapse" data-target="#collapse${userStatus.count}" aria-expanded="true" aria-controls="collapse${userStatus.count}" style="text-decoration: none;" >
						訂購者: ${roomOrderDetailSvc.getOneRoomOrderDetail(roomOrderVO.roomOrderId).room_guest_name}
						${roomOrderDetailSvc.getOneRoomOrderDetail(roomOrderVO.roomOrderId).room_guest_tel}
					</button>
				</h5>
			</div>
	
			<div id="collapse${userStatus.count}" class="collapse" aria-labelledby="headingOne">
				<div class="card-body">
					<div>入住期間: ${roomOrderVO.checkInDate} ~ ${roomOrderVO.checkOutDate}</div>
					<div>訂單金額: ${roomOrderVO.roomOrderSum}</div>
					<div>付款狀態: ${roomOrderVO.roomPaymentStatus == 0 ? '未付款': '已付款'}</div>
					<div>訂單狀態: ${roomOrderVO.roomOrderStatus == 2 ? '已入住': '已成立'}</div>
					 
					
					<div><button class="btn btn-secondary d-flex ml-auto">報到Check In</button></div>
				
				</div>
			</div>
		</div>
	</c:forEach>

