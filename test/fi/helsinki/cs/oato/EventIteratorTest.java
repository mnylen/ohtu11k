package fi.helsinki.cs.oato;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Collection;

public class EventIteratorTest {
    private Event pastEvent;
    private Event firstUpcomingEvent;
    private Event secondUpcomingEvent;
    private Collection<Event> allEvents;

    @Before
    public void setUp() {
        this.pastEvent           = EventFixtures.createPastEvent(EventFixtures.ONE_DAY);
        this.firstUpcomingEvent  = EventFixtures.createUpcomingEvent(EventFixtures.ONE_HOUR);
        this.secondUpcomingEvent = EventFixtures.createUpcomingEvent(EventFixtures.ONE_DAY);

        this.allEvents = new ArrayList<Event>();
        this.allEvents.add(this.firstUpcomingEvent);
        this.allEvents.add(this.pastEvent);
        this.allEvents.add(this.secondUpcomingEvent);
    }

    @Test
    public void testReturnsEventsInOrder() {
        EventIterator iterator = new EventIterator(this.allEvents, false);

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(sameInstance(this.pastEvent)));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(sameInstance(this.firstUpcomingEvent)));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(sameInstance(this.secondUpcomingEvent)));
        assertThat(iterator.hasNext(), is(false));
    }
}
