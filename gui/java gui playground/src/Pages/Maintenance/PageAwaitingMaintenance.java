package Pages.Maintenance;

import Controllers.Airline;
import components.MainWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Object.AirplaneLog;
import utils.AirplaneStatus;

public class PageAwaitingMaintenance extends MainWindow {
    private JTable table;
    private DefaultTableModel tableModel;
    public Airline airline;
    private List<AirplaneLog> awaitingMaintenancePlanes = new ArrayList<>();
    public PageAwaitingMaintenance(Airline airline){
        super("Planes Awaiting Maintenance");
        this.airline = airline;
    }

    @Override
    protected JPanel generateBody(){
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(generateTable());
        panel.add(generateButtons());

        return panel;
    }

    protected JPanel generateTable(){
        var panel  = new JPanel();
        panel.setLayout(new BorderLayout());
        String[] columnNames = {"ID","Name","Type","Capacity"};
        tableModel = new DefaultTableModel(columnNames,0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table = new JTable(tableModel);
        for(AirplaneLog plane : airline.controllerAirplane.getAllAirplanes()){
            if(plane.getStatus() == AirplaneStatus.awaitingMaintenance){
                awaitingMaintenancePlanes.add(plane);
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
    public void onSelect(){
        int selectedRow = table.getSelectedRow();

        if(selectedRow == -1){
            JOptionPane.showMessageDialog(null,"You have to select a plane to continue");
            return;
        }
        AirplaneLog plane = awaitingMaintenancePlanes.get(selectedRow);
        var PageFlights = new PageFutureFlights(plane,airline);
        PageFlights.show();
    }

}
