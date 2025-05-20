package Pages.Menu;

import components.FormInput;
import components.MainWindow;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class PageTemporaryMenu extends MainWindow {
    public PageTemporaryMenu(){super("Temporary Menu");}

    @Override
    protected JPanel generateBody(){
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        var leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(generateTable());
        leftPanel.add(generateCostForm());
        panel.add(leftPanel);
        panel.add(generateButtons());

        return panel;
    }
    protected JPanel generateTable(){
        var panel = new JPanel();


        String[] columnNames = {"Item", "Price"};
        Object[][] data = {
                {"Burger", 5.99},
                {"Pizza", 8.99},
                {"Salad", 4.49}
        };


        var tableModel = new DefaultTableModel(data, columnNames);
        var table = new JTable(tableModel);

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
    public void onRemove(){return;}
    public void onSave(){return;}
    public void onDelete(){return;}

    protected JPanel generateCostForm(){
        var panel = new JPanel();
        var panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter,BoxLayout.X_AXIS));
        var cost = new FormInput("Menu Cost",false,"$$$$");
        panelCenter.add(cost);
        panel.add(panelCenter);
        return panel;
    }
}
