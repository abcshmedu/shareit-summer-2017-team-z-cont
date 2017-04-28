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

    public User(String username, String password){
        this.username = username;
        this.password = password;
        isAdmin = false;
        isActivated = false;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public String getForename() {
        return forename;
    }

    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isActivated() {
        return isActivated;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (isAdmin() != user.isAdmin()) return false;
        if (isActivated() != user.isActivated()) return false;
        if (!getUsername().equals(user.getUsername())) return false;
        if (getForename() != null ? !getForename().equals(user.getForename()) : user.getForename() != null)
            return false;
        if (getSurname() != null ? !getSurname().equals(user.getSurname()) : user.getSurname() != null) return false;
        return getPassword().equals(user.getPassword());

    }
}
