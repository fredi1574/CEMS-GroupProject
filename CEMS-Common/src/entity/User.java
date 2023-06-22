package entity;


import java.io.Serializable;

public class User implements Serializable {
    
    String id, firstName, lastName, userName, password, email,role,phone;
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
     * @param phone the phone number of the user
     */
    public User(String id, String firstName, String lastName, String userName, String password, String email, String role,int isLoggedIn,String phone) {
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

    // Getters and Setters

    /**
     * Returns the ID of the user.
     * 
     * @return the ID of the user
     */
    public String getId() {
        return id;
    }
    public String getName() {
        return firstName;
    }
    public String getPhone() {
        return phone;
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
