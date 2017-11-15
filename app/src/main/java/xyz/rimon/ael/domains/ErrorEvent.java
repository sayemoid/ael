package xyz.rimon.ael.domains;

import java.util.Date;

/**
 * Created by SAyEM on 3/11/17.
 */

public class ErrorEvent extends Event {
    private Throwable error;
    private String message;
    private String triggeredFrom;

    public ErrorEvent(String tag, byte weight) {
        this.code = tag;
        this.weight = weight;
        this.tag = tag;
        this.date = new Date();
        this.type = Type.ERROR_EVENT;
    }
    public ErrorEvent(String code, String tag, byte weight) {
        this.code = code;
        this.weight = weight;
        this.tag = tag;
        this.date = new Date();
        this.type = Type.ERROR_EVENT;
    }

    public ErrorEvent(String triggeredFrom, Throwable throwable) {
        this.triggeredFrom = triggeredFrom;
        this.error = throwable;
        this.date = new Date();
        this.type = Type.ERROR_EVENT;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTriggeredFrom() {
        return triggeredFrom;
    }

    public void setTriggeredFrom(String triggeredFrom) {
        this.triggeredFrom = triggeredFrom;
    }

    @Override
    public String toString() {
        return "ErrorEvent{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", triggeredFrom='" + triggeredFrom + '\'' +
                '}';
    }
}
