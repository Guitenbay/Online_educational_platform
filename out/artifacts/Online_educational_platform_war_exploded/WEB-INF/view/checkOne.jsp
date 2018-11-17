<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2018/7/31
  Time: 17:34
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
    <link rel="stylesheet" type="text/css" href="../../css/check.css" />

</head>
<body>
<%@include file="header.jsp"%>
<div class="main container">
    <div class="row">
        <div class="col-9 check-block">
            <h2>The answers' list of the homework:</h2>
            <c:forEach items="${sessionScope.answerListMap}" var="i">
                <div class="card check-card" style="width: 100%;">
                    <div class="card-body">
                        <h4>His/Her answer:</h4>
                        <p class="card-title check-answer-font">${i.value.context}</p>
                        <hr>
                        <dl class="row add-info check-info-font">
                            <dt class="col-sm-3">Submit time: </dt>
                            <dd class="col-sm-9">${i.value.updateTime}</dd>
                            <dt class="col-sm-3">Student name: </dt>
                            <dd class="col-sm-9">${i.key}</dd>
                        </dl>
                        <hr>
                        <form action="/checkOne" method="post">
                            <c:if test="${i.value.score!=null}">
                                <dl class="row add-info check-info-font">
                                    <dt class="col-sm-3">His/her score now:</dt>
                                    <dd class="col-sm-9 check-score">${i.value.score}</dd>
                                </dl>
                            </c:if>
                            <input style="display: none" name="answerId" value="${i.value.id}">
                            <p style="color: #1c94c4">Please input his/her score here(the full score is 100 points):<p></p><input type="number" required max="100" min="0" name="score">
                            <button class="btn btn-info" type="submit">提交</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="col">
            <div class="check-block">
                <h5>以下同学尚未提交本作业：</h5>
                <ul class="list-group">
                    <c:forEach items="${sessionScope.userUndone}" var="i">
                        <li class="list-group-item">
                                ${i.username}
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="check-card check-block">
                <h5>以下同学已经提交本作业：</h5>
                <ul class="list-group">
                    <c:forEach items="${sessionScope.userDone}" var="i">
                        <li class="list-group-item">
                                ${i.username}
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>
</body>
<script type="text/javascript" src="../../js/jQuery.js"></script>
<script src="../../js/popper.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</html>
