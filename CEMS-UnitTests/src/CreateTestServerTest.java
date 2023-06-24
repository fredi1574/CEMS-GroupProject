import Client.Client;
import application.createNewTestScreen.notesScreen.InotesController;
import application.createNewTestScreen.notesScreen.NotesController;
import entity.*;
import javafx.event.ActionEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ChatIF;
import util.MsgHandler;
import util.StateManagement;
import util.TypeMsg;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateTestServerTest {

    private class stubServerClientCommunication implements IServerClientCommunication {
        private Object toServerMsg;
        private MsgHandler returnMsg;
        private String msg;

        private String errorMsg;

        private Object user;


        @Override
        public void sendToServer(Object msg) {
            toServerMsg = msg;

        }

        @Override
        public MsgHandler getReturnMsg() {

            return returnMsg;
        }

        @Override
        public void popUpError(String msg) {

            errorMsg = msg;
        }

        @Override
        public void popUpMessage(String msg) {
            this.msg = msg;
        }

        @Override
        public Object getUser() {
            return user;
        }

        @Override
        public void setUser(Object user) {
            this.user = user;
        }

        public void setReturnMsg(MsgHandler returnMsg) {
            this.returnMsg = returnMsg;
        }
    }

    public static StateManagement stateManagement;
    private static NotesController notesController;
    private MysqlConnection mysqlConnection;
    private entity.Test regularTest;


    @BeforeEach
    void setUp() {
        notesController = new NotesController(new NotesControllerStub());
        mysqlConnection = new MysqlConnection();
        mysqlConnection.connectToDb("Aa123456");

        regularTest = new entity.Test(
                "20",
                "010101",
                "Roman Gury",
                "10",
                "Algebra",
                "abc",
                TestTypeEnum.C,
                "def",
                "Math",
                "2023",
                "A",
                "A",
                "01"
        );
    }

    /**
     * functionality: server response to successful test creation
     * input: Test regularTest
     * expected result: "Server saved the test" response message from the server
     */
    @Test
    public void createTestSuccess() {

        stubServerClientCommunication iServerClientCommunication = new stubServerClientCommunication();

        notesController.setTest(regularTest);
        notesController.setiServerClientCommunication(iServerClientCommunication);
        iServerClientCommunication.setReturnMsg(new MsgHandler(TypeMsg.AddNewTestResponse, regularTest));

        assertEquals(notesController.getTest(), regularTest);

        Object msg = iServerClientCommunication.getReturnMsg().getMsg();
        entity.Test test = (entity.Test) msg;

        assertEquals(test.getId(), regularTest.getId());
        assertEquals(iServerClientCommunication.getReturnMsg().getType(), TypeMsg.AddNewTestResponse);
    }


    public static class NotesControllerStub implements InotesController {

        public NotesControllerStub() {
        }

        @Override
        public void deleteTestIfAlreadyExists() {
            if (notesController.getTest() != null) {
                notesController.setTest(null);
            }
        }

        @Override
        public void addTestToDB() {

            entity.Test newTest = new entity.Test(
                    stateManagement.getTestNum(),
                    stateManagement.getTestID(),
                    stateManagement.getAuthor(),
                    stateManagement.getTestDuration(),
                    stateManagement.getCourse().getCourseName(),
                    stateManagement.getTeacherComment(),
                    stateManagement.getTestType(),
                    stateManagement.getStudentComment(),
                    stateManagement.getCourse().getSubjectName(),
                    stateManagement.getYear(),
                    stateManagement.getSession(),
                    stateManagement.getSemester(),
                    stateManagement.getSubjectID()
            );

            //ClientUI.chat.accept(newTest);
            notesController.setTest(newTest);

        }

        @Override
        public void addAllTestQuestionsToDB() {
        }

        @Override
        public void replaceScreen(ActionEvent event) {
        }

        @Override
        public void checkNotes() {

        }
    }

}