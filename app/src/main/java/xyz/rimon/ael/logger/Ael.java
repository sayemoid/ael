package xyz.rimon.ael.logger;

import android.app.Activity;
import android.content.Context;

import org.apache.commons.collections4.IteratorUtils;
import org.greenrobot.eventbus.EventBus;

import java.util.Iterator;
import java.util.List;

import xyz.rimon.ael.commons.utils.StorageUtil;
import xyz.rimon.ael.domains.Event;
import xyz.rimon.ael.exceptions.EventTypeException;

/**
 * Created by SAyEM on 3/11/17.
 */

public class Ael {
    private Ael() {
    }

    public static void register(Object object){
        EventBus.getDefault().register(object);
    }
    public static void unregister(Object object){
        EventBus.getDefault().unregister(object);
    }

    public static void logEvent(Context context, Event event) {
        StorageUtil.writeObject(context, StorageUtil.FILE_NAME, event);
        EventBus.getDefault().post(event);
    }

    public static List<Event> getEvents(Activity context) {
        return StorageUtil.readObjects(context, StorageUtil.FILE_NAME);
    }

    public static List<Event> getEvents(Activity context, Event.Type eventType) {
        if (eventType == null) throw new EventTypeException("Event type can not be null");
        List<Event> eventList = StorageUtil.readObjects(context, StorageUtil.FILE_NAME);
        Iterator<Event> iterator = eventList.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getType().equals(eventType))
                iterator.remove();
        }
        return IteratorUtils.toList(iterator);
    }
}
