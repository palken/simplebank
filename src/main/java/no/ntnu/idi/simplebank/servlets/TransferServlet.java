package no.ntnu.idi.simplebank.servlets;

import no.ntnu.idi.simplebank.Account;
import no.ntnu.idi.simplebank.Database;
import no.ntnu.idi.simplebank.Utilities;
import org.owasp.appsensor.AppSensorIntrusion;
import org.owasp.appsensor.errors.AppSensorException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TransferServlet extends AbstractLoginServlet {

    /**
     *
     */
    private static final long serialVersionUID = -2871788747252213100L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        super.doGet(req, resp);

        String currentlyLoggedInUser = Utilities.getCurrentlyLoggedInUser(req);

        List<Account> currentlyLoggedInUserAccounts = Utilities.getAccountsForUser(currentlyLoggedInUser);

        req.setAttribute("currentlyLoggedInUser", currentlyLoggedInUser);
        req.setAttribute("currentlyLoggedInUserAccounts", currentlyLoggedInUserAccounts);

        req.getRequestDispatcher("/WEB-INF/Transfer.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String currentlyLoggedInUser = Utilities.getCurrentlyLoggedInUser(req);
        if (currentlyLoggedInUser == null) {
            new AppSensorIntrusion(new AppSensorException("ACE3", "ACE3 User exception", "An unauthenticated user is trying to access a resource which requires authentication"));
        }

        String fromAccount = req.getParameter("fromAccount");
        String amount = req.getParameter("amount");
        String toAccount = req.getParameter("toAccount");

        int fromAccountId;
        double doubleAmount;
        int toAccountId;

        try {
            fromAccountId = Integer.parseInt(fromAccount);
            doubleAmount = Double.parseDouble(amount);
            toAccountId = Integer.parseInt(toAccount);

            if (doubleAmount > 10000) {
                new AppSensorIntrusion(new AppSensorException("CU1", "A user is trying to transfer a large amount",
                        "The transaction from account: " + fromAccountId +
                        " is transferring a large amount: " + doubleAmount +
                        " to the account: " + toAccountId));
            }

            Database database = new Database();
            database.performTransaction(fromAccountId, doubleAmount, toAccountId);

            resp.sendRedirect(req.getContextPath() + "/Accountoverview");

        } catch (NumberFormatException numberFormatException) {
            req.setAttribute("Errors", "The fields need to have number for the accounts");
            this.doGet(req, resp);
        }

    }


}
