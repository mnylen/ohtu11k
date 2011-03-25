package fi.helsinki.cs.oato;

import java.util.GregorianCalendar;

public class EventFixtures {

    public static Event createDentistAppointment() {
        return new Event(
                new GregorianCalendar(2011, 3, 25, 16, 0, 0).getTime(),
                new GregorianCalendar(2011, 3, 25, 17, 0, 0).getTime(),
                "Dentist",
                "Unit Avenue 768");
    }

    public static Event createSoftwareEngineeringLecture() {
        return new Event(
                new GregorianCalendar(2011, 3, 25, 12, 0, 0).getTime(),
                new GregorianCalendar(2011, 3, 25, 14, 0, 0).getTime(),
                "Lecture on Software Engineering",
                "Exactum, CK112");
    }

}
