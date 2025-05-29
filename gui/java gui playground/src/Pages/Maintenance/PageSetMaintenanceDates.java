package Pages.Maintenance;

import Controllers.Airline;
import components.FormDateTimeInput;
import components.MainWindow;
import utils.AirplaneStatus;
import utils.DateTime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Object.AirplaneLog;
import Object.Flight;
import utils.Exceptions.InvalidInputException;
import utils.Status;

public class PageSetMaintenanceDates extends MainWindow {
    private FormDateTimeInput startMaintenanceDateInput;
    private FormDateTimeInput endMaintenanceDateInput;
    private AirplaneLog plane;
    private Airline airline;
    private List<Flight> planeFlights;
    public PageSetMaintenanceDates(AirplaneLog plane, Airline airline){

        super("Set Maintenance Dates");
        this.airline = airline;
        this.plane = plane;
    }

    @Override
    protected JPanel generateBody(){
       var panel = new JPanel();
       panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
       startMaintenanceDateInput = new FormDateTimeInput("Start Maintenance Date", new DateTime(00,00,01,01,2025),true);
       endMaintenanceDateInput = new FormDateTimeInput("End Maintenance Date", new DateTime(00,00,01,01,2025),true);
       panel.add(startMaintenanceDateInput);
       panel.add(endMaintenanceDateInput);
       panel.add(generateButtons());

       return panel;
    }
    protected JPanel generateButtons(){
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        var setMaintenanceBtn = new JButton("Set Maintenance Slot");
        var cancelBtn = new JButton("Cancel");
        setMaintenanceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setMaintenance();
                } catch (InvalidInputException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        panel.add(setMaintenanceBtn);
        panel.add(cancelBtn);
        return panel;
    }

    public void onCancel(){
        closeWindow();
    }
    public void setMaintenance() throws InvalidInputException {
        plane.setStatus(AirplaneStatus.maintenanceApproved);
        plane.setFinishMaintenanceDate(endMaintenanceDateInput.getValue());
        planeFlights = airline.controllerFlight.getFlightsAfterDate(endMaintenanceDateInput.getValue(),plane);
        for(Flight flight : planeFlights){
            if(flight.status == Status.cancelled){
                flight.status = Status.approved;
            }
        }
        closeWindow();
        JOptionPane.showMessageDialog(null,"Airplane Maintenance Dates set successfully");
    }
}
