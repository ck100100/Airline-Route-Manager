package Controllers;
import Object.*;
import utils.Status;
import utils.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControllerFlight {
    private List<Flight> flightList = new ArrayList<Flight>();

    public ControllerFlight() {
        generateMockData();
    }

    public List<Flight> getAllFlights() {
        return flightList;
    }
    private int counter = 0;

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
        while (item == null && i < flightList.size()) {
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

    public Flight getFlightAfterDate(DateTime dateTime, AirplaneLog airplane) {
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

    private boolean hasOverlappingFlights(Flight flightToCheck) {
        for(Flight currentFlight: flightList) {
            boolean equalEntities = currentFlight.airplaneID != flightToCheck.airplaneID
                    || containsMutualItem((ArrayList<Integer>) currentFlight.pilotListID, (ArrayList<Integer>) flightToCheck.pilotListID)
                    || containsMutualItem((ArrayList<Integer>) currentFlight.flightAttendantIDLlist, (ArrayList<Integer>) flightToCheck.flightAttendantIDLlist);
            if(equalEntities) {
                boolean hasNoOverlap = currentFlight.arrivalTime.isBefore(flightToCheck.departureTime)
                        && flightToCheck.arrivalTime.isBefore(currentFlight.departureTime);
                if(hasNoOverlap == false)
                    return false;
            }
        }
        return true;
    }

    private boolean isPlaneAvailable(Flight flightToCheck, AirplaneLog airplane) {
        for(Flight currentFlight: flightList) {
            boolean equalEntities = currentFlight.airplaneID != airplane.getId();
            if(equalEntities) {
                boolean hasNoOverlap = currentFlight.arrivalTime.isBefore(flightToCheck.departureTime)
                        && flightToCheck.arrivalTime.isBefore(currentFlight.departureTime);
                if(hasNoOverlap == false)
                    return false;
            }
        }
        return true;
    }

    private boolean isFlightAttendantAvailable(Flight flightToCheck, FlightAttendant flightAttendant) {
        Integer[] fl = {flightAttendant.getFlightAttendantID()};
        for(Flight currentFlight: flightList) {
            boolean equalEntities = containsMutualItem((ArrayList<Integer>) currentFlight.flightAttendantIDLlist, new ArrayList<>(Arrays.asList(fl)));
            if(equalEntities) {
                boolean hasNoOverlap = currentFlight.arrivalTime.isBefore(flightToCheck.departureTime)
                        && flightToCheck.arrivalTime.isBefore(currentFlight.departureTime);
                if(hasNoOverlap == false)
                    return false;
            }
        }
        return true;
    }

    private boolean isPilotAvailable(Flight flightToCheck, Pilot pilot) {
        Integer[] pl = {pilot.getPilotID()};
        for(Flight currentFlight: flightList) {
            boolean equalEntities = containsMutualItem((ArrayList<Integer>) currentFlight.pilotListID, new ArrayList<>(Arrays.asList(pl)));
            if(equalEntities) {
                boolean hasNoOverlap = currentFlight.arrivalTime.isBefore(flightToCheck.departureTime)
                        && flightToCheck.arrivalTime.isBefore(currentFlight.departureTime);
                if(hasNoOverlap == false)
                    return false;
            }
        }
        return true;
    }


    private boolean containsMutualItem(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        for(Integer item1: list1)
            for(Integer item2: list2) {
                if (item1 == item2)
                    return true;
            }
        return false;
    }

    private List<Flight> getFlightsBeforeDate(DateTime dateTime, AirplaneLog airplane) {
        ArrayList<Flight> flightsBeforeDate = new ArrayList<>();
        for(int i=0; i < flightList.size(); i++) {
            Flight currentFlight = flightList.get(i);
            if(currentFlight.airplaneID != airplane.getId() || currentFlight.arrivalTime.isBeforeOrEqual(dateTime))
                flightsBeforeDate.add(currentFlight);
        }

        return flightsBeforeDate;
    }

    public List<Flight> getFlightsAfterDate(DateTime dateTime, AirplaneLog airplane) {
        ArrayList<Flight> flightsAfterDate = new ArrayList<>();
        for(int i=0; i < flightList.size(); i++) {
            Flight currentFlight = flightList.get(i);
            if(currentFlight.airplaneID != airplane.getId() || !currentFlight.arrivalTime.isBefore(dateTime))
                flightsAfterDate.add(currentFlight);
        }

        return flightsAfterDate;
    }

    public void fileFlight(Flight flight) {
        flight.flightID = ++counter;
        flightList.add(flight);
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
        f1.pricePerSeat = 10.0;
        f1.flightAttendantIDLlist = new ArrayList<>();
        f1.pilotListID = new ArrayList<>();

        fileFlight(f1);

    }
}
