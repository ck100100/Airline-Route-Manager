package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormInputLarge extends JPanel {
    private JTextArea inputBox;
    public FormInputLarge(String labelText, boolean enabled, String defaultValue) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10, 0, 10, 0));

        JLabel label = new FormLabel(labelText);

        label.setAlignmentX(Component.LEFT_ALIGNMENT);
//        label.setBorder(new EmptyBorder(0, 0, 10, 0));
        inputBox = new JTextArea(defaultValue, 10, 10);
        inputBox.setPreferredSize(new Dimension(350, 30));
        inputBox.setEnabled(enabled);

        this.add(label);
        this.add(inputBox);
    }

    public void setActive(boolean state){
        inputBox.setEnabled(state);
    }
}
