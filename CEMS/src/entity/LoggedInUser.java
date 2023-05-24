package entity;

public class LoggedInUser extends User {
    public static User authenticatedUser;

    public LoggedInUser(String id, String firstName, String lastName, String userName, String password, String email) {
        super(id, firstName, lastName, userName, password, email);
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
    public Role getRole() {
        return super.getRole();
    }
}