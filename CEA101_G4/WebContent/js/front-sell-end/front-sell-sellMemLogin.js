        $(document).ready(function() {
        	
        	var contextPath = $('[name="contextPath"]').val();
    		var urlTarget = contextPath + "/sell/sell.do";
    		$(document).click(function() {
    			$('.alert').fadeIn("slow").addClass('d-none');
    		})
        	
        	$('.message a').click(function(){
        		   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
        		});
        	
        	var checkColLength = function(colName) {
        		if($(colName).val().length < 1)
        			return false
        		return true
        	}
        	
        	$('.createBtn').click(function() {
        		if(!checkColLength(".register-form [name='sellMemAccount']")){
					swal({
						  title: "Try again",
						  text: "帳號為必填欄位",
						  icon: "warning",
						  dangerMode: true,
						})
        			return
        		};
        		if(!checkColLength(".register-form [name='sellMemPwd']")){
					swal({
						  title: "Try again",
						  text: "密碼為必填欄位",
						  icon: "warning",
						  dangerMode: true,
						})
        			return
        		};
        		if(!checkColLength(".register-form [name='sellMemPwdRe']")){
					swal({
						  title: "Try again",
						  text: "確認密碼為必填欄位",
						  icon: "warning",
						  dangerMode: true,
						})
        			return
        		};
        		if($(".register-form [name='sellMemPwd']").val() !== $(".register-form [name='sellMemPwdRe']").val()) {
					swal({
						  title: "Try again",
						  text: "密碼與確認密碼不一致，請重新輸入",
						  icon: "warning",
						  dangerMode: true,
						})
					console.log($(".register-form [name='sellMemPwd']").val());
					console.log($(".register-form [name='sellMemPwdRe']").val());
					return
        		}
//        		var pwdRegex = "\w{8,20}";
        		if($(".register-form [name='sellMemPwd']").val().match(/\w{8,20}/) < 1) {
					swal({
						  title: "Try again",
						  text: "密碼長度需介於8~20位數之間",
						  icon: "warning",
						  dangerMode: true,
						})
        			return
        		}
        		
        		$(".register-form").submit();
        	})
        	
        	
            $(".register-form [name='sellMemAccount']").blur(function() {
            	var input = $(".register-form [name='sellMemAccount']");
            	console.log("value = " + input.val());
            	console.log("input.val().length() = " + input.val().length);
            	if(input.val().length > 0) {
        			$.ajax({
        				url: urlTarget,
        				type: "POST",
        				data: {
        					"action": "checkIsExist",
        					"colName": "SELL_MEM_ACCOUNT",
        					"checkValue" : $(this).val(),
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
        					}
        				}
        			});
            	} else {
					swal({
						  title: "Try again",
						  text: "帳號為必填欄位",
						  icon: "warning",
						  dangerMode: true,
						})
            	}

            })

        })