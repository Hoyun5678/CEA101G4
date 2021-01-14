<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.souvenir_photo.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	SouvenirPhotoService souphSvc = new SouvenirPhotoService();
	List<SouvenirPhotoVO> list = souphSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->

 <link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/back-index-sidebar.css">
<title>所有特產照片 - listAllSouphoto.jsp</title>

<style>

body#listAllSou {
	background-color: #E5E5E5;
	text-align: center;

}

table#table-1 {
	background-color: #888;
	text-align: center;
	border: solid black 2px;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>


#table-1 {
	margin: auto;
	text-align: center;
	font-size: 14px;
}

#table-1 td ,#table-1 th {
	vertical-align: middle;
		padding: 5px;
	text-align: center;
}

a.page2a {
	font-size: 15px;
	color: black;
	text-decoration: none;
}

a.page2a:hover , a#local:hover {
	text-decoration: none;
color: white;
}

a#local {
	font-size: 30px;
		font-size: 15px;
	color: black;
	text-decoration: none;
}

input.input-group-text:hover {
	opacity: 0.5;
}

font {
	color: green;
}

#pageselect {
	display: flex;
	flex-direction: row;
	justify-content: center;
}


img {
	width:150px;
	height: 100px;
}
</style>

</head>
<body id="listAllSou">
	
<!-- 	<div class="container-fluid" id="titleAndError"> -->

<!-- 		<table id="table-1"> -->
<!-- 			<tr> -->
<!-- 				<td > -->
<!-- 					<h3>所有特產資料 - listAllSou.jsp</h3> -->
<!-- 					<h4> -->
<!-- 						<a id="local" href="/CEA101G4/back-end/souvenir/select_page.jsp">回首頁</a> -->
<!-- 					</h4> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 	</div> -->

<div id="wrapper">
        <%@ include file="/back-end/back-index-sidebar.jsp"%>
          <div id="page-content-wrapper">

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
 	
      
                <%@ include file="page1.file"%>
        

      
      


                    <table class="table table-dark table-striped" id="table-1">
        <thead class=".thead-dark">
            <tr>
                <th>特產照片編號</th>
                <th>特產編號</th>
                <th>特產照片</th>
                <th></th>
                <th></th>
            </tr>
        </thead>

        <c:forEach var="souphVO" items="${list}" begin="<%=pageIndex%>"
            end="<%=pageIndex+rowsPerPage-1%>">

            <tr>
                <td>${souphVO.sou_photo_id}</td>
                <td>${souphVO.sou_id}</td>
		<td><img src="${pageContext.request.contextPath}/souvenir_photo/SouvenirPhotoServlet?sou_photo_id=${souphVO.sou_photo_id}&action=getSouPhoto"></td>					
               




                <td>
                    <FORM METHOD="post"
                        ACTION="<%=request.getContextPath()%>/souvenir_photo/SouvenirPhotoServlet"
                        style="margin-bottom: 0px;">
                        <input class="input-group-text" type="submit" value="修改">
                        <input type="hidden" name="sou_photo_id" value="${souphVO.sou_photo_id}">
                        <input type="hidden" name="action" value="getOne_For_Update">
                    </FORM>
                </td>
                <td>
                    <FORM METHOD="post"
                        ACTION="<%=request.getContextPath()%>/souvenir_photo/SouvenirPhotoServlet"
                        style="margin-bottom: 0px;">
                        <input class="input-group-text" type="submit" value="刪除">
                        <input type="hidden" name="sou_photo_id" value="${souphVO.sou_photo_id}">
                        <input type="hidden" name="action" value="delete">
                    </FORM>
                </td>
            </tr>
        </c:forEach>
    </table>

 		<%@ include file="page2.file"%>
        </div>
    </div>



	
	
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
		
</body>
</html>