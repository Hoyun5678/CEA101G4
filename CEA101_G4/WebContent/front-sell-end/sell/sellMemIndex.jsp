<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.sell.model.*"%>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-sell-end/front-sell-sellMemIndex.css">
    
	<title>所有房間資料 - listAllRoom.jsp</title>
</head>
<body>
	<div id="viewport">
	    <%@ include file="/front-sell-end/sellMemSideBar.jsp"%>
	    <div id="content">
	        <%@ include file="/front-sell-end/sellNavBar.jsp"%>
			<div class="container-fluid" style="padding: 0;">
			
				<div class="container">
                    <div class="row">
                    	<div class="offset-1">
                        <div class="calendarDiv">
                            <form>
                            	<span><i class="zmdi zmdi-chevron-left"></i></span>
                            	
                                <select id="yearList" name="calendarYear"></select>
                                <select id="monthList" name="calendarMonth">
                                    <option value="0">JAN</option>
                                    <option value="1">FEB</option>
                                    <option value="2">MAR</option>
                                    <option value="3">APR</option>
                                    <option value="4">MAY</option>
                                    <option value="5">JUN</option>
                                    <option value="6">JUL</option>
                                    <option value="7">AUG</option>
                                    <option value="8">SEP</option>
                                    <option value="9">OCT</option>
                                    <option value="10">NOV</option>
                                    <option value="11">DEC</option>
                                </select>
                                <i class="zmdi zmdi-chevron-right"></i>
                            </form>
                            <table class="calendarBody">
                                <thead>
                                    <tr>
                                        <td>SUN</td>
                                        <td>MON</td>
                                        <td>TUE</td>
                                        <td>WED</td>
                                        <td>THU</td>
                                        <td>FRI</td>
                                        <td>SAT</td>
                                    </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                        </div>
                        
                        <div class="onSelectRoomOrder col-6">
                        	<div class="displaySelectedDate">
                        		<h4>預計Check in 列表</h4>
                        	</div>
                        	<div id="accordion">
                        	</div>
                    	</div>
                    </div>
                </div>
			</div>
		</div>
		<input type="hidden" name="contextPath" value="${pageContext.request.contextPath}" />
		<input type="hidden" id="sellMemId" name="sellMemId" value="${sellVO.sellMemId}" />
	</div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/front-sell-end/front-sell-sellMemIndex.js"></script>

    
</body>
</html>