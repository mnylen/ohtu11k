package fi.helsinki.cs.oato.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;

import javax.swing.*;

import fi.helsinki.cs.oato.Event;

public class MainGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel events = new JPanel();

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
        events.setPreferredSize( new Dimension( this.getWidth() - 50 , 200 ) );
        
        JTabbedPane eventsPane = new JTabbedPane();
        
        // add next events to a scroll pane
        JScrollPane nextEventPane = new JScrollPane( events );
        eventsPane.add("Future events", nextEventPane );
        
        // for demo only
        eventsPane.add("All events", new JPanel() );
        
        this.add( eventsPane );
        
        this.setLayout( new FlowLayout() );
        
    }

    // should take event
    public void addEvent(Event event) {
        // create a new panel for showing this item
        JPanel item = new JPanel();
        item.setLayout( new FlowLayout() );
        
        // show actual event
        JLabel text = new JLabel("text " + event.toString() );
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
        
        item.setPreferredSize( new Dimension( events.getWidth() , 40 ) );
        item.setBackground(  Color.PINK );
        // add mouse over listener for this item
        // hide / display delete / edit when mouse over
        item.addMouseListener( new EventDisplayListener(delete, edit) );
        
        events.add( item );
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
