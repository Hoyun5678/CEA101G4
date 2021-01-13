<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.souvenir_photo.model.*"%>
<%@ page import="com.souvenir_product.model.*"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%
	SouvenirPhotoVO souphVO = (SouvenirPhotoVO) request.getAttribute("souphVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	SouvenirProductVO soupVO = (SouvenirProductVO) request.getAttribute("soupVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>特產照片修改 - update_souphoto_input.jsp</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
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
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}

img {
	width:30%;
}
</style>

</head>
<body bgcolor='white'>

	<div id="wrapper">
		<%@ include file="/back-end/back-index-sidebar.jsp"%>
		<div id="page-content-wrapper">

			<table id="table-1">
				<tr>
					<td>
						<h3>特產資料修改 - update_sou_input.jsp</h3>
						<h4>
							<a
								href="/CEA101G4/back-end/souvenir_photo/select_souphoto_page.jsp">回首頁</a>
						</h4>
					</td>
				</tr>
			</table>

			<h3>資料修改:</h3>

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
				ACTION="<%=request.getContextPath()%>/souvenir_photo/SouvenirPhotoServlet"
				name="form1" enctype="multipart/form-data">
				<table>
					<tr>
						<td>特產照片編號:<font color=red><b>*</b></font></td>
						<td><%=souphVO.getSou_photo_id()%></td>
					</tr>

					<jsp:useBean id="souphSvc" scope="page"
						class="com.souvenir_photo.model.SouvenirPhotoService" />
					<tr>
						<td>特產編號:<font color=red><b>*</b></font></td>
						<td><SELECT size="1" name=sou_id id="soflow">
								<c:forEach var="souphVO" items="${souphSvc.all}">
									<option value="${souphVO.sou_id}"
										${(souphVO.sou_id==souphVO.sou_id)? 'selected':'' }>${souphVO.sou_id}
								</c:forEach>
						</select></td>
					</tr>

					<tr>
					
					<td>特產照片:<font color=red><b>*</b></font></td>
						<td>
						<input type="file" name="sou_photo" accept="image/*" size="45" 
						value="${souphVO.sou_photo}" onchange="preview()" /> 
						<img id="frame" src="${pageContext.request.contextPath}/souvenir_photo/SouvenirPhotoServlet?sou_photo_id=${souphVO.sou_photo_id}&action=getSouPhoto"></td>

					</tr>
					

				</table>
				<br> <input type="hidden" name="action" value="update">
				<input type="hidden" name="sou_photo_id" value="<%=souphVO.getSou_photo_id()%>">
				<input type="submit" value="送出修改">
			</FORM>
			


		</div>
	</div>
</body>
<script>
// function readURL(input) {
// 	if (input.files && input.files[0]) {
// 		var reader = new FileReader();
// 		reader.onload = function(e) {
// 			$('#preview').attr('src', e.target.result);
// 		}
// 		reader.readAsDataURL(input.files[0]);
// 	}
// }

// $("#file").change(
// 		function() {
// 			readURL(this);
// 			$("#preview").css("width", "200px").css("height", "150px").css(
// 					"margin-bottom", "20px");
// 		});

function preview() {
    frame.src=URL.createObjectURL(event.target.files[0]);
}
</script>


</html>