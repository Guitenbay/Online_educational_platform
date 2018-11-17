package controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import config.FileConfig;
import dao.*;
import dao.impl.*;
import model.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@WebServlet(name = "OperationServlet", urlPatterns = "*.op")
public class OperationServlet extends HttpServlet {

    private CourseDao courseDao = new CourseDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private SelectedDao selectedDao = new SelectedDaoImpl();
    private CommentDao commentDao = new CommentDaoImpl();
    private ChapterDao chapterDao = new ChapterDaoImpl();
    private SectionDao sectionDao = new SectionDaoImpl();
    private HomeworkDao homeworkDao = new HomeworkDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        String methodName = servletPath.substring(1, servletPath.length() - 3);

        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            response.sendRedirect("/error");
        }
    }

    private void getContextForIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        List<Course> coursesForTrend = courseDao.getMaxNumberCourseList(0, 3);
        List<Course> coursesForNew = courseDao.getCourseAllByDesc();
        List<Course> coursesForIndex = coursesForTrend;

        for (int i = 0; i < 3; i++) {
            coursesForIndex.add(coursesForNew.get(i));
        }
        request.setAttribute("firstResult",coursesForIndex);
        request.getRequestDispatcher("/").forward(request,response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        session.setAttribute("username",null);
        session.setAttribute("userId",null);
        request.getRequestDispatcher("").forward(request,response);
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String sort = request.getParameter("sortWay");
        String search = request.getParameter("searchWay");
        String word = request.getParameter("word");
        List<Course> courses;

        JSONArray jsonArray = new JSONArray();

        if (sort.equals("true")){
            courses = courseDao.getMaxNumberCourseAll();
        }
        else {
            courses = courseDao.getCourseAllByDesc();
        }

        if (search.equals("false")) {   // 按名字搜索
            for (Course course : courses) {
                if (course.getName().contains(word)) {
                    Map<String, Object> entry = new HashMap<>();
                    entry.put("id", course.getId());
                    entry.put("src", course.getImagePath());
                    entry.put("name", course.getName());
                    entry.put("description", course.getDescription());
                    entry.put("creatorName", userDao.getUserById(course.getCreatorId()).getUsername());
                    jsonArray.add(entry);
                }
            }
        }
        else {                          // 按教师名搜索
            for (Course course : courses) {
                int id = course.getCreatorId();
                User user = userDao.getUserById(id);
                if (user.getUsername().contains(word)) {
                    Map<String, Object> entry = new HashMap<>();
                    entry.put("id", course.getId());
                    entry.put("src", course.getImagePath());
                    entry.put("name", course.getName());
                    entry.put("description", course.getDescription());
                    entry.put("creatorName", user.getUsername());
                    jsonArray.add(entry);
                }
            }
        }
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonArray);
    }

    private void addComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("/login");
            return;
        }

        String courseId = request.getParameter("courseId");
        String title = request.getParameter("comment-title");
        String context = request.getParameter("comment-context");
        System.out.println(courseId);
        System.out.println(title);
        System.out.println(context);

        commentDao.save(new Comment(0, title, context, userId, Integer.parseInt(courseId)));

        response.sendRedirect("/course?type=comment&id="+courseId);
    }

    private void resetCourseImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileUploadException {
        JSONObject jsonObject = new JSONObject();

        List<FileItem> fileItemList = FileUtil.getFileItemList(request);
        String courseId = FileUtil.getField(fileItemList, "courseId");

        if (courseId == null || courseId.equals("")) {
            response.sendRedirect("/error");
            return;
        }

        String path = FileConfig.GLOBAL_PATH + "/" + FileConfig.COURSE_UPLOAD_DIRECTORY + "/" + courseId;

        if (FileUtil.saveFile(fileItemList, path, courseId)) {
            response.sendRedirect("/add?type=info&id=" + courseId);
        } else {
            response.sendRedirect("/error");
        }
    }

    private void removeRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String resPath = request.getParameter("resPath");

        if (resPath == null || resPath.equals("")){
            response.sendRedirect("/error");
            return;
        }

        File file = new File(FileConfig.GLOBAL_PATH + resPath);
        if (file.exists()){
            file.delete();
        } else {
            System.out.println("file is not exist");
            response.sendRedirect("/error");
            return;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(jsonObject.toString());
    }

    private void addRes(HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
        List<FileItem> fileItemList = FileUtil.getFileItemList(request);

        String courseId = FileUtil.getField(fileItemList, "courseId");

        if (courseId == null || courseId.equals("")) {
            response.sendRedirect("/error");
            return;
        }

        String uploadPath = FileConfig.GLOBAL_PATH + "/"
                + FileConfig.COURSE_UPLOAD_DIRECTORY + "/" + courseId + "/" + FileConfig.RESOURCE_UPLOAD_DIRECTORY;

        File uploadDir = new File(uploadPath);
        FileUtil.mkdir(uploadDir);

        if (FileUtil.saveFileBySelfName(fileItemList, uploadPath)){
            response.sendRedirect("/add?type=resource&id=" + courseId);
        }else {
            response.sendRedirect("/error");
        }

    }

    private void dropCourse(HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        String courseId = request.getParameter("courseId");

        if (userId == null) {
            response.sendRedirect("/login");
            return;
        }

        if (courseId == null || courseId.equals("")){
            response.sendRedirect("/error");
            return;
        }

        selectedDao.delete(userId, Integer.parseInt(courseId));

        Course course = courseDao.getCourseById(Integer.parseInt(courseId));
        int number = course.getNumber() - 1;
        course.setNumber(number);
        courseDao.update(course);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(jsonObject.toString());
    }

    private void chooseCourse(HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        String courseId = request.getParameter("courseId");

        if (userId == null) {
            response.sendRedirect("/login");
            return;
        }

        if (courseId == null || courseId.equals("")){
            response.sendRedirect("/error");
            return;
        }

        selectedDao.save(userId, Integer.parseInt(courseId));

        Course course = courseDao.getCourseById(Integer.parseInt(courseId));
        int number = course.getNumber() + 1;
        course.setNumber(number);
        courseDao.update(course);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(jsonObject.toString());
    }

    private void addCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JSONObject jsonObject = new JSONObject();
        try {
            List<FileItem> fileItemList = FileUtil.getFileItemList(request);

            HttpSession session = request.getSession();
            int creatorId = (int) session.getAttribute("userId");
            String courseName = FileUtil.getField(fileItemList, "course-name");
            String description = FileUtil.getField(fileItemList, "course-description");
            courseDao.save(new Course(0, courseName, description, creatorId, 0));

            int courseId = courseDao.getCourseIdOfMostNew();

            // 这个路径相对当前应用的目录
            String uploadPath = FileConfig.GLOBAL_PATH + "/"
                    + FileConfig.COURSE_UPLOAD_DIRECTORY + "/" + courseId;

            File uploadDir = new File(uploadPath);
            FileUtil.mkdir(uploadDir);

            if (FileUtil.saveFile(fileItemList, uploadPath, String.valueOf(courseId))){
                jsonObject.put("success", true);
                response.sendRedirect("/account");
            }else {
                jsonObject.put("success", false);
                jsonObject.put("message", "something is wrong");
            }
        } catch (FileUploadException e) {
            jsonObject.put("success", false);
            jsonObject.put("message", "something is wrong");
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(jsonObject.toString());
    }

    private void addSection(HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
        List<FileItem> fileItemList = FileUtil.getFileItemList(request);

        String courseId = FileUtil.getField(fileItemList, "courseId");
        String sectionId = FileUtil.getField(fileItemList, "sectionId");
        String chapterId = FileUtil.getField(fileItemList, "chapterId");
        String sectionTitle = FileUtil.getField(fileItemList, "sectionTitle");

        if (chapterId == null || chapterId.equals("")) {
            response.sendRedirect("/error");
            return;
        }

        Section section;
        if (sectionId == null || sectionId.equals("")) {
            section = new Section();
            section.setChapterId(Integer.parseInt(chapterId));
        } else {
            section = sectionDao.getSectionById(Integer.parseInt(sectionId));
        }

        if (sectionTitle != null && !sectionTitle.equals("")) {
            section.setName(sectionTitle);
        }

        if (sectionId == null || sectionId.equals("")) {
            sectionDao.save(section);
        } else {
            sectionDao.update(section);
        }


        String chapterDir = FileConfig.CHAPTER_UPLOAD_DIRECTORY + "-" + section.getChapterId();

        String uploadPath = FileConfig.GLOBAL_PATH + "/"
                + FileConfig.COURSE_UPLOAD_DIRECTORY + "/" + courseId + "/" + chapterDir + "/" + sectionId;


        String videoPath = uploadPath + "/" + FileConfig.VIDEO_UPLOAD_DIRECTORY;
        String pptPath = uploadPath + "/" + FileConfig.PPT_UPLOAD_DIRECTORY;

        File videoDir = new File(videoPath);
        FileUtil.mkdir(videoDir);

        File pptDir = new File(pptPath);
        FileUtil.mkdir(pptDir);

        for (FileItem item : fileItemList) {
            // 处理不在表单中的字段
            if (!item.isFormField() && item.getSize() > 0) {
                String fileName = item.getName();
                String filePath;
                if (item.getFieldName().equals("section-video")) {
                    filePath = videoPath + "/" + fileName;
                } else {
                    filePath = pptPath + "/" + fileName;
                }
                File storeFile = new File(filePath);
                // 保存文件到硬盘
                try {
                    item.write(storeFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        response.sendRedirect("/add?type=section&id=" + courseId + "&sectionId=" + sectionId);
    }

    private void addChapter(HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
        String chapterName = request.getParameter("chapterName");
        String name =new String(chapterName.getBytes("ISO8859-1"),"UTF-8");
        String courseId = request.getParameter("courseId");

        if (courseId == null || courseId.equals("")) {
            response.sendRedirect("/error");
            return;
        }

        chapterDao.save(new Chapter(0, name, Integer.parseInt(courseId)));

        response.sendRedirect("/add?type=info&id=" + courseId);
    }

    private void addHomework(HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
        String title = request.getParameter("title");
        String ddl = request.getParameter("ddl");
        String context = request.getParameter("context");
        String homeworkId = request.getParameter("homeworkId");
        String courseId = request.getParameter("courseId");

        if (courseId == null || courseId.equals("")) {
            response.sendRedirect("/error");
            return;
        }

        Homework homework;
        if (homeworkId != null && !homeworkId.equals("")) {
            homework = homeworkDao.getHomeworkById(Integer.parseInt(homeworkId));
        } else {
            homework = new Homework();
            homework.setCourseId(Integer.parseInt(courseId));
        }

        if (!title.equals("")) {
            homework.setName(title);
        }
        if (!ddl.equals("")) {
            String[] dateStrings = ddl.split("-");
            Date date = new Date(Integer.parseInt(dateStrings[0]) - 1900, Integer.parseInt(dateStrings[1]) - 1, Integer.parseInt(dateStrings[2]));
            homework.setDeadLine(date);
        }
        if (!context.equals("")) {
            homework.setDescription(context);
        }

        if (homeworkId != null && !homeworkId.equals("")) {
            homeworkDao.update(homework);
        } else {
            homeworkDao.save(homework);
        }

        response.sendRedirect("/check?courseId=" + Integer.parseInt(courseId));
    }

}
