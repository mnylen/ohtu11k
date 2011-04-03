package fi.helsinki.cs.oato;

import fi.helsinki.cs.oato.gui.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;

public class Main {
    public static OnlineCourseSource onlineCourses;
    public static final boolean DEBUG = true;

    private static JFrame mainGui;

    public static void main(String args[]) {
        onlineCourses = new OnlineCourseSource();
        
        /* Enable font anti-aliasing */
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");

        /* Set bearable look&feel */
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (Exception e) {
            try {
                MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
                UIManager.setLookAndFeel(new MetalLookAndFeel());
            } catch (Exception e2) {}
        };

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainGui = new MainGUI();
                Main.mainGui.setVisible(true);
            }
        });
    }

    public static JFrame getMainGui() {
        return mainGui;
    }
}