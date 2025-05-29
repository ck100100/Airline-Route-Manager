package Controllers;

import Object.Job;
import Object.Contractor;
import java.util.ArrayList;
import java.util.List;

public class JobController {
    // Placeholder data - static list shared by all pages/sessions
    private static List<Job> jobs = new ArrayList<>();

    static {
        // Δημιουργία demo Contractors
        Contractor contractor1 = new Contractor(1, "Contractor 1", "contractor@gmail.com", true);
        Contractor contractor2 = new Contractor(2, "Contractor 2", "contractor2@gmail.com", true);
        Contractor contractor3 = new Contractor(3, "Contractor 3", "contractor3@gmail.com", false);
        Contractor contractor4 = new Contractor(4, "Contractor 4", "contractor4@gmail.com", true);
        Contractor contractor5 = new Contractor(5, "Contractor 5", "contractor5@gmail.com", true);

        // Δημιουργία demo Jobs με αντικείμενα Contractor
        jobs.add(new Job("Engine Refit", "Maintenance", "10th March 2025", contractor1, "in-progress", "JFK", "Refit engine", "1000"));
        jobs.add(new Job("Cabin Cleaning", "Cleaning", "12th March 2025", contractor2, "awaiting payment", "LHR", "Clean passenger cabin", "600"));
        jobs.add(new Job("Electrical Check", "Inspection", "15th March 2025", contractor3, "completed", "CDG", "Check electrical systems", "500"));
        jobs.add(new Job("Landing Gear Overhaul", "Maintenance", "20th March 2025", contractor4, "in-progress", "FRA", "Complete overhaul of landing gear.", "2000"));
        jobs.add(new Job("Avionics Upgrade", "Upgrade", "25th March 2025", contractor5, "in-progress", "MAD", "Install new avionics suite.", "4000"));
    }

    public List<Job> getAllJobs() { return jobs; }

    public void addJob(Job job) { jobs.add(job); }

    public void updateStatus(int jobId, String newStatus) {
        for (Job job : jobs) {
            if (job.id == jobId) { job.status = newStatus; }
        }
    }
}
