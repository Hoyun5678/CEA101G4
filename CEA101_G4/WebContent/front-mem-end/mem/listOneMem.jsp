<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<html>
<head>
<link rel="Shortcut Icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/image/LOGO/tiger.png" />
<title>會員資料</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/front-mem-end/listOneMem.css">
<style>
#logo{
    margin-top: -25px;
}
</style>

</head>
<body bgcolor='white'>

	<%@include file="/front-mem-end/bar.jsp"%>


	<div>
		<%@include file="/front-mem-end/mem/update_mem_pwd.jsp"%>
	</div>
	

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/member/member.do" name="form1">
		<table>
			 <div class="form-group">
            <div class="col-4 d-inline-block">
                <label class="d-flex justify-content-end">會員編號:</label>
            </div>
            <div class="col-5 d-inline-block">
                <input type="text" readonly class="form-control" id="mem_id" name="mem_id" value="${memVO.mem_id}" />
            </div>
        </div>
        <div class="form-group">
            <div class="col-4 d-inline-block">
                <label class="d-flex justify-content-end">會員帳號:</label>
            </div>
            <div class="col-5 d-inline-block">
                <input type="text" readonly class="form-control" id="mem_account" name="mem_account" value="${memVO.mem_id}" />
            </div>
        </div>
        <div class="form-group">
            <div class="col-4 d-inline-block">
                <label class="d-flex justify-content-end">會員姓名:</label>
            </div>
            <div class="col-5 d-inline-block">
                <input type="text" readonly class="form-control can_change" id="mem_name" name="mem_name" value="${memVO.mem_name}" />
            </div>
        </div>
        <div class="form-group">
            <div class="col-4 d-inline-block">
                <label class="d-flex justify-content-end">會員生日:</label>
            </div>
            <div class="col-5 d-inline-block">
                <input type="text" readonly class="form-control" id="mem_birth" name="mem_birth" value="${memVO.mem_birth}" />
            </div>
        </div>
        <div class="form-group">
            <div class="col-4 d-inline-block">
                <label class="d-flex justify-content-end">會員電話:</label>
            </div>
            <div class="col-5 d-inline-block">
                <input type="text" readonly class="form-control can_change" id="mem_tel" name="mem_tel" value="${memVO.mem_tel}" />
            </div>
        </div>
        <div class="form-group">
            <div class="col-4 d-inline-block">
                <label class="d-flex justify-content-end">會員地址:</label>
            </div>
            <div class="col-5 d-inline-block">
                <input type="text" readonly class="form-control can_change" id="mem_address" name="mem_address" value="${memVO.mem_address}" />
            </div>
        </div>
        <div class="form-group">
            <div class="col-4 d-inline-block">
                <label class="d-flex justify-content-end">會員信箱:</label>
            </div>
            <div class="col-5 d-inline-block">
                <input type="text" readonly class="form-control" id="mem_mail" name="mem_mail" value="${memVO.mem_mail}" />
            </div>
        </div>
        <div class="form-group">
            <div class="col-4 d-inline-block">
                <label class="d-flex justify-content-end">會員性別:</label>
            </div>
            <div class="col-5 d-inline-block">
                <input type="text" readonly class="form-control" id="mem_gender"  value="${memVO.mem_gender eq 0?"男":"女"}" />
                <input type="hidden" value="memVO.mem_gender" name="mem_gender"/>
            </div>
        </div>

		</table>
		<div class="form-group">
        <div class="col-10" style="text-align: center;">
            <input type="hidden" name="action" value="update" />
            <button type="button" class="btn btn-warning" id="mem_change" style="margin-left: 70px;">
                修改<span class="glyphicon glyphicon-send"></span>
            </button>
            <button type="submit" class="btn btn-warning" id="mem_submit" style="display:none;margin-left: 70px;">
                送出<span class="glyphicon glyphicon-send"></span>
            </button>
            <button type="button" class="btn btn-warning" id="pwd_light_box">
                修改密碼<span class="glyphicon glyphicon-send"></span>
            </button>
        </div>
    </div>
	</FORM>
	
					<div class="row">
						<c:if test="${not empty errorMsgs}">
							<%-- 錯誤表列 from Servlet --%>
							<div class="alert alert-danger col-lg-3 col-lg-offset-1" role="alert" id="titleAndError">
								<font style="color:red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color:red">${message}</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>
					</div>

</body>
<script
		src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-157cd5b220a5c80d4ff8e0e70ac069bffd87a61252088146915e8726e5d9f147.js"></script>
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script
		src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>



	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js'></script>
<script>
        var urlTarget = "<%=request.getContextPath()%>/member/member.do";
        $(document).ready(function() {
        	$("#mem_change").click(function(){
        		$("#mem_change").css("display","none");
        		$(".can_change").removeAttr("readonly");
        		$("#mem_submit").css("display","inline");
        	});
            $("#pwd_light_box").click(function() {
                $("#fix_pwd").css("display", "block");
            });

            $('#contact_form_updatepwd').bootstrapValidator({
                    // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },

                    fields: {
                        mem_pwd_old: {
                            trigger: "blur",
                            validators: {
                                notEmpty: {
                                    message: '請輸入舊密碼'
                                },
                                remote: {
                                    url: urlTarget,
                                    delay: 2000,
                                    type: "POST",
                                    data: {
                                        action: "get_pwd_check",
                                        mem_pwd_old: $(
                                                this)
                                            .val(),
                                        mem_account: $(
                                                "#mem_account_1")
                                            .val()
                                    },
                                    message: '舊密碼錯誤',

                                },

                            }
                        },

                        mem_pwd: {
                            validators: {
                                stringLength: {
                                    min: 8
                                },

                                notEmpty: {
                                    message: '請輸入密碼，有效長度至少為8'
                                },
                                regexp: {
                                    regexp: /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,}$/,
                                    message: '密碼需要大小寫字母與數字組合'
                                },
                            }
                        },
                        mem_pwd_re: {
                            validators: {
                                stringLength: {
                                    min: 8
                                },

                                notEmpty: {
                                    message: '請輸入密碼，有效長度至少為8'
                                },
                                identical: {
                                    field: 'mem_pwd',
                                    message: '密碼確認欄位需與密碼一致'
                                }
                            }
                        },
                    }
                })
                .

            on(
                'success.form.bv',
                function(e) {
                    $(
                            '#success_message')
                        .slideDown({
                                opacity: "show"
                            },
                            "slow"); // Do something ...
                    $(
                            '#contact_form')
                        .data(
                            'bootstrapValidator')
                        .resetForm();

                    // Prevent form submission
                    e
                        .preventDefault();

                    // Get the form instance
                    var $form = $(e.target);

                    // Get the BootstrapValidator instance
                    var bv = $form
                        .data('bootstrapValidator');

                    // Use Ajax to submit form data
                    $
                        .post(
                            $form
                            .attr('action'),
                            $form
                            .serialize(),
                            function(
                                result) {
                                console
                                    .log(result);
                            },
                            'json');
                });

        })
</script>
</html>