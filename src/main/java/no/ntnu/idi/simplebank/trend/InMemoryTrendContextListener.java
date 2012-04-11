package no.ntnu.idi.simplebank.trend;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InMemoryTrendContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0) {

    }

    public void contextInitialized(ServletContextEvent arg0) {
        new TrendMonitoringThread().start();

    }

    private class TrendMonitoringThread extends Thread {
        public void run() {
            //noinspection InfiniteLoopStatement
            while (true) {
                InMemoryTrendMonitoring.checkUT1();
                InMemoryTrendMonitoring.checkUT2();
                InMemoryTrendMonitoring.checkUT3();
                InMemoryTrendMonitoring.checkUT4();
                InMemoryTrendMonitoring.checkNumberOfTransferRequests();
                InMemoryTrendMonitoring.checkNumberOfLoginsAndLogouts();
                InMemoryTrendMonitoring.checkNumberOfAccessToASpecificResource();

                try {
                    Thread.sleep(1000 * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
