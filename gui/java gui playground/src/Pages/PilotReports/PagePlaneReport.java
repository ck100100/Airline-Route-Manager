package Pages.PilotReports;

import components.FormInput;
import components.FormInputLarge;
import components.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Object.Flight;
import Controllers.ControllerFlight;
import Object.AirplaneLog;
import utils.DateTime;
import utils.Status;
import utils.AirplaneStatus;

import java.time.LocalDateTime;
import java.util.List;

public class PagePlaneReport extends MainWindow {
    private final Flight selectedFlight;
    private final AirplaneLog selectedPlane;
    private List<Flight> planeFlights;
    public PagePlaneReport(Flight selectedFlight, AirplaneLog selectedPlane) {

        super("Pilot Report");
        this.selectedFlight = selectedFlight;
        this.selectedPlane = selectedPlane;
    }

    @Override
    protected JPanel generateBody() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(generateForm());
        panel.add(generateButtons());

        return panel;
    }

    protected JPanel generateForm() {
        var panel = new JPanel();

        var panelLeft = new JPanel();
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
        var planeInput = new FormInput("Plane", false, selectedPlane.getType());
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
        var descriptionInput = new FormInputLarge("Description", false, selectedFlight.report.getDescription());
        var statusInput = new FormInput("Status", false, selectedFlight.report.getStatus().toString());
        panelRight.add(descriptionInput);
        panelRight.add(statusInput);

        panel.add(panelLeft);
        panel.add(panelRight);
        return panel;
    }

    protected JPanel generateButtons() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        var groundBtn = new JButton("Ground");
        var returnBtn = new JButton("Return");
        groundBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPlaneGround();
            }
        });
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onReturn();
            }
        });

        panel.add(groundBtn);
        panel.add(returnBtn);
        return panel;
    }

    public void onPlaneGround() {
        selectedPlane.setStatus(AirplaneStatus.awaitingMaintenance);
        selectedPlane.setGroundFlightID(selectedFlight.flightID);
        var controller = new ControllerFlight();
        LocalDateTime currDateTime = LocalDateTime.now();
        DateTime dateTime = new DateTime(currDateTime.getMinute(), currDateTime.getHour(),
                currDateTime.getDayOfMonth(),currDateTime.getMonthValue(),currDateTime.getYear());
        planeFlights = controller.getFlightsAfterDate(dateTime,selectedPlane);
        for(Flight flight : planeFlights){
            if(flight.status == Status.approved ){
                flight.status = Status.cancelled;
            }
        }
        if(planeFlights.size() > 0){
            JOptionPane.showMessageDialog(null,"Flights cancelled successfully");
        }
    }

    public void onReturn() {
        closeWindow();
    }
}
