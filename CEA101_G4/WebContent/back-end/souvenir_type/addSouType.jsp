<%@page import="com.souvenir_type.model.SouvenirTypeVO"%>
<%@page import="com.souvenir_product.model.SouvenirProductVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.souvenir_product.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%
	SouvenirTypeVO soutVO = (SouvenirTypeVO) request.getAttribute("soutVO");
%>
<%-- <%= soupVO==null %>--${soupVO.deptno}-- --%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>特產品項資料新增 - addSouType.jsp</title>

<style>


/*---------------------------------------------------------*/

body{
background-color:#E5E5E5;
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
</style>

</head>
<body bgcolor='white'>
<div id="wrapper">
		<%@ include file="/back-end/back-index-sidebar.jsp"%>
		
		<div id="page-content-wrapper">
	<div align="center">
<!-- 					<h4> -->
<!-- 						<a href="/CEA101G4/back-end/souvenir/select_page.jsp">回首頁</a> -->
<!-- 					</h4> -->
		
<!-- 		<h3>新增特產</h3> -->

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<br>

		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/souvenir_type/SouvenirTypeServlet"
			name="form1">
			<table>

				<div class="gamescontainer">
					<input type="hidden" name="vorp" value="p"> <input
						type="hidden" name="thisblid" value="0">
					<div class="gamecontainer">


						<p>
							<label for="namelabel">特產種類名稱:</label><input type="text"
								name="sou_type_name" size="45"
								value="<%=(soutVO == null) ? "雜貨" : soutVO.getSou_type_name()%>">
						</p>
					
						<div id="preview"></div>
					</div>
				</div>

			</table>
			<br> <input type="hidden" name="action" value="insert">
			<input class="press" type="submit" value="送出新增">
		</FORM>


	</div>
	</div>
	</div>
	
</body>

</html>