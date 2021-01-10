<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roomorderdetail.model.*"%>

<%
  RoomOrderDetailVO rodVO = (RoomOrderDetailVO) request.getAttribute("rodVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)

%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/front-sell-end/roomorderdetail-upd.css">
<title>訂單資料修改 - update_roomorderdetail_input.jsp</title>

</head>
<body bgcolor='white'>
<%@ include file="/front-sell-end/sellMemSideBar.jsp" %> 

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div class="container">
<h3>修改房間明細</h3>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomorderdetail/roomorderdetail.do" name="form1">
	<div class="row">
		<div class="col-4">
			<span>房間訂單編號:</span>
		</div>
		<div class="col-8" style="padding-top: 10px;padding-left: 10px;">	
			<%=rodVO.getRoom_order_id()%>
		</div>
	</div>
	<div class="row">
		<div class="col-4">
			<span>房間編號:</span>
		</div>
		<div class="col-8" style="padding-top: 10px;padding-left: 10px;">	
			<%=rodVO.getRoom_id()%>
		</div>
	</div>
	<div class="row">
		<div class="col-4">
			<label for="rprice" class="text-small-uppercase">房間價格:</label>
		</div>
		<div class="col-8">
			<input type="TEXT" name="room_cur_price" size="30" 
			 value="<%= (rodVO==null)? "7788" : rodVO.getRoom_cur_price()%>" />
		</div>
	</div>
	
<div class="row">
<input type="hidden" name="action" value="update">
<input type="hidden" name="room_order_id" value="<%=rodVO.getRoom_order_id()%>">
<input type="hidden" name="room_id"  value="<%=rodVO.getRoom_id()%>">
<input type="submit" value="送出修改">
</div>
</FORM>
</div>
</body>

</html>