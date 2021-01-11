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
	<style>
		img {
			max-width: 100%;
		}
 		.photoBox div { 
  			max-width: 100%;  
  			height: 160px;
			position: relative;
 			overflow: hidden; 
 		} 
 		.photoBox div:active { 
  			top: 2px;
  			left: 2px;
  			opacity: .7;
  			filter: grayscale();
 		} 
 		
		.photoBox div img {
			position: absolute;
			top: 50%;
			left: 0;
			transform: translateY(-50%);
		}
		.toDelete {
			opacity: .7;
			filter: grayscale();
		}
		.btns {
			position: fixed;
			right: 10%;
			bottom: 10%;
		}
	</style>
</head>
<body>
	<div id="viewport">
		<%@ include file="/front-sell-end/sellMemSideBar.jsp"%>
		<div id="content">
			<%@ include file="/front-sell-end/sellNavBar.jsp"%>
			<div class="container-fluid" style="padding: 0;">
				<div class="container">

					<div class="container custom-width" id="showPics">
						<div class="row">
							<div class="offset-1 col-10" style="margin-top: 30px;">
								<h2>${roomVO.roomName}的展示圖片</h2>
							</div>
							<c:forEach var="roomPhotoVO" items="${list}" >
								<div class="col-12 col-sm-6 col-lg-4 photoBox">
									<div>
										<img class="roomPhotoImg" name="${roomPhotoVO.roomPhotoId}" src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoVO.roomPhotoId}&action=getOnePhoto">
										<input type="hidden" name="roomPhotoId" value="${roomPhotoVO.roomPhotoId}" class="roomPId">
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
                </div>
			</div>
		</div>
	</div>
	<div class="btns">
		<button class="deleteBtn btn btn-danger d-none">刪除圖片</button>
		<button class="uploadBtn btn btn-primary" style="margin-left: 10px;">上傳圖片</button>
	</div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
    	$(document).ready(function() {
    		var contextPath = '<%=request.getContextPath()%>';
    		var urlTarget = contextPath + '/roomphoto/roomphoto.do';
    		
    		$('.roomPhotoImg').click(function() {
    			$(this).toggleClass('toDelete');
    			
    			let toDeleteList = $('.toDelete');
    			if(toDeleteList.length > 0) {
    				$('.deleteBtn').removeClass('d-none');
    			} else {
    				$('.deleteBtn').addClass('d-none');
    			}
    		})
    		
    		
    		$(document).on('click', '.deleteBtn', function() {
    			let toDelete = document.querySelector('.toDelete');
    			do {
    				console.log(toDelete[0]);
    				var pare = toDelete[0].parentNode.parentNode;
    				$.ajax({
    					url: urlTarget, 
    					type: "POST",
    					data: {
    						"action": "delete",
    						"roomPhotoId": toDelete[0].name,
    					},
    					success: function(data) {
    						console.log(data);
    					},
    				})
    				pare.innerHTML =``;
    				toDelete = $('.toDelete');
    				} while (toDelete);
    		})
    		
    		$(document).on('click', '.uploadBtn', function() {
    			
    		})
    		
    	})
    </script>
	
</body>
</html>

