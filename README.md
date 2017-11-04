# Android Event Logger
*Android Event Logger is a library to log your app events on local storage.* 

*Access your logged events anytime you want.*


<b>Create Events</b>

```
    Event event = EventFactory.getInstance().createEvent(Event.Type.USER_EVENT, "event_code", "event_tag", (byte) 4):
```

You ccan create as much events as you want for future use. All of the events will be registered to <b>EventRegistry</b>.

You can find any specific event by tag or event code.

```
    Event e = EventRegistry.getInstance().getEventByTag("event_tag");
```

<b>Log Events</b>

```
    Ael.logEvent(this, e);
```

<b>Get logged Events</b>

*All Logged events*

```
    List<Event> eventList = Ael.getEvents(this);
```

*By Event Type*

``` 
    List<Event> eventList = Ael.getEvents(context, eventType)
```

<b>Do something when an event is logged</b>

An event is published automatically when you log an event using Ael.logEnent(Context,Event) method.
You can subscribe to that event and execute some code.

```
@Subscribe
public void onLogEvent(UserEvent event) {
    Toast.makeText(this, event.toString(), Toast.LENGTH_SHORT).show();
}
```

<b> Add Ael to your project</b>

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
	
dependencies {
    compile 'com.github.sayemkcn:ael:rc~1.1-SNAPSHOT'
}

```