<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-sell-end/front-sell-sideBar.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.min.css" />
<style>
#header-img{
    width: 116px;
    height: 55px;
    
}
header{
background-image:
		url("../../image/mem_center/mem_back.jpg");
background-size:cover;
background-repeat:no-repeat;
opacity: 0.4;
}
</style>
</head>
<body>
<div id="sidebar">
    <header>
        <img src="<%=request.getContextPath()%>/image/LOGO/tiger2.png" id="header-img">
    </header>
    <ul class="nav">
        <li>
            <a href="<%=request.getContextPath()%>/front-sell-end/sell/sellMemIndex.jsp">
            	<i class="zmdi zmdi-view-dashboard"></i> 民宿會員首頁 </a>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/front-sell-end/sell/listOneSell.jsp">
            	<i class="zmdi zmdi-link"></i> 民宿會員基本資料管理
            </a>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/front-sell-end/room/listAllRoom.jsp">
            	<i class="zmdi zmdi-widgets"></i> 房間管理 </a>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/front-sell-end/roomorder/listAllRoomOrder.jsp"> <i class="zmdi zmdi-calendar"></i> 房間訂單管理 </a>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/front-sell-end/activity_product/listOneActivityProduct.jsp"> <i class="zmdi zmdi-calendar"></i> 活動管理 </a>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/front-sell-end/activity_period/listOneActivityPeriod.jsp"> <i class="zmdi zmdi-calendar"></i> 活動期別管理 </a>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/front-sell-end/activity_order/sellListOneActivityOrder.jsp"> <i class="zmdi zmdi-settings"></i> 活動訂單管理 </a>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/front-sell-end/foodspot/listAllFoodSpot.jsp"> <i class="zmdi zmdi-settings"></i> 美食與景點管理 </a>
        </li>
    </ul>
</div>
</body>
</html>
