<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sell.model.*"%>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
	<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/image/LOGO/tiger.png" />
	<title>民宿會員基本資料</title>
	<style>
		button {
            float: right;
            margin: 10px;
        }
	</style>
</head>
<body>
	<div id="viewport">
	    <%@ include file="/front-sell-end/sellMemSideBar.jsp"%>
	    <div id="content">
	        <%@ include file="/front-sell-end/sellNavBar.jsp"%>
			<div class="container-fluid" style="padding: 0;">
				<div class="container mt-4">
					<div class="col-9 offset-1">
		                <h2>民宿會員基本資料</h2>
		                <hr>
		            </div>
                	<form class="form-horizontal" method="POST"
                    	action="<%=request.getContextPath()%>/front-sell-end/sell/updateSellInfo.jsp"
                        >
                    	<div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">民宿會員姓名:</label>
                            </div>                    	
                        	<div class="col-5 d-inline-block">
                            	<input
                                	type="text"
                                    readonly
                                    class="form-control"
                                    id="sellMemName"
                                    name="sellMemName"
                                    value="${sellVO.sellMemName}"
                                />
                            </div>
						</div>
                        <div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">手機號碼:</label>
                            </div>
                            <div class="col-5 d-inline-block">
                                <input
                                    type="text"
                                    readonly
                                    class="form-control"
                                    id="sellMemTel"
                                    name="sellMemTel"
                                    value="${sellVO.sellMemTel}"
                                />
                            </div>
                        </div>
                        <div class="form-group">
                        	<div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">E-mail:</label>
                            </div>
                            <div class="col-5 d-inline-block">
                                <input
                                    type="email"
                                    readonly
                                    class="form-control"
                                    id="sellMemMail"
                                    name="sellMemMail"
                                    value="${sellVO.sellMemMail}"
                                />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">民宿名稱:</label>
                            </div>
                            <div class="col-5 d-inline-block">
                                <input
                                    type="text"
                                    readonly
                                    class="form-control"
                                    id="sellRoomName"
                                    name="sellRoomName"
                                    value="${sellVO.sellRoomName}"
                                />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">民宿地址:</label>
                            </div>                        
                            <div class="col-5 d-inline-block">
                                <input
                                    type="text"
                                    readonly
                                    class="form-control"
                                    id="sellMemAddress"
                                    name="sellMemAddress"
                                    value="${sellVO.sellMemAddress}"
                                />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-10">
                                <input type="hidden" name="sellMemId" value="${sellVO.sellMemId}" />
                                <input type="hidden" name="action" value="updateInfo" />
                                <button type="submit" class="btn btn-secondary">修改基本資料</button>
                                <button type="button" class="btn btn-secondary" id="editPwdBtn">修改密碼</button>
                            </div>
                        </div>
                    </form>
                    <div class="row">
						<c:if test="${not empty errorMsgs}">
							<%-- 錯誤表列 from Servlet --%>
							<div class="alert alert-danger col-lg-6 col-lg-offset-1" role="alert" id="titleAndError">
								<font style="color:red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color:red">${message}</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>
					</div>
                </div>
			</div>
		</div>
	</div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
        <script type="text/javascript">
    	$(document).ready(function() {
    		var contextPath = '<%=request.getContextPath()%>';
			$('#editPwdBtn').click(function() {
				window.location.replace(contextPath + '/front-sell-end/sell/editSellPwd.jsp');
			})
    	})

    </script>

</body>
</html>