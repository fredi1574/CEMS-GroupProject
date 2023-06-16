package JunitTest;

import entity.User;

import java.util.ArrayList;

public class MysqlConnectionStub {

    private static boolean stubbedAuthenticationResult;

    public static void setStubbedAuthenticationResult(boolean result) {
        stubbedAuthenticationResult = result;
    }

    public static Object authenticateUser(String username, String password) {
        if (stubbedAuthenticationResult) {
            // Stub implementation for successful authentication

            // Test case 1: Valid username and password for a student role
            if (username.equals("student1") && password.equals("password123")) {
                User user = new User("1", "John", "Doe", username, password, "john.doe@example.com", "Student", 0, "123456789");
                return user;
            }

            // Test case 2: Valid username and password for a lecturer role
            if (username.equals("MayCaspi") && password.equals("a")) {
                User user = new User("2", "a", "a", username, password, "a", "Lecturer", 0, "052555444");
                return user;
            }

            // Test case 3: Valid username and password for a head of department role
            if (username.equals("hod1") && password.equals("admin123")) {
                User user = new User("3", "Mike", "Johnson", username, password, "mike.johnson@example.com", "Head of Department", 0, "456789123");
                return user;
            }
        } else {
            // Stub implementation for unsuccessful authentication
            return null;
        }

        return null;
    }

    public static ArrayList<User> getUser(String query) {
        // Stub implementation for getting user(s) from the database

        // You can create additional stubs for other database operations if needed

        return new ArrayList<>();  // Return an empty list for testing purposes
    }
}