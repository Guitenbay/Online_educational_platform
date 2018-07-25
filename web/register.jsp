<%--
  Created by IntelliJ IDEA.
  User: 22752
  Date: 2018/7/22
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="css/register.css">
</head>
<body>
<div class="main">
    <form class="register-form" action="/register.do" method="POST">
        <div class="register-title">
            <a id="to-login" href="/login.html"><img src="imgs/arrow-left.png"></a>
            Register
        </div>
        <div class="register-line">
            <div class="label">Username</div>
            <input class="register-input" type="text" id="username" name="username" />
        </div>
        <div class="register-line">
            <div class="label">Password</div>
            <input class="register-input" type="password" id="password" name="password" />
        </div>
        <div class="register-line">
            <div id="message">
                <%--<%  if (request.getAttribute("message") != null) { %>--%>
                       <%= request.getAttribute("message") %>
                <%--<%    }--%>
                <%--%>--%>
            </div>
        </div>
        <div class="register-submit">
            <input class="register-button" onclick="register()" type="button" value="Submit" />
        </div>
    </form>
</div>
</body>
<script type="text/javascript" src="js/jQuery.js"></script>
<script type="text/javascript" src="js/register.js"></script>
</html>
