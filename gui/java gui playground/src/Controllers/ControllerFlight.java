package Controllers;
import Object.*;
import utils.Status;
import utils.DateTime;

import java.util.ArrayList;
import java.util.List;

public class ControllerFlight {
    private List<Flight> flightList = new ArrayList<Flight>();

    public ControllerFlight() {
        generateMockData();
    }

    public List<Flight> getAllFlights() {
        return flightList;
    }

    public List<Flight> getFlightsByFilter(Status status) {
        List<Flight> filteredList = new ArrayList<Flight>();

        for (Flight flight : flightList) {
            if (flight.status == status)
                filteredList.add(flight);
        }

        return filteredList;
    }

    public void cancelFlightByID(int flightID) throws IllegalArgumentException {
        /*
        It will cancel the flight. If the flight id is invalid it will throw
        an illegal argument exception.
         */
        var flight = getFlightByID(flightID);
        flightList.remove(flight);

        // make sure that you add functionality to mark new flight as not possible
    }

    public Flight getFlightByID(Integer flightID) {
        /*
        if flight exists it returns the flight, otherwise
        it returns null
         */
        Flight item = null;
        int i = 0;
        while (item == null || i < flightList.size()) {
            var currFlight = flightList.get(i);
            if(currFlight.flightID == flightID)
                item = currFlight;
            else
                i++;
        }
        return item;
    }
    public List<Flight> getFlightsByPlaneId(int planeId){
        List<Flight> planeFlights = new ArrayList<>();
        for(Flight flight : flightList){
            if(flight.airplaneID == planeId){
                planeFlights.add(flight);
            }
        }
        return planeFlights;
    }

    private Flight getFlightAfterDate(DateTime dateTime, AirplaneLog airplane) {
        Flight earliestFlight = null;
        for(int i=0; i < flightList.size(); i++) {
            Flight currentFlight = flightList.get(i);
            if(currentFlight.airplaneID != airplane.getId() || currentFlight.departureTime.isBeforeOrEqual(dateTime))
                continue;

            if(earliestFlight != null && currentFlight.departureTime.isBefore(earliestFlight.departureTime))
                earliestFlight = currentFlight;
        }

        return earliestFlight;
    }

    private List<Flight> getFlightsAfterDate(DateTime dateTime, AirplaneLog airplane) {
        ArrayList<Flight> flightsAfterDate = new ArrayList<>();
        for(int i=0; i < flightList.size(); i++) {
            Flight currentFlight = flightList.get(i);
            if(currentFlight.airplaneID != airplane.getId() || currentFlight.arrivalTime.isBeforeOrEqual(dateTime))
                flightsAfterDate.add(currentFlight);
        }

        return flightsAfterDate;
    }

    private void generateMockData() {
        var f1 = new Flight();
        f1.setBasicDetails(
            "A1234",
            3,
            new DateTime(0, 14, 10, 1, 2025),
            10,
            new DateTime(0, 14, 10, 2, 2025)
        );
        f1.airplaneID = 2;
        var report = new FlightReport();
        report.createReport(Status.normal, "lksfrngkf");
        f1.report = report;
        f1.arrivalAirportID = 1;
        f1.departureAirportID = 2;
        f1.status = Status.approved;

        flightList.add(f1);

    }
}
