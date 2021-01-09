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
</head>
<style>
img {
	height: 420px;
}

.row {
	justify-content: center;
	align-items: center;
}

.col-lg-8{
	border: solid 1px black;
	margin-top: 5px;
	text-align: center;
}
.navbar-brand #logo {
	height : 15% ;
}
</style>
<body align="center">
	<%@ include file="/front-mem-end/bar.jsp"%>


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
									</a>
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

</html>

</body>
</html>