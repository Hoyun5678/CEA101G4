<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.souvenir_photo.model.*"%>
<%@ page import="com.souvenir_product.model.*"%>


<jsp:useBean id="SouvenirProductVO" scope="request"
	class="com.souvenir_product.model.SouvenirProductVO" />
<jsp:useBean id="souSvc" scope="page"
	class="com.souvenir_product.model.SouvenirProductService" />

<jsp:useBean id="souphSvc" scope="page"
	class="com.souvenir_photo.model.SouvenirPhotoService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'>
</head>
<style>
img {
	height: 420px;
}

.row {
	justify-content: center;
	align-items: center;
}

.col-lg-8 {
	border: solid 1px black;
	margin-top: 5px;
	text-align: center;
}

.navbar-brand #logo {
	height: 15%;
}

.h3, h3 {
	text-align: left;
}

#myDropdown2 .far, .fas, .fab {
	padding-right: 10px;
}

.dropbtn2 {
	color: white !important;
	font-size: 16px !important;
	cursor: pointer !important;
}

.dropbtn2:hover, .dropbtn2:focus {
	/* 	background-color: #5c867b; */
	/* 	! */
	/* 	important; */
	
}

.dropdown2 {
	position: relative !important;
	display: inline-block !important;
	color: black;
}

/* 點取範圍擴大 */
#account2 {
	/* 	position: relative; */
	size: 10em;
	position: fixed;
	top: 100px;
	right: 0 !important;
}

#account2::before {
	content: '';
	position: absolute;
	top: -5px;
	right: -5px;
	bottom: -5px;
	left: -5px;
}

.dropdown-content2 {
	display: none !important;
	position: absolute !important;
	background-color: #f9f9f9 !important;
	min-width: 160px !important;
	overflow: auto !important;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2) !important;
}

.dropdown-content2 a {
	color: black !important;
	padding: 12px 16px !important;
	text-decoration: none !important;
	display: block !important;
}

.dropdown2 a:hover {
	background-color: #77a599 !important;
}

.show2 {
	z-index: 99;
	display: block !important;
	position: fixed !important;
	left: 50%;
	transform: translateX(-50%);
}

#account2 {
	/* 	padding: 8px 10px 8px 15px; */
	/* 	border: 1px solid #cbd8d0; */
	/* 	border-radius: 30px; */
	/* 	margin-left: 20px; */
	
}

#shoppingcar {
	color: black;
}

#title {
	margin-top: 0 !important;
}

#addcar {
	position: absolute;
	bottom: 7px;
	right: -38px;
}

.btn-success {
	margin-bottom : 5px;
}
</style>
<body align="center">
	<%@ include file="/front-mem-end/bar.jsp"%>


	<c:if test="${not empty sessionScope.memVO}">
		<div class="dropdown2">

			<div id="account2" onclick="myFunction2()" class="dropbtn2">
				<i id="shoppingcar" class="fas fa-shopping-cart fa-2x"></i>


			</div>

			<div id="myDropdown2" class="dropdown-content2">
				<jsp:include page="/front-mem-end/souvenir/souvenircart.jsp"
					flush="true" />

			</div>
		</div>
	</c:if>

	<div class="container">
		<div class="row">
			<div id="title">
				<h3>${soupVO.sou_name}</h3>
				<div class="fav_area">
					<i class="far fa-star 2x" id="rate"></i> <font face="monospace"
						;font color="#8E8787">4.79(36)</font>
					<h6>嘉義縣中埔鄉、台灣地區</h6>
					<i class="fas fa-share-alt 2x" id="share"></i> <font
						face="monospace" ;font color="#8E8787"><u>分享</u></font> <i
						class="far fa-heart 2x" id="fav"></i> <font face="monospace" ;font
						color="#8E8787"><u>按讚</u></font>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="upside">
			<div class="row">
				<div class="col-lg-9">
					<div class="photogallery">
						<div class="row">
							<div class="col-lg-8">
								<c:forEach var="souphVO"
									items="${souphSvc.getBySouId(soupVO.sou_id)}">
									<img
										src="${pageContext.request.contextPath}/souvenir_photo/SouvenirPhotoServlet?sou_photo_id=${souphVO.sou_photo_id}&action=getSouPhoto">
								</c:forEach>
							</div>
							<div class="col-lg-3" id="rightside">
								<div id="firstcol">
									<div id="ratingscore">
										<h4 id="score">9.2</h4>
									</div>
									<div id="scoremeaning">
										<div id="up">完美，太漂亮了吧</div>
										<div id="down">/10 (89則評論)</div>
									</div>
									<div class="keyword">老闆娘太正</div>
									<div class="keyword">房間舒適</div>
								</div>
								<div id="secondcol">
									<div class="distance">
										<div>${soupVO.sou_des}</div>

										<div>
											<Form id="addcar" name="soupVO"
												action="<%=request.getContextPath()%>/shopping/shopping.do"
												method="POST">
												<div align="center" style="display: inline;">
													<input type='button' value='-' class='qtyminus' field='quantity' />
													<input type="text" name="quantity" size="3" value=1>
												</div>
												
												<input type='button' value='+' class='qtyplus' field='quantity' />
												<input class="btn btn-success" type="submit" value="加入購物車">
												<input type="hidden" name="sou_name" value="${soupVO.sou_name}"> 
												<input type="hidden" name="sou_price" value="${soupVO.sou_price}">
												<input type="hidden" name="sou_id" value="${soupVO.sou_id}">
												<input type="hidden" name="action" value="ADD">
											</Form>


											<form id='myform' method='POST' action='#'>
												
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<br>
</body>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.8.1/baguetteBox.min.js">
	
</script>
<script>
	baguetteBox.run('.photogallery');
</script>
<script>
	/* Demo purposes only */
	$(".hover").mouseleave(function() {
		$(this).removeClass("hover");
	});

	function myFunction2() {
		document.getElementById("myDropdown2").classList.toggle("show2");
	}

	// Close the dropdown if the user clicks outside of it
	window.onclick = function(event) {
		if (!event.target.matches('.dropbtn2')) {

			var dropdowns = document
					.getElementsByClassName("dropdown-content2");
			var i;
			for (i = 0; i < dropdowns.length; i++) {
				var openDropdown = dropdowns[i];
				if (openDropdown.classList.contains('show2')) {
					openDropdown.classList.remove('show2');
				}
			}
		}
	}

	$(function() {
		// This button will increment the value
		$('.qtyplus')
				.click(
						function(e) {
							// Stop acting like a button
							e.preventDefault();
							// Get the field name
							fieldName = $(this).attr('field');
							// Get its current value
							var currentVal = parseInt($(
									'input[name=' + fieldName + ']').val());
							// If is not undefined
							if (!isNaN(currentVal)) {
								// Increment
								$('input[name=' + fieldName + ']').val(
										currentVal + 1);
							} else {
								// Otherwise put a 0 there
								$('input[name=' + fieldName + ']').val(0);
							}
						});
		// This button will decrement the value till 0
		$(".qtyminus")
				.click(
						function(e) {
							// Stop acting like a button
							e.preventDefault();
							// Get the field name
							fieldName = $(this).attr('field');
							// Get its current value
							var currentVal = parseInt($(
									'input[name=' + fieldName + ']').val());
							// If it isn't undefined or its greater than 0
							if (!isNaN(currentVal) && currentVal > 0) {
								// Decrement one
								$('input[name=' + fieldName + ']').val(
										currentVal - 1);
							} else {
								// Otherwise put a 0 there
								$('input[name=' + fieldName + ']').val(0);
							}
						});
	});
</script>
<style>
.qty {
  width: 40px;
  height: 35px;
  text-align: center;
  border: 0;
  border-top: 1px solid #aaa;
  border-bottom: 1px solid #aaa;
}


input.qtyplus {
  width: 25px;
  height: 35px;
  border: 1px solid #aaa;
  background: #f8f8f8;
}

input.qtyminus {
  width: 25px;
  height: 35px;
  border: 1px solid #aaa;
  background: #f8f8f8;
}
</style>
</html>

</body>
</html>