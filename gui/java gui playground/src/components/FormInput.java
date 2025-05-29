package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormInput extends JPanel {
    private JTextField inputBox;

    public FormInput(String labelText, boolean enabled, String defaultValue) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10, 0, 10, 0));

        JLabel label = new JLabel(labelText); // Replace FormLabel with JLabel

        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputBox = new JTextField(defaultValue);
        inputBox.setPreferredSize(new Dimension(350, 30));
        inputBox.setEnabled(enabled);

        this.add(label);
        this.add(inputBox);
    }

    public void setActive(boolean state) {
        inputBox.setEnabled(state);
    }

    public boolean validateInput() {
        return true;
    }

    public String getText() throws Exception {
        if (!validateInput()) {
            throw new Exception("Invalid input...");
        }
        return inputBox.getText();
    }
}