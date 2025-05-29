package components;

import javax.swing.*;
import java.awt.*;

public class PanelCentered {
    private JPanel panelOuter, panelInner;

    public PanelCentered() {
        panelOuter = new JPanel();
        panelOuter.setLayout(new BorderLayout());

        JPanel spacerLeft = new JPanel();
        spacerLeft.setPreferredSize(new Dimension(200, 1));
        JPanel spacerRight = new JPanel();
        spacerRight.setPreferredSize(new Dimension(200, 1));

        panelInner = new JPanel();
        panelInner.setBackground(Color.RED);
        panelInner.setLayout(new BoxLayout(panelInner, BoxLayout.Y_AXIS));

        panelOuter.add(spacerLeft, BorderLayout.WEST);
        panelOuter.add(panelInner, BorderLayout.CENTER);
        panelOuter.add(spacerRight, BorderLayout.EAST);
    }

    public void add(JComponent component) {
        panelInner.add(component, BorderLayout.CENTER);
    }

    public void attatch(JComponent parentComponent) {
        parentComponent.add(panelOuter);
    }

    public void attatch(Container contentPane) {
    }
}
