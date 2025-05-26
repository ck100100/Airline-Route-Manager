package Controllers;

import Object.EmployeeLog;
import Object.Pilot;

import java.util.ArrayList;
import java.util.List;

public class ControllerPilot {
    private List<Pilot> pilots = new ArrayList<>();

    public void addPilot(Pilot pilot) {
        pilots.add(pilot);
    }

    public List<Pilot> getAllPilots() {
        return new ArrayList<>(pilots);
    }

    public Pilot getPilotByName(String name) {
        for (Pilot p : pilots) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public void removePilot(Pilot pilot) {
        pilots.remove(pilot);
    }
}
