package no.ntnu.idi.simplebank.servlets;

import no.ntnu.idi.simplebank.Database;
import no.ntnu.idi.simplebank.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -793966581847988871L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String first_name = req.getParameter("first_name");
        String surname = req.getParameter("surname");
        String somethingSecret = req.getParameter("somethingSecret");

        User newUser = new User(username, password, first_name, surname, somethingSecret);

        Database database = new Database();
        database.addUser(newUser);

        req.getRequestDispatcher("/WEB-INF/Login.jsp").forward(req, resp);
    }
}
