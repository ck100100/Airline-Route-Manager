package Pages.Airportlogs;

import Controllers.ControllerAirport;
import Object.AirportLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;

public class PageAirportGUI {
    private JFrame frame;
    private DefaultListModel<AirportLog> listModel;
    private JList<AirportLog> airportJList;
    private final ControllerAirport airportLogs = new ControllerAirport();

    public PageAirportGUI() {
        initGUI();
    }

    private void initGUI() {
        frame = new JFrame("Airports");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Airports");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(titleLabel, BorderLayout.WEST);

        JButton addButton = new JButton("+");
        addButton.addActionListener(e -> showForm(null));
        topPanel.add(addButton, BorderLayout.EAST);

        listModel = new DefaultListModel<>();
        for (AirportLog log : airportLogs.getAllAirports()) {
            listModel.addElement(log);
        }

        airportJList = new JList<>(listModel);
        airportJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        airportJList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !airportJList.isSelectionEmpty()) {
                    showForm(airportJList.getSelectedValue());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(airportJList);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void showForm(AirportLog original) {
        boolean isEdit = original != null;
        AirportLog temp = new AirportLog("");

        if (isEdit) {
            temp.setNameAirport(original.getNameAirport());
            temp.setLocation(original.getLocation());
            temp.setCapacity(original.getCapacity());
            temp.setOpeningTime(original.getOpeningTime());
            temp.setClosingTime(original.getClosingTime());
            temp.setMaxFlights(original.getMaxFlights());
            temp.setMainRunwayLength(original.getMainRunwayLength());
            temp.setNumberOfRunways(original.getNumberOfRunways());
            temp.setStatus(original.isStatus());
        }

        JFrame formFrame = new JFrame(isEdit ? "Edit Airport" : "Add Airport");
        formFrame.setSize(600, 500);
        formFrame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        JTextField nameField = new JTextField(temp.getNameAirport(), 20);
        JTextField locationField = new JTextField(temp.getLocation(), 20);
        JTextField capacityField = new JTextField(String.valueOf(temp.getCapacity()));
        JTextField openField = new JTextField(temp.getOpeningTime() != null ? temp.getOpeningTime().toString() : "06:00");
        JTextField closeField = new JTextField(temp.getClosingTime() != null ? temp.getClosingTime().toString() : "22:00");
        JTextField maxFlightsField = new JTextField(String.valueOf(temp.getMaxFlights()));
        JTextField runwayLengthField = new JTextField(String.valueOf(temp.getMainRunwayLength()));
        JTextField runwayCountField = new JTextField(String.valueOf(temp.getNumberOfRunways()));
        JCheckBox statusBox = new JCheckBox("Active", temp.isStatus());

        String[] labels = {"Name", "Location", "Capacity", "Opening Time (HH:mm)", "Closing Time (HH:mm)",
                "Max Flights", "Main Runway Length", "Number of Runways"};

        JTextField[] fields = {nameField, locationField, capacityField, openField, closeField,
                maxFlightsField, runwayLengthField, runwayCountField};

        for (int i = 0; i < labels.length; i++) {
            c.gridx = 0;
            c.gridy = i;
            formFrame.add(new JLabel(labels[i] + ":"), c);
            c.gridx = 1;
            formFrame.add(fields[i], c);
        }

        c.gridx = 0; c.gridy = labels.length;
        c.gridwidth = 2;
        formFrame.add(statusBox, c);

        JPanel buttons = new JPanel(new GridLayout(1, 3, 5, 5));
        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");
        JButton delete = new JButton("Delete");

        buttons.add(save);
        if (isEdit) buttons.add(delete);
        buttons.add(cancel);

        c.gridy++;
        formFrame.add(buttons, c);

        save.addActionListener(e -> {
            try {
                temp.setNameAirport(nameField.getText().trim());
                temp.setLocation(locationField.getText().trim());
                temp.setCapacity(Integer.parseInt(capacityField.getText()));
                temp.setOpeningTime(LocalTime.parse(openField.getText()));
                temp.setClosingTime(LocalTime.parse(closeField.getText()));
                temp.setMaxFlights(Integer.parseInt(maxFlightsField.getText()));
                temp.setMainRunwayLength(Float.parseFloat(runwayLengthField.getText()));
                temp.setNumberOfRunways(Integer.parseInt(runwayCountField.getText()));
                temp.setStatus(statusBox.isSelected());

                if (isEdit) {
                    original.setNameAirport(temp.getNameAirport());
                    original.setLocation(temp.getLocation());
                    original.setCapacity(temp.getCapacity());
                    original.setOpeningTime(temp.getOpeningTime());
                    original.setClosingTime(temp.getClosingTime());
                    original.setMaxFlights(temp.getMaxFlights());
                    original.setMainRunwayLength(temp.getMainRunwayLength());
                    original.setNumberOfRunways(temp.getNumberOfRunways());
                    original.setStatus(temp.isStatus());
                    airportJList.repaint();
                } else {
                    airportLogs.addAirport(temp);
                    listModel.addElement(temp);
                }
                formFrame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(formFrame, "Invalid input: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        if (isEdit) {
            delete.addActionListener(e -> {
                airportLogs.removeAirport(original);
                listModel.removeElement(original);
                formFrame.dispose();
            });
        }

        cancel.addActionListener(e -> formFrame.dispose());

        formFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PageAirportGUI::new);
    }
}
