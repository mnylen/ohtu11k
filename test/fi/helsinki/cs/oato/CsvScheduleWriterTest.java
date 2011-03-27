package fi.helsinki.cs.oato;

import com.csvreader.CsvReader;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CsvScheduleWriterTest {
    private Schedule schedule;
    private Event dentistAppointment;
    private Event softwareEngineeringLecture;
    
    @Before
    public void setUp() {
        this.dentistAppointment         = EventFixtures.createDentistAppointment();
        this.softwareEngineeringLecture = EventFixtures.createSoftwareEngineeringLecture();

        ArrayList<Event> events = new ArrayList<Event>();
        events.add(this.dentistAppointment);
        events.add(this.softwareEngineeringLecture);

        this.schedule = new Schedule(events);
    }

    @Test
    public void testWrite() throws IOException  {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ScheduleWriter writer     = new CsvScheduleWriter(out);
        writer.write(this.schedule);

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        CsvReader reader        = new CsvReader(
                in,
                CsvScheduleWriter.SEPARATOR,
                CsvScheduleWriter.CHARSET);

        assertCorrectHeaders(reader);
        assertNextRecordEqualTo(reader, this.dentistAppointment);
        assertNextRecordEqualTo(reader, this.softwareEngineeringLecture);
        assertThat(reader.readRecord(), is(false));
    }

    private void assertCorrectHeaders(CsvReader reader) throws IOException {
        reader.readHeaders();

        assertThat(reader.getHeader(CsvScheduleWriter.HEADER_START_DATE_INDEX),
                equalTo(CsvScheduleWriter.HEADER_START_DATE));

        assertThat(reader.getHeader(CsvScheduleWriter.HEADER_END_DATE_INDEX),
                equalTo(CsvScheduleWriter.HEADER_END_DATE));

        assertThat(reader.getHeader(CsvScheduleWriter.HEADER_DESCRIPTION_INDEX),
                equalTo(CsvScheduleWriter.HEADER_DESCRIPTION));

        assertThat(reader.getHeader(CsvScheduleWriter.HEADER_LOCATION_INDEX),
                equalTo(CsvScheduleWriter.HEADER_LOCATION));
    }

    private void assertNextRecordEqualTo(CsvReader reader, Event event) throws IOException {
        assertThat(reader.readRecord(), is(true));
        
        assertThat(reader.get(CsvScheduleWriter.HEADER_START_DATE_INDEX),
                equalTo(CsvScheduleWriter.DATE_FORMAT.format(
                        event.getStartDate())));

        assertThat(reader.get(CsvScheduleWriter.HEADER_END_DATE_INDEX),
                equalTo(CsvScheduleWriter.DATE_FORMAT.format(
                        event.getEndDate())));

        assertThat(reader.get(CsvScheduleWriter.HEADER_DESCRIPTION_INDEX),
                equalTo(event.getDescription()));

        assertThat(reader.get(CsvScheduleWriter.HEADER_LOCATION_INDEX),
                equalTo(event.getLocation()));
    }
}
