package controller;

import dao.UserDao;
import dao.impl.UserDaoImpl;
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        username = (username == null) ? "" : username;
        password = (password == null) ? "" : password;

        if (!username.equals("") && !password.equals("")) {

            User user = userDao.getUserByName(username);
            if (user == null) {
                request.setAttribute("message", "username has not existed");
            } else if (!user.getPassword().equals(password)) {
                request.setAttribute("message", "password is wrong");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect(response.encodeRedirectURL("/"));
                return;
            }

        } else {
            request.setAttribute("message", "username or password is null");
        }
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }
}
