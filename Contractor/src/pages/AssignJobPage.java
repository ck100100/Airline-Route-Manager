package pages;

import javax.swing.*;
import java.awt.*;

import components.FormInput; // Import FormInput
import components.ButtonFactory; // Import ButtonFactory

public class AssignJobPage extends JFrame {
    public AssignJobPage() {
        super("Assign Contracted Job");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);

        add(generateWindow());
        setVisible(true);
    }

    protected JPanel generateWindow() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create form inputs
        FormInput jobName = new FormInput("Job Name", true, "");
        FormInput jobType = new FormInput("Job Type", true, "");
        FormInput airport = new FormInput("Airport", true, "");
        FormInput date = new FormInput("Date (DD/MM/YYYY)", true, "");
        FormInput description = new FormInput("Description", true, "");
        FormInput cost = new FormInput("Cost (€)", true, "");

        // Create buttons using ButtonFactory
        JButton cancelButton = ButtonFactory.primary("Cancel");
        JButton submitButton = ButtonFactory.primary("Submit");

        // Add action listeners
        cancelButton.addActionListener(e -> dispose());
        submitButton.addActionListener(e -> {
            try {
                String message = String.format(
                        "Job Name: %s\nJob Type: %s\nAirport: %s\nDate: %s\nDescription: %s\nCost: %s",
                        jobName.getText(), jobType.getText(), airport.getText(),
                        date.getText(), description.getText(), cost.getText()
                );
                JOptionPane.showMessageDialog(null, message);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // Add components to the panel
        panel.add(jobName);
        panel.add(jobType);
        panel.add(airport);
        panel.add(date);
        panel.add(description);
        panel.add(cost);

        // Add buttons with horizontal spacing
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(cancelButton);
        buttonPanel.add(submitButton);
        panel.add(buttonPanel);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AssignJobPage());
    }
}