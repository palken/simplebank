package no.ntnu.idi.simplebank.filters;

import org.owasp.appsensor.AttackDetectorUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

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
            AttackDetectorUtils.verifySQLInjectionAttack(parameters.get(parameter)[0]);
            AttackDetectorUtils.verifyXSSAttack(parameters.get(parameter)[0]);
            AttackDetectorUtils.verifyNullByteDoesNotExist(parameters.get(parameter)[0]);
        }

        chain.doFilter(req, resp);

    }

    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
