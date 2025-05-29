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

        panel.add(firstnameInput);
        panel.add(lastnameInput);
        panel.add(emailInput);
        panel.add(passportNumberInput);
        panel.add(nationalityInput);
        panel.add(phoneNumberInput);
        panel.add(errorText);
        panel.add(submitBtn);


        return panel;
    }

    private void onSubmit() {
        Pattern emailRegrex = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]$");
        Pattern phoneNumberRegrex = Pattern.compile("^\\+?[0-9]{11,12}$");

        try {
//            String firstname = firstnameInput.getText();
//            String lastname = lastnameInput.getText();
//            String email = emailInput.getText();
//            String passportNumber = passportNumberInput.getText();
//            String nationality = nationalityInput.getText();
//            String phoneNumber = phoneNumberInput.getText();
//
//            if(firstname.length() < 3)
//                throw new InvalidInputException("Firstname must have atleast 3 characters");
//            else if(lastname.length() < 3)
//                throw new InvalidInputException("Lastname must have atleast 3 characters");
//            else if(passportNumber.length() < 3)
//                throw new InvalidInputException("Passport number must have atleast 3 characters");
//            else if(nationality.length() < 4)
//                throw new InvalidInputException("This nationality does not exist!");
//            else if(phoneNumberRegrex.matcher(phoneNumber).matches() == false)
//                throw new InvalidInputException("Invalid phone number");
//            else if(emailRegrex.matcher(email).matches() == false)
//                throw new InvalidInputException("This is not a valid email");
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
}
