package fi.helsinki.cs.oato.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;

import javax.swing.*;

import fi.helsinki.cs.oato.Event;

/**
 * Create main UI for the application. 
 * 
 **/
public class MainGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private EventList futureEvents = new EventList();
    private EventList allEvents = new EventList();

    /**
     * Create new GUI. Defaults to 500 x 500.
     * 
     **/
    public MainGUI(){
        this(500, 500);
    }
    
    /**
     * Creates new GUI. Size given.
     * 
     *  @param width width of the screen
     *  @param height height of the screen
     **/
    public MainGUI(int width, int height) {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(width, height);
        createUI();
        this.setVisible(true);
    }

    /**
     * Actually creates the UI 
     **/
    private void createUI() {
    	
    	// UI buttons
        JButton addEvent = new JButton("Add event");
        JButton saveData = new JButton("Save");
        JButton openData = new JButton("Open");
        
        addEvent.addActionListener( new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                // show add UI
                new EditEvent();
            }
        } );
        
        saveData.addActionListener( new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showSaveDialog( MainGUI.this );
            }
        } );
        
        openData.addActionListener( new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog( MainGUI.this );
            }
        });
        
        // display buttons
        this.add( addEvent );
        this.add( saveData );
        this.add( openData );
        
        Dimension eventDataSize = new Dimension( this.getWidth() - 50 , 350 );
        futureEvents.setPreferredSize( eventDataSize );
        allEvents.setPreferredSize(eventDataSize);
        
        JTabbedPane eventsPane = new JTabbedPane();
        
        eventsPane.add("Future events", futureEvents );
        eventsPane.add("All events", allEvents );
        
        this.add( eventsPane );
        
        this.setLayout( new FlowLayout() );
        
    }

    // should take event
    public void addEvent(Event event) {
        futureEvents.addEvent(event);
    }

}
