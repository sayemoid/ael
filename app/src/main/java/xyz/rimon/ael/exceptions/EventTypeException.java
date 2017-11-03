package xyz.rimon.ael.exceptions;

/**
 * Created by SAyEM on 3/11/17.
 */

public class EventTypeException extends  IllegalArgumentException {
    public EventTypeException() {
    }

    public EventTypeException(String s) {
        super(s);
    }
}
