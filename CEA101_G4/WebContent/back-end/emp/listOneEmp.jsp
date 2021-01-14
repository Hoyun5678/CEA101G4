<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.emp.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
<div id="wrapper">
        <%@ include file="/back-end/back-index-sidebar.jsp"%>

        <div id="page-content-wrapper">	
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneEmp.jsp</h3>
	</td></tr>
</table>

<table>
	<tr>
		<th>員工編號</th>
		<th>員工帳號</th>
		<th>員工密碼</th>
		<th>員工姓名</th>
		<th>員工帳號狀態</th>
		<th>員工性別</th>
	</tr>
	<tr>
		<td>${empVO.emp_id}</td>
			<td>${empVO.emp_account}</td>
			<td>${empVO.emp_pwd}</td>
			<td>${empVO.emp_name}</td>
			<td><c:choose>
                                                    <c:when test="${empVO.emp_acc_status==0}">
                                                       	帳號未啟用
                                                    </c:when>
                                                    <c:when test="${empVO.emp_acc_status==1}">
                                                       	 帳號已啟用
                                                    </c:when>
                                                    <c:when test="${empVO.emp_acc_status==2}">
                                                       	 帳號已失效
                                                    </c:when>
                                                    
                                                </c:choose></td>
			<td>${empVO.emp_gender eq 0?"男":"女"}</td> 
	</tr>
</table>
</div>
</div>
</body>
</html>