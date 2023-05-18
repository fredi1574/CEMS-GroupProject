package entity;


public abstract class User {
    
    String id, firstName, lastName, userName, password, email;
	Role role;
   

    public enum Role {
        student, lecturer, headofdepartment
    }

    /**
     * Constructs a User object with the specified parameters.
     * 
     * @param id        the ID of the user
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param userName  the username of the user
     * @param password  the password of the user
     * @param email     the email address of the user
     */
    protected User(String id, String firstName, String lastName, String userName, String password, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters

    /**
     * Returns the ID of the user.
     * 
     * @return the ID of the user
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     * 
     * @param id the ID to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the first name of the user.
     * 
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     * 
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the user.
     * 
     * @return the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     * 
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the full name of the user.
     * 
     * @return the full name of the user
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Returns the email address of the user.
     * 
     * @return the email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     * 
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the role of the user.
     * 
     * @return the role of the user
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     * 
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Returns the username of the user.
     * 
     * @return the username of the user
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username of the user.
     * 
     * @param userName the username to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the password of the user.
     * 
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Checks if this user is equal to the given object.
     * The user will be considered equal if their IDs are the same.
     * 
     * @param obj the object to compare
     * @return true if the user is equal to the object, false otherwise
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
}
