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

@WebServlet(name = "UpdateServlet", urlPatterns = "/update")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String id = request.getParameter("id");

        password = (password == null) ? "" : password;
        id = (id == null) ? "" : id;

        UserDao userDao = new UserDaoImpl();

        if (!password.equals("") && !id.equals("")) {
            User tmp = userDao.getUserById(Integer.parseInt(id));
            tmp.setPassword(password);
            userDao.update(tmp);
        }
    }
}
