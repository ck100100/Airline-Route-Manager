package Pages.FlightBooking;

import components.ErrorText;
import components.FormInput;
import components.MainWindow;
import Object.*;
import utils.Exceptions.InvalidInputException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class PagePassengerDetails extends MainWindow {
    private Flight flight;
    private FormInput firstnameInput;
    private FormInput lastnameInput;
    private FormInput emailInput;
    private FormInput passportNumberInput;
    private FormInput nationalityInput;
    private FormInput phoneNumberInput;
    private ErrorText errorText;

    public PagePassengerDetails(Flight flight) {
        super("Passenger Details");
        this.flight = flight;
    }

    @Override
    protected JPanel generateBody() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        firstnameInput = new FormInput("Firstname", true, null);
        lastnameInput = new FormInput("Lastname", true, null);
        emailInput = new FormInput("Email", true, null);
        passportNumberInput = new FormInput("Passport Number", true, null);
        nationalityInput = new FormInput("Nationality", true, null);
        phoneNumberInput = new FormInput("Phone Number", true, null);
        errorText = new ErrorText();
        var submitBtn = new JButton("Submit");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSubmit();
            }
        });
        var cancelBtn = new JButton("Cancel Button");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        panel.add(firstnameInput);
        panel.add(lastnameInput);
        panel.add(emailInput);
        panel.add(passportNumberInput);
        panel.add(nationalityInput);
        panel.add(phoneNumberInput);
        panel.add(errorText);
        panel.add(submitBtn);
        panel.add(cancelBtn);


        return panel;
    }

    private void onSubmit() {
        try {
            Passenger passenger = new Passenger();
            passenger.setFirstName(firstnameInput.getText());
            passenger.setLastName(lastnameInput.getText());
            passenger.setEmail(emailInput.getText());
            passenger.setPassportNumber(passportNumberInput.getText());
            passenger.setNationality(nationalityInput.getText());
            passenger.setPhoneNumber(phoneNumberInput.getText());

            FlightBooking booking = new FlightBooking(
                    passenger,
                    flight.flightID
            );

            errorText.setErrorMsg("");
            flight.addFlightBooking(booking);
            closeWindow();


        } catch (InvalidInputException e) {
            errorText.setErrorMsg(e.getMessage());
        }

    }

    private void onCancel() {
        closeWindow();
    }
}
