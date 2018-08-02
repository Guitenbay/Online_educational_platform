<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2018/7/31
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Do Homework</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/main.css" />
    <link rel="stylesheet" type="text/css" href="../../css/header.css" />
    <link rel="stylesheet" type="text/css" href="../../css/footer.css" />
    <link rel="stylesheet" type="text/css" href="../../css/check.css" />
</head>
<body>
<%@include file="header.jsp"%>
<div class="main container">
    <div class="check-block">
    <h3>The homework you have released:</h3>
    <hr>
    <div class="row">
        <c:forEach items="${sessionScope.homeworkList}" var="i">
        <div class="col-3">
            <div class="card index-center" style="width: 15rem;">
                <div class="card-body">
                    <h5 class="card-title">${i.key.name}</h5>
                    <p class="card-text">Course name:${i.value}</p>
                    <p class="card-text line-limit-length-for-chrome">${i.key.description}</p>
                    <p class="card-text">deadline:${i.key.deadLine}</p>
                    <a href="checkOne?id=${i.key.id}" class="btn btn-primary">Check it</a>
                    <a href="add?type=homework&id=${i.key.courseId}&homeworkId=${i.key.id}" class="btn btn-danger">Change it</a>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
    </div>

</div>

<%@include file="footer.jsp"%>
</body>
<script type="text/javascript" src="../../js/jQuery.js"></script>
<script src="../../js/popper.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</html>