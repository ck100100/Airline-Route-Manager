package Controllers;

public class Airline {
    public ControllerFlight controllerFlight;
    public ControllerAirplane controllerAirplane;
    public ControllerFoodMenu controllerFoodMenu;
    public ControllerAirport controllerAirport;
    public ControllerEmployee controllerEmployee;

    public Airline() {

        this.controllerFlight = new ControllerFlight();
        this.controllerAirplane = new ControllerAirplane();
        this.controllerFoodMenu = new ControllerFoodMenu();
        this.controllerAirport = new ControllerAirport();
        this.controllerEmployee = new ControllerEmployee();
    }
}
