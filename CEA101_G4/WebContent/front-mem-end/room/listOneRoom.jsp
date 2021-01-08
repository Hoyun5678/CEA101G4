<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.room.model.*"%>   
<%@ page import="com.member.model.*"%>   
<% MemberVO memVO=(MemberVO)session.getAttribute("memVO"); %>
<%@ page import="com.room.model.*"%>

<jsp:useBean id="roomVO" scope="request" class="com.room.model.RoomVO" />
<jsp:useBean id="sellVO" scope="request" class="com.sell.model.SellVO" />
<%-- <jsp:useBean id="rpVO" scope="request" class="com.roomphoto.model.RoomPhotoVO" /> --%>
<jsp:useBean id="roomphotoSvc" scope="page" class="com.roomphoto.model.RoomPhotoService" />
<jsp:useBean id="sellSvc" scope="page" class="com.sell.model.SellService" />
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/0316f9a1d0.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="crossorigin="anonymous"></script>
    <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js'></script>
    <link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'>
      
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.8.1/baguetteBox.min.js"></script>
   
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/front-mem-end/oneRoom.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.8.1/baguetteBox.min.css">

<title>Insert title here</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<!--     <link rel="stylesheet" href="css/style.css"> -->

<title>Insert title here</title>
<style>
.red_heart{
color:#dc3545;
}
.black_heart{
color:black;
}

=======
		/* 圖片輪播設定 */
		
	.carousel-item {
		height: 350px;
		
	}
	.carousel-item img {
		width: 100%;
		
		over-flow: hidden;
		position: absolute;
		top: 50%;
		left: 0;
		transform: translateY(-50%);
	}
	
	#map {
    height: 100%;
	}

	
</style>



</head>
<body>
	<%@ include file="/front-mem-end/bar.jsp"%>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomorder/roomorder.do">
 <div class="container">
        <div class="row">
            <div id="title">
            <input type="hidden" name="sellMemId" value="${roomVO.sellMemId}" />
			
			<input type="hidden" name="memId" value="${memVO.mem_id}" />
                <c:set var="sellVO" scope="page" value="${sellSvc.getOneSell(roomVO.sellMemId)}"/>
				 <h3>${sellVO.sellRoomName}</h3>
                <div class="fav_area">
                    <i class="far fa-star 2x" id="rate"></i>
                    <font face="monospace" ;font color="#8E8787">4.79(36)</font>
                    <h6> <a class="area" href="#">${sellVO.sellMemAddress}</a></h6>
                    <i class="fas fa-share-alt 2x" id="share"></i>
                    <font face="monospace" ;font color="#8E8787"><u>分享</u></font>
                    <c:set var="checkcount" scope="page" value="${rpcSvc.getCollect('MEM003',roomVO.roomId)}"/>
                    
                    <c:if test="${checkcount==true}">
                    <i class="far fa-heart 2x red_heart" id="fav"></i>
                    <font face="monospace" ;font color="#8E8787"><u>儲存</u></font>
                    <span id="save">(${rpcSvc.getCountCollect(roomVO.roomId)})</span>
                    </c:if>
                    
                    <c:if test="${checkcount==false}">
                    <i class="far fa-heart 2x black_heart" id="fav" ></i>
                    <font face="monospace" ;font color="#8E8787"><u>儲存</u></font>
                    <span id="save">(${rpcSvc.getCountCollect(roomVO.roomId)})</span>
                    </c:if>
                    
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="upside">
            <div class="row">
                
                    <div class="photogallery">
                   <c:forEach var="roomPhotoVO" items="${roomphotoSvc.getByRoomId(roomVO.roomId)}" begin="1" end="6" step="1" varStatus="status">
                        <c:if test="${status.count % 6 == 1}" >
                        <div class="row">
                            <div class="col-lg-8">
                                <a class="lightbox" id="bigpic" >
                                <a class="lightbox" href="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoVO.roomPhotoId}&action=getOnePhoto">
                                    <img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoVO.roomPhotoId}&action=getOnePhoto">
                                </a>
                            </div>
                          </c:if>   
                            <c:if test="${status.count % 6 == 2}" >
                            <div class="col-lg-4">
                                <div class="col-lg up">
                                    <a class="lightbox" href="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoVO.roomPhotoId}&action=getOnePhoto">
                                        <img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoVO.roomPhotoId}&action=getOnePhoto">
                                    </a>
                                </div>
                                </c:if> 
                                 <c:if test="${status.count % 6 == 3}" >
                                <div class="col-lg down">
                                    <a class="lightbox" href="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoVO.roomPhotoId}&action=getOnePhoto">
                                        <img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoVO.roomPhotoId}&action=getOnePhoto">
                                    </a>
                                </div>
                            </div>
                        </div>
                        </c:if> 
                       
                      
                        <c:if test="${status.count % 6 == 4}" >
                        <div class="row">
                            <div class="col-lg-4">
                                <a class="lightbox" href="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoVO.roomPhotoId}&action=getOnePhoto">
                                    <img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoVO.roomPhotoId}&action=getOnePhoto">
                                </a>
                            </div>
                         </c:if>
                          <c:if test="${status.count % 6 == 5}" >
                            <div class="col-lg-4">
                                <a class="lightbox" href="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoVO.roomPhotoId}&action=getOnePhoto">
                                    <img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoVO.roomPhotoId}&action=getOnePhoto">
                                </a>
                            </div>
                          </c:if>
                           <c:if test="${status.count % 6 == 0}" >
                            <div class="col-lg-4">
                                <a class="lightbox" href="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoVO.roomPhotoId}&action=getOnePhoto">
                                    <img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoVO.roomPhotoId}&action=getOnePhoto">
                                </a>
                            </div>
                        </div>
                        </c:if>
                        
                       
                    </c:forEach>
                    </div>
                

            <div class="container">
                <div class="row">
                    <div class="col-lg-9">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                    </div>
                    <div class="col-lg-3">
                    </div>
                </div>
                <h5 id="emptitle">空房情形</h5>
                <div class="row" id="emptyarea">
                    <div class="col-lg-4" id="checkin">
                        <div class="datetitle">入住日期</div> 
                        <input type="text" name="checkInDate" value="2021-02-03" /> 
                        
              
                    </div>
                    <div class="col-lg-4" id="checkout">
                        <div class="datetitle">退房日期</div>
                        <input type="text" name="checkOutDate" value="2021-02-05" />
                    </div>
                    <div class="col-lg-4" id="change">
                        <div id="day">共選了一晚</div>
                        <input id="changebtn" type="button" value="更改搜尋條件" name="changedate">
                    </div>
                </div>
<!--MODAL更改時間-->
				<div id="myModal" class="modal">
					<div class="modal-content">
				<span>入住日期: <input name="start_date" id="start_date" type="text" style="width:120px;"><i class="far fa-calendar-alt"></i></span>
  				<span>退房日期: <input name="end_date"   id="end_date"   type="text" style="width:120px;"><i class="far fa-calendar-alt"></i></span>
					
    				<span class="close">&times;</span>
 					</div>
				</div>
				
                <div class="bookroom">
                    <div class="room_header">
                        <div class="roomheader">客房類型</div>
                        <div class="roomheader">客房介紹</div>
                        <div class="roomheader">適合人數</div>
                        <div class="roomheader">今日價格</div>
                        <div class="roomheader">房數</div>
                        <div class="roomheader">心動不如馬上行動</div>
                    </div>
                     
                    <div class="room_product">
                     <c:forEach var="roomPhotoVO" items="${roomphotoSvc.getByRoomId(roomVO.roomId)}" begin="1" end="1"> 
                        <div>
                        	<div class="roomGrid roompic">${roomVO.roomName}</div>
						 	<img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoVO.roomPhotoId}&action=getOnePhoto" style="width:160px;height:120px;"> 
                        </div>
                        <div class="roomGrid roominfo">${roomVO.roomDes}</div>
                        <div class="roomGrid roompeo">${roomVO.roomCapacity}位</div>
                        <div class="roomGrid roompri">
                        <input type="text" name="roomOrderSum" value="${roomVO.roomPrice}" /></div>
                        <div class="roomGrid roomamo">
                            <select>
                                <option>選擇客房數</option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                       </div>
                         <input type="hidden" name="roomId" value="${roomVO.roomId}" />
                         <input type="hidden" name="action" value="fillorderinfo" />
			     		 <div class="roomGrid bookbtn">
                            <button type="submit" id="bookbtn">現在就預訂</button>  
                        </div>
                    </c:forEach> 
                    </FORM>  
              </div>
            <div id="food">
                <div id="foodtitle">推薦美食與景點</div>
                <div class="center">
                    <div class="wrapper">
                        <div class="inner">
                        <c:forEach var="fsVO" items="${fsSvc.getAll()}">
                            <div class="card">
                                <img src="${pageContext.request.contextPath}/foodspot/foodspot.do?fas_id=${fsVO.fas_id}&action=getFSPhoto">
                                <div class="content">
                                    <h1>${fsVO.fas_spot_name}</h1>
                                    <h3>${fsVO.fas_des}</h3>
                                </div>
                            </div>
                            </c:forEach>
                            
                        </div>
                    </div>
                    <div class="map">
                        <button class="active first"></button>
                        <button class="second"></button>
                        <button class="third"></button>
                    </div>
                </div>
            </div>
           
        <script>
        baguetteBox.run('.photogallery');
        const buttonsWrapper = document.querySelector(".map");
        const slides = document.querySelector(".inner");
=======
>>>>>>> branch 'main' of https://github.com/Hoyun5678/CEA101G4.git

<<<<<<< HEAD
        buttonsWrapper.addEventListener("click", e => {
            if (e.target.nodeName === "BUTTON") {
                Array.from(buttonsWrapper.children).forEach(item =>
                    item.classList.remove("active")
                );
                if (e.target.classList.contains("first")) {
                    slides.style.transform = "translateX(-0%)";
                    e.target.classList.add("active");
                } else if (e.target.classList.contains("second")) {
                    slides.style.transform = "translateX(-33.33333333333333%)";
                    e.target.classList.add("active");
                } else if (e.target.classList.contains('third')) {
                    slides.style.transform = 'translatex(-66.6666666667%)';
                    e.target.classList.add('active');
                }
            }
        });
        //收藏
        $("body").on('click', "#fav", function(){
//       	  	if(mem_id==""){
//       			swal(
//       				  '請先登入～'
//       			  )
//       		}else{
      	  		var action_for = "addCollect";
      	  		var heart = $("#fav");
      	  		console.log("inside");
      	  		if($(this).hasClass("red_heart")){
      	  			action_for = "deleteCollect";
      	  		}
      	  		$.ajax({
      	  			url: "<%= request.getContextPath() %>/roomproductcollect/roomproductcollect.do",
      	  			data: {
      	  				"action":action_for,
      	  				"mem_id":"MEM003",
      	  				"room_id":"${roomVO.roomId}",
      	  				},
      	  			dataType: "text", //可放xml,json,script,html
      	  			cache:false,
      	  			type:"post",
      	  			error:function(res, textStatus, jqXHR){
      	  			heart.toggleClass("red_heart");
      	  			heart.toggleClass("black_heart");
//       	  				console.log(res);
      	  				
      	  			},
      	  							//參數放servlet回傳的按讚數
      	  			success: function(data, textStatus, jqXHR){
      	  				heart.toggleClass("red_heart");
      	  				heart.toggleClass("black_heart");
      	  				$("#save").text(data);
      	  			}
      	
      	  		});
//       		}
        	})
        //MODAL更改時間
         // Get the modal
        var modal = document.getElementById("myModal");

        // Get the button that opens the modal
        var changebtn = document.getElementById("changebtn");

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];

        // When the user clicks the button, open the modal 
        changebtn.onclick = function() {
            modal.style.display = "block";
        }

        // When the user clicks on <span> (x), close the modal
        span.onclick = function() {
            modal.style.display = "none";
        }

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>
    
=======
	<div class="container">
		<div class="row">
			<div class="col-12">照片
				<div id="indicators${rowStatus.index}" class="carousel slide" data-ride="carousel">
					<ol class="carousel-indicators">
						<c:forEach var="roomphotoVO" items="${roomphotoSvc.getByRoomId(roomVO.roomId)}" varStatus="status">
						
						<li data-target="#indicators${rowStatus.index}" data-slide-to='<c:out value="${status.index}" />' class=""></li>
						</c:forEach>
					</ol>
				<div class="carousel-inner">
					<c:forEach var="roomphotoVO" items="${roomphotoSvc.getByRoomId(roomVO.roomId)}">
					<div class="carousel-item">
						<img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomphotoVO.roomPhotoId}&action=getOnePhoto" class="d-block">
					</div>
					</c:forEach>
	
				</div>
				<a class="carousel-control-prev" href="#indicators${rowStatus.index}" role="button" data-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a>
				<a class="carousel-control-next" href="#indicators${rowStatus.index}" role="button" data-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-6">房間資訊
				<div>房間名稱: ${roomVO.roomName}</div>
				<div>住宿價格/天: ${roomVO.roomPrice}</div>
				<div>房間容納人數: ${roomVO.roomCapacity}</div>
				<div>房間上架時間: <fmt:formatDate value="${roomVO.roomOnTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></div>
				<div>房間收藏次數: ${roomVO.roomCollect}</div>
				<div>房間敘述: ${roomVO.roomDes}</div>
			</div>
			<div class="col-6">Google 地圖
				<div id="map" class="">
				</div>
			</div>
			<div class="col-6">關於房東
			<c:set var="sellVO" scope="page" value="${sellSvc.getOneSell(roomVO.sellMemId)}"/>
				<div>房東名字: ${sellVO.sellMemName}</div>
			
			
			</div>
			<div class="col-6">訂房狀態(日曆)</div>
		</div>
	</div>
	
	<div>
		<form id="insertRoomOrder" action="<%=request.getContextPath() %>/roomorder/roomorder.do" method="POST">
			<div>測試新增訂單</div>
			<input type="text" name="sellMemId" value="${sellVO.sellMemId}" />
			<input type="text" name="roomId" value="${roomVO.roomId}" />
			<input type="text" name="memId" value="MEM005" />
			<input type="text" name="checkInDate" value="2021-02-03" />
			<input type="text" name="checkOutDate" value="2021-02-05" />
			<input type="text" name="expectArrTime" value="2021-02-03 15:00:00" />
			<input type="text" name="roomOrderRemarks" value="我要帶狗狗" />
			<input type="text" name="roomOrderSum" value="20000" />
			<input type="text" name="room_cur_price" value="10000" />
			<input type="text" name="room_guest_name" value="123" />
			<input type="text" name="room_guest_mail" value="有mail" />
			<input type="text" name="room_guest_tel" value=" 0912333444" />
			<input type="hidden" name="action" value="insert" />
			
			<button type="submit">新增訂單</button>
		</form>
	</div>

	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<!--     <script -->
<!--   		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDK-5rFDe76_LASpBLJJSHYd1JM7W9ttWg"> -->
<!--  	</script> -->
	<script>
    	$(document).ready(function() {
			$(".carousel-indicators li:first-child").addClass("active");
			$(".carousel-inner .carousel-item:first-child").addClass("active");
			initMap();

		});
		let initMap = function() {
			let map = new google.maps.Map(document.getElementById("map"), {
				center: { lat: 24.9576852, lng: 121.2250143 },
				zoom: 15,
			});
		} 
	</script>
>>>>>>> branch 'main' of https://github.com/Hoyun5678/CEA101G4.git

</body>
 <link   rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/datetimepicker/jquery.datetimepicker.css" />
    <script src="${pageContext.request.contextPath}/datetimepicker/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 300px;   /* height:  300px; */
  }
</style>

<script>
$.datetimepicker.setLocale('zh'); 
$(function(){
	 $('#start_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#end_date').val()?$('#end_date').val():false
	   })
	  },
	  timepicker:false
	 });
	 
	 $('#end_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#start_date').val()?$('#start_date').val():false
	   })
	  },
	  timepicker:false
	 });
});
</script>

</html>