package Object;

public class Job {
    private static int idCounter = 1;
    public int id;
    public String name, type, date, status, airport, description, cost;
    public Contractor contractor;


    public Job(String name, String type, String date, Contractor contractor, String status, String airport, String description, String cost) {
        this.id = idCounter++;
        this.name = name;
        this.type = type;
        this.date = date;
        this.contractor = contractor;
        this.status = status;
        this.airport = airport;
        this.description = description;
        this.cost = cost;
    }
}
