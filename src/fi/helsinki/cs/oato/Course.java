package fi.helsinki.cs.oato;

import java.util.*;
import org.joda.time.DateTime;

/**
    A course with a start date, an end date and a description (name). Fetched from online by OnlineCourseSource.
*/
public class Course {
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
        return getDescription() + " " + OnlineCourseSource.formatter.print(startDate) + ".." +
               OnlineCourseSource.formatter.print(endDate);
    }
}