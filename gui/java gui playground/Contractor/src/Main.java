import pages.AssignJobPage;
import pages.JobListPage;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

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
                new AssignJobPage();
            } else {
                new JobListPage();
            }

        });
    }
}