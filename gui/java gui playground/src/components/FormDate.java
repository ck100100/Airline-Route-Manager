package components;

import utils.DateTime;
import utils.Exceptions.InvalidInputException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormDate extends JPanel {
    private JTextField hourInput, minuteInput, dayInput, monthInput, yearInput;
    private boolean isEnabled;
    private String labelText;


    public FormDate(String labelText, int day, int month, int year, boolean enabled) {
        this.isEnabled = enabled;
        this.labelText = labelText;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10, 0, 10, 0));


        JLabel label = new FormLabel(labelText);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dayInput = generateField(day);
        monthInput = generateField(month);
        yearInput = generateField(year);


        inputPanel.add(dayInput);
        inputPanel.add(generateDateSeperator());
        inputPanel.add(monthInput);
        inputPanel.add(generateDateSeperator());
        inputPanel.add(yearInput);

        this.add(label);
        this.add(inputPanel);
    }

    public DateTime getValue() throws InvalidInputException {
        try {
            return new DateTime(
                    0,
                    0,
                    Integer.parseInt(dayInput.getText()),
                    Integer.parseInt(monthInput.getText()),
                    Integer.parseInt(yearInput.getText())
            );
        } catch(NumberFormatException e) {
            throw new InvalidInputException("The input with label '" + labelText + "' does not have a valid date!");
        } catch(IllegalArgumentException e) {
            throw new InvalidInputException("The input with label '" + labelText + "' does not have a valid date!");
        }
    }

    private JTextField generateField(Integer defaultValue) {
        Dimension minSize = new Dimension(50, 30);
        Dimension maxSize = new Dimension(100, 30);

        var field = new JTextField(Integer.toString(defaultValue));
        field.setMinimumSize(minSize);
        field.setMaximumSize(maxSize);
        field.setEnabled(this.isEnabled);

        return field;
    }

    private JComponent generateDateSeperator() {
        return new JLabel("/");
    }
}
