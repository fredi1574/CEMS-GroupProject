package util;

import javafx.scene.control.TextField;

public class TextFormatter {
    /**
     * limits the given text field's input
     * @param fieldName the text field whose inputs we wish to limit
     * @param numbersOnly text field will only accept numbers if value is true
     * @param maxInputLength the input length limit
     */
    public static void formatField(TextField fieldName, boolean numbersOnly, int maxInputLength) {
        javafx.scene.control.TextFormatter<String> textFormatter = new javafx.scene.control.TextFormatter<>(input -> {
            if (numbersOnly) {
                if (input.getControlNewText().matches("\\d*") &&
                        input.getControlNewText().length() <= maxInputLength) {
                    return input;
                }
            } else {
                if (input.getControlNewText().length() <= maxInputLength) {
                    return input;
                }
            }
            return null;
        });

        fieldName.setTextFormatter(textFormatter);
    }
}
