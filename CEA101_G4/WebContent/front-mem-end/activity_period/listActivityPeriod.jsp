<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity_period.model.*"%>
<%@ page import="com.activity_product.model.*"%>
<%@ page import="com.activity_photo.model.*"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>活動商品</title>

    <!-- splidejs 輪播的css跟js -->
    <script src="https://cdn.jsdelivr.net/npm/@splidejs/splide@latest/dist/js/splide.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@splidejs/splide@latest/dist/css/splide.min.css">

    <style>
        *{
            box-sizing: border-box;
        }
        body{
            margin: 0;
            padding: 0;
        }

        /* table_list樣式開始 */
        .listTable{
            /* border: 1px solid red; */
            margin: 50px auto;
            padding: 10px;
            border-radius: 5px;
            width: 95%;
            background-color: #fff;
            box-shadow: 0px 5px 40px -22px #8b8b8b;
        }
        .listTable td{
            /* overflow: hidden; */
            /* white-space: nowrap; */
            /* text-overflow: ellipsis; */
            /* max-width: 200px; */
            /* min-width: 100px; */
            padding: 10px;
        }
        .listTable td:nth-child(1){
            /* border: 1px solid blue; */
            width: 250px;
            overflow: hidden;
            padding: 10px 15px;
        }
        .listTable td > img{
            width: 200px;
            height: 150px;
/*             object-fit: cover; */
        }
        .listTable > td:nth-last-child(1), td:nth-last-child(2){
            width: 100px;
        }
        .btn{
            font-weight: 600;
            letter-spacing: 2px;
            padding: 10px;
            outline: none;
            cursor: pointer;
            width: 95px;
            background-color: #fce40d !important;
            border: 3px solid #fce40d !important;
            padding:10px !important;
            border-radius:0px !important;
            color: #332926;
            transition: all 0.2s ease-in-out;
        }
        .btn:hover{
            border: 3px solid #fce40d !important;
            background-color: #fff !important;
        }
        .submitBtn{
            font-weight: 600;
            letter-spacing: 2px;
            padding: 10px;
            outline: none;
            cursor: pointer;
            width: 80px;
            background-color: #332926;
            border: 3px solid #332926;
            color: white;
            transition: all 0.2s ease-in-out;
        }
        .submitBtn:hover{
            border: 3px solid #332926;
            background-color: #fff;
            color: #332926;
        }
        /* table_list樣式結束 */

        /* 燈箱樣式開始 */
        .itemLightboxBg{
            background-color: #0000008f;
            height: 100vh;
            width: 100%;
            position: fixed;
            z-index: 0;
            top: 0;
            left: 0;
        }
        .itemLightbox{
            /* border: 1px solid red;  */
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 55%;
            height: 450px;
            padding: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-radius: 5px;
            background-color: #fff;
            /* box-shadow: 0px 5px 40px -22px #8b8b8b; */
            box-shadow: 0px 5px 34px -19px #000000;
            z-index: 1;
        }
        .close{
            position: absolute;
            top: 5px;
            right: 20px;
            font-family: monospace;
            font-size: 28px;
            color: #797979;
            cursor: pointer;
        }
        .container{
            /* border: 1px solid blue; */
            width: 97%;
            height: 400px;
            margin: 0 auto;
            display: flex;
            justify-content: space-around;
            align-items: center;
        }
        .caro{
            width: 600px;
            display: flex;
            overflow: hidden;
        }
        .splide{
            min-width: 500px;
        }
        .splide__list{
            width: 500px;
        }
        .itemData{
            /* border: 1px solid gold; */
            width: 400px;
            height: 90%;
            margin-left: 20px;
        }
        .itemForm{
            text-align: right;
        }
        .itemTable th, #itemTable td{
            /* border: 1px solid red; */
            /* border-bottom: 1px solid #ccc; */
            text-align: left;
            padding: 15px;
        }
        .itemForm #submitBtn{
            margin: 15px;
        }
        .splide--fade>.splide__track>.splide__list>.splide__slide{
            height: 350px;
        }
       
        /* 燈箱樣式結束 */
    </style>
</head>
<body>
    <!-- 表格開始 -->
    <%@include file="/front-mem-end/bar.jsp"%>
    <%int i=0; %>
	<jsp:useBean id="actperSvc" scope="page" class="com.activity_period.model.ActivityPeriodService" />
	 <jsp:useBean id="actproSvc" scope="page" class="com.activity_product.model.ActivityProductService" />
	 <jsp:useBean id="actphoSvc" scope="page" class="com.activity_photo.model.ActivityPhotoService" />
	  <c:forEach var="actperVO" items="${actperSvc.all}">
    <table class="listTable">
        <tr>
            <td><img class="list-photo"src="<%=request.getContextPath()%>
											/ActivityPhoto/ActivityPhoto.do?act_id=
											${actperVO.act_id}&action=getListActPhoByActId"></td>
            <td>${actproSvc.getOneActPro(actperVO.act_id).act_name}</td>
            <td>資料2</td>
            <td>資料3</td>
            <td>資料4</td>
            <td><button class="btn">查看商品</button></td>
            <td><input type="submit" value="參加" class="submitBtn"></td>
        </tr>
    </table>
    <!-- 表格結束 -->
    <!-- 燈箱開始 -->
    <div class="itemLightbox">
        <span class="close">x</span>
        <div class="container">
            <!-- 輪播開始 -->
            <div class="caro">
                <div class="splide<%=i++ %>">
                    <div class="splide__track">
                        <ul class="splide__list">
                            <c:forEach var="actphoVO" items="${actphoSvc.getActPhoByActId(actperVO.act_id)}">
                            <li class="splide__slide">
                                <img src="<%=request.getContextPath()%>
											/ActivityPhoto/ActivityPhoto.do?act_photo_id=
											${actphoVO.act_photo_id}&action=getOneActPho">
                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="splide__progress">
                        <div class="splide__progress__bar">
                        </div>
                    </div>
                </div>
            </div>
            <!-- 輪播結束 -->
            <!-- 這是商品資料 -->
            <div class="itemData">
                <form class="itemForm">
                    <table class="itemTable">
                        <tr>
                            <th>商品資料1</th>
                        </tr>
                        <tr>
                            <td>商品資料2</td>
                        </tr>
                        <tr>
                            <td>商品資料3商品資料3商品資料3商品資料3商品資料3商品資料3</td>
                        </tr>
                        <tr>
                            <td>商品資料4</td>
                        </tr>
                        <tr>
                            <td>商品資料5</td>
                        </tr>
                    </table>
                    <!-- button先放這 -->
                    <input type="submit" value="參加" class="submitBtn">
                </form>
            </div>
        </div>
    </div>
    <!-- 燈箱結束 -->
    <!-- 這是燈箱背景 -->
    <div class="itemLightboxBg"></div>
</c:forEach>
<script>
        let viewBtn = document.getElementsByClassName('btn');
     document.addEventListener('DOMContentLoaded', function() {
    	 for(let i=0;i<viewBtn.length;i++){
    		 
            let splide = new Splide('.splide'+i, {
                'cover': true,
                'fixedHeight': '70%',
                'width': '100%',
                'rewind': true,
                'type': 'fade',
                'speed': 1600, // 切換時的秒數
                'autoplay': true, // 自動播放
                'interval': 3500, // 間格秒數(ms)
                'arrows': false,
                'pagination': false,
            })
            splide.on('autoplay:playing', function(rate) {
                // console.log( rate ); // 0-1
            });
            splide.mount();
    	 }
        });
        // 燈箱開關
        let closeBtn = document.getElementsByClassName('close');
        let lightbox = document.getElementsByClassName('itemLightbox');
        let bg = document.getElementsByClassName('itemLightboxBg');
        for (let i = 0; i < viewBtn.length; i++) { lightbox[i].style.display = 'none';
            bg[i].style.display = 'none';
            window.addEventListener('load', function() { viewBtn[i].addEventListener('click', function() { lightbox[i].style.display = '';
                    bg[i].style.display = ''; });
                closeBtn[i].addEventListener('click', function() { lightbox[i].style.display = 'none';
                    bg[i].style.display = 'none'; }); }); }
    </script>
</body>
</html>