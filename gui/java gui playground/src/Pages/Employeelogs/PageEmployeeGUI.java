package Pages.Employeelogs;

import Controllers.*;
import Object.EmployeeLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PageEmployeeGUI extends JFrame {
    private JComboBox<String> filterComboBox;
    private JTextField nameFilterField, ageFilterField;

    private ControllerEmployee controller;
    private DefaultListModel<Employee> listModel;
    private JList<Employee> employeeList;

    private JTextField nameField, ageField, contactField, dutiesField, salaryField, hoursField;
    private JCheckBox statusBox;
    private JTextField certField, hoursFlownField;
    private JTextField languagesField, seniorityField;
    private JCheckBox safetyBox;

    public PageEmployeeGUI() {
        setTitle("Employee Logs");
        setSize(800, 500);  // Thinner window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        controller = new ControllerEmployee();
        initComponents();
    }

    private void initComponents() {
        filterComboBox = new JComboBox<>(new String[]{"All", "Pilots", "Flight Attendants"});
        nameFilterField = new JTextField();
        ageFilterField = new JTextField();

        JPanel filterPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        filterPanel.add(new JLabel("Filter By Type:"));
        filterPanel.add(filterComboBox);
        filterPanel.add(new JLabel("Name:"));
        filterPanel.add(nameFilterField);
        filterPanel.add(new JLabel("Age:"));
        filterPanel.add(ageFilterField);

        ActionListener filterListener = e -> updateListBasedOnFilter();
        filterComboBox.addActionListener(filterListener);
        nameFilterField.addActionListener(filterListener);
        ageFilterField.addActionListener(filterListener);

        listModel = new DefaultListModel<>();
        employeeList = new JList<>(listModel);
        employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(employeeList);

        JButton addButton = new JButton("+");
        addButton.addActionListener(e -> showAddDialog());

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel("Employees"), BorderLayout.NORTH);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        leftPanel.add(addButton, BorderLayout.SOUTH);

        employeeList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Employee selected = employeeList.getSelectedValue();
                if (selected != null) showEmployeePopup(selected);
            }
        });

        setLayout(new BorderLayout());
        add(filterPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.CENTER);

        updateListBasedOnFilter();
    }

    private void updateListBasedOnFilter() {
        listModel.clear();

        String type = (String) filterComboBox.getSelectedItem();
        String nameFilter = nameFilterField.getText().trim().toLowerCase();
        String ageFilter = ageFilterField.getText().trim();

        List<Employee> employees;
        if ("Pilots".equals(type)) {
            employees = new ArrayList<>(controller.getAllPilots());
        } else if ("Flight Attendants".equals(type)) {
            employees = new ArrayList<>(controller.getAllFlightAttendants());
        } else {
            employees = new ArrayList<>(controller.getAllEmployees());
        }

        for (Employee e : employees) {
            boolean matches = true;

            if (!nameFilter.isEmpty() && !e.getName().toLowerCase().contains(nameFilter)) matches = false;

            if (!ageFilter.isEmpty()) {
                try {
                    int age = Integer.parseInt(ageFilter);
                    if (e.getAge() != age) matches = false;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Age filter must be a number", "Invalid Filter", JOptionPane.WARNING_MESSAGE);
                    break;
                }
            }

            if (matches) listModel.addElement(e);
        }
    }

    private void showEmployeePopup(Employee employee) {
        JDialog dialog = new JDialog(this, "Employee Details", true);
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        nameField = new JTextField(employee.getName(), 20);
        ageField = new JTextField(String.valueOf(employee.getAge()), 20);
        contactField = new JTextField(employee.getContactInfo(), 20);
        dutiesField = new JTextField(employee.getExtraDuties(), 20);
        salaryField = new JTextField(String.valueOf(employee.getSalary()), 20);
        hoursField = new JTextField(employee.getWorkingHours(), 20);
        statusBox = new JCheckBox("Active", employee.isStatus());

        panel.add(new JLabel("Name:")); panel.add(nameField);
        panel.add(new JLabel("Age:")); panel.add(ageField);
        panel.add(new JLabel("Contact:")); panel.add(contactField);
        panel.add(new JLabel("Extra Duties:")); panel.add(dutiesField);
        panel.add(new JLabel("Salary:")); panel.add(salaryField);
        panel.add(new JLabel("Working Hours:")); panel.add(hoursField);
        panel.add(statusBox);

        if (employee instanceof Pilot) {
            Pilot p = (Pilot) employee;
            certField = new JTextField(p.getPlaneCertification(), 20);
            hoursFlownField = new JTextField(String.valueOf(p.getFlightHours()), 20);
            panel.add(new JLabel("Certification:")); panel.add(certField);
            panel.add(new JLabel("Flight Hours:")); panel.add(hoursFlownField);
        } else if (employee instanceof FlightAttendant) {
            FlightAttendant f = (FlightAttendant) employee;
            languagesField = new JTextField(f.getLanguagesSpoken(), 20);
            seniorityField = new JTextField(f.getSeniorityLevel(), 20);
            safetyBox = new JCheckBox("Safety Training Completed", f.isSafetyTrainingCompleted());
            panel.add(new JLabel("Languages:")); panel.add(languagesField);
            panel.add(new JLabel("Seniority:")); panel.add(seniorityField);
            panel.add(safetyBox);
        }

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        JButton deleteButton = new JButton("Delete");

        saveButton.addActionListener(ev -> {
            try {
                int age = Integer.parseInt(ageField.getText());
                float salary = Float.parseFloat(salaryField.getText());
                if (age <= 0 || salary < 0) {
                    throw new IllegalArgumentException("Age must be positive and salary non-negative.");
                }

                if (employee instanceof Pilot) {
                    int hours = Integer.parseInt(hoursFlownField.getText());
                    if (hours < 0) throw new IllegalArgumentException("Flight hours must be non-negative.");
                }

                employee.setName(nameField.getText());
                employee.setAge(age);
                employee.setContactInfo(contactField.getText());
                employee.setExtraDuties(dutiesField.getText());
                employee.setSalary(salary);
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
                JOptionPane.showMessageDialog(dialog, "Changes saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter valid numeric values.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        deleteButton.addActionListener(ev -> {
            controller.removeEmployee(employee);
            listModel.removeElement(employee);
            dialog.dispose();
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(saveButton);
        btnPanel.add(cancelButton);
        btnPanel.add(deleteButton);

        panel.add(Box.createVerticalStrut(10));
        panel.add(btnPanel);

        JScrollPane scrollPane = new JScrollPane(panel);
        dialog.add(scrollPane);
        dialog.setVisible(true);
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

}

