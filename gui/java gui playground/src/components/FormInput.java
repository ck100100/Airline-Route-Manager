package components;

import utils.Exceptions.InvalidInputException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormInput extends JPanel {
    protected JTextField inputBox;
    private String label;
    public FormInput(String labelText, boolean enabled, String defaultValue) {
        this.label = labelText;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10, 0, 10, 0));

        JLabel label = new FormLabel(labelText);

        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputBox = new JTextField(defaultValue);
        inputBox.setMaximumSize(new Dimension(350, 30));
        inputBox.setEnabled(enabled);

        this.add(label);
        this.add(inputBox);
    }

    public void setActive(boolean state){
        inputBox.setEnabled(state);
    }

    public boolean validateInput() {
        return true;
    }

    public String getText() throws InvalidInputException {
        if(validateInput() == false)
            throw new InvalidInputException("The field '" + label + "' has an invalid value");
        return inputBox.getText();
    }
}
