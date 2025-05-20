package Pages.Menu;

import components.FormInput;
import components.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class PageMenuCreation extends MainWindow {
    public PageMenuCreation(){super("Create Menu");}

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
    private void onInsert() {
        return;
    }

    private void onRegisterMenu() {
        var TemporaryMenu = new PageTemporaryMenu();
        TemporaryMenu.show();
    }
    private void onCancel() {
        closeWindow();
    }
    private void onInsertMenuItem(){
        var InsertMenuItem = new PageInsertMenuItem();
        InsertMenuItem.show();
    }
}
