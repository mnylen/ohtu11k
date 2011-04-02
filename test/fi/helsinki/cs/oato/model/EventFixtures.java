package fi.helsinki.cs.oato.model;

import fi.helsinki.cs.oato.model.Event;
import org.joda.time.DateTime;

public abstract class EventFixtures {
    public static final long ONE_HOUR  = 3600000;
    public static final long TEN_HOURS = ONE_HOUR * 10;
    public static final long ONE_DAY   = ONE_HOUR * 24;
    
    public static Event createDentistAppointment() {
        return new Event(
                new DateTime(2011, 3, 25, 16, 0, 0, 0),
                new DateTime(2011, 3, 25, 17, 0, 0, 0),
                "Dentist",
                "Unit Avenue 768");
    }

    public static Event createSoftwareEngineeringLecture() {
        return new Event(
                new DateTime(2011, 3, 25, 12, 0, 0,0),
                new DateTime(2011, 3, 25, 14, 0, 0, 0),
                "Lecture on Software Engineering",
                "Exactum, CK112");
    }

    public static Event createUpcomingEvent(long millisInFuture) {
        return new Event(
                new DateTime(System.currentTimeMillis()+millisInFuture),
                new DateTime(System.currentTimeMillis()+millisInFuture),
                "Event",
                "Everywhere");
    }

    public static Event createPastEvent(long millisInPast) {
        return new Event(
                new DateTime(System.currentTimeMillis()-millisInPast),
                new DateTime(System.currentTimeMillis()-millisInPast),
                "Event",
                "Everywhere");
    }
}
