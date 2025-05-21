package Pages.Flights;
import components.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class PageCreateFlight extends MainWindow {
    public PageCreateFlight() {
        super("Create Flight");
    }

    @Override
    protected JPanel generateBody() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Dropdown options
        String[] testList = {"opt1", "opt2", "opt3"};
        String[] airplaneTestList = {"ASDF", "A30", "B550"};
        ArrayList<String> listOfAirports = new ArrayList<>(Arrays.asList(testList));
        ArrayList<String> airplaneList = new ArrayList<>(Arrays.asList(airplaneTestList));

        // Form Inputs
        FormInput flightNumberInput = new FormInput("Flight Number", true, "");
        var departureDateInput = new FormDateTimeInput("Departure Time", true, 00, 00, 01, 01, 25);
        var arrivalDateInput = new FormDateTimeInput("Arrival Time", true, 00, 00, 01, 01, 25);
        var departureAirport = new FormDropdown("Departure Airport", listOfAirports);
        departureAirport.setAlignmentX(Component.LEFT_ALIGNMENT);
        var arrivalAirport = new FormDropdown("Arrival Airport", listOfAirports);
        arrivalAirport.setAlignmentX(Component.LEFT_ALIGNMENT);
        var airplaneID = new FormDropdown("Airplane", airplaneList);
        airplaneID.setAlignmentX(Component.LEFT_ALIGNMENT);
        var pilotID = new FormDropdown("Pilot", airplaneList);
        pilotID.setAlignmentX(Component.LEFT_ALIGNMENT);
        var flightAttendantID = new FormDropdown("Flight Attendant", airplaneList);
        flightAttendantID.setAlignmentX(Component.LEFT_ALIGNMENT);
        var menuID = new FormDropdown("Menu", airplaneList);
        menuID.setAlignmentX(Component.LEFT_ALIGNMENT);

        // add objects to panel
        panel.add(flightNumberInput);
        panel.add(departureDateInput);
        panel.add(arrivalDateInput);
        panel.add(departureAirport);
        panel.add(arrivalAirport);
        panel.add(airplaneID);
        panel.add(pilotID);
        panel.add(flightAttendantID);
        panel.add(menuID);

        panel.add(renderViewActionButtons());

        // Scroll Functionality
        var scrollPane = new JScrollPane(panel);
        var wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BorderLayout());
        wrapperPanel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel renderViewActionButtons() {
        JPanel panel = new JPanel();
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setLayout(new FlowLayout());

        var editBtn = ButtonFactory.primary("Edit");
        var rescheduleBtn = ButtonFactory.primary("Reschedule");
        var cancelFlightBtn = ButtonFactory.primary("Cancel Flight");
        var cancelBtn = ButtonFactory.primary("Cancel");

        panel.add(editBtn);
        panel.add(rescheduleBtn);
//        panel.add(cancelFlightBtn);
        panel.add(cancelBtn);

        return panel;
    }
}
