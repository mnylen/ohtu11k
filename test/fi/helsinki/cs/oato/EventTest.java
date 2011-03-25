package fi.helsinki.cs.oato;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.GregorianCalendar;

public class EventTest {
    @Test
    public void testEquals() {
        Event dentistAppointment = new Event(
                new GregorianCalendar(2011, 3, 25, 16, 0, 0).getTime(),
                new GregorianCalendar(2011, 3, 25, 17, 0, 0).getTime(),
                "Dentist",
                "Unit Avenue 789");
        assertThat(dentistAppointment, equalTo(dentistAppointment));

        Event sameDentistAppointment = new Event(
                new GregorianCalendar(2011, 3, 25, 16, 0, 0).getTime(),
                new GregorianCalendar(2011, 3, 25, 17, 0, 0).getTime(),
                "Dentist",
                "Unit Avenue 789");
        assertThat(sameDentistAppointment, equalTo(dentistAppointment));
        
        Event softwareEngineeringLecture = new Event(
                new GregorianCalendar(2011, 3, 25, 12, 0, 0).getTime(),
                new GregorianCalendar(2011, 3, 25, 14, 0, 0).getTime(),
                "Lecture on Software Engineering",
                "Exactum, CK112");
        assertThat(softwareEngineeringLecture, not(equalTo(dentistAppointment)));
    }
}
