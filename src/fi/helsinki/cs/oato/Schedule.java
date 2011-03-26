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

    /**
     * Get all scheduled events.
     * <p>
     * Please note that the events collection is in insertion order. If order
     * by date is required, use the {@link #nextEvents()} method.
     * 
     * @return readonly collection of all scheduled events
     */
    public Collection<Event> allEvents() {
        return Collections.unmodifiableCollection(events);
    }
}
