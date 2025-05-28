package components;

import utils.Exceptions.InvalidInputException;

public class FormInputNumerical extends FormInput {
    public FormInputNumerical(String labelText, boolean enabled, Double defaultValue) {
        super(labelText, enabled, Double.toString(defaultValue));
    }

    @Override
    public boolean validateInput() {
        String textContents = inputBox.getText();
        try {
            Double.parseDouble(textContents);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    public double getValue() throws InvalidInputException {
        String textContents = getText();
        return Double.parseDouble(textContents);
    }

}
