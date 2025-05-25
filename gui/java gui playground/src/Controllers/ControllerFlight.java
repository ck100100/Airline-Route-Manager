package Controllers;
import Object.Flight;
import Object.FlightReport;
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

        for(int i = 0; i < flightList.size(); i++) {
            var flight = flightList.get(i);
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
    private void generateMockData() {
        var f1 = new Flight();
        f1.setBasicDetails(
            "A1234",
            3,
            new DateTime(00, 14, 10, 01, 25),
            10,
            new DateTime(00, 14, 10, 02, 25)
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
