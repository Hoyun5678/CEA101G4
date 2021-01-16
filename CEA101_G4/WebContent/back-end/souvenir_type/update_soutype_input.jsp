<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.souvenir_type.model.SouvenirTypeVO"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%
	SouvenirTypeVO soutVO = (SouvenirTypeVO) request.getAttribute("soutVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>特產資料修改</title>
<style>
button {
	float: right;
}

.help-block {
	color: #E60000;
	font-weight: bold;
}
</style>
</head>

<body>
<div id="wrapper">
		<%@ include file="/back-end/back-index-sidebar.jsp"%>
<div id="page-content-wrapper">
<br>
	<div id="viewport">
		<div id="content">
			<div class="container-fluid" style="padding: 0;">
				<div class="container mt-4">
					<div class="col-9 offset-1">
						<h2>特產種類修改</h2>
						<hr>
					</div>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/souvenir_type/SouvenirTypeServlet"
						name="form1">
						<div class="form-group">
							<div class="col-4 d-inline-block">
								<label class="d-flex justify-content-end">特產種類編號:</label>
							</div>
							<div class="col-5 d-inline-block">
								<input type="text" class="form-control" name="sou_type_id"
									value="<%=soutVO.getSou_type_id()%>" />
							</div>
						</div>
						<div class="form-group">
							
							<div class="form-group">
								<div class="col-4 d-inline-block">
									<label class="d-flex justify-content-end">特產種類名稱:</label>
								</div>
								<div class="col-5 d-inline-block">
									<input type="text" class="form-control" id="roomCapacity"
										name="sou_type_name" value="<%=soutVO.getSou_type_name()%>" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-10">
									<input type="hidden" name="action" value="update"> <input
			type="hidden" name="sou_type_id" value="<%=soutVO.getSou_type_id()%>">
									<button type="submit" class="btn btn-primary">送出修改</button>
								</div>
							</div>
					</form>
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
	</div>
</body>

</html>