<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2018/7/30
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Do Homework</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="../../css/theme.css" media="all" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="../../css/main.css" />
    <link rel="stylesheet" type="text/css" href="../../css/header.css" />
    <link rel="stylesheet" type="text/css" href="../../css/footer.css" />
    <link rel="stylesheet" type="text/css" href="../../css/homework.css" />
</head>
<body>
<%@include file="header.jsp"%>
<div class="main container">
    <div class="homework-block">
        <h3>${sessionScope.aHomework.name}</h3>
        <dl class="row add-info">
            <dt class="col-sm-3">Course Name: </dt>
            <dd class="col-sm-9">${sessionScope.courseName}</dd>
            <dt class="col-sm-3">Deadline: </dt>
            <dd class="col-sm-9">${sessionScope.aHomework.deadLine}</dd>
        </dl>
        <hr>
        <h4>【Question】</h4>
        <p id="questionDes">${sessionScope.aHomework.description}</p>
        <hr>
        <%if (session.getAttribute("canDo").equals(1)){%>
        <form action="/homework" method="post">
            <label for="myAnswer">Please answer here:</label><br>
            <input name="hidden" style="display: none" value="${param.id}">
            <textarea id="myAnswer" cols="120" rows="20" name="myAnswer"></textarea><br>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        <%}else {%>
        <h4 style="color: red">**You can't do the homework because you have missed the deadline!</h4>
        <%}%>
    </div>
</div>

<%@include file="footer.jsp"%>
</body>
<script type="text/javascript" src="../../js/jQuery.js"></script>
<script src="../../js/popper.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</html>