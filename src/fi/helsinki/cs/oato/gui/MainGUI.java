package fi.helsinki.cs.oato.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.*;

import fi.helsinki.cs.oato.io.CsvScheduleReader;
import fi.helsinki.cs.oato.io.CsvScheduleWriter;
import fi.helsinki.cs.oato.model.Event;
import fi.helsinki.cs.oato.model.Schedule;
import org.joda.time.DateTime;

import static fi.helsinki.cs.oato.Strings.*;

/**
 * Create main UI for the application. 
 * 
 **/
public class MainGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private EventList futureEvents = new EventList(this);
    private EventList allEvents = new EventList(this);
    private Schedule schedule;
    
    public static final int PADDING_X = 5;
    public static final int PADDING_Y = 2;

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
     * Note: the UI must be set visible in the main-method!
     * 
     *  @param width width of the screen
     *  @param height height of the screen
     **/
    public MainGUI(int width, int height) {
        super("OATO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        createUI();
        loadFile( new File( "events.csv" ) );
        this.pack();
    }

    /**
     * Actually creates the UI 
     **/
    private void createUI() {
        JPanel border = new JPanel();
        border.setLayout(new BorderLayout());
        border.setBorder(new EmptyBorder(PADDING_Y, PADDING_X, PADDING_Y, PADDING_X));
        add(border);
        BorderLayout layout = new BorderLayout();
        border.setLayout(layout);
        
        // UI buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addEvent = new JButton(localize("Add event"));
        JButton saveData = new JButton(localize("Save"));
        JButton openData = new JButton(localize("Open"));
        
        addEvent.addActionListener( new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                // show add UI
                new EditEvent(MainGUI.this);
            }
        } );
        
        saveData.addActionListener( new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int selection = chooser.showSaveDialog( MainGUI.this );
                if( selection == JFileChooser.APPROVE_OPTION ) {
                	MainGUI.this.saveFile( chooser.getSelectedFile() );
                }
            }
        } );
        
        openData.addActionListener( new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int selection = chooser.showOpenDialog( MainGUI.this );
                if( selection == JFileChooser.APPROVE_OPTION ) {
                	MainGUI.this.loadFile( chooser.getSelectedFile() );
                }
            }
        });
        
        // display buttons
        buttonPanel.add( addEvent );
        buttonPanel.add( saveData );
        buttonPanel.add( openData );
        border.add(buttonPanel, BorderLayout.NORTH);
        
        Dimension eventDataSize = new Dimension( this.getWidth() - 50 , 350 );
        futureEvents.setPreferredSize(eventDataSize);
        allEvents.setPreferredSize(eventDataSize);
        
        JTabbedPane eventsPane = new JTabbedPane();
        
        eventsPane.add(localize("Future events"), futureEvents );
        eventsPane.add(localize("All events"), allEvents );
        
        border.add( eventsPane, BorderLayout.CENTER );
        
    }
    
    /***
     * Loads File f and updates the Schedule to be f
     * 
     * @param f the file to be loaded.
     * 
     * */
    private void loadFile(File f) {
        try {
            FileInputStream fis = new FileInputStream(f);
            schedule = new CsvScheduleReader(fis).read();
        } catch (Exception e) {
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(this, localize("Could not load events"));
            schedule = new Schedule();
        }
        updateSchedule( this.schedule );
    }
    
    private void saveFile(File f) {
    	try {
            FileOutputStream fos = new FileOutputStream(f);
            CsvScheduleWriter writer = new CsvScheduleWriter(fos);
            writer.write( this.schedule );
        } catch (Exception e) {
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(this, "Could not load events");
        }
    }
    
    /**
     * Update the schedule viewed.
     * 
     * @param s the new schedule
     *
     */
    void updateSchedule(Schedule s) {
    	this.futureEvents.addEvents( s.nextEvents() );
    	this.allEvents.addEvents( s.allEvents() );
    }

    /**
     * Returns the schedule of this view.
     * 
     * @return schedule.
     */
    Schedule getSchedule() {
    	return this.schedule;
    }
    
    /**
     * @deprecated Used for debug only
     * **/
    public void addEvent(Event event) {
        futureEvents.addEvent(event);
    }

}
