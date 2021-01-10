<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
	MemberVO memVO = (MemberVO) request.getAttribute("memVO");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<!--  index -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<!--   <link rel="stylesheet" type="text/css" href="css/style.css"> -->
<!--   enroll -->
<link rel="apple-touch-icon" type="image/png"
	href="https://cpwebassets.codepen.io/assets/favicon/apple-touch-icon-5ae1a0698dcc2402e9712f7d01ed509a57814f994c660df9f7a952f3060705ee.png" />
<meta name="apple-mobile-web-app-title" content="CodePen">
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/image/LOGO/tiger.png" />
<link rel="mask-icon" type=""
	href="https://cpwebassets.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg"
	color="#111" />
<title>登入會員</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"
	type="text/javascript"></script>
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css'>
<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css'>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
<style>
#success_message {
	display: none;
}

#enroll {
	font-size: 28px;
}

.container {
	border: 1px, solid;
}

.container:hover {
	opacity: 1;
}


#btn {
	text-align: center;
}

</style>
<script>
	window.console = window.console || function(t) {
	};
</script>
<script>
	if (document.location.search.match(/type=embed/gi)) {
		window.parent.postMessage("resize", "*");
	}
</script>
</head>


<body translate="no">
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<%@include file="/front-mem-end/mem/forget.jsp"%>

	<div class="container">
		<form class="well form-horizontal" METHOD="post"
			ACTION="<%=request.getContextPath()%>/member/member.do" name="form1"
			id="contact_form">
			<fieldset id="test">
				<!-- Form Name -->
				<legend align="center" id="enroll">會員登入</legend>
				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label">帳號</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon glyphicon-user"></i> </i></span> <input
								name="mem_account" placeholder="請輸入您的帳號" class="form-control"
								type="text">
						</div>
					</div>
				</div>
				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label">密碼</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i> </i></span> <input name="mem_pwd"
								placeholder="請輸入您的密碼" class="form-control" type="password">
						</div>
					</div>
				</div>
				<div class="form-group" style="text-align: center;">
						<label class="col-md-4 control-label"></label> 
						<input type="hidden" name="action" value="mem_login">
						<div class="col-md-4">
							<button type="submit" class="btn btn-warning">
								送出 <span class="glyphicon glyphicon-send"></span>
							</button>
							
							<a href="<%=request.getContextPath()%>/front-mem-end/mem/regis.jsp" class="btn btn-success">註冊一般會員 <i class="fas fa-user"></i></a>
							<button type="button" class="btn btn-info" id="mem_forget">
								忘記密碼 <i class="fas fa-grin-beam-sweat  fa-1x"></i>
							</button>
						</div>
					</div>
					

			</fieldset>
		</form>
	</div>


</body>
	<!-- /.container -->
	<script
		src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-157cd5b220a5c80d4ff8e0e70ac069bffd87a61252088146915e8726e5d9f147.js"></script>
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script
		src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>
		<script id="rendered-js">
	var urlTarget = "<%=request.getContextPath()%>/member/member.do";
		$(document).ready(function() {
			
			
			  $("#mem_forget").click(function() {
	                $("#forget_light_box").css("display", "block");
	                $("#a1").css("display","block");
	                
	            });
			  
			
				//前端攔截=================================================================
					
				

					
	
					$('#contact_form_forget').bootstrapValidator({
						// To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
						feedbackIcons : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},

						fields : {
							 mem_account: {
									trigger:"blur",
		                            validators: {
		                                stringLength: {
		                                    min: 8,
		                                    max: 20,
		                                    message: '帳號長度限制為8~20碼'
		                                },
		                                notEmpty: {
		                                    message: '請輸入帳號'
		                                },
		                            }
		                        },
							
							mem_birth : {
								validators : {
									 date: {
										   format: 'YYYY/MM/DD',
										   message: 'The format is YYYY/MM/DD'
										 },

									notEmpty : {
										message : '請輸入您的生日'
									}
								}
							},

							mem_mail : {
								
		                            validators: {
		                              
		                                notEmpty: {
		                                    message: '請輸入email'
		                                },

									emailAddress : {
										message : '請提供有效的Email'
									}
								}
							},

						}
					});

					
					
					
					
					
				})
</script>

</html>