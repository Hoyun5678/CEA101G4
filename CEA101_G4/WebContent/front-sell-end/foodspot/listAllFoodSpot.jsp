<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.foodspot.model.*"%>
<%@ page import="com.sell.model.*"%>


<jsp:useBean id="fsSvc" scope="page" class="com.foodspot.model.FoodSpotService" />

<c:set var="sellVO" scope="page" value="${sellVO}"/>
<!-- //     FoodSpotService fsSvc = new FoodSpotService(); -->
<!-- //     List<FoodSpotVO> list = (List<FoodSpotVO>)request.getAttribute("fsVO1"); -->
<!-- //     pageContext.setAttribute("list",list); -->
 


<html>
<head>
<link rel="icon" type="image/gif" href="favicon.gif" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css">
<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/image/LOGO/tiger.png" />
<title>美食與景點管理</title>
<style>
	
	.content{
		width:1030px!important;
	}
	table img{
  		width:100px;
  		height:80px;
  	}
  	input.btn_mod {
  		border: 1px solid #ccd9d1;
  		border-radius:5%;
		background-color: #679186;
		color: #e6eced;
		padding: 5px 7px;
		font-size: 16px;
		cursor: pointer;
	}

  	input.btn_del {
  		border: 1px solid #ced3df;
  		border-radius:5%;
  		background-color:#677591;
  		color: #e6eced;
  		padding: 5px 7px;
  		font-size: 16px;
  		cursor: pointer;
	}	
  	button#add{
  		background-color:#cc907b;
  		color:white;
		font-size:16px;
		font-weight:300;
		padding:3px 5px 3px 5px;
	    float:right;
		margin:10px;
	}
</style>

<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js" > </script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/sweetalert2.all.min.js"></script> --%>
<!-- <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script> -->
</head>

  

<body>
<%@ include file="/front-sell-end/sellMemSideBar.jsp" %> 
<%@ include file="/front-sell-end/sellNavBar.jsp"%>

 <div class="content">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
</c:if>
    
<div class="main" style="margin-left: 255px;width: 1000px;"/>

<table class="table table-striped align-middle" id="tb" >
<div class="right">
<button type="button" class="btn" id="add" onclick="window.location.href='<%=request.getContextPath()%>/front-sell-end/foodspot/addFoodSpot.jsp'"><i class="fa fa-plus" style="padding-right:5px"></i>新增</button>
</div>
<thead>
	<tr id ="th">
		<th>美食與景點編號</th>
		<th>美食與景點名稱</th>
		<th>美食景點地址</th>
		<th>美食景點敘述</th>
		<th>美食景點圖片</th>
		<th>美食景點經度</th>
		<th>美食景點緯度</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	</thead>
	
	
<%-- 	<%@ include file="page1.file" %>  --%>
 <tbody>
 <c:forEach var="fsVO" items="${fsSvc.getOneBySell(sellVO.sellMemId)}">

		
		<tr class="td">
		<td>${fsVO.fas_id}</td>
		<td>${fsVO.fas_spot_name}</td>
		<td>${fsVO.fas_add}</td>
		<td>${fsVO.fas_des}</td>
		<td><img src="${pageContext.request.contextPath}/foodspot/foodspot.do?fas_id=${fsVO.fas_id}&action=getFSPhoto"></td>					
		<td>${fsVO.fas_longitud}</td>
		<td>${fsVO.fas_latitude}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/foodspot/foodspot.do" style="margin-bottom: 0px;">
			     <input class="btn_mod" type="submit" value="修改" id="btn_mod">
			     <input type="hidden" name="fas_id"  value="${fsVO.fas_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/foodspot/foodspot.do" style="margin-bottom: 0px;">
			     <input class="btn_del" type="submit" value="刪除" id="btn_del">
			     <input type="hidden" name="fas_id"  value="${fsVO.fas_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<%-- <%@ include file="page2.file" %> --%>
</div>
  <script type="text/javascript">
//      	 $('input#btn_del').on('click',function(){
    		
//      	 event.preventDefault();
     		 
//  		 Swal.fire({
//  			 title: '確定刪除嗎?',
//   			 text: "資料將一去不復返!",
//   			 icon: 'warning',
//  			 showCancelButton: true,
//  			 confirmButtonColor: '#3085d6',
//  			 cancelButtonColor: '#d33',
//              confirmButtonText: '確定!',
//              cancelButtonText:'取消!',
             
// 		 }).then((result) => {
// 	 		 if (result.isConfirmed) {
// 	    		Swal.fire(
// 	     		 '已成功刪除!'
// 	         	)
// 	  		}
//  		})
// 		});
    	
   	 </script> 
    
    	 
      
<script>
     $('#tb').DataTable({
     language: {
     "emptyTable": "無資料...",
     "processing": "處理中...",
     "loadingRecords": "載入中...",
     "lengthMenu": "每頁 _MENU_ 筆資料",
     "zeroRecords": "無搜尋結果",
     "info": "_START_ 至 _END_ / 共 _TOTAL_ 筆",
     "infoEmpty": "尚無資料",
     "infoFiltered": "(從 _MAX_ 筆資料過濾)",
     "infoPostFix": "",
     "search": "搜尋關鍵字:",
     "paginate": {
     "first": "首頁",
     "last": "末頁",
     "next": "下頁",
     "previous": "前頁"
     },
     "aria": {
     "sortAscending": ": 升冪",
     "sortDescending": ": 降冪"
     }
     }
     });

     </script>
</body>
</html>