package no.ntnu.idi.simplebank.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.ntnu.idi.simplebank.Account;
import no.ntnu.idi.simplebank.Database;
import no.ntnu.idi.simplebank.Utilities;

public class AccountOverviewServlet extends AbstractLoginServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5718198798028645542L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
		
		String currentlyLoggedInUser = Utilities.getCurrentlyLoggedInUser(req);
		
		Database database = new Database();
		List<Account> accountsForCurrentlyLoggedInUser 
			= database.getAccountsForUsername(currentlyLoggedInUser);
		
		req.setAttribute("accounts", accountsForCurrentlyLoggedInUser);
		req.getRequestDispatcher("/WEB-INF/AccountsOverview.jsp").forward(req, resp);
		
	}

}