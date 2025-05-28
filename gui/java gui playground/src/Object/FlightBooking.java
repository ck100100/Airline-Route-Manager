import java.time.LocalDate;

public class FlightBooking {
    private static int counter = 1;

    private final int bookingID;
    private int passengerID;
    private int flightID;
    private int numberOfBriefcases;
    private LocalDate purchaseDate;
    private String briefcaseID;
    private String receiptNumber;

    public FlightBooking(int passengerID, int flightID) {
        this.bookingID = counter++;
        this.passengerID = passengerID;
        this.flightID = flightID;
        this.purchaseDate = LocalDate.now();
    }

    public int getBookingID() { return bookingID; }

    public int getPassengerID() { return passengerID; }
    public void setPassengerID(int passengerID) { this.passengerID = passengerID; }

    public int getFlightID() { return flightID; }
    public void setFlightID(int flightID) { this.flightID = flightID; }

    public int getNumberOfBriefcases() { return numberOfBriefcases; }
    public void setNumberOfBriefcases(int numberOfBriefcases) { this.numberOfBriefcases = numberOfBriefcases; }

    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }

    public String getBriefcaseID() { return briefcaseID; }
    public void setBriefcaseID(String briefcaseID) { this.briefcaseID = briefcaseID; }

    public String getReceiptNumber() { return receiptNumber; }
    public void setReceiptNumber(String receiptNumber) { this.receiptNumber = receiptNumber; }

    @Override
    public String toString() {
        return "Booking #" + bookingID + " – Passenger: " + passengerID + ", Flight: " + flightID;
    }
}
