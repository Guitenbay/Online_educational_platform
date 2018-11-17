package controller;

import dao.*;
import dao.impl.*;
import model.Chapter;
import model.Course;
import model.Homework;
import model.Section;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AddServlet", urlPatterns = "/add")
public class AddServlet extends HttpServlet {

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

        if (userId == null) {
            response.sendRedirect("/login");
            return;
        }

        List<Chapter> chapterList = chapterDao.getChapterListByCourseId(Integer.parseInt(courseId));
        Map<Integer, List<Section>> mapOfSectionList = new HashMap<>();
        for (Chapter chapter : chapterList) {
            mapOfSectionList.put(chapter.getId(), sectionDao.getSectionListByChapterId(chapter.getId()));
        }
        request.setAttribute("chapterList", chapterList);
        request.setAttribute("mapOfSectionList", mapOfSectionList);

        switch (type){
            case "info": info(request, response, Integer.parseInt(courseId)); break;
            case "section": section(request, response, Integer.parseInt(courseId)); break;
            case "homework": homework(request, response); break;
            case "resource": resource(request, response, Integer.parseInt(courseId)); break;
        }
    }

    private void resource(HttpServletRequest request, HttpServletResponse response, int courseId) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") == null) {
            response.sendRedirect("/login");
            return;
        }

        Course course = courseDao.getCourseById(courseId);

        request.setAttribute("resList", course.getResRelativePaths());

        request.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(request, response);
    }

    private void homework(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String homeworkId = request.getParameter("homeworkId");

        if (homeworkId != null && !homeworkId.equals("")) {
            Homework homework = homeworkDao.getHomeworkById(Integer.parseInt(homeworkId));
            request.setAttribute("homework", homework);
        }

        request.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(request, response);
    }

    private void section(HttpServletRequest request, HttpServletResponse response, int courseId) throws ServletException, IOException {
        String sectionId = request.getParameter("sectionId");

        if (sectionId == null || sectionId.equals("")) {
            sectionDao.save(new Section(0, "none", courseId));
        }

        if (sectionId != null && !sectionId.equals("")) {
            Section section = sectionDao.getSectionById(Integer.parseInt(sectionId));
            request.setAttribute("section", section);
            request.setAttribute("sectionVideo", section.getVideoPaths(courseId));
            request.setAttribute("sectionPPT", section.getPPTPaths(courseId));
        }

        request.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(request, response);
    }

    private void info(HttpServletRequest request, HttpServletResponse response, int courseId) throws ServletException, IOException {
        Course course = courseDao.getCourseById(courseId);
        request.setAttribute("src", course.getImagePath());
        request.setAttribute("teacher", userDao.getUserById(course.getCreatorId()).getUsername());
        request.setAttribute("number", course.getNumber());

        request.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(request, response);
    }
}
