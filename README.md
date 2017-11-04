# Android Event Logger
Android Event Logger is a library to log your app events on local storage. Access your logged events anytime you want.

<b>Create Events</b><br/>
```Event event = EventFactory.getInstance().createEvent(Event.Type.USER_EVENT, "event_code", "event_tag", (byte) 4):```
<br/>You ccan create as much eventss as you want for future use. All of the events will be registered to <b>EventRegistry</b>.
<br/>You can find any specific event by tag or event code.
```Event e = EventRegistry.getInstance().getEventByTag("event_tag");```

<b>Log Events</b><br/>
```Ael.logEvent(this, e);```

<b>Get logged Events</b><br/>
All Logged events<br/>
```List<Event> eventList = Ael.getEvents(this);```
By Event Type<br/>
``` List<Event> eventList = Ael.getEvents(context, eventType)```
