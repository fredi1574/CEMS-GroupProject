import application.loginWindowScreen.LoginWindowController;
import entity.ILoginGetUserInput;
import entity.IServerClientCommunication;
import entity.User;
import org.junit.jupiter.api.Test;


import java.io.IOException;


import util.MsgHandler;
import util.TypeMsg;

import static org.junit.jupiter.api.Assertions.*;

class LoginWindowControllerTest {

    private class stubServerClientCommunication implements IServerClientCommunication { //stub for communication between server and client
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



    private class stubILoginGetUserInput implements ILoginGetUserInput { //stub for Login

        private String ID;
        private String role;
        private String errorMsg;
        private String password;

        public stubILoginGetUserInput(String ID, String password) {
            this.ID = ID;
            this.password = password;
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

        @Override
        public String geteErrorMsg() {
            return errorMsg;
        }
    }

    /**
     * Functionality: Test whether the method returns true when the username is empty and the password is entered.
     * Input: userName = "", password = "4"
     * Expected result: true
     */
    @Test
    public void Login_EnterPasswordButNotUser() throws IOException {
        LoginWindowController myCon = new LoginWindowController();
        String userName = "";
        String password = "4";
        assertTrue(myCon.isEmptyDetails(userName, password));
    }

    /**
     * Functionality: Test whether the method returns true when the username is entered and the password is empty.
     * Input: userName = "Yuval", password = ""
     * Expected result: true
     */
    @Test
    public void Login_EnterUserButNotPassword() throws IOException {
        LoginWindowController myCon = new LoginWindowController();
        String userName = "Yuval";
        String password = "";
        assertTrue(myCon.isEmptyDetails(userName, password));
    }

    /**
     * Functionality: Test whether the method returns true when both the username and password are empty.
     * Input: userName = "", password = ""
     * Expected result: true
     */
    @Test
    public void Login_NotEnterUserAndNotPassword() throws IOException {
        LoginWindowController myCon = new LoginWindowController();
        String userName = "";
        String password = "";
        assertTrue(myCon.isEmptyDetails(userName, password));
    }

    /**
     * Functionality: Test whether the method returns false when both the username and password are entered.
     * Input: userName = "AbedTayer", password = "a"
     * Expected result: false
     */
    @Test
    public void Login_EnterUserAndPassword() throws IOException {
        LoginWindowController myCon = new LoginWindowController();
        String userName = "AbedTayer";
        String password = "a";
        assertFalse(myCon.isEmptyDetails(userName, password));
    }

    /**
     * Functionality: Test whether the isEmptyDetails method handles the case when both the username and password are null.
     * Input: userName = null, password = null
     * Expected result: The method should throw an exception and the expected exception message should be "null"
     */
    @Test
    public void Login_NullUsernameAndPassword() throws IOException {
        LoginWindowController myCon = new LoginWindowController();
        String userName = null;
        String password = null;
        assertThrows(Exception.class, () -> myCon.isEmptyDetails(userName, password));
    }

    /**
     * Functionality: Test successful login with a student.
     * Input: userName = Abed, password = a,role = Student
     * Expected result: isEmptyDetails = false, user.getUserName() = Abed,user.getPassword() = a, iServerClientCommunication.errorMsg = null
     */

    @Test
    void Login_Successful_WithStudent() throws IOException {

        LoginWindowController myCon = new LoginWindowController();
        stubServerClientCommunication iServerClientCommunication = new stubServerClientCommunication();
        String userName = "Abed";
        String passWord = "a";
        String role = "Student";
        User myStudent = new User();
        myStudent.setUserName(userName);
        myStudent.setPassword(passWord);
        myStudent.setRole(role);
        ILoginGetUserInput iLoginGetUserInput = new stubILoginGetUserInput(userName, passWord);
        myCon.setiLoginGetUserInput(iLoginGetUserInput);
        myCon.setiServerClientCommunication(iServerClientCommunication);
        iServerClientCommunication.setReturnMsg(new MsgHandler(TypeMsg.LoginResponse, myStudent));
        assertEquals(myCon.isEmptyDetails(userName, passWord), false);
        Object msg = iServerClientCommunication.getReturnMsg().getMsg();
        User user = (User) msg;
        assertEquals(user.getUserName(), myStudent.getUserName());
        assertEquals(user.getPassword(), myStudent.getPassword());
        assertEquals(iServerClientCommunication.errorMsg, null);

    }


    /**
     * Functionality: Test whether a student is already logged in.
     * Input: userName = NoaKrisp, password = a,role = Student
     * Expected result: isEmptyDetails = true, iServerClientCommunication.getUser() = null, iServerClientCommunication.errorMsg = "This user is already logged in"
     */
    @Test
    public void Login_StudentAlreadyLoggedIn() throws IOException {
        LoginWindowController myCon = new LoginWindowController();
        stubServerClientCommunication iServerClientCommunication = new stubServerClientCommunication();
        User myStudent = new User();
        String userName = "NoaKrisp";
        String passWord = "a";
        String role = "Student";
        myStudent.setUserName(userName);
        myStudent.setPassword(passWord);
        myStudent.setRole(role);
        ILoginGetUserInput iLoginGetUserInput = new stubILoginGetUserInput(userName, passWord);
        myCon.setiLoginGetUserInput(iLoginGetUserInput);
        myCon.setiServerClientCommunication(iServerClientCommunication);
        iServerClientCommunication.setReturnMsg(new MsgHandler(TypeMsg.LoginResponse, "logged in"));
        assertEquals(myCon.isAlreadyLoggedIn(), true);
        assertEquals(iServerClientCommunication.getReturnMsg().getMsg(), "logged in");
        assertEquals(null, iServerClientCommunication.getUser());
        assertEquals(iServerClientCommunication.errorMsg, "This user is already logged in");
    }


}