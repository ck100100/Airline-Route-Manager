package Controllers;

import Object.AirportLog;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ControllerAirport {
    private final List<AirportLog> airports;

    public ControllerAirport() {
        airports = new ArrayList<>();

        airports.add(makeAirport("JFK", "New York", 5000, LocalTime.of(5, 0), LocalTime.of(23, 30), 300, 3_500f, 4));
        airports.add(makeAirport("LAX", "Los Angeles", 6000, LocalTime.of(4, 30), LocalTime.of(23, 0), 400, 3_900f, 5));
    }

    private AirportLog makeAirport(String name, String location, int capacity,
                                   LocalTime opening, LocalTime closing,
                                   int maxFlights, float runwayLength, int runways) {
        AirportLog a = new AirportLog(name);
        a.setLocation(location);
        a.setCapacity(capacity);
        a.setOpeningTime(opening);
        a.setClosingTime(closing);
        a.setMaxFlights(maxFlights);
        a.setMainRunwayLength(runwayLength);
        a.setNumberOfRunways(runways);
        a.setStatus(true);
        return a;
    }

    public void addAirport(AirportLog airport) {
        airports.add(airport);
    }

    public void removeAirport(AirportLog airport) {
        airports.remove(airport);
    }

    public List<AirportLog> getAllAirports() {
        return airports;
    }
}
