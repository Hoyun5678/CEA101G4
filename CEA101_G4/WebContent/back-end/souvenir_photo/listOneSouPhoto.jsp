<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.souvenir_photo.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	SouvenirPhotoVO souphVO = (SouvenirPhotoVO) request.getAttribute("souphVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>


<jsp:useBean id="souphSvc" scope="page" class="com.souvenir_photo.model.SouvenirPhotoService" />
<jsp:useBean id="souSvc" scope="page" class="com.souvenir_product.model.SouvenirProductService" />
<c:set var="sou_id" value="${param.sou_id}"/>
<c:set var="soupVO" scope="page" value="${souSvc.getOneSou(param.sou_id)}"/>
<c:set var="list" scope="page" value="${souphSvc.getBySouId(soupVO.sou_id)}"/>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    
	<title>民宿會員 - 查看房間圖片</title>
	<style>
		img {
			max-width: 100%;
		}
		.photoBox { 
  			margin: 10px 0;

 		} 
 		.photoBox div { 
  			max-width: 100%;  
  			height: 160px;
			position: relative;
 			overflow: hidden;
  			box-shadow: 3px 3px 5px #ccc;
			border-radius: 10px; 			
 		} 
 		.photoBox div:active { 
  			top: 2px;
  			left: 2px;
  			opacity: .7;
  			filter: grayscale();
 		} 
 		
		.photoBox div img {
			position: absolute;
			top: 50%;
			left: 0;
			transform: translateY(-50%);
		}
		.toDelete {
			opacity: .7;
			filter: grayscale();
		}
		.btns, .submitBtn {
			position: fixed;
			right: 0%;
			top:22;
		}
		
		#greyBack {
			position: fixed;
			top: 0;
			left: 0;
			width: 100%;
			height: 100%;
			cursor: pointer;
			background-color: rgba(0, 0, 0, 0.3);
		}
		
		#uploadPics .container {
			background-color: rgba(255, 255, 255, 1);
			border-radius: 10px;
	 		height: 60%;
	 		width: 50%;
			position: absolute;
			padding: 10px;
			left: 25%;
			top: 10%;
		}
	
		.imgBox img {
			max-width: 100%;
			max-height: 100%;
		}
		
		#formFileMultiple {
			width: 100%;
			height: 100%;
			border: 1px solid red;
		}
		
		.offset-1 {
			margin-left : 0 !important;
		}
	</style>
</head>
<body>
	<div id="wrapper">

		<!-- 		這邊是你原本放sidebar的位置 include你的sidebar進來           -->
		<%@ include file="/back-end/back-index-sidebar.jsp"%>

		<div id="page-content-wrapper">


	<div id="viewport">
		<div id="content">
			<div class="container-fluid" style="padding: 0;">
				<div class="container">

					<div class="container custom-width" id="showPics">
						<div class="row">
							<div class="offset-1 col-10" style="margin-top: 30px;">
								<h2>${soupVO.sou_name}的展示圖片</h2>
							</div>
							<c:forEach var="souphVO" items="${list}" >
								<div class="col-12 col-sm-6 col-lg-4 photoBox">
									<div>
										<img class="roomPhotoImg" name="${souphVO.sou_photo_id}" src="${pageContext.request.contextPath}/souvenir_photo/SouvenirPhotoServlet?sou_photo_id=${souphVO.sou_photo_id}&action=getSouPhoto&scaleSize=500">
										<input type="hidden" name="sou_photo_id" value="${souphVO.sou_photo_id}" class="roomPId">
									</div>
								</div>
								
							</c:forEach>
						</div>
					</div>
                </div>
			</div>
		</div>
	</div>
	<div class="btns">
		<form method='POST' action="<%=request.getContextPath()%>/back-end/souvenir_photo/addSouPhoto.jsp" >
			<button type="button" class="deleteBtn btn btn-danger d-none">刪除圖片</button>
			<input type="hidden" name="sou_id" value='${sou_id}' />
			<button type="button" class="uploadBtn btn btn-primary" style="margin-left: 10px;">上傳圖片</button>
		</form>
	</div>
<!-- 燈箱區 -->
	<div class="container-fluid d-none" id="greyBack">

	</div>
	<div class="container-fluid d-none" id="uploadPics">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/souvenir_photo/SouvenirPhotoServlet</">
			<div class="container" id="updateRoomPhotoInput">
				<div class="row ">
					<div class="col-12">
						<label for="formFileMultiple" class="form-label uploadLab">房間名稱: ${soupVO.sou_name}</label>
						<input class="form-control" type="file" id="formFileMultiple"  name="sou_photo" multiple>
					</div>
					<div class="col-11 justify-content-start" id="preview">
					</div>
					<div class="col-1 btns">
						<input type="hidden" name="sou_id" value="${sou_id}">
						<input type="hidden" name="action" value="insert">
						<button type="submit" class="btn btn-primary">確認上傳</button>
					</div>
				</div>
			</div>
		</FORM>
	</div>
	</div>
	</div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
    	$(document).ready(function() {
    		var contextPath = '<%=request.getContextPath()%>';
    		var urlTarget = contextPath + '/souvenir_photo/SouvenirPhotoServlet';
    		
    		$('.roomPhotoImg').click(function() {
    			$(this).toggleClass('toDelete');
    			
    			let toDeleteList = $('.toDelete');
    			if(toDeleteList.length > 0) {
    				$('.deleteBtn').removeClass('d-none');
    			} else {
    				$('.deleteBtn').addClass('d-none');
    			}
    		})
    		
    		
    		$(document).on('click', '.deleteBtn', function() {
    			let toDelete = $('.toDelete');
    			while (toDelete.length > 0) {
    				var par = toDelete[0].parentNode.parentNode;
//     				console.log(par)
    				$.ajax({
    					url: urlTarget, 
    					type: "POST",
    					data: {
    						"action": "delete",
    						"sou_photo_id": toDelete[0].name,
    					},
    					success: function(data) {
    						console.log(data);
    					},
    				})
    				par.remove();
    				
    				toDelete = $('.toDelete');
    				}
    			$('.deleteBtn').addClass('d-none');
    		})
    		
    		
    		$(document).on('click', '.uploadBtn', function() {
    			$(this).parent('form').submit();
    		})
    		
//     		$(document).on('click', '.uploadBtn', function() {
//     			$('#greyBack').removeClass('d-none');
//     			$('#uploadPics').removeClass('d-none');
//     		})
//     		$(document).on('click', '#greyBack', function() {
//     			$('#greyBack').addClass('d-none');
//     			$('#uploadPics').addClass('d-none');
//     		})
    		
    		
    		
//     		$("#formFileMultiple").change(function() {
//     			var photoList = [];
//                 for (let a = 0; a < this.files.length; a++) {
//                 	photoList.push(this.files[a]);
//                 }
//     			var preview = document.getElementById("preview");
    			
//                 if (photoList) {
//                 	preview.innerHTML = ``;
                	
//                     for (let i = 0; i < photoList.length; i++) {
//                         let file = photoList[i];
//                         if (file.type != undefined && file.type.indexOf('image') > -1) {
//                             // 填入檔名
//                             // filename.value = file.name;

//                             // new a FileReader
//                             let reader = new FileReader();
//                             reader.addEventListener('load', function(e) {
//                                 let div = document.createElement('div');
//                                 div.classList.add("col-3");
//                                 div.innerHTML = `
//                                 <label name="roomPhotoName" id="` + file.name + `"></label><br>
//                                 <div class="img-div"><img class="img-thumbnail object-fit" src="` + e.target.result + `"></div>`;
//                                 preview.append(div);
//                             });
//                             // 使用FileReader物件上的 readAsDataURL(file) 的方法，傳入要讀取的檔案，並開始進行讀取
//                             reader.readAsDataURL(file);
//                         }
//                     }
//                 } else {
// 					// 彈出警告視窗 alert('請上傳圖片！');
// 					alert('請上傳圖片');
// 				}
//     		});
    		
    		
    		
    	})
    </script>
	
</body>
</html>

