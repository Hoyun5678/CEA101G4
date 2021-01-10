<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodspot.model.*"%>
<%@ page import="com.sell.model.*"%>   
<% SellVO sellVO=(SellVO)session.getAttribute("sellVO"); %>
<%
  FoodSpotVO fsVO = (FoodSpotVO) request.getAttribute("fsVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%--<%= fsVO==null %>--${fsVO.fas_id}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/front-sell-end/front-sell-food.css">
<title>修改美食景點 </title>
</head>

<body bgcolor='white'>
<%@ include file="/front-sell-end/sellMemSideBar.jsp" %>

<%-- 錯誤表列 --%>
<div>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</div>
<div class="container">
<h3>修改美食與景點</h3>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/foodspot/foodspot.do" name="form1" enctype="multipart/form-data">
<div class="row">
	<div class="col-4">
       <span>美食景點編號:</span>
    </div>
    <div class="col-8" style="padding-top:10px;padding-left: 14px;">   
           <%=fsVO.getFas_id()%>
    </div>
    </div>
		
	<div class="row">
	<div class="col-4">
       <span>民宿會員編號:</span>
    </div>
    <div class="col-8" style="padding-top:10px;padding-left: 14px;">   
       ${fsVO.sell_mem_id}
           <input type="hidden" name="sell_mem_id" 
			 value="<%= fsVO.getSell_mem_id()%>" />
    </div>
    </div>

<div class="row">
	<div class="col-4">
       <label for="username" class="text-small-uppercase">美食與景點名稱:</label>
    </div>
    <div class="col-8">   
           <input class="text-body" id="fsname" type="TEXT" name="fas_spot_name" size="45" required
			 value="<%= (fsVO==null)? "路邊攤" : fsVO.getFas_spot_name()%>" />
    </div>
 </div> 
 <div class="row">  
    <div class="col-4">
           <label for="address" class="text-small-uppercase">美食與景點地址:</label>
    </div>
    <div class="col-8">
     	   <input class="text-body" id="fsaddress" type="TEXT" name="fas_add" size="45" required 
			 value="<%= (fsVO==null)? "桃園市中壢區提把米路" : fsVO.getFas_add()%>" />
    </div>
 </div>
 <div class="row">
    <div class="col-4">
          <label for="describe" class="text-small-uppercase">美食景點敘述:</label>
    </div>
    <div class="col-8">
          <input class="text-body" id="fsdes" type="TEXT" name="fas_des" size="45"  
             value="<%= (fsVO==null)? "有吃又有拿" : fsVO.getFas_des()%>" />
    </div>
 </div>
 <div class="row">
    <div class="col-4">
    	  <label for="describe" class="text-small-uppercase">美食景點照片:</label>
    </div>
    <div class="col-8">	
		
 			 <input type="file" name="fas_photo" onchange="preview()" accept="image/*" value="${fsVO.fas_photo}">
 			 <img id="frame" src="${pageContext.request.contextPath}/foodspot/foodspot.do?fas_id=${fsVO.fas_id}&action=getFSPhoto" width="100px" height="100px"/>
		
	</div>
 </div>
	<div class="row">
		<div class="col-4">
           <label for="fslong" class="text-small-uppercase">美食景點經度:</label>
   		</div>
    	<div class="col-8">
           <input class="text-body" id="fslong" type="TEXT" name="fas_longitud" size="45"  
             value="<%= (fsVO==null)? "24.12345678" : fsVO.getFas_longitud()%>" />
    	</div>
    </div>
	<div class="row">
		<div class="col-4">
          <label for="fslati" class="text-small-uppercase">美食景點緯度:</label>
   		</div>
    	<div class="col-8">
           <input class="text-body" id="fslati" type="TEXT" name="fas_latitude" size="45"  
             value="<%= (fsVO==null)? "121.3456788" : fsVO.getFas_latitude()%>" />
    	</div>
    </div>
	
	
	

<%-- 	<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${memSvc.all}"> --%>
<%-- 				<option value="${memVO.mem_id}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->


<div class="row">
<input type="hidden" name="action" value="update">
<input type="hidden" name="fas_id" value="<%=fsVO.getFas_id()%>">
<input type="submit" value="送出修改">
</div>
</FORM>
<script>

function preview() {
    frame.src=URL.createObjectURL(event.target.files[0]);
}
</script>
</body>
</html>