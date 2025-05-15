package pages;

import javax.swing.*;
import java.awt.*;

import components.FormInput;
import components.ButtonFactory;

public class ViewJobPage extends JFrame {
    // Sample job data (to be replaced with data passed from JobListPage)
    private String jobName = "Engine Refit";
    private String jobType = "Maintenance";
    private String airport = "JFK Airport";
    private String date = "10/01/25"; // Matching the mockup format
    private String description = "Refit the engine for flight safety.";
    private String cost = "10 €"; // Matching the mockup value
    private String contactDetails = "contractor@gmail.com";
    private String jobStatus = "awaiting payment"; // Matching the mockup status

    public ViewJobPage() {
        super("View Job");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Header with back arrow
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 240, 240));
        JButton backButton = new JButton("<");
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        backButton.addActionListener(e -> {
            dispose();
            new JobListPage();
        });
        JLabel titleLabel = new JLabel("View Job");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(backButton, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Job details panel using FormInput
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create FormInput instances for read-only display
        FormInput jobNameInput = new FormInput("Job Name:", false, jobName);
        FormInput jobTypeInput = new FormInput("Job Type:", false, jobType);
        FormInput airportInput = new FormInput("Airport:", false, airport);
        FormInput dateInput = new FormInput("Date:", false, date);
        FormInput descriptionInput = new FormInput("Description:", false, description);
        FormInput costInput = new FormInput("Cost:", false, cost);
        FormInput contactInput = new FormInput("Contact Details:", false, contactDetails);
        FormInput statusInput = new FormInput("Job Status:", false, jobStatus);

        // Disable editing for all fields
        jobNameInput.setActive(false);
        jobTypeInput.setActive(false);
        airportInput.setActive(false);
        dateInput.setActive(false);
        descriptionInput.setActive(false);
        costInput.setActive(false);
        contactInput.setActive(false);
        statusInput.setActive(false);

        // Add fields to panel
        detailsPanel.add(jobNameInput);
        detailsPanel.add(jobTypeInput);
        detailsPanel.add(airportInput);
        detailsPanel.add(dateInput);
        detailsPanel.add(descriptionInput);
        detailsPanel.add(costInput);
        detailsPanel.add(contactInput);
        detailsPanel.add(statusInput);

        mainPanel.add(new JScrollPane(detailsPanel), BorderLayout.CENTER);

        // Action buttons using ButtonFactory
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton approveButton = ButtonFactory.primary("Approve and Submit Payment");
        approveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Payment approved and submitted for this job!");
            jobStatus = "awaiting payment"; // No change needed as it's already matching
            detailsPanel.removeAll();
            detailsPanel.add(new FormInput("Job Name:", false, jobName));
            detailsPanel.add(new FormInput("Job Type:", false, jobType));
            detailsPanel.add(new FormInput("Airport:", false, airport));
            detailsPanel.add(new FormInput("Date:", false, date));
            detailsPanel.add(new FormInput("Description:", false, description));
            detailsPanel.add(new FormInput("Cost:", false, cost));
            detailsPanel.add(new FormInput("Contact Details:", false, contactDetails));
            detailsPanel.add(new FormInput("Job Status:", false, jobStatus));
            detailsPanel.revalidate();
            detailsPanel.repaint();
        });

        buttonPanel.add(approveButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewJobPage());
    }
}