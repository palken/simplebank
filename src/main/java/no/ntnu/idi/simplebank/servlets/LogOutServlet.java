package no.ntnu.idi.simplebank.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
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

        HttpSession session = req.getSession();
        session.removeAttribute("logged_in_user");

        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
