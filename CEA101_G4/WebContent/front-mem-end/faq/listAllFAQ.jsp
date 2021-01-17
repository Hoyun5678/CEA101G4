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
<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/image/LOGO/tiger.png" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>SuperGoing-FAQ</title>

<style>


#faq {
  max-width: 700px;
  margin: auto;
  padding: 0 15px;
  text-align: center;
}

section.faq {
  padding-top: 2em;
  padding-bottom: 3em;
}

#faq ul {
  text-align: left;
}
.transition, p, ul li i:before, ul li i:after {
  transition: all 0.3s;
}

#faq .no-select, #faq h2 {
  -webkit-tap-highlight-color: transparent;
  -webkit-touch-callout: none;
  -webkit-user-select: none;
  -khtml-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}


#faq h2 {
  color: #cc071e;
  font-family: 'Microsoft JhengHei';
  font-size: 16px;
  
  text-align: left;
  padding: 15px 15px 0;
  text-transform: none;
  font-weight: 600;
/*   letter-spacing: 1px; */
  display: block;
  margin: 0;
  cursor: pointer;
  transition: .2s;
}

#faq p {
  color: #333;
  text-align: left;
  font-family: 'hm_light', sans-serif;
  font-size: 14px;
  line-height: 1.45;
  position: relative;
  overflow: hidden;
  max-height: 250px;
  will-change: max-height;
  contain: layout;
  display: inline-block;
  opacity: 1;
  transform: translate(0, 0);
  margin-top: 5px;
  margin-bottom: 15px;
  padding: 0 50px 0 15px;
  transition: .3s opacity, .6s max-height;
  -webkit-hyphens: auto;
  -ms-hyphens: auto;
  hyphens: auto;
  z-index: 2;
}

#faq ul {
  list-style: none;
  perspective: 900;
  padding: 0;
  margin: 0;
}
#faq ul li {
  position: relative;
  overflow: hidden;
  padding: 0;
  margin: 0;
  /*padding-bottom: 4px;*/
  /*padding-top: 18px;*/
  background: #fff;
  box-shadow: 0 3px 10px -2px rgba(0,0,0,0.1);
  -webkit-tap-highlight-color: transparent;
}
#faq ul li + li {
  margin-top: 15px;
}
#faq ul li:last-of-type {
  padding-bottom: 0;
}
#faq ul li i {
  position: absolute;
  transform: translate(-6px, 0);
  margin-top: 28px;
  right: 15px;
}
#faq ul li i:before, ul li i:after {
  content: "";
  position: absolute;
  background-color: #cc071e;
  width: 3px;
  height: 9px;
}
#faq ul li i:before {
  transform: translate(-2px, 0) rotate(45deg);
}
#faq ul li i:after {
  transform: translate(2px, 0) rotate(-45deg);
}
#faq ul li input[type=checkbox] {
  position: absolute;
  cursor: pointer;
  width: 100%;
  height: 100%;
  z-index: 1;
  opacity: 0;
  touch-action: manipulation;
}
#faq ul li input[type=checkbox]:checked ~ h2 {
  color: #000;
}
#faq ul li input[type=checkbox]:checked ~ p {
  /*margin-top: 0;*/
  max-height: 0;
  transition: .3s;
  opacity: 0;
  /*transform: translate(0, 50%);*/
}
#faq ul li input[type=checkbox]:checked ~ i:before {
  transform: translate(2px, 0) rotate(45deg);
}
#faq ul li input[type=checkbox]:checked ~ i:after {
  transform: translate(-2px, 0) rotate(-45deg);
}


* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

html, body {
  height: 100%;
}

a,
a:visited,
a:focus,
a:active,
a:link {
  text-decoration: none;
  outline: 0;
}

a {
  color: currentColor;
  transition: .2s ease-in-out;
}


/* ul { */
/*   padding: 0; */
/*   list-style: none; */
/* } */

</style>
<link href='//cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css' rel='stylesheet'></link>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js" > </script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script src='//cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js'></script>
</head>
<body>
<%@ include file="/front-mem-end/bar.jsp" %> 
           
<div id="faq">
        <div style="font-weight:600;font-size:20px;color:#cc071e;">常見問答集</div>  
        <ul>
           
           <c:forEach var="faqVO" items="${list}">
            <li>
                <input type="checkbox" checked>
                <i></i>
              
                <h2><td>${faqVO.faq_question}</td></h2>
                <p>${faqVO.faq_answer}</p>
               
            </li>
             </c:forEach>
        </ul>
</div>



<!-- 		<th>問與答編號</th> -->
<!-- 		<th>問答提問</th> -->
<!-- 		<th>問答解答</th> -->

<%-- 	<c:forEach var="faqVO" items="${list}"> --%>
		
<!-- 		<tr> -->
<%-- 		<td>${faqVO.faq_id}</td> --%>
<%-- 		<td>${faqVO.faq_question}</td> --%>
<%-- 		<td>${faqVO.faq_answer}</td> --%>
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
	
 

</body>
</html>