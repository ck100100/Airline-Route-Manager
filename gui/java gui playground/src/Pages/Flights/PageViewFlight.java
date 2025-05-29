package Pages.Flights;

import components.*;
import Object.*;
import Controllers.*;
import utils.DateTime;
import utils.Exceptions.InvalidInputException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import static javax.swing.BorderFactory.createEmptyBorder;

public class PageViewFlight extends MainWindow {
    private Airline airline;
    private Flight flight;
    boolean isEditing = true;
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
    private ArrayList<AirportLog> listOfAirports;
    private ArrayList<FlightAttendant> flightAttendants;
    private ArrayList<Pilot> pilots = new ArrayList<>();
    private ArrayList<FoodMenu> foodMenus = new ArrayList<>();

    public PageViewFlight(Airline airline, Flight flight) {
        super("View Flight");
        this.airline = airline;
        this.flight = flight;
    }

    @Override
    protected JPanel generateBody() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Dropdown options
        listOfAirports = (ArrayList<AirportLog>) airline.controllerAirport.getAllAirports();
        ArrayList<String> airportNameList = new ArrayList<>();
        for(AirportLog airport : listOfAirports)
            airportNameList.add(airport.getNameAirport());
        String departureAirportName = airline.controllerAirport.getAirportByID(flight.departureAirportID).getNameAirport();
        String arrivalAirportName = airline.controllerAirport.getAirportByID(flight.arrivalAirportID).getNameAirport();

        ArrayList<AirplaneLog> airplaneList = (ArrayList<AirplaneLog>) airline.controllerAirplane.getAllAirplanes();
        ArrayList<String> airplaneNameList = new ArrayList<>();
        for(AirplaneLog airplane : airplaneList)
            airplaneNameList.add(airplane.getName());

        pilots = (ArrayList<Pilot>)  airline.controllerEmployee.getAllPilots();
        ArrayList<String> pilotNames = new ArrayList<>();
        for(Pilot pilot : pilots)
            pilotNames.add(pilot.getName());

        String[] selectedPilotNames = new String[flight.pilotListID.size()];
        for(int i=0 ; i<selectedPilotNames.length; i++) {
            Pilot pilot = airline.controllerEmployee.getPilotByID(flight.pilotListID.get(i));
            selectedPilotNames[i] = pilot.getName();
        }

        flightAttendants = (ArrayList<FlightAttendant>) airline.controllerEmployee.getAllFlightAttendants();
        ArrayList<String> flightAttendantNames = new ArrayList<>();
        for(FlightAttendant flightAttendant : flightAttendants)
            flightAttendantNames.add(flightAttendant.getName());

        String[] selectedFlightAttendantNames = new String[flight.flightAttendantIDLlist.size()];
        for(int i=0; i < flight.flightAttendantIDLlist.size(); i++) {
            Integer flightAttendantID = flight.flightAttendantIDLlist.get(i);
            Employee employee = airline.controllerEmployee.getFlightAttendantByID(flightAttendantID);
            selectedFlightAttendantNames[i] = employee.getName();
        }

        foodMenus = (ArrayList<FoodMenu>) airline.controllerFoodMenu.getFoodMenus();
        ArrayList<String> foodMenuNameList = new ArrayList<>();
        for (FoodMenu foodMenu: foodMenus)
            foodMenuNameList.add(foodMenu.name);
        String defaultFoodMenu;
        if(flight.menuID != null)
            defaultFoodMenu = airline.controllerFoodMenu.getMenuByID(flight.menuID).name;
        else
            defaultFoodMenu = null;


        String airplaneName = airline.controllerAirplane.getAirplaneByID(flight.airplaneID).getName();



        // Form Inputs
        flightNumberInput = new FormInput("Flight Number", isEditing, flight.flightNumber);
        departureDateInput = new FormDateTimeInput("Departure Time", flight.departureTime, isEditing);
        arrivalDateInput = new FormDateTimeInput("Arrival Time", flight.arrivalTime, isEditing);
        departureAirport = new FormDropdown("Departure Airport", airportNameList, departureAirportName);
        departureAirport.setAlignmentX(Component.LEFT_ALIGNMENT);
        arrivalAirport = new FormDropdown("Arrival Airport", airportNameList, arrivalAirportName);
        arrivalAirport.setAlignmentX(Component.LEFT_ALIGNMENT);
        airplaneInput = new FormDropdown("Airplane", airplaneNameList, airplaneName);
        airplaneInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        pilotInput = new FormDropdownMultiSelect("Pilot", pilotNames, selectedPilotNames);
        pilotInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        flightAttendantInput = new FormDropdownMultiSelect("Flight Attendant", flightAttendantNames, selectedFlightAttendantNames);
        flightAttendantInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        menuInput = new FormDropdown("Menu", foodMenuNameList, defaultFoodMenu);
        menuInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        seatPriceInput = new FormInputNumerical("Price Per Seat", true, flight.pricePerSeat);
        errorText = new ErrorText();

        departureAirport.setActive(false);
        arrivalAirport.setActive(false);

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

        var exitBtn = ButtonFactory.primary("Exit");
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSelectExit();
            }
        });
        var cancelFlightBtn = ButtonFactory.primary("Cancel");
        cancelFlightBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectsCancelFlight();
            }
        });

        var saveBtn = ButtonFactory.primary("Edit");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSaveChanges();
            }
        });

        panel.add(exitBtn);
        panel.add(cancelFlightBtn);
        panel.add(saveBtn);

        return panel;
    }

    private void onSelectExit() {
        closeWindow();
    }

    private void onSaveChanges() {
        try {
            Double pricePerSeat = seatPriceInput.getValue();
            if(pricePerSeat <= 0.0)
                throw new InvalidInputException("Seat price must be greater than zero");


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

            flight.flightNumber = flightNumberInput.getText();
            flight.departureTime = departureDateInput.getValue();
            flight.arrivalTime = arrivalDateInput.getValue();
            flight.airplaneID = airplaneInput.getSelectedIndex();
            flight.pilotListID = newPilotIDList;
            flight.flightAttendantIDLlist = newFlightAttendantIDList;
            flight.menuID = foodMenuID;
            flight.pricePerSeat = pricePerSeat;

            if(flight.flightNumber.length() < 5)
                throw new InvalidInputException("Flight Number must be atleast 5 characters long");
            if(flight.arrivalTime.isBeforeOrEqual(flight.departureTime))
                throw new InvalidInputException("Departure time must be before the arrival time");

            System.out.println("Flight saved successfully!");
            errorText.clear();
            errorText.repaint();

            this.closeWindow();

        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            errorText.setErrorMsg(e.getMessage());
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    private void selectsCancelFlight() {
        closeWindow();
        airline.controllerFlight.cancelFlightByID(flight.flightID);
        System.out.println("Flight deleted!");
    }
}
