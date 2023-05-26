package entity;

import java.io.Serializable;

public class LoggedInUser extends User implements Serializable {
    private static LoggedInUser instance;
    public static User authenticatedUser;

    public LoggedInUser(String id, String firstName, String lastName, String userName, String password, String email,String role) {
        super(id, firstName, lastName, userName, password, email,role);
    }

    public static User getAuthenticatedUser() {
        return authenticatedUser;
    }

    public static void setAuthenticatedUser(User user) {
        authenticatedUser = user;
    }


    public String getName() {
        return getFirstName() + " " + getLastName();
    }

    @Override
    public String getRole() {
        return super.getRole();
    }
}