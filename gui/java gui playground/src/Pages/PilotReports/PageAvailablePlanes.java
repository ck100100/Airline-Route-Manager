package Pages.PilotReports;

import Controllers.ControllerAirplane;
import components.MainWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Controllers.ControllerAirplane;
import Object.AirplaneLog;
import utils.AirplaneStatus;

public class PageAvailablePlanes extends MainWindow {
    private JTable table;
    private DefaultTableModel tableModel;
    private final ControllerAirplane controllerAirplane;
    private List<AirplaneLog> activePlaneList = new ArrayList<>();
    public PageAvailablePlanes(ControllerAirplane controllerAirplane) {

        super("Available Planes");
        this.controllerAirplane = controllerAirplane;
    }

    @Override
    protected JPanel generateBody() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(generateTable());
        panel.add(generateButtons());

        return panel;
    }

    protected JPanel generateTable() {
        var panel = new JPanel();
        panel.setLayout(new BorderLayout());
        String[] columnNames = {"ID","Name","Type","Capacity"};

        tableModel = new DefaultTableModel(columnNames,0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table = new JTable(tableModel);
        for(AirplaneLog plane : controllerAirplane.getAllAirplanes()) {
            if(plane.getStatus() == AirplaneStatus.active){
                activePlaneList.add(plane);
                tableModel.addRow(new Object[]{
                        plane.getId(),
                        plane.getName(),
                        plane.getType(),
                        plane.getCapacity()
                });
            }
        }

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        return panel;
    }

    protected JPanel generateButtons() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        var selectBtn = new JButton("Select");
        var cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        selectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSelect();
            }
        });

        panel.add(selectBtn);
        panel.add(cancelBtn);
        return panel;
    }

    public void onCancel() {
        closeWindow();
    }

    public void onSelect() {
        int selectedRow = table.getSelectedRow();
        AirplaneLog plane = activePlaneList.get(selectedRow);
        var PlaneFlights = new PageRecentFlight(plane);
        PlaneFlights.show();
    }
}

