package Controllers;

import Object.*;
import java.util.ArrayList;
import java.util.List;

public class ControllerEmployee {
    private final List<Employee> employees;

    public ControllerEmployee() {
        employees = new ArrayList<>();
        generateMockData();
    }
    public List<Pilot> getAllPilots() {
        List<Pilot> pilots = new ArrayList<>();
        for (Employee e : employees) {
            if (e instanceof Pilot) {
                pilots.add((Pilot) e);
            }
        }
        return pilots;
    }

    public List<FlightAttendant> getAllFlightAttendants() {
        List<FlightAttendant> fas = new ArrayList<>();
        for (Employee e : employees) {
            if (e instanceof FlightAttendant) {
                fas.add((FlightAttendant) e);
            }
        }
        return fas;
    }

    private Employee makeEmployee(String name, boolean status, int idNumber, int age,
                                  String contactInfo, String job, String extraDuties,
                                  float salary, String workingHours) {
        Employee e = new Employee(name);
        e.setStatus(status);
     //   e.setIdNumber(idNumber);
        e.setAge(age);
        e.setContactInfo(contactInfo);
        e.setJob(job);
        e.setExtraDuties(extraDuties);
        e.setSalary(salary);
        e.setWorkingHours(workingHours);
        return e;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Pilot getPilotByID(int pilotID) {
        for (Employee e : employees) {
            if (e instanceof Pilot) {
                Pilot p = (Pilot) e;
                if (p.getPilotID() == pilotID) {
                    return p;
                }
            }
        }
        return null;
    }

    public FlightAttendant getFlightAttendantByID(int flightAttendantID) {
        for (Employee e : employees) {
            if (e instanceof FlightAttendant) {
                FlightAttendant f = (FlightAttendant) e;
                if (f.getFlightAttendantID() == flightAttendantID) {
                    return f;
                }
            }
        }
        return null;
    }


    private void generateMockData() {
        Pilot p1 = new Pilot("Captain Mike");
        p1.setStatus(true);
        p1.setPlaneCertification("Boeing 737");
        p1.setFlightHours(4500);
        p1.setJob("Pilot");
        p1.setSalary(120000.0f);
        p1.setWorkingHours("6am-2pm");
        employees.add(p1);

        FlightAttendant f1 = new FlightAttendant("Linda Grey");
        f1.setStatus(true);
        f1.setLanguagesSpoken("English, Spanish");
        f1.setSeniorityLevel("Senior");
        f1.setSafetyTrainingCompleted(true);
        f1.setJob("Flight Attendant");
        f1.setSalary(50000.0f);
        f1.setWorkingHours("7am-3pm");
        employees.add(f1);
    }

}

