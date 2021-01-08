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
<link rel="stylesheet" type="text/css" href="css/back-index-sidebar.css">
</head>
<style>
   .col {
  
            margin-left: 30px;
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

        .snip1268 h2 {
            text-align: center;
        }
        .snip1268 {
            margin-left: 50px;

        }

        .image img {
            height: 200px;

        }

        #photowall {
            width: 1140px;
            margin-left: 50px;
        }
        /* Icon set - http://ionicons.com */
        @import url(https://fonts.googleapis.com/css?family=Raleway:400,500,700);
        @import url(https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css);
        </style>
<!-- icon圖示  https://ionicons.com/ -->
<script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>

<body>

	<%@ include file="/front-mem-end/bar.jsp"%>
	<!-- 這邊是你原本sidebar的位置ㄛ -->


	<div id="photowall" class="row">
		<c:forEach var="soupVO" items="${list}" >
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
						<a href="#">
						<i><ion-icon name="heart"></ion-icon></i>
						</a> 
						<a href="#">
						 <i><ion-icon name="sad-outline"></ion-icon></i>
						 </a> 
						 <a href="#">
						 <i><ion-icon name="reader-outline"></ion-icon></i>
						 </a> 
			

			     
			     		
			
				
<!-- 						 </i> -->
<!-- 						 </FORM> -->
					</div>
      				</form>     				
<!-- 					<a href="#" class="add-to-cart">Add to Cart</a> -->
				</div>
						
				<figcaption>
					<h2 class="productname">${soupVO.sou_name}</h2>
					
					<div class="price">$${soupVO.sou_price}元</div>
						<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/souvenir_product/SouvenirProductServlet"
								style="margin-bottom: 0px;">
								<input class="input-group-text" type="submit" value="詳情">
								<input type="hidden" name="sou_id" value="${soupVO.sou_id}">
								<input type="hidden" name="action" value="checkSouvenirDetail">
							</FORM>
					   
				</figcaption>
				<Form name="soupVO" action="<%=request.getContextPath()%>/shopping/shopping.do" method="POST">
					<div align="center" style="display:inline;">數量： <input type="text" name="quantity" size="3" value=1></div>
					<input type="submit" value="加入購物車">
					<input type="hidden" name="sou_name" value="${soupVO.sou_name}">
					<input type="hidden" name="sou_price" value="${soupVO.sou_price}">
             		<input type="hidden" name="sou_id" value="${soupVO.sou_id}">
      				<input type="hidden" name="action" value="ADD"> 
      				</Form> 
			</figure>
		</div>
		</c:forEach>
	</div>
	

    <script>
        /* Demo purposes only */
        $(".hover").mouseleave(
            function() {
                $(this).removeClass("hover");
            }
        );
    </script>

</body>
</html>