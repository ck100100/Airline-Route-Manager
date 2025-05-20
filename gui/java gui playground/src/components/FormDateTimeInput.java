package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormDateTimeInput extends JPanel {
    private JTextField hourInput, minuteInput, dayInput, monthInput, yearInput;
    private boolean isEnabled;
    public FormDateTimeInput(String labelText, boolean enabled, int defaultHour, int defaultMinute, int defaultDay, int defaultMonth, int defaultYear) {
        this.isEnabled = enabled;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10, 0, 10, 0));


        JLabel label = new FormLabel(labelText);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        hourInput = generateField(defaultHour);
        minuteInput = generateField(defaultMinute);
        dayInput = generateField(defaultDay);
        monthInput = generateField(defaultMonth);
        yearInput = generateField(defaultYear);


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

        var field = new JTextField(defaultValue);
        field.setMinimumSize(minSize);
        field.setMaximumSize(maxSize);
        field.setEnabled(this.isEnabled);

        return field;
    }
}
