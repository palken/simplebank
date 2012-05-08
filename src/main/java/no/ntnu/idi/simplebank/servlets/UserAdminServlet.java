package no.ntnu.idi.simplebank.servlets;

import no.ntnu.idi.simplebank.Database;
import no.ntnu.idi.simplebank.User;
import no.ntnu.idi.simplebank.Utilities;
import org.owasp.appsensor.AppSensorIntrusion;
import org.owasp.appsensor.errors.AppSensorException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserAdminServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -5207017716475906105L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String loggedInUser = Utilities.getCurrentlyLoggedInUser(req);
        if (loggedInUser == null) {
            new AppSensorIntrusion(new AppSensorException("ACE3", "AppSensor ACE3 exception",
                   "A user is trying to access users servlet without being authorized"));
        }

        Database database = new Database();

        List<User> users = database.getAllUsers();

        req.setAttribute("users", users);

        req.getRequestDispatcher("/WEB-INF/Users.jsp").forward(req, resp);
    }
}
