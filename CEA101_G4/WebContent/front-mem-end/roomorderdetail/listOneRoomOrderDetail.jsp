<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomorder.model.*"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="roomSvc" scope="page" class="com.room.model.RoomService" />
<jsp:useBean id="roomOrderDetailSvc" scope="page" class="com.roomorderdetail.model.RoomOrderDetailService" />
<%-- <jsp:useBean id="roomOrderDetailVO" scope="page" class="com.roomorderdetail.model.RoomOrderDetailVO" /> --%>
<jsp:useBean id="sellSvc" scope="request" class="com.sell.model.SellService" />
<% MemberVO memVO=(MemberVO)session.getAttribute("memVO");
%>
<%
RoomOrderService roSvc = new RoomOrderService();
List<RoomOrderVO>roomOrderVO = (List<RoomOrderVO>)request.getAttribute("oneRoomOrderList");
pageContext.setAttribute("roomOrderVO", roomOrderVO);
%>
<!-- roomOrderVO -->




<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"> 
	 <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/1.0.1/tailwind.min.css'>   
    
	<title>房間訂單 </title>
	<style>
	
	#order:before{
		content: "";
	    display: block;
	    position: absolute;
	    left: 300px;
	   
	    height: 310px;
	    border-left: 2px solid #e6e6e6;
	    z-index: -1;
	}
	
	
	
	</style>
</head>

<body>
<%@ include file="/front-mem-end/bar.jsp"%>
<div class="font-bold text-xl md:text-3xl ml-20">查看訂單明細 </div>
<div class="container">
<c:forEach var="roomOrderVO" items="${roomOrderVO}"> 
  <div class="column">
  	<div class="bg-cover bg-bottom h-56 md:h-auto md:w-64">
  		<img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomId=${roomOrderDetailSvc.getOneRoomOrderDetail(roomOrderVO.roomOrderId).room_id}&action=getOnePhotoByRoomId") style="object-fit:cover;height:270px;">
  		<div>${sellSvc. getOneSell(roomOrderVO.sellMemId).sellMemAddress}</div>
  		<h4>有什麼問題嗎？</h4>
		<h6>聯繫住宿以獲得解答，或提出特殊要求。</h6>	
  		<div>${sellSvc.getOneSell(roomOrderVO.sellMemId).sellMemTel}</div>
  	</div>
  <div class="column">
 	<div>訂單編號:${roomOrderVO.roomOrderId}</div>
 	<div>房型:${roomSvc.getOneRoom(roomOrderDetailSvc.getOneRoomOrderDetail(roomOrderVO.roomOrderId).room_id).roomName}</div>
  </div>
  <div class="column">
  	<div>入住時間${roomOrderVO.checkInDate}</div>
  	<div>退房時間${roomOrderVO.checkOutDate}</div>
  	
  			${countday}
  </div>
</div>
	
	
	NT$ ${roomOrderVO.roomOrderSum}
	
	
	
	</c:forEach>
	
	
  </div>
</body>

</html>