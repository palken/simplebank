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
 * Time: 13:43
 */
public class ExpectOnlyPostFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getMethod().equals("GET")) {
            new AppSensorIntrusion(new AppSensorException("RE3", "AppSensor RE3 user exception",
                    "An attacker is sending a GET request to a site which only accepts POST"));
        }
    }

    @Override
    public void destroy() {
    }
}
