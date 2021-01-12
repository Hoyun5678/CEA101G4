		$(document).ready(function() {
			var contextPath = $('[name="contextPath"]').val();
			var urlTarget = contextPath + "/sell/sell.do";
			
			$('#pwdForm').bootstrapValidator({
                fields: {
                	oriSellMemPwd: {
                        validators: {
                            notEmpty: {
                                message: '請輸入原始密碼'
                            },
                        }
                    },
                    sellMemPwd: {
                        validators: {
                            stringLength: {
                                min: 8,
                                max: 20,
                                message: '密碼長度限制為8~20碼'
                            },
                            regexp: {
                                regexp: /\w.*/,
                                message: '密碼欄位只能輸入英數字或下底線'
                            },
                            notEmpty: {
                                message: '請輸入新密碼'
                            },
                        }
                    },
                    sellMemPwdRe: {
                        validators: {
                            notEmpty: {
                                message: '請再次輸入新密碼'
                            },
                            identical: {
                                field: 'sellMemPwd',
                                message: '密碼確認欄位需與密碼一致'
                            }
                        },
                    },
                }
	        })
	        
			// reset sellMemPwdRe when password change			
			$("[name='sellMemPwd']").change(function() {
// 				$("#pwdForm").bootstrapValidator('updateStatus', 'sellMemPwd', 'INVALID');
				$("#pwdForm")
			    .data('bootstrapValidator')
			    .updateStatus('sellMemPwdRe', 'NOT_VALID', null)
			    .validateField('sellMemPwdRe');
			})
			
			// valid form data again before submit
//			$("button").click(function(e) {
//				e.preventDefault();
//				if($("#pwdForm").data('bootstrapValidator').isValid()) {
//					// submit
//					
//					$.ajax({
//						url: urlTarget,
//						type: "POST",
//						data: $("#pwdForm").serialize(),
//						success: function(data) {
//							if(data.trim()) {
//								console.log("t" + data);
//								$('#errorMsgs').html(data);
//								 
//							} else {
//								console.log("f" + data);
//			                    swal({
//			  						title: "Good job!",
//			  						text: "修改密碼成功!",
//			  						icon: "success",
//								});
//			                    e.preventDefault();
//							}
//						}
//					});
//					
//				}
//			})
		})