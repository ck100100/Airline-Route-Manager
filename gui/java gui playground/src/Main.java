import Controllers.ControllerAirplane;
import Controllers.ControllerFoodMenu;
import Controllers.ControllerMenuItem;
import Pages.Flights.PageCreateFlight;
import Pages.Flights.PageViewFlightList;
import Pages.Menu.PageInsertMenuItem;
import Pages.Menu.PageMenuCreation;
import Pages.PilotReports.PageAvailablePlanes;
import Pages.PilotReports.PagePlaneReport;
import Pages.PilotReports.PageRecentFlight;
import components.MainWindow;
import Controllers.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Define controllers
                var controllerFlight = new ControllerFlight();
                var controllerAirplane = new ControllerAirplane();
                var controllerFoodMenu = new ControllerFoodMenu();
                Airline airline = new Airline(controllerFlight, controllerAirplane, controllerFoodMenu);

                var main = new PageViewFlightList(airline);
                main.show();
            }
        });
    }
}