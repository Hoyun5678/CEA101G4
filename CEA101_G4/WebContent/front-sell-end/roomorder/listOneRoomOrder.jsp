<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.sell.model.*"%>
<jsp:useBean id="roomOrderService" scope="page" class="com.roomorder.model.RoomOrderService" />
<jsp:useBean id="roomOrderDetailSvc" scope="page" class="com.roomorderdetail.model.RoomOrderDetailService" />
<jsp:useBean id="roomSvc" scope="page" class="com.room.model.RoomService" />

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
	<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/image/LOGO/tiger.png" />
	<title>訂單詳情</title>
	<style>
		button {
            float: right;
            margin: 10px;
        }
	</style>
</head>
<body>
	<div id="viewport">
	    <%@ include file="/front-sell-end/sellMemSideBar.jsp"%>
	    <div id="content">
	        <%@ include file="/front-sell-end/sellNavBar.jsp"%>
	        <c:set var="roomOrderVO" scope='page' value='${roomOrderService.getOneRoomOrder(param.roomOrderId)}' />
	        <c:set var="roomOrderDetailVO" scope='page' value='${roomOrderDetailSvc.getOneRoomOrderDetail(param.roomOrderId)}' />
	        
			<div class="container-fluid" style="padding: 0;">
				<div class="container mt-4">
					<div class="col-9 offset-1">
		                <h2>訂單${roomOrderVO.roomOrderId} 詳細資訊</h2>
		                <hr>
		            </div>
                	<form class="form-horizontal" method="POST"
                    	action="<%=request.getContextPath()%>/front-sell-end/sell/updateSellInfo.jsp"
                        >
                    	<div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">Booking期間:</label>
                            </div>                    	
                        	<div class="col-5 d-inline-block">
                            	<label>${roomOrderVO.checkInDate} ~ ${roomOrderVO.checkOutDate}</label>
                            </div>
						</div>
                    	<div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">預計抵達時間:</label>
                            </div>                    	
                        	<div class="col-5 d-inline-block">
                            	<label><fmt:formatDate value="${roomOrderVO.expectArrTime}" pattern="HH:mm"/></label>
                            </div>
						</div>
						<div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">訂單備註:</label>
                            </div>                    	
                        	<div class="col-5 d-inline-block">
                            	<label>${roomOrderVO.roomOrderRemarks}</label>
                            </div>
						</div>
						<div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">訂單總金額:</label>
                            </div>                    	
                        	<div class="col-5 d-inline-block">
                            	<label>${roomOrderVO.roomOrderSum}</label>
                            </div>
						</div>
						<c:set var="roomVO" scope='page' value='${roomSvc.getOneRoom(roomOrderDetailVO.room_id)}' />
						<div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">訂購房間:</label>
                            </div>                    	
                        	<div class="col-5 d-inline-block">
                            	<label>${roomVO.roomName}</label>
                            </div>
						</div>
						<div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">訂購人姓名:</label>
                            </div>                    	
                        	<div class="col-5 d-inline-block">
                            	<label>${roomOrderDetailVO.room_guest_name}</label>
                            </div>
						</div>
						<div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">聯絡人Email:</label>
                            </div>                    	
                        	<div class="col-5 d-inline-block">
                            	<label>${roomOrderDetailVO.room_guest_mail}</label>
                            </div>
						</div>
						<div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">聯絡人電話:</label>
                            </div>                    	
                        	<div class="col-5 d-inline-block">
                            	<label>${roomOrderDetailVO.room_guest_tel}</label>
                            </div>
						</div>
						
						<div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">訂單狀態:</label>
                            </div>                    	
                        	<div class="col-5 d-inline-block">
                        		<input type='hidden' class='orderStatus' value='${roomOrderVO.roomOrderStatus}'>
								<select name='roomOrderStatus'>
									<option value="0">待審核</option>
									<option value="1">已確認</option>
									<option value="2">已CHECK IN</option>
									<option value="3">已CHECK OUT</option>
									<option value="4">已取消</option>
									<option value="5">已完成</option>
								</select>
                            </div>
						</div>
						
						<div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">付款狀態:</label>
                            </div>                    	
                        	<div class="col-5 d-inline-block">
                            	<input type='hidden' class='payStatus' value='${roomOrderVO.roomPaymentStatus}'>
                            	<select name='roomPaymentStatus'>
									<option value="0">未付款</option>
									<option value="1">付款失敗</option>
									<option value="2">已付款</option>
									<option value="3">退款中</option>
									<option value="4">已退款</option>
								</select>
                            </div>
						</div>

                        <div class="form-group">
                            <div class="col-10">
                                <input type="hidden" name="sellMemId" value="${sellVO.sellMemId}" />
                                <input type="hidden" name="action" value="updateRoomOrder" />
                                <button type="button" class="btn btn-secondary" id="editPwdBtn">修改訂單</button>
                            </div>
                        </div>
                    </form>
                    <div class="row">
						<c:if test="${not empty errorMsgs}">
							<%-- 錯誤表列 from Servlet --%>
							<div class="alert alert-danger col-lg-6 col-lg-offset-1" role="alert" id="titleAndError">
								<font style="color:red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color:red">${message}</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>
					</div>
                </div>
			</div>
		</div>
	</div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
    <script>
    	$(document).ready(function() {
    		
    		$('[name="roomOrderStatus"]').val($('.orderStatus').val());
    		$('[name="roomPaymentStatus"]').val($('.payStatus').val());
    		
    	})
    </script>

</body>
</html>