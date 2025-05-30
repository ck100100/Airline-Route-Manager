package Pages.Menu;

import Controllers.ControllerFoodMenu;
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
    private final ControllerFoodMenu foodMenuController;
    private final List<FoodMenuItem> selectedItems = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;
    private FormInput nameInput;
    private FormInput descriptionInput;
    public PageMenuCreation(ControllerMenuItem controller, ControllerFoodMenu foodMenuController){
        super("Create Menu");
        this.controller = controller;
        this.foodMenuController = foodMenuController;
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

         nameInput = new FormInput("Name", true, "");
         descriptionInput = new FormInput("Description", true, "");
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
                try {
                    onRegisterMenu();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
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
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(null,"No items selected to insert");
            return;
        }
        String name = tableModel.getValueAt(selectedRow,0).toString();
        double price = Double.parseDouble(tableModel.getValueAt(selectedRow,1).toString());
        double weight = Double.parseDouble(tableModel.getValueAt(selectedRow,2).toString());
        FoodMenuItem selectedItem = new FoodMenuItem();
        selectedItem.menuItemName = name;
        selectedItem.price = price;
        selectedItem.weight = weight;
        selectedItems.add(selectedItem);
    }

    private void onRegisterMenu() throws Exception {
        String name = nameInput.getText();
        String description = descriptionInput.getText();
        if(name.isEmpty() || description.isEmpty()){
            JOptionPane.showMessageDialog(null,"You have to fill all forms before creating menu");
            return;
        }
        float price = calculatePrice(selectedItems);
        if(selectedItems.isEmpty()){
            JOptionPane.showMessageDialog(null,"You have to select menu items first to create menu");
            return;
        }
        var TemporaryMenu = new PageTemporaryMenu(selectedItems,name,description,price,foodMenuController);
        TemporaryMenu.show();
    }
    private void onCancel() {
        selectedItems.clear();
        closeWindow();
    }
    private void onInsertMenuItem(){
        var InsertMenuItem = new PageInsertMenuItem(controller, this::refreshTableData);
        InsertMenuItem.show();
    }
    private int calculatePrice(List<FoodMenuItem> selectedItems){
        int price = 0;
        for(FoodMenuItem item : selectedItems){
            price += item.price;
        }
        return price;
    }

}
