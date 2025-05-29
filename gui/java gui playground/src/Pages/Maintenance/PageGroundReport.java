package Pages.Maintenance;
import components.FormInput;
import components.FormInputLarge;
import components.MainWindow;
import Controllers.Airline;

import Object.AirplaneLog;
import Object.Flight;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageGroundReport extends MainWindow {
    private AirplaneLog plane;
    private Airline airline;
    private Flight selectedFlight;
    public PageGroundReport(AirplaneLog plane, Airline airline){
        super("Ground Report");
        this.plane = plane;
        this.airline = airline;
    }

    @Override
    protected JPanel generateBody(){
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        System.out.println(plane.getGroundFlightID());
        panel.add(generateForm());
        panel.add(generateButtons());

        return panel;
    }

    protected JPanel generateForm() {
        var panel = new JPanel();

        selectedFlight = airline.controllerFlight.getFlightByID(plane.getGroundFlightID());

        var panelLeft = new JPanel();
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
        var planeInput = new FormInput("Plane", false, plane.getType());
        var flightNumInput = new FormInput("Flight Number", false, selectedFlight.flightNumber);
        var departureAirportInput = new FormInput("Departure Airport", false, selectedFlight.departureAirportID.toString());
        var arrivalAirport = new FormInput("Arrival Airport", false, selectedFlight.arrivalAirportID.toString());
        var pilotInput = new FormInput("Pilot", false,"");
        var copilotInput = new FormInput("Co-Pilot", false, "");

        panelLeft.add(planeInput);
        panelLeft.add(flightNumInput);
        panelLeft.add(departureAirportInput);
        panelLeft.add(arrivalAirport);
        panelLeft.add(pilotInput);
        panelLeft.add(copilotInput);

        var panelRight = new JPanel();
        String description = selectedFlight.report != null ? selectedFlight.report.getDescription() : "N/A";
        String status = (selectedFlight.report != null && selectedFlight.report.getStatus() != null)
                ? selectedFlight.report.getStatus().toString()
                : "N/A";

        var descriptionInput = new FormInputLarge("Description", false, description);
        var statusInput = new FormInput("Status", false, status);
        panelRight.add(descriptionInput);
        panelRight.add(statusInput);

        panel.add(panelLeft);
        panel.add(panelRight);
        return panel;
    }

    protected JPanel generateButtons(){
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        var returnBtn = new JButton("Return");
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onReturn();
            }
        });
        panel.add(returnBtn);
        return panel;
    }
    public void onReturn(){
        closeWindow();
    }
}
