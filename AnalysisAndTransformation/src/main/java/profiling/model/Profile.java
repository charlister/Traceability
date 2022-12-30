package profiling.model;

import java.util.Date;

public class Profile {
    protected static long counter = 0;
    protected long id;
    protected long userId;
    protected Date timestamp;
    protected String event;
    protected String action;

    public Profile() {
        this.id = ++counter;
    }

    public Profile(long userId, Date timestamp, String event, String action) {
        this.id = ++counter;
        this.userId = userId;
        this.timestamp = timestamp;
        this.event = event;
        this.action = action;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", userId=" + userId +
                ", timestamp=" + timestamp +
                ", event='" + event + '\'' +
                ", action='" + action + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


}
