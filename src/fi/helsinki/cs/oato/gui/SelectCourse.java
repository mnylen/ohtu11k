package fi.helsinki.cs.oato.gui;

import java.awt.*;
import static java.awt.FlowLayout.*;
import java.awt.event.*;
import org.joda.time.DateTime;

import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

import fi.helsinki.cs.oato.*;
import fi.helsinki.cs.oato.Event;
import static fi.helsinki.cs.oato.Helpers.*;

public class SelectCourse extends JDialog {
    private EditEvent parent;

    public static final int PADDING_X = 0;
    public static final int PADDING_Y = 2;
    public static final int LIST_ROWS = 15;
    public static final int LIST_WIDTH = 550;

    private JPanel border = new JPanel();
    private JList list = new JList(new Vector<Object>(Main.onlineCourses.getCourses()));
    private JScrollPane listScroller = new JScrollPane(list);
    private JPanel buttons = new JPanel(new FlowLayout(RIGHT));
    private JButton okButton = new JButton("OK");
    private JButton cancelButton = new JButton("Cancel");
    
    public SelectCourse(EditEvent parent) {
        super(parent, "Select course");
        this.parent = parent;

        initView();
    }

    private void initView() {
        border.setLayout(new BorderLayout());
        border.setBorder(new EmptyBorder(PADDING_Y, PADDING_X, PADDING_Y, PADDING_X));
        add(border);

        list.setSelectedIndex(0);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(LIST_ROWS);
        list.setFixedCellWidth(LIST_WIDTH);
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = list.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        parent.selectCourse((Course) list.getModel().getElementAt(index));
                        dispose();
                    }
                }
            }
        };
        list.addMouseListener(mouseListener);
        
        border.add(listScroller, BorderLayout.CENTER);

        buttons.add(okButton);
        buttons.add(cancelButton);
        border.add(buttons, BorderLayout.SOUTH);

        cancelButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               dispose();
           }
        });
        
        okButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               if (list.getSelectedIndex() != -1) {
                    parent.selectCourse((Course) list.getSelectedValue());
                    dispose();
               }
           }
        });

        pack();
        setVisible(true);
    }
}