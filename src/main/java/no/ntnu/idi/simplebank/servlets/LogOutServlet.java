package no.ntnu.idi.simplebank.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 4463629978929432627L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("logged_in_user")) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }

        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
