<%--
  Created by IntelliJ IDEA.
  User: 22752
  Date: 2018/7/26
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/main.css" />
    <link rel="stylesheet" type="text/css" href="../../css/header.css" />
    <link rel="stylesheet" type="text/css" href="../../css/footer.css" />
    <link rel="stylesheet" type="text/css" href="../../css/login-register.css">
</head>
<body>
<%@include file="header.jsp"%>
<div class="main container center">
    <form class="lr-form" action="${pageContext.request.contextPath}/login" method="POST">
        <div class="lr-title">
            Login
            <a id="to-register" href="${pageContext.request.contextPath}/register"><img src="../../img/arrow-right.png"></a>
        </div>
        <div class="lr-line">
            <div class="lr-label">Username</div>
            <input class="lr-input" type="text" name="username" />
        </div>
        <div class="lr-line">
            <div class="lr-label">Password</div>
            <input class="lr-input" type="password" name="password" />
        </div>
        <div class="lr-line">
            <%  Object message = request.getAttribute("message");
                if (message != null) {%>
            <div class="alert alert-warning alert-dismissible fade show lr-center" role="alert">
                <%= message %>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%  }%>
        </div>
        <div class="lr-submit">
            <input class="lr-button" type="submit" value="Submit" />
        </div>
    </form>
</div>
<%@include file="footer.jsp"%>
</body>
<script type="text/javascript" src="../../js/jQuery.js"></script>
<script src="../../js/popper.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</html>
