package Pages.Airplanelogs;

import Controllers.*;
import Object.AirplaneLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.stream.Collectors;

public class PageAirplaneGUI extends JFrame {
    private DefaultListModel<String> airplaneListModel;
    private JList<String> airplaneJList;
    private JTextField capacityField, typeField, flightRangeField;
    private ControllerAirplane airplaneLogs = new ControllerAirplane();
    private JPanel rightPanel;

    public PageAirplaneGUI() {
        setTitle("Airplane Logs");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        airplaneListModel = new DefaultListModel<>();
        airplaneJList = new JList<>(airplaneListModel);
        JScrollPane scrollPane = new JScrollPane(airplaneJList);
        scrollPane.setPreferredSize(new Dimension(300, getHeight()));

        JLabel titleLabel = new JLabel("Airplanes");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JButton addButton = new JButton("+");
        JPanel topLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topLeftPanel.add(titleLabel);
        topLeftPanel.add(addButton);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(topLeftPanel, BorderLayout.NORTH);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);

        setupRightPanel();
        add(rightPanel, BorderLayout.CENTER);

        updateAirplaneList(airplaneLogs.getAllAirplanes());

        addButton.addActionListener(e -> {
            PageAirplaneForm form = new PageAirplaneForm(this, null);
            form.setVisible(true);
        });

        airplaneJList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = airplaneJList.getSelectedIndex();
                    if (index != -1) {
                        int id = getAirplaneIdFromList(index);
                        AirplaneLog selected = findById(id);
                        if (selected != null) {
                            new PageAirplaneForm(PageAirplaneGUI.this, selected).setVisible(true);
                        }
                    }
                }
            }
        });
    }

    private void setupRightPanel() {
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel filterLabel = new JLabel("Filter By");
        filterLabel.setFont(new Font("Arial", Font.BOLD, 14));

        capacityField = new JTextField(10);
        typeField = new JTextField(10);
        flightRangeField = new JTextField(10);

        JButton filterCapacity = new JButton("🔍");
        JButton filterType = new JButton("🔍");
        JButton filterRange = new JButton("🔍");

        JPanel capPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        capPanel.add(new JLabel("Capacity"));
        capPanel.add(capacityField);
        capPanel.add(filterCapacity);

        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typePanel.add(new JLabel("Type"));
        typePanel.add(typeField);
        typePanel.add(filterType);

        JPanel rangePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rangePanel.add(new JLabel("Flight range"));
        rangePanel.add(flightRangeField);
        rangePanel.add(filterRange);

        rightPanel.add(filterLabel);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(capPanel);
        rightPanel.add(typePanel);
        rightPanel.add(rangePanel);

        filterCapacity.addActionListener(e -> applyFilters());
        filterType.addActionListener(e -> applyFilters());
        filterRange.addActionListener(e -> applyFilters());
    }

    private void applyFilters() {
        List<AirplaneLog> filtered = airplaneLogs.getAllAirplanes();

        String capInput = capacityField.getText().trim();
        String rangeInput = flightRangeField.getText().trim();
        String typeInput = typeField.getText().trim().toLowerCase();

        if (!capInput.isEmpty() && capInput.contains("-")) {
            String[] parts = capInput.split("-");
            int min = Integer.parseInt(parts[0]);
            int max = Integer.parseInt(parts[1]);
            filtered = filtered.stream()
                    .filter(a -> a.getCapacity() >= min && a.getCapacity() <= max)
                    .collect(Collectors.toList());
        }

        if (!rangeInput.isEmpty() && rangeInput.contains("-")) {
            String[] parts = rangeInput.split("-");
            int min = Integer.parseInt(parts[0]);
            int max = Integer.parseInt(parts[1]);
            filtered = filtered.stream()
                    .filter(a -> a.getMaxFlightRange() >= min && a.getMaxFlightRange() <= max)
                    .collect(Collectors.toList());
        }

        if (!typeInput.isEmpty()) {
            filtered = filtered.stream()
                    .filter(a -> a.getType() != null && a.getType().contains(typeInput))
                    .collect(Collectors.toList());
        }

        updateAirplaneList(filtered);
    }

    private int getAirplaneIdFromList(int index) {
        String listEntry = airplaneListModel.get(index);
        return Integer.parseInt(listEntry.split("–")[0].trim());
    }

    private void updateAirplaneList(List<AirplaneLog> list) {
        airplaneListModel.clear();
        for (AirplaneLog a : list) {
            airplaneListModel.addElement(a.getId() + " – (" + a.getName() + ")");
        }
    }

    public void addOrUpdateAirplane(AirplaneLog airplane) {
        if (!airplaneLogs.getAllAirplanes().contains(airplane)) {
            airplaneLogs.addAirplane(airplane);
        }
        updateAirplaneList(airplaneLogs.getAllAirplanes());
    }

    public void deleteAirplane(AirplaneLog airplane) {
        airplaneLogs.removeAirplane(airplane);
        updateAirplaneList(airplaneLogs.getAllAirplanes());
    }

    private AirplaneLog findById(int id) {
        return airplaneLogs.getAllAirplanes().stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PageAirplaneGUI().setVisible(true));
    }
}
