package xyz.rimon.ael.domains;

import java.util.Date;

/**
 * Created by SAyEM on 3/11/17.
 */

public class AppEvent extends Event {
    private String triggeredFrom;

    public AppEvent(String code,String tag,byte rating){
        this.code = code;
        this.rating = rating;
        this.tag = tag;
        this.date = new Date();
        this.type = Type.APP_EVENT;
    }
    public AppEvent(String triggeredFrom,String name,String code,String tag,byte rating) {
        this.triggeredFrom = triggeredFrom;
        this.name = name;
        this.code = code;
        this.tag = tag;
        this.rating = rating;
        this.date = new Date();
        this.type = Type.APP_EVENT;
    }

    public String getTriggeredFrom() {
        return triggeredFrom;
    }

    public void setTriggeredFrom(String triggeredFrom) {
        this.triggeredFrom = triggeredFrom;
    }

    @Override
    public String toString() {
        return "AppEvent{" +
                "triggeredFrom='" + triggeredFrom + '\'' +
                ", id=" + id +
                ", eventName='" + name + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", tag='" + tag + '\'' +
                ", rating=" + rating +
                ", date=" + date +
                "} " + super.toString();
    }
}
