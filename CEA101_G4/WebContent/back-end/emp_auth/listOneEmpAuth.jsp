<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.emp_auth.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  EmpAuthVO empAuthVO = (EmpAuthVO) request.getAttribute("empAuthVO"); //EmpAuthServlet.java(Concroller), 存入req的empAuthVO物件
%>
<jsp:useBean id="authorizationSvc" scope="page" class="com.authorization.model.AuthorizationService" />
<html>
<head>
<title>員工資料 - listOneEmpAuth.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneEmpAuth.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/emp_auth/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr><th>員工編號</th>
		<th>員工權限</th>
	</tr>
	<tr>
		<td>${empAuthVO.emp_id}</td>
		<td>${authorizationSvc.getOneAuthorization(empAuthVO.func_id).func_name}</td>
	
	</tr>
</table>

</body>
</html>