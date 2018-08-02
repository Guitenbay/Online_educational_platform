package controller;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.MD5;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        username = (username == null) ? "" : username;
        password = (password == null) ? "" : password;
        try {
            if (!username.equals("") && !password.equals("")) {

                User user = userDao.getUserByName(username);
                if (user == null) {
                    request.setAttribute("message", "username has not existed");
                } else if (!MD5.md5(password, "ShiAndMao").equals(user.getPassword())) {
                    request.setAttribute("message", "password is wrong");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("userId", userDao.getUserIdByName(username));
                    response.sendRedirect(response.encodeRedirectURL("/"));
                    return;
                }

            } else {
                request.setAttribute("message", "username or password is null");
            }
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }
}
