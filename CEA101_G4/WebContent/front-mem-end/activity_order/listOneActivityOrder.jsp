<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.activity_product.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.activity_order.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
    //  SellVO sellVO = (SellVO) session.getAttribute("sellVO");
    //  String sell_mem_id=sellVO.getSellMemId();
    //  ActivityProductService actproSvc=new ActivityProductService();
    //  List<ActivityProductVO>list = 
    //          actproSvc.getAllbySellMemId(sell_mem_id);
    //  pageContext.setAttribute("list",list );
%>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.2.2/css/buttons.bootstrap.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-sell-end/front-sell-sideBar.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.min.css" />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
    <title>週期管理</title>
    <style type="text/css">
        .sidenav {
            grid-area: sidenav;
            background-color: #394263;
            top: 0;
            left: 0;
            background-color: #111;
            opacity: .9;
        }

        .grid-container {
            display: flex;
        }

        .main {
            grid-area: main;
            padding: 30px;
        }

        .footer {
            grid-area: footer;
            background-color: #648ca6;
        }

        .sidebar-menu {}

        .treeview {
            border-bottom: 1px solid;
            padding: 5px;
            text-align: left;
            text-decoration: none;
        }

        .sidebar-menu a {
            display: block;
            padding: 20px 30px;
            font-size: 14px;
            text-decoration: none;
            color: #ccc;
        }

        .treeview:hover {
            color: #fff;
            transition: 0.4s;
        }

        .slide a {
            color: #000;
            font-size: 36px;
        }

        #content {
            overflow: hidden;
        }

        example #contetn {
            align-items: center;
            text-align: center;
        }

        .btn_group {
            width: 120px;
            align-items: center;
            display: flex;
            line-height: 100px;
        }

        .act_content {
            max-width: 500px;
            max-height: 68px;
            text-overflow: ellipsis;
            display: block;
            overflow: hidden;
            border: 0;
            white-space: nowrap;
        }

        .table {
            font-size: 5px;
        }
        .list-photo {
            height: 50px;
            width: 50px;
        }
    </style>
</head>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color: red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color: red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<%--         <%@include file="/front-mem-end/mem/mem_center_page.jsp"%> --%>
<body>
    <div class="container-fluid">
        <div class="container">
            <div class="row">
                <div class="col calendarCol">
                    <main class="main">
                        <table id="example" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%" style="text-align: center;">
                            <thead>
                                <tr class="warning" style="line-height: 25px; text-align: center;">
                                    <th>活動訂單編號</th>
                                    <th>活動期別編號</th>
                                    <th>活動總金額</th>
                                    <th>活動訂單狀態</th>
                                    <th>付款狀態</th>
                                    <th>活動備註</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <jsp:useBean id="actordSvc" scope="page" class="com.activity_order.model.ActivityOrderService" />
                                <c:forEach var="actordVO" items="${actordSvc.getActOrderByMemId(memVO.mem_id)}">
                                    <form METHOD="post" ACTION="<%=request.getContextPath()%>/ActivityPeriod/ActivityPeriod.do">
                                        <tr style="line-height: 25px; text-align: center;">
                                            <td>${actordVO.act_order_id}</td>
                                            <td>${actordVO.act_period_id}</td>
                                            <td>${actordVO.act_sum_price}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${actordVO.act_order_status==0}">
                                                      	 待審核
                                                    </c:when>
                                                    <c:when test="${actordVO.act_order_status==1}">
                                                      	  已確認
                                                    </c:when>
                                                    <c:when test="${actordVO.act_order_status==2}">
                                                      	  已取消
                                                    </c:when>
                                                    <c:otherwise>
                                                       	 已完成
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${actordVO.act_payment_status==0}">
                                                        	未付款
                                                    </c:when>
                                                    <c:when test="${actordVO.act_payment_status==1}">
                                                       	 付款失敗
                                                    </c:when>
                                                    <c:when test="${actordVO.act_payment_status==2}">
                                                       	 已付款
                                                    </c:when>
                                                    <c:when test="${actordVO.act_payment_status==3}">
                                                    	    退款中
                                                    </c:when>
                                                    <c:otherwise>
                                                      	  已退款
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${actordVO.act_order_remarks}</td>
                                             <c:choose>
                                                    <c:when test="${actordVO.act_order_status==2}">
                                                      	<td></td>
                                                    </c:when>
                                                    <c:otherwise>
                                            <td class="btn_group" style="width: 100%;">
                                                <button type="submit" class="btn btn-light btn-xs dt-edit" style="margin-right: 16px;">取消訂單</button>
                                                <input type="hidden" name="act_period_id" value="${actperoVO.act_period_id}"> <input type="hidden" name="action" value="upDateActivityPeriodStatus">
                                                      	  
                                            </td>
                                                    </c:otherwise>
                                             </c:choose>
                                        </tr>
                                    </form>
                                </c:forEach>
                            </tbody>
                        </table>
                    </main>
                </div>
            </div>
        </div>
    </div>
</body>

</html>