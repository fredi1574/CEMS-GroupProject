package entity;

public class Headofdepartment extends User {

    /**
     * Constructs a Principal object with the specified parameters.
     * The role is set as a principal.
     * 
     * @param id        the ID of the Headofdepartment
     * @param firstName the first name of the Headofdepartment
     * @param lastName  the last name of the Headofdepartment
     * @param userName  the username of the Headofdepartment
     * @param password  the password of the Headofdepartment
     * @param email     the email address of the Headofdepartment
     * @param role      the role of the Headofdepartment
     */
    public Headofdepartment(String id, String firstName, String lastName, String userName, String password, String email,
            String role) {
        super(id, firstName, lastName, userName, password, email,role);
    }
}
