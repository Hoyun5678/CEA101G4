<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html>
<html lang="en">

<head>

<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/image/LOGO/tiger.png" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" />

</head>

<body>



<%@include file="/front-mem-end/front-nav-bar.jsp" %>

<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

		<div id="carouselExampleFade" class="carousel slide carousel-fade"
			data-interval="3000" data-ride="carousel" data-pause="false">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="<%=request.getContextPath()%>/image/1.jpg"
						class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="<%=request.getContextPath()%>/image/2.jfif"
						class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="<%=request.getContextPath()%>/image/3.jfif"
						class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="<%=request.getContextPath()%>/image/4 .jfif"
						class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="<%=request.getContextPath()%>/image/5.jfif"
						class="d-block w-100" alt="...">
				</div>
			</div>
	</div>
	<main>
		<section class="s0">
			<div class="provide">
				<h2 class="provide_title">選擇不同的民宿</h2>
				<div class="provide_item_all">
					<div class="item">
						<div class="crown_container"></div>
						<h3 class="item_title">整套房源</h3>
						<figure>
							<img src="<%=request.getContextPath()%>/image/index/1.jpg" alt="">
							<figcaption>三天兩夜 NT50000起</figcaption>
						</figure>
					</div>
					<div class="item">
						<div class="crown_container"></div>
						<h3 class="item_title">小木屋</h3>
						<figure>
							<img src="<%=request.getContextPath()%>/image/index/2.jpg" alt="">
							<figcaption>兩天一夜 NT35000起</figcaption>
						</figure>
					</div>
					<div class="item">
						<div class="crown_container"></div>
						<h3 class="item_title">獨特房源</h3>
						<figure>
							<img src="<%=request.getContextPath()%>/image/index/5.jpg" alt="">
							<figcaption>熱門促銷</figcaption>
						</figure>
					</div>
					<div class="item">
						<div class="crown_container"></div>
						<h3 class="item_title">寵物友善</h3>
						<figure>
							<img src="<%=request.getContextPath()%>/image/index/7.jpg" alt="">
							<figcaption>我最不會</figcaption>
						</figure>
					</div>
				</div>
			</div>
		</section>
		<div class="allplace">
			<div class="placeimg">
				<h3 class="our">熱門民宿</h3>
				<div class="box_all">
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big1.jpg"> <img class="sma"
							src="<%=request.getContextPath()%>/image/index/sma1.jpg" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big2.jpg"> <img class="sma"
							src="<%=request.getContextPath()%>/image/index/sma2.jpg" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big3.jpg"> <img class="sma"
							src="<%=request.getContextPath()%>/image/index/sma3.jpg" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big4.jpg"> <img class="sma"
							src="<%=request.getContextPath()%>/image/index/sma4.jpg" alt="">
						</a>
					</div>
				</div>
				<div class="box_all">
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big5.jpg"> <img class="sma"
							src="<%=request.getContextPath()%>/image/index/sma5.jpg" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big6.jpg"> <img class="sma"
							src="<%=request.getContextPath()%>/image/index/sma6.jpg" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big7.jpg"> <img class="sma"
							src="<%=request.getContextPath()%>/image/index/sma7.jpg" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big8.jpg"> <img class="sma"
							src="<%=request.getContextPath()%>/image/index/sma8.jpg" alt="">
						</a>
					</div>
				</div>
			</div>
		</div>
		</section>
		<section class="s0">
			<div class="provide">
				<h2 class="provide_title">體驗不同類型的活動</h2>
				<div class="provide_item_all">
					<div class="item">
						<div class="crown_container"></div>
						<h3 class="item_title">水上</h3>
						<figure>
							<img src="https://picsum.photos/350/400?random=9" alt="">
							<figcaption>兩人同行一人免費</figcaption>
						</figure>
					</div>
					<div class="item">
						<div class="crown_container"></div>
						<h3 class="item_title">路上</h3>
						<figure>
							<img src="https://picsum.photos/350/400?random=10" alt="">
							<figcaption>需確認天氣情況</figcaption>
						</figure>
					</div>
					<div class="item">
						<div class="crown_container"></div>
						<h3 class="item_title">手作</h3>
						<figure>
							<img src="https://picsum.photos/350/400?random=11" alt="">
							<figcaption>體驗DIY課程</figcaption>
						</figure>
					</div>
					<div class="item">
						<div class="crown_container"></div>
						<h3 class="item_title">文化體驗</h3>
						<figure>
							<img src="https://picsum.photos/350/400?random=12" alt="">
							<figcaption>NT199起</figcaption>
						</figure>
					</div>
				</div>
			</div>
		</section>
		<div class="allplace">
			<div class="placeimg">
				<h3 class="our">熱門活動</h3>
				<div class="box_all">
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big1.jpg"> <img class="sma"
							src="https://picsum.photos/350/400?random=5" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big2.jpg"> <img class="sma"
							src="https://picsum.photos/350/400?random=6" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big3.jpg"> <img class="sma"
							src="https://picsum.photos/350/400?random=7" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big4.jpg"> <img class="sma"
							src="https://picsum.photos/350/400?random=8" alt="">
						</a>
					</div>
				</div>
				<div class="box_all">
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big5.jpg"> <img class="sma"
							src="https://picsum.photos/350/400?random=1" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big6.jpg"> <img class="sma"
							src="https://picsum.photos/350/400?random=2" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big7.jpg"> <img class="sma"
							src="https://picsum.photos/350/400?random=3" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big8.jpg"> <img class="sma"
							src="https://picsum.photos/350/400?random=4" alt="">
						</a>
					</div>
				</div>
			</div>
		</div>
		<section class="s0">
			<div class="provide">
				<h2 class="provide_title">品嘗不同特產</h2>
				<div class="provide_item_all">
					<div class="item">
						<div class="crown_container"></div>
						<h3 class="item_title">甜品</h3>
						<figure>
							<img src="https://picsum.photos/350/400?random=13" alt="">
							<figcaption>低溫直送</figcaption>
						</figure>
					</div>
					<div class="item">
						<div class="crown_container"></div>
						<h3 class="item_title">酒類</h3>
						<figure>
							<img src="https://picsum.photos/350/400?random=14" alt="">
							<figcaption>NT1200起</figcaption>
						</figure>
					</div>
					<div class="item">
						<div class="crown_container"></div>
						<h3 class="item_title">文創商品</h3>
						<figure>
							<img src="https://picsum.photos/350/400?random=15" alt="">
							<figcaption>紀念品最低NT39起</figcaption>
						</figure>
					</div>
					<div class="item">
						<div class="crown_container"></div>
						<h3 class="item_title">禮盒</h3>
						<figure>
							<img src="https://picsum.photos/350/400?random=16" alt="">
							<figcaption>拿去送人啦</figcaption>
						</figure>
					</div>
				</div>
			</div>
		</section>
		<div class="allplace">
			<div class="placeimg">
				<h3 class="our">熱門特產</h3>
				<div class="box_all">
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big1.jpg"> <img class="sma"
							src="https://picsum.photos/350/400?random=17" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big2.jpg"> <img class="sma"
							src="https://picsum.photos/350/400?random=18" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big3.jpg"> <img class="sma"
							src="https://picsum.photos/350/400?random=19" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big4.jpg"> <img class="sma"
							src="https://picsum.photos/350/400?random=20" alt="">
						</a>
					</div>
				</div>
				<div class="box_all">
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big5.jpg"> <img class="sma"
							src="https://picsum.photos/350/400?random=21" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big6.jpg"> <img class="sma"
							src="https://picsum.photos/350/400?random=202" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big7.jpg"> <img class="sma"
							src="https://picsum.photos/350/400?random=203" alt="">
						</a>
					</div>
					<div class="box" uk-lightbox>
						<a class="uk-button uk-button-default"
							href="./image/index/big8.jpg"> <img class="sma"
							src="https://picsum.photos/350/400?random=204" alt="">
						</a>
					</div>
				</div>
			</div>
		</div>

		<section class="s1">
			<h2 class="provide_title">你也是民宿業者嗎？馬上<a href='<%=request.getContextPath()%>/front-sell-end/sellMemLogin.jsp'>加入</a>我們的平台！</h2>
			<div class="intro">
				<img src="https://picsum.photos/1400/500?random=204" alt="">
				<div class="intro_text">
					<h3 class="our"></h3>
					<p class="intro_t"></p>
					<h3 class="our"></h3>
					<p class="intro_t"></p>
				</div>
			</div>
		</section>
	
	</main>
	<footer>
		<div class="foo conta" style="text-align: center;">
			<div class="fooco">SuperGoing</div>
			<div>E-mail：你最愛的民宿平台@gmail.com</div>
			<div>Tele：2933-3939</div>
			<div class="copy">
			<button type="button" class="btn btn-warning" onclick="location.href='<%=request.getContextPath()%>/front-mem-end/faq/listAllFAQ.jsp'">
								FAQ
			</button>
			</div>
				<div class="copy">
			<button type="button" class="btn btn-warning" onclick="location.href='<%=request.getContextPath()%>/front-mem-end/news/listNews.jsp'">
								最新消息
			</button>
			</div>
		</div>
		<div class="foo logo">
			
		</div>
	</footer>
</body>
</body>

</html>