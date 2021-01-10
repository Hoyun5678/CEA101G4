<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.souvenir_product.model.*"%>





<%
	SouvenirProductService souSvc = new SouvenirProductService();
	List<SouvenirProductVO> list = souSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="souphSvc" scope="page"
	class="com.souvenir_photo.model.SouvenirPhotoService" />


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>backend-index</title>
<link href="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js">
</head>
<style>
/* 改 */
.col {
	/* 	margin-left: 30px; */
	border: 1px solid #333;
	margin-top: 2px;
	text-align: center;
}

.row {
	margin-right: 0px;
	/* border: 1px solid red; */
}

figcaption {
	text-align: center;
}

.productname {
	height: 50px;
	/* border: 1px solid blue; */
}

.image {
	height: 200px;
	text-align: center;
}


/* 改 */
#photowall {
	width: 1140px;
	margin-left: 50%;
	transform: translateX(-50%);
}

.snip1268 {
	font-family: 'Raleway', Arial, sans-serif;
	position: relative;
	overflow: hidden;
	margin: 10px;
	min-width: 220px;
	max-width: 220px;
	width: 100%;
	color: #333333;
	text-align: center;
	background-color: #ffffff;
	line-height: 1.6em;
}

.snip1268 * {
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
	-webkit-transition: all 0.6s ease;
	transition: all 0.6s ease;
}

.snip1268 .image {
	position: relative;
}

.snip1268 img {
	max-width: 100%;
	vertical-align: top;
	-webkit-transition: opacity 0.35s;
	transition: opacity 0.35s;
}

.snip1268 .icons, .snip1268 .add-to-cart {
	position: absolute;
	left: 20px;
	right: 20px;
	opacity: 0;
}

.snip1268 .icons {
	-webkit-transform: translateY(-100%);
	transform: translateY(-100%);
	top: 20px;
	display: flex;
	justify-content: space-between;
}

.snip1268 .icons a {
	width: 32.5%;
	background: #ffffff;
}

.snip1268 .icons a:hover {
	background: #000000;
}

.snip1268 .icons a:hover i {
	color: #ffffff;
	opacity: 1;
}

.snip1268 .icons i {
	line-height: 46px;
	font-size: 20px;
	color: #000000;
	text-align: center;
	opacity: 0.7;
	margin: 0;
}

.snip1268 .add-to-cart {
	position: absolute;
	bottom: 20px;
	-webkit-transform: translateY(100%);
	transform: translateY(100%);
	font-size: 0.8em;
	color: #000000;
	line-height: 46px;
	letter-spacing: 1.5px;
	background-color: #ffffff;
	font-weight: 700;
	text-decoration: none;
	text-transform: uppercase;
}

.snip1268 .add-to-cart:hover {
	background: #000000;
	color: #ffffff;
}

.snip1268 figcaption {
	padding: 20px 20px 30px;
}

.snip1268 h2, .snip1268 p {
	margin: 0;
	text-align: left;
}

.snip1268 h2 {
	margin-bottom: 10px;
	font-weight: 700;
		text-align: center;
}

.snip1268 p {
	margin-bottom: 15px;
	font-size: 0.85em;
	font-weight: 500;
}

.snip1268 .price {
	font-size: 1.3em;
	opacity: 0.5;
	font-weight: 700;
	text-align: right;
}

.snip1268:hover img, .snip1268.hover img {
	opacity: 0.8;
}

.snip1268:hover .icons, .snip1268.hover .icons, .snip1268:hover .add-to-cart,
	.snip1268.hover .add-to-cart {
	-webkit-transform: translateY(0);
	transform: translateY(0);
	opacity: 1;
}

/* Icon set - http://ionicons.com */
@import url(https://fonts.googleapis.com/css?family=Raleway:400,500,700)
	;

@import
	url(https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css)
	;
</style>
<!-- icon圖示  https://ionicons.com/ -->
<script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>

<body>

	<%@ include file="/front-mem-end/bar.jsp"%>
	<!-- 這邊是你原本sidebar的位置 -->


	<div id="photowall" class="row">
		<c:forEach var="soupVO" items="${list}">
			<div class="col">
				<figure class="snip1268">
					<div class="image">


						<c:forEach var="souphVO" items="${souphSvc.all}">
							<c:if test="${soupVO.sou_id==souphVO.sou_id}">
								<img
									src="${pageContext.request.contextPath}/souvenir_photo/SouvenirPhotoServlet?sou_photo_id=${souphVO.sou_photo_id}&action=getSouPhoto">
							</c:if>
						</c:forEach>
						<div class="icons">
							<a href="#"> <i><ion-icon name="heart"></ion-icon></i>
							</a> <a href="#"> <i><ion-icon name="sad-outline"></ion-icon></i>
							</a> <a
								href="<%=request.getContextPath()%>/souvenir_product/SouvenirProductServlet?sou_id=${soupVO.sou_id}&action=checkSouvenirDetail">
								<i><ion-icon name="reader-outline"></ion-icon></i>
							</a>
<!-- 							改 -->






							<!-- 						 </i> -->
							<!-- 						 </FORM> -->
						</div>
						</form>
<!-- 						改 -->
						<a
							href="<%=request.getContextPath()%>/shopping/shopping.do?sou_name=${soupVO.sou_name}&sou_price=${soupVO.sou_price}&sou_id=${soupVO.sou_id}&quantity=1&action=ADD"
							class="add-to-cart">Add to Cart</a>
					</div>


					<figcaption>
						<h2 class="productname">${soupVO.sou_name}</h2>

						<div class="price">$${soupVO.sou_price}元</div>
						<!-- 						<FORM METHOD="post" -->
						<%-- 								ACTION="<%=request.getContextPath()%>/souvenir_product/SouvenirProductServlet" --%>
						<!-- 								style="margin-bottom: 0px;"> -->
						<!-- 								<input class="input-group-text" type="submit" value="詳情"> -->
						<%-- 								<input type="hidden" name="sou_id" value="${soupVO.sou_id}"> --%>
						<!-- 								<input type="hidden" name="action" value="checkSouvenirDetail"> -->
						<!-- 							</FORM> -->

					</figcaption>
					<%-- 				<Form name="soupVO" action="<%=request.getContextPath()%>/shopping/shopping.do" method="POST"> --%>
					<!-- 					<div align="center" style="display:inline;">數量： <input type="text" name="quantity" size="3" value=1></div> -->
					<!-- 					<input type="submit" value="加入購物車"> -->
					<%-- 					<input type="hidden" name="sou_name" value="${soupVO.sou_name}"> --%>
					<%-- 					<input type="hidden" name="sou_price" value="${soupVO.sou_price}"> --%>
					<%--              		<input type="hidden" name="sou_id" value="${soupVO.sou_id}"> --%>
					<!--       				<input type="hidden" name="action" value="ADD">  -->
					<!--       				</Form>  -->
				</figure>
			</div>
		</c:forEach>
	</div>


	<script>
		/* Demo purposes only */
		$(".hover").mouseleave(function() {
			$(this).removeClass("hover");
		});
	</script>

</body>
</html>