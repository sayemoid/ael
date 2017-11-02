package xyz.rimon.ael;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import xyz.rimon.ael.domains.Event;
import xyz.rimon.ael.factory.EventFactory;
import xyz.rimon.ael.registry.EventRegistry;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = this.findViewById(R.id.events);

        Event event = EventFactory.getInstance().createEvent(Event.Type.USER_EVENT, "test_code1", "test_tag1", (byte) 4);
        Event even2 = EventFactory.getInstance().createEvent(Event.Type.USER_EVENT, "test_code2", "test_tag2", (byte) 3);
        Event even3 = EventFactory.getInstance().createEvent(Event.Type.USER_EVENT, "test_code3", "test_tag3", (byte) 5);

        StringBuilder tags = new StringBuilder();
        for (Event e : EventRegistry.getInstance().values()) {
            tags.append(e.getTag()+"\n");
        }

        tv.setText(tags.toString());
    }
}
