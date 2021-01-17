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
<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/image/LOGO/tiger.png" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" />
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/1.0.1/tailwind.min.css'>
    <title>SuperGoing房間訂單 </title>
    <style>
        * {
    font-family: Arial, sans;
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    -moz-box-sizing: border-box;
}

h1 {
    margin: 1em 0;
    text-align: center;
}

#container {
    margin: 0 auto;
    width: 70%;  /* Ancho del contenedor */
    height:100%;
    
}

#container input {
    height: 2.5em;
    visibility: hidden;
}

#container label {
    background: #f9f9f9;  /* Fondo de las pestañas */
    border-radius: .25em .25em 0 0;
    color: #888;  /* Color del texto de las pestañas */
    cursor: pointer;
    display: block;
    float: left;
    font-size: 1em;  /* Tamaño del texto de las pestañas */
    height: 2.5em;
    line-height: 2.5em;
    margin-right: .25em;
    padding: 0 1.5em;
    text-align: center;
}

#container input:hover + label {
    background: #ddd;  /* Fondo de las pestañas al pasar el cursor por encima */
    color: #666;  /* Color del texto de las pestañas al pasar el cursor por encima */
}

#container input:checked + label {
    background: #f1f1f1;  /* Fondo de las pestañas al presionar */
    color: #444; /* Color de las pestañas al presionar */
    position: relative;
    z-index: 6;
    /*
    -webkit-transition: .1s;
    -moz-transition: .1s;
    -o-transition: .1s;
    -ms-transition: .1s;
    */
}

#content {
    border-radius: 0 .25em .25em .25em;
    min-height: 20em;  /* Alto del contenido */
    position: relative;
    width: 90%;
    z-index: 5;
    height:100%;
}

#content div {
    opacity: 0;
    padding: 1.5em;
    position: absolute;
    z-index: -100;
    background-color: #fdfdfd;
    width: 100%;
    /*
    transition: all linear 0.1s;
    */
}

/* #content-1 div { */
/*  clear: both; */
/*  margin-bottom: 1em; */
/* } */


/* #content-2 div { */
/*  float: left; */
/*  width: 48.5%; */
/* } */


/* #content-3 div { */
/*  margin-bottom: 1em; */
/* } */
/* #content-3 div { */
/*  margin-left: 2em; */
/* } */

#container input#tab-1:checked ~ #content #content-1,
#container input#tab-2:checked ~ #content #content-2,
#container input#tab-3:checked ~ #content #content-3,
#container input#tab-4:checked ~ #content #content-4,
#container input#tab-5:checked ~ #content #content-5  
{
    opacity: 1;
    z-index: 100;
}

input.visible {
  visibility: visible !important;
}
table{
    width: 100%;
    border-collapse: collapse;
    padding: 20px;
    margin: 20px;
}
table .info{
    padding:40px;
}
    
    
    </style>
</head>

<body>
    <%@ include file="/front-mem-end/bar.jsp"%>
    <div class="font-bold text-xl md:text-3xl ml-20">我的旅程 </div>
    <div id="container">
        <input id="tab-1" type="radio" name="tab-group" checked="checked" />
        <label for="tab-1">全部訂單</label>
        <input id="tab-2" type="radio" name="tab-group" />
        <label for="tab-2">待審核</label>
        <input id="tab-3" type="radio" name="tab-group" />
        <label for="tab-3">已確認</label>
        <input id="tab-4" type="radio" name="tab-group" />
        <label for="tab-4">已取消</label>
        <input id="tab-5" type="radio" name="tab-group" />
        <label for="tab-5">已完成</label>
        <div id="content">
            <div id="content-1">
                <c:forEach var="roomordVO" items="${roomOrderSvc.getByMemId(memVO.mem_id)}">
                    <table>
                        <tr>
                            <td>
                                <img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomId=${roomOrderDetailSvc.getOneRoomOrderDetail(roomordVO.roomOrderId).room_id}&action=getOnePhotoByRoomId" ) style="object-fit:cover;height:160px;">
                            </td>
                            <td class="info">
                                ${roomordVO.roomOrderId}
                                <p class="font-bold text-xl md:text-2xl">${sellSvc.getOneSell(roomordVO.sellMemId).sellRoomName} </p>
                                <p class="text-gray-700 md:text-lg">${roomordVO.checkInDate}-${roomordVO.checkOutDate}</p>
                                <span class="text-gray-900 font-bold">$${roomordVO.roomOrderSum}</span>
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
                            </td>
                            <td>
                                <form id="regForm" action="<%=request.getContextPath() %>/roomorder/roomorder.do" method="POST">
                                    <input type="hidden" name="room_order_id" value="${roomordVO.roomOrderId}">
                                    <input type="hidden" name="mem_id" value="${roomordVO.memId}">
                                    <input type="hidden" name="action" value="checkRoomOrderDetail" />
                                    <button class=" my-3 px-3 py-1 bg-gray-600 hover:bg-gray-400 font-bold text-white md:text-20 rounded-lg shadow-md">查看詳情</button>
                                </form>
                            </td>
                        </tr>
                    </table>
                </c:forEach>
            </div>
            <div id="content-2">
                <c:forEach var="roomordVO" items="${roomOrderSvc.getByMemId(memVO.mem_id)}">
                    <c:if test="${roomordVO.roomOrderStatus==0}">
                        <table>
                            <tr>
                                <td>
                                    <img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomId=${roomOrderDetailSvc.getOneRoomOrderDetail(roomordVO.roomOrderId).room_id}&action=getOnePhotoByRoomId" ) style="object-fit:cover;height:160px;">
                                </td>
                                <td class="info">
                                    ${roomordVO.roomOrderId}
                                    <p class="font-bold text-xl md:text-2xl">${sellSvc.getOneSell(roomordVO.sellMemId).sellRoomName} </p>
                                    <p class="text-gray-700 md:text-lg">${roomordVO.checkInDate}-${roomordVO.checkOutDate}</p>
                                    <span class="text-gray-900 font-bold">$${roomordVO.roomOrderSum}</span>
                                </td>
                                <td>
                                    <form id="regForm" action="<%=request.getContextPath() %>/roomorder/roomorder.do" method="POST">
                                        <input type="hidden" name="room_order_id" value="${roomordVO.roomOrderId}">
                                        <input type="hidden" name="mem_id" value="${roomordVO.memId}">
                                        <input type="hidden" name="action" value="checkRoomOrderDetail" />
                                        <button class=" my-3 px-3 py-1 bg-gray-600 hover:bg-gray-400 font-bold text-white md:text-20 rounded-lg shadow-md">查看詳情</button>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </c:if>
                </c:forEach>
            </div>
            <div id="content-3">
                <c:forEach var="roomordVO" items="${roomOrderSvc.getByMemId(memVO.mem_id)}">
                    <c:if test="${roomordVO.roomOrderStatus==1}">
                        <table>
                            <tr>
                                <td>
                                    <img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomId=${roomOrderDetailSvc.getOneRoomOrderDetail(roomordVO.roomOrderId).room_id}&action=getOnePhotoByRoomId" ) style="object-fit:cover;height:160px;">
                                </td>
                                <td>
                                    ${roomordVO.roomOrderId}
                                    <p class="font-bold text-xl md:text-2xl">${sellSvc.getOneSell(roomordVO.sellMemId).sellRoomName} </p>
                                    <p class="text-gray-700 md:text-lg">${roomordVO.checkInDate}-${roomordVO.checkOutDate}</p>
                                    <span class="text-gray-900 font-bold">$${roomordVO.roomOrderSum}</span>
                                </td>
                                <td>
                                    <form id="regForm" action="<%=request.getContextPath() %>/roomorder/roomorder.do" method="POST">
                                        <input type="hidden" name="room_order_id" value="${roomordVO.roomOrderId}">
                                        <input type="hidden" name="mem_id" value="${roomordVO.memId}">
                                        <input type="hidden" name="action" value="checkRoomOrderDetail" />
                                        <button class=" my-3 px-3 py-1 bg-gray-600 hover:bg-gray-400 font-bold text-white md:text-20 rounded-lg shadow-md">查看詳情</button>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </c:if>
                </c:forEach>
            </div>
            <div id="content-4">
                <c:forEach var="roomordVO" items="${roomOrderSvc.getByMemId(memVO.mem_id)}">
                    <c:if test="${roomordVO.roomOrderStatus==4}">
                        <table>
                            <tr>
                                <td>
                                    <img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomId=${roomOrderDetailSvc.getOneRoomOrderDetail(roomordVO.roomOrderId).room_id}&action=getOnePhotoByRoomId" ) style="object-fit:cover;height:160px;">
                                </td>
                                <td>
                                    ${roomordVO.roomOrderId}
                                    <p class="font-bold text-xl md:text-2xl">${sellSvc.getOneSell(roomordVO.sellMemId).sellRoomName} </p>
                                    <p class="text-gray-700 md:text-lg">${roomordVO.checkInDate}-${roomordVO.checkOutDate}</p>
                                    <span class="text-gray-900 font-bold">$${roomordVO.roomOrderSum}</span>
                                </td>
                                <td>
                                    <form id="regForm" action="<%=request.getContextPath() %>/roomorder/roomorder.do" method="POST">
                                        <input type="hidden" name="room_order_id" value="${roomordVO.roomOrderId}">
                                        <input type="hidden" name="mem_id" value="${roomordVO.memId}">
                                        <input type="hidden" name="action" value="checkRoomOrderDetail" />
                                        <button class=" my-3 px-3 py-1 bg-gray-600 hover:bg-gray-400 font-bold text-white md:text-20 rounded-lg shadow-md">查看詳情</button>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </c:if>
                </c:forEach>
            </div>
            <div id="content-5">
                <c:forEach var="roomordVO" items="${roomOrderSvc.getByMemId(memVO.mem_id)}">
                    <c:if test="${roomordVO.roomOrderStatus==5}">
                        <table>
                            <tr>
                                <td>
                                    <img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomId=${roomOrderDetailSvc.getOneRoomOrderDetail(roomordVO.roomOrderId).room_id}&action=getOnePhotoByRoomId" ) style="object-fit:cover;height:160px;">
                                </td>
                                <td>
                                    ${roomordVO.roomOrderId}
                                    <p class="font-bold text-xl md:text-2xl">${sellSvc.getOneSell(roomordVO.sellMemId).sellRoomName} </p>
                                    <p class="text-gray-700 md:text-lg">${roomordVO.checkInDate}-${roomordVO.checkOutDate}</p>
                                    <span class="text-gray-900 font-bold">$${roomordVO.roomOrderSum}</span>
                                </td>
                                <td>
                                    <form id="regForm" action="<%=request.getContextPath() %>/roomorder/roomorder.do" method="POST">
                                        <input type="hidden" name="room_order_id" value="${roomordVO.roomOrderId}">
                                        <input type="hidden" name="mem_id" value="${roomordVO.memId}">
                                        <input type="hidden" name="action" value="checkRoomOrderDetail" />
                                        <button class=" my-3 px-3 py-1 bg-gray-600 hover:bg-gray-400 font-bold text-white md:text-20 rounded-lg shadow-md">查看詳情</button>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
</body>

</html>