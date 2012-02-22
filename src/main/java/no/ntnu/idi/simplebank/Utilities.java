package no.ntnu.idi.simplebank;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utilities {
	
	public static String getCurrentlyLoggedInUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false); // We don't want to create a new session
		
		if (session != null) {
			String currentlyLoggedInUser = (String) session.getAttribute("logged_in_user");
			return currentlyLoggedInUser;
		} else {
			return null;
		}
		
	}

}
