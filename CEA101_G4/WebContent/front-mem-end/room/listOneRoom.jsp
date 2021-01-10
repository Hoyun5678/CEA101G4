<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.room.model.*"%>   
<%@ page import="com.member.model.*"%>   
<%@ page import="com.foodspot.model.*"%> 
<% MemberVO memVO=(MemberVO)session.getAttribute("memVO"); %>

<jsp:useBean id="roomVO" scope="request" class="com.room.model.RoomVO" />
<jsp:useBean id="sellVO" scope="request" class="com.sell.model.SellVO" />
<%-- <jsp:useBean id="rpVO" scope="request" class="com.roomphoto.model.RoomPhotoVO" /> --%>
<jsp:useBean id="roomphotoSvc" scope="page" class="com.roomphoto.model.RoomPhotoService" />
<jsp:useBean id="sellSvc" scope="page" class="com.sell.model.SellService" />
<jsp:useBean id="rpcSvc" scope="page" class="com.roomproductcollect.model.RoomProductCollectService" />
<jsp:useBean id="fsSvc" scope="page" class="com.foodspot.model.FoodSpotService" />
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

<style>
.red_heart{
color:#dc3545;
}
.black_heart{
color:black;
}

</style>

</head>
<body>
<%@ include file="/front-mem-end/bar.jsp" %> 
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
                    <c:set var="checkcount" scope="page" value="${rpcSvc.getCollect(memVO.mem_id,roomVO.roomId)}"/>
                    
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
                        <div class="roomGrid roompri">$ ${roomVO.roomPrice}</div>                                     
                        <input type="hidden" name="roomPrice" value="${roomVO.roomPrice}" />
                        <input type="hidden" name="roomId" value="${roomVO.roomId}" />
                        <input type="hidden" name="action" value="fillorderinfo" />
			     		<div class="roomGrid bookbtn">
                        	<button type="submit" id="bookbtn">現在就預訂</button>  
                        </div> 
                        </c:forEach>
                    </div> 
                </div>                   
           </div>            
            <div id="food">
                <div id="foodtitle">推薦美食與景點</div>
                <div class="center">
                    <div class="wrapper">
                        <div class="inner">
                        <c:forEach var="fsVO" items="${fsSvc.getOneBySell(roomVO.sellMemId)}">
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
            
           </FORM> 
        <script>
        baguetteBox.run('.photogallery');
        const buttonsWrapper = document.querySelector(".map");
        const slides = document.querySelector(".inner");

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
      	  				"mem_id":'${memVO.mem_id}',
      	  				"room_id":'${roomVO.roomId}',
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
    

</body>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/datetimepicker/jquery.datetimepicker.css" />
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