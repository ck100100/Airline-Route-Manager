package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class FormDropdown extends JPanel {
    private JComboBox dropdown;

    public FormDropdown(String labelText, ArrayList<String> options) {
        Vector<String> formOptions = new Vector<>();
        formOptions.addAll(options);
        formOptions.add(0, "Select:");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 0, 10, 0));

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new FormLabel(labelText);
        labelPanel.add(label);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel dropdownPanel = new JPanel();
        dropdownPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        dropdown = new JComboBox(formOptions);
        dropdown.setMaximumSize(new Dimension(200, 30));
        dropdown.setSize(new Dimension(200, 30));
        dropdown.setAlignmentX(Component.LEFT_ALIGNMENT);
        dropdownPanel.add(dropdown);

        add(labelPanel);
        add(dropdownPanel);
    }

    public void setActive(boolean state) {
        dropdown.setEnabled(state);
    }

    public boolean validateInput() {
        if(dropdown.getSelectedIndex() == 0)
            return false;

        return true;
    }

    public String getText() throws Exception {
        if(validateInput() == false)
            throw new Exception("Invalid input");

        return (String) dropdown.getSelectedItem();
    }

    public int getSelectedIndex() {
        return dropdown.getSelectedIndex() - 1;
    }
}
