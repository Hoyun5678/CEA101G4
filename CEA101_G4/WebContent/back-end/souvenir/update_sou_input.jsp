<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.souvenir_product.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%
	SouvenirProductVO soupVO = (SouvenirProductVO) request.getAttribute("soupVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%-- <%=soupVO == null%>--${soupVO.sou_id}-- --%>
<html>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<title>房間資料修改</title>
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
						<h2>房間資料修改</h2>
						<hr>
					</div>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/souvenir_product/SouvenirProductServlet"
						name="form1">
						<div class="form-group">
							<div class="col-4 d-inline-block">
								<label class="d-flex justify-content-end">特產名稱:</label>
							</div>
							<div class="col-5 d-inline-block">
								<input type="text" class="form-control" name="sou_name"
									value="<%=soupVO.getSou_name()%>" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-4 d-inline-block">
								<label class="d-flex justify-content-end">特產種類:</label>
							</div>

							<jsp:useBean id="soutSvc" scope="page"
								class="com.souvenir_type.model.SouvenirTypeService" />
							<div class="col-5 d-inline-block">
								<select size="1" name=sou_type_id>
									<c:forEach var="soutVO" items="${soutSvc.all}">
										<option value="${soutVO.sou_type_id}"
											${(soupVO.sou_id==soutVO.sou_type_id)? 'selected':'' }>${soutVO.sou_type_name}
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<div class="col-4 d-inline-block">
									<label class="d-flex justify-content-end">特產價格:</label>
								</div>
								<div class="col-5 d-inline-block">
									<input type="text" class="form-control" id="roomCapacity"
										name="sou_price" value="<%=soupVO.getSou_price()%>" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-4 d-inline-block">
									<label class="d-flex justify-content-end">特產敘述:</label>
								</div>
								<div class="col-5 d-inline-block">
									<textarea class="form-control" name="sou_des"
										placeholder="寫一些民宿房間的特色介紹~"><%=(soupVO.getSou_des() == null) ? "" : soupVO.getSou_des()%></textarea>
								</div>
							</div>
							<div class="form-group">
								<div class="col-4 d-inline-block">
									<label class="d-flex justify-content-end">特產狀態:</label>
								</div>
								<div class="col-5 d-inline-block">
									<input type="radio" id="roomSta0" name="sou_status" value="0"
										<%=(soupVO.getSou_status() == 0) ? "checked" : ""%>> <label
										for="roomSta0">下架 </label> <input type="radio" id="roomSta1"
										name="sou_status" value="1"
										<%=(soupVO.getSou_status() == 1) ? "checked" : ""%>> <label
										for="roomSta1">上架</label>
									</td>
								</div>
							</div>
							<input type="hidden" name="sou_like_count" value="<%= (soupVO==null)? "0" : soupVO.getSou_like_count()%>" />
							<input type="hidden" name="sou_on_date" value="<%= (soupVO==null)? "0" : soupVO.getSou_on_date()%>" />
							<input type="hidden" name="sou_off_date" value="<%= (soupVO==null)? "0" : soupVO.getSou_off_date()%>" />
							
							<div class="form-group">
								<div class="col-10">
									<input type="hidden" name="action" value="update"> <input
										type="hidden" name="sou_id" value="<%=soupVO.getSou_id()%>">
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