package edu.hm.model;

/**
 * Created by Maximilian on 21.04.2017.
 */
public class User {


    private String username;
    private String forename;
    private String surname;
    private boolean isAdmin;
    private boolean isActivated;
    private String password;

    /**
     * ctor for new users.
     * @param username the username of the new user
     * @param password the users password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        isAdmin = false;
        isActivated = false;
    }

    /**
     * setter for the forname.
     * @param forename the new forename of the user
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * setter for the users surname.
     * @param surname the users new surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * setter for the is admin boolean.
     * @param admin if the User is an admin or not
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /**
     * setter for the users activation status.
     * @param activated boolean if the user is activated or not
     */
    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    /**
     * getter for the forname.
     * @return the Forname iof the user as a string
     */
    public String getForename() {
        return forename;
    }

    /**
     * getter for the username.
     * @return the username as a String
     */
    public String getUsername() {
        return username;
    }

    /**
     * getter for the useres surname.
     * @return the Users surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * getter for the users password.
     * @return the users password as a String
     */
    public String getPassword() {
        return password;
    }

    /**
     * getter for isAdmin.
     * @return a boolean tellign wether the user is an admin or not
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * getter for isActivted.
     * @return a boolean tellign if the USer is activated or not
     */
    public boolean isActivated() {
        return isActivated;
    }

    @Override
    public String toString() {
        return "User{"
                + "username='"
                + username
                + '\''
                + ", forename='"
                + forename
                + '\''
                + ", surname='"
                + surname
                + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (isAdmin() != user.isAdmin()) {
            return false;
        }
        if (isActivated() != user.isActivated()) {
            return false;
        }
        if (!getUsername().equals(user.getUsername())) {
            return false;
        }
        if (getForename() != null ? !getForename().equals(user.getForename()) : user.getForename() != null) {
            return false;
        }
        if (getSurname() != null ? !getSurname().equals(user.getSurname()) : user.getSurname() != null) {
            return false;
        }
        return getPassword().equals(user.getPassword());

    }

    @Override
    public int hashCode() {
        int result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getForename() != null ? getForename().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (isAdmin() ? 1 : 0);
        result = 31 * result + (isActivated() ? 1 : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
    }
}
