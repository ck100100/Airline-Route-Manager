package Pages.Menu;

import components.ButtonFactory;
import components.FormInput;
import components.MainWindow;
import Object.FoodMenuItem;
import Controllers.ControllerMenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageInsertMenuItem extends MainWindow {
    private final ControllerMenuItem controller;
    private Runnable onInsertCallback;
    private FormInput nameInput;
    private FormInput costInput;
    private FormInput weightInput;
    public PageInsertMenuItem(ControllerMenuItem controller,Runnable onInsertCallback){

        super("Insert Menu Item");
        this.controller = controller;
        this.onInsertCallback = onInsertCallback;
    }

    @Override
    protected JPanel generateBody(){
        var panel = new JPanel();
        this.nameInput = new FormInput("Name", true, "");
        this.costInput = new FormInput("Cost", true, "");
        this.weightInput = new FormInput("Weight", true, "");
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
        try{

            String name = nameInput.getText();
            String costStr = costInput.getText();
            String weightStr = weightInput.getText();

            if(name.isEmpty() || costStr.isEmpty() || weightStr.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"All fields are required");
                return;
            }
            double cost = Double.parseDouble(costStr);
            double weight = Double.parseDouble(weightStr);

            var newFoodItem = new FoodMenuItem();
            newFoodItem.menuItemName = name;
            newFoodItem.price = cost;
            newFoodItem.weight = weight;
            controller.addItem(newFoodItem);
            if(onInsertCallback != null){
                onInsertCallback.run();
            }

        } catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }


        closeWindow();
    }

    public void onCancel() {
        closeWindow();
    }
}
