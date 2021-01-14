<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.roomproductcollect.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%> 
<%@ page import="com.room.model.*"%> 
<jsp:useBean id="collectSvc" scope="request" class="com.roomproductcollect.model.RoomProductCollectService" />
<jsp:useBean id="roomSvc" scope="request" class="com.room.model.RoomService" />
<jsp:useBean id="sellSvc" scope="request" class="com.sell.model.SellService" />
<jsp:useBean id="rpSvc" scope="request" class="com.roomphoto.model.RoomPhotoService" />
<% MemberVO memVO=(MemberVO)session.getAttribute("memVO");
%>

<%
RoomProductCollectService rpcSvc = new RoomProductCollectService();
List<String> rpcListMem = (List<String>)request.getAttribute("rpcListMem");
pageContext.setAttribute("rpcListMem", rpcListMem);
%>



<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">

<title>我的收藏</title>
<style>


@import url('https://fonts.googleapis.com/css?family=Montserrat:400,500,600,700,800,900|Open Sans:400,600,800');

a,
a:hover,
a:focus {
    color: inherit;
}

body {
    background-color: #F1F2F3;
}
.favcontainer{
height:85%;
width:90%;
}

.card-container {
/*     padding: 100px 0px; */
    -webkit-perspective: 1000;
    perspective: 1000;
}

.profile-card-2 {
    max-width: 300px;
    max-height: 400px;
    background-color: #FFF;
    box-shadow: 0px 0px 25px rgba(0, 0, 0, 0.1);
    background-position: center;
    overflow: hidden;
    position: relative;
    margin: 10px auto;
    cursor: pointer;
    border-radius: 10px;
    display:inline-block;
    margin-left:100px;
}

.profile-card-2 img {
    transition: all linear 0.25s;
    width:300px;
    height:400px;
     object-fit:cover;
}

.profile-card-2 .profile-name {
    position: absolute;
    left: 30px;
    bottom: 70px;
    font-size: 30px;
    color: #FFF;
    text-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5);
    font-weight: bold;
    transition: all linear 0.25s;
}

.profile-card-2 .profile-icons {
    position: absolute;
    bottom: 30px;
    right: 30px;
    color: #FFF;
    transition: all linear 0.25s;
}

.profile-card-2 .profile-username {
    position: absolute;
    bottom: 50px;
    left: 30px;
    color: #FFF;
    font-size: 13px;
    transition: all linear 0.25s;
}

.profile-card-2 .profile-icons .fa {
    margin: 5px;
}

.profile-card-2:hover img {
    filter: grayscale(100%);
}

.profile-card-2:hover .profile-name {
    bottom: 80px;
}

.profile-card-2:hover .profile-username {
    bottom: 60px;
}

.profile-card-2:hover .profile-icons {
    right: 40px;
}
#none{
margin-left:100px;
}
.heading{
margin-left:100px;
}


</style>
</head>
<body>
<%@ include file="/front-mem-end/bar.jsp" %>
 	<div class="heading">
		<h2>收藏清單</h2>
	</div>
	<c:choose>
	<c:when test="${rpcListMem==null || rpcListMem.size() == 0}">
	<div id="none">您尚未收藏房間喔!</div>
	</c:when>
	<c:otherwise>
	<div class="favcontainer">
	<div class="row" style="display:flex;">
	<c:forEach var="rpcListMem" items="${rpcListMem}">  
		<div class="col-lg-4" style="display:inline";>  
    		<div class="profile-card-2">
    			<a href="<%=request.getContextPath()%>/room/room.do?roomId=${rpcListMem}&action=checkRoomDetail">
    			<img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomId=${rpcListMem}&action=getOnePhotoByRoomId" class="img img-responsive">
        		<div class="profile-name">${sellSvc.getOneSell(roomSvc.getOneRoom(rpcListMem).sellMemId).sellRoomName}</div>
        		<div class="profile-username">${roomSvc.getOneRoom(rpcListMem).roomName} $ ${roomSvc.getOneRoom(rpcListMem).roomPrice}</div>
        		<div class="profile-icons"><i class="far fa-heart"></i>${collectSvc.getCountCollect(rpcListMem)}</div>
    			</a>
   			</div>	
		</div>
	</c:forEach>
	</div>
	</div>
	</c:otherwise>
	</c:choose>
      
</body>
</html>