package xyz.rimon.ael;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import xyz.rimon.ael.domains.Event;
import xyz.rimon.ael.domains.UserEvent;
import xyz.rimon.ael.factory.EventFactory;
import xyz.rimon.ael.logger.Ael;
import xyz.rimon.ael.registry.EventRegistry;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        Ael.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Ael.unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogEvent = this.findViewById(R.id.btnLogEvent);
        Button btnShowEvent = this.findViewById(R.id.btnShowEvents);
        final TextView tv = this.findViewById(R.id.events);

        Event event = EventFactory.getInstance().createEvent(Event.Type.USER_EVENT, "test_code1", "test_tag1", (byte) 4);
        Event even2 = EventFactory.getInstance().createEvent(Event.Type.USER_EVENT, "test_code2", "test_tag2", (byte) 3);
        Event even3 = EventFactory.getInstance().createEvent(Event.Type.USER_EVENT, "test_code3", "test_tag3", (byte) 5);

        btnLogEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event e = EventRegistry.getInstance().getEventByTag("test_tag2");
                if (e == null) {
                    Toast.makeText(getApplicationContext(), "Could not find event!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Ael.logEvent(MainActivity.this, e);
            }
        });

        btnShowEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Event> eventList = Ael.getEvents(MainActivity.this);
                StringBuilder sb = new StringBuilder();
                for (Event e : eventList) {
                    sb.append(e.getTag());
                    sb.append("\n");
                }
                tv.setText(sb.toString());
            }
        });

    }

    @Subscribe
    public void onLogEvent(UserEvent event) {
        Toast.makeText(this, event.toString(), Toast.LENGTH_SHORT).show();
    }
}
