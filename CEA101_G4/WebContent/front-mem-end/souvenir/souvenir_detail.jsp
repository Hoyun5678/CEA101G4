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
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">
<link
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'>
</head>
<style type="text/css" media="screen">

.pd-wrap {
	padding: 40px 0;
	font-family: 'Poppins', sans-serif;
}

.heading-section {
	text-align: center;
	margin-bottom: 20px;
}

.sub-heading {
	font-family: 'Poppins', sans-serif;
	font-size: 12px;
	display: block;
	font-weight: 600;
	color: #2e9ca1;
	text-transform: uppercase;
	letter-spacing: 2px;
}

.heading-section h2 {
	font-size: 32px;
	font-weight: 500;
	padding-top: 10px;
	padding-bottom: 15px;
	font-family: 'Poppins', sans-serif;
}

.user-img {
	width: 80px;
	height: 80px;
	border-radius: 50%;
	position: relative;
	min-width: 80px;
	background-size: 100%;
}

.carousel-testimonial .item {
	padding: 30px 10px;
}

.quote {
	position: absolute;
	top: -23px;
	color: #2e9da1;
	font-size: 27px;
}

.name {
	margin-bottom: 0;
	line-height: 14px;
	font-size: 17px;
	font-weight: 500;
}

.position {
	color: #adadad;
	font-size: 14px;
}

.owl-nav button {
	position: absolute;
	top: 50%;
	transform: translate(0, -50%);
	outline: none;
	height: 25px;
}

.owl-nav button svg {
	width: 25px;
	height: 25px;
}

.owl-nav button.owl-prev {
	left: 25px;
}

.owl-nav button.owl-next {
	right: 25px;
}

.owl-nav button span {
	font-size: 45px;
}

.product-thumb .item img {
	height: 100px;
}

.product-name {
	font-size: 22px;
	font-weight: 500;
	line-height: 22px;
	margin-bottom: 4px;
}

.product-price-discount {
	font-size: 22px;
	font-weight: 400;
	padding: 10px 0;
	clear: both;
}

.product-price-discount span.line-through {
	text-decoration: line-through;
	margin-left: 10px;
	font-size: 14px;
	vertical-align: middle;
	color: #a5a5a5;
}

.display-flex {
	display: flex;
}

.align-center {
	align-items: center;
}

.product-info {
	width: 100%;
}

.reviews-counter {
	font-size: 13px;
}

.reviews-counter span {
	vertical-align: -2px;
}

.rate {
	float: left;
	padding: 0 10px 0 0;
}

.rate:not (:checked )>input {
	position: absolute;
	top: -9999px;
}

.rate:not (:checked )>label {
	float: right;
	width: 15px;
	overflow: hidden;
	white-space: nowrap;
	cursor: pointer;
	font-size: 21px;
	color: #ccc;
	margin-bottom: 0;
	line-height: 21px;
}

.rate:not (:checked )>label:before {
	content: '\2605';
}

.rate>input:checked ~label {
	color: #ffc700;
}

.rate:not (:checked )>label:hover, .rate:not (:checked )>label:hover
	 ~label {
	color: #deb217;
}

.rate>input:checked+label:hover, .rate>input:checked+label:hover ~label,
	.rate>input:checked ~label:hover, .rate>input:checked ~label:hover
	 ~label, .rate>label:hover ~input:checked ~label {
	color: #c59b08;
}

.product-dtl p {
	font-size: 14px;
	line-height: 24px;
	color: #7a7a7a;
	word-break:break-all;
}

.product-dtl .form-control {
	font-size: 15px;
	
}

.product-dtl label {
	line-height: 16px;
	font-size: 15px;
}

.form-control:focus {
	outline: none;
	box-shadow: none;
}

.product-count {
	margin-top: 15px;
	    display: initial;
}

.product-count .qtyminus, .product-count .qtyplus {
	width: 34px;
	height: 34px;
	background: #212529;
	text-align: center;
	font-size: 19px;
	line-height: 36px;
	color: #fff;
	cursor: pointer;
	margin-left:12px;
}

.product-count .qtyminus {
	border-radius: 3px 0 0 3px;
}

.product-count .qtyplus {
	border-radius: 0 3px 3px 0;
}

.product-count .qty {
	width: 60px;
	text-align: center;
	margin: 10px 0px;
}

.round-black-btn {
	border-radius: 4px;
	background: #212529;
	color: #fff;
	padding: 7px 45px;
	display: inline-block;
	margin-top: 20px;
	border: solid 2px #212529;
	transition: all 0.5s ease-in-out 0s;
}

.round-black-btn:hover, .round-black-btn:focus {
	background: transparent;
	color: #212529;
	text-decoration: none;
}

.product-info-tabs {
	margin-top: 25px;
}

.product-info-tabs .nav-tabs {
	border-bottom: 2px solid #d8d8d8;
}

.product-info-tabs .nav-tabs .nav-item {
	margin-bottom: 0;
}

.product-info-tabs .nav-tabs .nav-link {
	border: none;
	border-bottom: 2px solid transparent;
	color: #323232 !important;
	
}

.product-info-tabs .nav-tabs .nav-item .nav-link:hover {
	border: none;
}

.product-info-tabs .nav-tabs .nav-item.show .nav-link,
	.product-info-tabs .nav-tabs .nav-link.active, .product-info-tabs .nav-tabs .nav-link.active:hover
	{
	border: none;
	border-bottom: 2px solid #d8d8d8;
	font-weight: bold;
}

.product-info-tabs .tab-content .tab-pane {
	padding: 30px 20px;
	font-size: 15px;
	line-height: 24px;
	color: #7a7a7a;
}

.review-form .form-group {
	clear: both;
}

.mb-20 {
	margin-bottom: 20px;
}

.review-form .rate {
	float: none;
	display: inline-block;
}

.review-heading {
	font-size: 24px;
	font-weight: 600;
	line-height: 24px;
	margin-bottom: 6px;
	text-transform: uppercase;
	color: #000;
}

.review-form .form-control {
	font-size: 14px;
}

.review-form input.form-control {
	height: 40px;
}

.review-form textarea.form-control {
	resize: none;
}

.review-form .round-black-btn {
	text-transform: uppercase;
	cursor: pointer;
}

/*         car */
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
/* 	position: absolute; */
	bottom: 7px;
	right:0;
}

#detailPhoto {
	width:450px;
	height:297.9px;

}

.btn-success {
	margin-bottom: 5px;
}
</style>

<body>
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

	<div class="pd-wrap">
		<div class="container">
			<div class="heading-section">
				<h2>特產詳情</h2>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div id="slider" class="owl-carousel product-slider">
						<div class="item">
							<c:forEach var="souphVO"
								items="${souphSvc.getBySouId(soupVO.sou_id)}">
								<img id="detailPhoto"
									src="${pageContext.request.contextPath}/souvenir_photo/SouvenirPhotoServlet?sou_photo_id=${souphVO.sou_photo_id}&action=getSouPhoto">
							</c:forEach>
						</div>
						<div class="item">
							<img src="https://i.ytimg.com/vi/PJ_zomNMK_s/maxresdefault.jpg" />
						</div>
						<div class="item">
							<img
								src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQI6nUmObt62eDkqNSmIvCN_KkQExtbpJmUbVx_eTh_Y3v3r-Jw" />
						</div>
						<div class="item">
							<img src="https://i.ytimg.com/vi/PJ_zomNMK_s/maxresdefault.jpg" />
						</div>
						<div class="item">
							<img
								src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQI6nUmObt62eDkqNSmIvCN_KkQExtbpJmUbVx_eTh_Y3v3r-Jw" />
						</div>
						<div class="item">
							<img src="https://i.ytimg.com/vi/PJ_zomNMK_s/maxresdefault.jpg" />
						</div>
						<div class="item">
							<img
								src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQI6nUmObt62eDkqNSmIvCN_KkQExtbpJmUbVx_eTh_Y3v3r-Jw" />
						</div>
					</div>
					
				</div>
				<div class="col-md-6">
					<div class="product-dtl">
						<div class="product-info">
							<div class="product-name">${soupVO.sou_name}</div>
							<div class="reviews-counter">
								<div class="rate">
									<input type="radio" id="star5" name="rate" value="5" checked />
									<label for="star5" title="text">5 stars</label> <input
										type="radio" id="star4" name="rate" value="4" checked /> <label
										for="star4" title="text">4 stars</label> <input type="radio"
										id="star3" name="rate" value="3" checked /> <label
										for="star3" title="text">3 stars</label> <input type="radio"
										id="star2" name="rate" value="2" /> <label for="star2"
										title="text">2 stars</label> <input type="radio" id="star1"
										name="rate" value="1" /> <label for="star1" title="text">1
										star</label>
								</div>
								<span>399 Reviews</span>
							</div>
							<div class="product-price-discount">
								<span>$${soupVO.sou_price}</span><span class="line-through">$699.00</span>
							</div>
						</div>
						<p>${soupVO.sou_des}</p>
						<div class="row">
							<div class="col-md-6">
								<label for="size">Size</label> <select id="size" name="size"
									class="form-control">
									<option>S</option>
									<option>M</option>
									<option>L</option>
									<option>XL</option>
								</select>
							</div>
<!-- 							<div class="col-md-6"> -->
<!-- 								<label for="color">Color</label> <select id="color" name="color" -->
<!-- 									class="form-control"> -->
<!-- 									<option>Blue</option> -->
<!-- 									<option>Green</option> -->
<!-- 									<option>Red</option> -->
<!-- 								</select> -->
<!-- 							</div> -->
						</div>

						<Form id="addcar" name="soupVO"
							action="<%=request.getContextPath()%>/shopping/shopping.do"
							method="POST">
							<div class="product-count" class="display-flex">
								<label for="size">Quantity</label>
																<div class="qtyplus">+</div>
								
								<input type="text" name="quantity" value="1" class="qty">
																<div class="qtyminus">-</div>
								
								<input class="round-black-btn" type="submit" value="加入購物車">
								<input type="hidden" name="sou_name" value="${soupVO.sou_name}">
								<input type="hidden" name="sou_price"
									value="${soupVO.sou_price}"> <input type="hidden"
									name="sou_id" value="${soupVO.sou_id}"> <input
									type="hidden" name="action" value="ADD">

							</div>
						</Form>
					</div>
					
				</div>
			</div>
			<div class="product-info-tabs">
				<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item"><a class="nav-link active"
						id="description-tab" data-toggle="tab" href="#description"
						role="tab" aria-controls="description" aria-selected="true">Description</a>
					</li>
					<li class="nav-item"><a class="nav-link" id="review-tab"
						data-toggle="tab" href="#review" role="tab" aria-controls="review"
						aria-selected="false">Reviews (0)</a></li>
				</ul>
				<div class="tab-content" id="myTabContent">
					<div class="tab-pane fade show active" id="description"
						role="tabpanel" aria-labelledby="description-tab">
						退款及退貨<br>
如閣下於收貨後發現產品出現以下情況，可向我們要求退貨及更換產品。惟所有退貨或更換要求必須在收貨後七 ( 7 )日內進行：<br>
(i)	產品已損壞 /包裝不完整<br>
(ii)	懷疑出現品質問題<br>
(iii)	遺漏或錯誤送遞產品<br>
閣下必須就上述要求提供證明以供核實，包括：<br>
(i)	出示有效文件 / 證據證明有問題之產品於 supergoing.com 購買; 及<br>
(ii)	把有問題之產品的影像拍攝並透過電郵 / facebook message 發送給我們作為證據。<br>
所有上述要求一經核實，我們會於七 ( 7 )日內就相同產品安排退貨及更換。如果該產品沒有存貨，我們可從閣下的付款閘口中退回該產品的金額。
						</div>
					<div class="tab-pane fade" id="review" role="tabpanel"
						aria-labelledby="review-tab">
						<div class="review-heading">REVIEWS</div>
						<p class="mb-20">There are no reviews yet.</p>
						<form class="review-form">
							<div class="form-group">
								<label>Your rating</label>
								<div class="reviews-counter">
									<div class="rate">
										<input type="radio" id="star5" name="rate" value="5" /> <label
											for="star5" title="text">5 stars</label> <input type="radio"
											id="star4" name="rate" value="4" /> <label for="star4"
											title="text">4 stars</label> <input type="radio" id="star3"
											name="rate" value="3" /> <label for="star3" title="text">3
											stars</label> <input type="radio" id="star2" name="rate" value="2" />
										<label for="star2" title="text">2 stars</label> <input
											type="radio" id="star1" name="rate" value="1" /> <label
											for="star1" title="text">1 star</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label>Your message</label>
								<textarea class="form-control" rows="10"></textarea>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" name="" class="form-control"
											placeholder="Name*">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" name="" class="form-control"
											placeholder="Email Id*">
									</div>
								</div>
							</div>
							<button class="round-black-btn">Submit Review</button>
						</form>
					</div>
				</div>
			</div>
			<div
				style="text-align: center; font-size: 14px; padding-bottom: 20px;">
				Get free icon packs for your next project at <a
					href="http://iiicons.in/" target="_blank"
					style="color: #ff5e63; font-weight: bold;">www.soupergoing.com</a>
			</div>
		</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="   sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var slider = $("#slider");
						var thumb = $("#thumb");
						var slidesPerPage = 4; //globaly define number of elements per page
						var syncedSecondary = true;
						slider.owlCarousel({
							items : 1,
							slideSpeed : 2000,
							nav : false,
							autoplay : false,
							dots : false,
							loop : true,
							responsiveRefreshRate : 200
						}).on('changed.owl.carousel', syncPosition);
						thumb
								.on(
										'initialized.owl.carousel',
										function() {
											thumb.find(".owl-item").eq(0)
													.addClass("current");
										})
								.owlCarousel(
										{
											items : slidesPerPage,
											dots : false,
											nav : true,
											item : 4,
											smartSpeed : 200,
											slideSpeed : 500,
											slideBy : slidesPerPage,
											navText : [
													'<svg width="18px" height="18px" viewBox="0 0 11 20"><path style="fill:none;stroke-width: 1px;stroke: #000;" d="M9.554,1.001l-8.607,8.607l8.607,8.606"/></svg>',
													'<svg width="25px" height="25px" viewBox="0 0 11 20" version="1.1"><path style="fill:none;stroke-width: 1px;stroke: #000;" d="M1.054,18.214l8.606,-8.606l-8.606,-8.607"/></svg>' ],
											responsiveRefreshRate : 100
										}).on('changed.owl.carousel',
										syncPosition2);

						function syncPosition(el) {
							var count = el.item.count - 1;
							var current = Math.round(el.item.index
									- (el.item.count / 2) - .5);
							if (current < 0) {
								current = count;
							}
							if (current > count) {
								current = 0;
							}
							thumb.find(".owl-item").removeClass("current").eq(
									current).addClass("current");
							var onscreen = thumb.find('.owl-item.active').length - 1;
							var start = thumb.find('.owl-item.active').first()
									.index();
							var end = thumb.find('.owl-item.active').last()
									.index();
							if (current > end) {
								thumb.data('owl.carousel').to(current, 100,
										true);
							}
							if (current < start) {
								thumb.data('owl.carousel').to(
										current - onscreen, 100, true);
							}
						}

						function syncPosition2(el) {
							if (syncedSecondary) {
								var number = el.item.index;
								slider.data('owl.carousel').to(number, 100,
										true);
							}
						}
						thumb.on("click", ".owl-item", function(e) {
							e.preventDefault();
							var number = $(this).index();
							slider.data('owl.carousel').to(number, 300, true);
						});

						$(".qtyminus").on("click", function() {
							var now = $(".qty").val();
							if ($.isNumeric(now)) {
								if (parseInt(now) - 1 > 0) {
									now--;
								}
								$(".qty").val(now);
							}
						})
						$(".qtyplus").on("click", function() {
							var now = $(".qty").val();
							if ($.isNumeric(now)) {
								$(".qty").val(parseInt(now) + 1);
							}
						});
					});
	
	
	
	function myFunction2() {
		document.getElementById("myDropdown2").classList.toggle("show2");
	}

	
	// 購物車下拉 the dropdown if the user clicks outside of it
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
</script>

</html>