package no.ntnu.idi.simplebank.trend;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: palt
 * Date: 4/11/12
 * Time: 14:44
 */
public class SimplebankTrendEvent {
    private Date time;
    private String resourceAccessed;
    private String accessedByName;
    private String accessedByAddress;
    private String accessMethod;

    public SimplebankTrendEvent(Date time, String resourceAccessed,
                                String accessedByName, String accessedByAddress, String accessMethod) {
        this.time = time;
        this.resourceAccessed = resourceAccessed;
        this.accessedByName = accessedByName;
        this.accessedByAddress = accessedByAddress;
        this.accessMethod = accessMethod;
    }

    public String getAccessMethod() {
        return accessMethod;
    }

    public Date getTime() {
        return time;
    }

    public String getResourceAccessed() {
        return resourceAccessed;
    }

    public String getAccessedByName() {
        return accessedByName;
    }

    public String getAccessedByAddress() {
        return accessedByAddress;
    }

}