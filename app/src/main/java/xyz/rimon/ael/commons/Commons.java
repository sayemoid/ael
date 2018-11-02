package xyz.rimon.ael.commons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import xyz.rimon.ael.commons.utils.DateUtils;

/**
 * Created by SAyEM on 15/11/17.
 */

public class Commons {
    public static Gson buildGson() {
        // Creates the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();

        builder.setDateFormat(DateUtils.SERVER_DATE_TIME_PATTERN);
        // Register an adapter to manage the date types as long values
//        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
//            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//                return new Date(json.getAsJsonPrimitive().getAsLong());
//            }
//        });
        return builder.create();
    }
}
