<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.souvenir_product.model.*"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	SouvenirProductService souSvc = new SouvenirProductService();
	List<SouvenirProductVO> list = souSvc.getAll();
	pageContext.setAttribute("list", list);
%>





<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front-sell-end/front-sell-listAllRoom.css">

<title>所有特產資料</title>
</head>
<style>
img {
	width: 50px;
	height: 50px;
}
</style>
<body>

<div id="wrapper">
		<%@ include file="/back-end/back-index-sidebar.jsp"%>
<div id="page-content-wrapper">
	<div id="viewport">
		<div id="content">
			<div class="container-fluid" style="padding: 0;">
				<table class="table table-striped table-hover align-middle"
					id="tableRoomList">
					<thead class=".thead-dark">
						<tr>
							<th>特產照片</th>
							<!-- 						<th>特產編號</th> -->
							<th>特產名稱</th>
							<th>特產類型</th>
							<th>特產價格</th>
							<!-- 						<th>特產上架日期</th> -->
							<!-- 						<th>特產下架日期</th> -->
							<!-- 						<th>特產累積按讚次數</th> -->
							<th class="des">特產敘述</th>
							<th>特產狀態</th>
							<th>特產管理</th>
							<th>圖片管理</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="soupVO" items="${list}">
							<tr>
								<jsp:useBean id="souphSvc" scope="page"
									class="com.souvenir_photo.model.SouvenirPhotoService" />
								<jsp:useBean id="soutSvc" scope="page"
									class="com.souvenir_type.model.SouvenirTypeService" />

								<td><c:forEach var="souphVO" items="${souphSvc.all}">
										<c:if test="${soupVO.sou_id==souphVO.sou_id}">
											<img
												src="${pageContext.request.contextPath}/souvenir_photo/SouvenirPhotoServlet?sou_photo_id=${souphVO.sou_photo_id}&action=getSouPhoto">
										</c:if>
									</c:forEach></td>
								<td>${soupVO.sou_name}</td>
								<td>${soutSvc.getOneSouType(soupVO.sou_type_id).sou_type_name}</td>
								<td>$${soupVO.sou_price}</td>
								<td class="des">${soupVO.sou_des}</td>
								<td>${(soupVO.sou_status==0)? "下架中": "已上架"}</td>
								<td>
									<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/souvenir_product/SouvenirProductServlet"
								style="margin-bottom: 0px;">
								<input class="input-group-text" type="submit" value="修改">
								<input type="hidden" name="sou_id" value="${soupVO.sou_id}">
								<input type="hidden" name="action" value="getOne_For_Update">
							</FORM>
								</td>
								<td>
									  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/souvenir_photo/listOneSouPhoto.jsp" style="margin-bottom: 0px;">
							     <input type="hidden" name="sou_id"  value="${soupVO.sou_id}">
							     <button type="submit" class="btn btn-secondary edit">圖片</button>
							  </FORM>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			
				<div class="row">
					<c:if test="${not empty errorMsgs}">
						<%-- 錯誤表列 from Servlet --%>
						<div class="alert alert-danger col-lg-6 col-lg-offset-1"
							role="alert" id="titleAndError">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	<script>
    	$(document).ready(function() {
    		var contextPath = '<%=request.getContextPath()%>
		';
					$("#addRoom").click(
							function() {

								window.location.replace(contextPath
										+ "/front-sell-end/room/addRoom.jsp");
							})
				})
	</script>


</body>
</html>