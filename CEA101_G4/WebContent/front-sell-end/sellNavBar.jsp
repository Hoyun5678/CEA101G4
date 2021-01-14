<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sell.model.*"%> 
<% 
	SellVO sellVO = (SellVO) request.getAttribute("sellVO"); 
	pageContext.setAttribute("sellVO", sellVO);
%>
<nav class="navbar navbar-default bg-light justify-content-end">
    <div class="navbar-expand-lg d-flex">
    	<div class='d-flex'>
	       	<a class="nav-link" href="#">
				<i class="zmdi zmdi-notifications text-danger"></i>
			</a>
		</div>
		<div class='dropdown d-flex align-items-center sellMemNav'>
			<a class="dropdown-toggle" data-toggle='dropdown'>${ not empty sellVO ? sellVO.sellMemName: "User" }
        		<span class='caret'></span>
        	</a>
        	<ul class='dropdown-menu dropdown-menu-right'>
        		<li><a href='<%=request.getContextPath()%>/sell/sell.do?action=sellMemLogout'>登出</a></li>
        	</ul>
       	</div>
    </div>
</nav>