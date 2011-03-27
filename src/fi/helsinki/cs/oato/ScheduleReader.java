package fi.helsinki.cs.oato;

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
