<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">

	<title>房間照片新增 - addRoomPhoto.jsp</title>
	<style>
	    #preview div .img-thumbnail {
            box-sizing: border-box;
            display: block;
            height: 150px;
		  	width: 100%;
            object-fit: cover;
        }
        .uploadBtn {
        	text-align: right;
        	margin-top: 20px;
        }
        
		.test {
			position: relative;
		
		}
        
        #formFileMultiple {
        	width: 100%;
        	height: 70%;
        	box-shadow: 2px 2px 5px #8c8e98;
        	background-color: #f3f3f3;
        }
        
        #preview {
        	position: absolute;
        	border: 1px solid blue;
		    left: 10%;
		    top: 15%;
		    width: 80%;
        }

    </style>
</head>
<body>
	<div id="viewport">
	    <%@ include file="/front-sell-end/sellMemSideBar.jsp"%>
	    <div id="content">
	        <%@ include file="/front-sell-end/sellNavBar.jsp"%>
			<div class="container-fluid" style="padding: 0;">
				<div class="container">
					<form METHOD="post" ACTION="<%=request.getContextPath()%>/roomphoto/roomphoto.do" enctype="multipart/form-data">
						<div>
							<label class="form-label">房間名稱: ${roomVO.roomName}</label>
						</div>
						<div class="col-12 test">
<!-- 							<label for="formFileMultiple" class="form-label uploadLab">請選擇欲上傳的圖片</label> -->
							<input class="form-control" type="file" id="formFileMultiple"  name="roomPhoto" multiple>
							<div class="row" id="preview">
							</div>
						</div>

						<div class="uploadBtn">
							<input type="hidden" name=roomId value="${param.roomId}">
							<input type="hidden" name="action" value="insert">
							<button type="submit" class="btn btn-secondary">確認上傳</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
    	$(document).ready(function() {
    		
    		
    		$("#formFileMultiple").change(function() {
    			var photoList = [];
                for (let a = 0; a < this.files.length; a++) {
                	photoList.push(this.files[a]);
                }
    			var preview = document.getElementById("preview");
    			
                if (photoList) {
                	preview.innerHTML = ``;
                	
                    for (let i = 0; i < photoList.length; i++) {
                        let file = photoList[i];
                        if (file.type != undefined && file.type.indexOf('image') > -1) {
                            // 填入檔名
                            // filename.value = file.name;

                            // new a FileReader
                            let reader = new FileReader();
                            reader.addEventListener('load', function(e) {
                                let div = document.createElement('div');
                                div.classList.add("col-4");
                                div.innerHTML = `
                                <label name="roomPhotoName" id="` + file.name + `"></label><br>
                                <div class="img-div"><img class="img-thumbnail object-fit" src="` + e.target.result + `"></div>`;
                                preview.append(div);
                            });
                            // 使用FileReader物件上的 readAsDataURL(file) 的方法，傳入要讀取的檔案，並開始進行讀取
                            reader.readAsDataURL(file);
                        }
                    }
                } else {
					// 彈出警告視窗 alert('請上傳圖片！');
					alert('請上傳圖片');
				}
    		});

		});
    	
// 		function selectPic(picName) {
// 			var tar = $("#" + picName)
//             tar.click(function() {
//             	tar.prop("checked", !tar.prop("checked"));
//             })
//             // console.log(ckbox.checked);
//     	}

    </script>

</body>
</html>