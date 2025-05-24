package Object;

import java.time.LocalTime;

public class AirportLog {
    private static int counter = 1;

    private final int airportID;
    private String nameAirport;
    private boolean status;
    private String location;
    private int capacity;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private int maxFlights;
    private float mainRunwayLength;
    private int numberOfRunways;

    public AirportLog(String name) {
        this.airportID = counter++;
        this.nameAirport = name;
        this.status = true;
    }

    public int getAirportID() { return airportID; }

    public String getNameAirport() { return nameAirport; }
    public void setNameAirport(String nameAirport) { this.nameAirport = nameAirport; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public LocalTime getOpeningTime() { return openingTime; }
    public void setOpeningTime(LocalTime openingTime) { this.openingTime = openingTime; }

    public LocalTime getClosingTime() { return closingTime; }
    public void setClosingTime(LocalTime closingTime) { this.closingTime = closingTime; }

    public int getMaxFlights() { return maxFlights; }
    public void setMaxFlights(int maxFlights) { this.maxFlights = maxFlights; }

    public float getMainRunwayLength() { return mainRunwayLength; }
    public void setMainRunwayLength(float mainRunwayLength) { this.mainRunwayLength = mainRunwayLength; }

    public int getNumberOfRunways() { return numberOfRunways; }
    public void setNumberOfRunways(int numberOfRunways) { this.numberOfRunways = numberOfRunways; }

    @Override
    public String toString() {
        return airportID + " – (" + nameAirport + ")";
    }
}
