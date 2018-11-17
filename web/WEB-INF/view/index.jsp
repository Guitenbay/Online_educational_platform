<%@ page import="config.FileConfig" %><%--
Created by IntelliJ IDEA.
User: 22752
Date: 2018/7/22
Time: 14:58
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
<% if(request.getAttribute("firstResult")==null){
    request.getRequestDispatcher("/getContextForIndex.op").forward(request,response);
}%>
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
                <div class="carousel-item index-height active">
                    <img class="d-block w-100" src="${requestScope.firstResult.get(0).imagePath}" alt="First slide">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>${requestScope.firstResult.get(0).name}</h5>
                        <p class="line-limit-length-for-chrome">${requestScope.firstResult.get(0).description}</p>
                    </div>
                </div>
                <div class="carousel-item index-height">
                    <img class="d-block w-100" src="${requestScope.firstResult.get(1).imagePath}" alt="Second slide">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>${requestScope.firstResult.get(1).name}</h5>
                        <p class="line-limit-length-for-chrome">${requestScope.firstResult.get(1).description}</p>
                    </div>
                </div>
                <div class="carousel-item index-height">
                    <img class="d-block w-100" src="${requestScope.firstResult.get(2).imagePath}" alt="Third slide">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>${requestScope.firstResult.get(2).name}</h5>
                        <p class="line-limit-length-for-chrome">${requestScope.firstResult.get(2).description}</p>
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
                <c:forEach items="${requestScope.firstResult}" begin="0" end="2" step="1" var="i">
                <div class="col-4">
                    <div class="card index-center" style="width: 18rem;">
                        <img class="card-img-top" src="${i.imagePath}" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">${i.name}</h5>
                            <p class="card-text line-limit-length-for-chrome">${i.description}</p>
                            <a href="${pageContext.request.contextPath}/course?type=info&id=${i.id}" class="btn btn-primary">Go for detail</a>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
        <div class="index-block index-margin">
            <h3>New</h3>
            <div class="row">
                <c:forEach items="${requestScope.firstResult}" begin="3" end="5" step="1" var="i">
                    <div class="col-4">
                        <div class="card index-center" style="width: 18rem;">
                            <img class="card-img-top" src="${i.imagePath}" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title">${i.name}</h5>
                                <p class="card-text line-limit-length-for-chrome">${i.description}</p>
                                <a href="${pageContext.request.contextPath}/course?type=info&id=${i.id}" class="btn btn-primary">Go for detail</a>
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
