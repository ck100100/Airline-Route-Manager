package Pages.FlightBooking;

import Controllers.*;
import Object.*;
import components.MainWindow;
import utils.DateTime;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PageViewFlightsToBook extends MainWindow {
    private JTable table;
    private List<Flight> flightList;
    private Airline airline;
    private int numberOfBreifcases;

    public PageViewFlightsToBook(Airline airline, List<Flight> flightList, int numberOfBreifcases) {
        super("Search For Flight");
        this.airline = airline;
        this.flightList = flightList;
        this.numberOfBreifcases = numberOfBreifcases;
    }

    @Override
    protected JPanel generateBody() {
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(generateTable());

        return panel;
    }

    private JPanel generateTable() {
        JPanel panel = new JPanel();

        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        updateTableModel(table);
        table.getColumnModel().getColumn(2).setMinWidth(150);
        table.getColumnModel().getColumn(4).setMinWidth(150);
        table.setEnabled(true);


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowClicked = table.getSelectedRow();
                System.out.println(rowClicked);
                onSelectsBookFlight(flightList.get(rowClicked));
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        var exitBtn = new JButton("exit");
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onExit();
            }
        });
        panel.add(exitBtn);

        return panel;
    }

    private void updateTableModel(JTable table) {
        String[] tableColumnNames = {"flight number", "departure airport", "departure time", "arrival airport", "arrival time", "price"};
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
            final double pricePerBreifcase = 5.0;
            double totalPrice = pricePerBreifcase * numberOfBreifcases + flight.pricePerSeat;
            String[] parsedFlight = {
                    flight.flightNumber.toString(),
                    airline.controllerAirport.getAirportByID(flight.departureAirportID).getNameAirport(),
                    flight.departureTime.parse(),
                    airline.controllerAirport.getAirportByID(flight.arrivalAirportID).getNameAirport(),
                    flight.arrivalTime.parse(),
                    Double.toString(totalPrice) + "€"
            };
            parsedFlights[i] = parsedFlight;
        }

        return parsedFlights;
    }

    private void onSelectsBookFlight(Flight flight) {
        var nextPage = new PagePassengerDetails(flight);
        nextPage.show();
        this.closeWindow();
    }

    private void onExit() {
        closeWindow();
    }
}
