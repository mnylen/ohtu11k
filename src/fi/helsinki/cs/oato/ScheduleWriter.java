package fi.helsinki.cs.oato;

import java.io.IOException;
import java.nio.charset.Charset;

public abstract class ScheduleWriter {

    public abstract void write(Schedule schedule) throws IOException;
    
}
