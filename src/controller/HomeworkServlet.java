package controller;

import dao.AnswerDao;
import dao.CourseDao;
import dao.HomeworkDao;
import dao.UserDao;
import dao.impl.AnswerDaoImpl;
import dao.impl.CourseDaoImpl;
import dao.impl.HomeworkDaoImpl;
import dao.impl.UserDaoImpl;
import model.Answer;
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
import java.util.Date;

@WebServlet(name = "HomeworkServlet",urlPatterns = "/homework")
public class HomeworkServlet extends HttpServlet {

    private HomeworkDao homeworkDao=new HomeworkDaoImpl();
    private CourseDao courseDao=new CourseDaoImpl();
    private UserDao userDao=new UserDaoImpl();
    private AnswerDao answerDao=new AnswerDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession httpSession=request.getSession();

        String username = (String) httpSession.getAttribute("username");
        String homeworkId = request.getParameter("hidden");

        if (username == null || homeworkId == null){
            response.sendRedirect("/error");
            return;
        }

        Homework homework=homeworkDao.getHomeworkById(Integer.parseInt(homeworkId));
        User user=userDao.getUserByName(username);
        Answer answer=new Answer();
        answer.setContext(request.getParameter("myAnswer"));
        answer.setHomeworkId(homework.getId());
        answer.setUpdateTime(new Date());
        answer.setUserId(user.getId());
        answer.setScore(null);
        answerDao.save(answer);
        response.sendRedirect("/lookHomework?id="+homeworkId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession httpSession=request.getSession();
        String homeworkId = request.getParameter("id");

        if (homeworkId == null) {
            response.sendRedirect("/error");
            return;
        }

        Homework homework=homeworkDao.getHomeworkById(Integer.parseInt(homeworkId));
        Course course =courseDao.getCourseById(homework.getCourseId());
        if (homework.getDeadLine().getTime()<new Date().getTime()){
            httpSession.setAttribute("canDo",0);
        }
        else httpSession.setAttribute("canDo",1);
        httpSession.setAttribute("courseName",course.getName());
        httpSession.setAttribute("aHomework",homework);
        request.getRequestDispatcher("/WEB-INF/view/homework.jsp").forward(request, response);
    }
}
