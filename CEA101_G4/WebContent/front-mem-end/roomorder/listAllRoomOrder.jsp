<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomorder.model.*"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="roomOrderSvc" scope="page" class="com.roomorder.model.RoomOrderService" />
<jsp:useBean id="roomOrderDetailSvc" scope="page" class="com.roomorderdetail.model.RoomOrderDetailService" />
<jsp:useBean id="roomOrderDetailVO" scope="page" class="com.roomorderdetail.model.RoomOrderDetailVO" />
<jsp:useBean id="sellSvc" scope="request" class="com.sell.model.SellService" />
<% MemberVO memVO=(MemberVO)session.getAttribute("memVO");%>
<%
RoomOrderService roSvc = new RoomOrderService();
List memRoomOrderList = (List)request.getAttribute("memRoomOrderList");
pageContext.setAttribute("memRoomOrderList", memRoomOrderList);
%>



<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">    
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-sell-end/front-sell-listAllRoom.css"> --%>
    
	<title>房間訂單 </title>
</head>
<body>
	<div id="viewport">
	    <%@ include file="/front-mem-end/bar.jsp"%>
	    <div id="content">
<div class="heading">
		<h2>我的旅程</h2>
	</div>
	<div class="container">
	<div class="row">
		<div class="col-md-4">
		<c:forEach var="memRoomOrderList" items="${memRoomOrderList}"> 
		<a href="<%=request.getContextPath()%>/roomorder/roomorder.do?roomOrderId=${memRoomOrderList.roomOrderId}&memId=${memRoomOrderList.memId}&action=insert">
		<img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomId=${roomOrderDetailSvc.getOneRoomOrderDetail(memRoomOrderList.roomOrderId).room_id}&action=getOnePhotoByRoomId" width="120px">
		${sellSvc.getOneSell(memRoomOrderList.sellMemId).sellRoomName}     
    	<c:choose>
    	<c:when test="${memRoomOrderList.roomOrderStatus ==0}"> 
    		<h4>待審核</h4>
    	</c:when>
    		<c:when test="${memRoomOrderList.roomOrderStatus ==1}">
    	<h4>已確認</h4> 
    		</c:when>
    	<c:when test="${memRoomOrderList.roomOrderStatus ==2}"> 
    		<h4>已CHECK IN</h4>
    	</c:when>
    	<c:when test="${memRoomOrderList.roomOrderStatus ==3}"> 
    		<h4>已CHECK OUT</h4> 
    	</c:when>
    	<c:when test="${memRoomOrderList.roomOrderStatus ==4}"> 
    		<h4>已取消
    	</c:when>
    	<c:when test="${memRoomOrderList.roomOrderStatus ==5}">
    		已完成
    	</c:when> 
    	</c:choose>
    	
    		<c:choose>
    	<c:when test="${memRoomOrderList.roomPaymentStatus ==0}"> 
    		<h4>未付款</h4>
    	</c:when>
    		<c:when test="${memRoomOrderList.roomPaymentStatus ==1}">
    		<h4>付款失敗</h4> 
    		</c:when>
    	<c:when test="${memRoomOrderList.roomPaymentStatus ==2}"> 
    		<h4>已付款</h4>
    	</c:when>
    	<c:when test="${memRoomOrderList.roomPaymentStatus ==3}"> 
    		<h4>退款中</h4> 
    	</c:when>
    	<c:when test="${memRoomOrderList.roomPaymentStatus ==4}"> 
    		<h4>已退款
    	</c:when>
    	</c:choose>
     	${memRoomOrderList.checkInDate}-${memRoomOrderList.checkOutDate}
     	$${memRoomOrderList.roomOrderSum}
     	${memRoomOrderList.roomOrderStatus}
     	

    	</a>
   	</div>
	</c:forEach>
	</div>
	</div>
	</div>

    
</body>
</html>