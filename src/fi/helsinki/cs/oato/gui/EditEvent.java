package fi.helsinki.cs.oato.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import fi.helsinki.cs.oato.Event;

/***
 * Display UI for adding and editing events.
 * 
 **/
public class EditEvent extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField description = new JTextField("Description", 20);
    private JButton importFromInterface = new JButton("Look from Web");
    private JTextField location = new JTextField("Location", 20);
    private JSpinner startTime = new JSpinner();
    private JSpinner endTime = new JSpinner();
    private JButton addButton = new JButton("Add event");
    private JButton cancelButton = new JButton("Cancel");
    
    // record if we are in mode that an event is created
    private boolean createNew = false;

    /**
     * Creates new clean (empty) view for adding an event. 
     * 
     **/
    public EditEvent() {
        super();

        this.add( description );
        this.add( location );
        this.add( importFromInterface );
        
        // set models for Spinners for
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        Date initDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -1);
        Date earliestDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 2);
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
            	// TODO: debends which mode we're on
            }
        } );

        this.setLayout( new FlowLayout() );
        this.pack();
        this.setVisible(true);		
    }

    /**
     * Create new vier for editing event given.
     * 
     *  @param e the view for editing.
     * 
     **/
    public EditEvent(Event e) {
        this();
        
        description.setText( e.getDescription() );
        location.setText( e.getLocation() );
        startTime.getModel().setValue(e.getStartJavaDate());
        endTime.getModel().setValue(e.getStartJavaDate());
        
        createNew = false;
    }

}
