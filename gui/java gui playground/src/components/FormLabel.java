package components;

import javax.swing.*;
import java.awt.*;

public class FormLabel extends JLabel {

    public FormLabel(String text) {
        super(text, JLabel.LEFT);
        this.setFont(new Font("Robot", Font.BOLD, 16));
    }
}
