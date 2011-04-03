package fi.helsinki.cs.oato.model;

import java.util.*;

/**
 * Container for events.
 */
public class Schedule {
	
	// XXX should we allow same object to be in the list several times? that's the case atm.
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
    public Collection<Event> getEvents() {
        return Collections.unmodifiableCollection(events);
    }

    /**
     * Gets an iterator over all scheduled events.
     * <p>
     * The iterator returns all upcoming events in the order by starting
     * date and time.
     *
     * @return <code>Iterator</code> over upcoming events
     */
    public Iterator<Event> nextEvents() {
        return new EventIterator(this.getEvents(), true);
    }
    
    /**
     * Gets an iterator over all scheduled events.
     * <p>
     * The iterator returns all  events in the order by starting
     * date and time.
     *
     * @return <code>Iterator</code> over upcoming events
     */
    public Iterator<Event> allEvents() {
        return new EventIterator(this.getEvents(), false);
    }
    
    /**
     * Adds event to the Schedule.
     * 
     * @param event Event to be added.
     * 
     * @return <code>true</code> when adding has been successful and <code>false</code> when adding has failed.
     */
    public boolean addEvent(Event event) {
    	return this.events.add(event);
    }
}
