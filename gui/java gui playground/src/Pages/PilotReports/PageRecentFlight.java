package Pages.PilotReports;

import components.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageRecentFlight extends MainWindow {
    public PageRecentFlight() {
        super("Recent Flights");
    }

    @Override
    protected JPanel generateBody() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(generateTable());
        panel.add(generateButtons());

        return panel;
    }

    protected JPanel generateTable() {
        var panel = new JPanel();
        panel.add(new JLabel("Placeholder..."));

        return panel;
    }

    protected JPanel generateButtons() {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        var selectBtn = new JButton("Select");
        var cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        selectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSelect();
            }
        });

        panel.add(selectBtn);
        panel.add(cancelBtn);
        return panel;
    }

    public void onCancel() {
        closeWindow();
    }

    public void onSelect() {
        return;
    }
}
