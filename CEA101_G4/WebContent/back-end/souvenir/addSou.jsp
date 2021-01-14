<%@page import="com.souvenir_product.model.SouvenirProductVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.souvenir_product.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.souvenir_photo.model.SouvenirPhotoVO"%>



<%
	SouvenirProductVO soupVO = (SouvenirProductVO) request.getAttribute("soupVO");
%>

<%
	SouvenirPhotoVO souphVO = (SouvenirPhotoVO) request.getAttribute("souphVO");
%>
<%-- <%= soupVO==null %>--${soupVO.deptno}-- --%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>特產資料新增 - addSou.jsp</title>

<style>

/*---------------------------------------------------------*/
body {
	background-color: #E5E5E5;
}

.press, input[type=submit] {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f9f9f9
		), color-stop(1, #d9d9d9));
	background: -moz-linear-gradient(top, #f9f9f9 5%, #d9d9d9 100%);
	background: -webkit-linear-gradient(top, #f9f9f9 5%, #d9d9d9 100%);
	background: -o-linear-gradient(top, #f9f9f9 5%, #d9d9d9 100%);
	background: -ms-linear-gradient(top, #f9f9f9 5%, #d9d9d9 100%);
	background: linear-gradient(to bottom, #f9f9f9 5%, #d9d9d9 100%);
	filter: progid: DXImageTransform.Microsoft.gradient(startColorstr='#f9f9f9',
		endColorstr='#d9d9d9', GradientType=0);
	background-color: #f9f9f9;
	border: 2px solid #dcdcdc;
	display: inline-block;
	cursor: pointer;
	color: #666666;
	font-size: 16px;
	padding: 10px 50px;
	border-radius: 5px;
	text-decoration: none;
	text-shadow: 0px 1px 0px #ffffff;
}

.press:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #d9d9d9
		), color-stop(1, #f9f9f9));
	background: -moz-linear-gradient(top, #d9d9d9 5%, #f9f9f9 100%);
	background: -webkit-linear-gradient(top, #d9d9d9 5%, #f9f9f9 100%);
	background: -o-linear-gradient(top, #d9d9d9 5%, #f9f9f9 100%);
	background: -ms-linear-gradient(top, #d9d9d9 5%, #f9f9f9 100%);
	background: linear-gradient(to bottom, #d9d9d9 5%, #f9f9f9 100%);
	filter: progid: DXImageTransform.Microsoft.gradient(startColorstr='#d9d9d9',
		endColorstr='#f9f9f9', GradientType=0);
	background-color: #d9d9d9;
}

.press:active {
	position: relative;
	top: 1px;
}
/*image字*/
input[type=file] {
	color: #000000;
	font-size: 12px;
	background: #f5f5f5;
	padding: 9px 9px 9px 9px;
	border: solid 2px #c9c9c9;
	text-decoration: none;
	display: inline-block;
	font-family: inherit;
	font-size: inherit;
	line-height: inherit;
	width: 300px;
	margin-left: -4px;
}

.red.press {
	border: 2px solid #8a2a21;
	border-top: 2px solid #d02718;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f24437
		), color-stop(1, #c62d1f));
	background: -moz-linear-gradient(top, #f24437 5%, #c62d1f 100%);
	background: -webkit-linear-gradient(top, #f24437 5%, #c62d1f 100%);
	background: -o-linear-gradient(top, #f24437 5%, #c62d1f 100%);
	background: -ms-linear-gradient(top, #f24437 5%, #c62d1f 100%);
	background: linear-gradient(to bottom, #f24437 5%, #c62d1f 100%);
	filter: progid: DXImageTransform.Microsoft.gradient(startColorstr='#f24437',
		endColorstr='#c62d1f', GradientType=0);
	background-color: #f24437;
	color: white;
	text-shadow: 0px 1px 0px #810e05;
}

.red.press:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #c62d1f
		), color-stop(1, #f24437));
	background: -moz-linear-gradient(top, #c62d1f 5%, #f24437 100%);
	background: -webkit-linear-gradient(top, #c62d1f 5%, #f24437 100%);
	background: -o-linear-gradient(top, #c62d1f 5%, #f24437 100%);
	background: -ms-linear-gradient(top, #c62d1f 5%, #f24437 100%);
	background: linear-gradient(to bottom, #c62d1f 5%, #f24437 100%);
	filter: progid: DXImageTransform.Microsoft.gradient(startColorstr='#c62d1f',
		endColorstr='#f24437', GradientType=0);
	background-color: #c62d1f;
}
/** TEXT AREA, INPUTTING TEXT **/
input[type="text"], .textareastyle {
	resize: none;
	display: inline-block;
	margin: 0;
	width: 300px;
	font-family: inherit;
	font-size: 14px;
	padding: 10px;
	border: solid 2px #c9c9c9;
	transition: border 0.3s;
	appearance: none;
	box-shadow: none;
	border-radius: none;
}

input[type="text"]:focus, .textareastyle:focus {
	outline: none;
	border: solid 2px #969696;
}
/*select*/
select#soflow, select#soflow-color {
	-webkit-appearance: button;
	-webkit-box-shadow: 0px 1px 0px rgba(0, 0, 0, 0.1);
	-webkit-padding-end: 20px;
	-webkit-padding-start: 2px;
	-webkit-user-select: none;
	/* background-image: url(http://i62.tinypic.com/15xvbd5.png), -webkit-linear-gradient(#FAFAFA, #F4F4F4 40%, #E5E5E5); */
	background-position: 98% center;
	background-repeat: no-repeat;
	border: solid 2px #c9c9c9;
	color: #555;
	font-size: 14px;
	font-family: inherit;
	margin: 0px;
	overflow: hidden;
	padding: 2px 10px;
	text-overflow: ellipsis;
	white-space: nowrap;
	width: 300px;
	height: 40px;
}

select#soflow-color {
	color: #fff;
	background-image: url(http://i62.tinypic.com/15xvbd5.png),
		-webkit-linear-gradient(#779126, #779126 40%, #779126);
	background-color: #779126;
	-webkit-border-radius: 20px;
	-moz-border-radius: 20px;
	border-radius: 20px;
	padding-left: 15px;
}
/** main containers and divs **/
.gamecontainer {
	width: 550px;
}

.gamecontainer label {
	display: inline-block;
	width: 7em;
	text-align: left;
	padding-right: 0.5em;
	color: white;
}

.gamescontainer {
	background: #888;
	border: 1px solid #2e91aa;
	border-radius: 2px;
	display: inline-block;
}

.my-icon {
	vertical-align: middle;
}

#songlaho {
	margin-left:-201px;
}
</style>

</head>
<body bgcolor='white'>
	<div id="wrapper">

		<!-- 		這邊是你原本放sidebar的位置 include你的sidebar進來           -->
		<%@ include file="/back-end/back-index-sidebar.jsp"%>

		<div id="page-content-wrapper">

			<div align="center">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/souvenir_product/SouvenirProductServlet"
					name="form1" enctype="multipart/form-data">
					<table>

						<div class="gamescontainer">
							<input type="hidden" name="vorp" value="p"> <input
								type="hidden" name="thisblid" value="0">
							<div class="gamecontainer">


								<p>
									<label for="namelabel">特產名稱:</label><input type="text"
										name="sou_name" size="45"
										value="<%=(soupVO == null) ? "草莓" : soupVO.getSou_name()%>">
								</p>
								<p>


									<jsp:useBean id="soutSvc" scope="page"
										class="com.souvenir_type.model.SouvenirTypeService" />
									<label for="typelabel">特產種類:</label> <SELECT size="1"
										name=sou_type_id id="soflow">
										<c:forEach var="soutVO" items="${soutSvc.all}">
											<option value="${soutVO.sou_type_id}"
												${(soupVO.sou_id==soutVO.sou_type_id)? 'selected':'' }>${soutVO.sou_type_name}
										</c:forEach>
									</SELECT>
								</p>
								<p>
									<label>特產價格:</label><input type="TEXT" type="TEXT"
										name="sou_price" size="45"
										value="<%=(soupVO == null) ? "3000" : soupVO.getSou_price()%>">
								</p>
								<p>
									<label>特產上架日期:</label> <input type="text" id="f_date1"
										name="sou_on_date" size="45"
										value="<%=(soupVO == null) ? "2020-12-5 6:30:00" : soupVO.getSou_on_date()%>">
								</p>
								<p>
									<label>特產下架日期:</label> <input type="text" id="f_date2"
										name="sou_off_date" size="45"
										value="<%=(soupVO == null) ? "2020-12-5 12:30:00 " : soupVO.getSou_off_date()%>">
								</p>
<!-- 								<p> -->
<!-- 									<label for="designerlabel">特產按讚次數:</label><input -->
<!-- 										name="sou_like_count" type="TEXT" -->
<%-- 										value="<%=(soupVO == null) ? "3" : soupVO.getSou_like_count()%>"> --%>
<!-- 								</p> -->
<!-- 按讚次數hidden -->
<input type="hidden" name="sou_like_count"
			 value="<%= (soupVO==null)? "0" : soupVO.getSou_like_count()%>" />
			 
								<p>
									<label for="longdescriptionlabel" style="vertical-align: top;">特產描述</label>
									<TEXTAREA class="textareastyle" cols=60 rows=10 name="sou_des"><%=(soupVO == null) ? "好吃啦" : soupVO.getSou_des()%>
							
        				</TEXTAREA>
								</p>
								
								<p>
									<label id="songlaho">特產狀態:</label>
									
					<input value="<%=(soupVO == null) ? "0" : soupVO.getSou_status()%>" type="radio" name="sou_status">下架
					<input value="<%=(soupVO == null) ? "1" : soupVO.getSou_status()%>" type="radio" name="sou_status">上架
									
									
								</p>
								
									<jsp:useBean id="souphSvc" scope="page"
									class="com.souvenir_photo.model.SouvenirPhotoService" />
							<p>
									<label for="filelabel">特產照片:</label> 
									<INPUT type="file" id="file" name="sou_photo" accept="image/*" size="45"
										 onchange="loadImageFile(event)" />

								</p>
								<img style="width:0px" id="preview" src="#">
								

						</div>

					</table>
					<br> <input type="hidden" name="action" value="insert">
					<input class="press" type="submit" value="送出新增">
				</FORM>

<!-- sou_id?? -->
			</div>
		</div>
	</div>
</body>




<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Timestamp sou_on_date = null;
	try {
		sou_on_date = soupVO.getSou_on_date();
	} catch (Exception e) {
		sou_on_date = new java.sql.Timestamp(System.currentTimeMillis());
	}
%>

<%
	java.sql.Timestamp sou_off_date = null;
	try {
		sou_off_date = soupVO.getSou_off_date();
	} catch (Exception e) {
		sou_off_date = new java.sql.Timestamp(System.currentTimeMillis());
	}
%>


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 5,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=sou_on_date%>', // value:   new Date(),
		   
		   

           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 5,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=sou_off_date%>', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	// 1.以下為某一天之前的日期無法選擇
	var somedate1 = new Date('2017-06-15 21:56:56');
	$('#f_date1')
			.datetimepicker(
					{
						beforeShowDay : function(date) {
							if (date.getYear() < somedate1.getYear()
									|| (date.getYear() == somedate1.getYear() && date
											.getMonth() < somedate1.getMonth())
									|| (date.getYear() == somedate1.getYear()
											&& date.getMonth() == somedate1
													.getMonth() && date
											.getDate() < somedate1.getDate())) {
								return [ false, "" ]
							}
							return [ true, "" ];
						}
					});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>



<!-- 前端預覽圖片 -->
<script>
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#preview').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

$("#file").change(
		function() {
			readURL(this);
			$("#preview").css("width", "200px").css("height", "150px").css(
					"margin-bottom", "20px");
		});
</script>
</html>