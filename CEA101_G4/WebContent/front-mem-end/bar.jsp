<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>FontTopBar</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/0316f9a1d0.js" crossorigin="anonymous"></script>
    <script src="instastory.js"></script>
    <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js'></script>
    <link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.8.1/baguetteBox.min.css">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-mem-end/bar.css">
</head>
<body>
<div class=”container”>
        <nav class="navbar navbar-expand-lg bg-light navbar-light justify-content-center" id="topbar">
            <a class="navbar-brand" href="#">
                <img src="<%=request.getContextPath()%>/image/LOGO/logo.png" alt="Logo" id="logo">
            </a>
            <div class="ul">
                <ul class="navbar-nav">
                    <li class="nav-item" id="active">
                        <a class="nav-link" href="#">住宿</a>
                    </li>
                    <li class="nav-item" id="active">
                        <a class="nav-link" href="#">體驗</a>
                    </li>
                    <li class="nav-item" id="active">
                        <a class="nav-link" href="<%=request.getContextPath()%>/front-mem-end/souvenir/souvenir.jsp">伴手禮</a>
                    </li>
                    <li class="nav-item" id="active">
                        <a class="nav-link" href="#">加入平台</a>
                    </li>
                     <li class="nav-item" id="active">
                        <a class="nav-link" href="<%=request.getContextPath()%>/front-mem-end/souvenir/souvenircart.jsp">購物車</a>
                    </li>
                    <div id="account">
                        <a href="#">
                            <i class="fas fa-bars 4x" id="list"></i>
                            <i class="far fa-user-circle fa-2x" id="user"></i>
                        </a>
                    </div>
                </ul>
            </div>
        </nav>
    </div>

</body>
</html>