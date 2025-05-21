package Object;

public class AirplaneLog {
    private static int idCounter = 1;

    private final int id;
    private String name;
    private int capacity;
    private double maxTotalLoad;
    private int maxCarryOns;
    private double maxCargoSize;
    private double maxFlightRange;
    private int numberOfRows;
    private int seatsPerRow;
    private boolean active;

    public AirplaneLog(String name) {
        this.id = idCounter++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type.toLowerCase();
    }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public double getMaxTotalLoad() { return maxTotalLoad; }
    public void setMaxTotalLoad(double maxTotalLoad) { this.maxTotalLoad = maxTotalLoad; }

    public int getMaxCarryOns() { return maxCarryOns; }
    public void setMaxCarryOns(int maxCarryOns) { this.maxCarryOns = maxCarryOns; }

    public double getMaxCargoSize() { return maxCargoSize; }
    public void setMaxCargoSize(double maxCargoSize) { this.maxCargoSize = maxCargoSize; }

    public double getMaxFlightRange() { return maxFlightRange; }
    public void setMaxFlightRange(double maxFlightRange) { this.maxFlightRange = maxFlightRange; }

    public int getNumberOfRows() { return numberOfRows; }
    public void setNumberOfRows(int numberOfRows) { this.numberOfRows = numberOfRows; }

    public int getSeatsPerRow() { return seatsPerRow; }
    public void setSeatsPerRow(int seatsPerRow) { this.seatsPerRow = seatsPerRow; }

    public boolean isActive() { return active; }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("ID %d: %s (%s)", id, name, type);
    }

}

