<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="roomPhotoSvc" scope="page" class="com.roomphoto.model.RoomPhotoService" />
<jsp:useBean id="roomSvc" scope="page" class="com.room.model.RoomService" />

<c:set var="roomId" value="${param.roomId}"/>
<c:set var="roomVO" scope="page" value="${roomSvc.getOneRoom(param.roomId)}"/>
<c:set var="list" scope="page" value="${roomPhotoSvc.getByRoomId(roomVO.roomId)}"/>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    
	<title>民宿會員 - 查看房間圖片</title>
</head>
<body>
	<div id="viewport">
		<%@ include file="/front-sell-end/sellMemSideBar.jsp"%>
		<div id="content">
			<%@ include file="/front-sell-end/sellNavBar.jsp"%>
			<div class="container-fluid" style="padding: 0;">
				<div class="container">
					<h1>${roomVO.roomName}的展示圖片</h1>
                	
					<div class="row">
						<c:if test="${not empty errorMsgs}">
							<%-- 錯誤表列 from Servlet --%>
							<div class="alert alert-danger col-lg-6 col-lg-offset-1" role="alert" id="titleAndError">
								<font style="color:red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color:red">${message}</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>
					</div>
					
					<div class="container custom-width" id="showPics">
						<div class="row">
						
							<c:forEach var="roomPhotoVO" items="${list}" >
								<div class="col-12 col-sm-6 col-lg-4 photoBox">
									<div>
										<img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoVO.roomPhotoId}&action=getOnePhoto" class="img-thumbnail object-fit">
										<input type="hidden" name="roomPhotoId" value="${roomPhotoVO.roomPhotoId}">
										<button type="button" class="btn btn-outline-info editBtn">修改</button>
									</div>
								</div>
							</c:forEach>
						
						</div>
					</div>
					
					
					
                </div>
			</div>
		</div>
	</div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	
</body>
</html>

