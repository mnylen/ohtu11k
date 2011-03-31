package fi.helsinki.cs.oato;

import java.util.*;
import org.joda.time.DateTime;

import static fi.helsinki.cs.oato.Helpers.*;

/**
    A course with a start date, an end date and a description (name). Fetched from online by OnlineCourseSource.
*/
public class Course implements Comparable<Course>{
    private String description;
    private DateTime startDate;
    private DateTime endDate;

    /**
        Create from already parsed data.
    */
    Course(String description, DateTime startDate, DateTime endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public String toString() {
        return getDescription() + " (" + toFinnishDate(startDate) + "–" + toFinnishDate(endDate) + ")";
    }

    public int compareTo(Course other) {
        return this.getStartDate().compareTo(other.getStartDate());
    }

    public boolean equals(Course other) {
        return this.getDescription().equals(other.getDescription()) &&
               this.getStartDate().equals(other.getStartDate()) &&
               this.getEndDate().equals(other.getEndDate());
    }
}