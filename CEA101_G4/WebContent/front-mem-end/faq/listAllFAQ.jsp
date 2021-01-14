<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.faq.model.*"%>
<%
    FAQService faqSvc = new FAQService();
    List<FAQVO> list = faqSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>FAQ</title>


<style>
.main{
    width: 80%;
    height: 100%;
    margin: auto;}
</style>
<link href='//cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css' rel='stylesheet'></link>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js" > </script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script src='//cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js'></script>
</head>
<body>
<%@ include file="/front-mem-end/bar.jsp" %> 
            <h2>常見問答集</h2>  
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div>
<div class="main">
<div class="right">

</div>
<table class="table table-striped align-middle" id="tb" >
<thead>
	<tr id ="th">
		<th>問與答編號</th>
		<th>問答提問</th>
		<th>問答解答</th>

	</tr>
	</thead>
<%-- 	<%@ include file="page1.file" %>  --%>
	<tbody>
	<c:forEach var="faqVO" items="${list}">
		
		<tr>
		<td>${faqVO.faq_id}</td>
		<td>${faqVO.faq_question}</td>
		<td>${faqVO.faq_answer}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<%-- <%@ include file="page2.file" %> --%>
</div>
</div>
</div>
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