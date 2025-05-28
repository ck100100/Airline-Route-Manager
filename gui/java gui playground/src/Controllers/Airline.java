package Controllers;

public class Airline {
    public ControllerFlight controllerFlight;
    public ControllerAirplane controllerAirplane;
    public ControllerFoodMenu controllerFoodMenu;
    public ControllerAirport controllerAirport;
    public ControllerPilot controllerPilot;
    public ControllerFlightAttendant controllerFlightAttendant;

    public Airline(ControllerFlight controllerFlight, ControllerAirplane controllerAirplane, ControllerFoodMenu controllerFoodMenu, ControllerAirport controllerAirport) {
        this.controllerFlight = controllerFlight;
        this.controllerAirplane = controllerAirplane;
        this.controllerFoodMenu = controllerFoodMenu;
        this.controllerAirport = controllerAirport;
        this.controllerPilot = new ControllerPilot();
        this.controllerFlightAttendant = new ControllerFlightAttendant();
    }
}
