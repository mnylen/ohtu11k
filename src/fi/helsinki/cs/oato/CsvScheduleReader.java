package fi.helsinki.cs.oato;

import com.csvreader.CsvReader;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

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
            Date startDate     = null;
            Date endDate       = null;
            DateFormat format  = CsvScheduleWriter.DATE_FORMAT;

            try {
                startDate = format.parse(reader.get(CsvScheduleWriter.HEADER_START_DATE_INDEX));
                endDate   = format.parse(reader.get(CsvScheduleWriter.HEADER_END_DATE_INDEX));
            } catch (ParseException e) {
                throw e;
            }

            events.add(new Event(startDate, endDate, description, location));
        }

        return new Schedule(events);
    }

}
