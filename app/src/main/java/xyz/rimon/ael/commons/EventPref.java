package xyz.rimon.ael.commons;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import xyz.rimon.ael.domains.Event;

/**
 * Created by SAyEM on 2/11/18.
 */

public class EventPref {
    private static final String PREF_EVENT_LIST = "hdegdu3278463hude2";

    public static void saveEvent(Activity context, Event event) {
        List<Event> events = getEvents(context);
        if (events == null) events = new ArrayList();

        events.add(event);
        saveEvents(context, events);
        Log.i("EVENT_SAVED: " , String.valueOf(events.size()));
    }

    public static void saveEvents(Activity context, List<Event> events) {
        Gson gson = Commons.buildGson();
        Type typeToken = new TypeToken<List<Event>>() {
        }.getType();
        String s = gson.toJson(events, typeToken);
        Pref.savePreference(context, PREF_EVENT_LIST, s);
    }

    public static List<Event> getEvents(Activity context) {
        Gson gson = Commons.buildGson();
        Type typeToken = new TypeToken<List<Event>>() {
        }.getType();
        String s = Pref.getPreferenceString(context, PREF_EVENT_LIST);
        if (s == null) return null;
        return gson.fromJson(s, typeToken);
    }
}
