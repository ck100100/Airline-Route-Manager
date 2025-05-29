public class Passenger {
    private static int counter = 1;

    private final int passengerID;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private int passportNumber;
    private String nationality;
    private String phoneNumber;

    public Passenger(String firstName, String lastName) {
        this.passengerID = counter++;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getPassengerID() { return passengerID; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getPassportNumber() { return passportNumber; }
    public void setPassportNumber(int passportNumber) { this.passportNumber = passportNumber; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String toString() {
        return passengerID + " – " + firstName + " " + lastName;
    }
}
