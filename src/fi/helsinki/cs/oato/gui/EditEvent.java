package fi.helsinki.cs.oato.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

import fi.helsinki.cs.oato.Event;

public class EditEvent extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField description = new JTextField("Description", 20);
	private JButton importFromInterface = new JButton("Look from Web");
	private JTextField location = new JTextField("Location", 20);
	private JSpinner startTime = new JSpinner();
	private JSpinner endTime = new JSpinner();
	private JButton addButton = new JButton("Add event");
	private JButton cancelButton = new JButton("Cancel");
	
	public EditEvent() {
		super();
	
		this.add( description );
		this.add( location );
		this.add( importFromInterface );
		
		// set models for Spinners for
		Calendar calendar = new GregorianCalendar();
		Date initDate = calendar.getTime();
		calendar.add(Calendar.YEAR, -1);
		Date earliestDate = calendar.getTime();
		calendar.add(Calendar.YEAR, 1);
		Date latestDate = calendar.getTime();
		SpinnerModel startModel = new SpinnerDateModel(initDate,
		                             earliestDate,
		                             latestDate,
		                             Calendar.YEAR);
		SpinnerModel endModel = new SpinnerDateModel(initDate,
                earliestDate,
                latestDate,
                Calendar.YEAR);
		
		startTime.setModel( startModel );
		endTime.setModel( endModel );
		
		new JSpinner.DateEditor( startTime );
		new JSpinner.DateEditor( endTime );
		
		this.add( startTime );
		this.add( endTime );
		this.add( addButton );
		this.add( cancelButton );
		
		cancelButton.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				EditEvent.this.dispose();
			}
		} );
		
		addButton.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO: add real action
			}
		} );
		
		this.setLayout( new FlowLayout() );
		this.pack();
		this.setVisible(true);		
	}
	
	public EditEvent(Event e) {
		this();
		
		description.setText( e.getDescription() );
		location.setText( e.getLocation() );
		
	}

}
