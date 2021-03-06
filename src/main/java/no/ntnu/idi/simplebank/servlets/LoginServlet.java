package no.ntnu.idi.simplebank.servlets;

import no.ntnu.idi.simplebank.Database;
import org.owasp.appsensor.AppSensorIntrusion;
import org.owasp.appsensor.AttackDetectorUtils;
import org.owasp.appsensor.errors.AppSensorException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1613861886766248905L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String next = request.getParameter("next");


        if (username == null || password == null) {
            request.setAttribute("login_failed", Boolean.TRUE);
            request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
        }

        if (!(request.getParameterMap().containsKey("username")) || !(request.getParameterMap().containsKey("password"))) {
            new AppSensorIntrusion(new AppSensorException("IE4", "User tampering with the POST-parameters", "A user is tampering with the POST-parameteres by removing some"));
        }

        if (next != null) {
            if (!next.startsWith("/") && !next.equals("null")) {
                new AppSensorIntrusion(new AppSensorException("ACE1", "User trying to malicious redirect with the next paramenter", "User malicious redirect with th next paramenter"));
                next = "";
            }
        }

        if (username.isEmpty()) {
            new AppSensorIntrusion(new AppSensorException("AE8", "Username-field is empty", "Username-field is empty"));
        }
        if (username.length() > 100 || username.length() < 2) {
            new AppSensorIntrusion(new AppSensorException("AE4", "Unexpected quantity in username", "Unexpected number of characters in username " + username.length()));
        }

        if (password.isEmpty()) {
            new AppSensorIntrusion(new AppSensorException("AE9", "Password-field is empty", "Password-field is empty"));
        }
        if (password.length() > 100 || password.length() < 2) {
            new AppSensorIntrusion(new AppSensorException("AE4", "Unexpected quantity in password", "Unexpected number of characters in password " + password.length()));
        }


        if (AttackDetectorUtils.verifySQLInjectionAttack(username) || AttackDetectorUtils.verifySQLInjectionAttack(password)) {
            new AppSensorIntrusion(new AppSensorException("CIE1", "User trying to SQL-inject loginfield", "user trying to SQL-inject login"));
            request.setAttribute("login_failed", Boolean.TRUE);
            request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
        }

        Database database = new Database();
        boolean authenticated = database.authenticate(username, password);

        HttpSession session = request.getSession();

        if (!authenticated) {
            request.setAttribute("login_failed", Boolean.TRUE);
            Integer numberOfLoginAttempts = (Integer) session.getAttribute("numberOfLoginAttempts");
            if (numberOfLoginAttempts == null) {
                numberOfLoginAttempts = 1;
                session.setAttribute("numberOfLoginAttempts", numberOfLoginAttempts);
            } else {
                numberOfLoginAttempts = numberOfLoginAttempts+1;
                if (numberOfLoginAttempts > 10 ) {
                    new AppSensorIntrusion(new AppSensorException("AE3", "AppSensor AE3 message",
                            "A user have had over 10 failed login attempts from address: " + request.getRemoteAddr()));
                }
                session.setAttribute("numberOfLoginAttempts", numberOfLoginAttempts);
            }
            String lastUsername = (String) session.getAttribute("last_login_username");

            if (lastUsername != null) {
                if (!username.equals(lastUsername) && !lastUsername.equals("null")) {
                    new AppSensorIntrusion(new AppSensorException("AE1", "User trying different usernames", "User trying different username"));
                }
            }

            session.setAttribute("last_login_username", username);
            request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
        } else {
            Cookie loginCookie = new Cookie("logged_in_user", username);
            response.addCookie(loginCookie);
            session.setAttribute("logged_in_user", username);
            //response.setHeader("Cache-Control","private, no-store, no-cache, must-revalidate, post-check=0, pre-check=0");


            if (next.equals("null")) {
                response.sendRedirect(request.getContextPath() + "/Accountoverview");
                //request.getRequestDispatcher("/WEB-INF/AccountsOverview.jsp").forward(request,response);
            } else {
                response.sendRedirect(next);
            }

        }

    }


}
