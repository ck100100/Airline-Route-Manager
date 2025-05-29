package Pages.PilotReports;

import components.FormInput;
import components.FormInputLarge;
import components.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PagePlaneReport extends MainWindow {
    public PagePlaneReport() {
        super("Pilot Report");
    }

    @Override
    protected JPanel generateBody() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(generateForm());
        panel.add(generateButtons());

        return panel;
    }

    protected JPanel generateForm() {
        var panel = new JPanel();

        var panelLeft = new JPanel();
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
        var planeInput = new FormInput("Plane", false, "");
        var flightNumInput = new FormInput("Flight Number", false, "");
        var departureAirportInput = new FormInput("Departure Airport", false, "");
        var arrivalAirport = new FormInput("Arrival Airport", false, "");
        var pilotInput = new FormInput("Pilot", false,"");
        var copilotInput = new FormInput("Co-Pilot", false, "");

        panelLeft.add(planeInput);
        panelLeft.add(flightNumInput);
        panelLeft.add(departureAirportInput);
        panelLeft.add(arrivalAirport);
        panelLeft.add(pilotInput);
        panelLeft.add(copilotInput);

        var panelRight = new JPanel();
        var descriptionInput = new FormInputLarge("Description", false, "");
        panelRight.add(descriptionInput);

        panel.add(panelLeft);
        panel.add(panelRight);
        return panel;
    }

    protected JPanel generateButtons() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        var groundBtn = new JButton("Ground");
        var returnBtn = new JButton("Return");
        groundBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPlaneGround();
            }
        });
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onReturn();
            }
        });

        panel.add(groundBtn);
        panel.add(returnBtn);
        return panel;
    }

    public void onPlaneGround() {
        /*
        Called when "ground" button is pressed
         */
        return;
    }

    public void onReturn() {
        closeWindow();
    }
}
