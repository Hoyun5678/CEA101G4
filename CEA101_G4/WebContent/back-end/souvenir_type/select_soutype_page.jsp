<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM SouType: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>SouvenirType: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for SouvenirType: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/souvenir_type/listAllSouType.jsp'>List</a> all SouvenirType.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/souvenir_type/SouvenirTypeServlet" >
        <b>輸入特產種類編號 (如ST001):</b>
        <input type="text" name="sou_type_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="soutSvc" scope="page" class="com.souvenir_type.model.SouvenirTypeService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/souvenir_type/SouvenirTypeServlet" >
       <b>選擇特產種類:</b>
       <select size="1" name="sou_type_id">
         <c:forEach var="soutVO" items="${soutSvc.all}" > 
          <option value="${soutVO.sou_type_id}">${soutVO.sou_type_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/souvenir_type/SouvenirTypeServlet" >
       <b>選擇特產種類名稱:</b>
       <select size="1" name="sou_type_id">
         <c:forEach var="soutVO" items="${soutSvc.all}" > 
          <option value="${soutVO.sou_type_id}">${soutVO.sou_type_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>特產管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/souvenir_type/addSouType.jsp'>Add</a> a new SouvenirType.</li>
</ul>

</body>
</html>