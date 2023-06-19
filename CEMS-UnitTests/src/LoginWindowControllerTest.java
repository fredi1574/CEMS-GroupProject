import application.loginWindowScreen.LoginWindowController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginWindowControllerTest {

    @Mock
    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() {

        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

    }


    @Test
    public void Login_EnterPasswordButNotUser() throws SQLException {

//        when(mockConnection.createStatement()).thenReturn(mockStatement);
//        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
//        when(mockResultSet.next()).thenReturn(false);

       String userName = "";
       String password = "4";

        LoginWindowController loginWindowController = new LoginWindowController();

        assertFalse(loginWindowController.isNotEmptyUser(userName, password));
    }
}