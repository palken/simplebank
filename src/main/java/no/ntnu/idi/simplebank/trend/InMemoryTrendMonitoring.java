package no.ntnu.idi.simplebank.trend;

import org.owasp.appsensor.errors.AppSensorException;

import java.util.*;


class InMemoryTrendMonitoring {

    private static final String loginUri = "/simplebank/Login";
    private static final String logoutUri = "/simplebank/logout";

    // Code fetched from OWASP AppSensor Demoapp2
    public static void checkUT1() {

        HashMap<String, List<SimplebankTrendEvent>> trends = SimplebankInMemoryTrendDataStore
                .getInstance().getCopyOfAllEventsByUserAddress();

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.HOUR, -1); // go back 1 hr
        Date oneHourAgo = cal.getTime();

        for (String userAddress : trends.keySet()) {
            List<SimplebankTrendEvent> eventsForUser = trends.get(userAddress);
            HashMap<String, Integer> resourceUsageMap = new HashMap<String, Integer>();

            for (SimplebankTrendEvent te : eventsForUser) {
                if (te.getTime().after(oneHourAgo)) { // only check issues in
                    // the last hour
                    String resource = te.getResourceAccessed();
                    if (resourceUsageMap.containsKey(resource)) {
                        Integer i = resourceUsageMap.get(resource);
                        i++; // add 1
                        resourceUsageMap.put(resource, i);
                    } else { // resource is not yet in map
                        resourceUsageMap.put(resource, 1);
                    }
                }
            }

            for (String resource : resourceUsageMap.keySet()) {
                int numberOfAccesses = resourceUsageMap.get(resource);
                if (numberOfAccesses >= 100) {
                    // over limit, fire violation
                    new AppSensorException("UT1", "AppSensorUser Message UT1",
                            "Attacker at address [" + userAddress
                                    + "] has sent [" + numberOfAccesses
                                    + "] requests to resource [" + resource
                                    + "] in the last hour");
                }
            }
        }
    }

    public static void checkUT2() {
        HashMap<String, List<SimplebankTrendEvent>> trends = SimplebankInMemoryTrendDataStore
                .getInstance().getCopyOfAllEventsByUserAddress();

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.MINUTE, -5);
        Date fiveMinutesAgo = cal.getTime();

        for (String userAddress : trends.keySet()) {
            List<SimplebankTrendEvent> eventsForUser = trends.get(userAddress);
            int numberOfAccesses = 0;

            for (SimplebankTrendEvent trendEvent : eventsForUser) {
                if (trendEvent.getTime().after(fiveMinutesAgo)) {
                    numberOfAccesses++;
                }
            }

            if (numberOfAccesses > 300) {
                new AppSensorException("UT2", "AppSensorUser Message UT2",
                        "Attacker at address: " + userAddress
                                + "  have accessed the site "
                                + numberOfAccesses
                                + " Times the last five minutes");
            }
        }
    }

    public static void checkUT3() {
        HashMap<String, List<SimplebankTrendEvent>> trends = SimplebankInMemoryTrendDataStore
                .getInstance().getCopyOfAllEventsByUserAddress();

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.HOUR, -24);
        Date oneDayAgo = cal.getTime();

        for (String userAddress : trends.keySet()) {

            List<SimplebankTrendEvent> eventsForUser = trends.get(userAddress);
            Date firstAccess = new GregorianCalendar().getTime();

            for (SimplebankTrendEvent trendEvent : eventsForUser) {
                if (trendEvent.getTime().before(firstAccess)) {
                    firstAccess = trendEvent.getTime();
                }
            }

            int beforeTodayCount = 0;
            int todayCount = 0;

            for (SimplebankTrendEvent trendEvent : eventsForUser) {
                if (trendEvent.getTime().before(oneDayAgo)) {
                    beforeTodayCount++;
                }
                if (trendEvent.getTime().after(oneDayAgo)) {
                    todayCount++;
                }
            }

            long differenceInDaysMillis = oneDayAgo.getTime()
                    - firstAccess.getTime();
            long differenceInDays = differenceInDaysMillis
                    / (24 * 60 * 60 * 1000);

            if (differenceInDays < 1) {
                differenceInDays = 1; // reset if too small
            }

            long average = beforeTodayCount / differenceInDays;

            if (todayCount > (average * 20)) {
                new AppSensorException("UT3", "AppSensor UT3 message",
                        "The user at address " + userAddress + " have a increased traffic of 20 times compared to previous traffic \n" +
                                "The average was: " + average + " while todays count is: " + todayCount);
            }

        }
    }

    /**
     * check if user deviates from normal access for specific function - sharp increase in usage
     */
    public static void checkUT4() {
        HashMap<String, List<SimplebankTrendEvent>> trends =
                SimplebankInMemoryTrendDataStore.getInstance().getCopyOfAllEventsByUserAddress();

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.HOUR, -24);    //go back 1 day
        Date oneDayAgo = cal.getTime();

        for (String userAddress : trends.keySet()) {
            List<SimplebankTrendEvent> eventsForUser = trends.get(userAddress);
            Set<String> uniqueResourcesForUser = new HashSet<String>();

            for (SimplebankTrendEvent te : eventsForUser) {
                uniqueResourcesForUser.add(te.getResourceAccessed());
            }

            for (String resourceAccessedByUser : uniqueResourcesForUser) {
                Date firstAccess = new GregorianCalendar().getTime();
                for (SimplebankTrendEvent te : eventsForUser) {
                    if (resourceAccessedByUser.equals(te.getResourceAccessed())) {
                        if (te.getTime().before(firstAccess)) {
                            firstAccess = te.getTime();
                        }
                    }
                }
                //now we have the firstAccess set to the earliest access
                //average the number of accesses/day for all days from first access through yesterday
                //and compare that to today's access numbers
                int beforeTodayCount = 0;
                int todayCount = 0;

                for (SimplebankTrendEvent te : eventsForUser) {
                    if (resourceAccessedByUser.equals(te.getResourceAccessed())) {
                        if (te.getTime().before(oneDayAgo)) {
                            beforeTodayCount++;
                        }
                        if (te.getTime().after(oneDayAgo)) {
                            todayCount++;
                        }
                    }
                }

                //have the count - now let's get the # of days between first access and yesterday
                long differenceInDaysMillis = oneDayAgo.getTime() - firstAccess.getTime();
                long differenceInDays = differenceInDaysMillis / (24 * 60 * 60 * 1000);

                if (differenceInDays < 1) {
                    differenceInDays = 1;   //reset if too small
                }

                long average = beforeTodayCount / differenceInDays;

                if (todayCount > (average * 20)) {      //if usage jumps more than 20X average
                    //over limit, fire violation
                    new AppSensorException("UT4", "AppSensorUser Message UT4", "Attacker at address [" + userAddress + "] has sent [" +
                            todayCount + "] requests to the resource [" + resourceAccessedByUser + "] in the last day when the previous per-day access average" +
                            "for this user was [" + average + "]");
                }
            }
        }
    }

    public static void checkNumberOfTransferRequests() {
        HashMap<String, List<SimplebankTrendEvent>> trends =
                SimplebankInMemoryTrendDataStore.getInstance().getCopyOfAllEventsByUserAddress();

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.MINUTE, -60);    //go back 1 hour
        Date oneHourAgo = cal.getTime();

        for (String userAddress : trends.keySet()) {
            List<SimplebankTrendEvent> trendsForUser = trends.get(userAddress);

            int numberOfTransferRequests = 0;
            for (SimplebankTrendEvent trendEvent : trendsForUser) {
                if (trendEvent.getResourceAccessed().equals("/simplebank/Transfer") &&
                        trendEvent.getTime().after(oneHourAgo)) {
                    numberOfTransferRequests++;
                }
            }

            if (numberOfTransferRequests > 30) {
                new AppSensorException("UT4", "user using transfer money feature too much",
                        "A user from address: " + userAddress +
                                " have accessed the transfer feature " + numberOfTransferRequests +
                                " number of times the last hour");
            }
        }
    }

    public static void checkNumberOfLoginsAndLogouts() {
        List<SimplebankTrendEvent> trendEvents =
                SimplebankInMemoryTrendDataStore.getInstance().getCopyOfAllEvents();

        int numberOfLogins = 0;
        int numberOfLogouts = 0;

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.MINUTE, -5);
        Date fiveMinutesAgo = cal.getTime();

        for (SimplebankTrendEvent trendEvent : trendEvents) {
            if (trendEvent.getTime().after(fiveMinutesAgo)) {
                if (trendEvent.getResourceAccessed()
                        .equals(loginUri) && trendEvent.getAccessMethod().equals("POST")) {
                    numberOfLogins++;
                } else if (trendEvent.getResourceAccessed().equals(
                        logoutUri) && trendEvent.getAccessMethod().equals("GET")) {
                    numberOfLogouts++;
                }
            }
        }
        if (numberOfLogins > 50) {
            new AppSensorException("STE2", "User mesage STE2",
                    "A large number of login attempts have been made. \n" +
                            "The total number in the last five minutes is: " + numberOfLogins);
        }
        if (numberOfLogouts > 50) {
            new AppSensorException("STE2", "User mesage STE2",
                    "A large number of logout attempts have been made. \n" +
                            "The total number in the last five minutes is: " + numberOfLogouts);
        }
    }

    public static void checkNumberOfAccessToASpecificResource() {
        HashMap<String, List<SimplebankTrendEvent>> trends =
                SimplebankInMemoryTrendDataStore.getInstance().getCopyOfAllEventsByResource();

        for (String resource : trends.keySet()) {
            List<SimplebankTrendEvent> eventsForResouces = trends.get(resource);

            Calendar cal = new GregorianCalendar();
            cal.add(Calendar.MINUTE, -5);
            Date fiveMinutesAgo = cal.getTime();

            int numberOfAccessesToResource = 0;

            for (SimplebankTrendEvent trendEvent : eventsForResouces) {
                if (!trendEvent.getResourceAccessed().equals(loginUri) && !trendEvent.getResourceAccessed().equals(logoutUri)) {
                    if (trendEvent.getTime().after(fiveMinutesAgo)) {
                        numberOfAccessesToResource++;
                    }

                }
            }

            if (numberOfAccessesToResource > 50) {
                new AppSensorException("STE3", "AppSensor STE3 exception",
                        "The resource: " + resource +
                                " Was accessed: " + numberOfAccessesToResource +
                                " Times in the last five minutes");
            }

        }
    }

}
