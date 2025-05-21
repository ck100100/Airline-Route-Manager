package Pages.Flights;

import components.ButtonFactory;
import components.MainWindow;
import Object.Flight;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Controllers.Airline;

public class PageViewFlightList extends MainWindow {
    private Airline airline;

    public PageViewFlightList(Airline airline) {
        super("View Flights");
        this.airline = airline;
    }

    @Override
    protected JPanel generateBody() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        var createFlightBtn = ButtonFactory.primary("Create Flight");
        createFlightBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        createFlightBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSelectCreateFlight();
            }
        });

        panel.add(createFlightBtn);
        panel.add(generateTable());

        return panel;
    }

    private JPanel generateTable() {
        JPanel panel = new JPanel();

        String[] tableColumnNames = {"flight number", "departure airport", "departure time", "arrival airport", "arrival time"};
        String[][] tableData = parseFlightDetails(airline.controllerFlight.getAllFlights());

        DefaultTableModel tableModel = new DefaultTableModel(tableData, tableColumnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // overrides cell editing function to disable cell editing
                return false;
            }
        };

        var table = new JTable(tableModel);
        table.setEnabled(true);


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowClicked = table.getSelectedRow();
                System.out.println(rowClicked);
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        return panel;
    }

    private String[][] parseFlightDetails(List<Flight> flightList) {
        String[][] parsedFlights = new String[flightList.size()][];
        for(int i=0; i < flightList.size(); i++) {
            Flight flight = flightList.get(i);
            String[] parsedFlight = {
                    flight.flightNumber.toString(),
                    flight.departureAirportID.toString(),
                    flight.departureTime.parse(),
                    flight.arrivalAirportID.toString(),
                    flight.arrivalTime.parse()
            };
            parsedFlights[i] = parsedFlight;
        }

        return parsedFlights;
    }

    private void onSelectsViewFlight(Flight flight) {
        // this needs to be implemented!
    }

    private void onSelectCreateFlight() {
        var newPage = new PageCreateFlight();
        newPage.show();
    }
}
