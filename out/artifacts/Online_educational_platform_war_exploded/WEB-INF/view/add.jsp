<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2018/7/27
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Add</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/fileinput.css" media="all"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="../../css/theme.css" media="all" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="../../css/main.css" />
    <link rel="stylesheet" type="text/css" href="../../css/header.css" />
    <link rel="stylesheet" type="text/css" href="../../css/footer.css" />
    <link rel="stylesheet" type="text/css" href="../../css/add.css" />
</head>
<body>
<%@include file="header.jsp"%>
<% String courseId = request.getParameter("id"); %>
<div class="main container">

    <div class="row add-block">
        <div class="col-4 center">
            <h3>高等数学</h3>
        </div>
        <div class="col">
            <ul class="nav justify-content-end">
                <li class="nav-item">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addChapter">添加章节</button>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="check?courseId=<%=courseId%>">查看作业</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="add?type=homework&id=<%=courseId%>">添加作业</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="add?type=resource&id=<%=courseId%>">添加资源</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="add?type=info&id=<%=courseId%>">查看课程信息</a>
                </li>
            </ul>
        </div>
    </div>

    <div class="row">
        <div class="col-4">
            <div id="accordion add-block">
                <c:forEach items="${requestScope.chapterList}" step="1" var="chapter">
                    <div class="card course-card">
                        <div class="card-header" id="heading${chapter.id}">
                            <h5 class="mb-0">
                                <button class="btn btn-link" data-toggle="collapse" data-target="#collapse${chapter.id}" aria-expanded="true" aria-controls="collapse${chapter.id}">
                                        ${chapter.name}
                                </button>
                                <a class="btn btn-link add-end add-section-button" href="add?type=section&id=<%=courseId%>&chapterId=${chapter.id}">添加课时</a>
                            </h5>
                        </div>

                        <div id="collapse${chapter.id}" class="collapse show" aria-labelledby="heading${chapter.id}" data-parent="#accordion">
                            <div class="card-body">
                                <ul class="list-group list-group-flush">
                                    <!-- 对章节知识点循环 -->
                                    <c:forEach items="${requestScope.mapOfSectionList.get(chapter.id)}" step="1" var="section">
                                        <li class="list-group-item list-group-item-action">
                                            <a href="${pageContext.request.contextPath}/add?type=section&id=<%=courseId%>&sectionId=${section.id}">
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

        <div class="col-8">
            <div class="add-block">
            <% if (request.getParameter("type").equals("section")){%>
            <form class="form-horizontal" action="${pageContext.request.contextPath}/addSection.op" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="section-title">课时名称</label>
                    <div class="col-sm-10">
                        <input class="form-control" id="section-title" name="sectionTitle" type="text" placeholder="${requestScope.section.name}">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-12">
                        <p class="h4">Video</p>
                        <c:forEach items="${requestScope.sectionVideo}" var="videoPath">
                            <video class="add-video" src="${videoPath}" controls ></video>
                        </c:forEach>
                        <p class="h4">PPT</p>
                        <c:forEach items="${requestScope.sectionPPT}" var="pptPath">
                            <iframe class="add-ppt" src="${pptPath}"></iframe>
                        </c:forEach>
                    </div>
                </div>
                <div class="form-group" >
                    <label class="col-sm-6 control-label">添加视频</label>
                    <div class="col-12">
                        <div class="col-10 file-loading">
                            <input id="add-video-input" type="file" name="section-video" class="file-loading" data-max-file-count="1" data-max-file-size="0"/>
                            <input type="hidden" name="sectionId" value="${requestScope.section.id}" />
                            <input type="hidden" name="courseId" value="<%=courseId%>" />
                        </div>
                        <hr>
                    </div>
                    <label class="col-sm-6 control-label">添加PDF</label>
                    <div class="col-12">
                        <div class="col-10 file-loading">
                            <input id="add-pdf-input" type="file" name="section-pdf" class="file-loading" data-max-file-count="1" data-max-file-size="0"/>
                        </div>
                        <hr>
                    </div>
                    <input type="hidden" name="sectionId" value="${requestScope.section.id}" />
                    <input type="hidden" name="chapterId" value="<%=request.getParameter("chapterId")%>" />
                    <input type="hidden" name="courseId" value="<%=courseId%>" />
                    <button type="submit" class="btn btn-primary">确定</button>
                </div>
            </form>
            <%}%>

            <% if (request.getParameter("type").equals("homework")){%>
            <form action="${pageContext.request.contextPath}/addHomework.op" method="post">
                <div class="row">
                    <div class="col-9">
                        <p class="display-4">Homework</p>
                    </div>
                    <div class="col-3">
                        <button type="button" class="btn btn-success add-end add-homework-info" onclick="window.location.href = '/check?courseId=<%=courseId%>'">提交情况&批改</button>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="homework-title">作业名称</label>
                    <div class="col-sm-10">
                        <input class="form-control" id="homework-title" name="title" type="text" placeholder="${requestScope.homework.name}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="homework-ddl">截止日期</label>
                    <div class="col-sm-10">
                        <input class="form-control" id="homework-ddl" name="ddl" type="date" value="${requestScope.homework.deadLine}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="homework-description">作业内容</label>
                    <div class="col-sm-10">
                        <textarea cols="55" rows="6" id="homework-description" name="context" placeholder="${requestScope.homework.description}"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <input type="hidden" name="homeworkId" value="<%=request.getParameter("homeworkId")%>">
                    <input type="hidden" name="courseId" value="<%=courseId%>">
                    <hr>
                    <button type="submit" class="btn btn-primary">确定</button>
                </div>
            </form>
            <% }%>

            <% if (request.getParameter("type").equals("resource")){%>
            <p class="display-4">Resources</p>
            <hr>
            <div class="col">
                <ul class="list-group list-group-flush">
                    <c:forEach items="${requestScope.resList}" var="resPath">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-8">
                                    <a class="nav-link" href="/assets${resPath['resPath']}">${resPath['name']}</a>
                                </div>
                                <div class="col-4">
                                    <button type="button" class="btn btn-danger add-end" onclick="removeRes(<%=courseId%>, '${resPath['resPath']}')">Remove</button>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <hr>
            <form action="${pageContext.request.contextPath}/addRes.op" method="post" enctype="multipart/form-data">
                <div class="file-loading">
                    <input id="add-res-input" name="add-res" type="file" class="file-loading" data-max-file-count="1" data-max-file-size="0"/>
                </div>
                <input type="hidden" name="courseId" value="<%=courseId%>">
                <hr>
                <button type="submit" class="btn btn-primary">确定</button>
            </form>
            <% }%>

            <% if (request.getParameter("type").equals("info")){%>
            <p class="display-4">Info</p>
            <hr>
            <img class="add-img" src="${requestScope.src}" alt="First slide" />
            <form action="${pageContext.request.contextPath}/resetCourseImg.op" method="post" enctype="multipart/form-data">
                <p class="col-sm-6 control-label">Select photo:</p>
                <div class="file-loading">
                    <input id="add-img-input" name="add-img" type="file" class="file-loading" data-max-file-count="1" data-max-file-size="7800"/>
                </div>
                <input type="hidden" name="courseId" value="<%=courseId%>" />
                <hr>
                <button type="submit" class="btn btn-primary">确定</button>
            </form>
            <hr>
            <dl class="row add-info">
                <dt class="col-sm-3">授课教师</dt>
                <dd class="col-sm-9">${requestScope.teacher}</dd>
                <dt class="col-sm-3">报名人数</dt>
                <dd class="col-sm-9">${requestScope.number}</dd>
            </dl>
            <%}%>
            </div>
        </div>
    </div>
</div>
<%--add Chapter modal--%>
<div class="modal fade" id="addChapter" tabindex="-1" role="dialog" aria-labelledby="loginModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ModalChapterTitle">添加章节</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/addChapter.op" method="post" onsubmit="return checkChapter()">
                    <div class="form-group row">
                        <label for="chapter-name" class="col-sm-2 col-form-label">章节名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="chapter-name" name="chapterName" placeholder="Chapter Name">
                            <input type="hidden" name="courseId" value="<%=courseId%>">
                            <p id="add-chapter-warning" style="color: red"></p>
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
<script src="../../js/add.js"></script>
</html>
