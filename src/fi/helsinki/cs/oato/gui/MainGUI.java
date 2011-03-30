package fi.helsinki.cs.oato.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;

import javax.swing.*;

import fi.helsinki.cs.oato.Event;

public class MainGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private EventList futureEvents = new EventList();

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
        
        this.add( addEvent );
        this.add( saveData );
        this.add( openData );
        
        // display scrollpane as big
        futureEvents.setPreferredSize( new Dimension( this.getWidth() - 50 , 200 ) );
        
        JTabbedPane eventsPane = new JTabbedPane();
        
        eventsPane.add("Future events", futureEvents );
        
        // for demo only
        eventsPane.add("All events", new JPanel() );
        
        this.add( eventsPane );
        
        this.setLayout( new FlowLayout() );
        
    }

    // should take event
    public void addEvent(Event event) {
        futureEvents.addEvent(event);
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
            this.delete.setVisible(false);
            this.edit.setVisible(false);
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }
        
        
    }

}
