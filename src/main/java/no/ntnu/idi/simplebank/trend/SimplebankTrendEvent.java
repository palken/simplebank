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

    public void setAccessMethod(String accessMethod) {
        this.accessMethod = accessMethod;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getResourceAccessed() {
        return resourceAccessed;
    }

    public void setResourceAccessed(String resourceAccessed) {
        this.resourceAccessed = resourceAccessed;
    }

    public String getAccessedByName() {
        return accessedByName;
    }

    public void setAccessedByName(String accessedByName) {
        this.accessedByName = accessedByName;
    }

    public String getAccessedByAddress() {
        return accessedByAddress;
    }

    public void setAccessedByAddress(String accessedByAddress) {
        this.accessedByAddress = accessedByAddress;
    }
}