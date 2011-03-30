package fi.helsinki.cs.oato;

public class Helpers {
    public static java.util.Date toJavaDate(org.joda.time.DateTime dt) {
        return new java.util.Date(dt.getMillis());
    }
    public static org.joda.time.DateTime toJodaDate(java.util.Date d) {
        return new org.joda.time.DateTime(d.getTime());
    }
}