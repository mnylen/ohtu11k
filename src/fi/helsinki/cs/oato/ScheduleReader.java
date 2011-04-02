package fi.helsinki.cs.oato;

import fi.helsinki.cs.oato.model.Schedule;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

public abstract class ScheduleReader {
    protected InputStream in;

    public ScheduleReader(InputStream in) {
        this.in = in;
    }

    public abstract Schedule read() throws IOException, ParseException;
    
}
