package fi.helsinki.cs.oato.io;

import fi.helsinki.cs.oato.model.Schedule;

import java.io.IOException;

public abstract class ScheduleWriter {

    public abstract void write(Schedule schedule) throws IOException;
    
}
