package fi.helsinki.cs.oato.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

import fi.helsinki.cs.oato.model.Event;
import fi.helsinki.cs.oato.model.Schedule;

import static fi.helsinki.cs.oato.Strings.*;


/**
 * UI component for showing Events in a list. 
 **/
public class EventList extends JScrollPane {
	
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Actual container for the data.
	 **/
	private JPanel content = new JPanel();
	
	private MainGUI parent;
	
	/**
	 * Creates new empty list of events.
	 **/
	public EventList(MainGUI parent) {
		this.parent = parent;
        this.content.setVisible(true);
		this.setViewportView( content );
	}
	
	/**
	 * Sets the preferred size of this UI component.
	 * 
	 * @param preferredSize Preferred size of UI component.
	 **/
	public void setPreferredSize(Dimension preferredSize) {
		this.content.setPreferredSize( preferredSize );
	}
	
	/**
	 * Adds new event to this UI view. 
	 * 
	 * Note, this does not promise, that the event would be put to correct position, it is just laid below all other events.
	 * 
	 * @param event The event to be added.
	 **/
	public void addEvent(fi.helsinki.cs.oato.model.Event event) {
		// create a new panel for showing this item
		JPanel item = new JPanel();
		item.setLayout( new FlowLayout() );
		
		// show actual event
		JLabel text = new JLabel( event.toString() );
		item.add( text );
		
		// button for editing this event
		JButton edit = new JButton(localize("Edit"));
		item.add( edit );
		edit.setVisible(false);
		
		// button for deleting this event
		JButton delete = new JButton(localize("Delete"));
		// delete.setSize(100, 50);
		delete.setVisible(false);
		
		ActionListener eventListener = new EventActionListener(event, edit, delete);
		edit.addActionListener( eventListener );
		delete.addActionListener( eventListener );
		item.add( delete );
		
		item.setPreferredSize( new Dimension( (int) content.getPreferredSize().getWidth() , 40 ) );
		// add mouse over listener for this item
		// hide / display delete / edit when mouse over
		EventDisplayListener listener = new EventDisplayListener(delete, edit);
		item.addMouseListener( listener );
		// XXX these needs to be added also to children events to make the experience corrext
		delete.addMouseListener( listener );
		edit.addMouseListener( listener );
		
		this.content.add( item );
	}
	
	/**
	 * Adds all the events in the iterator.
	 * 
	 * @param events list of events to be added.
	 * */
	public void addEvents(Iterator<Event> events) {
		this.content.removeAll();
		while( events.hasNext() ) {
			this.addEvent( events.next() );
		}
	}
	
	/**
	 * Handles the button pressing from "Edit"-button. 
	 * 
	 **/
	private class EventActionListener implements ActionListener {

		private fi.helsinki.cs.oato.model.Event event;
		private JComponent edit;
		private JComponent delete;
		
		public EventActionListener(fi.helsinki.cs.oato.model.Event event, JComponent edit, JComponent delete) {
			this.event = event;
			this.edit = edit;
			this.delete = delete;
		}
		
		public void actionPerformed(ActionEvent e) {
			if( e.getSource() == this.edit ) {
				new EditEvent( EventList.this.parent, this.event);
			}
			if( e.getSource() == this.delete ) {
				Schedule schedule = EventList.this.parent.getSchedule();
				schedule.removeEvent(this.event);
				EventList.this.parent.updateSchedule(schedule);
			}
		}
		
	}
	
	/**
	 * Implements the hover-effect of "Delete" and "Edit"-buttons. 
	 **/
	private class EventDisplayListener implements MouseListener {

		JButton delete;
		JButton edit;
		
		public EventDisplayListener(JButton delete, JButton edit) {
			this.delete = delete;
			this.edit = edit;
		}
		
		public void mouseClicked(MouseEvent e) {}

		public void mouseEntered(MouseEvent e) {
			this.delete.setVisible(true);
			this.edit.setVisible(true);
		}

		public void mouseExited(MouseEvent e) {
			// XXX don't act when inside the component
			// XXX currently fixed with a hack
			this.delete.setVisible(false);
			this.edit.setVisible(false);
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}
		
	}
	
}
