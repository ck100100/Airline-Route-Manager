package Controllers;

import Object.EmployeeLog;
import Object.FlightAttendant;

import java.util.ArrayList;
import java.util.List;

public class ControllerFlightAttendant {
    private List<FlightAttendant> flightAttendants = new ArrayList<>();

    public void addFlightAttendant(FlightAttendant fa) {
        flightAttendants.add(fa);
    }

    public List<FlightAttendant> getAllFlightAttendants() {
        return new ArrayList<>(flightAttendants);
    }

    public FlightAttendant getFlightAttendantByName(String name) {
        for (FlightAttendant fa : flightAttendants) {
            if (fa.getName().equalsIgnoreCase(name)) {
                return fa;
            }
        }
        return null;
    }

    public void removeFlightAttendant(FlightAttendant fa) {
        flightAttendants.remove(fa);
    }
}
