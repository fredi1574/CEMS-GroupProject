package entity;

import java.io.Serializable;
/**
 * Represents a user.
 */
public class User implements Serializable {

     String id;
     String firstName;
     String lastName;
     String userName;
     String password;
     String email;
     String role;
     String phone;
     int isLoggedIn;

    /**
     * Constructs a User object with the specified parameters.
     *
     * @param id        The ID of the user.
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param userName  The username of the user.
     * @param password  The password of the user.
     * @param email     The email address of the user.
     * @param role      The role of the user.
     * @param isLoggedIn The login status of the user.
     * @param phone     The phone number of the user.
     */
    public User(String id, String firstName, String lastName, String userName, String password, String email, String role, int isLoggedIn, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.isLoggedIn = isLoggedIn;
        this.phone = phone;
    }
    public User(){

    }

    /**
     * Retrieves the ID of the user.
     *
     * @return The ID of the user.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the first name of the user.
     *
     * @return The first name of the user.
     */
    public String getName() {
        return firstName;
    }

    /**
     * Retrieves the last name of the user.
     *
     * @return The last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Retrieves the full name of the user.
     *
     * @return The full name of the user.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Retrieves the username of the user.
     *
     * @return The username of the user.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the role of the user.
     *
     * @return The role of the user.
     */
    public String getRole() {
        return role;
    }

    /**
     * Retrieves the phone number of the user.
     *
     * @return The phone number of the user.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Retrieves the login status of the user.
     *
     * @return The login status of the user.
     */
    public int getIsLoggedIn() {
        return isLoggedIn;
    }

    /**
     * Checks if this User object is equal to another object.
     *
     * @param obj The object to compare to.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User other = (User) obj;
            return this.id.equals(other.id);
        } else if (obj instanceof String) {
            String other = (String) obj;
            return this.id.equals(other);
        }
        return false;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIsLoggedIn(int isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
}
