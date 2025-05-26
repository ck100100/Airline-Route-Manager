package Object;
import utils.Status;

public class FlightReport {
    Status status;
    String Description;
    public FlightReport(){
        this.status = Status.normal;
        this.Description = "";
    }
    public void createReport(Status status, String Description){
        this.status = status;
        this.Description = Description;
    }
    public Status getStatus(){
        return status;
    }
    public String getDescription(){
        return Description;
    }
}
