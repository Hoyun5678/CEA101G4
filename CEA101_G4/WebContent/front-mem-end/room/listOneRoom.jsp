<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.room.model.*"%>   
<%@ page import="com.member.model.*"%>   
<%@ page import="com.foodspot.model.*"%> 
<% MemberVO memVO=(MemberVO)session.getAttribute("memVO");
String checkInDate =(String) request.getAttribute("checkInDate");
String checkOutDate =(String) request.getAttribute("checkOutDate");
%>

<jsp:useBean id="roomVO" scope="request" class="com.room.model.RoomVO" />
<jsp:useBean id="sellVO" scope="request" class="com.sell.model.SellVO" />
<%-- <jsp:useBean id="rpVO" scope="request" class="com.roomphoto.model.RoomPhotoVO" /> --%>
<jsp:useBean id="roomphotoSvc" scope="page" class="com.roomphoto.model.RoomPhotoService" />
<jsp:useBean id="roomOrderedDateSvc" scope="page" class="com.roomordereddate.model.RoomOrderedDateService" />
<jsp:useBean id="sellSvc" scope="page" class="com.sell.model.SellService" />
<jsp:useBean id="rpcSvc" scope="page" class="com.roomproductcollect.model.RoomProductCollectService" />
<jsp:useBean id="fsSvc" scope="page" class="com.foodspot.model.FoodSpotService" />
<c:set var="roomPhotoList" scope="page" value="${roomphotoSvc.getByRoomId(roomVO.roomId)}"  />
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/front-mem-end/front-mem-listOneRoom.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.8.1/baguetteBox.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">

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
                 <div class="col-lg-9">
                    <div class="photogallery">
                        <div class="row">
                            <div class="col-lg-8" style="width:570px;height:428.5px;">
                                <a class="lightbox" href="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoList[0].roomPhotoId}&action=getOnePhoto">
                                    <img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoList[0].roomPhotoId}&action=getOnePhoto">
                                </a>
                            </div>
                            <div class="col-lg-4" style="width:285px;height:428.5px;">
                                <div class="col-lg up" style="width:285px;height:214.75px;padding:0px;">
                                    <a class="lightbox" href="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoList[1].roomPhotoId}&action=getOnePhoto">
                                        <img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoList[1].roomPhotoId}&action=getOnePhoto">
                                    </a>
                                </div>
                                <div class="col-lg down" style="width:285px;height:214.75px;padding:0px;">
                                    <a class="lightbox" href="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoList[2].roomPhotoId}&action=getOnePhoto">
                                        <img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoList[2].roomPhotoId}&action=getOnePhoto">
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="width:855px;height:214.75px;">
                            <div class="col-lg-4" style="width:285px;height:214.75px;">
                                <a class="lightbox" href="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoList[3].roomPhotoId}&action=getOnePhoto">
                                    <img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoList[3].roomPhotoId}&action=getOnePhoto">
                                </a>
                            </div>
                            <div class="col-lg-4" style="width:285px;height:214.75px;">
                                <a class="lightbox" href="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoList[4].roomPhotoId}&action=getOnePhoto">
                                    <img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoList[4].roomPhotoId}&action=getOnePhoto">
                                </a>
                            </div>
                            <div class="col-lg-4" style="width:285px;height:214.75px;">
                                <a class="lightbox" href="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoList[5].roomPhotoId}&action=getOnePhoto">
                                    <img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoList[5].roomPhotoId}&action=getOnePhoto">
                                </a>
                            </div>
                        </div>
                    </div>
                    </div>
                    <div class="col-lg-3" id="rightside">
                     <div id="firstcol">
                        <div id="ratingscore">
                            <h4 id="score">9.2</h4>
                        </div>
                        <div id="scoremeaning">
                            <div id="up">完美，太漂亮了吧</div>
                            <div id="down">/10 (89則評論)</div>
                        </div>
                        <div class="keyword">老闆娘太正</div>
                        <div class="keyword">房間舒適</div>
                    </div>
                    <div id="secondcol">
                        <div class="distance">
                            <div style="font-weight:600;margin-bottom:10px;">人氣景點</div>
                            <div>中壢觀光夜市------------5.72 km </div>
                            <div>中壢----------------------5.94 km</div>
                            <div>華泰名品城---------------6.79 km</div>
                            <div>虎頭山公園---------------6.91 km</div>
                        </div>
                    </div>
                    <div id="thirdcol">
                        
                        <div class="googlemap">
                            <iframe width="255px" height="200px" frameborder="0" style="border:0" src="https://www.google.com/maps/embed/v1/place?key=AIzaSyApgPPHUGkfdZcb0Teerhh7AStmD4zWPWE&q=${sellVO.sellMemAddress}" allowfullscreen>
                            </iframe>
                        </div>
                       
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-9">
                    <div style="font-style:Microsoft JhengHei;font-size:16px;">以善待家人的心情珍惜來訪旅人與生活土地。 在日光綠築我們客製化服務每個旅人需求，在相同如家溫暖裡留下不一樣新鮮回憶。 如同健康生活初衷，我們謙卑與自然學習共生的方式，打造日光綠築成為一間有生命隨四季變化的綠建築。 我們由衷希望帶起一場生活革命，以全新角度看待健康生活這件事，與大家一同分享來自日光田野間的感動......
                   	日光綠築融合生態工法綠建築，使用隔熱玻璃，種植親水落羽松引入冬山河水。形成通風採光冬暖夏涼，自然宜人的生態棲地。 在日光綠築可以感受萬物生命四季更迭，在這裏每刻光景都不一樣。有青綠秧苗、金黃水稻，還有盛夏落羽松、秋日楓紅。 我們在田野間以透明帷幕收納四季美景，拉近起居、農場、人及自然間的距離。
                   	</div>
                   	</div>
                    
                    <div class="col-lg-3">
                    </div>
                </div>
                <h5 id="emptitle" style="text-align: center;">空房情形</h5>
                
                <c:set var='roomOrderedDateList' scope='page' value='${roomOrderedDateSvc.getOrderedDateByRoomId(roomVO.roomId)}' />
                <input type="hidden" name='roomOrderedDateList' value='${roomOrderedDateList}' />
                <input type="hidden" name='roomId' value='${roomVO.roomId}' />
                <input type="hidden" name='contextPath' value='<%=request.getContextPath()%>' />
				<div class="row calendarRow justify-content-center">
					<div class="calendarDiv currentCalendar" style="margin-right: 15px">
						<div class="d-flex align-content-center">
							<div class="mr-auto align-content-center lastMonth align-items-center d-flex">
								<span class="material-icons">
                        			keyboard_arrow_left
                        		</span>
                        	</div>
                       		<div class="d-inline-block yearMonth align-items-center d-flex">
								<h3><span class="mon">JAN</span> 
                                <span class="year">2021</span></h3>
							</div>
							<div class="d-inline-block ml-auto invisible">
								<span class="material-icons">
									keyboard_arrow_right
								</span>
							</div>
                               

						</div>
						<table class="calendarBody">
							<thead>
	                            <tr>
	                                <td>SUN</td>
	                                <td>MON</td>
	                                <td>TUE</td>
	                                <td>WED</td>
	                                <td>THU</td>
	                                <td>FRI</td>
	                                <td>SAT</td>
	                            </tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
					<div class="calendarDiv nextCalendar">
						<div class="d-flex align-content-center">
							<div class="mr-auto d-inline-block invisible">
		                 		<span class="material-icons">
		                 			keyboard_arrow_left
		                 		</span>
                 			</div>
						<div class="d-inline-block align-content-center yearMonth align-items-center d-flex">
							<h3><span class="mon">FEB</span> 
							<span class="year">2021</span></h3>
						</div>
						<div class="d-inline-block ml-auto nextMonth align-content-center align-items-center d-flex">
                         	<span class="material-icons">
                 				keyboard_arrow_right
                 			</span>
						</div>
					</div>
					<table class="calendarBody">
					    <thead>
					        <tr>
					            <td>SUN</td>
					            <td>MON</td>
					            <td>TUE</td>
					            <td>WED</td>
					            <td>THU</td>
					            <td>FRI</td>
					            <td>SAT</td>
					        </tr>
					    </thead>
					    <tbody></tbody>
					</table>
				</div>
			</div>
			<div class="row justify-content-center d-flex align-items-center" id="emptyarea">
			    <div class="col-3" id="checkin" style=''>
			        <div class="datetitle">入住日期</div> 
			        <input readonly type="text" name="checkInDate" value="${checkInDate}" /> 
			    </div>
			    <div class="col-3" id="checkout">
			        <div class="datetitle">退房日期</div>
			        <input readonly type="text" name="checkOutDate" value="${checkOutDate}" />
			    </div>
			    <div class="col-3" id="change">
			        <div id="day">共選了<span id='dayCount'>一</span>晚</div>
<!-- 			        <input id="changebtn" type="button" value="更改搜尋條件" name="changedate"> -->
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
                                  
                        <div class="roomGrid roompic">
                        	<div> ${roomVO.roomName}</div>
                        	<img src="<%=request.getContextPath()%>/roomphoto/roomphoto.do?roomPhotoId=${roomPhotoList[0].roomPhotoId}&action=getOnePhoto">
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
                        <button type="button" class="active first"></button>
                        <button type="button" class="second"></button>
                        <button type="button" class="third"></button>
                    </div>
                </div>
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
    
    </script>
    

</body>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/datetimepicker/jquery.datetimepicker.css" />
    <script src="${pageContext.request.contextPath}/datetimepicker/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/datetimepicker/jquery.datetimepicker.full.js"></script>
	<script src="${pageContext.request.contextPath}/js/front-mem-end/listOneRoom.js"></script>
	

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