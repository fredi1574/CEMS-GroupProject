import application.loginWindowScreen.LoginWindowController;
import entity.ILoginGetUserInput;
import entity.IServerClientCommunication;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;


import util.MsgHandler;
import util.TypeMsg;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
class LoginWindowControllerTest {

    private class stubServerClientCommunication implements IServerClientCommunication{
        private Object toServerMsg;
        private MsgHandler returnMsg;
        private String msg;

        private String errorMsg;

        private Object user;





        @Override
        public void sendToServer(Object msg) {
            toServerMsg=msg;

        }
        @Override
        public MsgHandler getReturnMsg() {

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




    }


    /**
     * @author guy, sharon
     * stub of ILoginGetUserInput
     * meant to set the input pressed by user.
     */
    private class stubILoginGetUserInput implements ILoginGetUserInput {

        private String ID;
        private String role;
        private String errorMsg;
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
        @Override
        public String geteErrorMsg(){
            return errorMsg;
        }
    }



    @Test
    void SuccesFullWithStudent() throws IOException {

            LoginWindowController myCon = new LoginWindowController();
            stubServerClientCommunication iServerClientCommunication = new stubServerClientCommunication();
            String userName = "Abed";
            String passWord = "a";
            String role = "Student";
            User myStudent = new User();
            myStudent.setUserName(userName);
            myStudent.setPassword(passWord);
            myStudent.setRole(role);
            ILoginGetUserInput iLoginGetUserInput = new stubILoginGetUserInput(userName,passWord);
            myCon.setiLoginGetUserInput(iLoginGetUserInput);
            myCon.setiServerClientCommunication(iServerClientCommunication);
            iServerClientCommunication.setReturnMsg(new MsgHandler(TypeMsg.LoginResponse,myStudent));
            assertEquals(myCon.isEmptyDetails(userName,passWord),true);
            Object msg = iServerClientCommunication.getReturnMsg().getMsg();
            User user = (User)msg;
            assertEquals(((User) msg).getUserName(),myStudent.getUserName());
            assertEquals(((User) msg).getPassword(),myStudent.getPassword());
            assertEquals(iServerClientCommunication.errorMsg,null);
         /*
            assertEquals(((String[])((ClientMessage)iServerClientCommunication.getToServerMsg()).getMessage())[1],passWord);
            assertEquals(iServerClientCommunication.getServerMsg().getType(),ServerMessageTypes.LOGIN_STUDENT);
            assertEquals(iServerClientCommunication.getServerMsg().getMessage(),myStudent);
            assertEquals(myStudent,iServerClientCommunication.getUser());
            assertEquals(iServerClientCommunication.errorMsg,null);

             */

        }
    @Test
    public void StudentAlreadyLoggedIn() throws IOException {
        LoginWindowController myCon = new LoginWindowController();
        stubServerClientCommunication iServerClientCommunication = new stubServerClientCommunication();
        User myStudent = new User();
        String userName = "NoaKrisp";
        String passWord = "a";
        String role = "Student";
        myStudent.setUserName(userName);
        myStudent.setPassword(passWord);
        myStudent.setRole(role);
        ILoginGetUserInput iLoginGetUserInput = new stubILoginGetUserInput(userName,passWord);
        myCon.setiLoginGetUserInput(iLoginGetUserInput);
        myCon.setiServerClientCommunication(iServerClientCommunication);
        iServerClientCommunication.setReturnMsg(new MsgHandler(TypeMsg.LoginResponse, "logged in"));
        assertEquals(myCon.isAlreadyLoggedIn(),false);;
        assertEquals(iServerClientCommunication.getReturnMsg().getMsg(),"logged in");
        assertEquals(null,iServerClientCommunication.getUser());
        assertEquals(iServerClientCommunication.errorMsg,"This user is already logged in");


    }
/**
 * Functionality: Test whether the method returns false when the username is empty and the password is entered.
 * Input: userName = "", password = "4"
 * Expected result: false
 */

    @Test
    public void Login_EnterPasswordButNotUser() throws IOException {
        LoginWindowController myCon = new LoginWindowController();
        String userName = "";
        String password = "4";
        assertFalse(myCon.isEmptyDetails(userName, password));
    }

    /**
     * Functionality: Test whether the method returns false when the username is entered and the password is empty.
     * Input: userName = "Yuval", password = ""
     * Expected result: false
     */

    @Test
    public void Login_EnterUserButNotPassword() throws IOException {
        LoginWindowController myCon = new LoginWindowController();
        String userName = "Yuval";
        String password = "";
        assertFalse(myCon.isEmptyDetails(userName, password));
    }

    /**
     * Functionality: Test whether the method returns false when both the username and password are empty.
     * Input: userName = "", password = ""
     * Expected result: false
     */

    @Test
    public void Login_NotEnterUserAndNotPassword() throws IOException {
        LoginWindowController myCon = new LoginWindowController();
        String userName = "";
        String password = "";
         assertFalse(myCon.isEmptyDetails(userName, password));
    }

    /**
     * Functionality: Test whether the method returns true when both the username and password are entered.
     * Input: userName = "AbedTayer", password = "a"
     * Expected result: true
     */

    @Test
    public void Login_EnterUserAndPassword() throws IOException {
        LoginWindowController myCon = new LoginWindowController();
        String userName = "AbedTayer";
        String password = "a";
        assertTrue(myCon.isEmptyDetails(userName, password));
    }


}