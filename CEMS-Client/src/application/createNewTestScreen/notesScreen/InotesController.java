package application.createNewTestScreen.notesScreen;

import javafx.event.ActionEvent;

public interface InotesController {
    void deleteTestIfAlreadyExists();

    void addTestToDB();

    void addAllTestQuestionsToDB();

    void replaceScreen(ActionEvent event);
}
