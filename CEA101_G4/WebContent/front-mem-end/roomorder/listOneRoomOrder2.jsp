<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="sellSvc" scope="request" class="com.sell.model.SellService" />
<jsp:useBean id="roomOrderVO" scope="request" class="com.roomorder.model.RoomOrderVO" />
<jsp:useBean id="roomOrderDetailSvc" scope="page" class="com.roomorderdetail.model.RoomOrderDetailService" />
<jsp:useBean id="roomSvc" scope="page" class="com.room.model.RoomService" />
<c:set var="roomOrderDetailVO" scope="page" value="${roomOrderDetailSvc.getOneRoomOrderDetail(roomOrderVO.roomOrderId)}" />
<!DOCTYPE html>
<html>

<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-mem-end/listOneOrder.css">
 

</head>  
<body>
	<%@include file="/front-mem-end/bar.jsp"%>
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
            <td id="ordertop">
                <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:600px;">
                    <tr>
                        <td id="td1">
                            <table align="left" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:300px;">
                                <tr>
                                    <td td="2">
                                        <h1>${sellSvc.getOneSell(roomOrderVO.sellMemId).sellRoomName}</h1>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td align="center" style="padding: 0px 35px 20px 35px; background-color: #ffffff;" bgcolor="#ffffff">
                            <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:600px;">
                                <tr>
                                    <td id="icon"> <img src="https://img.icons8.com/carbon-copy/100/000000/checked-checkbox.png" width="125" height="120" style="display: block; border: 0px;" /><br>
                                        <h2 style="font-size: 30px; font-weight: 800; line-height: 36px; color: #333333; margin: 0;"> 感謝您的預定! </h2>
                                    </td>
                                </tr>
                                <tr>
                                    <td id="word">
                                    	<div> <img id="orderimg" src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomId=${roomOrderDetail.room_id}&action=getOnePhotoByRoomId">  </div>
                                        <div>入住日期${roomOrderVO.checkInDate}</div>
                                        <div>退房日期${roomOrderVO.checkOutDate}</div>
                                        <div>預計抵達時間${roomOrderVO.expectArrTime}</div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <table cellspacing="0" cellpadding="0" border="0" width="100%">
                                            <tr>
                                                <td class="oderidleft"> 房間訂單編號 </td>
                                                <td class="orderidright"> # ${roomOrderVO.roomOrderId} </td>
                                            </tr>
                                             <tr>
                                                <td class="headleft"> 房間名稱 </td>
                                                <td class="headcenter"> 共住天數 </td>
                                                <td class="headright"> 每晚價格 </td>
                                            </tr>
                                            <tr>
                                                <td class="contentleft"> ${roomSvc.getOneRoom(roomOrderDetail.room_id).roomName}</td>
                                                <td class="contentcenter">${countday}</td>
                                                <td class="contentright"> ${roomSvc.getOneRoom(roomOrderDetail.room_id).roomPrice} </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" style="padding-top: 20px;">
                                        <table cellspacing="0" cellpadding="0" border="0" width="100%">
                                            <tr>
                                                <td class="bottomleft"> 總共 </td>
                                                <td class="bottomleft">${roomOrderVO.roomOrderSum}</td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td id="td3">
                            <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:660px;">
                                <tr>
                                    <td align="center" valign="top" style="font-size:0;">
                                        <div id="bottom">
                                            <table align="left" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:300px;">
                                                <tr>
                                                    <td id="guest">
                                                        <p style="font-weight: 800;">入住房客姓名</p>
                                                        <p>${roomOrderDetailVO.room_guest_name}</p>
                                                        <p style="font-weight: 800;">入住房客連絡電話</p>
                                                         <p>${roomOrderDetailVO.room_guest_tel}</p>
                                                         <p style="font-weight: 800;">入住房客電子信箱</p>
                                                         <p>${roomOrderDetailVO.room_guest_mail}</p>
                                                    </td>
                                              
                                                </tr>
                                                
                                                		
                                            </table>
                                        </div>
                                        <div id="date">
                                            <table align="left" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:300px;">
                                                <tr>
                                                    <td id="tddate">
                                                        <p style="font-weight: 800;">訂單成立時間</p>
                                                        <p>${roomOrderVO.roomOrderTime}</p>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        
                                        	<table>	
                                        		<tr><td><p>備註:</p></td><td><p>${roomOrderVO.roomOrderRemarks}</p></td></tr>
                                            </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td id="td4">
                            <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:600px;">
                                <tr>
                                    <td id="ad">
                                        <h2> Get 30% off your next order. </h2>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center" style="padding: 25px 0 15px 0;">
                                        <table border="0" cellspacing="0" cellpadding="0">
                                            <tr>
                                                <td align="center" style="border-radius: 5px;" bgcolor="#66b3b7"> </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td align="center" style="padding: 35px; background-color: #ffffff;" bgcolor="#ffffff">
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</body>

</html>