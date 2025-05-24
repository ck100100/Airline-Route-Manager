package Pages.Menu;

import Controllers.ControllerMenuItem;
import components.FormInput;
import components.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Object.FoodMenuItem;

public class PageMenuCreation extends MainWindow {
    private final ControllerMenuItem controller;
    private final List<FoodMenuItem> selectedItems = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;
    public PageMenuCreation(ControllerMenuItem controller){
        super("Create Menu");
        this.controller = controller;
    }


    @Override
    protected JPanel generateBody(){
        var panel = new JPanel();
        panel.add(generateMenuItemsList());
        panel.add(generateTextFields());
        return panel;
    }

    protected JPanel generateTextFields(){
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        var nameInput = new FormInput("Name", true, "");
        var descriptionInput = new FormInput("Description", true, "");
        panel.add(nameInput);
        panel.add(descriptionInput);

        var insertBtn = new JButton("Insert");
        insertBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onInsert();
            }
        });
        panel.add(insertBtn);
        var cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        panel.add(cancelBtn);
        var registerMenuBtn = new JButton("Register Menu");
        registerMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRegisterMenu();
            }
        });
        panel.add(registerMenuBtn);
        var insertMenuItemBtn = new JButton("Insert Menu Item");
        insertMenuItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onInsertMenuItem();
            }
        });
        panel.add(insertMenuItemBtn);

        return panel;
    }

    private JPanel generateMenuItemsList() {
        var panel = new JPanel();
        String[] columnNames = {"Item", "Price","Weight"};

         tableModel = new DefaultTableModel(columnNames,0);
         table = new JTable(tableModel);
        refreshTableData();


        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        return panel;
    }
    private void refreshTableData(){
        tableModel.setRowCount(0);
        for(var item : controller.getMenuItems()){
            Object[] row = {item.menuItemName, item.price, item.weight};
            tableModel.addRow(row);
        }
    }
    private void onInsert() {
        int selectedRow = table.getSelectedRow();
        String name = tableModel.getValueAt(selectedRow,0).toString();
        double price = Double.parseDouble(tableModel.getValueAt(selectedRow,1).toString());
        double weight = Double.parseDouble(tableModel.getValueAt(selectedRow,2).toString());
        FoodMenuItem selectedItem = new FoodMenuItem();
        selectedItem.menuItemName = name;
        selectedItem.price = price;
        selectedItem.weight = weight;
        selectedItems.add(selectedItem);
    }

    private void onRegisterMenu() {
        var TemporaryMenu = new PageTemporaryMenu();
        TemporaryMenu.show();
    }
    private void onCancel() {
        closeWindow();
    }
    private void onInsertMenuItem(){
        var InsertMenuItem = new PageInsertMenuItem(controller, this::refreshTableData);
        InsertMenuItem.show();
    }

}
