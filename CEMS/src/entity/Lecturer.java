package entity;

public class Lecturer extends User {

    /**
     * Constructs a Lecturer object with the specified parameters.
     * The role is set as a teacher.
     * 
     * @param id        the ID of the Lecturer
     * @param firstName the first name of the Lecturer
     * @param lastName  the last name of the Lecturer
     * @param userName  the username of the Lecturer
     * @param password  the password of the Lecturer
     * @param email     the email address of the Lecturer
     * @param role      the role of the Lecturer
     */
    public Lecturer(String id, String firstName, String lastName, String userName, String password, String email,
            String role) {
        super(id, firstName, lastName, userName, password, email);
        this.role = Role.lecturer;
    }
}
