package no.ntnu.idi.simplebank.trend;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InMemoryTrendContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		new TrendMonitoringThread().start();

	}
	
	class TrendMonitoringThread extends Thread {
		public void run() {
			while (true) {
				InMemoryTrendMonitoring.checkUT1();
				InMemoryTrendMonitoring.checkUT2();
				InMemoryTrendMonitoring.checkUT3();
				InMemoryTrendMonitoring.checkUT4();
				InMemoryTrendMonitoring.checkNumberOfTransferRequests();
				
				try {
					Thread.sleep(1000*10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
