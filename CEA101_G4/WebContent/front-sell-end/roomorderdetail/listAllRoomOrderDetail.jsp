<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomorderdetail.model.*"%>

<%
    RoomOrderDetailService rodSvc = new RoomOrderDetailService();
    List<RoomOrderDetailVO> list = rodSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="roomSvc" scope="page" class="com.room.model.RoomService" />

<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/0316f9a1d0.js" crossorigin="anonymous"></script>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<title>所有訂單資料 - listAllRoomOrderDetail.jsp</title>

<style>
	.content{
		width:1030px!important;
	}
	table img{
  		width:100px;
  		height:80px;
  	}
  	input.btn_mod {
  		border: 1px solid #ccd9d1;
  		border-radius:5%;
		background-color: #679186;
		color: #e6eced;
		padding: 5px 7px;
		font-size: 16px;
		cursor: pointer;
	}

  	input.btn_del {
  		border: 1px solid #ced3df;
  		border-radius:5%;
  		background-color:#677591;
  		color: #e6eced;
  		padding: 5px 7px;
  		font-size: 16px;
  		cursor: pointer;
	}	
  	button#add{
  		background-color:#cc907b;
  		color:white;
		font-size:16px;
		font-weight:300;
		padding:3px 5px 3px 5px;
	    float:right;
		margin-bottom:30px;
	}
</style>

</head>
<body>
<%@ include file="/front-sell-end/sellMemSideBar.jsp" %> 
<div class="content">
    <div class="content-header">
       <h2>房間訂單管理</h2>
       </div>        
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

 	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomorderdetail/roomorderdetail.do " >
 	
        <b>查詢房間訂單編號 (如RO001):</b> 
        <span class="icon"><i class="fa fa-search"></i></span>
        <input type="text" name="room_order_id" id="search" placeholder="Search...">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出" id="find">
 	</FORM>

<div class="right">
<button type="button" class="btn" id="add" onclick="window.location.href='/CEA101G4/front-sell-end/roomorderdetail/addRoomOrderDetail.jsp'"><i class="fas fa-plus" style="padding-right:5px"></i>新增</button>

</div>

<table class="table table-striped align-middle" id="tb" >

	<tr id ="th">
	<tr>
		<th>房間訂單編號</th>
		<th>房間名稱</th>
		<th>房間價格</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="rodVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${rodVO.room_order_id}</td>
			<td>${roomSvc.getOneRoom(rodVO.room_id).roomName}</td>
			<td>${rodVO.room_cur_price}</td>
			

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomorderdetail/roomorderdetail.do" style="margin-bottom: 0px;">
			     <input class="btn_mod" type="submit" value="修改">
			      <input type="hidden" name="room_order_id"  value="${rodVO.room_order_id}">
			      <input type="hidden" name="room_id"  value="${rodVO.room_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomorderdetail/roomorderdetail.do" style="margin-bottom: 0px;">
			     <input class="btn_del" type="submit" value="刪除">
			     <input type="hidden" name="room_order_id"  value="${rodVO.room_order_id}">
			     <input type="hidden" name="room_id"  value="${rodVO.room_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
  
</div>


   </script>



</body>
</html>