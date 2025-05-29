package Object;

import java.time.LocalDate;

public class FlightBooking {
    private static int counter = 1;

    private final int bookingID;
    private Passenger passenger;
    private int flightID;
    private int numberOfBriefcases;
    private LocalDate purchaseDate;

    public FlightBooking(Passenger passenger, int flightID) {
        this.bookingID = counter++;
        this.passenger = passenger;
        this.flightID = flightID;
        this.purchaseDate = LocalDate.now();
    }

    public int getBookingID() { return bookingID; }

    public Passenger getPassenger() { return passenger; }
    public void setPassenger(Passenger passenger) { this.passenger = passenger; }

    public int getFlightID() { return flightID; }
    public void setFlightID(int flightID) { this.flightID = flightID; }

    public int getNumberOfBriefcases() { return numberOfBriefcases; }
    public void setNumberOfBriefcases(int numberOfBriefcases) { this.numberOfBriefcases = numberOfBriefcases; }

    public LocalDate getPurchaseDate() { return purchaseDate; }

    @Override
    public String toString() {
        return "Booking #" + bookingID + " – Passenger: " + passenger + ", Flight: " + flightID;
    }
}
