package application.loginWindowScreen.JUnitTest;

import Client.Client;
import Client.ClientUI;
import application.loginWindowScreen.IServerClientCommunication;
import application.loginWindowScreen.LoginWindowController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.Test;
import util.MsgHandler;
import util.TypeMsg;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import javafx.event.ActionEvent;
public class LoginWindowControllerTest {
    private String userName;
    private String password;
    private String role;
    List<String> UserToLogin ;
    private IServerClientCommunication iServerClientCommunication;
    private  LoginWindowController loginWindowController;
/*
     class stubServerClientCommunication implements IServerClientCommunication {
     Client client;
        @Override
        public void sendToServer(Object msg) {
            client.handleMessageFromClientUI(msg);
        }

        @Override
        public MsgHandler<Object> getServerMsg() {

            return client.messageFromServer;
        }
    }


 */
    @Before
    public void setUp() throws Exception {
        loginWindowController = new LoginWindowController();
        iServerClientCommunication = loginWindowController.new LoginServerClientCommunication();
        loginWindowController.setiServerClientCommunication(iServerClientCommunication);
         UserToLogin = new ArrayList<>();
    }


    @Test
    public void studentSuccessfulLogin() throws IllegalAccessException {
        userName = "AbedTayer";
        password = "a";
        UserToLogin.add(userName);
        UserToLogin.add(password);
        MsgHandler Login = new MsgHandler(TypeMsg.TryLogin,UserToLogin);
        assertEquals(iServerClientCommunication.sendToServer(Login),TypeMsg.LoginResponse);
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