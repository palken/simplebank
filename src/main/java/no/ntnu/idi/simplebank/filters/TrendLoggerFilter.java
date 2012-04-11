package no.ntnu.idi.simplebank.filters;

import no.ntnu.idi.simplebank.Utilities;
import no.ntnu.idi.simplebank.trend.SimplebankInMemoryTrendLogger;
import no.ntnu.idi.simplebank.trend.SimplebankTrendEvent;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

public class TrendLoggerFilter implements Filter {

    public void destroy() {
        // TODO Auto-generated method stub

    }

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        String user = Utilities.getCurrentlyLoggedInUser(request);

        if (user == null) {
            user = "anonymous";
        }

        SimplebankInMemoryTrendLogger.getInstance().log(new SimplebankTrendEvent(
                new Date(), request.getRequestURI(), user, request.getRemoteAddr(), request.getMethod()
        ));

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
