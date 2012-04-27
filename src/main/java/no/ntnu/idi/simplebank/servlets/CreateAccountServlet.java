package no.ntnu.idi.simplebank.servlets;

import no.ntnu.idi.simplebank.Account;
import no.ntnu.idi.simplebank.Database;
import no.ntnu.idi.simplebank.User;
import no.ntnu.idi.simplebank.Utilities;
import org.owasp.appsensor.AppSensorIntrusion;
import org.owasp.appsensor.errors.AppSensorException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateAccountServlet extends AbstractLoginServlet {


    /**
     *
     */
    private static final long serialVersionUID = 8103928834085123537L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        super.doGet(req, resp);

        req.getRequestDispatcher("/WEB-INF/CreateAccount.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String accountName = req.getParameter("accountName");
        String accountType = req.getParameter("accountType");
        String money = req.getParameter("money");
        String user = Utilities.getCurrentlyLoggedInUser(req);
        double moneyDouble;

        try {
            moneyDouble = Double.parseDouble(money);
        } catch (NumberFormatException numberFormatException) {
            req.setAttribute("error_in_new_account", "The field money must contain a number");
            req.getRequestDispatcher("/WEB-INF/CreateAccount.jsp").forward(req, resp);
            return;
        }

        Database database = new Database();
        User accountOwner = database.getUser(user);
        if (moneyDouble > 10000) {
            new AppSensorIntrusion(new AppSensorException("CU2", "User is making new account with high amount of money",
                    "A user: " + accountOwner.getFirst_name() +
                    " is making an account with a large amount of money: " + moneyDouble));
        }
        Account account = new Account(accountName, accountType, moneyDouble, accountOwner);
        database.addAccount(account);

        resp.sendRedirect(req.getContextPath() + "/Accountoverview");
    }

}
