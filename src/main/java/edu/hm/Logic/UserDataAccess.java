package edu.hm.Logic;

import edu.hm.model.User;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public interface UserDataAccess {

    /**
     * adds a User with the given username and password.
     * @param username the Username of the new User
     * @param password the new Users password
     * @return the new User
     */
    User addUser(String username, String password);

    /**
     * finds a User by its username.
     * @param username the username to be searched
     * @return the found User
     */
    User findUserByUsername(String username);

    /**
     * returns all Users as an ArrayList.
     * @return a List of all Users
     */
    ArrayList<User> getAllUsers();

    /**
     * signals the persistence layer to save changes.
     */
    void saveChanges();
}
