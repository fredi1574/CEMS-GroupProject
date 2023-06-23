package util;

public interface ChatIF {
    /**
     * Method that when overridden is used to display objects onto
     * a UI.
     */
    public void accept(Object str);
    void display(String message);
}
