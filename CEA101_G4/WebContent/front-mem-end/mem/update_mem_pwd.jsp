<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<!--  index -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" -->
<!-- 	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" -->
<!-- 	crossorigin="anonymous"> -->
<!--   <link rel="stylesheet" type="text/css" href="css/style.css"> -->
<!--   enroll -->
<!-- <link rel="apple-touch-icon" type="image/png" -->
<!-- 	href="https://cpwebassets.codepen.io/assets/favicon/apple-touch-icon-5ae1a0698dcc2402e9712f7d01ed509a57814f994c660df9f7a952f3060705ee.png" /> -->
<!-- <meta name="apple-mobile-web-app-title" content="CodePen"> -->
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/image/LOGO/tiger.png" />
<link rel="mask-icon" type=""
	href="https://cpwebassets.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg"
	color="#111" />
<title>修改密碼</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"
	type="text/javascript"></script>
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css'>
<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css'>
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

form{
height:300px;
width:600px;
margin:0px auto;
}
.form-group input{
width:200px;
}
#fix_pwd{
position:fixed;
display:none;
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
	<div class="container" id="fix_pwd">
		<form class="well form-horizontal" METHOD="post"
			ACTION="<%=request.getContextPath()%>/member/member.do" name="form1"
			id="contact_form_updatepwd" style="text-align: center;">
			
			<fieldset id="test">
				<!-- Form Name -->
				<legend align="center" id="enroll">修改密碼</legend>
				<!-- Text input-->

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label">舊密碼</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i> </i></span> <input name="mem_pwd_old"
								placeholder="密碼8~12碼" class="form-control" type="password">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">新密碼</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input name="mem_pwd"
								placeholder="再次輸入密碼" class="form-control" type="password">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">新密碼確認</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input name="mem_pwd_re"
								placeholder="再次輸入密碼" class="form-control" type="password">
						</div>
					</div>
				</div>
							<input id="mem_account_1" type="hidden" name="mem_account"
			value="${memVO.mem_account}" class="form-control">
							<input type="hidden" name="action" value="change_mem_pwd"/>
							<button type="submit" class="btn btn-info">
								修改密碼 <span class="glyphicon glyphicon-ok"></span>
							</button>
			</fieldset>
		</form>
	</div>
	<!-- /.container -->
<!-- 	<script -->
<!-- 		src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-157cd5b220a5c80d4ff8e0e70ac069bffd87a61252088146915e8726e5d9f147.js"></script> -->
<!-- 	<script -->
<!-- 		src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script> -->
<!-- 	<script -->
<!-- 		src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script> -->
<!-- 	<script -->
<!-- 		src='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script> -->



<!-- 	<script	src='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js'></script> -->
	<script id="rendered-js">
<%-- 	var urlTarget = "<%=request.getContextPath()%>/member/member.do"; --%>
// 		$(document)
// 				.ready(
// 						function() {

// 							//前端攔截=================================================================

// 							$('#contact_form')
// 									.bootstrapValidator(
// 											{
// 												// To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
// 												feedbackIcons : {
// 													valid : 'glyphicon glyphicon-ok',
// 													invalid : 'glyphicon glyphicon-remove',
// 													validating : 'glyphicon glyphicon-refresh'
// 												},

// 												fields : {
// 													mem_pwd_old : {
// 														trigger:"blur",
// 														validators : {

// 															notEmpty : {
// 																message : '請輸入舊密碼'
// 															},
// 															remote : {
// 																url : urlTarget,
// 																delay : 2000,
// 																type : "POST",
// 																data : {
// 																	action : "get_pwd_check",
// 																	mem_pwd_old : $(this).val(),
// 																	mem_account:$("#mem_account_1").val()
// 																},
// 																message : '舊密碼錯誤',
																
// 															},

// 														}
// 													},

// 													mem_pwd : {
// 														validators : {
// 															stringLength : {
// 																min : 8
// 															},

// 															notEmpty : {
// 																message : '請輸入密碼，有效長度至少為8'
// 															},
// 															regexp : {
// 																regexp : /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,}$/,
// 																message : '密碼需要大小寫字母與數字組合'
// 															},
// 														}
// 													},
// 													mem_pwd_re : {
// 														validators : {
// 															stringLength : {
// 																min : 8
// 															},

// 															notEmpty : {
// 																message : '請輸入密碼，有效長度至少為8'
// 															},
// 															identical : {
// 																field : 'mem_pwd',
// 																message : '密碼確認欄位需與密碼一致'
// 															}
// 														}
// 													},
// 												}
// 											})
// 									.

// 									on(
// 											'success.form.bv',
// 											function(e) {
// 												$('#success_message')
// 														.slideDown({
// 															opacity : "show"
// 														}, "slow"); // Do something ...
// 												$('#contact_form').data(
// 														'bootstrapValidator')
// 														.resetForm();

// 												// Prevent form submission
// 												e.preventDefault();

// 												// Get the form instance
// 												var $form = $(e.target);

// 												// Get the BootstrapValidator instance
// 												var bv = $form
// 														.data('bootstrapValidator');

// 												// Use Ajax to submit form data
// 												$
// 														.post(
// 																$form
// 																		.attr('action'),
// 																$form
// 																		.serialize(),
// 																function(result) {
// 																	console
// 																			.log(result);
// 																}, 'json');
// 											});

// 						})
// 		//# sourceURL=pen.js
</script> 
</body>

</html>