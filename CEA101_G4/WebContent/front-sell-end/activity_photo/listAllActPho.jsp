<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.activity_product.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.activity_photo.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
    List<ActivityPhotoVO> list = (List<ActivityPhotoVO>) request.getAttribute("list");
    pageContext.setAttribute("list", list);
%>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.2.2/css/buttons.bootstrap.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-sell-end/front-sell-sideBar.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.min.css" />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
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

        .list-photo {
        	border-radius: 20px;
            height: 250px;
            width: 250px;
        }
        .upLoadbtn{ 
      		 border-radius: 10px; 
   			 width: 100px; 
   			 height: 30px;
   		} 
   		#preview img{
   			margin-right: 20px;
   		}
   		
    </style>
    <script type="text/javascript">
        function init() {
            let myFile = document.getElementById("myFile");
            let preview = document.getElementById('preview');
			let upLoadImg=document.getElementById("upLoadImg");
			
            // 2. 對myFile物件註冊change事件 - 改變選擇的檔案時觸發
            myFile.addEventListener("change", function() {
                	$('#preview').html("");
                if (this.files) {
                    for (let i = 0; i < this.files.length; i++) {
                        let file = this.files[i];
                        if (file.type.indexOf("image") > -1) {
                            let reader = new FileReader();
                            reader.addEventListener("load", function(e) {

                                let label = document.createElement("label");
                                let img = document.createElement("img");
                                img.setAttribute("height", "150px");
                                img.setAttribute("width", "150px");
                                img.src = e.target.result;
                                preview.append(label);
                                label.append(img);
                                $('#upLoadImg').prop('disabled', false);

                            });
                            reader.readAsDataURL(file);
                        } else {
                            alert('請上傳圖片！');
                            $('#myFile').val("");
                        }

                    }

                }
            })
        }
        window.onload = init;
    </script>
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
                                <table id="example" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%" style="text-align: center;">
                                    <tbody>
                                        <c:choose>
                                            <c:when test="${list.size()==0}">
                                                <tr style="line-height: 25px; text-align: center;">
                                                    <td><img src="<%=request.getContextPath()%>/image/nophoto/none2.jpg"></td>
                                            </c:when>
                                            <c:otherwise>
                                                <c:forEach var="actphoVO" items="${list}">
                                                    <tr style="line-height: 25px; text-align: center;">
                                                        <td><img class="list-photo" src="<%=request.getContextPath()%>/ActivityPhoto/ActivityPhoto.do?act_photo_id=${actphoVO.act_photo_id}&action=getOneActPho"></td>
                                                        <td>
                                                            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ActivityPhoto/ActivityPhoto.do" style="margin-bottom: 0px;">
                                                                <input type="submit" value="刪除">
                                                                <input type="hidden" name="act_id" value="${actphoVO.act_id}">
                                                                <input type="hidden" name="act_photo_id" value="${actphoVO.act_photo_id}"> <input type="hidden" name="action" value="delete">
                                                            </FORM>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                        
                                        
                                        <FORM action="<%=request.getContextPath()%>/ActivityPhoto/ActivityPhoto.do" method="post" enctype="multipart/form-data">
                                            <div class="container">
                                                <div class="row">
	                                                <div class="col-12 align-self-center" style="text-align: center;">
	                                                    <label>請上傳圖片檔案</label>
	                                                    <!-- 這裡一定要有一個<input type="file">的元素，當上傳點 -->
	                                                    <input type="file" name="upfile1" id="myFile" multiple>
	                                                    
	                                                </div>
	                                              </div>
                                                
                                                <div class="row">
                                                	<div class="col-12 align-self-center" style="text-align: center;">
		                                                    <input type="hidden" name="act_id" value="${act_id}"><br>
		                                                    <input type="hidden" name="action" value="upDate_By_Act_id"><br>
		                                                    <button type="submit" id="upLoadImg" disabled="true" class="upLoadbtn">上傳圖片</button>
                                                    <div id="preview"></div>
                                                 </div>
                                                </div>
                                            </div>
                                        </FORM>
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