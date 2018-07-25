package controller;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        username = (username == null) ? "" : username;
        password = (password == null) ? "" : password;

        UserDao userDao = new UserDaoImpl();

        if (!username.equals("") && !password.equals("")) {

            if (userDao.getUserByName(username) == null) {
                userDao.save(new User(0, username, password));
                request.setAttribute("message", "register success");
            } else {
                request.setAttribute("message", "username has existed");
            }

        } else {
            request.setAttribute("message", "username or password is null");
        }

        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
