package no.ntnu.idi.simplebank.filters;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.ntnu.idi.simplebank.Utilities;

import org.owasp.appsensor.APPSENSOR;
import org.owasp.appsensor.trendmonitoring.TrendEvent;

public class TrendLoggerFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String user = Utilities.getCurrentlyLoggedInUser(request);
		
		if (user == null) {
			user = "anonomyous";
		}
		
		APPSENSOR.trendLogger().log(new TrendEvent(new Date(),
				request.getRequestURI(), user, request.getRemoteAddr()));
		chain.doFilter(req, resp);

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
