package controller;

import dao.AnswerDao;
import dao.CourseDao;
import dao.HomeworkDao;
import dao.UserDao;
import dao.impl.AnswerDaoImpl;
import dao.impl.CourseDaoImpl;
import dao.impl.HomeworkDaoImpl;
import dao.impl.UserDaoImpl;
import model.Course;
import model.Homework;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "CheckServlet",urlPatterns = "/check")
public class CheckServlet extends HttpServlet {
    private CourseDao courseDao = new CourseDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private HomeworkDao homeworkDao=new HomeworkDaoImpl();
    private AnswerDao answerDao=new AnswerDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession httpSession = request.getSession();

        Integer userId = (Integer) httpSession.getAttribute("userId");
        String courseIdString = request.getParameter("courseId");

        if (userId == null) {
            response.sendRedirect("/login");
            return;
        }

        if (courseIdString == null || courseIdString.equals("")){
            response.sendRedirect("/error");
            return;
        }

        User user = userDao.getUserById(userId);
        int courseId = Integer.parseInt(courseIdString);

        List<Homework> homeworkList = homeworkDao.getHomeworkListByCourseId(courseId);

        Map<Homework,String> homeworkMap=new LinkedHashMap<>();
        for (Homework aHomeworkList : homeworkList) {
            String courseName = courseDao.getCourseById(aHomeworkList.getCourseId()).getName();
            homeworkMap.put(aHomeworkList, courseName);
        }

        httpSession.setAttribute("homeworkList",homeworkMap);
        request.getRequestDispatcher("/WEB-INF/view/check.jsp").forward(request,response);

    }
}
