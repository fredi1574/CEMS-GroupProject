package application.loginWindowScreen.JUnitTest;

import Client.Client;
import Client.ClientUI;
import application.loginWindowScreen.LoginWindowController;
import org.junit.Before;
import org.junit.Test;
import util.MsgHandler;
import util.TypeMsg;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class LoginWindowControllerTest {
   private LoginWindowController loginWindowController;
    private String userName;
    private String password;
    private String role;
    List<String> UserToLogin ;
    @Before
    public void setUp() throws Exception {
        loginWindowController = new LoginWindowController();
    }

    /**
     * Functionality:
     * input:
     * expected :
     */
    @Test
    public void Login_EnterPasswordButNotUser(){

        userName = "";
        password = "4";
        assertFalse(loginWindowController.isNotEmptyUser(userName,password));
    }
    /**
     * Functionality:
     * input:
     * expected :
     */
    @Test
    public void Login_EnterUserButNotPassword(){
        userName = "Yuival";
        password = "";
        assertFalse(loginWindowController.isNotEmptyUser(userName,password));
    }
    /**
     * Functionality:
     * input:
     * expected :
     */
    @Test
    public void Login_NotEnterUserAndNotPassword(){
        userName = "";
        password = "";
        assertFalse(loginWindowController.isNotEmptyUser(userName,password));
    }
    /**
     * Functionality:
     * input:
     * expected :
     */
    @Test
    public void Login_EnterUserAndPassword(){
        userName = "AbedTayer";
        password = "a";
        assertTrue(loginWindowController.isNotEmptyUser(userName,password));
    }
    /*
    @Test
    public void Login_EnterNullUserAndNullPassword(){
        String expected = "null";
        userName = null;
        password = null;
        try {
            loginWindowController.isNotEmptyUser(userName, password);
        }catch (Exception e){
            assertEquals(expected,"null");
        }

    }

     */


}