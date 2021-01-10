<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>註冊成為民宿會員</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css'>
    <link href="https://cdn.bootcdn.net/ajax/libs/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css" />
    <style type="text/css">
        #success_message {
            display: none;
        }
        legend {
            margin: auto;
        }
    </style>
</head>

<body>
    <!-- partial:index.partial.html -->
    <div class="container">
        <form class="well form-horizontal" action="<%=request.getContextPath()%>/sell/sell.do" method="post" id="regisForm">
            <fieldset>
                <!-- Form Name -->
                <legend>註冊成為民宿會員</legend>
                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label">帳號</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input name="sellMemAccount" readonly placeholder="會員帳號" class="form-control" type="text" value='<%=request.getParameter("sellMemAccount") %>'>
                        </div>
                    </div>
                </div>
<!--                 Text input -->
<!--                 <div class="form-group"> -->
<!--                     <label class="col-md-4 control-label">密碼</label> -->
<!--                     <div class="col-md-4 inputGroupContainer"> -->
<!--                         <div class="input-group"> -->
<!--                             <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> -->
<!--                             <input name="sellMemPwd" id="sellMemPwd" placeholder="密碼" class="form-control" type="password"> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 Text input -->
<!--                 <div class="form-group"> -->
<!--                     <label class="col-md-4 control-label">密碼確認</label> -->
<!--                     <div class="col-md-4 inputGroupContainer"> -->
<!--                         <div class="input-group"> -->
<!--                             <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> -->
<!--                             <input name="sellMemPwdRe" placeholder="再次輸入密碼" class="form-control" type="password"> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label">會員姓名</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                            <input name="sellMemName" placeholder="會員姓名" class="form-control" type="text">
                        </div>
                    </div>
                </div>
                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label">生日</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                            <input name="sellMemBirth" placeholder="請選擇生日" class="form-control" type="text">
                        </div>
                    </div>
                </div>
                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label">手機</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
                            <input name="sellMemTel" placeholder="0912345678" class="form-control" type="text">
                        </div>
                    </div>
                </div>
                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label">E-Mail</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                            <input name="sellMemMail" placeholder="E-Mail Address" class="form-control" type="email">
                        </div>
                    </div>
                </div>
                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label">身分證</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                            <input name="sellMemIdNumber" placeholder="身分證字號" class="form-control" type="text">
                        </div>
                    </div>
                </div>
                <!-- radio checks -->
                <div class="form-group">
                    <label class="col-md-4 control-label">性別</label>
                    <div class="col-md-4">
                        <div class="radio">
                            <label>
                                <input type="radio" name="sellGender" value="0" /> 男
                            </label>&nbsp&nbsp
                            <label>
                                <input type="radio" name="sellGender" value="1" /> 女
                            </label>
                        </div>
                    </div>
                </div>
                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label">民宿名稱</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                            <input name="sellRoomName" placeholder="民宿名稱" class="form-control" type="text">
                        </div>
                    </div>
                </div>
                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label">民宿地址</label>
                    <div class="col-md-4 inputGroupContainer">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                            <input name="sellMemAddress" placeholder="民宿地址" class="form-control" type="text">
                        </div>
                    </div>
                </div>
                <!-- Success message -->
                <div class="alert alert-success" role="alert" id="success_message">Success <i class="glyphicon glyphicon-thumbs-up"></i> Thanks for contacting us, we will get back to you shortly.</div>
                <!-- Button -->
                <div class="form-group">
                    <label class="col-md-4 control-label"></label>
                    <div class="col-md-4">
                    	<input type="hidden" name="sellMemPwd" value='<%=request.getParameter("sellMemPwd") %>' />
                    	<input type="hidden" name="sellMemPwdRe" value='<%=request.getParameter("sellMemPwdRe") %>' />
                    	<input type="hidden" name="action" value="insert" />
                        <button type="submit" class="btn btn-warning" id="regisBtn">註冊 <span class="glyphicon glyphicon-send"></span></button>
                        <button type="button" class="btn testBtn">測試 <span class="glyphicon glyphicon-send"></span></button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
    <!-- /.container -->
    <!-- partial -->

    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script type="text/javascript">
    	var urlTarget = "<%=request.getContextPath()%>/sell/sell.do";
		$(document).ready(function() {
			
			var account = $("[name='sellMemAccount']");
		
			account.change(checkAccount)
			
			function checkAccount() {
    			$.ajax({
    				url: urlTarget,
    				type: "POST",
    				data: {
    					"action": "checkIsExist",
    					"colName": "SELL_MEM_ACCOUNT",
    					"checkValue" : account.val(),
    				},
    				success: function(data) {
    					var re = JSON.parse(data);
    					console.log("data.valid = " + re.valid);
    					if(re.valid === false){
    						// 帳號已存在
    						swal({
    							  title: "Try again",
    							  text: "帳號已存在，請重新輸入",
    							  icon: "warning",
    							  dangerMode: true,
    							})
    						
    						
    						$("#regisForm")
    						    .data('bootstrapValidator')
    						    .updateStatus('sellMemAccount', 'NOT_VALID', null)
    						    .validateField('sellMemAccount');
    						
    						account.val("");
    						account.focus();
    					}
    				}
				})
			}
			
			
// 			$('#regisBtn').click(function() {
// 				checkAccount();
// 				$('#regisForm').data('bootstrapValidator').valid;
// 				if($('#regisForm').data('bootstrapValidator').isValid()) {
// 	                swal({
// 						title: "Good job!",
// 						text: "註冊成功!",
// 						icon: "success",
// 					});
					
// 					$('#regisBtn').submit();
// 				}
				
				
// 			})
			
			
			$('.testBtn').click(function() {
// 				$("[name='sellMemAccount']").val("testAccount001");
// 				$("[name='sellMemPwd']").val("asdfasdf");
// 				$("[name='sellMemPwdRe']").val("asdfasdf");
				$("[name='sellMemName']").val("何昀");
				$("[name='sellMemBirth']").val("1999-11-11");
				$("[name='sellMemTel']").val("0911234567");
				$("[name='sellRoomName']").val("民宿的名字很重要嗎?");
				$("[name='sellMemAddress']").val("桃園市龍潭區梅龍一街22巷10號");
				$("[name='sellMemMail']").val("test@gmail.com");
				$("[name='sellMemIdNumber']").val("u109888222");
				$("[name='sellGender'][value='0']").prop("checked", true);
			});
			
			$.datetimepicker.setLocale('zh');
			$("[name='sellMemBirth']").datetimepicker({
		 	       theme: '',              //theme: 'dark',
			       timepicker:false,       //timepicker:true,
			       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
			       format:'Y-m-d',         //format:'Y-m-d H:i:s',
				   value: new Date(),
		           maxDate: '+1'  // 去除今日(不含)之後
		        });

            $('#regisForm').bootstrapValidator({

                    // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
//                         sellMemAccount: {
// //                         	threshold: 8,
// 							trigger: 'blur',
//                             validators: {
//                                 stringLength: {
//                                     min: 8,
//                                     max: 20,
//                                     message: '帳號長度限制為8~20碼'
//                                 },
//                                 notEmpty: {
//                                     message: '請輸入帳號'
//                                 },
//                                 remote: {
//                                     url: urlTarget,
//                                   delay: 1000,
//                                     type: "POST",
//                                     data: {
//                     					"action": "checkIsExist",
//                     					"colName": "SELL_MEM_ACCOUNT",
//                     					"checkValue" : account.val(),
//                     				},
//                                 }
//                             }
//                         },
//                         sellMemPwd: {
//                             validators: {
//                                 stringLength: {
//                                     min: 8,
//                                     max: 20,
//                                     message: '密碼長度限制為8~20碼'
//                                 },
//                                 notEmpty: {
//                                     message: '請輸入密碼'
//                                 },
//                             }
//                         },
//                         sellMemPwdRe: {
//                             validators: {
//                                 notEmpty: {
//                                     message: '請輸入密碼'
//                                 },
//                                 stringLength: {
//                                     min: 8,
//                                     max: 20,
//                                     message: '密碼長度限制為8~20碼'
//                                 },
//                                 identical: {
//                                     field: 'sellMemPwd',
//                                     message: '密碼確認欄位需與密碼一致'
//                                 }
//                             },
//                         },
                        sellMemName: {
                            validators: {
                                notEmpty: {
                                    message: '會員姓名請勿空白'
                                },
                            }
                        },
                        sellMemBirth: {
                            validators: {
                                notEmpty: {
                                    message: '請選擇生日'
                                },

                            }
                        },
                        sellMemTel: {
                            validators: {
                                notEmpty: {
                                    message: '請輸入手機號碼'
                                },

                            }
                        },
                        sellMemMail: {
                            validators: {
                                notEmpty: {
                                    message: '請輸入信箱'
                                },
                                emailAddress: {
                                    message: '請確認信箱格式'
                                }
                            }
                        },
                        sellMemIdNumber: {
                            validators: {
                                notEmpty: {
                                    message: '請輸入身分證字號'
                                },
                                regexp: {
                                    regexp: /^[a-zA-Z]\d{9}$/,
                                    message: '身分證格式有誤'
                                },
                            }
                        },
                        sellGender: {
                            validators: {
                                notEmpty: {
                                    message: '請選擇性別'
                                },
                            }
                        },
                        sellRoomName: {
                            validators: {
                                notEmpty: {
                                    message: '請輸入民宿名稱'
                                }
                            }
                        },
                        sellMemAddress: {
                            validators: {

                                notEmpty: {
                                    message: '請輸入民宿地址'
                                }
                            }
                        },

                    }
            })
//                 .on('success.form.bv', function(e) {
//                 	checkAccount();

// //                     $('#success_message').slideDown({ opacity: "show" }, "slow") // Do something ...
// //                     $('#regisForm').data('bootstrapValidator').resetForm();

//                     // Prevent form submission
// //                     e.preventDefault();

//                     // Get the form instance
                    
//                     if($('#regisForm').data('bootstrapValidator').isValid()) {
// 	                    swal({
// 	  						title: "Good job!",
// 	  						text: "註冊成功!",
// 	  						icon: "success",
// 						});
// 	                    $('regisBtn')
//                     }
//                     var $form = $(e.target);

//                     // Get the BootstrapValidator instance
//                     var bv = $form.data('bootstrapValidator');
                   
//                 });
			
		})
        
    </script>


</body>

</html>