<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodspot.model.*"%>
<%@ page import="com.sell.model.*"%>   
<% SellVO sellVO=(SellVO)session.getAttribute("sellVO"); %>
<%
FoodSpotVO fsVO = (FoodSpotVO) request.getAttribute("fsVO");
%>
<%-- <%= FoodSpotVO==null %>--${fsVO.fas_id}-- --%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/front-sell-end/front-sell-food.css">
<title>美食景點資料新增 </title>


</head>

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
<h3>新增美食與景點</h3>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/foodspot/foodspot.do" name="form1" enctype="multipart/form-data">
<div class="row">
	<div class="col-4">
       <label for="sellmemid" class="text-small-uppercase">民宿會員編號:</label>
    </div>
    <div class="col-8"style=" padding-top: 14px;padding-left: 10px;">   
    		${sellVO.sellMemId}
           <input type="hidden" name="sell_mem_id" value="${sellVO.getSellMemName()}" />
    </div>
    </div>

<div class="row">
	<div class="col-4">
       <label for="fsname" class="text-small-uppercase">美食與景點名稱:</label>
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
   		  <input type="file" id="file" name="fas_photo" accept="image/*" size="45"
				value="${fsVO.fas_photo}" onchange="loadImageFile(event)"/>
		   <img style="width:0px" id="preview" src="#"/>
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
<div class="row">
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增">
</div>
	</FORM>
	</div>

<script>

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#preview').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]); 
    }
}

$("#file").change(function() {
    readURL(this);
   	$("#preview").css("width", "100px");
});

</script>
</body>
</html>