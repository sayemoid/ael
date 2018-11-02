package xyz.rimon.ael;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.rimon.ael.commons.Commons;
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

        EventFactory.getInstance().createEvent(Event.Type.USER_EVENT, "test_tag1", Event.Weight.LOW);

        btnLogEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event e1 = EventRegistry.getInstance().getEventByTag("test_tag1");
                Map<String,String> bundle = new HashMap<>();
                bundle.put("Name","Sayem");
                bundle.put("click","btn");
                bundle.put("phone","01710226163");
                e1.setEventData(bundle);
                Ael.logEvent(MainActivity.this, e1);
            }
        });

        btnShowEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Event> eventList = Ael.getEvents(MainActivity.this);
                tv.setText(Commons.buildGson().toJson(eventList));

                Log.i("GSON", Commons.buildGson().toJson(eventList));
            }
        });

    }

    @Subscribe
    public void onLogEvent(UserEvent event) {
        Toast.makeText(this, event.toString(), Toast.LENGTH_SHORT).show();
    }
}
