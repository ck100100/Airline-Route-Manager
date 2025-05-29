package Pages.Airplanelogs;

import Object.AirplaneLog;
import utils.AirplaneStatus;

import javax.swing.*;
import java.awt.*;

public class PageAirplaneForm extends JDialog {
    private JTextField nameField, capacityField, loadField, carryOnsField, cargoSizeField,
            rangeField, rowsField, seatsPerRowField, typeField;
    private JCheckBox activeBox;

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
        activeBox = new JCheckBox("Active");
        activeBox.setSelected(airplane.getStatus() == AirplaneStatus.active);

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
        formPanel.add(activeBox);

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

        cancelBtn.addActionListener(e -> {
            dispose();
        });

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
        airplane.setActive(activeBox.isSelected());
    }

    private boolean validateFields() {
        try {
            Integer.parseInt(capacityField.getText().trim());
            Double.parseDouble(loadField.getText().trim());
            Integer.parseInt(carryOnsField.getText().trim());
            Double.parseDouble(cargoSizeField.getText().trim());
            Double.parseDouble(rangeField.getText().trim());
            Integer.parseInt(rowsField.getText().trim());
            Integer.parseInt(seatsPerRowField.getText().trim());
            if (nameField.getText().trim().isEmpty() || typeField.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Name and type must not be empty.");
            }
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
            return false;
        }
    }
}
