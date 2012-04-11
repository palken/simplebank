package no.ntnu.idi.simplebank.trend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: palt
 * Date: 4/11/12
 * Time: 14:55
 */
public class SimplebankInMemoryTrendDataStore {
    private static List<SimplebankTrendEvent> events = null;

    public synchronized void addEvent(SimplebankTrendEvent event) {
        initializeEventsIfNecessary();
        events.add(event);
    }

    public synchronized List<SimplebankTrendEvent> getCopyOfAllEvents() {
        initializeEventsIfNecessary();
        List<SimplebankTrendEvent> tempEvents = new ArrayList<SimplebankTrendEvent>();
        tempEvents.addAll(events);
        return tempEvents;
    }

    public synchronized HashMap<String, List<SimplebankTrendEvent>> getCopyOfAllEventsByResource() {
        initializeEventsIfNecessary();
        HashMap<String, List<SimplebankTrendEvent>> eventsByResource = new HashMap<String, List<SimplebankTrendEvent>>();

        //loop through all events
        for (SimplebankTrendEvent te : events) {
            String resource = te.getResourceAccessed();
            //if resource is already in map
            if (eventsByResource.containsKey(resource)) {
                List<SimplebankTrendEvent> tempEvents = eventsByResource.get(resource);
                tempEvents.add(te);
                eventsByResource.put(resource, tempEvents);
            } else {        //resource is not yet in map
                List<SimplebankTrendEvent> tempEvents = new ArrayList<SimplebankTrendEvent>();
                tempEvents.add(te);
                eventsByResource.put(resource, tempEvents);
            }
        }

        return eventsByResource;
    }

    public synchronized HashMap<String, List<SimplebankTrendEvent>> getCopyOfAllEventsByUserName() {
        initializeEventsIfNecessary();
        HashMap<String, List<SimplebankTrendEvent>> eventsByUserName = new HashMap<String, List<SimplebankTrendEvent>>();

        //loop through all events
        for (SimplebankTrendEvent te : events) {
            String userName = te.getAccessedByName();
            //if resource is already in map
            if (eventsByUserName.containsKey(userName)) {
                List<SimplebankTrendEvent> tempEvents = eventsByUserName.get(userName);
                tempEvents.add(te);
                eventsByUserName.put(userName, tempEvents);
            } else {        //resource is not yet in map
                List<SimplebankTrendEvent> tempEvents = new ArrayList<SimplebankTrendEvent>();
                tempEvents.add(te);
                eventsByUserName.put(userName, tempEvents);
            }
        }

        return eventsByUserName;
    }

    public synchronized HashMap<String, List<SimplebankTrendEvent>> getCopyOfAllEventsByUserAddress() {
        initializeEventsIfNecessary();
        HashMap<String, List<SimplebankTrendEvent>> eventsByUserAddress = new HashMap<String, List<SimplebankTrendEvent>>();

        //loop through all events
        for (SimplebankTrendEvent te : events) {
            String userAddress = te.getAccessedByAddress();
            //if resource is already in map
            if (eventsByUserAddress.containsKey(userAddress)) {
                List<SimplebankTrendEvent> tempEvents = eventsByUserAddress.get(userAddress);
                tempEvents.add(te);
                eventsByUserAddress.put(userAddress, tempEvents);
            } else {        //resource is not yet in map
                List<SimplebankTrendEvent> tempEvents = new ArrayList<SimplebankTrendEvent>();
                tempEvents.add(te);
                eventsByUserAddress.put(userAddress, tempEvents);
            }
        }

        return eventsByUserAddress;
    }

    private void initializeEventsIfNecessary() {
        if (events == null) {   //only happens once
            events = new ArrayList<SimplebankTrendEvent>();
        }
    }

    // singleton code below
    public static SimplebankInMemoryTrendDataStore getInstance() {
        return SimplebankInMemoryTrendDataStoreHolder.instance;
    }

    private SimplebankInMemoryTrendDataStore() {
    }

    private static final class SimplebankInMemoryTrendDataStoreHolder {
        static final SimplebankInMemoryTrendDataStore instance = new SimplebankInMemoryTrendDataStore();
    }
}


