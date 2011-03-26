package fi.helsinki.cs.oato;

import java.util.Collection;
import java.util.Iterator;

public class EventIterator implements Iterator<Event> {
    private Collection<Event> events;
    private boolean discardPast;

    public EventIterator(Collection<Event> events, boolean discardPast) {
        this.events = events;
        this.discardPast = discardPast;
    }
}
