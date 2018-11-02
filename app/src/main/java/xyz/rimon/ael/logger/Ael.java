package xyz.rimon.ael.logger;

import android.app.Activity;

import org.apache.commons.collections4.IteratorUtils;
import org.greenrobot.eventbus.EventBus;

import java.util.Iterator;
import java.util.List;

import xyz.rimon.ael.commons.EventPref;
import xyz.rimon.ael.commons.utils.StorageUtil;
import xyz.rimon.ael.config.Config;
import xyz.rimon.ael.domains.Event;
import xyz.rimon.ael.exceptions.EventTypeException;

/**
 * Created by SAyEM on 3/11/17.
 */

public class Ael {
    private Ael() {
    }

    public static void register(Object object) {
        EventBus.getDefault().register(object);
    }

    public static void unregister(Object object) {
        EventBus.getDefault().unregister(object);
    }

    public static void logEvent(Activity context, Event event) {
        if (Config.USE_STORAGE)
            StorageUtil.writeObject(context, StorageUtil.FILE_NAME, event);
        else
            EventPref.saveEvent(context, event);
        EventBus.getDefault().post(event);
    }

    public static List<Event> getEvents(Activity context) {
        return Config.USE_STORAGE ? StorageUtil.readObjects(context, StorageUtil.FILE_NAME) : EventPref.getEvents(context);
    }

    public static List<Event> getEvents(Activity context, Event.Type eventType) {
        if (eventType == null) throw new EventTypeException("Event type can not be null");
        List<Event> eventList = getEvents(context);
        Iterator<Event> iterator = eventList.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getType().equals(eventType))
                iterator.remove();
        }
        return IteratorUtils.toList(iterator);
    }
}
