package no.ntnu.idi.simplebank.servlets;

import no.ntnu.idi.simplebank.Utilities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AbstractLoginServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -5171827500118731993L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String currentlyLoggedInUser = Utilities.getCurrentlyLoggedInUser(req);

        if (currentlyLoggedInUser == null) {
            resp.sendRedirect(req.getContextPath() + "/Login?next=" + req.getRequestURI());
        }
    }

}
