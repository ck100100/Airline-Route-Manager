package Pages.Employeelogs;

import Controllers.*;
import Object.EmployeeLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PageEmployeeGUI extends JFrame {
    private ControllerEmployee controller;
    private DefaultListModel<Employee> listModel;
    private JList<Employee> employeeList;

    private JTextField nameField, idNumberField, ageField, contactField, jobField, dutiesField, salaryField, hoursField;
    private JCheckBox statusBox;

    // Pilot-specific
    private JTextField certField, hoursFlownField;

    // FA-specific
    private JTextField languagesField, seniorityField;
    private JCheckBox safetyBox;

    private JPanel detailsPanel;

    public PageEmployeeGUI() {
        setTitle("Employee Logs");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        controller = new ControllerEmployee();
        initComponents();
    }

    private void initComponents() {
        listModel = new DefaultListModel<>();
        for (Employee e : controller.getAllEmployees()) {
            listModel.addElement(e);
        }

        employeeList = new JList<>(listModel);
        employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(employeeList);

        JButton addButton = new JButton("+");
        addButton.addActionListener(e -> showAddDialog());

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel("Employees"), BorderLayout.NORTH);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        leftPanel.add(addButton, BorderLayout.SOUTH);

        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        updateDetailsPanel(null); // no selection

        employeeList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Employee selected = employeeList.getSelectedValue();
                updateDetailsPanel(selected);
            }
        });

        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(detailsPanel, BorderLayout.CENTER);
    }

    private void updateDetailsPanel(Employee employee) {
        detailsPanel.removeAll();

        if (employee == null) {
            detailsPanel.revalidate();
            detailsPanel.repaint();
            return;
        }

        nameField = new JTextField(employee.getName(), 20);
        idNumberField = new JTextField(String.valueOf(employee.getIdNumber()), 20);
        ageField = new JTextField(String.valueOf(employee.getAge()), 20);
        contactField = new JTextField(employee.getContactInfo(), 20);
        jobField = new JTextField(employee.getJob(), 20);
        dutiesField = new JTextField(employee.getExtraDuties(), 20);
        salaryField = new JTextField(String.valueOf(employee.getSalary()), 20);
        hoursField = new JTextField(employee.getWorkingHours(), 20);
        statusBox = new JCheckBox("Active", employee.isStatus());

        detailsPanel.add(new JLabel("Name:"));
        detailsPanel.add(nameField);
        detailsPanel.add(new JLabel("ID Number:"));
        detailsPanel.add(idNumberField);
        detailsPanel.add(new JLabel("Age:"));
        detailsPanel.add(ageField);
        detailsPanel.add(new JLabel("Contact:"));
        detailsPanel.add(contactField);
        detailsPanel.add(new JLabel("Job:"));
        detailsPanel.add(jobField);
        detailsPanel.add(new JLabel("Extra Duties:"));
        detailsPanel.add(dutiesField);
        detailsPanel.add(new JLabel("Salary:"));
        detailsPanel.add(salaryField);
        detailsPanel.add(new JLabel("Working Hours:"));
        detailsPanel.add(hoursField);
        detailsPanel.add(statusBox);

        if (employee instanceof Pilot) {
            Pilot p = (Pilot) employee;
            certField = new JTextField(p.getPlaneCertification(), 20);
            hoursFlownField = new JTextField(String.valueOf(p.getFlightHours()), 20);
            detailsPanel.add(new JLabel("Certification:"));
            detailsPanel.add(certField);
            detailsPanel.add(new JLabel("Flight Hours:"));
            detailsPanel.add(hoursFlownField);
        } else if (employee instanceof FlightAttendant) {
            FlightAttendant f = (FlightAttendant) employee;
            languagesField = new JTextField(f.getLanguagesSpoken(), 20);
            seniorityField = new JTextField(f.getSeniorityLevel(), 20);
            safetyBox = new JCheckBox("Safety Training Completed", f.isSafetyTrainingCompleted());
            detailsPanel.add(new JLabel("Languages:"));
            detailsPanel.add(languagesField);
            detailsPanel.add(new JLabel("Seniority:"));
            detailsPanel.add(seniorityField);
            detailsPanel.add(safetyBox);
        }

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(ev -> {
            employee.setName(nameField.getText());
            employee.setIdNumber(Integer.parseInt(idNumberField.getText()));
            employee.setAge(Integer.parseInt(ageField.getText()));
            employee.setContactInfo(contactField.getText());
            employee.setJob(jobField.getText());
            employee.setExtraDuties(dutiesField.getText());
            employee.setSalary(Float.parseFloat(salaryField.getText()));
            employee.setWorkingHours(hoursField.getText());
            employee.setStatus(statusBox.isSelected());

            if (employee instanceof Pilot) {
                ((Pilot) employee).setPlaneCertification(certField.getText());
                ((Pilot) employee).setFlightHours(Integer.parseInt(hoursFlownField.getText()));
            } else if (employee instanceof FlightAttendant) {
                ((FlightAttendant) employee).setLanguagesSpoken(languagesField.getText());
                ((FlightAttendant) employee).setSeniorityLevel(seniorityField.getText());
                ((FlightAttendant) employee).setSafetyTrainingCompleted(safetyBox.isSelected());
            }

            employeeList.repaint();
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(ev -> {
            controller.removeEmployee(employee);
            listModel.removeElement(employee);
            updateDetailsPanel(null);
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(saveButton);
        btnPanel.add(deleteButton);
        detailsPanel.add(btnPanel);

        detailsPanel.revalidate();
        detailsPanel.repaint();
    }

    private void showAddDialog() {
        String[] types = {"Employee", "Pilot", "Flight Attendant"};
        String selection = (String) JOptionPane.showInputDialog(this, "Select Type:", "Add Employee",
                JOptionPane.PLAIN_MESSAGE, null, types, types[0]);

        if (selection == null) return;

        String name = JOptionPane.showInputDialog(this, "Enter Name:");
        if (name == null || name.isEmpty()) return;

        Employee newEmp = switch (selection) {
            case "Pilot" -> new Pilot(name);
            case "Flight Attendant" -> new FlightAttendant(name);
            default -> new Employee(name);
        };

        controller.addEmployee(newEmp);
        listModel.addElement(newEmp);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PageEmployeeGUI().setVisible(true));
    }
}
