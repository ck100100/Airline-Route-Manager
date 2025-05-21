package Pages.Flights;
import components.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import static javax.swing.BorderFactory.createEmptyBorder;

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
        var pilotIDs = new FormDropdownMultiSelect("Pilot", airplaneList, null);
        pilotIDs.setAlignmentX(Component.LEFT_ALIGNMENT);
        var flightAttendantIDs = new FormDropdownMultiSelect("Flight Attendant", airplaneList, null);
        flightAttendantIDs.setAlignmentX(Component.LEFT_ALIGNMENT);
        var menuID = new FormDropdown("Menu", airplaneList);
        menuID.setAlignmentX(Component.LEFT_ALIGNMENT);

        // add objects to panel
        panel.add(flightNumberInput);
        panel.add(departureDateInput);
        panel.add(arrivalDateInput);
        panel.add(departureAirport);
        panel.add(arrivalAirport);
        panel.add(airplaneID);
        panel.add(pilotIDs);
        panel.add(flightAttendantIDs);
        panel.add(menuID);

        panel.add(renderViewActionButtons());

        // Scroll Functionality
        var scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(createEmptyBorder());
        var wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BorderLayout());
        wrapperPanel.add(scrollPane, BorderLayout.CENTER);

        return wrapperPanel;
    }

    private JPanel renderViewActionButtons() {
        JPanel panel = new JPanel();
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setLayout(new FlowLayout());

        var cancelBtn = ButtonFactory.primary("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSelectCancel();
            }
        });
        var saveBtn = ButtonFactory.primary("Save");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSelectSave();
            }
        });

        panel.add(cancelBtn);
        panel.add(saveBtn);

        return panel;
    }

    private void onSelectCancel() {
        closeWindow();
    }

    private void onSelectSave() {
        // fix this
    }
}
