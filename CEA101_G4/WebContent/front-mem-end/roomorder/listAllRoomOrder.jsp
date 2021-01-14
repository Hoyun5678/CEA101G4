<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomorder.model.*"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="roomOrderSvc" scope="page" class="com.roomorder.model.RoomOrderService" />
<jsp:useBean id="roomOrderDetailSvc" scope="page" class="com.roomorderdetail.model.RoomOrderDetailService" />
<jsp:useBean id="roomOrderDetailVO" scope="page" class="com.roomorderdetail.model.RoomOrderDetailVO" />
<jsp:useBean id="sellSvc" scope="request" class="com.sell.model.SellService" />


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
<div class="font-bold text-xl md:text-3xl ml-20">我的旅程 </div>
    <!-- partial:index.partial.html -->
<!--     <div class="flex items-center justify-center min-h-screen"> -->

    <c:forEach var="roomordVO" items="${roomOrderSvc.getByMemId(memVO.mem_id)}"> 
        <div class="max-w-md md:max-w-4xl px-2 mt-10 ml-40" id="order">
            <div class="bg-white shadow-xl rounded-lg overflow-hidden md:flex">
                <div class="bg-cover bg-bottom h-56 md:h-auto md:w-64"><img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomId=${roomOrderDetailSvc.getOneRoomOrderDetail(roomordVO.roomOrderId).room_id}&action=getOnePhotoByRoomId") style="object-fit:cover;height:270px;">
                </div>
                <div>
                    <div class=" ml-20 p-4 md:p-5">
                        <p class="font-bold text-xl md:text-2xl">${sellSvc.getOneSell(roomordVO.sellMemId).sellRoomName}   </p>
                        <p class="text-gray-700 md:text-lg">${roomordVO.checkInDate}-${roomordVO.checkOutDate}</p>
                        ${roomordVO.roomOrderId}
                    </div>
                    <div class="ml-20 p-4 md:p-5 bg-gray-100">
                        <div class="sm:flex sm:justify-between sm:items-center">
                            <div>
                                <div class="text-3xl ml-3 text-gray-700"><span class="text-gray-900 font-bold">$${roomordVO.roomOrderSum}</span></div>
                                <div class="flex items-center">
                                    <div class="text-gray-600 ml-2 text-sm md:text-base mt-1">
                                    <c:choose>
								    	<c:when test="${roomordVO.roomOrderStatus ==0}"> 
								    		待審核
								    	</c:when>
								    		<c:when test="${roomordVO.roomOrderStatus ==1}">
								    	           已確認 
								    		</c:when>
								    	<c:when test="${roomordVO.roomOrderStatus ==2}"> 
								    		已CHECK IN
								    	</c:when>
								    	<c:when test="${roomordVO.roomOrderStatus ==3}"> 
								    		已CHECK OUT
								    	</c:when>
								    	<c:when test="${roomordVO.roomOrderStatus ==4}"> 
								    		已取消
								    	</c:when>
								    	<c:when test="${roomordVO.roomOrderStatus ==5}">
								    		已完成
								    	</c:when> 
								    	</c:choose>
								    	
								    <c:choose>
								    	<c:when test="${roomordVO.roomPaymentStatus ==0}"> 
								    		未付款
								    	</c:when>
								    	<c:when test="${roomordVO.roomPaymentStatus ==1}">
								    		付款失敗 
								    	</c:when>
								    	<c:when test="${roomordVO.roomPaymentStatus ==2}"> 
								    		已付款
								    	</c:when>
								    	<c:when test="${roomordVO.roomPaymentStatus ==3}"> 
								    		退款中 
								    	</c:when>
								    	<c:when test="${roomordVO.roomPaymentStatus ==4}"> 
								    		已退款
								    	</c:when>
								    </c:choose>
								    </div>
                                </div>
                            </div>
                            <form id="regForm" action="<%=request.getContextPath() %>/roomorder/roomorder.do" method="POST">
                            <input type="hidden" name="room_order_id" value="${roomordVO.roomOrderId}">
                            <input type="hidden" name="mem_id" value="${roomordVO.memId}">
                            <input type="hidden" name="action" value="checkRoomOrderDetail" />
                            <button class="mt-3 sm:mt-0 py-2 px-5 ml-20 md:py-3 md:px-6 bg-gray-600 hover:bg-gray-400 font-bold text-white md:text-lg rounded-lg shadow-md">查看詳情</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </c:forEach>
<!--     </div> -->
    <!-- partial -->
</body>

</html>