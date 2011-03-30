package fi.helsinki.cs.oato.gui;

import java.awt.*;
import static java.awt.FlowLayout.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.joda.time.DateTime;

import javax.swing.*;

import fi.helsinki.cs.oato.Event;
import static fi.helsinki.cs.oato.Helpers.*;

public class EditEvent extends JFrame {

    public static final String DATE_FORMAT = "dd.MM.yyyy HH:mm ";
    
    private static final long serialVersionUID = 1L;

    private JPanel importPanel = new JPanel(new FlowLayout(RIGHT));
    private JButton importButton = new JButton("Import course…");
    private JPanel centerPanel;
    private JLabel descriptionLabel = new JLabel("Description");
    private JTextField description = new JTextField(0);
    private JLabel locationLabel = new JLabel("Location");
    private JTextField location = new JTextField(0);
    private JLabel startTimeLabel = new JLabel("Start");
    private JSpinner startTime = new JSpinner();
    private JLabel endTimeLabel = new JLabel("End");
    private JSpinner endTime = new JSpinner();
    private JPanel buttonPanel = new JPanel(new FlowLayout(RIGHT));
    private JButton addButton = new JButton("Add event");
    private JButton okButton = new JButton("OK");
    private JButton cancelButton = new JButton("Cancel");

    public EditEvent() {
        super("Add event");
        DateTime now = (new DateTime()).withMillisOfSecond(0).withSecondOfMinute(0).withMinuteOfHour(0);
        initView(new Event(now, now, "", ""));
    }

    public EditEvent(Event e) {
        super("Edit event");

        initView(e);
    }

    private void initView(Event e) {
        setLayout(new BorderLayout());
        
        importPanel.add(importButton);
        add(importPanel, BorderLayout.NORTH);

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        add(centerPanel, BorderLayout.CENTER);

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

        add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        description.setText( e.getDescription() );
        location.setText( e.getLocation() );

        cancelButton.addActionListener( new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                EditEvent.this.dispose();
            }
        } );

        addButton.addActionListener( new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        } );

        importButton.addActionListener( new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        } );

        this.pack();
        this.setVisible(true);
    }

    private void setSpinnerModels(DateTime eventStart, DateTime eventEnd) {
        SpinnerModel startModel = new SpinnerDateModel(toJavaDate(eventStart),
                                        toJavaDate(eventStart.minusYears(1)),
                                        toJavaDate(eventStart.plusYears(1)),
                                        Calendar.YEAR);
        SpinnerModel endModel = new SpinnerDateModel(toJavaDate(eventEnd),
            toJavaDate(eventEnd.minusYears(1)),
            toJavaDate(eventEnd.plusYears(1)),
            Calendar.DAY_OF_MONTH);

        startTime.setModel( startModel );
        endTime.setModel( endModel );

        startTime.setEditor(new JSpinner.DateEditor(startTime, DATE_FORMAT));
        endTime.setEditor(new JSpinner.DateEditor(endTime, DATE_FORMAT));
        
    }

    private void setLeft(JComponent c) {
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
    }
}
