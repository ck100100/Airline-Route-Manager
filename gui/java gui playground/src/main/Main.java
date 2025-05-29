package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> showPageSelectionDialog());
    }

    public static void showPageSelectionDialog() {
        int choice = JOptionPane.showOptionDialog(
                null,
                "Select a page to open:",
                "Page Selection",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Assign Job", "Job List"},
                "Assign Job"
        );
        if (choice == 0) {
            new pages.AssignJobPage();
        } else {
            new pages.JobListPage();
        }
    }
}