<%--
  Created by IntelliJ IDEA.
  User: 22752
  Date: 2018/7/26
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Course</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/fileinput.css" media="all"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="../../css/theme.css" media="all" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="../../css/main.css" />
    <link rel="stylesheet" type="text/css" href="../../css/header.css" />
    <link rel="stylesheet" type="text/css" href="../../css/footer.css" />
    <link rel="stylesheet" type="text/css" href="../../css/course.css" />
</head>
<body>
<%@include file="header.jsp"%>
<% String courseId = request.getParameter("id"); %>
<% Integer firstSectionId = (Integer) request.getAttribute("firstSectionId");%>
<% boolean isCreator = (boolean)request.getAttribute("isCreator"); %>
<div class="main container">
    <div class="row course-block">
        <div class="col-4 center">
            <h3><%=request.getAttribute("courseName")%></h3>
        </div>
        <div class="col">
            <ul class="nav justify-content-end">
                <li class="nav-item">
                    <% if ((boolean)request.getAttribute("isChoose")) {%>
                    <button type="button" class="btn btn-primary" onclick="dropCourse(<%=courseId%>)">退课</button>
                    <% } else if (isCreator) {%>
                    <button type="button" class="btn btn-primary" disabled="disabled">选课</button>
                    <% } else {%>
                    <button type="button" class="btn btn-primary" onclick="chooseCourse(<%=courseId%>)">选课</button>
                    <% }%>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="course?type=info&id=<%=courseId%>">课程信息</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="course?type=context&id=<%=courseId%>&sectionId=<%=firstSectionId%>">学习</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="course?type=comment&id=<%=courseId%>">讨论</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="course?type=homework&id=<%=courseId%>">作业</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="course?type=resource&id=<%=courseId%>">资源</a>
                </li>
            </ul>
        </div>
    </div>

    <div class="course-block">
        <% if (request.getParameter("type").equals("info")) {%>
        <p class="h3">Info</p>
        <hr>
        <img class="course-img" src="<%=request.getAttribute("src")%>" alt="First slide" />
        <hr>
        <dl class="row add-info">
            <dt class="col-sm-3">授课教师</dt>
            <dd class="col-sm-9"><%=request.getAttribute("teacher")%></dd>
            <dt class="col-sm-3">报名人数</dt>
            <dd class="col-sm-9"><%=request.getAttribute("number")%></dd>
            <dt class="col-sm-3">描述</dt>
            <dd class="col-sm-9"><%=request.getAttribute("description")%></dd>
        </dl>
            <% if (isCreator) {%>
            <hr>
            <button type="button" class="btn btn-primary" onclick="window.location.href = '/add?type=info&id=<%=courseId%>'">修改课程</button>
            <% }%>
        <% }%>

        <% if (request.getParameter("type").equals("context")) {%>
        <p class="h3"><%=request.getAttribute("sectionName")%></p>
        <hr>
        <div class="row">
        <div class="col-8 course-context">
            <h4>Video</h4>
            <c:forEach items="${requestScope.sectionVideo}" var="videoPath">
                <video class="course-video" src="${videoPath}" controls ></video>
            </c:forEach>
            <h4>PPT</h4>
            <c:forEach items="${requestScope.sectionPPT}" var="PPTPath">
                <iframe class="course-ppt" src="${PPTPath}"></iframe>
            </c:forEach>
        </div>
        <div class="col-4">
            <div id="accordion">
                <c:forEach items="${requestScope.chapterList}" step="1" var="chapter">
                    <div class="card course-card">
                        <div class="card-header" id="heading${chapter.id}">
                            <h5 class="mb-0">
                                <button class="btn btn-link" data-toggle="collapse" data-target="#collapse${chapter.id}" aria-expanded="true" aria-controls="collapse${chapter.id}">
                                    ${chapter.name}
                                </button>
                            </h5>
                        </div>

                        <div id="collapse${chapter.id}" class="collapse show" aria-labelledby="heading${chapter.id}" data-parent="#accordion">
                            <div class="card-body">
                                <ul class="list-group list-group-flush">
                                    <!-- 对章节知识点循环 -->
                                    <c:forEach items="${requestScope.mapOfSectionList.get(chapter.id)}" step="1" var="section">
                                    <li class="list-group-item list-group-item-action">
                                        <a href="${pageContext.request.contextPath}/course?type=context&id=<%=courseId%>&sectionId=${section.id}">
                                            ${section.name}
                                        </a>
                                    </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        </div>
        <%}%>

        <% if (request.getParameter("type").equals("comment")) {%>
        <p class="h3">Comment</p>
        <div class="dropdown-divider"></div>
        <nav class="nav flex-column">
            <c:forEach items="${requestScope.commentList}" var="comment">
                <div class="course-comment">
                    <div class="h4">${comment['title']}<small class="text-muted course-comment-name">${comment['username']}</small></div>
                    <p class="blockquote">${comment['context']}</p>
                </div>
            </c:forEach>
        </nav>
        <hr>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addComment">添加评论</button>
        <%}%>

        <% if (request.getParameter("type").equals("homework")) {%>
        <p class="h3">Homework</p>
        <div class="dropdown-divider"></div>
        <div class="row">
            <c:forEach items="${requestScope.homeworkList}" var="homework">
                <div class="col-3">
                    <div class="card" style="width: 15rem;">
                        <div class="card-body">
                            <h5 class="card-title">${homework.name}</h5>
                            <p class="card-text">deadline: ${homework.deadline}</p>
                            <p class="card-text ${homework.done}">${homework.done}</p>
                            <p class="card-text course-score">Score: ${homework.score}</p>
                            <a href="${pageContext.request.contextPath}/homework?id=${homework.homeworkId}" class="btn btn-primary">Go to do</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <%}%>

        <% if (request.getParameter("type").equals("resource")) {%>
        <p class="h3">Resources</p>
        <div class="dropdown-divider"></div>
        <nav class="nav flex-column">
            <c:forEach items="${requestScope.resList}" var="resPath">
                <div class="nav-link">
                    <a class="course-link mr-auto" href="/assets${resPath['resPath']}">${resPath['name']}</a>
                </div>
            </c:forEach>
        </nav>
        <%}%>
    </div>
</div>
<%--add Comment modal--%>
<div class="modal fade" id="addComment" tabindex="-1" role="dialog" aria-labelledby="loginModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ModalChapterTitle">添加评论</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/addComment.op" method="post" onsubmit="return checkComment()">
                    <div class="form-group row">
                        <label for="comment-title" class="col-sm-2 col-form-label">标题</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="comment-title" name="comment-title" placeholder="Comment Title">
                            <input type="hidden" name="courseId" value="<%=courseId%>">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="comment-context" class="col-sm-2 col-form-label">内容</label>
                        <div class="col-sm-10">
                            <textarea cols="50" rows="3" id="comment-context" name="comment-context"></textarea>
                            <p style="color: #f42a28" id="comment-warning"></p>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary">添加</button>
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
<script src="../../js/course.js"></script>
</html>
