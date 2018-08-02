package controller;

import com.alibaba.fastjson.JSONArray;
import dao.*;
import dao.impl.*;
import model.Answer;
import model.Course;
import model.Homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "AccountServlet", urlPatterns = "/account")
public class AccountServlet extends HttpServlet {

    private CourseDao courseDao = new CourseDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private SelectedDao selectedDao = new SelectedDaoImpl();
    private HomeworkDao homeworkDao = new HomeworkDaoImpl();
    private AnswerDao answerDao = new AnswerDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("/error");
            return;
        }

        int openNumber = courseDao.getCourseListByUserId(userId).size();
        List<Map<String, Object>> mapList = selectedDao.getSelectedListByUserId(userId);
        int chooseNumber = mapList.size();
        int homeworkNumber = 0;
        for (Map<String, Object> tmp : mapList) {
            int courseId = (int) tmp.get("courseId");
            homeworkNumber += homeworkDao.getHomeworkListByCourseId(courseId).size();
        }
        request.setAttribute("openNumber", openNumber);
        request.setAttribute("chooseNumber", chooseNumber);
        request.setAttribute("homeworkNumber", homeworkNumber);

        request.getRequestDispatcher("/WEB-INF/view/account.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("type");

        method = (method == null) ? "" : method;
        switch (method){
            case "open": open(request, response); break;
            case "choose": choose(request, response); break;
            case "answer": answer(request, response); break;
        }
    }

    private void answer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("/login");
            return;
        }

        List<Map<String, Object>> mapList = selectedDao.getSelectedListByUserId(userId);
        JSONArray jsonArray = new JSONArray();
        for (Map<String, Object> tmp : mapList) {
            int courseId = (int) tmp.get("courseId");
            String courseName = courseDao.getCourseById(courseId).getName();
            List<Homework> homeworkList = homeworkDao.getHomeworkListByCourseId(courseId);
            for (Homework homework: homeworkList) {
                Map<String, Object> entry = new HashMap<>();
                entry.put("name", homework.getName());
                entry.put("courseName", courseName);
                entry.put("homeworkId", homework.getId());
                Answer answer;
                if ((answer = answerDao.getAnswerByUserIdAndHomeworkId(userId, homework.getId())) != null) {
                    entry.put("done", "Done");
                    entry.put("score", answer.getScore());
                } else {
                    entry.put("done", "Undone");
                    entry.put("score", "none");
                }
                entry.put("deadline", homework.getDeadLine().toString());
                jsonArray.add(entry);
            }
        }
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonArray);
    }

    private void choose(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("/login");
            return;
        }

        List<Map<String, Object>> mapList = selectedDao.getSelectedListByUserId(userId);
        JSONArray jsonArray = new JSONArray();

        for (Map<String, Object> tmp : mapList) {
            Map<String, Object> entry = new HashMap<>();
            int courseId = (int) tmp.get("courseId");
            Course course = courseDao.getCourseById(courseId);

            entry.put("name", course.getName());
            entry.put("description", course.getDescription());
            entry.put("src", course.getImagePath());
            entry.put("id", course.getId());
            entry.put("creatorName", userDao.getUserById(course.getCreatorId()).getUsername());
            jsonArray.add(entry);
        }
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonArray);
    }

    private void open(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("/login");
            return;
        }

        List<Course> courseList = courseDao.getCourseListByUserId(userId);
        JSONArray jsonArray = new JSONArray();

        for (Course tmp : courseList) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("name", tmp.getName());
            entry.put("description", tmp.getDescription());
            entry.put("src", tmp.getImagePath());
            entry.put("id", tmp.getId());
            entry.put("creatorName", userDao.getUserById(tmp.getCreatorId()).getUsername());
            jsonArray.add(entry);
        }
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonArray);
    }
}
