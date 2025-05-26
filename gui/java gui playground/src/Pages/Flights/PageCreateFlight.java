package Pages.Flights;
import Controllers.Airline;
import Controllers.ControllerFoodMenu;
import components.*;
import Object.*;
import utils.DateTime;
import utils.Exceptions.InvalidInputException;

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
    private Airline airline;
    private FormInput flightNumberInput;
    private FormDateTimeInput departureDateInput;
    private FormDateTimeInput arrivalDateInput;
    private FormDropdown departureAirport;
    private FormDropdown arrivalAirport;
    private FormDropdown airplaneInput;
    private FormDropdownMultiSelect pilotInput;
    private FormDropdownMultiSelect flightAttendantInput;
    private FormDropdown menuInput;
    private FormInputNumerical seatPriceInput;
    private ErrorText errorText;
    public PageCreateFlight(Airline airline) {
        super("Create Flight");
        this.airline = airline;
    }

    @Override
    protected JPanel generateBody() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Dropdown options
        String[] testList = {"opt1", "opt2", "opt3"};
        String[] airplaneTestList = {"ASDF", "A30", "B550"};
        ArrayList<String> listOfAirports = new ArrayList<>(Arrays.asList(testList));
        ArrayList<String> airplaneListTemp = new ArrayList<>(Arrays.asList(airplaneTestList));

        ArrayList<AirplaneLog> airplaneList = (ArrayList<AirplaneLog>) airline.controllerAirplane.getAllAirplanes();
        ArrayList<String> airplaneNameList = new ArrayList<>();
        for(AirplaneLog airplane : airplaneList)
            airplaneNameList.add(airplane.getName());

//        ArrayList<FoodMenu> foodMenuList = airline.controllerFoodMenu.getAllFoodMenus();
//        ArrayList<String> foodMenuNameList = new ArrayList<>();
//        for (FoodMenu foodMenu: foodMenuList) {
//            foodMenuNameList.add(foodMenu.name)
//        }

        // Form Inputs
        flightNumberInput = new FormInput("Flight Number", true, "");
        departureDateInput = new FormDateTimeInput("Departure Time", new DateTime(00, 14, 01, 01, 2025), true);
        arrivalDateInput = new FormDateTimeInput("Arrival Time", new DateTime(00, 14, 01, 01, 2025), true);
        departureAirport = new FormDropdown("Departure Airport", listOfAirports);
        departureAirport.setAlignmentX(Component.LEFT_ALIGNMENT);
        arrivalAirport = new FormDropdown("Arrival Airport", listOfAirports);
        arrivalAirport.setAlignmentX(Component.LEFT_ALIGNMENT);
        airplaneInput = new FormDropdown("Airplane", airplaneNameList);
        airplaneInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        pilotInput = new FormDropdownMultiSelect("Pilot", airplaneNameList, null);
        pilotInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        flightAttendantInput = new FormDropdownMultiSelect("Flight Attendant", airplaneNameList, null);
        flightAttendantInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        menuInput = new FormDropdown("Menu", foodMenuList);
        menuInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        seatPriceInput = new FormInputNumerical("Price Per Seat", true, 0.0);
        errorText = new ErrorText();

        // add objects to panel
        panel.add(flightNumberInput);
        panel.add(departureDateInput);
        panel.add(arrivalDateInput);
        panel.add(departureAirport);
        panel.add(arrivalAirport);
        panel.add(airplaneInput);
        panel.add(pilotInput);
        panel.add(flightAttendantInput);
        panel.add(menuInput);
        panel.add(seatPriceInput);
        panel.add(errorText);

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
        Flight newFlight = new Flight();
        try {
            Double pricePerSeat = seatPriceInput.getValue();
            if(pricePerSeat <= 0.0)
                throw new InvalidInputException("Seat price must be greater than zero");

            Integer[] mockList = {1};
            ArrayList<Integer> mockPilotList = new ArrayList<>(Arrays.asList(mockList));
            ArrayList<Integer> mockFlightAttendantList = new ArrayList<>(Arrays.asList(mockList));

            newFlight.flightNumber = flightNumberInput.getText();
            newFlight.departureTime = departureDateInput.getValue();
            newFlight.arrivalTime = arrivalDateInput.getValue();
            newFlight.departureAirportID = 1; // change this
            newFlight.arrivalAirportID = 2; // change this
            newFlight.airplaneID = airplaneInput.getSelectedIndex();
            newFlight.pilotListID = mockPilotList; // change this
            newFlight.flightAttendantIDLlist = mockFlightAttendantList; // change this
            newFlight.menuID = 1; // change this
            newFlight.pricePerSeat = pricePerSeat;

            System.out.println("Flight saved successfully!");
            errorText.clear();

            this.closeWindow();

        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            errorText.setErrorMsg(e.getMessage());
        }
    }
}
