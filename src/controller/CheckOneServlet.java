package controller;

import dao.*;
import dao.impl.*;
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
import java.util.*;

@WebServlet(name = "CheckOneServlet",urlPatterns = "/checkOne")
public class CheckOneServlet extends HttpServlet {
    private AnswerDao answerDao = new AnswerDaoImpl();
    private CourseDao courseDao = new CourseDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private HomeworkDao homeworkDao = new HomeworkDaoImpl();
    private SelectedDao selectedDao = new SelectedDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession httpSession = request.getSession();
        int answerId = Integer.parseInt(request.getParameter("answerId"));
        int score = Integer.parseInt(request.getParameter("score"));
        int homeworkId=Integer.parseInt(httpSession.getAttribute("homeworkId").toString());
        Answer answer = new Answer();
        answer.setId(answerId);
        answer.setScore(score);
        answerDao.updateScore(answer);
        response.sendRedirect("checkOne?id="+homeworkId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession httpSession = request.getSession();

        int homeworkId = Integer.parseInt(request.getParameter("id"));
        httpSession.setAttribute("homeworkId",homeworkId);
        Homework homework = homeworkDao.getHomeworkById(homeworkId);
        List<Answer> answerList = answerDao.getAnswerListByHomeworkId(homeworkId);
        Course course = courseDao.getCourseById(homework.getCourseId());
        List<Map<String, Object>> userList = selectedDao.getSelectedListByCourseId(course.getId());
        List<User> userDone = new ArrayList<>();
        List<User> userUndone = new ArrayList<>();
        Map<String, Answer> answerListMap = new HashMap<>();
        for (int q = 0; q < answerList.size(); q++) {
            answerListMap.put(userDao.getUserById(answerList.get(q).getUserId()).getUsername(), answerList.get(q));
        }
        for (int p = 0; p < userList.size(); p++) {
            boolean done = false;
            for (int i = 0; i < answerList.size(); i++) {
                if (answerList.get(i).getUserId() == Integer.parseInt(userList.get(p).get("userId").toString())) {
                    done = true;
                }
            }
            if (done == true){
                userDone.add(userDao.getUserById(Integer.parseInt(userList.get(p).get("userId").toString())));
            } else {
                userUndone.add(userDao.getUserById(Integer.parseInt(userList.get(p).get("userId").toString())));
            }
        }
        httpSession.setAttribute("userDone", userDone);
        httpSession.setAttribute("userUndone", userUndone);
        httpSession.setAttribute("answerListMap", answerListMap);
        request.getRequestDispatcher("/WEB-INF/view/checkOne.jsp").forward(request, response);


    }
}
