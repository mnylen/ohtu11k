package fi.helsinki.cs.oato;

import fi.helsinki.cs.oato.gui.*;
import javax.swing.SwingUtilities;

public class Main {
    public static OnlineCourseSource onlineCourses;
    public static final boolean DEBUG = true;

    public static void main(String args[]) {
        onlineCourses = new OnlineCourseSource();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainGUI();
            }
        });
    }
}