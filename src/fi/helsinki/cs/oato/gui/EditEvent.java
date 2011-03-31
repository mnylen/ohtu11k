package fi.helsinki.cs.oato.gui;

import java.awt.*;
import static java.awt.FlowLayout.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTime;

import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

import fi.helsinki.cs.oato.*;
import fi.helsinki.cs.oato.Event;
import static fi.helsinki.cs.oato.Helpers.*;

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

    private JPanel border = new JPanel();
    private JPanel centerPanel;
    
    private JPanel importPanel = new JPanel(new FlowLayout(RIGHT));
    private JButton importButton = new JButton("Import course…");
    
    private JLabel descriptionLabel = new JLabel("Description");
    private JTextField description = new JTextField(0);
    
    private JLabel locationLabel = new JLabel("Location");
    private JTextField location = new JTextField(0);
    
    private JLabel startTimeLabel = new JLabel("Start");
    private JSpinner startTime = new JSpinner();
    private JLabel endTimeLabel = new JLabel("End");
    private JSpinner endTime = new JSpinner();

    private JCheckBox repeat = new JCheckBox("Repeat event");
    private JPanel repeatPanel;
    private JLabel repeatLabel = new JLabel("Repeat on same weekday between");
    private JSpinner repeatStart = new JSpinner();
    private JSpinner repeatEnd = new JSpinner();
    
    private JPanel buttonPanel = new JPanel(new FlowLayout(RIGHT));
    private JButton addButton = new JButton("Add event");
    private JButton okButton = new JButton("OK");
    private JButton cancelButton = new JButton("Cancel");
    
    // record if we are in mode that an event is created
    private boolean createNew = true;

    /**
     * Creates new clean (empty) view for adding an event. 
     * 
     **/
    public EditEvent() {
        super("Add event");
        DateTime now = (new DateTime()).withMillisOfSecond(0).withSecondOfMinute(0).withMinuteOfHour(0);
        initView(new Event(now, now.plusHours(2), "", ""));
    }

    public EditEvent(Event e) {
        super("Edit event");
        createNew = false;
        initView(e);
    }

    private void initView(Event e) {
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

        setLeft(repeat);
        repeat.setSelected(false);
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

        border.add(buttonPanel, BorderLayout.SOUTH);
        if (createNew) {
            buttonPanel.add(addButton);
        } else {
            buttonPanel.add(okButton);
        }
        buttonPanel.add(cancelButton);

        description.setText( e.getDescription() );
        location.setText( e.getLocation() );

        cancelButton.addActionListener( new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        } );

        addButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okClicked();
            }
        } );

        okButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okClicked();
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

    protected void okClicked() {
        
    }

    protected void importClicked() {
        if (Main.onlineCourses.isReady()) {
            new SelectCourse(this);
        }
    }

    public void selectCourse(Course c) {
        setRepeatModels(c.getStartDate(), c.getEndDate());
        description.setText( c.getDescription() );
        
        
        if (!repeat.isSelected()) {
            repeat.doClick();
        }
    }
}
