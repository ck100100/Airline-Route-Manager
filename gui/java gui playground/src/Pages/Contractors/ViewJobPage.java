package Pages.Contractors;

import components.*;
import Object.Job;
import Controllers.JobController;
import utils.Colors;

import javax.swing.*;
import java.awt.*;

public class ViewJobPage extends JFrame {
    private Job job;
    private JobController jobController = new JobController();

    public ViewJobPage(String jobName, String jobType, String airport, String date,
                       String description, String cost, String contact) {
        super("View Job");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        // Find the correct job (pass Job object or ID for production apps)
        for (Job j : jobController.getAllJobs()) {
            if (j.name.equals(jobName) && j.date.equals(date) && j.contractor.contact.equals(contact)) {
                job = j;
                break;
            }
        }
        if (job == null) {
            JOptionPane.showMessageDialog(this, "Job not found!");
            dispose();
            return;
        }

        // Main vertical panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60)); // padding

        centerPanel.add(new FormLabel("Contracted Job Details"));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // FormInputs for display (readonly)
        FormInput jobNameInput = new FormInput("Job Name:", false, job.name);
        FormInput jobStatusInput = new FormInput("Job Status:", false, job.status);
        FormInput jobTypeInput = new FormInput("Job Type:", false, job.type);
        FormInput dateInput = new FormInput("Start Date:", false, job.date);
        FormInput descriptionInput = new FormInput("Description:", false, job.description);
        FormInput contractorInput = new FormInput("Contractor:", false, job.contractor.toString());
        FormInput airportInput = new FormInput("Airport:", false, job.airport);
        FormInput costInput = new FormInput("Cost:", false, job.cost + "€");

        centerPanel.add(jobNameInput);
        centerPanel.add(jobStatusInput);
        centerPanel.add(jobTypeInput);
        centerPanel.add(dateInput);
        centerPanel.add(descriptionInput);
        centerPanel.add(contractorInput);
        centerPanel.add(airportInput);
        centerPanel.add(costInput);

        // Buttons
        JButton backButton = ButtonFactory.primary("Back");
        JButton paymentButton = ButtonFactory.primary("Contracted job paid");

        backButton.setBackground(Colors.accent1);           // red for back
        backButton.setForeground(Colors.secondary);         // light gray text

        paymentButton.setBackground(Colors.primary);        // dark gray for submit/payment
        paymentButton.setForeground(Colors.secondary);      // light gray text

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(backButton);
        buttonPanel.add(paymentButton);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(buttonPanel);

        // Attach to JFrame
        this.getContentPane().add(centerPanel);
        this.getContentPane().setBackground(Colors.secondary);

        backButton.addActionListener(e -> {
            dispose();
            //Main.showPageSelectionDialog();
        });

        paymentButton.addActionListener(e -> {
            jobController.updateStatus(job.id, "payment fulfilled");
            JOptionPane.showMessageDialog(this, "Payment marked as submitted for this job!");
            dispose();
            new JobListPage();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewJobPage(
                "Engine Refit", "Maintenance", "JFK", "10th March 2025",
                "Refit engine", "1000", "contractor@gmail.com"));
    }
}
