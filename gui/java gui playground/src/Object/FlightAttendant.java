package Object;

public class FlightAttendant extends Employee {
    private String languagesSpoken;
    private String seniorityLevel;
    private boolean safetyTrainingCompleted;

    private static int nextFAID = 1;
    private int flightAttendantID;

    public FlightAttendant(String name) {
        super(name);
        this.flightAttendantID = nextFAID++;
    }

    public int getFlightAttendantID() {
        return flightAttendantID;
    }

    public String getLanguagesSpoken() {
        return languagesSpoken;
    }

    public void setLanguagesSpoken(String languagesSpoken) {
        this.languagesSpoken = languagesSpoken;
    }

    public String getSeniorityLevel() {
        return seniorityLevel;
    }

    public void setSeniorityLevel(String seniorityLevel) {
        this.seniorityLevel = seniorityLevel;
    }

    public boolean isSafetyTrainingCompleted() {
        return safetyTrainingCompleted;
    }

    public void setSafetyTrainingCompleted(boolean safetyTrainingCompleted) {
        this.safetyTrainingCompleted = safetyTrainingCompleted;
    }

    @Override
    public String toString() {
        return super.toString() + " [Flight Attendant]";
    }
}

