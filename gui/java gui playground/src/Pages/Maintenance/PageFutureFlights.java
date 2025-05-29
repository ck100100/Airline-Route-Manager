package Pages.Maintenance;

import Controllers.Airline;
import Controllers.ControllerFlight;
import components.FormInput;
import components.FormInputLarge;
import components.MainWindow;
import Object.AirplaneLog;
import Object.Flight;
import utils.DateTime;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class PageFutureFlights extends MainWindow {
    private AirplaneLog plane;
    public Airline airline;
    private Flight selectedFlight;
    private List<Flight> planeFlights;
    private DefaultTableModel tableModel;
    private JTable table;
    public PageFutureFlights(AirplaneLog plane, Airline airline){
        super("Future Flights");
        this.plane = plane;
        this.airline = airline;
    }

    @Override
    protected JPanel generateBody(){
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(generateTable());
        //panel.add(generateForm());
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
        planeFlights = controller.getFlightsAfterDate(dateTime,plane);
        for(Flight flight : planeFlights){
            Object[] row = {flight.flightNumber};
            tableModel.addRow(row);
        }
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        return panel;
    }

    protected JPanel generateButtons(){
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        var setMaintenanceDatesBtn = new JButton("Set Maintenance Dates");
        var cancelBtn = new JButton("Cancel");
        var groundReportBtn = new JButton("Ground Report");

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setMaintenanceDatesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSetDates();
            }
        });

        groundReportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onGroundReport();
            }
        });
        panel.add(groundReportBtn);
        panel.add(setMaintenanceDatesBtn);
        panel.add(cancelBtn);
        return panel;
    }

    public void onCancel(){
        closeWindow();
    }

    public void onSetDates(){
        var setMaintenanceDates = new PageSetMaintenanceDates(plane,airline);
        setMaintenanceDates.show();

    }

    public void onGroundReport(){
        var reportPage = new PageGroundReport(plane,airline);
        reportPage.show();
    }
}
