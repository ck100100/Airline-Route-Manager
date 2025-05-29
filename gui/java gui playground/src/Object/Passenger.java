package Object;

import utils.Exceptions.InvalidInputException;

import java.util.regex.Pattern;

public class Passenger {
    private static int counter = 1;

    private final int PASSENGER_ID;
    private String firstName;
    private String lastName;
    private String email;
    private String passportNumber;
    private String nationality;
    private String phoneNumber;

    public Passenger() {
        this.PASSENGER_ID = counter++;
    }

    public int getPASSENGER_ID() { return PASSENGER_ID; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) throws InvalidInputException {
        if(firstName.length() < 3)
            throw new InvalidInputException("Firstname must have a length of atleast 3");
        this.firstName = firstName;
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) throws InvalidInputException {
        if(lastName.length() < 3)
            throw new InvalidInputException("Lastname must have atleast 3 characters");

        this.lastName = lastName;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) throws InvalidInputException {
        Pattern emailRegrex = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+$");
        if(emailRegrex.matcher(email).matches() == false)
            throw new InvalidInputException("This is not a valid email");
        this.email = email;
    }

    public String getPassportNumber() { return passportNumber; }
    public void setPassportNumber(String passportNumber) throws InvalidInputException {
        if(passportNumber.length() < 3)
            throw new InvalidInputException("Passport number must have atleast 3 characters");
        this.passportNumber = passportNumber;
    }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) throws InvalidInputException {
        if(nationality.length() < 4)
            throw new InvalidInputException("This nationality does not exist!");
        this.nationality = nationality;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) throws InvalidInputException {
        Pattern phoneNumberRegrex = Pattern.compile("^\\+?[0-9]{11,12}$");
        if(phoneNumberRegrex.matcher(phoneNumber).matches() == false)
            throw new InvalidInputException("Invalid phone number");
        this.phoneNumber = phoneNumber; }

    @Override
    public String toString() {
        return PASSENGER_ID + " – " + firstName + " " + lastName;
    }
}
