package fi.helsinki.cs.oato;
import java.util.HashMap;
import static org.joda.time.DateTimeConstants.*;

/** This class is a (vulgar) way to localize our strings.
    This is not how it should be done. But it fits our (agile) purpose for now. */

public class Strings {
    private static final HashMap<String,String> l = new HashMap<String,String>();
    /* Weekdays inflected to "on X" where X is the weekday */
    private static final HashMap<Integer,String> weekdaysOn = new HashMap<Integer,String>();
    
    static {
        l.put("remove() operation is not supported by EventIterator",
              "EventIterator ei tue remove()-metodia");
        l.put("Got following courses:",
              "Haettu seuraavat kurssit:");
        l.put("While fetching online course data: ",
              "Haettaessa verkkokursseja: ");
        l.put("Retrying fetch",
              "Yritetään uudelleen");
        l.put("Received nothing",
              "Tyhjä vastaus");
        l.put("Import course…",
              "Tuo kurssi…");
        l.put("Description",
              "Kuvaus");
        l.put("Location",
              "Paikka");
        l.put("Start",
              "Alku");
        l.put("End",
              "Loppu");
        l.put("Repeat event",
              "Toista tapahtuma");
        l.put("Repeat on same weekday between",
              "Toista samana viikonpäivänä välillä:");
        l.put("Add event",
              "Lisää tapahtuma");
        l.put("OK",
              "OK");
        l.put("Cancel",
              "Peruuta");
        l.put("Edit",
              "Muokkaa");
        l.put("Delete",
              "Poista");
        l.put("Save",
              "Tallenna");
        l.put("Open",
              "Avaa");
        l.put("Future events",
              "Tulevat tapahtumat");
        l.put("All events",
              "Kaikki tapahtumat");
        l.put("Select course",
              "Valitse kurssi");
        l.put("Edit event",
              "Muokkaa tapahtumaa");
        l.put("today",
              "tänään");
        l.put("tomorrow",
              "huomenna");
        l.put("Error",
              "Virhe");
        l.put("Empty description",
              "Tyhjä kuvaus");
        l.put("Event end can not be before start",
              "Tapahtuman ei voi loppua ennen alkua");
        l.put("Invalid repeat interval",
              "Epäkelpo toistoväli");
        l.put("Repeat interval does not contain event start",
              "Toistoväli ei sisällä tapahtuman alkua");
        l.put("Could not load events",
              "Tapahtumia ei voitu ladata");
        l.put(" has no translation",
              " ei käännetty");
        l.put("(unknown weekday)",
              "(tuntematon viikonpäivä)");

        weekdaysOn.put(MONDAY,     "maanantaina");
        weekdaysOn.put(TUESDAY,    "tiistaina");
        weekdaysOn.put(WEDNESDAY,  "keskiviikkona");
        weekdaysOn.put(THURSDAY,   "torstaina");
        weekdaysOn.put(FRIDAY,     "perjantaina");
        weekdaysOn.put(SATURDAY,   "lauantaina");
        weekdaysOn.put(SUNDAY,     "sunnuntaina");
    }

    /** Localize <code>original</code>. Edit Strings.java to add your translation.
        @param original String to translate.
        @return Finnish translation. */
    public static String localize(String original) {
        if (!l.containsKey(original)) {
            if (Main.DEBUG) {
                System.err.println(original + localize(" has no translation"));
                return original;
            }
        }
        return l.get(original);
    }

    /** Localize <code>weekDay</code>.
        @param constant Weekday to translate (from org.joda.time.DateTimeConstants).
        @return Finnish translation. */
    public static String weekdayOn(int original) {
        if (!weekdaysOn.containsKey(original)) {
            if (Main.DEBUG) {
                System.err.println(original + localize(" has no translation"));
                return localize("(unknown weekday)");
            }
        }
        return weekdaysOn.get(original);
    }
    
}