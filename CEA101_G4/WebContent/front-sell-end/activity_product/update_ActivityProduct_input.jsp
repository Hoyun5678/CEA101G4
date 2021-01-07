<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity_product.model.*"%>

<%
	ActivityProductVO actproVO = (ActivityProductVO) request.getAttribute("actproVO");
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>修改活動 - addActivityProduct.jsp</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front-sell-end/front-sell-sideBar.css" />



</head>
<body>





	<div id="viewport">
		<%@ include file="/front-sell-end/sellMemSideBar.jsp"%>
		<div id="content">
			<%@ include file="/front-sell-end/sellNavBar.jsp"%>
			<div class="container-fluid">
				<div class="container">
					<h1>修改活動</h1>
					<c:if test="${not empty errorMsgs}">
						<div class="container">
							<div class="alert alert-danger" role="alert">
								<strong>更新失敗，請修正以下錯誤:</strong>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li>${message}
									</c:forEach>
								</ul>
							</div>
						</div>
					</c:if>
					<form class="form-horizontal" method="POST"
						action="<%=request.getContextPath()%>/ActivityProduct/ActivityProduct.do">
						<jsp:useBean id="acttypeSvc" scope="page"
							class="com.activity_type.model.ActivityTypeService" />
						<div class="form-group">
							<label class="control-label col-sm-2" for="act_type_id">活動種類:</label>
							<div class="col-sm-5">
								<select size="1" name="act_type_id">
									<c:forEach var="acttypeVO" items="${acttypeSvc.all}">
										<option value="${acttypeVO.act_type_id}"
										${(acttypeVO.act_type_id==actproVO.act_type_id)?'selected':'' }> ${acttypeVO.act_type_name}
											
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="act_name">活動商品:</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="act_name"
									name="act_name" value="${actproVO.act_name}" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="act_price">活動商品價格:</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="act_price"
									name="act_price" value="${actproVO.act_price}" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="act_des">活動敘述:</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="act_des"
									name="act_des" value="${actproVO.act_des}" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="act_add">活動地點:</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="act_add"
									name="act_add" value="${actproVO.act_add}" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-7">
								<input type="hidden" name="sell_mem_id" value="${sellVO.sellMemId}"> 
								<input type="hidden" name="act_id" value="${actproVO.act_id}"> 
								<input type="hidden" name="action" value="update">
									

								<button type="submit" class="btn btn-default">送出修改</button>
							</div>
						</div>
					</form>
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
</body>



</html>