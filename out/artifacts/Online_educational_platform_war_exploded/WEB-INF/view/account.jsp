<%--
  Created by IntelliJ IDEA.
  User: 22752
  Date: 2018/7/26
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Account</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/fileinput.css" media="all"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="../../css/theme.css" media="all" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="../../css/main.css" />
    <link rel="stylesheet" type="text/css" href="../../css/header.css" />
    <link rel="stylesheet" type="text/css" href="../../css/footer.css" />
    <link rel="stylesheet" type="text/css" href="../../css/account.css" />
</head>
<body>
<%@include file="header.jsp"%>
<div class="main">
    <div class="account-bg"><div class="account-mask"></div></div>
    <div class="container account-relative">
        <div class="row">
            <div class="col-3">
                <div class="account-name account-block">
                    <%= session.getAttribute("username") %>
                </div>
                <ul class="list-group account-block">
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <button class="account-link" onclick="accountAjax('choose')" >
                            我选的课
                        </button>
                        <span class="badge badge-primary badge-pill"><%=request.getAttribute("chooseNumber")%></span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <button class="account-link" onclick="accountAjax('open')" >
                            我开的课
                        </button>
                        <span class="badge badge-primary badge-pill"><%=request.getAttribute("openNumber")%></span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <button class="account-link" onclick="accountAjax('answer')" >
                            我的作业
                        </button>
                        <span class="badge badge-primary badge-pill"><%=request.getAttribute("homeworkNumber")%></span>
                    </li>
                </ul>
            </div>
            <div class="col account-block" id="account-ajax-block">
                <%--add code here--%>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="loginModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="loginModalLongTitle">添加课程</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/addCourse.op" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-sm-6 control-label" for="name">课程名称</label>
                        <div class="col">
                            <input class="form-control" id="name" name="course-name" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-6 control-label">课程描述</label>
                        <div class="col">
                            <textarea cols="55" rows="3" id="description" name="course-description"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-6 control-label">课程图片</label>
                        <div class="col">
                            <div class="file-loading">
                                <input id="account-img-input" name="course-img" type="file" class="file-loading" data-max-file-count="1" data-max-file-size="7800"/>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <button class="btn btn-primary justify-content-end" type="submit">开课</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<%@include file="footer.jsp"%>
</body>
<script type="text/javascript" src="../../js/jQuery.js"></script>
<script src="../../js/plugins/piexif.min.js" type="text/javascript"></script>
<script src="../../js/plugins/sortable.min.js" type="text/javascript"></script>
<script src="../../js/plugins/purify.min.js" type="text/javascript"></script>
<script src="../../js/popper.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script src="../../js/fileinput.js"></script>
<script src="../../js/theme.js" type="text/javascript"></script>
<script src="../../js/add-file.js"></script>
<script src="../../js/account-ajax.js"></script>
</html>
