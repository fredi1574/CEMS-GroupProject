package entity;

public class Student extends User {

    /**
     * Constructs a Student object with the specified parameters.
     * The role is set as a student.
     * 
     * @param id        the ID of the student
     * @param firstName the first name of the student
     * @param lastName  the last name of the student
     * @param userName  the username of the student
     * @param password  the password of the student
     * @param email     the email address of the student
     * @param role      the role of the student
     */
    public Student(String id, String firstName, String lastName, String userName, String password, String email,
            String role) {
        super(id, firstName, lastName, userName, password, email);
        this.role = Role.student;
    }
}
