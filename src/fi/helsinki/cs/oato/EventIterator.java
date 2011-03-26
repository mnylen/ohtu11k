package fi.helsinki.cs.oato;

import java.util.*;

public class EventIterator implements Iterator<Event> {
    private List<Event> events;
    private Iterator<Event> innerIterator;
    
    private boolean discardPast;

    public EventIterator(Collection<Event> events, boolean discardPast) {
        this.events = new ArrayList<Event>(events);
        Collections.sort(this.events);

        this.discardPast   = discardPast;
        this.innerIterator = this.events.iterator();
    }

    public boolean hasNext() {
        return innerIterator.hasNext();
    }

    public Event next() {
        return innerIterator.next();
    }

    public void remove() {
        throw new UnsupportedOperationException(
                "remove() operation is not supported by EventIterator");
    }
}
