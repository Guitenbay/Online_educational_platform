package controller;

import com.alibaba.fastjson.JSONArray;
import dao.*;
import dao.impl.*;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CourseServlet", urlPatterns = "/course")
public class CourseServlet extends HttpServlet {

    private SelectedDao selectedDao = new SelectedDaoImpl();
    private CourseDao courseDao = new CourseDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private ChapterDao chapterDao = new ChapterDaoImpl();
    private SectionDao sectionDao = new SectionDaoImpl();
    private CommentDao commentDao = new CommentDaoImpl();
    private HomeworkDao homeworkDao = new HomeworkDaoImpl();
    private AnswerDao answerDao = new AnswerDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        String type = request.getParameter("type");
        String courseId = request.getParameter("id");

        if (type == null || courseId == null){
            response.sendRedirect("/error");
            return;
        }

        if (userId == null || selectedDao.getSelectedByUserIdAndCourseId(userId, Integer.parseInt(courseId)) == null) {
            request.setAttribute("isChoose", false);
        } else {
            request.setAttribute("isChoose", true);
        }

        Course course = courseDao.getCourseById(Integer.parseInt(courseId));
        request.setAttribute("courseName", course.getName());
        if (userId == null || course.getCreatorId() != userId) {
            request.setAttribute("isCreator", false);
        } else {
            request.setAttribute("isCreator", true);
        }

        List<Chapter> chapterList = chapterDao.getChapterListByCourseId(Integer.parseInt(courseId));
        if (chapterList.size() > 0){
            List<Section> sectionList = sectionDao.getSectionListByChapterId(chapterList.get(0).getId());
            if (sectionList.size() > 0) {
                request.setAttribute("firstSectionId", sectionList.get(0).getId());
            } else {
                request.setAttribute("firstSectionId", 0);
            }
        } else {
            request.setAttribute("firstSectionId", 0);
        }

        switch (type){
            case "info": info(request, response, course); break;
            case "context": context(request, response, chapterList, Integer.parseInt(courseId)); break;
            case "comment": comment(request, response, Integer.parseInt(courseId)); break;
            case "homework": homework(request, response, userId, Integer.parseInt(courseId)); break;
            case "resource": resource(request, response, Integer.parseInt(courseId)); break;
        }
    }

    private void info(HttpServletRequest request, HttpServletResponse response, Course course) throws ServletException, IOException {
        request.setAttribute("src", course.getImagePath());
        request.setAttribute("teacher", userDao.getUserById(course.getCreatorId()).getUsername());
        request.setAttribute("number", course.getNumber());
        request.setAttribute("description", course.getDescription());

        request.getRequestDispatcher("/WEB-INF/view/course.jsp").forward(request, response);
    }

    private void context(HttpServletRequest request, HttpServletResponse response, List<Chapter> chapterList, int courseId) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") == null) {
            response.sendRedirect("/login");
            return;
        }

        String sectionIdString = request.getParameter("sectionId");

        if (sectionIdString == null) {
            response.sendRedirect("/error");
            return;
        }

        int sectionId = Integer.parseInt(sectionIdString);
        if (sectionId == 0) {
            request.setAttribute("sectionName", "Sorry, no context");
        } else {
            Section section = sectionDao.getSectionById(sectionId);
            request.setAttribute("sectionVideo", section.getVideoPaths(courseId));
            request.setAttribute("sectionPPT", section.getPPTPaths(courseId));
            request.setAttribute("sectionName", section.getName());

            Map<Integer, List<Section>> mapOfSectionList = new HashMap<>();
            for (Chapter chapter : chapterList) {
                mapOfSectionList.put(chapter.getId(), sectionDao.getSectionListByChapterId(chapter.getId()));
            }
            request.setAttribute("chapterList", chapterList);
            request.setAttribute("mapOfSectionList", mapOfSectionList);
        }

        request.getRequestDispatcher("/WEB-INF/view/course.jsp").forward(request, response);
    }

    private void comment(HttpServletRequest request, HttpServletResponse response, int courseId) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") == null) {
            response.sendRedirect("/login");
            return;
        }

        List<Map<String, Object>> comments = new ArrayList<>();
        for (Comment comment : commentDao.getCommentListByCourseId(courseId)) {
            Map<String, Object> entry = new HashMap<>();

            entry.put("title", comment.getTitle());
            entry.put("context", comment.getContext());
            entry.put("username", userDao.getUserById(comment.getUserId()).getUsername());
            comments.add(entry);
        }

        request.setAttribute("commentList", comments);

        request.getRequestDispatcher("/WEB-INF/view/course.jsp").forward(request, response);
    }

    private void homework(HttpServletRequest request, HttpServletResponse response, Integer userId, int courseId) throws ServletException, IOException {
        if (userId == null) {
            response.sendRedirect("/login");
            return;
        }
        List<Homework> homeworkList = homeworkDao.getHomeworkListByCourseId(courseId);

        List<Map<String, Object>> result = new ArrayList<>();

        for (Homework homework : homeworkList) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("name", homework.getName());
            entry.put("homeworkId", homework.getId());
            Answer answer;
            if ((answer = answerDao.getAnswerByUserIdAndHomeworkId(userId, homework.getId())) == null) {
                entry.put("done", "Undone");
                entry.put("score", "none");
            } else {
                entry.put("done", "Done");
                entry.put("score", answer.getScore());
            }
            entry.put("deadline", homework.getDeadLine().toString());
            result.add(entry);
        }
        request.setAttribute("homeworkList", result);

        request.getRequestDispatcher("/WEB-INF/view/course.jsp").forward(request, response);
    }

    private void resource(HttpServletRequest request, HttpServletResponse response, int courseId) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") == null) {
            response.sendRedirect("/login");
            return;
        }

        Course course = courseDao.getCourseById(courseId);

        request.setAttribute("resList", course.getResRelativePaths());

        request.getRequestDispatcher("/WEB-INF/view/course.jsp").forward(request, response);
    }
}
