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
    private JTable table;
    private List<Flight> flightList;

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

        var refreshBtn = ButtonFactory.primary("Refresh");
        refreshBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRefresh();
            }
        });

        panel.add(createFlightBtn);
        panel.add(generateTable());
        panel.add(refreshBtn);

        return panel;
    }

    private JPanel generateTable() {
        JPanel panel = new JPanel();

        table = new JTable();
        updateTableModel(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(2).setMinWidth(150);
        table.getColumnModel().getColumn(4).setMinWidth(150);
        table.setEnabled(true);


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowClicked = table.getSelectedRow();
                System.out.println(rowClicked);
                onSelectsViewFlight(flightList.get(rowClicked));
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        return panel;
    }

    private void updateTableModel(JTable table) {
        String[] tableColumnNames = {"flight number", "departure airport", "departure time", "arrival airport", "arrival time"};
        flightList = airline.controllerFlight.getAllFlights();
        String[][] tableData = parseFlightDetails(flightList);

        DefaultTableModel tableModel = new DefaultTableModel(tableData, tableColumnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // overrides cell editing function to disable cell editing
                return false;
            }
        };

        table.setModel(tableModel);
    }

    private String[][] parseFlightDetails(List<Flight> flightList) {
        String[][] parsedFlights = new String[flightList.size()][];
        for(int i=0; i < flightList.size(); i++) {
            Flight flight = flightList.get(i);
            String[] parsedFlight = {
                    flight.flightNumber.toString(),
                    airline.controllerAirport.getAirportByID(flight.departureAirportID).getNameAirport(),
                    flight.departureTime.parse(),
                    airline.controllerAirport.getAirportByID(flight.arrivalAirportID).getNameAirport(),
                    flight.arrivalTime.parse()
            };
            parsedFlights[i] = parsedFlight;
        }

        return parsedFlights;
    }

    private void onSelectsViewFlight(Flight flight) {
        var newPage = new PageViewFlight(airline, flight);
        newPage.show();
    }

    private void onSelectCreateFlight() {
        var newPage = new PageCreateFlight(airline);
        newPage.show();
    }

    private void onRefresh() {
        // this needs to be implemented!
        updateTableModel(table);
    }
}
