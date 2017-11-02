package xyz.rimon.ael.registry;

import java.util.HashMap;

import xyz.rimon.ael.domains.Event;

/**
 * Created by SAyEM on 3/11/17.
 */

public class EventRegistry extends HashMap<String, Event> {
    private static int id = 0;
    private static EventRegistry registry;

    private EventRegistry() {
    }

    public static EventRegistry getInstance() {
        if (registry != null) return registry;
        return registry = new EventRegistry();
    }

    public Event register(Event event) {
        if (event.getId() == 0) event.setId(++id);
        this.put(event.getTag(), event);
        return event;
    }

    public Event getEventByTag(String tag) {
        return this.get(tag);
    }

    public Event getEventById(int id) {
        for (Event event : values()) {
            if (event.getId() == id) return event;
        }
        return null;
    }
}
