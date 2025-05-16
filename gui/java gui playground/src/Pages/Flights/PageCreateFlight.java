package Pages.Flights;
import components.FormDateTimeInput;
import components.FormInput;
import components.FormLabel;
import components.MainWindow;

import javax.swing.*;
import java.awt.*;

public class PageCreateFlight extends MainWindow {
    public PageCreateFlight() {
        super("Create Flight");
    }

    @Override
    protected JPanel generateBody() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        FormInput flightNumberInput = new FormInput("Flight Number", true, "");
        var departureDateInput = new FormDateTimeInput("Departure Time", true, 00, 00, 01, 01, 25);
        var arrivalDateInput = new FormDateTimeInput("Arrival Time", true, 00, 00, 01, 01, 25);

        panel.add(flightNumberInput);
        panel.add(departureDateInput);
        panel.add(arrivalDateInput);
        

        return panel;
    }
}
