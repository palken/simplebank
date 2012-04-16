package no.ntnu.idi.simplebank.servlets;

import org.owasp.appsensor.AppSensorIntrusion;
import org.owasp.appsensor.intrusiondetection.AppSensorIntrusionDetector;
import org.owasp.appsensor.intrusiondetection.IntrusionRecord;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: palt
 * Date: 4/16/12
 * Time: 11:03
 */
public class ViewAppSensorEventsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<IntrusionRecord> intrusionRecords = new AppSensorIntrusionDetector().getIntrusionStore().getAllIntrusionRecords();
        for (IntrusionRecord intrusionRecord : intrusionRecords) {
            List<AppSensorIntrusion> intrusions = intrusionRecord.getCopyIntrusionsObserved();
            request.setAttribute("intrusions", intrusions);
        }
         request.getRequestDispatcher("/WEB-INF/AppSensorEvents.jsp").forward(request, response);
    }
}