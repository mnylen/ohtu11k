package fi.helsinki.cs.oato;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Schedule.
 */
public class Schedule {
    private Collection<Event> events;

    public Schedule() {
        this.events = new ArrayList<Event>();
    }

    public Schedule(Collection<Event> events) {
        this.events = new ArrayList<Event>(events);
    }

    public Collection<Event> getEvents() {
        return events;
    }
}
