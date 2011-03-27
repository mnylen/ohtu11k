package fi.helsinki.cs.oato.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import fi.helsinki.cs.oato.Event;
import fi.helsinki.cs.oato.EventFixtures;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public MainGUI(){
		super();
		this.pack();
		this.setSize(500, 500);
		createUI();
		this.setVisible(true);
	}
	
	private void createUI() {
		JButton addEvent = new JButton("Add event");
		JButton saveData = new JButton("Save");
		JButton openData = new JButton("Open");
		
		this.add( addEvent );
		this.add( saveData );
		this.add( openData );
		
		this.setLayout( new FlowLayout() );
		
	}
	
	// should take event
	public void addEvent(Event event) {
		// create a new panel for showing this item
		JPanel item = new JPanel();
		item.setLayout( new FlowLayout() );
		JLabel text = new JLabel("text " + event.toString() );
		item.add( text );
		JButton edit = new JButton("Edit");
		item.add( edit );
		edit.setVisible(false);
		JButton delete = new JButton("Delete");
		delete.setSize(100, 50);
		delete.setVisible(false);
		item.add( delete );
		// add mouse over listener for this item
		// hide / display delete / edit when mouse over
		item.setSize(500, 50);
		item.addMouseListener( new EventDisplayListener(delete, edit) );
		
		this.add( item );
	}
	
	private class EventDisplayListener implements MouseListener {

		JButton delete;
		JButton edit;
		
		public EventDisplayListener(JButton delete, JButton edit) {
			this.delete = delete;
			this.edit = edit;
		}
		
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {
			this.delete.setVisible(true);
			this.edit.setVisible(true);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			this.delete.setVisible(false);
			this.edit.setVisible(false);
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
		
		
	}
	
	/*
	 * For testing purposes
	 */
	public static void main(String[] args) {
		MainGUI m = new MainGUI();
		m.addEvent( EventFixtures.createDentistAppointment() );
		m.addEvent( EventFixtures.createSoftwareEngineeringLecture() );
	}

}
