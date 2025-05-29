package Pages.Menu;

import components.ButtonFactory;
import components.FormInput;
import components.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageInsertMenuItem extends MainWindow {
    public PageInsertMenuItem(){
        super("Insert Menu Item");
    }

    @Override
    protected JPanel generateBody(){
        var panel = new JPanel();
        var nameInput = new FormInput("Name", true, "");
        var costInput = new FormInput("Cost", true, "");
        var weightInput = new FormInput("Weight", true, "");
        panel.add(nameInput);
        panel.add(costInput);
        panel.add(weightInput);

        panel.add(createButtons());

        return panel;
    }

    private JPanel createButtons() {
        var buttonPanel = new JPanel();
        var submitButton = ButtonFactory.primary("Submit");
        var cancelButton = ButtonFactory.primary("Cancel");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSubmit();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        return buttonPanel;
    }

    public void onSubmit() {
        /*
        does something when we press submit
        */

        return;
    }

    public void onCancel() {
        closeWindow();
    }
}
