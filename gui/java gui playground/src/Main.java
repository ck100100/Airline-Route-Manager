import Pages.Contractors.PageMain;
import Controllers.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Airline airline = new Airline();
                var main = new PageMain(airline);
                main.show();
            }
        });
    }
}