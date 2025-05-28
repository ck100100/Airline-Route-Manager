package Object;

import Controllers.ControllerAirplane;
import Controllers.ControllerFlight;
import utils.*;

import java.util.ArrayList;
import java.util.List;

public class Flight {
    public int flightID;
    public String flightNumber;
    public Integer airplaneID;
    public List<Integer> pilotListID = null;
    public List<Integer> flightAttendantIDLlist = null;
    public Double pricePerSeat;
    public Integer departureAirportID;
    public DateTime departureTime;
    public Integer arrivalAirportID;
    public DateTime arrivalTime;
    public Integer menuID;
    public Integer[] bookingList = {};
    public Status status = Status.draft;
    public FlightReport report;

    public Flight() {}

    public void setBasicDetails(String flightNumber, Integer depAirportID, DateTime depTime, Integer arrAirportID, DateTime arrTime) {
        this.flightNumber = flightNumber;
        this.departureTime = depTime;
        this.departureAirportID = depAirportID;
        this.arrivalTime = arrTime;
        this.arrivalAirportID = arrAirportID;
    }

    public Status getStatus(ControllerAirplane controllerAirplane) {
        if(arrivalTime.isBefore(DateTime.now())) {
            status = Status.completed;
            return status;
        }

        boolean isNonApproved =
                flightNumber != ""
                && departureTime != null
                && arrivalTime != null
                && departureAirportID != null
                && arrivalAirportID != null;
        boolean isApproved =
                isNonApproved
                && pricePerSeat != null
                && menuID != null
                && airplaneID != null
                && hasEnoughFlightAttendants()
                && hasEnoughPilots();

        AirplaneLog airplane = controllerAirplane.getAirplaneByID(airplaneID);
        if(airplane.getStatus() == AirplaneStatus.maintenanceApproved || airplane.getStatus() == AirplaneStatus.awaitingMaintenance) {
            status = Status.cancelled;
            return status;
        }

        if (isApproved)
            status = Status.approved;
        else if (isNonApproved)
            status = Status.non_approved;
        else
            status = Status.draft;
    }

    public boolean setAirplaneID(Integer airplaneID) {
        // this needs to account for planes which are not available
        this.airplaneID = airplaneID;
        return true;
    }

    public ArrayList<Integer> setPilots(ArrayList<Integer> pilotsIDList) {
        /*
        Accepts a list of all the pilots the flight will have.
        It will return the id's of any pilot that is unnable to
        make the flight
         */

        // needs to check to see if pilots are actually available
        this.pilotListID = pilotsIDList;

        ArrayList<Integer> unavailablePilots = new ArrayList<>();
        return unavailablePilots;
    }

    public ArrayList<Integer> setFlightAttendants(ArrayList<Integer> flightAttendantIDList) {
        /*
        Accepts a list of all the flight attendants the flight will have.
        It will return the id's of any flight attendant that is unnable to
        make the flight
         */

        // needs to check to see if pilots are actually available
        this.flightAttendantIDLlist = flightAttendantIDList;

        ArrayList<Integer> unavailableFlightAttendants = new ArrayList<>();
        return unavailableFlightAttendants;
    }

    private boolean hasEnoughFlightAttendants() {
        return true; // must create this method
    }

    private boolean hasEnoughPilots() {
        return true; // must create this method
    }

    public void setMenu(Integer menuID) {
        this.menuID = menuID;
    }

    public void changeFlightNumber(String newFlightNumber, ControllerFlight controllerFlight) throws IllegalArgumentException {
        boolean isDuplicateFlightNumber = false;
        if(isDuplicateFlightNumber)
            throw new IllegalArgumentException("This flight number already exists!");

        flightNumber = newFlightNumber;
    }
}
