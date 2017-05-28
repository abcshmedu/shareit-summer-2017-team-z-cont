package edu.hm.REST;


import edu.hm.model.User;

import java.util.ArrayList;

/**
 * Created by Maximilian on 28.05.2017.
 */
public interface UserAdminAccess {

    /**
     * creates a new User with the given Username and Password.
     * @param username the new Users Username
     * @param password the new Users Password
     * @return the new User
     */
    User createUser(String username, String password);

    /**
     * creates a new User with Username, Password, for and surname.
     * @param username the new Users username
     * @param password the new Users password
     * @param forname the Users forname
     * @param surname the Users surname
     * @return the newly created User
     */
    User createUser(String username, String password, String forname, String surname);

    /**
     * allows a User to log in.
     * @param username the Username used in the login attempt
     * @param password the password used in the Login attempt
     * @return an error message or the users new token if succesfull
     */
    String logIn(String username, String password);

    /**
     * allows a user to log out.
     * @param token the users current token
     */
    void logOut(String token);

    /**
     * Allows an Admin to promote another USer to admin status.
     * @param username the username of the user to be promoted
     * @param token the admins token
     * @return a boolean the represents if this worked or not
     */
    boolean makeAdmin(String username, String token);

    /**
     * used by an admin to activate a user.
     * @param username the user to be activated
     * @param token the admisn token
     * @return a boolean indicating if this worked
     */
    boolean activateUser(String username, String token);

    /**
     * returns the USer with the given Username.
     * @param username the username that is searched for
     * @return the found user
     */
    User findUser(String username);

    /**
     * returns all Users.
     * @return a List of all users
     */
    ArrayList<User> getAllUsers();

    /**
     * returns all Admins for another admin.
     * @param token the Admins token
     * @return a List of all admins
     */
    ArrayList<User> getAllAdmins(String token);

    /**
     * allows a User to change his username.
     * @param forename the new forename
     * @param token the users valid token
     */
    void changeForename(String forename, String token);

    /**
     * allows a User to change his surname.
     * @param surname the new surname
     * @param token the users valid token
     */
    void changeSurname(String surname, String token);

    /**
     * validates a given Token.
     * @param token the token to be checked
     * @return true if the token is in use and valid, false if not
     */
    boolean checkToken(String token);

    /**
     * returrns the user corresponding to the given token.
     * @param token the token whose user is searched
     * @return the found user, or null if no User was found
     */
    User getUserForToken(String token);
}
