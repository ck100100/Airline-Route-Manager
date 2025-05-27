package components;

import utils.DateTime;
import utils.Exceptions.InvalidInputException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormDateTimeInput extends JPanel {
    private JTextField hourInput, minuteInput, dayInput, monthInput, yearInput;
    private boolean isEnabled;
    private String labelText;
    public FormDateTimeInput(String labelText, DateTime dateTime, boolean enabled) {
        this.isEnabled = enabled;
        this.labelText = labelText;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10, 0, 10, 0));


        JLabel label = new FormLabel(labelText);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        hourInput = generateField(dateTime.hour);
        minuteInput = generateField(dateTime.minute);
        dayInput = generateField(dateTime.day);
        monthInput = generateField(dateTime.month);
        yearInput = generateField(dateTime.year);


        inputPanel.add(hourInput);
        inputPanel.add(generateTimeSeperator());
        inputPanel.add(minuteInput);
        inputPanel.add(generateHorizontalSpacer());
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
                    Integer.parseInt(minuteInput.getText()),
                    Integer.parseInt(hourInput.getText()),
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

    private JComponent generateTimeSeperator() {
        return new JLabel(":");
    }

    private JComponent generateDateSeperator() {
        return new JLabel("/");
    }

    private JComponent generateHorizontalSpacer() {
        var spacer = new JPanel();
        spacer.setMaximumSize(new Dimension(100, 0));
        return spacer;
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
}
