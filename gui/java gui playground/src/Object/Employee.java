package Object;

public class Employee {
    private static int idCounter = 1;

    private final int employeeID;
    private String name;
    private boolean status;
   // private int idNumber;
    private int age;
    private String contactInfo;
    private String job;
    private String extraDuties;
    private float salary;
    private String workingHours;

    public Employee(String name) {
        this.employeeID = idCounter++;
        this.name = name;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    // Getters and Setters for all fields

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

   // public int getIdNumber() {
    //    return idNumber;
   // }

   // public void setIdNumber(int idNumber) {
   //     this.idNumber = idNumber;
    //}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getExtraDuties() {
        return extraDuties;
    }

    public void setExtraDuties(String extraDuties) {
        this.extraDuties = extraDuties;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public String toString() {
        return String.format("ID %d: %s (%s)", employeeID, name, job);
    }
}
