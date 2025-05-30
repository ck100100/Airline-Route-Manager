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
    private ArrayList<Pilot> pilots;
    private ArrayList<FlightAttendant> flightAttendants;
    private ArrayList<FoodMenu> foodMenus;
    private ArrayList<AirportLog> listOfAirports;

    public PageCreateFlight(Airline airline) {
        super("Create Flight");
        this.airline = airline;
    }

    @Override
    protected JPanel generateBody() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        listOfAirports = (ArrayList<AirportLog>) airline.controllerAirport.getAllAirports();
        ArrayList<String> airportNameList = new ArrayList<>();
        for(AirportLog airport : listOfAirports)
            airportNameList.add(airport.getNameAirport());

        ArrayList<AirplaneLog> airplaneList = (ArrayList<AirplaneLog>) airline.controllerAirplane.getAllAirplanes();
        ArrayList<String> airplaneNameList = new ArrayList<>();
        for(AirplaneLog airplane : airplaneList)
            airplaneNameList.add(airplane.getName());

        pilots = (ArrayList<Pilot>)  airline.controllerEmployee.getAllPilots();
        ArrayList<String> pilotNames = new ArrayList<>();
        for(Pilot pilot : pilots)
            pilotNames.add(pilot.getName());

        flightAttendants = (ArrayList<FlightAttendant>) airline.controllerEmployee.getAllFlightAttendants();
        ArrayList<String> flightAttendantNames = new ArrayList<>();
        for(FlightAttendant flightAttendant : flightAttendants)
            flightAttendantNames.add(flightAttendant.getName());

        foodMenus = (ArrayList<FoodMenu>) airline.controllerFoodMenu.getFoodMenus();
        ArrayList<String> foodMenuNameList = new ArrayList<>();
        for (FoodMenu foodMenu: foodMenus)
            foodMenuNameList.add(foodMenu.name);

        // Form Inputs
        flightNumberInput = new FormInput("Flight Number", true, "");
        departureDateInput = new FormDateTimeInput("Departure Time", new DateTime(00, 14, 01, 01, 2025), true);
        arrivalDateInput = new FormDateTimeInput("Arrival Time", new DateTime(00, 14, 01, 01, 2025), true);
        departureAirport = new FormDropdown("Departure Airport", airportNameList, null);
        departureAirport.setAlignmentX(Component.LEFT_ALIGNMENT);
        arrivalAirport = new FormDropdown("Arrival Airport", airportNameList, null);
        arrivalAirport.setAlignmentX(Component.LEFT_ALIGNMENT);
        airplaneInput = new FormDropdown("Airplane", airplaneNameList, null);
        airplaneInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        pilotInput = new FormDropdownMultiSelect("Pilot", pilotNames, null);
        pilotInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        flightAttendantInput = new FormDropdownMultiSelect("Flight Attendant", flightAttendantNames, null);
        flightAttendantInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        menuInput = new FormDropdown("Menu", foodMenuNameList, null);
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

            int departureAirportID = listOfAirports.get(departureAirport.getSelectedIndex() - 1).getAirportID();
            int arrivalAirportID = listOfAirports.get(arrivalAirport.getSelectedIndex() - 1).getAirportID();

            ArrayList<Integer> newFlightAttendantIDList = new ArrayList<>();
            ArrayList<Integer> flightAttendantSelectedIndexes = flightAttendantInput.getSelectedItemIndexes();
            for(Integer index : flightAttendantSelectedIndexes)
                newFlightAttendantIDList.add(flightAttendants.get(index).getFlightAttendantID());

            ArrayList<Integer> newPilotIDList = new ArrayList<>();
            ArrayList<Integer> selectedIndexes = pilotInput.getSelectedItemIndexes();
            for(Integer index : selectedIndexes)
                newPilotIDList.add(pilots.get(index).getPilotID());

            Integer foodMenuSelectedIndex = menuInput.getSelectedIndex();
            Integer foodMenuID;
            if(foodMenuSelectedIndex == 0)
                foodMenuID = null;
            else
                foodMenuID = foodMenus.get(foodMenuSelectedIndex - 1).menuID;

            newFlight.flightNumber = flightNumberInput.getText();
            newFlight.departureTime = departureDateInput.getValue();
            newFlight.arrivalTime = arrivalDateInput.getValue();
            newFlight.departureAirportID = departureAirportID;
            newFlight.arrivalAirportID = arrivalAirportID;
            newFlight.airplaneID = airplaneInput.getSelectedIndex();
            newFlight.pilotListID = newPilotIDList;
            newFlight.flightAttendantIDLlist = newFlightAttendantIDList;
            newFlight.menuID = foodMenuID;
            newFlight.pricePerSeat = pricePerSeat;

            if(newFlight.flightNumber.length() < 5)
                throw new InvalidInputException("Flight Number must be atleast 5 characters long");
            if(newFlight.arrivalTime.isBeforeOrEqual(newFlight.departureTime))
                throw new InvalidInputException("Departure time must be before the arrival time");

            boolean planeAvailable = airline.controllerFlight.isPlaneAvailable(newFlight.departureTime, newFlight.arrivalTime, newFlight.airplaneID);
            if(!planeAvailable)
                throw new InvalidInputException("The plane is not available to be scheduled for this flight!");

            for(Integer pilotID : newPilotIDList) {
                boolean pilotAvailable = airline.controllerFlight.isPilotAvailable(newFlight.departureTime, newFlight.arrivalTime, pilotID);
                if(!pilotAvailable)
                    throw new InvalidInputException("One of the pilots are not available for the flight");
            }

            for(Integer flightAttendantID : newFlightAttendantIDList) {
                boolean isFlightAttendantAvailable = airline.controllerFlight.isFlightAttendantAvailable(newFlight.departureTime, newFlight.arrivalTime, flightAttendantID);
                if (!isFlightAttendantAvailable)
                    throw new InvalidInputException("One of the flight attendants are not available for the flight");
            }



                System.out.println("Flight saved successfully!");
            errorText.clear();
            errorText.repaint();

            airline.controllerFlight.fileFlight(newFlight);

            this.closeWindow();

        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            errorText.setErrorMsg(e.getMessage());
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
