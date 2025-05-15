package components;

import javax.swing.*;
import java.awt.*;

public class ButtonFactory {
    public static JButton primary(String text) {
        JButton btn = new JButton(text);

        btn.setFont(new Font("Roboto", Font.PLAIN, 16));
        btn.setOpaque(true);
        return btn;
    }

    public static void primary(String εύρεσηΕργολάβων, Object o) {
    }
}
