package Pages;

import Controllers.Airline;
import Pages.Airportlogs.PageAirportGUI;
import Pages.FlightBooking.PageSearchFlight;
import Pages.Flights.PageCreateFlight;
import Pages.Flights.PageViewFlightList;
import Pages.PilotReports.PagePlaneReport;
import components.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PageMain extends MainWindow {
    private Airline airline;
    public PageMain(Airline airline) {
        super("SpyOps");
        this.airline = airline;
    }

    @Override
    protected JPanel generateBody() {
        JPanel panel = new JPanel();


        var viewFlightsBtn = new JButton("Manage Flights");
        viewFlightsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPage(new PageViewFlightList(airline));
            }
        });
        var pilotReportsBtn = new JButton("Pilot Reports");
        pilotReportsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // fill this in
            }
        });
        var foodMenuBtn = new JButton("Food Menu");
        foodMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // fill this in
            }
        });
        var airportsBtn = new JButton("Manage Airports");
        airportsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // fill this in
            }
        });
        var employeesBtn = new JButton("Manage Employees");
        employeesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // fill this in
            }
        });
        var airplanesBtn = new JButton("Manage Airplanes");
        airplanesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // fill this in
            }
        });
        var maintenanceBtn = new JButton("Maintenance");
        maintenanceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // fill this in
            }
        });
        var flightBookingBtn = new JButton("Book Flight");
        flightBookingBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPage(new PageSearchFlight(airline));
            }
        });
        var contractorsBtn = new JButton("Manage Contractors");
        contractorsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // fill this in
            }
        });

        panel.add(viewFlightsBtn);
        panel.add(pilotReportsBtn);
        panel.add(foodMenuBtn);
        panel.add(airportsBtn);
        panel.add(employeesBtn);
        panel.add(airplanesBtn);
        panel.add(maintenanceBtn);
        panel.add(flightBookingBtn);
        panel.add(contractorsBtn);

        return panel;
    }

    private void showPage(MainWindow page) {
        page.show();
    }
}
