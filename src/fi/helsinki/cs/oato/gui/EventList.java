package fi.helsinki.cs.oato.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import fi.helsinki.cs.oato.Event;
import static fi.helsinki.cs.oato.Strings.*;


/**
 * UI component for showing Events in a list. 
 **/
public class EventList extends JScrollPane implements EventContainer {
	
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Actual container for the data.
	 **/
	private JPanel content = new JPanel();
	
	/**
	 * Creates new empty list of events.
	 **/
	public EventList() {
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
	public void addEvent(Event event) {
		// create a new panel for showing this item
		JPanel item = new JPanel();
		item.setLayout( new FlowLayout() );
		
		// show actual event
		JLabel text = new JLabel("TI 11.2. " + event.toString() );
		item.add( text );
		
		// button for editing this event
		JButton edit = new JButton(localize("Edit"));
		item.add( edit );
		edit.setVisible(false);
		edit.addActionListener( new EventActionListener(event) );
		
		// button for deleting this event
		JButton delete = new JButton(localize("Delete"));
		delete.setSize(100, 50);
		delete.setVisible(false);
		delete.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO: a mockup solution
				JButton b = (JButton)(e.getSource());
				b.getParent().setVisible(false);
			}
		} );
		item.add( delete );
		
		item.setPreferredSize( new Dimension( content.getWidth() , 40 ) );
		// add mouse over listener for this item
		// hide / display delete / edit when mouse over
		item.addMouseListener( new EventDisplayListener(delete, edit) );
		
		this.content.add( item );
	}
	
	/**
	 * Handles the button pressing from "Edit"-button. 
	 * 
	 **/
	private class EventActionListener implements ActionListener {

		private Event event;
		
		public EventActionListener(Event event) {
			this.event = event;
		}
		
		public void actionPerformed(ActionEvent e) {
			new EditEvent(EventList.this, this.event);
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
			// this.delete.setVisible(false);
			// this.edit.setVisible(false);
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}
		
	}
	
}
