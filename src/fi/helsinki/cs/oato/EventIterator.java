package fi.helsinki.cs.oato;

import java.util.*;

/**
 * Iterator over a collection of events. Returns events in ascending order by
 * the starting date.
 */
public class EventIterator implements Iterator<Event> {
    private Event firstEvent;
    private Iterator<Event> innerIterator;
    private boolean discardPast;

    /**
     * Constructs a new <code>EventIterator</code> for the specified collection.
     *
     * @param events the event collection
     * @param discardPast if <code>true</code>, does not return past events;
     *                    if <code>false</code>, returns past events
     */
    public EventIterator(Collection<Event> events, boolean discardPast) {
        List<Event> sortedEvents = new ArrayList<Event>(events);
        Collections.sort(sortedEvents);

        this.discardPast   = discardPast;
        this.innerIterator = sortedEvents.iterator();

        if (this.discardPast) {
            rewindToFirstUpcomingEvent();
        }
    }

    public boolean hasNext() {
        if (this.firstEvent != null) {
            return true;
        } else {
            return this.innerIterator.hasNext();
        }
    }

    public Event next() {
        Event nextEvent = null;

        if (this.firstEvent != null) {
            nextEvent = this.firstEvent;
            this.firstEvent = null;
        } else {
            nextEvent = this.innerIterator.next();
        }

        return nextEvent;
    }

    /**
     * @throws UnsupportedOperationException always
     */
    public void remove() {
        throw new UnsupportedOperationException(
                "remove() operation is not supported by EventIterator");
    }

    /**
     * Rewinds the inner iterator to first upcoming event and stores the
     * event to <code>firstEvent</code> instance variable.
     */
    private void rewindToFirstUpcomingEvent() {
        long timeMillis = System.currentTimeMillis();
        Event event = null;

        while (this.innerIterator.hasNext()) {
            event = this.innerIterator.next();

            if (event.getStartDate().getTime() >= timeMillis) {
                break;
            }
        }

        if (event != null) {
            this.firstEvent = event;
        }
    }
}
