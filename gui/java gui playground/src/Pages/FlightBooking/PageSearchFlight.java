package Pages.FlightBooking;

import Controllers.Airline;
import components.*;
import Object.*;
import utils.DateTime;
import utils.Exceptions.InvalidInputException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PageSearchFlight extends MainWindow {
    private Airline airline;
    FormDropdown departureAirportInput;
    FormDropdown arrivalAirportInput;
    FormDate flightDateInput;
    FormDate arrivalDateInput;
    ArrayList<AirportLog> airports;
    ErrorText errorText;
    public PageSearchFlight(Airline airline) {
        super("Search For Flight");
        this.airline = airline;
    }

    @Override
    protected JPanel generateBody() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        ArrayList<String> airportNames = new ArrayList<>();
        airports = (ArrayList<AirportLog>) airline.controllerAirport.getAllAirports();
        for(AirportLog airport : airports)
            airportNames.add(airport.getNameAirport());

        departureAirportInput = new FormDropdown("Departure Airport", airportNames, null);
        departureAirportInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        arrivalAirportInput = new FormDropdown("Arrival Airport", airportNames, null);
        arrivalAirportInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        flightDateInput = new FormDate("Flight Date", 01, 01, 2025, true);

        var searchBtn = ButtonFactory.primary("Search");
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSearch();
            }
        });

        errorText = new ErrorText();

        panel.add(departureAirportInput);
        panel.add(arrivalAirportInput);
        panel.add(flightDateInput);
        panel.add(errorText);
        panel.add(searchBtn);

        return panel;
    }

    private void onSearch() {
        try {
            if(departureAirportInput.getSelectedIndex() == 0)
                throw new InvalidInputException("You must select a departure airport");
            else if(arrivalAirportInput.getSelectedIndex() == 0)
                throw new InvalidInputException("You must select a arrival airport");

            DateTime flightDate = flightDateInput.getValue();
            int departureAirportID = airports.get(departureAirportInput.getSelectedIndex() - 1).getAirportID();
            int arrivalAirportID = airports.get(arrivalAirportInput.getSelectedIndex() - 1).getAirportID();
            DateTime todaysDate = DateTime.now();
            todaysDate.minute = 0;
            todaysDate.hour = 0;

            if(departureAirportID == arrivalAirportID)
                throw new InvalidInputException("Flight can not depart and arrive at the same airport");
            else if(flightDate.isBefore(todaysDate))
                throw new InvalidInputException("Can not select a past date");

            errorText.setErrorMsg("");

            ArrayList<Flight> flights = (ArrayList<Flight>) airline.controllerFlight.getFlights(departureAirportID, arrivalAirportID, flightDate);
            var nextPage = new PageViewFlightsToBook(airline, flights);
            nextPage.show();
            closeWindow();
        } catch (InvalidInputException e) {
            errorText.setErrorMsg(e.getMessage());
        }
    }
}
