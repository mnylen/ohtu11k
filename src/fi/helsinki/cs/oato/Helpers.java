package fi.helsinki.cs.oato;

import java.util.*;
import org.joda.time.*;
import org.joda.time.format.*;

public class Helpers {
    public static Date toJavaDate(DateTime dt) {
        return new Date(dt.getMillis());
    }
    public static DateTime toJodaDate(Date d) {
        return new DateTime(d.getTime());
    }
    
    public static final DateTimeFormatter finnishDateTime  = DateTimeFormat.forPattern("d.M.YYYY H:mm");
    public static final DateTimeFormatter finnishTime      = DateTimeFormat.forPattern("H:mm");
    public static final DateTimeFormatter finnishDate      = DateTimeFormat.forPattern("d.M.YYYY");
    
    public static String toFinnishDateTime(DateTime dt) {
        return finnishDateTime.print(dt);
    }

    public static String toFinnishTime(DateTime dt) {
        return finnishTime.print(dt);
    }

    public static String toFinnishDate(DateTime dt) {
        return finnishDate.print(dt);
    }
}