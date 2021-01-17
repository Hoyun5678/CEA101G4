<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.activity_product.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sell.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	// 	SellVO sellVO = (SellVO) session.getAttribute("sellVO");
	// 	String sell_mem_id=sellVO.getSellMemId();
	// 	ActivityProductService actproSvc=new ActivityProductService();
	// 	List<ActivityProductVO>list = 
	// 			actproSvc.getAllbySellMemId(sell_mem_id);
	// 	pageContext.setAttribute("list",list );
%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/buttons/1.2.2/css/buttons.bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front-sell-end/front-sell-sideBar.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.min.css" />
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

<title>活動管理</title>

<style type="text/css">
.sidenav {
	grid-area: sidenav;
	background-color: #394263;
	top: 0;
	left: 0;
	background-color: #111;
	opacity: .9;
}

.grid-container {
	display: flex;
}

.main {
	grid-area: main;
	padding: 30px;
}

.footer {
	grid-area: footer;
	background-color: #648ca6;
}

.sidebar-menu {
	
}

.treeview {
	border-bottom: 1px solid;
	padding: 5px;
	text-align: left;
	text-decoration: none;
}

.sidebar-menu a {
	display: block;
	padding: 20px 30px;
	font-size: 14px;
	text-decoration: none;
	color: #ccc;
}

.treeview:hover {
	color: #fff;
	transition: 0.4s;
}

.slide a {
	color: #000;
	font-size: 36px;
}

#content {
	overflow: hidden;
}

example #contetn {
	align-items: center;
	text-align: center;
}

.btn_group {
	width: 120px;
	align-items: center;
	display: flex;
	line-height: 100px;
}

.act_content {
	max-width: 500px;
	max-height: 68px;
	text-overflow: ellipsis;
	display: block;
	overflow: hidden;
	border: 0;
	white-space: nowrap;
}

.table {
	font-size: 5px;
}
.list-photo{
height:50px;
width:50px;}
</style>

</head>
<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
<body>

	<div id="viewport">
		<%@ include file="/front-sell-end/sellMemSideBar.jsp"%>
		<div id="content">
			<%@ include file="/front-sell-end/sellNavBar.jsp"%>
			<div class="container-fluid">
				<div class="container">
					<div class="row">
						<div class="col calendarCol">
							<main class="main">
								<table id="example"
									class="table table-striped table-bordered table-hover"
									cellspacing="0" width="100%" style="text-align: center;">
									<thead>
										<tr class="warning"
											style="line-height: 25px; text-align: center;">
											<th>圖片預覽</th>
											<th>活動種類</th>
											<th>活動商品名稱</th>
											<th>活動商品價格</th>
											<th>活動敘述</th>
											<th>活動地點</th>
											<th style="text-align: center; width: 100px;"><button
													type="button" data-func="dt-add" style="width: 90px;"
													class="btn btn-success btn-xs dt-add"
													onclick="location.href='<%=request.getContextPath()%>/front-sell-end/activity_product/addActivityProduct.jsp'">
													新增活動</button></th>
										</tr>
									</thead>
									<tbody>
										<jsp:useBean id="actproSvc" scope="page"
											class="com.activity_product.model.ActivityProductService" />
										<jsp:useBean id="acttypeSvc" scope="page"
											class="com.activity_type.model.ActivityTypeService" />
										<c:forEach var="actproVO"
											items="${actproSvc.getAllbySellMemId(sellVO.sellMemId)}">
											<tr style="line-height: 25px; text-align: center;">
											<td><img class="list-photo"src="<%=request.getContextPath()%>
											/ActivityPhoto/ActivityPhoto.do?act_id=
											${actproVO.act_id}&action=getListActPhoByActId"></td>
												<td>${acttypeSvc.getOneActType(actproVO.act_type_id).act_type_name}</td>
												<td>${actproVO.act_name}</td>
												<td>${actproVO.act_price}</td>
												<td class="act_content" style="max-width: 100px;">${actproVO.act_des}</td>
												<td>${actproVO.act_add}</td>
												<td class="btn_group" style="width: 100%;justify-content: center;">
													<form METHOD="post"
														ACTION="<%=request.getContextPath()%>/ActivityProduct/ActivityProduct.do">
														<button type="submit"
															class="btn btn-light btn-xs dt-edit"
															style="margin-right: 16px;">修改</button>
														<input type="hidden" name="act_id"
															value="${actproVO.act_id}"> <input type="hidden"
															name="action" value="getOne_For_Update">
													</form>
													
													
													<form METHOD="post"
														ACTION="<%=request.getContextPath()%>/ActivityPhoto/ActivityPhoto.do">
														<button type="submit"
															class="btn btn-light btn-xs dt-edit"
															style="margin-right: 16px;">圖片管理</button>
														<input type="hidden" name="act_id"
															value="${actproVO.act_id}"> <input type="hidden"
															name="action" value="get_Photo_By_Act_id">
													</form>
													
													
													<form METHOD="post"
														ACTION="<%=request.getContextPath()%>/ActivityPeriod/ActivityPeriod.do">
														<button type="submit"
														class="btn btn-light btn-xs dt-delete">設定週期</button>
														<input type="hidden" name="act_id"
															value="${actproVO.act_id}"> <input type="hidden"
															name="action" value="getOne_For_Insert">
													</form>
													
													
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</main>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>


</html>