package Pages.Flights;
import components.FormInput;
import components.FormLabel;
import components.MainWindow;

import javax.swing.*;

public class PageCreateFlight extends MainWindow {
    public PageCreateFlight(String title) {
        super(title);
    }

    @Override
    protected JPanel generateBody() {
        var panel = new JPanel();
        panel.add(new FormLabel("Hi"));
        var input1 = new FormInput("Hello", true, "");
        panel.add(input1);

        return panel;
    }
}
