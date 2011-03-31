package fi.helsinki.cs.oato;
import static fi.helsinki.cs.oato.Helpers.*;

import org.joda.time.DateTime;
import java.util.Date;

/**
 * Represents an event that occurs at specific location and time.
 */
public class Event implements Comparable<Event> {
    private DateTime startDate;
    private DateTime endDate;
    private String description;
    private String location;

    /**
     * Construct a new <code>Event</code> with specified details.
     *
     * @param startDate the beginning date and time
     * @param endDate the ending date and time
     * @param description the description
     * @param location the location
     */
    public Event(DateTime startDate, DateTime endDate, String description,
                 String location) {

        this.startDate   = startDate;
        this.endDate     = endDate;
        this.description = description;
        this.location    = location;
    }

    /**
     * Get event start date.
     * @return the start date
     */
    public DateTime getStartDate() {
        return startDate;
    }

    /**
     * Get event start date as java.util.Date.
     * @return the start date
     */
    public Date getStartJavaDate() {
        return new Date(startDate.getMillis());
    }

     /**
     * Set event start date.
     * @param startDate the start date
     */
    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

     /**
     * Get event end date.
     * @return the end date
     */
    public DateTime getEndDate() {
        return endDate;
    }

     /**
     * Get event end date as java.util.Date.
     * @return the end date
     */
    public Date getEndJavaDate() {
        return new Date(endDate.getMillis());
    }

     /**
     * Set event end date.
     * @param endDate the end date
     */
    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * Get event description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set event description.
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get event location.
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set event location.
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Compares this event to given event.
     *
     * @param another the other event
     * @return <code>-1</code> if this event starts earlier than the other;
     *         <code>0</code> if the events start at the same time;
     *         <code>1</code> if this event starts later than the other
     */
    public int compareTo(Event another) {
        if (this.equals(another)) {
            return 0;
        } else {
            return this.getStartDate().compareTo(another.getStartDate());
        }
    }
    
    /**
     * Compares this event to given LocalDateTime.
     *
     * @param time time to compare to
     * @return <code>-1</code> if this event starts earlier than time;
     *         <code>0</code> if the events start exactly at time;
     *         <code>1</code> if this event starts later than time
     */
    public int compareTo(DateTime time) {
		return this.getStartDate().compareTo(time);
    }
    
    public boolean equals(Object another) {
        if (!(another instanceof Event)) {
            return false;
        }

        if (another == this) {
            return true;
        } else {
            Event other = (Event)another;
            
            boolean equals = this.getDescription().equals(other.getDescription());
            equals = equals && this.getLocation().equals(other.getLocation());
            equals = equals && this.getStartDate().equals(other.getStartDate());
            equals = equals && this.getEndDate().equals(other.getEndDate());

            return equals;
        }
    }
    
    public String toString() {
    	return getDescription() + " " + toFinnishTime(startDate) + " [" + getLocation() + "]";
    }
}
