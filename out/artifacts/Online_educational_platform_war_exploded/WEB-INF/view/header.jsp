<%--
  Created by IntelliJ IDEA.
  User: 22752
  Date: 2018/7/25
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light">
    <div class="container">
    <a class="navbar-brand header-font" href="#">Online-Courses</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"/>
            <input type="image" onclick="" src="../../imgs/search.png" id="search_button">
        </form>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Home<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/search">Search</a>
            </li>
        </ul>
        <% if (session.getAttribute("username") != null) {%>
        <div class="btn-group">
            <button type="button" class="btn dropdown-toggle header-btn" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <%= session.getAttribute("username")%>
            </button>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="#">My Account</a>
                <a class="dropdown-item" href="#">My Homework</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Log out</a>
            </div>
        </div>
        <% } else { %>
        <div class="header-lr"><a class="to-lr" href="${pageContext.request.contextPath}/login">Sign in</a>or<a class="to-lr" href="${pageContext.request.contextPath}/register">Sign up</a></div>
        <% }%>
    </div>
    </div>
</nav>
