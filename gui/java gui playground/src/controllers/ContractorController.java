package controllers;


import Object.Contractor;
import java.util.ArrayList;
import java.util.List;

public class ContractorController {
    private static List<Contractor> contractors = new ArrayList<>();

    static {
        contractors.add(new Contractor(1, "Contractor 1", "contractor@gmail.com", true));
        contractors.add(new Contractor(2, "Contractor 2", "contractor2@gmail.com", true));
        contractors.add(new Contractor(3, "Contractor 3", "contractor3@gmail.com", false));
        contractors.add(new Contractor(4, "Contractor 4", "contractor4@gmail.com", true));
        contractors.add(new Contractor(5, "Contractor 5", "contractor5@gmail.com", true));
    }

    public List<Contractor> getAllContractors() {
        return contractors;
    }

    public List<Contractor> getAvailableContractors() {
        List<Contractor> available = new ArrayList<>();
        for (Contractor c : contractors) {
            if (c.available) available.add(c);
        }
        return available;
    }
}
