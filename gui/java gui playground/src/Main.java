import Controllers.ControllerMenuItem;
import Pages.Flights.PageCreateFlight;
import Pages.Menu.PageInsertMenuItem;
import Pages.Menu.PageMenuCreation;
import Pages.PilotReports.PageAvailablePlanes;
import Pages.PilotReports.PagePlaneReport;
import Pages.PilotReports.PageRecentFlight;
import components.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//                MainWindow main = new PageCreateFlight("Dev Ops");
                // var main = new PageInsertMenuItem();
                var controller = new ControllerMenuItem();
                var main = new PageMenuCreation(controller);
                main.show();
            }
        });
    }
}