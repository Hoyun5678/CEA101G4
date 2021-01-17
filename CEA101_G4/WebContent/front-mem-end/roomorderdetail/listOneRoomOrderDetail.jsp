<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomorder.model.*"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="roomSvc" scope="page" class="com.room.model.RoomService" />
<jsp:useBean id="roomOrderDetailSvc" scope="page" class="com.roomorderdetail.model.RoomOrderDetailService" />
<%-- <jsp:useBean id="roomOrderDetailVO" scope="page" class="com.roomorderdetail.model.RoomOrderDetailVO" /> --%>
<jsp:useBean id="sellSvc" scope="request" class="com.sell.model.SellService" />
<%
RoomOrderService roSvc = new RoomOrderService();
RoomOrderVO roomOrderVO = (RoomOrderVO)request.getAttribute("roomOrderVO");
pageContext.setAttribute("roomOrderVO", roomOrderVO);
%>

<html>
<head>
<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/image/LOGO/tiger.png" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" />
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"> 
	 <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/1.0.1/tailwind.min.css'>   
    
	<title>SuperGoing查看訂單詳情</title>
	<style>
	*{
	font-family:
	Microsoft JhengHei;
	}
	
	#roomorderpic::after{
	content: "";
    position: absolute;
    top: 310px;
    background: #dbd9d9;
    width: 90%;
    height: 1.5px;
    left: 30px;
	}
	#roomorderpic{
	font-weight: 500;
    font-size: 18px;
    font-family:Microsoft JhengHei;
	}
	
	 #contact{
	 margin-top:40px;
	 text-align:center;
	 }
	 
	.col-lg-5,.col-lg-4,.col-lg-3{
	border:1px #eae4e4 solid;
	padding:10px;	
	}
	
	#roomorderpic {
	text-align:center;
	}
	img{
	display:block; 
	margin:auto;
	}
	.title{
	font-size:16px;
	margin-top:15px;
	}
	.value{
	font-size:16px;
	font-weight:600;
	display:inline;
	}
	.order::after{
	content: "";
    position: absolute;
    top: 170px;
    background: #dbd9d9;
    width: 220px;
    height: 1.5px;
    left: 40px;
    }
    .date::after{
	content: "";
    position: absolute;
    top: 310px;
    background: #dbd9d9;
    width: 220px;
    height: 1.5px;
    left: 40px;
    }
	.roomprice{
	margin-top:30px;
	font-size:18px;
	}
	.order{
	margin-top:30px;
	}
	#center{
	padding-left:50px;
	}
	#right{
	text-align:center;
	}
	.date{
	margin-top:30px;
	}
	.guestInfo{
	margin-top:20px;
	text-align:center;
	}
	.btn_upd{
	background-color: #626b79;
    padding: 5px;
    border-radius: 5px;
    color: #fff;
    margin-top: 30px;
	}
	.btn_upd.hover{
	background-color:grey;
	}
	
	</style>
</head>

<body>
<%@ include file="/front-mem-end/bar.jsp"%>
<div class="font-bold text-xl md:text-3xl ml-20">查看訂單明細 </div>
<div class="container">
<div class="row">
  
  <div class="col-lg-5" id="left">
  	<div id="roomorderpic">
  		<img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomId=${roomOrderDetailSvc.getOneRoomOrderDetail(roomOrderVO.roomOrderId).room_id}&action=getOnePhotoByRoomId") style="object-fit:cover;height:270px;">
  		<div style="font-weight:600;">${sellSvc. getOneSell(roomOrderVO.sellMemId).sellRoomName}地址:${sellSvc. getOneSell(roomOrderVO.sellMemId).sellMemAddress}</div>
  	</div>
  	<div class="guestInfo">
  		<div class="title">入住者姓名:</div><div class="value">${roomOrderDetailSvc.getOneRoomOrderDetail(roomOrderVO.roomOrderId).room_guest_name}</div>
  		<div class="title">入住者連絡電話:</div><div class="value">${roomOrderDetailSvc.getOneRoomOrderDetail(roomOrderVO.roomOrderId).room_guest_tel}</div>
  		<div class="title">入住者電子信箱:</div><div class="value">${roomOrderDetailSvc.getOneRoomOrderDetail(roomOrderVO.roomOrderId).room_guest_mail}</div>
  	</div>
  	
  </div>
  <div class="col-lg-4" id="center">
 	<div class="order">
 		<div class="title">訂單編號:</div><div class="value">${roomOrderVO.roomOrderId}</div>	
 		<div class="title">房型:</div><div class="value">${roomSvc.getOneRoom(roomOrderDetailSvc.getOneRoomOrderDetail(roomOrderVO.roomOrderId).room_id).roomName}</div>
	</div>
	<div class="date">
  		<div class="title">入住時間</div><div class="value">${roomOrderVO.checkInDate}</div>
  		<div class="title">退房時間</div><div class="value">${roomOrderVO.checkOutDate}</div> 	
  	</div>
  	<div class="roomprice">
  		<div>${countday}晚</div>
  		<div class="title">價格:</div>
  		<div class="value">NT$ ${roomOrderVO.roomOrderSum}</div>
  	</div>
  </div>
  
  <div class="col-lg-3" id="right">
  
 	 <div id="contact"><h4>有什麼問題嗎？</h4>
		<h6>聯繫住宿以獲得解答，或提出特殊要求。</h6>	
  		<div>${sellSvc.getOneSell(roomOrderVO.sellMemId).sellMemTel}</div>
  	</div>
  	<div style="margin-top:40px;font-weight:600;font-size:22px;">訂單狀態:
  	<c:choose>
		<c:when test="${roomOrderVO.roomOrderStatus ==0}"> 
			待審核
		</c:when>
		<c:when test="${roomOrderVO.roomOrderStatus ==1}">
			已確認 
		</c:when>
		<c:when test="${roomOrderVO.roomOrderStatus ==2}"> 
			已CHECK IN
		</c:when>
		<c:when test="${roomOrderVO.roomOrderStatus ==3}"> 
			已CHECK OUT
		</c:when>
		<c:when test="${roomOrderVO.roomOrderStatus ==4}"> 
			已取消
		</c:when>
		<c:when test="${roomOrderVO.roomOrderStatus ==5}">
			已完成
		</c:when> 
	</c:choose>
		</div>
  	
  	
  	<c:choose>
  	<c:when test="${roomOrderVO.roomOrderStatus == 0 || roomOrderVO.roomOrderStatus == 1}">
  	<form METHOD="post" id="updateOrder" action="<%=request.getContextPath()%>/roomorder/roomorder.do"">
  	<input type="hidden" name="room_order_id"  value="${roomOrderVO.roomOrderId}"> 	
  	<input type="submit" class="btn_upd" value="取消訂單">
	<input type="hidden" name="action"value="getOne_For_Update">
  	</form>
  	</c:when>
  	</c:choose>
  </div>

	</div>
  </div>
</body>

</html>