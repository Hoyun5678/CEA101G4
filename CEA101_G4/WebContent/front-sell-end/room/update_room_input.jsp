<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%
	RoomVO roomVO = (RoomVO) request.getAttribute("roomVO"); 
	//EmpServlet.java (Concroller) 存入req的roomVO物件 (包括幫忙取出的roomVO, 也包括輸入資料錯誤時的roomVO物件)
%>
<html>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <title>房間資料修改</title>
    <style>
	    button {
	        float: right;
	    }
	    .help-block {
	    	color: #E60000;
	    	font-weight: bold;
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
		                <h2>房間資料修改</h2>
		                <hr>
		            </div>
                    <form class="form-horizontal" method="POST" action="<%=request.getContextPath()%>/room/room.do">
                    	<div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">房間名稱:</label>
                            </div>                    	
                        	<div class="col-5 d-inline-block">
                            	<input
                                	type="text"
                                    class="form-control"
                                    name="roomName"
                                    value="<%=roomVO.getRoomName()%>"
                                />
                            </div>
						</div>
                        <div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">民宿價格 /晚:</label>
                            </div>
                            <div class="col-5 d-inline-block">
                                <input
                                    type="text"
                                    class="form-control"
                                    id="roomPrice"
                                    name="roomPrice"
                                    value="<%=roomVO.getRoomPrice()%>"
                                />
                            </div>
                        </div>
                        <div class="form-group">
                        	<div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">可容納人數:</label>
                            </div>
                            <div class="col-5 d-inline-block">
                                <input
                                    type="text"
                                    class="form-control"
                                    id="roomCapacity"
                                    name="roomCapacity"
                                    value="<%=roomVO.getRoomCapacity()%>"
                                />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">民宿介紹:</label>
                            </div>
                            <div class="col-5 d-inline-block">
                                <textarea
                                    class="form-control"
                                    name="roomDes"
                                    placeholder="寫一些民宿房間的特色介紹~"
                                ><%=(roomVO.getRoomDes()==null) ? "": roomVO.getRoomDes()%></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-4 d-inline-block">
                            	<label class="d-flex justify-content-end">民宿地址:</label>
                            </div>                        
                            <div class="col-5 d-inline-block">
                                <input type="radio" id="roomSta0" name="roomStatus" value="0" <%=(roomVO.getRoomStatus()==0) ? "checked" :""%> >
		                        <label for="roomSta0">下架   </label>
		                        <input type="radio" id="roomSta1" name="roomStatus" value="1" <%=(roomVO.getRoomStatus()==1) ? "checked" :""%> >
		                        <label for="roomSta1">上架</label></td>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-10">
								<input type="hidden" name="sellMemId" value="${sellVO.sellMemId}">
								<input type="hidden" name="roomId" value="${roomVO.roomId}">
								<input type="hidden" name="action" value="update">
                                <button type="submit" class="btn btn-primary">送出修改</button>
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
</body>

</html>