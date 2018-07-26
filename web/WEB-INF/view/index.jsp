<%--
Created by IntelliJ IDEA.
User: 22752
Date: 2018/7/22
Time: 14:58
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/main.css" />
    <link rel="stylesheet" type="text/css" href="../../css/header.css" />
    <link rel="stylesheet" type="text/css" href="../../css/footer.css" />
    <link rel="stylesheet" type="text/css" href="../../css/index.css" />
    </head>
<body>
    <%@include file="header.jsp"%>
    <div class="main container">
        <%--carousel--%>
        <div id="carouselExampleIndicators" class="carousel slide index-margin" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="d-block w-100" src="${pageContext.request.contextPath}/imgs/first-slide.svg" alt="First slide">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>First</h5>
                        <p>waiting write something...</p>
                    </div>
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="${pageContext.request.contextPath}/imgs/second-slide.svg" alt="Second slide">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Second</h5>
                        <p>waiting write something...</p>
                    </div>
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="${pageContext.request.contextPath}/imgs/third-slide.svg" alt="Third slide">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Third</h5>
                        <p>waiting write something...</p>
                    </div>
                </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <%--carousel--%>

        <div class="index-block index-margin">
            <h3>Trending</h3>
            <div class="row">
                <% for (int i=0; i < 3; i++) {%>
                <div class="col-4">
                    <div class="card index-center" style="width: 18rem;">
                        <img class="card-img-top" src="/imgs/test.svg" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">Card title</h5>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                            <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                    </div>
                </div>
                <% }%>
            </div>
        </div>
        <div class="index-block index-margin">
            <h3>New</h3>
            <div class="row">
                <% for (int i=0; i < 3; i++) {%>
                <div class="col-4">
                    <div class="card index-center" style="width: 18rem;">
                        <img class="card-img-top" src="/imgs/test.svg" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">Card title</h5>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                            <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                    </div>
                </div>
                <% }%>
            </div>
        </div>
        write code continue...
    </div>
    <%@include file="footer.jsp"%>
</body>
<script type="text/javascript" src="../../js/jQuery.js"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
