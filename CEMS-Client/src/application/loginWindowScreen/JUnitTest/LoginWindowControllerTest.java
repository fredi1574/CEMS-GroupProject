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

import static org.junit.Assert.assertFalse;
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
     * Functionality: Test whether the method returns false when the username is empty and the password is entered.
     * input: userName = "", password = "4"
     * expected: false
     */
    @Test
    public void Login_EnterPasswordButNotUser(){
        userName = "";
        password = "4";
        assertFalse(loginWindowController.isNotEmptyUser(userName,password));
    }

    /**
     * Functionality: Test whether the method returns false when the username is entered and the password is empty.
     * input: userName = "Yuval", password = ""
     * expected: false
     */
    @Test
    public void Login_EnterUserButNotPassword(){
        userName = "Yuval";
        password = "";
        assertFalse(loginWindowController.isNotEmptyUser(userName,password));
    }

    /**
     * Functionality: Test whether the method returns false when both the username and password are empty.
     * input: userName = "", password = ""
     * expected: false
     */
    @Test
    public void Login_NotEnterUserAndNotPassword(){
        userName = "";
        password = "";
        assertFalse(loginWindowController.isNotEmptyUser(userName,password));
    }

    /**
     * Functionality: Test whether the method returns true when both the username and password are entered.
     * input: userName = "AbedTayer", password = "a"
     * expected: true
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