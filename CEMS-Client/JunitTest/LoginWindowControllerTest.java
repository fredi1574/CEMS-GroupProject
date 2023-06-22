import application.loginWindowScreen.LoginWindowController;
import entity.ILoginGetUserInput;
import entity.IServerClientCommunication;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import Client.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import util.MsgHandler;
import util.TypeMsg;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginWindowControllerTest {
    private class stubServerClientCommunication implements IServerClientCommunication{

        private Object toServerMsg;

        public Object getToServerMsg() {
            return toServerMsg;
        }

        private MsgHandler returnMsg;
        private String msg;

        private String errorMsg;

        private Object user;

        @Override
        public void sendToServer(Object msg) {
            toServerMsg=msg;

        }
        @Override
        public MsgHandler getServerMsg() {

            return returnMsg;
        }
        @Override
        public void popUpError(String msg) {

            errorMsg=msg;
        }
        @Override
        public void popUpMessage(String msg) {
            this.msg=msg;
        }

        @Override
        public Object getUser() {
            return user;
        }

        @Override
        public void setUser(Object user) {
            this.user=user;
        }

        public void setReturnMsg(MsgHandler returnMsg) {
            this.returnMsg = returnMsg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

    }


    /**
     * @author guy, sharon
     * stub of ILoginGetUserInput
     * meant to set the input pressed by user.
     */
    private class stubILoginGetUserInput implements ILoginGetUserInput {

        private String ID;
        private String role;

        private String password;

        public stubILoginGetUserInput(String ID, String password) {
            this.ID=ID;
            this.password=password;
        }
        @Override
        public String getUserID() {

            return ID;
        }

        @Override
        public String getUserPassword() {

            return password;
        }

        @Override
        public String getUserRole() {
            return role;
        }

    }


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void testIsNotEmptyUser() throws IOException {

            LoginWindowController myCon = new LoginWindowController();
            stubServerClientCommunication iServerClientCommunication = new stubServerClientCommunication();
            String userName = "Abed";
            String passWord = "a";
            User myStudent = new User();
            myStudent.setUserName(userName);
            myStudent.setPassword(passWord);
            ILoginGetUserInput iLoginGetUserInput = new stubILoginGetUserInput(userName,passWord);
            myCon.setiLoginGetUserInput(iLoginGetUserInput);
            myCon.setiServerClientCommunication(iServerClientCommunication);
            iServerClientCommunication.setReturnMsg(new MsgHandler(TypeMsg.LoginResponse, myStudent));
            assertEquals(myCon.isNotEmptyUser(userName,passWord),true);
            /*
            assertEquals(((ClientMessage)iServerClientCommunication.getToServerMsg()).getType(),ClientMessageType.LOGIN_PERSON);
            assertEquals(((String[])((ClientMessage)iServerClientCommunication.getToServerMsg()).getMessage())[0],userName);
            assertEquals(((String[])((ClientMessage)iServerClientCommunication.getToServerMsg()).getMessage())[1],passWord);
            assertEquals(iServerClientCommunication.getServerMsg().getType(),ServerMessageTypes.LOGIN_STUDENT);
            assertEquals(iServerClientCommunication.getServerMsg().getMessage(),myStudent);
            assertEquals(myStudent,iServerClientCommunication.getUser());
            assertEquals(iServerClientCommunication.errorMsg,null);

             */

        }
    @Test
    void testV() {

    }

    @Test
    void testLogIN() {
        // Test empty username and password fields

    }

}