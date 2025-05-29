package Pages.Menu;

import components.FormInput;
import components.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        return panel;
    }

    private JPanel generateMenuItemsList() {
        var panel = new JPanel();
        return panel;
    }
    private void onInsert() {
        return;
    }

    private void onRegisterMenu() {
        return;
    }
    private void onCancel() {
        closeWindow();
    }
}
