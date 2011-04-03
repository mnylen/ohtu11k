package fi.helsinki.cs.oato.io;

import com.csvreader.CsvWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import fi.helsinki.cs.oato.model.Event;
import fi.helsinki.cs.oato.model.Schedule;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class CsvScheduleWriter extends ScheduleWriter {
    public static final String HEADER_START_DATE = "start";
    public static final int HEADER_START_DATE_INDEX = 0;
    public static final String HEADER_END_DATE = "end";
    public static final int HEADER_END_DATE_INDEX = 1;
    public static final String HEADER_DESCRIPTION = "description";
    public static final int HEADER_DESCRIPTION_INDEX = 2;
    public static final String HEADER_LOCATION = "location";
    public static final int HEADER_LOCATION_INDEX = 3;
    public static final char SEPARATOR = ',';
    public static final DateTimeFormatter DATE_FORMATTER = ISODateTimeFormat.basicDateTimeNoMillis();
    public static final Charset CHARSET = Charset.forName("UTF-8");
    private static final int COLUMN_COUNT = 4;

    private OutputStream out;

    public CsvScheduleWriter(OutputStream out) {
        this.out = out;
    }
    
    public void write(Schedule schedule) throws IOException {
        CsvWriter writer = new CsvWriter(this.out, SEPARATOR, CHARSET);
        writeHeaderRecord(writer);

        for (Event event : schedule.getEvents()) {
            writeEventRecord(writer, event);
        }

        writer.flush();
        this.out.flush();
    }

    private void writeHeaderRecord(CsvWriter writer) throws IOException {
        String[] headers = new String[COLUMN_COUNT];
        headers[HEADER_START_DATE_INDEX]  = HEADER_START_DATE;
        headers[HEADER_END_DATE_INDEX]    = HEADER_END_DATE;
        headers[HEADER_DESCRIPTION_INDEX] = HEADER_DESCRIPTION;
        headers[HEADER_LOCATION_INDEX]    = HEADER_LOCATION;

        writer.writeRecord(headers);
    }

    private void writeEventRecord(CsvWriter writer, Event event) throws IOException {
        String[] record = new String[COLUMN_COUNT];
        record[HEADER_START_DATE_INDEX]  = DATE_FORMATTER.print(event.getStartDate());
        record[HEADER_END_DATE_INDEX]    = DATE_FORMATTER.print(event.getEndDate());
        record[HEADER_DESCRIPTION_INDEX] = event.getDescription();
        record[HEADER_LOCATION_INDEX]    = event.getLocation();
        
        writer.writeRecord(record);
    }
}
