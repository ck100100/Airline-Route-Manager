package Pages.Menu;

import Controllers.ControllerFoodMenu;
import components.FormInput;
import components.MainWindow;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Object.FoodMenuItem;
import Object.FoodMenu;

public class PageTemporaryMenu extends MainWindow {
    private List<FoodMenuItem> tempItems;
    private final ControllerFoodMenu controllerFoodMenu;
    private JTable table;
    private String name;
    private String description;
    private float price;
    private DefaultTableModel tableModel;
    public PageTemporaryMenu(List<FoodMenuItem> tempItems, String name, String description, float price, ControllerFoodMenu controllerFoodMenu){
        super("Temporary Menu");
        this.tempItems = tempItems;
        this.name = name;
        this.description = description;
        this.price = price;
        this.controllerFoodMenu = controllerFoodMenu;
    }

    @Override
    protected JPanel generateBody(){
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        var leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(nameAndDescriptionForm());
        leftPanel.add(generateTable());
        leftPanel.add(generateCostForm());
        panel.add(leftPanel);
        panel.add(generateButtons());

        return panel;
    }
    protected JPanel generateTable(){
        var panel = new JPanel();


        String[] columnNames = {"Item", "Price"};
        tableModel = new DefaultTableModel(columnNames,0);
        table = new JTable(tableModel);
        refreshTable();


        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        return panel;
    }
    protected JPanel generateButtons(){
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        var removeItemBtn = new JButton("Remove Item");
        var saveMenuBtn = new JButton("Save Menu");
        var deleteMenuBtn = new JButton("Delete Menu");

        removeItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRemove();
            }
        });
        saveMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSave();
            }
        });
        deleteMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDelete();
            }
        });
        panel.add(removeItemBtn);
        panel.add(saveMenuBtn);
        panel.add(deleteMenuBtn);
        return panel;
    }
    protected JPanel nameAndDescriptionForm(){
        var panel = new JPanel();
        var panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter,BoxLayout.X_AXIS));
        var nameLabel = new FormInput("Name",false,name);
        var descriptionLabel = new FormInput("Description",false,description);
        panelCenter.add(nameLabel);
        panelCenter.add(descriptionLabel);
        panel.add(panelCenter);
        return panel;
    }
    public void onRemove(){
        int selectedRow = table.getSelectedRow();
        tempItems.remove(selectedRow);
        for(FoodMenuItem item : tempItems){
            price += item.price;
        }
        refreshTable();
    }
    private void refreshTable(){
        tableModel.setRowCount(0);
        for(FoodMenuItem item : tempItems){
            tableModel.addRow(new Object[]{item.menuItemName, item.price});
        }
    }
    public void onSave(){
        if(tempItems.isEmpty()){
            JOptionPane.showMessageDialog(null,"Cannot save an empty menu");
            return;
        }
        FoodMenu newMenu = new FoodMenu();
        newMenu.menuID = controllerFoodMenu.getNextFoodMenuId();
        newMenu.description = description;
        newMenu.name = name;
        newMenu.foodMenu = tempItems;
        controllerFoodMenu.addMenu(newMenu);
        tempItems.clear();
        refreshTable();
        closeWindow();
    }
    public void onDelete(){
        tempItems.clear();
        refreshTable();
        closeWindow();
    }

    protected JPanel generateCostForm(){
        var panel = new JPanel();
        var panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter,BoxLayout.X_AXIS));
        var cost = new FormInput("Menu price",false,String.valueOf(price));
        panelCenter.add(cost);
        panel.add(panelCenter);
        return panel;
    }
}
