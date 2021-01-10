<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roomorder.model.*"%>

<jsp:useBean id="roomOrderSvc" scope="page" class="com.roomorder.model.RoomOrderService" />
<jsp:useBean id="roomOrderDetailSvc" scope="page" class="com.roomorderdetail.model.RoomOrderDetailService" />
<c:set var="list" scope="page" value="${roomOrderSvc.getAll()}"/>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-sell-end/front-sell-listAllRoom.css">
    
	<title>所有房間訂單資料 - listAllRoom.jsp</title>
</head>
<body>
	<c:if test="${not empty sellVO}">
		<c:set var="sellVO" scope="page" value="${sellVO}"/>
		<c:set var="list" scope="page" value="${roomOrderSvc.getBySellMemId(sellVO.sellMemId)}"/>
	</c:if>
	<div id="viewport">
	    <%@ include file="/front-sell-end/sellMemSideBar.jsp"%>
	    <div id="content">
	        <%@ include file="/front-sell-end/sellNavBar.jsp"%>
			<div class="container-fluid" style="padding: 0;">			
				<table class="table table-striped table-hover align-middle" id="tableRoomList">
					<thead class=".thead-dark">
						<tr>
							<th>房間名稱</th>
							<th>入住期間</th>
							<th>入住者</th>
							<th>訂單金額</th>
							<th>付款狀態</th>
							<th>訂單狀態</th>
						</tr>
					</thead>
					<tbody>
				
					<c:forEach var="roomOrderVO" items="${list}" >
						<c:set var="roomOrderDetailVO" scope="page" value="${roomOrderDetailSvc.getOneRoomOrderDetail(roomOrderVO.roomOrderId)}" />
						<tr>
							<td>${roomOrderDetailVO.room_id}</td>
							<td>${roomOrderVO.checkInDate} <br>~ ${roomOrderVO.checkOutDate}</td>
							<td>${roomOrderDetailVO.room_guest_name}</td>
							<td>${roomOrderVO.roomOrderSum}</td>
							<td>
							  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomorder/roomorder.do" style="margin-bottom: 0px;">
							     <input type="hidden" name="roomOrderId"  value="${roomOrderVO.roomOrderId}">
							     <input type="hidden" name="action"	value="updatePaymentStatus">
							     <button type="button" class="btn btn-secondary edit" id="roomPaymentStatusBtn">${roomOrderVO.roomPaymentStatus}</button>
							  </FORM>
							</td>
							<td>
							  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomorder/roomorder.do" style="margin-bottom: 0px;">
							     <input type="hidden" name="roomOrderId"  value="${roomOrderVO.roomOrderId}">
							     <input type="hidden" name="action"  value="updateOrderStatus">
							     <button type="button" class="btn btn-secondary edit" id="roomOrderStatusBtn">${roomOrderVO.roomOrderStatus}</button>
							  </FORM>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<div class="row" style="margin: 15px;">
					<div class="col-md col-md-offset-10">
						<button class="btn btn-primary" id="addRoom">新增房間</button>
					</div>
				</div>
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

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
    <script>
    	$(document).ready(function() {
    		var contextPath = '<%=request.getContextPath()%>';
    		$("#addRoom").click(function() {

    			window.location.replace(contextPath + "/front-sell-end/room/addRoom.jsp");
    		})
    	})
    </script>

    
</body>
</html>