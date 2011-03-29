package fi.helsinki.cs.oato;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;

public class CsvScheduleReaderTest {
    private Schedule schedule;
    private CsvScheduleReader reader;
    private Event dentistAppointment;
    private Event softwareEngineeringLecture;

    @Before
    public void setUp() throws IOException {
        this.dentistAppointment         = EventFixtures.createDentistAppointment();
        this.softwareEngineeringLecture = EventFixtures.createSoftwareEngineeringLecture();

        ArrayList<Event> events = new ArrayList<Event>();
        events.add(this.dentistAppointment);
        events.add(this.softwareEngineeringLecture);
        
        this.schedule = new Schedule(events);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ScheduleWriter writer     = new CsvScheduleWriter(out);
        writer.write(this.schedule);

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        this.reader             = new CsvScheduleReader(in);
    }

    @Test
    public void testRead() throws Exception {
        Schedule readSchedule = this.reader.read();
        assertThat(readSchedule.allEvents().size(), is(schedule.allEvents().size()));

        assertThat(readSchedule.allEvents().contains(this.dentistAppointment), is(true));
        assertThat(readSchedule.allEvents().contains(this.softwareEngineeringLecture), is(true));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testReadWithInvalidDates() throws Exception {
        byte[] rawData = ("start,end,description,location\n" +
                         "invalid date,another invalid date,Some event,Some location\n").getBytes();

        this.reader = new CsvScheduleReader(new ByteArrayInputStream(rawData));
        this.reader.read();
    }
}
