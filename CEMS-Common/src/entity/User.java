package entity;


import java.io.Serializable;

public class User implements Serializable {
    
    String id, firstName, lastName, userName, password, email,role;
    int isLoggedIn;


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
    public User(String id, String firstName, String lastName, String userName, String password, String email, String role,int isLoggedIn) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.isLoggedIn = isLoggedIn;
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
    public int getIsLoggedIn() {
        return isLoggedIn;
    }
    public void setIsLoggedIn() {
        this.isLoggedIn = isLoggedIn;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {return role;
    }
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


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
