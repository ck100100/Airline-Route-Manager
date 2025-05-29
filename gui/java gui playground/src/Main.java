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
                var controllerAirport = new ControllerAirport();
                Airline airline = new Airline(controllerFlight, controllerAirplane, controllerFoodMenu, controllerAirport);

                var main = new PageViewFlightList(airline);
                main.show();
            }
        });
    }

    public void showPageSelectionDialog() {
        int choice = JOptionPane.showOptionDialog(
                null,
                "Select a page to open:",
                "Page Selection",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Assign Job", "Job List"},
                "Assign Job"
        );
        if (choice == 0) {
            new Pages.AssignJobPage();
        } else {
            new Pages.JobListPage();
        }
    }
}
