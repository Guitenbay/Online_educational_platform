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
    private UserDao userDao = new UserDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordAgain = request.getParameter("password again");

        username = (username == null) ? "" : username;
        password = (password == null) ? "" : password;
        passwordAgain = (passwordAgain == null) ? "" : passwordAgain;


        if (!username.equals("") && !password.equals("") && !passwordAgain.equals("")) {

            if (!password.equals(passwordAgain)) {
                request.setAttribute("message", "two password is not equaled");
            } else if (userDao.getUserByName(username) != null) {
                request.setAttribute("message", "username has existed");
            } else {
                userDao.save(new User(0, username, password));
                request.setAttribute("message", "register success");
            }

        } else {
            request.setAttribute("message", "username or password is null");
        }

        request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
    }
}
