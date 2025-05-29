package Pages;

import components.*;
import Object.Job;
import Controllers.JobController;
import utils.Colors;
import Object.Contractor;
import Controllers.ContractorController;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class AssignJobPage extends JFrame {
    public AssignJobPage() {

        super("Assign Contracted Job");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        ContractorController contractorController = new ContractorController();
        System.out.println("AssignJobPage loaded!");

        // Main vertical panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60)); // padding

        // Section Label
        centerPanel.add(new FormLabel("Assign New Contracted Job"));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Form fields
        FormInput jobName = new FormInput("Job Name:", true, "");
        FormInput jobType = new FormInput("Job Type:", true, "");
        FormInput airport = new FormInput("Airport:", true, "");
        FormInput date = new FormInput("Date (DD/MM/YYYY):", true, "");
        FormInput description = new FormInput("Description:", true, "");
        FormInput cost = new FormInput("Cost (€):", true, "");

        centerPanel.add(jobName);
        centerPanel.add(jobType);
        centerPanel.add(airport);
        centerPanel.add(date);
        centerPanel.add(description);
        centerPanel.add(cost);

        // Buttons
        JButton cancelButton = ButtonFactory.primary("Cancel");
        JButton submitButton = ButtonFactory.primary("Submit");

        cancelButton.setBackground(Colors.accent1);
        submitButton.setBackground(Colors.primary);
        cancelButton.setForeground(Colors.secondary);
        submitButton.setForeground(Colors.secondary);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(cancelButton);
        buttonPanel.add(submitButton);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(buttonPanel);

        // Attach to JFrame
        this.getContentPane().add(centerPanel);
        this.getContentPane().setBackground(Colors.secondary);

        // Button Actions
        cancelButton.addActionListener(e -> {
            dispose();
            Main.showPageSelectionDialog();
        });

        submitButton.addActionListener(e -> {
            System.out.println("Submit pressed");
            try {
                String jobNameText = jobName.getText();
                String jobTypeText = jobType.getText();
                String airportText = airport.getText();
                String dateText = date.getText();
                String descriptionText = description.getText();
                String costText = cost.getText();

                List<Contractor> availableContractors = contractorController.getAvailableContractors();
                if (availableContractors.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No contractors available!");
                    return;
                }
                Contractor selectedContractor = (Contractor) JOptionPane.showInputDialog(
                        this,
                        "Select a contractor:",
                        "Available Contractors",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        availableContractors.toArray(),
                        availableContractors.get(0)
                );
                if (selectedContractor == null) return;

                // Προσθήκη Job
                JobController jobController = new JobController();
                jobController.addJob(new Job(
                        jobNameText,
                        jobTypeText,
                        dateText,
                        selectedContractor, // πλέον είναι Contractor αντικείμενο
                        "in-progress",
                        airportText,
                        descriptionText,
                        costText
                ));

                JOptionPane.showMessageDialog(this, "Job successfully assigned!");
                dispose();
                new JobListPage();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AssignJobPage::new);
    }
}
