package components;

import javax.swing.*;
import java.awt.*;

public class ErrorText extends JLabel {
    private boolean hasErrorMsg;
    public ErrorText() {
        setProperties();
        this.setText("");
        this.hasErrorMsg = false;

    }

    public ErrorText(String errorMsg) {
        setProperties();
        this.setText(errorMsg);
        this.hasErrorMsg = true;
    }

    public void setErrorMsg(String errorMsg) {
        this.setText(errorMsg);
        hasErrorMsg = true;
        this.repaint();
    }

    public void clear() {
        this.setText("");
        hasErrorMsg = false;
        this.repaint();
    }

    public boolean isDisplayingError() {
        return this.hasErrorMsg;
    }

    private void setProperties() {
        this.setFont(new Font("Robot", Font.BOLD, 14));
        this.setForeground(Color.red);
    }
}
