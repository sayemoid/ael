package xyz.rimon.ael.factory;

import xyz.rimon.ael.domains.AppEvent;
import xyz.rimon.ael.domains.ErrorEvent;
import xyz.rimon.ael.domains.Event;
import xyz.rimon.ael.domains.UserEvent;
import xyz.rimon.ael.registry.EventRegistry;

/**
 * Created by SAyEM on 3/11/17.
 */

public class EventFactory {
    private static EventFactory eventFactory;

    private EventFactory() {
    }

    public static EventFactory getInstance() {
        if (eventFactory != null) return eventFactory;
        else return eventFactory = new EventFactory();
    }

    public Event createEvent(Event.Type eventType, String code, String tag, byte rating) {
        if (eventType == null || code == null || tag == null)
            throw new IllegalArgumentException("Event type, code or tag can not be null");
        if (rating > 5 || rating <= 0)
            throw new IllegalArgumentException("Rating should be between 1-5");

        if (eventType.equals(Event.Type.USER_EVENT)) {
            Event e = new UserEvent(code, tag, rating);
            e = EventRegistry.getInstance().register(e);
            return e;
        } else if (eventType.equals(Event.Type.APP_EVENT)) {
            Event e = new AppEvent(code, tag, rating);
            e = EventRegistry.getInstance().register(e);
            return e;
        } else if (eventType.equals(Event.Type.ERROR_EVENT)) {
            Event e = new ErrorEvent(code, tag, rating);
            e = EventRegistry.getInstance().register(e);
            return e;
        }

        return new UserEvent(code, tag, rating);
    }

}
