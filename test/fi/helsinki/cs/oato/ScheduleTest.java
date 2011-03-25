package fi.helsinki.cs.oato;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Collection;

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
}
