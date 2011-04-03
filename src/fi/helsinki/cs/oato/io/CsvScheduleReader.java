package fi.helsinki.cs.oato.io;

import com.csvreader.CsvReader;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;

import fi.helsinki.cs.oato.model.Event;
import fi.helsinki.cs.oato.model.Schedule;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

public class CsvScheduleReader extends ScheduleReader {
    public CsvScheduleReader(InputStream in) {
        super(in);
    }

    public Schedule read() throws IOException, ParseException {
        CsvReader reader = new CsvReader(
                this.in,
                CsvScheduleWriter.SEPARATOR,
                CsvScheduleWriter.CHARSET);
        reader.setSkipEmptyRecords(true);
        reader.readRecord(); // read out the header
        
        ArrayList<Event> events = new ArrayList<Event>();
        while (reader.readRecord()) {
            String description = reader.get(CsvScheduleWriter.HEADER_DESCRIPTION_INDEX);
            String location    = reader.get(CsvScheduleWriter.HEADER_LOCATION_INDEX);
            DateTime startDate     = null;
            DateTime endDate       = null;
            DateTimeFormatter formatter = CsvScheduleWriter.DATE_FORMATTER;

            startDate = formatter.parseDateTime(reader.get(CsvScheduleWriter.HEADER_START_DATE_INDEX));
            endDate   = formatter.parseDateTime(reader.get(CsvScheduleWriter.HEADER_END_DATE_INDEX));

            events.add(new Event(startDate, endDate, description, location));
        }

        return new Schedule(events);
    }

}
