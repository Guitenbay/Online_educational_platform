<%--
  Created by IntelliJ IDEA.
  User: 22752
  Date: 2018/7/25
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light header-nav">
    <div class="container">
    <a class="navbar-brand header-font" href="#">Online-Courses</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
<%--id="navbarSupportedContent" onclick="doSearchFromHeader()"--%>
    <div class="collapse navbar-collapse" >
        <form class="form-inline my-2 my-lg-0" method="get" action="${pageContext.request.contextPath}/search">
            <input class="form-control mr-sm-2" id="search-input-header" name="search" placeholder="Search"/>
            <input type="image" src="../../img/search.png" id="search_button">
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
                <a class="dropdown-item" href="/account">My Account</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="/logout.op">Log out</a>
            </div>
        </div>
        <% } else { %>
        <div class="header-lr"><a class="to-lr" href="${pageContext.request.contextPath}/login">Sign in</a>or<a class="to-lr" href="${pageContext.request.contextPath}/register">Sign up</a></div>
        <% }%>
    </div>
    </div>
</nav>
