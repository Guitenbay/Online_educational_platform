<%--
  Created by IntelliJ IDEA.
  User: 22752
  Date: 2018/7/26
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Search</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/main.css" />
    <link rel="stylesheet" type="text/css" href="../../css/header.css" />
    <link rel="stylesheet" type="text/css" href="../../css/footer.css" />
    <link rel="stylesheet" type="text/css" href="../../css/search.css" />
</head>
<body>
<%@include file="header.jsp"%>
<div class="main container">
    <div class="search-control search-block">
        <form class="form-inline row" method="POST">
            <div class="col-6 mr-auto">
                <div class="search-radio-button">
                    <label class="search-radio-label" for="optionsRadios1">Select the sort way:</label>
                    <div class="btn-group btn-group-toggle" data-toggle="buttons">
                        <label class="btn btn-secondary active">
                            <%--<input type="radio" name="options" id="option2" autocomplete="off"> Radio--%>
                            <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" autocomplete="off">heat
                        </label>
                        <label class="btn btn-secondary">
                            <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2" autocomplete="off">time starting
                            <%--<input type="radio" name="options" id="option3" autocomplete="off"> Radio--%>
                        </label>
                    </div>
                </div>
                <div class="search-radio-button">
                    <label class="search-radio-label" for="optionsRadios3">Select the search way:</label>
                    <div class="btn-group btn-group-toggle" data-toggle="buttons">
                        <label class="btn btn-secondary active">
                            <%--<input type="radio" name="options" id="option2" autocomplete="off"> Radio--%>
                            <input type="radio" name="optionsRadios2" id="optionsRadios3" value="option1" autocomplete="off">teacher's name
                        </label>
                        <label class="btn btn-secondary">
                            <input type="radio" name="optionsRadios2" id="optionsRadios4" value="option2" autocomplete="off">course name
                            <%--<input type="radio" name="options" id="option3" autocomplete="off"> Radio--%>
                        </label>
                    </div>
                </div>
            </div>
            <input class="form-control mr-sm-2 col-3" id="search-input" name="search" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0 col-1" type="button" onclick="doSearch(1)">Search</button>
        </form>
    </div>
    <div class="search-result search-block">
        <div class="row" id="searchContent">
        </div>
    </div>

    <div class="btn-toolbar search-btn-toolbar-center search-btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
        <div class="btn-group mr-2" role="group" id="searchPage">
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
<script type="text/javascript" src="../../js/jQuery.js"></script>
<script src="../../js/popper.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script src="../../js/search.js"></script>
</html>

