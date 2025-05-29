package Object;

public class Pilot extends Employee {

    private String planeCertification;
    private int flightHours;

    private static int nextPilotID = 1;
    private int pilotID;

    public Pilot(String name) {
        super(name);
        this.pilotID = nextPilotID++;
    }

    public int getPilotID() {
        return pilotID;
    }


    public String getPlaneCertification() {
        return planeCertification;
    }

    public void setPlaneCertification(String planeCertification) {
        this.planeCertification = planeCertification;
    }

    public int getFlightHours() {
        return flightHours;
    }

    public void setFlightHours(int flightHours) {
        this.flightHours = flightHours;
    }

    @Override
    public String toString() {
        return super.toString() + " [Pilot]";
    }
}

