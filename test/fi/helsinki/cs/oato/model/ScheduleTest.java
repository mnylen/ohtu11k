package fi.helsinki.cs.oato.model;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ScheduleTest {
    private Event dentistAppointment;
    private Event softwareEngineeringLecture;
    
    @Before
    public void setUp() {
        this.dentistAppointment = EventFixtures.createDentistAppointment();
        this.softwareEngineeringLecture = EventFixtures.createSoftwareEngineeringLecture();
    }

    @Test
    public void testConstructsWithEventsCollection() {
        Collection<Event> events = new ArrayList<Event>();
        events.add(this.dentistAppointment);
        events.add(this.softwareEngineeringLecture);

        Schedule schedule = new Schedule(events);
        Collection<Event> actualEvents = schedule.getEvents();

        assertThat(actualEvents.contains(this.dentistAppointment), is(true));
        assertThat(actualEvents.contains(this.softwareEngineeringLecture), is(true));
        assertThat(actualEvents, is(not(sameInstance(events))));
    }

    @Test
    public void testAllEventsIsUnmodifiable() {
        Schedule schedule = new Schedule();
        Collection<Event> allEvents = schedule.getEvents();

        try {
            allEvents.add(this.dentistAppointment);
            fail("allEvents() should return unmodifiable collection");
        } catch (UnsupportedOperationException e) {
            assertThat(allEvents.contains(this.dentistAppointment), is(false));
        }
    }

    @Test
    public void testNextEvents() {
        Schedule schedule = new Schedule();
        Iterator<Event> nextEvents = schedule.nextEvents();

        assertThat(nextEvents, not(nullValue()));
        assertThat(nextEvents, instanceOf(EventIterator.class));
        assertThat(((EventIterator)nextEvents).isPastDiscarded(), is(true));
    }
    
    @Test
    public void testAddingAdds() {
	    Schedule schedule = new Schedule();
	    
	    assertThat( schedule.getEvents().size() , is(0) );
	    assertThat( schedule.getEvents().contains( this.dentistAppointment ), is(false) );
	    
	    boolean retVal = schedule.addEvent( this.dentistAppointment );
	    assertThat( retVal , is(true) );
	    assertThat( schedule.getEvents().size() , is(1) );
	    assertThat( schedule.getEvents().contains( this.dentistAppointment ), is(true) );
    }
    
    @Test
    public void testRemoving() {
	    Schedule schedule = new Schedule();
	    
	    schedule.addEvent( this.dentistAppointment );
	    assertThat( schedule.getEvents().size() , is(1) );
	    assertThat( schedule.getEvents().contains( this.dentistAppointment ), is(true) );
	    
	    schedule.removeEvent( this.dentistAppointment );
		assertThat( schedule.getEvents().size() , is(0) );
	    assertThat( schedule.getEvents().contains( this.dentistAppointment ), is(false) );
	    
    }
}
