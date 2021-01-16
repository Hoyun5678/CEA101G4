<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">

 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" 
 integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
 
 <link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/back-end/back-index-sidebar.css">
	
	
</head>
<body>
<div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <div id="sidebarspace">
                    <a href="<%=request.getContextPath()%>/back-end/back-index.jsp" title="">
                        <img id="leftImg" src="<%=request.getContextPath()%>/image/LOGO/supergoing.png" alt="">
                    </a>
                </div>
                <span>首頁<br>
                    <a href="<%=request.getContextPath()%>/back-end/faq/listAllFAQ.jsp">
                        FAQ
                    </a>
                    <a href="#">
               		         評論審核
                    </a>
                    <a href="<%=request.getContextPath()%>/back-end/news/listAllNews.jsp">
                 	       最新消息管理
                    </a>
                </span>
                <span>特產<br>
                    <a href="<%=request.getContextPath()%>/back-end/souvenir/select_page.jsp">
                    	    一般特產
                    </a>
                    <a href="#">
                      	  促銷特產
                    </a>
                    <a href="<%=request.getContextPath()%>/back-end/souvenir_order/listAllSouvenirOrder.jsp">
                     	   特產訂單
                    </a>
                </span>
                <span>帳號管理<br>
                    <a href="#">
                    	    一般會員
                    </a>
                    <a href="#">
               		         民宿廠商
                    </a>
                    <a href="<%=request.getContextPath()%>/back-end/emp/listAllEmp.jsp">
              		          後台員工
                    </a>
                </span>
                <span>其他<br>
                    <a href="#">
                    	    排程器
                    </a>
                    <a href="#">
                     	   聊聊管理
                    </a>
                    <a href="<%=request.getContextPath()%>/back-end/replyreport/back_select.jsp">
                    	    審核評論檢舉
                    </a>
                </span>
            </ul>
            <c:if test="${not empty sessionScope.empVO}">
            <h4 id="login">${empVO.emp_name}<a id="loginout" href=" <%=request.getContextPath()%>/emp/emp.do?action=emp_log_out">登出</h4></a>
            </c:if>
            <c:if test="${empty sessionScope.empVO}">
            <a href="<%=request.getContextPath()%>/back-end/emp/empLogin.jsp" id="login"> 員工登入 </a>
            </c:if>
        </div>
</body>
</html>