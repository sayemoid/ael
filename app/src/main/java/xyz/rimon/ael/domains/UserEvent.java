package xyz.rimon.ael.domains;

import java.util.Date;

/**
 * Created by SAyEM on 3/11/17.
 */

public class UserEvent extends Event {
    private User user;
    private String actionType;

    public UserEvent(String code, String tag,byte rating){
        this.code = code;
        this.rating = rating;
        this.tag = tag;
        this.date = new Date();
        this.type = Type.USER_EVENT;
    }

    public UserEvent(String name,String code,String tag,byte rating) {
        this.name = name;
        this.code = code;
        this.tag = tag;
        this.rating = rating;
        this.date = new Date();
        this.type = Type.USER_EVENT;
    }
    public UserEvent(String name,String code,String tag,byte rating,User user) {
        this.user = user;
        this.name = name;
        this.code = code;
        this.tag = tag;
        this.rating = rating;
        this.date = new Date();
        this.type = Type.USER_EVENT;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "user=" + user +
                ", id=" + id +
                ", actionType='" + actionType + '\'' +
                ", eventName='" + name + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", tag='" + tag + '\'' +
                ", rating=" + rating +
                ", date=" + date +
                "} " + super.toString();
    }
}
