<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>民宿會員登入 / 註冊</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-sell-end/front-sell-sellMemLogin.css">
</head>

<body>
    <!-- partial:index.partial.html -->
    <div class="login-page">
    <input type="hidden" name="contextPath" value="<%=request.getContextPath()%>">
		<c:if test="${not empty errorMsgs}">
			<%-- 錯誤表列 from Servlet --%>
			<div class="alert alert-danger" role="alert" id="titleAndError">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>

        <div class="form">
            <form class="register-form" method="POST" action="<%=request.getContextPath()%>/front-sell-end/sellMemRegis.jsp">
                <input type="text" name="sellMemAccount" placeholder="username" />
                <input type="password" placeholder="password" name="sellMemPwd" />
                <input type="password" placeholder="confirm password" name="sellMemPwdRe" />
                <button type="button" class="createBtn">create</button>
                <p class="message">Already registered? <a href="#">Sign In</a></p>
            </form>
            
            <form class="login-form" method="POST" action="<%=request.getContextPath()%>/sell/sell.do">
                <input type="text" placeholder="username" name="sellMemAccount" />
                <input type="password" placeholder="password" name="sellMemPwd" />
                <input type="hidden" name="action" value="sellMemLogin" />
                <button type="submit">login</button>
                <p class="message">Not registered? <a href="#">Create an account</a></p>

            </form>

        </div>

    </div>
    <!-- partial -->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/front-sell-end/front-sell-sellMemLogin.js"></script>
</body>

</html>