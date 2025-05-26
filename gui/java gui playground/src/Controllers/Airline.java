package Controllers;

public class Airline {
    public ControllerFlight controllerFlight;
    public ControllerAirplane controllerAirplane;
    public ControllerFoodMenu controllerFoodMenu;

    public Airline(ControllerFlight controllerFlight, ControllerAirplane controllerAirplane, ControllerFoodMenu controllerFoodMenu) {
        this.controllerFlight = controllerFlight;
        this.controllerAirplane = controllerAirplane;
        this.controllerFoodMenu = controllerFoodMenu;
    }
}
