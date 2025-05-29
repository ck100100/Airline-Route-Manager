package Pages.PilotReports;

import components.MainWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Controllers.ControllerFlight;
import Object.Flight;
import Object.AirplaneLog;
import utils.DateTime;

import java.time.LocalDateTime;

public class PageRecentFlight extends MainWindow {
    private AirplaneLog plane;
    private List<Flight> planeFlights;
    private DefaultTableModel tableModel;
    private JTable table;
    public PageRecentFlight(AirplaneLog plane) {

        super("Recent Flights");
        this.plane = plane;
    }

    @Override
    protected JPanel generateBody() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(generateTable());
        panel.add(generateButtons());


        return panel;
    }

    protected JPanel generateTable() {
        var panel = new JPanel();
        String[] columnNames = {"Flight Number"};
         tableModel = new DefaultTableModel(columnNames,0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        var controller = new ControllerFlight();
        LocalDateTime currDateTime = LocalDateTime.now();

        DateTime dateTime = new DateTime(currDateTime.getMinute(), currDateTime.getHour()
                , currDateTime.getDayOfMonth(),currDateTime.getMonthValue(),currDateTime.getYear());
        planeFlights = controller.getFlightsBeforeDate(dateTime,plane);
        for(Flight flight : planeFlights){
            Object[] row = {flight.flightNumber};
            tableModel.addRow(row);
        }
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        return panel;
    }

    protected JPanel generateButtons() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        var selectBtn = new JButton("Select");
        var cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        selectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSelect();
            }
        });

        panel.add(selectBtn);
        panel.add(cancelBtn);
        return panel;
    }

    public void onCancel() {
        closeWindow();
    }

    public void onSelect() {
        int selectedRow = table.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(null,"Select a flight");
            return;
        }
        Flight selectedFlight = planeFlights.get(selectedRow);
        var FlightReportPage = new PagePlaneReport(selectedFlight,plane);
        FlightReportPage.show();
    }
}
