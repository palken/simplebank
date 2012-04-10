package no.ntnu.idi.simplebank.filters;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.owasp.appsensor.APPSENSOR;
import org.owasp.appsensor.AttackDetectorUtils;
import org.owasp.appsensor.errors.AppSensorException;
import org.owasp.appsensor.trendmonitoring.TrendEvent;

public class InputFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		
		@SuppressWarnings("unchecked")
		Map<String, String[]> parameters = request.getParameterMap();
		
		for (String parameter : parameters.keySet()) {
			if (AttackDetectorUtils.verifySQLInjectionAttack(parameters.get(parameter)[0])) {
				new AppSensorException("CIE1", "User trying to perform a SQL-injection",
						"A user is likely performing a SQL-injection attack with the string: " + 
						parameters.get(parameter)[0]);
			}
			
			if (AttackDetectorUtils.verifyXSSAttack(parameters.get(parameter)[0])) {
				new AppSensorException("IE1", "User is trying an XSS-attack", 
						"A user is likely trying an XSS attack with the string: " + 
				parameters.get(parameter)[0]);
			}
			
			if (!AttackDetectorUtils.verifyNullByteDoesNotExist(parameters.get(parameter)[0])) {
				new AppSensorException("CIE3", "user is trying a null byte attack", 
						"A user is trying a null byte injection in the following string: " +
						parameters.get(parameter)[0]);
			}
			
		}
		
		chain.doFilter(req, resp);

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
