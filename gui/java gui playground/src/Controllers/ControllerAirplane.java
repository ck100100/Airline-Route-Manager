package Controllers;

import Object.AirplaneLog;
import Object.Flight;
import utils.AirplaneStatus;

import java.util.ArrayList;
import java.util.List;

public class ControllerAirplane {
    private final List<AirplaneLog> airplanes;

    public ControllerAirplane() {
        airplanes = new ArrayList<>();

        generateMockData();
    }

        private AirplaneLog makeAirplane(String name, int capacity, double maxTotalLoad, int maxCarryOns,
                                     double maxCargoSize, double maxFlightRange, int rows,
                                     int seatsPerRow, boolean active, String type) {
        AirplaneLog a = new AirplaneLog(name);
        a.setCapacity(capacity);
        a.setMaxTotalLoad(maxTotalLoad);
        a.setMaxCarryOns(maxCarryOns);
        a.setMaxCargoSize(maxCargoSize);
        a.setMaxFlightRange(maxFlightRange);
        a.setNumberOfRows(rows);
        a.setSeatsPerRow(seatsPerRow);
        a.setActive(active);
        a.setType(type);
        return a;
    }

    public AirplaneLog getAirplaneByID(Integer airplaneID) {
        boolean found = false;
        int i = 0;
        AirplaneLog currAirplane = null;
        while(!found && i < airplanes.size()) {
            currAirplane = airplanes.get(i);
            if(currAirplane.getId() == airplaneID)
                found = true;
            else
                i++;
        }
        if(found)
            return currAirplane;
        else
            return null;

    }




    public void addAirplane(AirplaneLog airplane) {
        airplanes.add(airplane);
    }

    public void removeAirplane(AirplaneLog airplane) {
        airplanes.remove(airplane);
    }

    public List<AirplaneLog> getAllAirplanes() {
        return airplanes;
    }


    private void generateMockData() {
        airplanes.add(makeAirplane("Boeing 737", 160, 18000, 80, 15.5, 5600, 32, 6, true, "passenger"));
        airplanes.add(makeAirplane("Airbus A320", 180, 19500, 90, 18.0, 6100, 30, 6, true, "passenger"));
        airplanes.add(makeAirplane("Cessna 172", 4, 1100, 4, 0.5, 1280, 5, 2, false, "cargo"));
        airplanes.add(makeAirplane("Boeing 747", 416, 396900, 200, 60.0, 13850, 60, 10, true, "passenger"));
        airplanes.add(makeAirplane("Antonov An-225", 6, 640000, 0, 130.0, 15400, 3, 2, false, "cargo"));
        airplanes.add(makeAirplane("Lockheed C-5 Galaxy", 75, 381000, 10, 110.0, 11100, 10, 8, false, "cargo"));
        airplanes.add(makeAirplane("Embraer E190", 100, 12500, 60, 10.5, 4260, 25, 4, true, "passenger"));
        airplanes.add(makeAirplane("McDonnell Douglas DC-10", 270, 250000, 120, 55.0, 9650, 40, 7, true, "passenger"));
        airplanes.add(makeAirplane("Airbus Beluga", 2, 47000, 0, 120.0, 2775, 2, 1, true, "cargo"));
        airplanes.add(makeAirplane("Bombardier CRJ700", 70, 11200, 40, 8.0, 3700, 20, 4, true, "passenger"));
        airplanes.add(makeAirplane("Douglas DC-3", 21, 7000, 10, 2.5, 2410, 7, 3, false, "passenger"));
        airplanes.add(makeAirplane("Boeing 777F", 0, 347800, 0, 120.0, 9200, 0, 0, true, "cargo"));
        airplanes.add(makeAirplane("Pilatus PC-12", 9, 1200, 8, 1.2, 2800, 3, 3, true, "passenger"));
        AirplaneLog p1 = new AirplaneLog("Plane");
        p1.setStatus(AirplaneStatus.active);
        p1.setGroundFlightID(20);
        p1.setCapacity(10);
        p1.setType("cargo");
        airplanes.add(p1);
    }
}
