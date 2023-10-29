import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EventCalendarManagement extends JFrame {
    private List<Event> events = new ArrayList<>();
    private JTable eventTable;
    private DefaultTableModel tableModel;
    private JButton addButton, editButton, deleteButton, detailsButton, editDetailsButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventCalendarManagement().setVisible(true));
    }
    public EventCalendarManagement() {
        setTitle("Event Calendar Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        initComponents();
    }
    private void initComponents() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Number");
        tableModel.addColumn("Event Name");
        tableModel.addColumn("Event Date");
        tableModel.addColumn("Event Details");

        eventTable = new JTable(tableModel);
        eventTable.getColumnModel().getColumn(0).setPreferredWidth(50); // Set column width for numbers

        addButton = new JButton("Add Event");
        editButton = new JButton("Edit Event");
        deleteButton = new JButton("Delete Event");
        detailsButton = new JButton("Event Details");
        editDetailsButton = new JButton("Edit Details");

        setLayout(new BorderLayout());
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        controlPanel.add(detailsButton);
        controlPanel.add(editDetailsButton);

        add(new JScrollPane(eventTable), BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Event newEvent = createNewEvent();
                if (newEvent != null) {
                    events.add(newEvent);
                    Collections.sort(events);
                    updateEventTable();
                }
            }
        });
}
