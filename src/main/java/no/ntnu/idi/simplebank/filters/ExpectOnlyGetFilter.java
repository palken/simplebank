package no.ntnu.idi.simplebank.filters;

import org.owasp.appsensor.AppSensorIntrusion;
import org.owasp.appsensor.errors.AppSensorException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: palt
 * Date: 4/13/12
 * Time: 13:46
 */
public class ExpectOnlyGetFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getMethod().equals("POST")) {
            new AppSensorIntrusion(new AppSensorException("RE4", "An RE4 user exception",
                    "An attacker is sending a POST-request to a page which only accepts GET"));
        }
    }

    @Override
    public void destroy() {
    }
}
