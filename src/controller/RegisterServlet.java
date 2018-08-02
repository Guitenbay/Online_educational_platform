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
import java.util.Date;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        username = (username == null) ? "" : username;
        password = (password == null) ? "" : password;


        if (!username.equals("") && !password.equals("")) {

            if (userDao.getUserByName(username) != null) {
                request.setAttribute("message", "username has existed");
            } else {
                try {
                    userDao.save(new User(0, username, MD5.md5(password, "ShiAndMao")));
                }catch (Exception e){
                    e.printStackTrace();
                }
                request.setAttribute("message", "register success");
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("userId", userDao.getUserIdByName(username));
                response.sendRedirect(response.encodeRedirectURL("/"));
                return;
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
