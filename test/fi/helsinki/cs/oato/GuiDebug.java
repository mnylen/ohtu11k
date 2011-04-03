package fi.helsinki.cs.oato;

import javax.swing.SwingUtilities;

import fi.helsinki.cs.oato.gui.MainGUI;
import fi.helsinki.cs.oato.model.EventFixtures;

public class GuiDebug {

	/**
	 * Runs the GUI with given data.
	 * 
	 * @param args null
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Main.main(null);
            }
        });
		MainGUI m = (MainGUI) Main.getMainGui();
		m.addEvent( EventFixtures.createDentistAppointment() );
		m.addEvent( EventFixtures.createSoftwareEngineeringLecture() );
	}

}
