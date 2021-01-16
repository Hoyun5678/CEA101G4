<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    NewsService newsSvc = new NewsService();
    List<NewsVO> list = newsSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>最新消息</title>
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
<body bgcolor='white'>
 <div id="wrapper">
       <%@ include file="/front-mem-end/bar.jsp"%>
        <div id="page-content-wrapper">
        <h3 align="center"><B>最新消息</B></h3>
<table class="table table-striped align-middle" id="tb" >

	<tr>
		<th>最新消息編號</th>
		<th>最新消息內容</th>
		<th>發布時間</th>	
	</tr>
<c:forEach var="newsVO" items="${list}">
		<tr>
			<td>${newsVO.news_id}</td>
			<td>${newsVO.news_content}</td>
			<td>${newsVO.news_date}</td>			
		</tr>
</c:forEach>
</table>



</div>
</div>

</body>
</html>