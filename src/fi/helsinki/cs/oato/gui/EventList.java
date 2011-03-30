package fi.helsinki.cs.oato.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import fi.helsinki.cs.oato.Event;

public class EventList extends JScrollPane {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel content = new JPanel();
	
	public EventList() {
		this.setViewportView( content );
	}
	
	public void setPreferredSize(Dimension preferredSize) {
		this.content.setPreferredSize( preferredSize );
	}
	
	// should take event
	public void addEvent(Event event) {
		// create a new panel for showing this item
		JPanel item = new JPanel();
		item.setLayout( new FlowLayout() );
		
		// show actual event
		JLabel text = new JLabel("TI 11.2. " + event.toString() );
		item.add( text );
		
		// button for editing this event
		JButton edit = new JButton("Edit");
		item.add( edit );
		edit.setVisible(false);
		edit.addActionListener( new EventActionListener(event) );
		
		// button for deleting this event
		JButton delete = new JButton("Delete");
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
		// item.setBackground(  Color.PINK );
		// add mouse over listener for this item
		// hide / display delete / edit when mouse over
		item.addMouseListener( new EventDisplayListener(delete, edit) );
		
		this.content.add( item );
	}
	
	private class EventActionListener implements ActionListener {

		private Event event;
		
		public EventActionListener(Event event) {
			this.event = event;
		}
		
		public void actionPerformed(ActionEvent e) {
			new EditEvent(this.event);
			
		}
		
		
	}
	
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
