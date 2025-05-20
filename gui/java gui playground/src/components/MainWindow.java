package components;

import components.ButtonFactory;
import components.FormInput;
import components.PanelCentered;

import javax.swing.*;
import java.awt.*;
import utils.Colors;

public abstract class MainWindow {
    private JFrame window;
    private String title;

    private final int headerHeight = 100;

    public MainWindow(String title) {
        this.title = title;
        initialise();
    }

    public void show() {
        var outerPanel = new JPanel();
        outerPanel.setLayout(new BorderLayout());
        outerPanel.add(displayHeader(), BorderLayout.NORTH);
        var panel = new PanelCentered();
        panel.attatch(outerPanel);
        panel.add(generateBody());
        window.add(outerPanel, BorderLayout.CENTER);
        window.setVisible(true);
    }

    private JPanel displayHeader() {
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BorderLayout());
        panelHeader.setPreferredSize(new Dimension(1, headerHeight));
        panelHeader.setBackground(Colors.secondary);

        JLabel label = new JLabel(this.title);
        label.setFont(new Font("Roboto", Font.PLAIN, 30));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        panelHeader.add(label, BorderLayout.CENTER);
        return panelHeader;
    }

    protected abstract JPanel generateBody();

    private void initialise() {
        window = new JFrame();
        window.setTitle("Hello World");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1200, 800);
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout());
    }

    public void closeWindow() {
        window.dispose();
    }
}
