import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.User;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginServerTest {
private MysqlConnection mysqlConnection = MysqlConnection.getInstance() ;
    User userStudent;
    User userLecturer;
    User getUserLecturerTryLogIN;
    User userHeadOfDepartment;
    @BeforeEach
    public void setUp(){
        mysqlConnection.connectToDb("AB12345tre");
        userStudent = new User();
        userLecturer  = new User();
        getUserLecturerTryLogIN = new User();
        userHeadOfDepartment = new User();

        //student
        userStudent.setUserName("AbedTayer");
        userStudent.setPassword("a");
        userStudent.setRole("Student");
        userStudent.setId("5");
        userStudent.setIsLoggedIn(0);

        //Lecturer
        userLecturer.setUserName("Darwin");
        userLecturer.setIsLoggedIn(1);
        userLecturer.setRole("Lecturer");
        userLecturer.setPassword("a");
        userLecturer.setId("11");

        //Lecturer Try To Login
        getUserLecturerTryLogIN.setUserName("Tanya");
        getUserLecturerTryLogIN.setPassword("a");
        getUserLecturerTryLogIN.setId("8");
        getUserLecturerTryLogIN.setRole("Lecturer");
        getUserLecturerTryLogIN.setIsLoggedIn(0);

        //userHeadOfDepartment
        userHeadOfDepartment.setUserName("FrediBul");
        userHeadOfDepartment.setPassword("a");
        userHeadOfDepartment.setRole("Head of Department/Lecturer");
        userHeadOfDepartment.setIsLoggedIn(0);
        userHeadOfDepartment.setId("3");
    }
    /**

     Functionality: Test whether the method returns null when the username and password combination is not found in the MySQL database.
     Input: userName = "sergi", password = "a"
     Expected result: null
     */
    @Test
    public void studentNotFound(){
        Object expected = null;
        Object actula = mysqlConnection.authenticateUser("sergi","a");
        assertEquals(actula,expected);
    }
         // Abed Tayar is True
         //   Abed Tayer Wrong
    /**

     Functionality: Test whether the method successfully authenticates a student user by providing the correct username and password.
     Input: userName = "AbedTayer", password = "a"
     Expected result:
     The actual user object returned by the method should have the same ID as the expected student user object.
     The actual user object should have the same role as the expected student user object.
     The actual user object should have the same password as the expected student user object.
     The actual user object should have the same login status (isLoggedIn) as the expected student user object.
     */
    @Test
    public void studentsuccessToLogIn(){

        User actual = (User) mysqlConnection.authenticateUser("AbedTayer","a");
        assertEquals(actual.getId(),userStudent.getId());
        assertEquals(actual.getRole(),userStudent.getRole());
        assertEquals(actual.getPassword(),userStudent.getPassword());
        assertEquals(actual.getIsLoggedIn(),userStudent.getIsLoggedIn());
    }
    /**

     Functionality: Test whether the method successfully authenticates a lecturer user by providing the correct username and password.
     Input: userName = "Darwin", password = "a"
     Expected result:
     The actual user object returned by the method should have the same ID as the expected lecturer user object.
     The actual user object should have the same role as the expected lecturer user object.
     The actual user object should have the same password as the expected lecturer user object.
     The actual user object should have the same login status (isLoggedIn) as the expected lecturer user object.
     */
    @Test
    public void LecturerIsLogged(){

        User actual = (User) mysqlConnection.authenticateUser("Darwin","a");
        assertEquals(actual.getId(),userLecturer.getId());
        assertEquals(actual.getRole(),userLecturer.getRole());
        assertEquals(actual.getPassword(),userLecturer.getPassword());
        assertEquals(actual.getIsLoggedIn(),userLecturer.getIsLoggedIn());

    }
    /**

     Functionality: Test whether the method handles the case when a lecturer user tries to log in with an incorrect username and password combination.
     Input: userName = "Tanya", password = "a"
     Expected result:
     The actual user object returned by the method should have the same ID as the expected lecturer user object with failed login attempts.
     The actual user object should have the same role as the expected lecturer user object with failed login attempts.
     The actual user object should have the same password as the expected lecturer user object with failed login attempts.
     The actual user object should have the same login status (isLoggedIn) as the expected lecturer user object with failed login attempts.
     */
    @Test
    public void LecturerTryToLogIn(){

        User actual = (User) mysqlConnection.authenticateUser("Tanya","a");
        assertEquals(actual.getId(),getUserLecturerTryLogIN.getId());
        assertEquals(actual.getRole(),getUserLecturerTryLogIN.getRole());
        assertEquals(actual.getPassword(),getUserLecturerTryLogIN.getPassword());
        assertEquals(actual.getIsLoggedIn(),getUserLecturerTryLogIN.getIsLoggedIn());

    }
    /**

     Functionality: Test whether the method successfully authenticates a head of department user by providing the correct username and password.
     Input: userName = "FrediBul", password = "a"
     Expected result:
     The actual user object returned by the method should have the same ID as the expected head of department user object.
     The actual user object should have the same role as the expected head of department user object.
     The actual user object should have the same password as the expected head of department user object.
     The actual user object should have the same login status (isLoggedIn) as the expected head of department user object.
     */
    @Test
    public void HeadOfDeprtmentTryToLogIn(){

        User actual = (User) mysqlConnection.authenticateUser("FrediBul","a");
        assertEquals(actual.getId(),userHeadOfDepartment.getId());
        assertEquals(actual.getRole(),userHeadOfDepartment.getRole());
        assertEquals(actual.getPassword(),userHeadOfDepartment.getPassword());
        assertEquals(actual.getIsLoggedIn(),userHeadOfDepartment.getIsLoggedIn());
    }

}
