package fi.helsinki.cs.oato;

import java.util.*;

/**
 * Container for events.
 */
public class Schedule {
    private Collection<Event> events;

    /**
     * Constructs an empty schedule.
     */
    public Schedule() {
        this.events = new ArrayList<Event>();
    }

    /**
     * Constructs a schedule with specified events.
     * @param events the events
     */
    public Schedule(Collection<Event> events) {
        this.events = new ArrayList<Event>(events);
    }

    public Collection<Event> getEvents() {
        return events;
    }
}
