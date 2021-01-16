<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Sou: Home</title>

<style>
#location {
	margin-left: -66px;
	margin-top: -35px;
	/*             border: 1px solid red; */
	max-width: 100%;
}

.badge {
	font-size: 16% !important;
}

.col-3 {
/*  border: 1px solid blue; */
	width: 100px;
	height: 85px;
	margin-top: 40px;
	
	/* text-align: center */
}

.col {
	/* border: 1px solid black; */
	
}

buttom {
	width: 100px;
}

.input-group {
	width: 313px;
}

.form-control-sm, .input-group-sm>.form-control, .input-group-sm>.input-group-append>.btn,
	.input-group-sm>.input-group-append>.input-group-text, .input-group-sm>.input-group-prepend>.btn,
	.input-group-sm>.input-group-prepend>.input-group-text {
	width: 300px;
}

.form-control {
	float: left;
	height: 20px;
	height: 39px;
	width:300px;
}


#error {
    position: absolute; 
    top: 0px; 
    right: 42%; 

}
#listmanager{
	text-align:center;
}

#exampleFormControlSelect1{
	width:405.5px;
}
</style>

</head>
<body bgcolor='white'>

	<div id="wrapper">
		<%@ include file="/back-end/back-index-sidebar.jsp"%>
		
		<div id="page-content-wrapper">
		<br>
			<div>
				<p>
				<h3 id="location" class="container">
					特產頁面 <span class="badge badge-dark">Current Location </span>
					</h1>
					</p>
			</div>
			<div id="listmanager" class="row">
				<div class="col-3">
					<p>
						<b>一般特產清單</b>
					</p>
					<p>
						<a href="<%=request.getContextPath()%>/back-end/souvenir/listAllSou.jsp"><button
								class="btn btn-outline-secondary">查看</button></a>
					</p>
				</div>
				
				<div class="col-3">
					<p>
						<b>一般特產新增</b>
					</p>
					<p>
						<a href="<%=request.getContextPath()%>/back-end/souvenir/addSou.jsp"><button
								class="btn btn-outline-secondary">增加</button></a>
					</p>
				</div>
				<div class="col-3">
					<p>
						<b>特產種類清單</b>
					</p>
					<p>
						<a href="<%=request.getContextPath()%>/back-end/souvenir_type/listAllSouType.jsp"><button
								class="btn btn-outline-secondary">查看</button></a>
					</p>
				</div>
				<div class="col-3">
					<p>
						<b>特產種類新增</b>
					</p>
					<p>
						<a href="<%=request.getContextPath()%>/back-end/souvenir_type/addSouType.jsp"><button
								class="btn btn-outline-secondary">增加</button></a>
					</p>
				</div>
			</div>
			<br>
			<jsp:useBean id="souSvc" scope="page"
				class="com.souvenir_product.model.SouvenirProductService" />
			<jsp:useBean id="sodSvc" scope="page"
				class="com.souvenir_discount.model.SouvenirDiscountService" />
			<div class="row">
				<div class="col">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/souvenir_product/SouvenirProductServlet">
						<b>輸入特產編號:</b>
						<div class="input-group mb-3">
							<input class="form-control" type="text" name="sou_id"> <input
								type="hidden" name="action" value="getOne_For_Display">
							<input class="btn btn-outline-secondary" type="submit" value="送出">
						</div>
					</FORM>
				</div>
				<div class="col">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/souvenir_discount/SouvenirDiscountServlet">
						<b>輸入特產促銷編號:</b>
						<div class="input-group mb-3">
							<input class="form-control" type="text" name="sou_dis_id">
							<input type="hidden" name="action" value="getOne_For_Display">
							<input class="btn btn-outline-secondary" type="submit" value="送出">
						</div>
					</FORM>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<b>選擇特產編號:</b>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/souvenir_product/SouvenirProductServlet">
						<select class="form-control form-control-sm"
							id="exampleFormControlSelect1" size="1" name="sou_id">
							<c:forEach var="soupVO" items="${souSvc.all}">
								<option value="${soupVO.sou_id}">${soupVO.sou_id}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_For_Display">
						<input class="btn btn-outline-secondary" type="submit" value="送出">
					</FORM>
				</div>
				<div class="col">
					<b>選擇特產促銷編號:</b>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/souvenir_discount/SouvenirDiscountServlet">
						<select class="form-control form-control-sm"
							id="exampleFormControlSelect1" size="1" name="sou_dis_id">
							<c:forEach var="sodVO" items="${sodSvc.all}">
								<option value="${sodVO.sou_dis_id}">${sodVO.sou_dis_id}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_For_Display">
						<input class="btn btn-outline-secondary" type="submit" value="送出">
					</FORM>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<b>選擇特產商品:</b>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/souvenir_product/SouvenirProductServlet">
						<select class="form-control form-control-sm"
							id="exampleFormControlSelect1" size="1" name="sou_id">
							<c:forEach var="soupVO" items="${souSvc.all}">
								<option value="${soupVO.sou_id}">${soupVO.sou_name}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_For_Display">
						<input class="btn btn-outline-secondary" type="submit" value="送出">
					</FORM>
				</div>
				<div class="col">
					<b>選擇特產促銷商品:</b>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/souvenir_discount/SouvenirDiscountServlet">
						<select class="form-control form-control-sm"
							id="exampleFormControlSelect1" size="1" name="sou_dis_id">
							<c:forEach var="sodVO" items="${sodSvc.all}">
								<option value="${sodVO.sou_dis_id}">${sodVO.sou_dis_name}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_For_Display">
						<input class="btn btn-outline-secondary" type="submit" value="送出">
					</FORM>
				</div> 
			</div>
			<%-- 錯誤表列 --%>
			<div id="error">
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		</div>
		</div>
	</div>
	
</body>
<style>

</style>
</html>