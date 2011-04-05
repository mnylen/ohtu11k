package fi.helsinki.cs.oato.gui;

import java.awt.*;
import static java.awt.FlowLayout.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;

import fi.helsinki.cs.oato.model.Course;
import fi.helsinki.cs.oato.model.Schedule;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import fi.helsinki.cs.oato.*;
import fi.helsinki.cs.oato.model.Event;
import static fi.helsinki.cs.oato.Helpers.*;
import static fi.helsinki.cs.oato.Strings.*;

/***
 * Display UI for adding and editing events.
 * 
 **/
public class EditEvent extends JFrame {

    public static final String DATETIME_FORMAT = "EEE dd.MM.yyyy HH:mm ";
    public static final String DATE_FORMAT = "EEE dd.MM.yyyy ";
    public static final int PADDING_X = 5;
    public static final int PADDING_Y = 3;
    
    private static final long serialVersionUID = 1L;

    private MainGUI parent;

    private JPanel border = new JPanel();
    private JPanel centerPanel;
    
    private JPanel importPanel = new JPanel(new FlowLayout(RIGHT));
    private JButton importButton = new JButton(localize("Import course…"));
    
    private JLabel descriptionLabel = new JLabel(localize("Description"));
    private JTextField description = new JTextField(0);
    
    private JLabel locationLabel = new JLabel(localize("Location"));
    private JTextField location = new JTextField(0);
    
    private JLabel startTimeLabel = new JLabel(localize("Start"));
    private JSpinner startTime = new JSpinner();
    private JLabel endTimeLabel = new JLabel(localize("End"));
    private JSpinner endTime = new JSpinner();

    private JCheckBox repeat = new JCheckBox(localize("Repeat event"));
    private JPanel repeatPanel;
    private JLabel repeatLabel = new JLabel(localize("Repeat on same weekday between"));
    private JSpinner repeatStart = new JSpinner();
    private JSpinner repeatEnd = new JSpinner();
    
    private JPanel buttonPanel = new JPanel(new FlowLayout(RIGHT));
    private JButton addButton = new JButton(localize("Add event"));
    private JButton cancelButton = new JButton(localize("Cancel"));
    
    // record if we are in mode that an event is created
    private boolean createNew = true;
    
    private Event event = null;

    /**
     * Creates new clean (empty) view for adding an event. 
     * 
     **/
    public EditEvent(MainGUI parent) {
        super(localize("Add event"));
        this.parent = parent;
        DateTime now = (new DateTime()).withMillisOfSecond(0).withSecondOfMinute(0).withMinuteOfHour(0);
        initView(new Event(now, now.plusHours(2), "", ""));
        
        // when no previous event has been given, add event to Schedule
        addButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateEvent();
                String validation = event.validate();
                if (validation != null) {
                    updateOkButton();
                    JOptionPane.showMessageDialog(EditEvent.this, validation, localize("Error"), JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Schedule s = EditEvent.this.parent.getSchedule();
                if (repeat.isSelected()) {
                    if (! repeatEvent(s)) {
                        return;
                    }
                } else {
                    s.addEvent( event );
                }
                EditEvent.this.parent.updateSchedule(s);
                EditEvent.this.dispose();
            }
        } );

    }

    public EditEvent(MainGUI parent, Event e) {
        super(localize("Edit event"));
        this.parent = parent;
        createNew = false;
        initView(e);
        addButton.setText(localize("OK"));
        
        addButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateEvent();
                String validation = event.validate();
                if (validation != null) {
                    updateOkButton();
                    JOptionPane.showMessageDialog(EditEvent.this, validation, localize("Error"), JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Schedule s = EditEvent.this.parent.getSchedule();
                EditEvent.this.parent.updateSchedule(s);
                EditEvent.this.dispose();
            }
        } );
    }

    private void updateEvent() {
        event.setStartDate( toJodaDate((Date) startTime.getValue()) );
        event.setEndDate( toJodaDate((Date) endTime.getValue()) );
        event.setDescription( description.getText() );
        event.setLocation( location.getText() );
    }

    private boolean repeatEvent(Schedule s) {
        DateTime start = toJodaDate((Date) repeatStart.getValue());
        DateTime end = toJodaDate((Date) repeatEnd.getValue());
        Interval interval;

        try {
            interval = new Interval(start, end.plusDays(1).minus(1));
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, localize("Invalid repeat interval"),
                                          localize("Error"), JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (! interval.contains(event.getStartDate())) {
            JOptionPane.showMessageDialog(this, localize("Repeat interval does not contain event start"),
                                          localize("Error"), JOptionPane.ERROR_MESSAGE);
            return false;
        }

        /* Repeat to future */
        for (int i=0; interval.contains(event.getStartDate().plusWeeks(i)); i++) {
            s.addEvent( new Event(event.getStartDate().plusWeeks(i),
                                  event.getEndDate().plusWeeks(i),
                                  event.getDescription(),
                                  event.getLocation()) );
        }
        
        /* Repeat to past */
        for (int i=1; interval.contains(event.getStartDate().minusWeeks(i)); i++) {
            s.addEvent( new Event(event.getStartDate().minusWeeks(i),
                                  event.getEndDate().minusWeeks(i),
                                  event.getDescription(),
                                  event.getLocation()) );
        }

        return true;
    }

    private void initView(Event e) {
    	
    	this.event = e;
    	
        setResizable(false);
        border.setLayout(new BorderLayout());
        border.setBorder(new EmptyBorder(PADDING_Y, PADDING_X, PADDING_Y, PADDING_X));
        add(border);

        if (createNew) {
            addImportButton();
        }

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        border.add(centerPanel, BorderLayout.CENTER);

        descriptionLabel.setLabelFor(description);
        setLeft(descriptionLabel);
        setLeft(description);
        centerPanel.add(descriptionLabel);
        centerPanel.add(description);
        
        locationLabel.setLabelFor(location);
        setLeft(locationLabel);
        setLeft(location);
        centerPanel.add(locationLabel);
        centerPanel.add(location);

        setSpinnerModels(e.getStartDate(), e.getEndDate());
        centerPanel.add(startTimeLabel);
        setLeft(startTimeLabel);
        setLeft(startTime);
        centerPanel.add(startTime);
        centerPanel.add(endTimeLabel);
        setLeft(endTimeLabel);
        setLeft(endTime);
        centerPanel.add(endTime);

        repeat.setSelected(false);

        if (createNew) {
            setLeft(repeat);
            centerPanel.add(repeat);

            repeatPanel = new JPanel();
            repeatPanel.setLayout(new BoxLayout(repeatPanel, BoxLayout.PAGE_AXIS));
            setLeft(repeatPanel);

            DateTime now = (new DateTime()).withMillisOfSecond(0).withSecondOfMinute(0).withMinuteOfHour(0).withHourOfDay(0);
            setRepeatModels(now, now.plusDays(14));
            repeatPanel.add(repeatLabel);
            repeatPanel.add(repeatStart);
            repeatPanel.add(repeatEnd);
            setLeft(repeatLabel);
            setLeft(repeatStart);
            setLeft(repeatEnd);
        }

        border.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(addButton);
        addButton.setEnabled(event.validate() == null);
        buttonPanel.add(cancelButton);

        description.setText( e.getDescription() );
        location.setText( e.getLocation() );

        cancelButton.addActionListener( new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        } );

        importButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                importClicked();
            }
        } );

        repeat.addItemListener( new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    centerPanel.add(repeatPanel);
                } else {
                    centerPanel.remove(repeatPanel);
                }
                pack();
            }
        } );
        description.addKeyListener( new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateOkButton();
            }
        });

        ChangeListener timeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                updateOkButton();
            }
        };
        startTime.addChangeListener(timeListener);
        endTime.addChangeListener(timeListener);

        pack();
        setVisible(true);
    }

    private void setSpinnerModels(DateTime eventStart, DateTime eventEnd) {
        SpinnerModel startModel = new SpinnerDateModel(toJavaDate(eventStart),
                                        toJavaDate(eventStart.minusYears(1)),
                                        toJavaDate(eventStart.plusYears(1)),
                                        Calendar.DAY_OF_MONTH);
        SpinnerModel endModel = new SpinnerDateModel(toJavaDate(eventEnd),
            toJavaDate(eventEnd.minusYears(1)),
            toJavaDate(eventEnd.plusYears(1)),
            Calendar.DAY_OF_MONTH);

        startTime.setModel( startModel );
        endTime.setModel( endModel );

        startTime.setEditor(new JSpinner.DateEditor(startTime, DATETIME_FORMAT));
        endTime.setEditor(new JSpinner.DateEditor(endTime, DATETIME_FORMAT));
        
    }

    private void setRepeatModels(DateTime start, DateTime end) {
        SpinnerModel startModel = new SpinnerDateModel(toJavaDate(start),
                                        toJavaDate(start.minusYears(1)),
                                        toJavaDate(start.plusYears(1)),
                                        Calendar.DAY_OF_MONTH);
        SpinnerModel endModel = new SpinnerDateModel(toJavaDate(end),
            toJavaDate(end.minusYears(1)),
            toJavaDate(end.plusYears(1)),
            Calendar.DAY_OF_MONTH);

        repeatStart.setModel( startModel );
        repeatEnd.setModel( endModel );

        repeatStart.setEditor(new JSpinner.DateEditor(repeatStart, DATE_FORMAT));
        repeatEnd.setEditor(new JSpinner.DateEditor(repeatEnd, DATE_FORMAT));
        
    }

    private void addImportButton() {
        importPanel.add(importButton);
        border.add(importPanel, BorderLayout.NORTH);
        if (!Main.onlineCourses.isReady()) {
            importButton.setEnabled(false);
            Main.onlineCourses.addObserver(new Observer() {
                public void update(Observable o, Object arg) {
                    if (Main.onlineCourses.isReady()) {
                        EditEvent.this.importButton.setEnabled(true);
                    }
                }
            });
        }
    }
    
    private void setLeft(JComponent c) {
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    protected void importClicked() {
        if (Main.onlineCourses.isReady()) {
            new SelectCourse(this);
        }
    }

    private void updateOkButton() {
        updateEvent();
        boolean valid = (event.validate() == null);
        if (valid ^ addButton.isEnabled()) {
            addButton.setEnabled(valid);
            EditEvent.this.pack();
        }
    }

    public void selectCourse(Course c) {
        setRepeatModels(c.getStartDate(), c.getEndDate());
        setSpinnerModels(c.getStartDate().plusHours(8), c.getStartDate().plusHours(10));
        description.setText( c.getDescription() );
        
        
        if (!repeat.isSelected()) {
            repeat.doClick();
        }

        updateOkButton();
    }
}
