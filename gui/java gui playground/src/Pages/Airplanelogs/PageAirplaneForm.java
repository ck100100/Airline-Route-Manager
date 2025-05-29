package Pages.Airplanelogs;

import Object.AirplaneLog;

import javax.swing.*;
import java.awt.*;

public class PageAirplaneForm extends JDialog {
    private JTextField nameField, capacityField, loadField, carryOnsField, cargoSizeField,
            rangeField, rowsField, seatsPerRowField, typeField;
    private JComboBox<AirplaneLog.AirplaneStatus> statusComboBox;

    private final AirplaneLog airplane;
    private final PageAirplaneGUI parent;
    private final boolean isNew;

    public PageAirplaneForm(PageAirplaneGUI parent, AirplaneLog airplaneToEdit) {
        super(parent, "Airplane Details", true);
        this.parent = parent;
        this.airplane = (airplaneToEdit != null) ? airplaneToEdit : new AirplaneLog("");
        this.isNew = airplaneToEdit == null;

        setSize(400, 500);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(11, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        nameField = new JTextField(airplane.getName());
        capacityField = new JTextField(String.valueOf(airplane.getCapacity()));
        loadField = new JTextField(String.valueOf(airplane.getMaxTotalLoad()));
        carryOnsField = new JTextField(String.valueOf(airplane.getMaxCarryOns()));
        cargoSizeField = new JTextField(String.valueOf(airplane.getMaxCargoSize()));
        rangeField = new JTextField(String.valueOf(airplane.getMaxFlightRange()));
        rowsField = new JTextField(String.valueOf(airplane.getNumberOfRows()));
        seatsPerRowField = new JTextField(String.valueOf(airplane.getSeatsPerRow()));
        typeField = new JTextField(airplane.getType() == null ? "" : airplane.getType());

        statusComboBox = new JComboBox<>(new AirplaneLog.AirplaneStatus[]{
                AirplaneLog.AirplaneStatus.active,
                AirplaneLog.AirplaneStatus.inactive
        });
        statusComboBox.setSelectedItem(airplane.getStatus());

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Capacity:"));
        formPanel.add(capacityField);
        formPanel.add(new JLabel("Max Total Load (kg):"));
        formPanel.add(loadField);
        formPanel.add(new JLabel("Max Carry-ons:"));
        formPanel.add(carryOnsField);
        formPanel.add(new JLabel("Max Cargo Size (m³):"));
        formPanel.add(cargoSizeField);
        formPanel.add(new JLabel("Flight Range (mi):"));
        formPanel.add(rangeField);
        formPanel.add(new JLabel("Number of Rows:"));
        formPanel.add(rowsField);
        formPanel.add(new JLabel("Seats per Row:"));
        formPanel.add(seatsPerRowField);
        formPanel.add(new JLabel("Type:"));
        formPanel.add(typeField);
        formPanel.add(new JLabel("Status:"));
        formPanel.add(statusComboBox);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");
        JButton deleteBtn = new JButton("Delete");

        buttonPanel.add(saveBtn);
        if (!isNew) buttonPanel.add(deleteBtn);
        buttonPanel.add(cancelBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        saveBtn.addActionListener(e -> {
            if (validateFields()) {
                updateAirplaneFromFields();
                parent.addOrUpdateAirplane(airplane);
                JOptionPane.showMessageDialog(this, "Airplane saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        deleteBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this airplane?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                parent.deleteAirplane(airplane);
                dispose();
            }
        });

        cancelBtn.addActionListener(e -> dispose());

        setLocationRelativeTo(parent);
    }

    private void updateAirplaneFromFields() {
        airplane.setName(nameField.getText().trim());
        airplane.setCapacity(Integer.parseInt(capacityField.getText().trim()));
        airplane.setMaxTotalLoad(Double.parseDouble(loadField.getText().trim()));
        airplane.setMaxCarryOns(Integer.parseInt(carryOnsField.getText().trim()));
        airplane.setMaxCargoSize(Double.parseDouble(cargoSizeField.getText().trim()));
        airplane.setMaxFlightRange(Double.parseDouble(rangeField.getText().trim()));
        airplane.setNumberOfRows(Integer.parseInt(rowsField.getText().trim()));
        airplane.setSeatsPerRow(Integer.parseInt(seatsPerRowField.getText().trim()));
        airplane.setType(typeField.getText().trim().toLowerCase());
        airplane.setStatus((AirplaneLog.AirplaneStatus) statusComboBox.getSelectedItem());
    }

    private boolean validateFields() {
        try {
            String name = nameField.getText().trim();
            String type = typeField.getText().trim();

            if (name.isEmpty() || type.isEmpty()) {
                throw new IllegalArgumentException("Name and type must not be empty.");
            }

            int capacity = Integer.parseInt(capacityField.getText().trim());
            double load = Double.parseDouble(loadField.getText().trim());
            int carryOns = Integer.parseInt(carryOnsField.getText().trim());
            double cargoSize = Double.parseDouble(cargoSizeField.getText().trim());
            double range = Double.parseDouble(rangeField.getText().trim());
            int rows = Integer.parseInt(rowsField.getText().trim());
            int seatsPerRow = Integer.parseInt(seatsPerRowField.getText().trim());

            if (capacity <= 0 || capacity > 1000) throw new IllegalArgumentException("Capacity must be between 1 and 1000.");
            if (load < 0 || load > 100000) throw new IllegalArgumentException("Max load is too high or negative.");
            if (carryOns < 0 || carryOns > 500) throw new IllegalArgumentException("Unreasonable carry-on count.");
            if (cargoSize < 0 || cargoSize > 5000) throw new IllegalArgumentException("Cargo size must be reasonable.");
            if (range <= 0 || range > 20000) throw new IllegalArgumentException("Flight range must be between 1 and 20000.");
            if (rows <= 0 || rows > 100) throw new IllegalArgumentException("Row count must be between 1 and 100.");
            if (seatsPerRow <= 0 || seatsPerRow > 20) throw new IllegalArgumentException("Seats per row must be between 1 and 20.");

            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
