<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
  <title>新增週期</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>
    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap-theme.min.css'>
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
  
        <div id="viewport">
            <%@ include file="/front-sell-end/sellMemSideBar.jsp"%>
            <div id="content">
                <%@ include file="/front-sell-end/sellNavBar.jsp"%>
                <div class="container-fluid">
                    <div class="container">
                        <h1>新增週期</h1>
                        <c:if test="${not empty errorMsgs}">
                            <div class="container">
                                <div class="alert alert-danger" role="alert">
                                    <strong>更新失敗，請修正以下錯誤:</strong>
                                    <ul>
                                        <c:forEach var="message" items="${errorMsgs}">
                                            <li>${message}
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </c:if>
                        <form class="form-horizontal" method="POST" action="<%=request.getContextPath()%>/ActivityPeriod/ActivityPeriod.do">
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="act_sign_start">活動報名開始:</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="act_sign_start" name="act_sign_start" value="${actperVO.act_sign_start}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="act_sign_end">活動報名結束:</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="act_sign_end" name="act_sign_end" value="${actperVO.act_sign_end}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="act_period_start">活動開始:</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="act_period_start" name="act_period_start" value="${actperVO.act_period_start}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="act_period_end">活動結束:</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="act_period_end" name="act_period_end" value="${actperVO.act_period_end}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="act_up_limit">活動人數上限:</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="act_up_limit" name="act_up_limit" value="${actperVO.act_up_limit}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="act_low_limit">活動人數下限:</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="act_low_limit" name="act_low_limit" value="${actperVO.act_low_limit}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="act_cur_price">此週期之價格:</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="act_cur_price" name="act_cur_price" value="${actperVO.act_cur_price}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-7">
                                    <input type="hidden" name="act_id" value="${actperVO.act_id}"> <input type="hidden" name="action" value="insert">
                                    <button type="submit" class="btn btn-default">送出新增</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
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





            $.datetimepicker.setLocale('zh');
            $("[name='act_sign_start']").datetimepicker({
                theme: '', //theme: 'dark',
                timepicker: false, //timepicker:true,
                step: 1, //step: 60 (這是timepicker的預設間隔60分鐘)
                format: 'Y-m-d', //format:'Y-m-d H:i:s',
                hideIfNoPrevNext: true,
                value: new Date(),
                //maxDate: "+3d", // 去除今日(不含)之後
                minDate: new Date(),
                onShow:function(ct ){
                	this.setOptions({
               maxDate:$("[name='act_sign_end']").val()?$("[name='act_sign_end']").val():false
               })
              },


            });
            $("[name='act_sign_end']").datetimepicker({
                theme: '', //theme: 'dark',
                timepicker: false, //timepicker:true,
                step: 1, //step: 60 (這是timepicker的預設間隔60分鐘)
                format: 'Y-m-d', //format:'Y-m-d H:i:s',
                value: $("[name='act_sign_start']").change(function(e) {
                    $("[name='act_sign_end']").val($("[name='act_sign_start']").val());
                }),
                onShow:function( ct ){
                	   this.setOptions({
                	    minDate:$("[name='act_sign_start']").val()?$("[name='act_sign_start']").val():false
                	   })
                	  },
            });
            $("[name='act_period_start']").datetimepicker({
                theme: '', //theme: 'dark',
                timepicker: true, //timepicker:true,
                step: 30, //step: 60 (這是timepicker的預設間隔60分鐘)
                format: 'Y-m-d H:i', //format:'Y-m-d H:i:s',
                value: $("[name='act_sign_end']").change(function(e) {
                    $("[name='act_period_start']").val($("[name='act_sign_end']").val());
                }),
                onShow:function( ct ){
             	   this.setOptions({
             	    minDate:$("[name='act_sign_end']").val()?$("[name='act_sign_end']").val():false
             	   })
             	  },
            });
            $("[name='act_period_end']").datetimepicker({
                theme: '', //theme: 'dark',
                timepicker: true, //timepicker:true,
                step: 30, //step: 60 (這是timepicker的預設間隔60分鐘)
                format: 'Y-m-d H:i', //format:'Y-m-d H:i:s',
                value: $("[name='act_period_start']").change(function(e) {
                    $("[name='act_period_end']").val($("[name='act_period_start']").val());
                }),
                onShow:function( ct ){
              	   this.setOptions({
              	    minDate: $("[name='act_period_start']").val()? $("[name='act_period_start']").val():false
              	   })
              	  },
            });


        })
        </script>
</body>

</html>